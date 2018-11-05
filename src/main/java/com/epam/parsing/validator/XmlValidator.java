package com.epam.parsing.validator;

import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class XmlValidator {
    private final static Logger log = Logger.getLogger(XmlValidator.class);

    public boolean isValid(String XmlPath, String XsdPath) {
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        try {
            Schema schema = schemaFactory.newSchema(new File(XsdPath));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(XmlPath)));
            log.info("File " + XmlPath + " is valid");
        } catch (SAXException | IOException e) {
            log.error(e.getClass().getSimpleName());
        }
        return true;
    }
}
