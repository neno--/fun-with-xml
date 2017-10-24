package com.github.nenomm.xmlfun.explicit;

import com.github.nenomm.xmlfun.XmlTest;
import org.junit.Test;

import java.net.URISyntaxException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

// schema files are not provided to the parser explicitly, via constructor;
// rather, they are resolved from actual documents.
public class NamespaceDecarationSchemaTest extends XmlTest {

	// test happy path
	@Test
	public void testCase1() throws URISyntaxException {
		assertTrue(validateExplicit("xml/explicit/namespace_tests/fileset1/document.xml"));
	}

	// test there is no location attribute in document
	@Test
	public void testCase2() throws URISyntaxException {
		assertFalse(validateExplicit("xml/explicit/namespace_tests/fileset2/document.xml"));
	}

	// bad namespace
	@Test
	public void testCase3() throws URISyntaxException {
		assertFalse(validateExplicit("xml/explicit/namespace_tests/fileset3/document.xml"));
	}

	// unused namespaces are ignored
	@Test
	public void testCase4() throws URISyntaxException {
		assertTrue(validateExplicit("xml/explicit/namespace_tests/fileset4/document.xml"));
	}

	// relative file paths are not working
	@Test
	public void testCase5() throws URISyntaxException {
		assertFalse(validateExplicit("xml/explicit/namespace_tests/fileset5/document.xml"));
	}

	// you cannot define targetNamespace for a namespace without name location pair
	@Test
	public void testCase6() throws URISyntaxException {
		assertFalse(validateExplicit("xml/explicit/namespace_tests/fileset6/document.xml"));
	}

	// use noNamespaceSchemaLocation - however schema must not have a namespace defined!
	// this attribute is used for validation of elements which do not belong to any namespace.
	@Test
	public void testCase7() throws URISyntaxException {
		assertFalse(validateExplicit("xml/explicit/namespace_tests/fileset7/document.xml"));
	}

	@Test
	public void testCase8() throws URISyntaxException {
		assertTrue(validateExplicit("xml/explicit/namespace_tests/fileset8/document.xml"));
	}
}


