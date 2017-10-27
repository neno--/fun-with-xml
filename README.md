##### About

Just tests to find out how XML handles dependencies, ie files
in import and include statements.

Also contains some tests regarding general XSD stuff.

Execute tests by running `./gradlew test`

Tests are separated into two categories:
* **explicit** - XSD locations are defined within XML documents themselves, and
* **implicit** - where XSD locations are provided programmatically to XML parser.

All tests were executed with Java 8 JAXP XML `Validator` provided by `Schema` created by `XMLSchemaFactory`.

##### Conclusions About the Dependencies:

Only XML instance documents can have namespace declarations, XSD schemas use either import
or include tags.

This is because namespace declaration only allows to use some namespace in XML, and not
to use it for creating new types in XSD schema.

Namespace declaration can be defined by target location (schemaLocation) explicitly in the document, or by
providing necessary namespaces (implicitly) to the parser.

Which option is better depends on the use case, but it is generally a bad idea to couple the instance document
with the location of the XSD. Especially if that location is transient in nature (eg not available on the internet).

Also, parsers can only take location attribute as a hint, so it is not guaranteed that this location will be used.

Import and include statements **must** have location (schemaLocation) attribute.

##### Namespaces and Documentation
Attributes can be used for documentation, beacuse they will be ignored from validation process, if they are bound to some foreign namespace.
In this case, (when namespace is only used to reference attributes) location can be ommited, and parser never needs to know this information.