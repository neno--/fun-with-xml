package com.github.nenomm.xmlfun.explicit;

import com.github.nenomm.xmlfun.XmlTest;
import org.junit.Test;

import java.net.URISyntaxException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

// schema files are not provided to the parser explicitly, via constructor;
// rather, they are resolved from actual documents.
public class IncludeSchemaTest extends XmlTest {

	// HAPPY PATH
	@Test
	public void testCase18() throws URISyntaxException {
		assertTrue(validateExplicit("xml/explicit/include_tests/fileset18/document.xml"));
	}

	// chameleon namespace - included has no target namespace.
	// BTW IDEA 2017.2.5 is not handling this correctly - it cannot find referenced element included
	// from "anonymous" XSD.
	@Test
	public void testCase19() throws URISyntaxException {
		assertTrue(validateExplicit("xml/explicit/include_tests/fileset19/document.xml"));
	}

	// cannot include different namespace
	@Test
	public void testCase20() throws URISyntaxException {
		assertFalse(validateExplicit("xml/explicit/include_tests/fileset20/document.xml"));
	}

}


