package com.github.nenomm.xmlfun;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class XmlTest {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	// todo: this way we probably won't need location attributes
	public boolean validateExplicit(String classpathResourceXmlInstance, String classpathResourceSchema) throws URISyntaxException {
		Source xmlFile = new StreamSource(new File(this.getClass().getClassLoader()
				.getResource(classpathResourceXmlInstance).toURI()));

		Source schemaFile = new StreamSource(new File(this.getClass().getClassLoader()
				.getResource(classpathResourceSchema).toURI()));

		SchemaFactory schemaFactory = SchemaFactory
				.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

		try {
			Schema schema = schemaFactory.newSchema(schemaFile);
			Validator validator = schema.newValidator();
			validator.validate(xmlFile);
			logger.info("{} is valid.", xmlFile.getSystemId());
			return true;
		} catch (SAXException e) {
			logger.warn("{}  is NOT valid reason: {}.", xmlFile.getSystemId(), e);
		} catch (IOException e) {
		}
		return false;
	}

	public boolean validateImplicit(String classpathResourceXmlInstance) throws URISyntaxException {
		Source xmlFile = new StreamSource(new File(this.getClass().getClassLoader()
				.getResource(classpathResourceXmlInstance).toURI()));

		SchemaFactory schemaFactory = SchemaFactory
				.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

		try {
			Schema schema = schemaFactory.newSchema();
			Validator validator = schema.newValidator();
			validator.validate(xmlFile);
			logger.info("{} is valid.", xmlFile.getSystemId());
			return true;
		} catch (SAXException e) {
			logger.warn("{}  is NOT valid reason: {}.", xmlFile.getSystemId(), e);
		} catch (IOException e) {
			logger.warn("{}", e);
		}
		return false;
	}
}
