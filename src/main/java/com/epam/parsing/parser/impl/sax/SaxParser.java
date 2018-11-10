package com.epam.parsing.parser.impl.sax;

import com.epam.parsing.entitys.Stone;
import com.epam.parsing.exceptions.ParserException;
import com.epam.parsing.parser.Parser;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SaxParser implements Parser {
    private static final Logger log = Logger.getLogger(SaxParser.class);

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
        log.info("SAX parser successfully worked");
        return stones;
    }
}
