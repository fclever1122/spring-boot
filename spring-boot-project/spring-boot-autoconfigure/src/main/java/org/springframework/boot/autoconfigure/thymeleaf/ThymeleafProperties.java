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

package org.springframework.boot.autoconfigure.thymeleaf;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.MediaType;
import org.springframework.util.MimeType;
import org.springframework.util.unit.DataSize;

/**
 * Properties for Thymeleaf.
 *
 * @author Stephane Nicoll
 * @author Brian Clozel
 * @author Daniel Fernández
 * @author Kazuki Shimizu
 * @since 1.2.0
 */
@ConfigurationProperties(prefix = "spring.thymeleaf")
public class ThymeleafProperties {

	private static final Charset DEFAULT_ENCODING = StandardCharsets.UTF_8;

	// 资源查找路径-前缀
	public static final String DEFAULT_PREFIX = "classpath:/templates/";

	// 资源查找路径-后缀
	public static final String DEFAULT_SUFFIX = ".html";

	/**
	 * 渲染前是否检查模板是否存在
	 */
	private boolean checkTemplate = true;

	/**
	 * 是否检查模板位置是否存在.
	 */
	private boolean checkTemplateLocation = true;

	/**
	 * 在构建 URL 时添加到查看名称的前缀
	 */
	private String prefix = DEFAULT_PREFIX;

	/**
	 * 构建 URL 时附加到视图名称的后缀
	 */
	private String suffix = DEFAULT_SUFFIX;

	/**
	 * 要应用于模板的模板模式。另见 Thymeleaf 的 TemplateMode 枚举
	 */
	private String mode = "HTML";

	/**
	 * 模板文件编码。
	 */
	private Charset encoding = DEFAULT_ENCODING;

	/**
	 * 是否启用模板缓存。
	 */
	private boolean cache = true;

	/**
	 * 在模板解析器链中的顺序。默认选用的模板解析器是链中的第一个。
	 * Order 从 1 开始，只有在定义了额外的“TemplateResolver”bean 时才应设置。
	 */
	private Integer templateResolverOrder;

	/**
	 * 可以解析的以逗号分隔的视图名称列表（允许模式）。
	 */
	private String[] viewNames;

	/**
	 * 应从解析中排除的以逗号分隔的视图名称列表（允许模式）。
	 */
	private String[] excludedViewNames;

	/**
	 * 在 SpringEL 表达式中启用 SpringEL 编译器。
	 */
	private boolean enableSpringElCompiler;

	/**
	 * 作为复选框标记的隐藏表单输入是否应该在复选框元素本身之前呈现。
	 */
	private boolean renderHiddenMarkersBeforeCheckboxes = false;

	/**
	 * 是否为 Web 框架启用 Thymeleaf 视图解析。
	 */
	private boolean enabled = true;

	private final Servlet servlet = new Servlet();

	private final Reactive reactive = new Reactive();

	public boolean isEnabled() {
		return this.enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isCheckTemplate() {
		return this.checkTemplate;
	}

	public void setCheckTemplate(boolean checkTemplate) {
		this.checkTemplate = checkTemplate;
	}

	public boolean isCheckTemplateLocation() {
		return this.checkTemplateLocation;
	}

	public void setCheckTemplateLocation(boolean checkTemplateLocation) {
		this.checkTemplateLocation = checkTemplateLocation;
	}

	public String getPrefix() {
		return this.prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getSuffix() {
		return this.suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String getMode() {
		return this.mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public Charset getEncoding() {
		return this.encoding;
	}

	public void setEncoding(Charset encoding) {
		this.encoding = encoding;
	}

	public boolean isCache() {
		return this.cache;
	}

	public void setCache(boolean cache) {
		this.cache = cache;
	}

	public Integer getTemplateResolverOrder() {
		return this.templateResolverOrder;
	}

	public void setTemplateResolverOrder(Integer templateResolverOrder) {
		this.templateResolverOrder = templateResolverOrder;
	}

	public String[] getExcludedViewNames() {
		return this.excludedViewNames;
	}

	public void setExcludedViewNames(String[] excludedViewNames) {
		this.excludedViewNames = excludedViewNames;
	}

	public String[] getViewNames() {
		return this.viewNames;
	}

	public void setViewNames(String[] viewNames) {
		this.viewNames = viewNames;
	}

	public boolean isEnableSpringElCompiler() {
		return this.enableSpringElCompiler;
	}

	public void setEnableSpringElCompiler(boolean enableSpringElCompiler) {
		this.enableSpringElCompiler = enableSpringElCompiler;
	}

	public boolean isRenderHiddenMarkersBeforeCheckboxes() {
		return this.renderHiddenMarkersBeforeCheckboxes;
	}

	public void setRenderHiddenMarkersBeforeCheckboxes(boolean renderHiddenMarkersBeforeCheckboxes) {
		this.renderHiddenMarkersBeforeCheckboxes = renderHiddenMarkersBeforeCheckboxes;
	}

	public Reactive getReactive() {
		return this.reactive;
	}

	public Servlet getServlet() {
		return this.servlet;
	}

	public static class Servlet {

		/**
		 * Content-Type value written to HTTP responses.
		 */
		private MimeType contentType = MimeType.valueOf("text/html");

		/**
		 * Whether Thymeleaf should start writing partial output as soon as possible or
		 * buffer until template processing is finished.
		 */
		private boolean producePartialOutputWhileProcessing = true;

		public MimeType getContentType() {
			return this.contentType;
		}

		public void setContentType(MimeType contentType) {
			this.contentType = contentType;
		}

		public boolean isProducePartialOutputWhileProcessing() {
			return this.producePartialOutputWhileProcessing;
		}

		public void setProducePartialOutputWhileProcessing(boolean producePartialOutputWhileProcessing) {
			this.producePartialOutputWhileProcessing = producePartialOutputWhileProcessing;
		}

	}

	public static class Reactive {

		/**
		 * Maximum size of data buffers used for writing to the response. Templates will
		 * execute in CHUNKED mode by default if this is set.
		 */
		private DataSize maxChunkSize = DataSize.ofBytes(0);

		/**
		 * Media types supported by the view technology.
		 */
		private List<MediaType> mediaTypes;

		/**
		 * Comma-separated list of view names (patterns allowed) that should be executed
		 * in FULL mode even if a max chunk size is set.
		 */
		private String[] fullModeViewNames;

		/**
		 * Comma-separated list of view names (patterns allowed) that should be the only
		 * ones executed in CHUNKED mode when a max chunk size is set.
		 */
		private String[] chunkedModeViewNames;

		public List<MediaType> getMediaTypes() {
			return this.mediaTypes;
		}

		public void setMediaTypes(List<MediaType> mediaTypes) {
			this.mediaTypes = mediaTypes;
		}

		public DataSize getMaxChunkSize() {
			return this.maxChunkSize;
		}

		public void setMaxChunkSize(DataSize maxChunkSize) {
			this.maxChunkSize = maxChunkSize;
		}

		public String[] getFullModeViewNames() {
			return this.fullModeViewNames;
		}

		public void setFullModeViewNames(String[] fullModeViewNames) {
			this.fullModeViewNames = fullModeViewNames;
		}

		public String[] getChunkedModeViewNames() {
			return this.chunkedModeViewNames;
		}

		public void setChunkedModeViewNames(String[] chunkedModeViewNames) {
			this.chunkedModeViewNames = chunkedModeViewNames;
		}

	}

}
