package com.epam.parsing.parser.impl.sax;

import com.epam.parsing.entitys.Stone;
import com.epam.parsing.exeptions.ParserException;
import com.epam.parsing.parser.Parser;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SaxParser implements Parser {
    public List<Stone> parse(String path) throws ParserException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        List<Stone> stones = new ArrayList<>();
        try {
            SAXParser saxParser = factory.newSAXParser();
            Handler handler = new Handler();
            handler.setContainer(stones);
            saxParser.parse(path, handler);
        } catch (SAXException | ParserConfigurationException | IOException e) {
            throw new ParserException(e.getMessage(), e);
        }
        return stones;
    }
}
