package com.epam.parsing.validation;

import com.epam.parsing.exceptions.DataExeption;
import com.epam.parsing.validator.XmlValidator;
import org.junit.Assert;
import org.junit.Test;

public class XmlValidatorTest {
    private static final String INPUT_FILE = "src/test/resources/input.xml";
    private static final String SCHEMA_FILE = "src/main/resources/schema.xsd";

    @Test
    public void shouldReturnTrueWhenFileValid() throws DataExeption {
        //given
        XmlValidator xmlValidator = new XmlValidator(SCHEMA_FILE);
        //when
        boolean result = xmlValidator.isValid(INPUT_FILE);
        //then
        Assert.assertTrue(result);
    }
}
