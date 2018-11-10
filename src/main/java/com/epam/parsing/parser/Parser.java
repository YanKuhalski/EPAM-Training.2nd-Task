package com.epam.parsing.parser;

import com.epam.parsing.entitys.Stone;
import com.epam.parsing.exceptions.ParserException;

import java.util.List;

public interface Parser {
    List<Stone> parse(String path) throws ParserException;
}
