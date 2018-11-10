package com.epam.parsing.parser;

import com.epam.parsing.enums.ParserType;
import com.epam.parsing.parser.impl.DomParser;
import com.epam.parsing.parser.impl.JaxbParser;
import com.epam.parsing.parser.impl.sax.SaxParser;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(DataProviderRunner.class)
public class ParserFactoryTest {
    @DataProvider
    public static Object[][] typeProvider() {
        return new Object[][]{
                {ParserType.DOM, DomParser.class},
                {ParserType.SAX, SaxParser.class},
                {ParserType.JAXB, JaxbParser.class},
        };
    }

    @Test
    @UseDataProvider("typeProvider")
    public void shouldReturnParser(ParserType type, Class clazz) {
        //given
        ParserFactory parserFactory = new ParserFactory();
        //when
        Parser parser = parserFactory.create(type);
        //then
        Assert.assertEquals(parser.getClass(), clazz);
    }
}
