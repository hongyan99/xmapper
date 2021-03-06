package org.beanone.xmapper;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import org.beanone.flattener.FlattenerTool;

public class TestObjectFactory {
	private final Properties configuration;
	private final XMapperConfiguration mappingConfig;
	private final Map<String, String> attributeMap;
	private final Map<String, String> template;
	private final TestTargetBean templateObject;
	private final FlattenerTool flattenerTool = new FlattenerTool();
	private final TestSourceBean sourceBean;

	public TestObjectFactory() {
		try {
			this.configuration = TestUtil
			        .loadMappingProperties("test_mapping.properties");
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}
		this.mappingConfig = new XMapperConfiguration(this.configuration,
		        new AttributeHandlerRegistry());

		this.sourceBean = new TestSourceBean();
		this.sourceBean.setIntVal(20);
		this.sourceBean.setStrVal("string1");
		this.sourceBean.setFrom(new TestSourceBean());

		this.attributeMap = this.flattenerTool.flat(this.sourceBean);

		this.templateObject = new TestTargetBean();
		this.templateObject.setTo(new TestTargetBean());
		this.template = this.flattenerTool.flat(this.templateObject);
	}

	public TestSourceBean createTestSourceBean() {
		final TestSourceBean sourceBean = new TestSourceBean();
		sourceBean.setIntVal(20);
		sourceBean.setStrVal("string1");

		final TestSourceBean from = new TestSourceBean();
		from.setStrVal("test");
		from.setIntVal(9);
		sourceBean.setFrom(from);

		final TestSourceBean another = new TestSourceBean();
		another.setStrAnotherVal("test");
		another.setIntVal(16);
		sourceBean.setAnother(another);
		return sourceBean;
	}

	public Map<String, String> getAttributeMap() {
		return this.attributeMap;
	}

	public Properties getConfiguration() {
		return this.configuration;
	}

	public FlattenerTool getFlattenerTool() {
		return this.flattenerTool;
	}

	public XMapperConfiguration getMappingConfig() {
		return this.mappingConfig;
	}

	public TestSourceBean getSourceBean() {
		return this.sourceBean;
	}

	public Map<String, String> getTemplate() {
		return this.template;
	}

	public TestTargetBean getTemplateObject() {
		return this.templateObject;
	}

}
