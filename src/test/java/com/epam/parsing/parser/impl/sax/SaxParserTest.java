package com.epam.parsing.parser.impl.sax;

import com.epam.parsing.entitys.Precious;
import com.epam.parsing.entitys.Semiprecious;
import com.epam.parsing.entitys.Stone;
import com.epam.parsing.enums.Color;
import com.epam.parsing.enums.Source;
import com.epam.parsing.exceptions.ParserException;
import com.epam.parsing.parser.Parser;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class SaxParserTest {
    private static final String INPUT_FILE = "src/test/resources/input.xml";
    private static final double DELTA = 0.01;

    @Test
    public void shouldParseWhenDataValid() throws ParserException {
        //given
        Parser parser = new SaxParser();

        //when
        List<Stone> stones = parser.parse(INPUT_FILE);
        //then
        Assert.assertEquals(4, stones.size());

        Stone firstStone = stones.get(0);
        Assert.assertEquals(firstStone.getClass(), Precious.class);

        Precious first = (Precious) firstStone;
        Assert.assertEquals("Diamond", first.getName());
        Assert.assertEquals("Africa", first.getOrigin());
        Assert.assertEquals(11.1, first.getValue(), DELTA);
        Assert.assertEquals(Color.WHITE, first.getColor());
        Assert.assertEquals(25, first.getNumberOfFaces());
        Assert.assertEquals(true, first.isNatural());
        Assert.assertEquals(99.9, first.getTransparency(), DELTA);

        Stone secondStone = stones.get(1);
        Assert.assertEquals(secondStone.getClass(), Semiprecious.class);

        Semiprecious second = (Semiprecious) secondStone;
        Assert.assertEquals("Garnet", second.getName());
        Assert.assertEquals("Africa", second.getOrigin());
        Assert.assertEquals(34.1, second.getValue(), DELTA);
        Assert.assertEquals(Color.RED, second.getColor());
        Assert.assertEquals(15, second.getNumberOfFaces());
        Assert.assertEquals(Source.METAMORPHIC, second.getSource());

        Stone thirdStone = stones.get(2);
        Assert.assertEquals(thirdStone.getClass(), Precious.class);

        Precious third = (Precious) thirdStone;
        Assert.assertEquals("Chrysoberyl", third.getName());
        Assert.assertEquals("China", third.getOrigin());
        Assert.assertEquals(15, third.getValue(), DELTA);
        Assert.assertEquals(Color.YELLOW, third.getColor());
        Assert.assertEquals(15, third.getNumberOfFaces());
        Assert.assertEquals(true, third.isNatural());
        Assert.assertEquals(70.5, third.getTransparency(), DELTA);

        Stone fourthStone = stones.get(3);
        Assert.assertEquals(fourthStone.getClass(), Semiprecious.class);

        Semiprecious fourth = (Semiprecious) fourthStone;
        Assert.assertEquals("Opal", fourth.getName());
        Assert.assertEquals("USA", fourth.getOrigin());
        Assert.assertEquals(15.1, fourth.getValue(), DELTA);
        Assert.assertEquals(Color.BLUE, fourth.getColor());
        Assert.assertEquals(4, fourth.getNumberOfFaces());
        Assert.assertEquals(Source.MAGMATIC, fourth.getSource());
    }
}
