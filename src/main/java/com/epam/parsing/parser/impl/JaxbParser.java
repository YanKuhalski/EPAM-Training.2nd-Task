package com.epam.parsing.parser.impl;

import com.epam.parsing.entitys.Stone;
import com.epam.parsing.entitys.Stones;
import com.epam.parsing.exeptions.ParserException;
import com.epam.parsing.parser.Parser;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

public class JaxbParser implements Parser {
    public List<Stone> parse(String path) throws ParserException {
        try {
            File file = new File(path);
            JAXBContext jaxbContext = JAXBContext.newInstance(Stones.class);
            Unmarshaller um = jaxbContext.createUnmarshaller();
            Stones stones = (Stones) um.unmarshal(file);
            return stones.getStones();
        } catch (JAXBException e) {
            throw new ParserException(e.getMessage(), e);
        }
    }
}
