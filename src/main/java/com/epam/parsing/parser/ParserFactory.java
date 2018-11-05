package com.epam.parsing.parser;

import com.epam.parsing.enums.ParserType;
import com.epam.parsing.parser.impl.DomParser;
import com.epam.parsing.parser.impl.JaxbParser;
import com.epam.parsing.parser.impl.sax.SaxParser;

public class ParserFactory {
    public Parser create(ParserType type) throws RuntimeException {
        switch (type) {
            case DOM:
                return new DomParser();
            case SAX:
                return new SaxParser();
            case JAXD:
                return new JaxbParser();
            default:
                throw new RuntimeException("Unsupported parser type : " + type);
        }
    }
}
