package br.com.netflics.core.reports;

/* generated by JSetup v0.95 :  at 23 de jun de 2021 23:11:05 */
public interface CSVColumn<T> {

	String getHeader();

	Object getValue(T t);

}
