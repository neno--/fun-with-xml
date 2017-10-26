package com.github.nenomm.xmlfun.implicit;

import com.github.nenomm.xmlfun.XmlTest;
import org.junit.Test;

import java.net.URISyntaxException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

// schema files are provided to the parser explicitly, via XML documents;
public class IncludeSchemaTest extends XmlTest {

	// HAPPY PATH
	// although resources can be served implicitly, included xml schemas must be located explicitly from XSD files.
	@Test
	public void testCase1() throws URISyntaxException {
		assertTrue(validateImplicit("xml/implicit/include_tests/fileset1/document.xml",
				"xml/implicit/include_tests/fileset1/schema.xml"));
	}

	// chameleon namespace - included has no target namespace.
	// BTW IDEA 2017.2.5 is not handling this correctly - it cannot find referenced element included
	// from "anonymous" XSD.
	@Test
	public void testCase2() throws URISyntaxException {
		assertTrue(validateImplicit("xml/implicit/include_tests/fileset2/document.xml",
				"xml/implicit/include_tests/fileset2/schema.xml"));
	}

	// cannot include different namespace
	@Test
	public void testCase3() throws URISyntaxException {
		assertFalse(validateImplicit("xml/implicit/include_tests/fileset3/document.xml",
				"xml/implicit/include_tests/fileset3/schema.xml"));
	}

}


