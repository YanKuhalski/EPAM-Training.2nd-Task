package com.epam.parsing.director;

import com.epam.parsing.entitys.Stone;
import com.epam.parsing.enums.ParserType;
import com.epam.parsing.exceptions.DataExeption;
import com.epam.parsing.exceptions.ParserException;
import com.epam.parsing.parser.ParserFactory;
import com.epam.parsing.parser.impl.DomParser;
import com.epam.parsing.validator.XmlValidator;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class DirectorTest {
    private static final ParserFactory parser = Mockito.mock(ParserFactory.class);
    private static final XmlValidator validator = Mockito.mock(XmlValidator.class);
    private static final DomParser domParseer = Mockito.mock(DomParser.class);
    private static final ParserType PARSER_TYPE = ParserType.DOM;
    private static final String INPUT_FILE = "src/test/resources/input.xml";

    @Test
    public void shouldReturnStoneList() throws DataExeption, ParserException {
        //given
        List<Stone> stoneList = new ArrayList<>();
        Director director = new Director(parser, validator);
        Mockito.when(domParseer.parse(Mockito.any(String.class))).thenReturn(stoneList);
        Mockito.when(parser.create(Mockito.any(ParserType.class))).thenReturn(domParseer);
        Mockito.when(validator.isValid(Mockito.any(String.class))).thenReturn(true);

        //when
        List<Stone> actual = director.parseXmlFile(INPUT_FILE, PARSER_TYPE);

        //then
        Assert.assertEquals(stoneList, actual);
    }
}
