package com.github.nenomm.xmlfun.explicit;

import com.github.nenomm.xmlfun.XmlTest;
import org.junit.Test;

import java.net.URISyntaxException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

// schema files are not provided to the parser explicitly, via constructor;
// rather, they are resolved from actual documents.
public class DocumentationTest extends XmlTest {

	// test plain comments
	@Test
	public void testCase1() throws URISyntaxException {
		assertTrue(validateExplicit("xml/explicit/documentation_tests/fileset1/document.xml"));
	}

	// not before the prolog
	@Test
	public void testCase2() throws URISyntaxException {
		assertFalse(validateExplicit("xml/explicit/documentation_tests/fileset2/document.xml"));
	}

	// attributes of non existant namespace
	// from the Book (Beginning XML, 5th ed.): Its purpose here is effectively to hide
	// an attribute from processors for which it has no meaning.
	@Test
	public void testCase3() throws URISyntaxException {
		assertTrue(validateExplicit("xml/explicit/documentation_tests/fileset3/document.xml"));
	}

	// however, if element is referenced from such namespace, exception is thrown (needs namespace import)
	@Test
	public void testCase4() throws URISyntaxException {
		assertFalse(validateExplicit("xml/explicit/documentation_tests/fileset4/document.xml"));
	}

	// use annotations (note that sample document does not exist)
	@Test
	public void testCase5() throws URISyntaxException {
		assertTrue(validateExplicit("xml/explicit/documentation_tests/fileset5/document.xml"));
	}


}


