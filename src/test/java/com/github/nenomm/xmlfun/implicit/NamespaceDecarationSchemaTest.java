package com.github.nenomm.xmlfun.implicit;

import com.github.nenomm.xmlfun.XmlTest;
import org.junit.Test;

import java.net.URISyntaxException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

// parsers receives needed XSD files implicitly, programmatically, rather than by looking at location attributes.
public class NamespaceDecarationSchemaTest extends XmlTest {

	// test happy path
	@Test
	public void testCase1() throws URISyntaxException {
		assertTrue(validateImplicit("xml/explicit/namespace_tests/fileset1/document.xml",
				"xml/explicit/namespace_tests/fileset1/schema.xml"));
	}

	// test there is no location attribute in document
	@Test
	public void testCase2() throws URISyntaxException {
		assertTrue(validateImplicit("xml/explicit/namespace_tests/fileset2/document.xml",
				"xml/explicit/namespace_tests/fileset2/schema.xml"));
	}

	// bad namespace
	@Test
	public void testCase3() throws URISyntaxException {
		assertFalse(validateImplicit("xml/explicit/namespace_tests/fileset3/document.xml",
				"xml/explicit/namespace_tests/fileset3/schema.xml"));
	}

	// unused namespaces are ignored
	@Test
	public void testCase4() throws URISyntaxException {
		assertTrue(validateImplicit("xml/explicit/namespace_tests/fileset4/document.xml",
				"xml/explicit/namespace_tests/fileset4/schema.xml"));
	}

	// relative file paths are not working
	// this test is not applicable to implicit case. it will pass.
	@Test
	public void testCase5() throws URISyntaxException {
		assertTrue(validateImplicit("xml/explicit/namespace_tests/fileset5/document.xml",
				"xml/explicit/namespace_tests/fileset5/schemas/schema.xml",
				"xml/explicit/namespace_tests/fileset5/schemas/anotherSchema.xml"));
	}

	// you cannot define targetNamespace for a namespace without name location pair
	// turns out you can if you use implicit scenario.
	@Test
	public void testCase6() throws URISyntaxException {
		assertTrue(validateImplicit("xml/explicit/namespace_tests/fileset6/document.xml",
				"xml/explicit/namespace_tests/fileset6/schema.xml"));
	}

	// use noNamespaceSchemaLocation - however schema must not have a namespace defined!
	// this attribute is used for validation of elements which do not belong to any namespace.
	@Test
	public void testCase7() throws URISyntaxException {
		assertFalse(validateImplicit("xml/explicit/namespace_tests/fileset7/document.xml",
				"xml/explicit/namespace_tests/fileset7/schema.xml"));
	}

	@Test
	public void testCase8() throws URISyntaxException {
		assertTrue(validateImplicit("xml/explicit/namespace_tests/fileset8/document.xml",
				"xml/explicit/namespace_tests/fileset8/schema.xml"));
	}
}


