package com.epam.parsing.parser.impl;

import com.epam.parsing.entitys.Stone;
import com.epam.parsing.entitys.Stones;
import com.epam.parsing.exceptions.ParserException;
import com.epam.parsing.parser.Parser;
import com.epam.parsing.parser.impl.sax.SaxParser;
import org.apache.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

public class JaxbParser implements Parser {
    private static final Logger log = Logger.getLogger(SaxParser.class);

    public List<Stone> parse(String path) throws ParserException {
        try {
            File file = new File(path);
            JAXBContext jaxbContext = JAXBContext.newInstance(Stones.class);
            Unmarshaller um = jaxbContext.createUnmarshaller();
            Stones stones = (Stones) um.unmarshal(file);
            log.info("Jaxb parser successfully worked");
            return stones.getStones();
        } catch (JAXBException e) {
            System.out.println(e.getMessage());
            throw new ParserException(e.getMessage(), e);
        }
    }
}
