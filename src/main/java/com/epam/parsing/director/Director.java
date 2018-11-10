package com.epam.parsing.director;

import com.epam.parsing.entitys.Stone;
import com.epam.parsing.enums.ParserType;
import com.epam.parsing.exceptions.DataExeption;
import com.epam.parsing.exceptions.ParserException;
import com.epam.parsing.parser.Parser;
import com.epam.parsing.parser.ParserFactory;
import com.epam.parsing.validator.XmlValidator;
import org.apache.log4j.Logger;

import java.util.List;

public class Director {
    private static final Logger log = Logger.getLogger(Director.class);
    private ParserFactory factory;
    private XmlValidator validator;

    public Director(ParserFactory factory, XmlValidator validator) {
        this.factory = factory;
        this.validator = validator;
    }

    public List<Stone> parseXmlFile(String filePath, ParserType parserType) throws ParserException, DataExeption {
        try {
            List<Stone> stones = null;
            if (validator.isValid(filePath)) {
                Parser parser = factory.create(parserType);
                log.info(parser.getClass().getSimpleName() + " was successfully created.");
                try {
                    stones = parser.parse(filePath);
                } catch (ParserException e) {
                    log.error(e.getClass().getSimpleName() + " " + e.getMessage());
                    throw e;
                }
            }
            log.info("Parsing successfully finished.");
            return stones;
        } catch (DataExeption dataExeption) {
            log.error(dataExeption.getClass().getSimpleName() + " " + dataExeption.getMessage());
            throw dataExeption;
        }
    }
}
