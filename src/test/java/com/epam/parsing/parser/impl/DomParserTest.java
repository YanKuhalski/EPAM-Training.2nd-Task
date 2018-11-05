package com.epam.parsing.parser.impl;

import com.epam.parsing.entitys.Precious;
import com.epam.parsing.entitys.Semiprecious;
import com.epam.parsing.entitys.Stone;
import com.epam.parsing.enums.Color;
import com.epam.parsing.enums.Source;
import com.epam.parsing.exeptions.ParserException;
import com.epam.parsing.parser.Parser;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class DomParserTest {
    private static final String INPUT_FILE = "src/test/resources/input.xml";
    private static final double DELTA = 0.01;

    @Test
    public void shouldParseWhenDataValid() throws ParserException {
        //given
        Parser parser = new DomParser();

        //when
        List<Stone> stones = parser.parse(INPUT_FILE);
        //then


        Assert.assertEquals(2, stones.size());


        Stone firstStone = stones.get(0);
        Assert.assertEquals(firstStone.getClass(), Precious.class);
        Precious first = (Precious) firstStone;
        Assert.assertEquals("FDf", first.getName());
        Assert.assertEquals("FDSs", first.getOrigin());
        Assert.assertEquals(Color.RED, first.getColor());
        Assert.assertEquals(4, first.getNumberOfFaces());
        Assert.assertEquals(false, first.isNatural());
        Assert.assertEquals(50, first.getTransparency(), DELTA);


        Stone secondStone = stones.get(1);
        Assert.assertEquals(secondStone.getClass(), Semiprecious.class);
        Semiprecious second = (Semiprecious) secondStone;

        Assert.assertEquals("AAA", second.getName());
        Assert.assertEquals("FDSs", second.getOrigin());
        Assert.assertEquals(Color.RED, second.getColor());
        Assert.assertEquals(4, second.getNumberOfFaces());
        Assert.assertEquals(Source.METAMORPHIC, second.getSource());

    }
}
