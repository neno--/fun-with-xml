package com.github.nenomm.xmlfun.implicit;

import com.github.nenomm.xmlfun.XmlTest;
import org.junit.Test;

import java.net.URISyntaxException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

// parsers receives needed XSD files implicitly, programmatically, rather than by looking at location attributes.
public class NamespaceDecarationSchemaTest extends XmlTest {

	// test happy path - with location
	@Test
	public void testCase1() throws URISyntaxException {
		assertTrue(validateImplicit("xml/implicit/namespace_tests/fileset1/document.xml",
				"xml/implicit/namespace_tests/fileset1/schema.xml"));
	}

	// test happy path - there is no location attribute in document
	@Test
	public void testCase2() throws URISyntaxException {
		assertTrue(validateImplicit("xml/implicit/namespace_tests/fileset2/document.xml",
				"xml/implicit/namespace_tests/fileset2/schema.xml"));
	}

	// you cannot define targetNamespace for a namespace without name location pair - NO WAIT - you can!
	@Test
	public void testCase6() throws URISyntaxException {
		assertTrue(validateImplicit("xml/implicit/namespace_tests/fileset6/document.xml",
				"xml/implicit/namespace_tests/fileset6/schema.xml"));
	}

	// use noNamespaceSchemaLocation - however schema must not have a namespace defined!
	// this attribute is used for validation of elements which do not belong to any namespace.
	// the same as in implicit case
	@Test
	public void testCase7() throws URISyntaxException {
		assertFalse(validateImplicit("xml/implicit/namespace_tests/fileset7/document.xml",
				"xml/implicit/namespace_tests/fileset7/schema.xml"));
	}
}


