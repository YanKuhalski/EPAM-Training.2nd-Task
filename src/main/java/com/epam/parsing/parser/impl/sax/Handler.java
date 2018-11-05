package com.epam.parsing.parser.impl.sax;

import com.epam.parsing.entitys.Precious;
import com.epam.parsing.entitys.Semiprecious;
import com.epam.parsing.entitys.Stone;
import com.epam.parsing.enums.Color;
import com.epam.parsing.enums.Source;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class Handler extends DefaultHandler {
    List<Stone> container;
    Precious precious;
    Semiprecious semiprecious;
    boolean isPrecious = false;
    boolean isSemiprecious = false;
    boolean bname = false;
    boolean borigin = false;
    boolean bvalue = false;
    boolean bcolor = false;
    boolean bnumberOfFaces = false;
    boolean bisNatural = false;
    boolean btransparency = false;
    boolean bsource = false;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase("precious")) {
            isPrecious = true;
            precious = new Precious();
        }
        if (qName.equalsIgnoreCase("semiprecious")) {
            isSemiprecious = true;
            semiprecious = new Semiprecious();
        }
        if (qName.equalsIgnoreCase("name")) {
            bname = true;
        }
        if (qName.equalsIgnoreCase("origin")) {
            borigin = true;
        }
        if (qName.equalsIgnoreCase("value")) {
            bvalue = true;
        }
        if (qName.equalsIgnoreCase("color")) {
            bcolor = true;
        }
        if (qName.equalsIgnoreCase("number-of-faces")) {
            bnumberOfFaces = true;
        }
        if (qName.equalsIgnoreCase("is-natural")) {
            bisNatural = true;
        }
        if (qName.equalsIgnoreCase("transparency")) {
            btransparency = true;
        }
        if (qName.equalsIgnoreCase("source")) {
            bsource = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("precious")) {
            isPrecious = false;
            if (container == null) {
                container = new ArrayList<>();
            }
            container.add(precious);
        }
        if (qName.equalsIgnoreCase("semiprecious")) {
            isSemiprecious = false;
            if (container == null) {
                container = new ArrayList<>();
            }
            container.add(semiprecious);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        Stone stone;
        if (isPrecious) {
            stone = precious;

        } else {
            stone = semiprecious;
        }

        if (bname) {
            stone.setName(new String(ch, start, length));
            bname = false;
        }
        if (borigin) {
            stone.setOrigin(new String(ch, start, length));
            borigin = false;
        }
        if (bnumberOfFaces) {
            stone.setNumberOfFaces(Integer.parseInt(new String(ch, start, length)));
            bnumberOfFaces = false;
        }
        if (bcolor) {
            stone.setColor(Color.valueOf(new String(ch, start, length)));
            bcolor = false;
        }
        if (bvalue) {
            stone.setValue(Double.valueOf(new String(ch, start, length)));
            bvalue = false;
        }
        if (bisNatural) {

            precious.setNatural(Boolean.parseBoolean(new String(ch, start, length)));
            bisNatural = false;
        }
        if (btransparency) {
            precious.setTransparency(Double.parseDouble(new String(ch, start, length)));
            btransparency = false;
        }
        if (bsource) {
            semiprecious.setSource(Source.valueOf(new String(ch, start, length)));
            bsource = false;
        }
    }

    public void setContainer(List<Stone> container) {
        this.container = container;
    }
}
