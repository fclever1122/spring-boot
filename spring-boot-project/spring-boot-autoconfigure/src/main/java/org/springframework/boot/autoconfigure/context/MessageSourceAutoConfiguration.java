/*
 * Copyright 2012-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.boot.autoconfigure.context;

import java.time.Duration;

import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionMessage;
import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.SearchStrategy;
import org.springframework.boot.autoconfigure.condition.SpringBootCondition;
import org.springframework.boot.autoconfigure.context.MessageSourceAutoConfiguration.ResourceBundleCondition;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.Ordered;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.ConcurrentReferenceHashMap;
import org.springframework.util.StringUtils;

/**
 * {@link EnableAutoConfiguration Auto-configuration} for {@link MessageSource}.
 *
 * @author Dave Syer
 * @author Phillip Webb
 * @author Eddú Meléndez
 * @since 1.5.0
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnMissingBean(name = AbstractApplicationContext.MESSAGE_SOURCE_BEAN_NAME, search = SearchStrategy.CURRENT)
@AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE)
@Conditional(ResourceBundleCondition.class)
@EnableConfigurationProperties
public class MessageSourceAutoConfiguration {

	private static final Resource[] NO_RESOURCES = {};

	/**
	 * 定义一个名为 messageSourceProperties 的 Bean，
	 * 并使用 @ConfigurationProperties 注解将前缀为 spring.messages 的配置属性绑定到该 Bean。
	 * MessageSourceProperties 类是用于封装消息源相关的配置属性
	 * @return
	 */
	@Bean
	@ConfigurationProperties(prefix = "spring.messages")
	public MessageSourceProperties messageSourceProperties() {
		return new MessageSourceProperties();
	}

	/**
	 *  定义一个名为 messageSource 的 Bean，并注入前面定义的 messageSourceProperties Bean。
	 *  在方法中创建一个 ResourceBundleMessageSource 的实例，
	 *  用于实现基于属性文件的消息源。根据 messageSourceProperties 中的配置，设置相应的属性值，
	 *  如基本名称（basenames）、编码（encoding）、是否使用系统区域设置（fallbackToSystemLocale）、缓存时长（cacheDuration）等。
	 * @param properties
	 * @return
	 */
	@Bean
	public MessageSource messageSource(MessageSourceProperties properties) {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		if (StringUtils.hasText(properties.getBasename())) {
			messageSource.setBasenames(StringUtils
					.commaDelimitedListToStringArray(StringUtils.trimAllWhitespace(properties.getBasename())));
		}
		if (properties.getEncoding() != null) {
			messageSource.setDefaultEncoding(properties.getEncoding().name());
		}
		messageSource.setFallbackToSystemLocale(properties.isFallbackToSystemLocale());
		Duration cacheDuration = properties.getCacheDuration();
		if (cacheDuration != null) {
			messageSource.setCacheMillis(cacheDuration.toMillis());
		}
		messageSource.setAlwaysUseMessageFormat(properties.isAlwaysUseMessageFormat());
		messageSource.setUseCodeAsDefaultMessage(properties.isUseCodeAsDefaultMessage());
		return messageSource;
	}

	/**
	 * 条件类，用于判断是否满足加载资源绑定的条件
	 */
	protected static class ResourceBundleCondition extends SpringBootCondition {

		private static ConcurrentReferenceHashMap<String, ConditionOutcome> cache = new ConcurrentReferenceHashMap<>();

		/**
		 * 在该方法中，通过读取配置的 spring.messages.basename 属性值来确定基本名称（basename），
		 * 如果配置值不存在，则默认为 "messages"。然后，遍历基本名称列表，尝试获取资源（properties 文件）并判断资源是否存在。
		 * 根据是否存在资源来确定是否满足加载资源绑定的条件。
		 * @param context the condition context
		 * @param metadata the annotation metadata
		 * @return
		 */
		@Override
		public ConditionOutcome getMatchOutcome(ConditionContext context, AnnotatedTypeMetadata metadata) {
			String basename = context.getEnvironment().getProperty("spring.messages.basename", "messages");
			ConditionOutcome outcome = cache.get(basename);
			if (outcome == null) {
				outcome = getMatchOutcomeForBasename(context, basename);
				cache.put(basename, outcome);
			}
			return outcome;
		}

		private ConditionOutcome getMatchOutcomeForBasename(ConditionContext context, String basename) {
			ConditionMessage.Builder message = ConditionMessage.forCondition("ResourceBundle");
			for (String name : StringUtils.commaDelimitedListToStringArray(StringUtils.trimAllWhitespace(basename))) {
				for (Resource resource : getResources(context.getClassLoader(), name)) {
					if (resource.exists()) {
						return ConditionOutcome.match(message.found("bundle").items(resource));
					}
				}
			}
			return ConditionOutcome.noMatch(message.didNotFind("bundle with basename " + basename).atAll());
		}

		/**
		 * 根据给定的类加载器和基本名称，获取对应的资源（properties 文件）数组。
		 * @param classLoader
		 * @param name
		 * @return
		 */
		private Resource[] getResources(ClassLoader classLoader, String name) {
			String target = name.replace('.', '/');
			try {
				return new PathMatchingResourcePatternResolver(classLoader)
						.getResources("classpath*:" + target + ".properties");
			}
			catch (Exception ex) {
				return NO_RESOURCES;
			}
		}

	}

}
