
package br.com.netflics.core.persistence;

import java.util.regex.Matcher;

import java.util.regex.Pattern;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.aop.ThrowsAdvice;

import br.com.netflics.core.rs.exception.ValidationException;
/* generated by JSetup v0.95 :  at 23 de jun de 2021 23:11:04 */
public class CatchThrowConstraintViolationException implements ThrowsAdvice {

	public void afterThrowing(ConstraintViolationException exception) throws Throwable {
		InfoMessage infoMessage = null;
		if (exception.getCause().getClass().getName().equals("org.postgresql.util.PSQLException")) {
			infoMessage = new InfoPGSLMessage(exception.getCause().getMessage());
		}
		if (infoMessage != null) {
			throw new ValidationException(exception, "Já existe [" + infoMessage.getEntityName() + "] com [ " + infoMessage.getAttributeName() + " ] = [" + infoMessage.getFieldValue() + "] ");
		}
	}
	interface InfoMessage {
		String getEntityName();

		String getAttributeName();

		String getFieldName();

		String getTableName();

		String getConstraintName();

		String getFieldValue();
	}

	private class InfoPGSLMessage implements InfoMessage {
		private String entityName;
		private String fieldName;

		private String attributeName;

		private String fieldValue;
		private String tableName;
		private String constraintName;

		public InfoPGSLMessage(String message) {
			try {
				String group = "";
				Pattern p = Pattern.compile("[\"]+([\\S]+)[\"]");

				Matcher m = p.matcher(message);
				if (m.find()) {
					group = m.group(1);
					applyBasicValues(group);
				}
				String group2 = "";
				Pattern p2 = Pattern.compile("[\\(]+([\\S]+)[\\)]");
				Matcher m2 = p2.matcher(message);
				if (m2.find()) {
					group2 = m2.group(1);
					applyExtraValues(group2);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		private void applyBasicValues(String group) {
			int indexOf = group.indexOf("_");
			if (group.length() > 0 && indexOf > 0) {
				setConstraintName(group);
				setEntityName(group.substring(0, indexOf));
				setAttributeName(group.substring(indexOf + 1, group.length()));
			}
		}

		private void applyExtraValues(String group) {
			int indexOf = group.indexOf("=");
			if (group.length() > 0 && indexOf > 0) {
				setFieldName(group.substring(0, indexOf - 1));
				setFieldValue(group.substring(indexOf + 2, group.length()));
			}
		}

		public String getEntityName() {
			return entityName;
		}

		public void setEntityName(String entityName) {
			this.entityName = entityName;
		}

		public String getFieldName() {
			return fieldName;
		}

		public void setFieldName(String fieldName) {
			this.fieldName = fieldName;
		}

		public String getAttributeName() {
			return attributeName;
		}

		public void setAttributeName(String attributeName) {
			this.attributeName = attributeName;
		}

		public String getFieldValue() {
			return fieldValue;
		}

		public void setFieldValue(String fieldValue) {
			this.fieldValue = fieldValue;
		}

		public String getTableName() {
			return tableName;
		}

		public void setTableName(String tableName) {
			this.tableName = tableName;
		}

		public String getConstraintName() {
			return constraintName;
		}

		public void setConstraintName(String constraintName) {
			this.constraintName = constraintName;
		}

	}
}
