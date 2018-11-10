package com.epam.parsing.validator;

import com.epam.parsing.exceptions.DataExeption;
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
    private String schemaFile;

    public XmlValidator(String schemaFile) {
        this.schemaFile = schemaFile;
    }

    public boolean isValid(String xmlFilePath) throws DataExeption {
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        try {
            Schema schema = schemaFactory.newSchema(new File(schemaFile));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(xmlFilePath)));
            log.info("File is valid");
            return true;
        } catch (SAXException | IOException e) {
            log.info("File is invalid");
            throw new DataExeption(e.getMessage(), e);
        }
    }
}
