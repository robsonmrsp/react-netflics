/* generated by JSetup v0.95 :  at 23 de jun de 2021 23:11:05 */
package br.com.netflics.core.reports;

import java.util.List;

import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class JasperMaker {
	public static String makePdfFile(String jrxmlFile, String fileName, Map<String, Object> parametros, List<?> beans) throws Exception {

		JRDataSource jrds = new JRBeanCollectionDataSource(beans);
		JasperReport report = JasperCompileManager.compileReport(jrxmlFile);
		JasperPrint print = JasperFillManager.fillReport(report, parametros, jrds);

		JasperExportManager.exportReportToPdfFile(print, fileName);

		return fileName;
	}
}