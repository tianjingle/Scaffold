package com.inclination.scaffold.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.FullyQualifiedTable;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.InnerClass;
import org.mybatis.generator.api.dom.java.InnerEnum;
import org.mybatis.generator.api.dom.java.JavaElement;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.config.MergeConstants;
import org.mybatis.generator.config.PropertyRegistry;
/**
 * mybatis 自动注释
 *
 */
public class CnkiCommentGenerator implements CommentGenerator {

	private Properties properties;
	private Properties systemPro;
	private boolean suppressDate;
	private boolean suppressAllComments;
	private String currentDateStr;

	public CnkiCommentGenerator() {
		super();
		properties = new Properties();
		systemPro = System.getProperties();
		suppressDate = false;
		suppressAllComments = false;
		currentDateStr = (new SimpleDateFormat("yyyy-MM-dd")).format(new Date());
	}

	@Override
	public void addConfigurationProperties(Properties properties) {
		// TODO Auto-generated method stub
		this.properties.putAll(properties);

		suppressDate = isTrue(properties.getProperty(PropertyRegistry.COMMENT_GENERATOR_SUPPRESS_DATE));

		suppressAllComments = isTrue(properties.getProperty(PropertyRegistry.COMMENT_GENERATOR_SUPPRESS_ALL_COMMENTS));
	}

	private boolean isTrue(String property) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void addFieldComment(Field field, IntrospectedTable introspectedTable,
			IntrospectedColumn introspectedColumn) {
		// TODO Auto-generated method stub
		if (suppressAllComments) {
			return;
		}

		StringBuilder sb = new StringBuilder();

		field.addJavaDocLine("/**");
		sb.append(" * ");
		sb.append(introspectedColumn.getRemarks());
		field.addJavaDocLine(sb.toString());

		field.addJavaDocLine(" */");
	}

	@Override
	public void addFieldComment(Field field, IntrospectedTable introspectedTable) {
		// TODO Auto-generated method stub
		if (suppressAllComments) {
			return;
		}
		StringBuilder sb = new StringBuilder();
		field.addJavaDocLine("/**");
		sb.append(" * ");
		sb.append(introspectedTable.getFullyQualifiedTable());
		field.addJavaDocLine(sb.toString());
		field.addJavaDocLine(" */");
	}

	protected void addJavadocTag(JavaElement javaElement, boolean markAsDoNotDelete) {
		javaElement.addJavaDocLine(" *");
		StringBuilder sb = new StringBuilder();
		sb.append(" * ");
		sb.append(MergeConstants.NEW_ELEMENT_TAG);
		if (markAsDoNotDelete) {
			sb.append(" do_not_delete_during_merge");
		}
		String s = (String) getDateString();
		if (s != null) {
			sb.append(' ');
			sb.append(s);
		}
		javaElement.addJavaDocLine(sb.toString());
	}

	@Override
	public void addModelClassComment(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		// TODO Auto-generated method stub
		if (suppressAllComments) {
			return;
		}
		String author = properties.getProperty("author");
		if (StringUtils.isBlank(author)) {
			author = "Cnki";
		}
		String dateFormat = properties.getProperty("dateFormat", "yyyy-MM-dd");
		SimpleDateFormat dateFormatter = new SimpleDateFormat(dateFormat);

		FullyQualifiedTable fullyQualifiedTable = introspectedTable.getFullyQualifiedTable();


		FullyQualifiedJavaType imp = new FullyQualifiedJavaType("java.io.Serializable");
		topLevelClass.addImportedType(imp);

		topLevelClass.addJavaDocLine("/**");
		topLevelClass.addJavaDocLine(" * " + introspectedTable.getRemarks());
		topLevelClass.addJavaDocLine(" * " + fullyQualifiedTable.getIntrospectedTableName());
		topLevelClass.addJavaDocLine(" * @author " + author);
		topLevelClass.addJavaDocLine(" * @date " + dateFormatter.format(new Date()));
		topLevelClass.addJavaDocLine(" */");
	}

	private Object getDateString() {
		// TODO Auto-generated method stub
		String result = null;
		if (!suppressDate) {
			result = currentDateStr;
		}
		return result;
	}

	@Override
	public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable) {
		// TODO Auto-generated method stub
		if (false) {
			return;
		}
		StringBuilder sb = new StringBuilder();
		innerClass.addJavaDocLine("/**");
		sb.append(" * ");
		sb.append(introspectedTable.getFullyQualifiedTable());
		sb.append(" ");
		sb.append(getDateString());
		innerClass.addJavaDocLine(sb.toString());
		innerClass.addJavaDocLine(" */");

	}

	@Override
	public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable, boolean markAsDoNotDelete) {
		// TODO Auto-generated method stub
		if (false) {
			return;
		}
		StringBuilder sb = new StringBuilder();
		innerClass.addJavaDocLine("/**");
		sb.append(" * ");
		sb.append(introspectedTable.getFullyQualifiedTable());
		innerClass.addJavaDocLine(sb.toString());

		sb.setLength(0);
		sb.append(" * @author ");
		sb.append(systemPro.getProperty("user.name"));
		sb.append(" ");
		sb.append(currentDateStr);

		addJavadocTag(innerClass, markAsDoNotDelete);

		innerClass.addJavaDocLine(" */");
	}

	private void addJavadocTag(InnerClass innerClass, boolean markAsDoNotDelete) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addEnumComment(InnerEnum innerEnum, IntrospectedTable introspectedTable) {
		// TODO Auto-generated method stub
		if (suppressAllComments) {
			return;
		}

		StringBuilder sb = new StringBuilder();

		innerEnum.addJavaDocLine("/**");
		addJavadocTag(innerEnum, false);
		sb.append(" * ");
		sb.append(introspectedTable.getFullyQualifiedTable());
		innerEnum.addJavaDocLine(sb.toString());
		innerEnum.addJavaDocLine(" */");
	}

	private void addJavadocTag(InnerEnum innerEnum, boolean b) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addGetterComment(Method method, IntrospectedTable introspectedTable,
			IntrospectedColumn introspectedColumn) {
		// TODO Auto-generated method stub
		if (suppressAllComments) {
			return;
		}
		method.addJavaDocLine("/**");
		StringBuilder sb = new StringBuilder();
		sb.append(" * ");
		sb.append(introspectedColumn.getRemarks());
		method.addJavaDocLine(sb.toString());

		sb.setLength(0);
		sb.append(" * @return ");
		sb.append(introspectedColumn.getActualColumnName());
		sb.append(" ");
		sb.append(introspectedColumn.getRemarks());
		method.addJavaDocLine(sb.toString());

		addJavadocTag(method, false);

		method.addJavaDocLine(" */");
	}

	public void addGetterComment(Method method) {
		// TODO Auto-generated method stub
		method.addJavaDocLine("/**");
		StringBuilder sb = new StringBuilder();
		sb.append(" * @param ");
		sb.append(method.getParameters());
		method.addJavaDocLine(sb.toString());
		sb.setLength(0);
		sb.append(" * @return ");
		sb.append(method.getReturnType());
		sb.append(" ");
		method.addJavaDocLine(sb.toString());
		addJavadocTag(method, false);
		method.addJavaDocLine(" */");
	}

	private void addJavadocTag(Method method, boolean b) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addSetterComment(Method method, IntrospectedTable introspectedTable,
			IntrospectedColumn introspectedColumn) {
		// TODO Auto-generated method stub
		if (suppressAllComments) {
			return;
		}
		method.addJavaDocLine("/**");
		StringBuilder sb = new StringBuilder();
		sb.append(" * ");
		sb.append(introspectedColumn.getRemarks());
		method.addJavaDocLine(sb.toString());
		Parameter parm = method.getParameters().get(0);
		sb.setLength(0);
		sb.append(" * @param ");
		sb.append(parm.getName());
		sb.append(" ");
		sb.append(introspectedColumn.getRemarks());
		method.addJavaDocLine(sb.toString());
		addJavadocTag(method, false);
		method.addJavaDocLine(" */");
	}

	public void addSetterComment(Method method) {
		// TODO Auto-generated method stub
		if (suppressAllComments) {
			return;
		}
		method.addJavaDocLine("/**");
		StringBuilder sb = new StringBuilder();
		sb.append(" * ");
		method.addJavaDocLine(sb.toString());
		sb.setLength(0);
		sb.append(" * @param ").append(method.getParameters());
		sb.append(" * @return ");
		sb.append(method.getReturnType());
		sb.append(" ");
		method.addJavaDocLine(sb.toString());
		addJavadocTag(method, false);
		method.addJavaDocLine(" */");
	}

	@Override
	public void addGeneralMethodComment(Method method, IntrospectedTable introspectedTable) {
		// TODO Auto-generated method stub
		if (suppressAllComments) {
			return;
		}
		method.addJavaDocLine("/**");
		// addJavadocTag(method, false);
		StringBuilder sb = new StringBuilder();
		sb.append(" * ");
		sb.append(MergeConstants.NEW_ELEMENT_TAG);
		String s = method.getName();
		sb.append(' ');
		sb.append(s);
		method.addJavaDocLine(sb.toString());
		method.addJavaDocLine(" */");
	}

	@Override
	public void addJavaFileComment(CompilationUnit compilationUnit) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addComment(XmlElement xmlElement) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addRootComment(XmlElement rootElement) {
		// TODO Auto-generated method stub

	}

}
