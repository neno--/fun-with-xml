package com.github.nenomm.xmlfun.implicit;

import com.github.nenomm.xmlfun.XmlTest;
import org.junit.Test;

import java.net.URISyntaxException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

// schema files are provided to the parser explicitly, via XML documents;
public class ImportSchemaTest extends XmlTest {

	// import is irrelevant if namespace is not used
	@Test
	public void testCase1() throws URISyntaxException {
		assertTrue(validateImplicit("xml/implicit/import_tests/fileset1/document.xml",
				"xml/implicit/import_tests/fileset1/schema.xml",
				"xml/implicit/import_tests/fileset1/anotherSchema.xml"));
	}

	// valid import path is *STILL* mandatory if imported namespace is used
	@Test
	public void testCase2() throws URISyntaxException {
		assertFalse(validateImplicit("xml/implicit/import_tests/fileset2/document.xml",
				"xml/implicit/import_tests/fileset2/schema.xml",
				"xml/implicit/import_tests/fileset2/anotherSchema.xml"));
	}

	// instance document must conform to the XSD with imported elements!
	// (missing nickname)
	@Test
	public void testCase3() throws URISyntaxException {
		assertFalse(validateImplicit("xml/implicit/import_tests/fileset3/document.xml",
				"xml/implicit/import_tests/fileset3/schema.xml",
				"xml/implicit/import_tests/fileset3/anotherSchema.xml"));
	}

	// instance document must use proper namespace prefix for all elements!
	@Test
	public void testCase4() throws URISyntaxException {
		assertFalse(validateImplicit("xml/implicit/import_tests/fileset4/document.xml",
				"xml/implicit/import_tests/fileset4/schema.xml",
				"xml/implicit/import_tests/fileset4/anotherSchema.xml"));
	}

	// HAPPY PATH
	// Referring to an Existing Global Element
	@Test
	public void testCase5() throws URISyntaxException {
		assertTrue(validateImplicit("xml/implicit/import_tests/fileset5/document.xml",
				"xml/implicit/import_tests/fileset5/schema.xml",
				"xml/implicit/import_tests/fileset5/anotherSchema.xml"));
	}

	/*
	 * Error resolving component 'another:nickname'. It was detected that 'another:nickname' is in namespace
	 * 'urn:com.github.nenomm.xmlfun.anotherSchema',
	 * but components from this namespace are not referenceable from schema document
	 * 'file:/home/projects/whateva/fun-with-xml/out/test/resources/xml/import_tests/fileset6/schema.xml'.
	 * If this is the incorrect namespace, perhaps the prefix of 'another:nickname' needs to be changed. If this is the
	 * correct namespace, then an appropriate 'import' tag should be added to
	 * 'file:/home/projects/whateva/fun-with-xml/out/test/resources/xml/import_tests/fileset6/schema.xml'.
	 *
	 *
	 * basically from this we see the difference between namespace declaration, which enables using alien vocabulary,
	 * and namespace import, which provides usage of alien types in creation of our own types. Or provides the usage
	 * of alien elements in our own elements.
	 */

	@Test
	public void testCase6() throws URISyntaxException {
		assertFalse(validateImplicit("xml/implicit/import_tests/fileset6/document.xml",
				"xml/implicit/import_tests/fileset6/schema.xml",
				"xml/implicit/import_tests/fileset6/anotherSchema.xml"));
	}

	// Referring to an Existing Global Type - although we 'hijack' the type for element, children are still from alien
	// namespace...
	@Test
	public void testCase7() throws URISyntaxException {
		assertTrue(validateImplicit("xml/implicit/import_tests/fileset7/document.xml",
				"xml/implicit/import_tests/fileset7/schema.xml",
				"xml/implicit/import_tests/fileset7/anotherSchema.xml"));
	}

	// can we import namespace XSD without target namespace?
	// (no target defined in anotherSchema.xml)
	// nope
	@Test
	public void testCase8() throws URISyntaxException {
		assertFalse(validateImplicit("xml/implicit/import_tests/fileset8/document.xml",
				"xml/implicit/import_tests/fileset8/schema.xml",
				"xml/implicit/import_tests/fileset8/anotherSchema.xml"));
	}

	// how about the same namespace?
	// nope - import prohibits this, parser won't allow it.
	@Test
	public void testCase9() throws URISyntaxException {
		assertFalse(validateImplicit("xml/implicit/import_tests/fileset9/document.xml",
				"xml/implicit/import_tests/fileset9/schema.xml",
				"xml/implicit/import_tests/fileset9/anotherSchema.xml"));
	}
}


