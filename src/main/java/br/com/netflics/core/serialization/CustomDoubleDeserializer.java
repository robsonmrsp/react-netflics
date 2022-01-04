/** generated by JSetup v0.95 :  at 23 de jun de 2021 23:11:05  */
package br.com.netflics.core.serialization;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class CustomDoubleDeserializer extends JsonDeserializer<Double> {
	public static final Logger LOGGER = LoggerFactory.getLogger(CustomDoubleDeserializer.class);
	@Override
	public Class<Double> handledType() {
		return Double.class;
	}

	@Override
	public Double deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {

		String dNumber = jp.getText();
		try {
			if (dNumber != null && dNumber.contains(",")) {
				dNumber = dNumber.replaceAll("\\.", "").replace(",", ".");
			}
			return new Double(dNumber);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.warn("Erro ao deserializar data: " + dNumber, e);
		}
		return null;
	}

	public static void main(String[] args) {

		String dNumber = "123.123,155";
		try {
			if (dNumber != null && dNumber.contains(",")) {
				dNumber = dNumber.replaceAll("\\.", "").replace(",", ".");
			}
			System.out.println("CustomLocalDateDeserializer.main()" + new Double(dNumber));
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.warn("Erro ao deserializar data: " + dNumber, e);
		}

	}
}