package com.github.nenomm.xmlfun;

import org.junit.Test;

import java.net.URISyntaxException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

// schema files are not provided to the parser explicitly, via constructor, rather, they are resolved from actual documents.
public class StructureTest extends XmlTest {

	// test global declaration
	@Test
	public void testCase1() throws URISyntaxException {
		assertTrue(validateImplicit("xml/structure_tests/fileset1/document.xml"));
	}

	// xml is just one simple type!
	// instance root can be any of the elements which are direct descendants of schema element in XSD!!
	// this example is for first element definition
	// this is also mentioned in the Book (Beginning XML, 5th ed.): When attempting to find the declaration for the root
	// element, the schema validator looks through only the global element declarations.
	@Test
	public void testCase2() throws URISyntaxException {
		assertTrue(validateImplicit("xml/structure_tests/fileset2/document.xml"));
	}

	// xml is just one simple type!
	// instance root can be any of the elements which are direct descendants of schema element in XSD!!
	// this example is for secong element definition
	@Test
	public void testCase3() throws URISyntaxException {
		assertTrue(validateImplicit("xml/structure_tests/fileset2/another_document.xml"));
	}

	// excerpt from exception:
	// FQDN simple type elements cannot containt attributes (except speciale ones)!
	// cannot have attributes, excepting those whose namespace name is identical to
	// 'http://www.w3.org/2001/XMLSchema-instance' and whose [local name] is one of 'type', 'nil',
	// 'schemaLocation' or 'noNamespaceSchemaLocation'.
	@Test
	public void testCase4() throws URISyntaxException {
		assertFalse(validateImplicit("xml/structure_tests/fileset3/document.xml"));
	}

	// org.xml.sax.SAXParseException: cvc-complex-type.3.2.2: Attribute 'strangeOne'
	// is not allowed to appear in element 'name'.
	@Test
	public void testCase5() throws URISyntaxException {
		assertFalse(validateImplicit("xml/structure_tests/fileset4/document.xml"));
	}

	// attribute can be ommited
	@Test
	public void testCase6() throws URISyntaxException {
		assertTrue(validateImplicit("xml/structure_tests/fileset5/document.xml"));
	}
}


