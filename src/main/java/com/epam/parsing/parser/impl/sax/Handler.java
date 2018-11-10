package com.epam.parsing.parser.impl.sax;

import com.epam.parsing.entitys.Precious;
import com.epam.parsing.entitys.Semiprecious;
import com.epam.parsing.entitys.Stone;
import com.epam.parsing.enums.Color;
import com.epam.parsing.enums.Source;
import com.epam.parsing.enums.Tags;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class Handler extends DefaultHandler {
    private List<Stone> container;
    private Stone stone;
    private Tags tag = Tags.UNDEFINED;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase("precious")) {
            tag = Tags.PRECIOUS;
        }
        if (qName.equalsIgnoreCase("semiprecious")) {
            tag = Tags.SEMIPRECIOUS;
        }
        if (qName.equalsIgnoreCase("name")) {
            tag = Tags.NAME;
        }
        if (qName.equalsIgnoreCase("origin")) {
            tag = Tags.ORIGIN;
        }
        if (qName.equalsIgnoreCase("value")) {
            tag = Tags.VALUE;
        }
        if (qName.equalsIgnoreCase("color")) {
            tag = Tags.COLOR;
        }
        if (qName.equalsIgnoreCase("number-of-faces")) {
            tag = Tags.NUMBER_OF_FACES;
        }
        if (qName.equalsIgnoreCase("is-natural")) {
            tag = Tags.IS_NATURAL;
        }
        if (qName.equalsIgnoreCase("transparency")) {
            tag = Tags.TRANSPARENCY;
        }
        if (qName.equalsIgnoreCase("source")) {
            tag = Tags.SOURCE;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("precious") || qName.equalsIgnoreCase("semiprecious")) {
            if (container == null) {
                container = new ArrayList<>();
            }
            container.add(stone);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        switch (tag) {
            case PRECIOUS:
                stone = new Precious();
                tag = Tags.UNDEFINED;
                break;

            case SEMIPRECIOUS:
                stone = new Semiprecious();
                tag = Tags.UNDEFINED;
                break;

            case NAME:
                tag = Tags.UNDEFINED;
                stone.setName(new String(ch, start, length));
                break;

            case ORIGIN:
                tag = Tags.UNDEFINED;
                stone.setOrigin(new String(ch, start, length));
                break;

            case VALUE:
                tag = Tags.UNDEFINED;
                stone.setValue(Double.valueOf(new String(ch, start, length)));
                break;

            case COLOR:
                tag = Tags.UNDEFINED;
                stone.setColor(Color.valueOf(new String(ch, start, length)));
                break;

            case NUMBER_OF_FACES:
                tag = Tags.UNDEFINED;
                stone.setNumberOfFaces(Integer.parseInt(new String(ch, start, length)));
                break;

            case SOURCE:
                tag = Tags.UNDEFINED;
                Semiprecious castedSemiprecious = (Semiprecious) stone;
                castedSemiprecious.setSource(Source.valueOf(new String(ch, start, length)));
                break;

            case IS_NATURAL:
                tag = Tags.UNDEFINED;
                Precious castedPrecious = (Precious) stone;
                castedPrecious.setNatural(Boolean.parseBoolean(new String(ch, start, length)));
                break;

            case TRANSPARENCY:
                tag = Tags.UNDEFINED;
                Precious precious = (Precious) stone;
                precious.setTransparency(Double.parseDouble(new String(ch, start, length)));
                break;

            default:
                break;
        }
    }

    public void setContainer(List<Stone> container) {
        this.container = container;
    }
}
