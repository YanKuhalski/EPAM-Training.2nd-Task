package com.epam.parsing.parser.impl;

import com.epam.parsing.entitys.Precious;
import com.epam.parsing.entitys.Semiprecious;
import com.epam.parsing.entitys.Stone;
import com.epam.parsing.enums.Color;
import com.epam.parsing.enums.Source;
import com.epam.parsing.exceptions.ParserException;
import com.epam.parsing.parser.Parser;
import com.epam.parsing.parser.impl.sax.SaxParser;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DomParser implements Parser {
    private static final Logger log = Logger.getLogger(SaxParser.class);

    public List<Stone> parse(String path) throws ParserException {
        List<Stone> stones = new ArrayList<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(path));
            document.getDocumentElement().normalize();

            NodeList nodes = document.getElementsByTagName("precious");
            Optional<Stone> optionalStone;
            for (int i = 0; i < nodes.getLength(); i++) {
                optionalStone = makePrecious(nodes.item(i));
                System.out.printf(optionalStone.toString());
                if (optionalStone.isPresent()) {
                    stones.add(optionalStone.get());
                }
            }

            nodes = document.getElementsByTagName("semiprecious");
            for (int i = 0; i < nodes.getLength(); i++) {
                optionalStone = makeSemiprecious(nodes.item(i));
                if (optionalStone.isPresent()) {
                    stones.add(optionalStone.get());
                }
            }
            log.info("DOM parser successfully worked");
        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new ParserException(e.getMessage(), e);
        }
        return stones;
    }

    private Optional<Stone> makeSemiprecious(Node node) {
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            Semiprecious semiprecious = new Semiprecious();
            makeBasis(semiprecious, element);
            Source source = Source.valueOf(getTagValue("source", element));
            semiprecious.setSource(source);
            return Optional.of(semiprecious);
        }
        return Optional.empty();
    }

    private Optional<Stone> makePrecious(Node node) {
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            Precious precious = new Precious();
            makeBasis(precious, element);
            boolean isNatural = Boolean.valueOf(getTagValue("is-natural", element));
            double transparency = Double.valueOf(getTagValue("transparency", element));
            precious.setNatural(isNatural);
            precious.setTransparency(transparency);
            return Optional.of(precious);
        }
        return Optional.empty();
    }

    private void makeBasis(Stone stone, Element element) {
        String name = getTagValue("name", element);
        String origin = getTagValue("origin", element);
        double value = Double.valueOf(getTagValue("value", element));
        Color color = Color.valueOf(getTagValue("color", element));
        int numberOfFaces = Integer.valueOf(getTagValue("number-of-faces", element));
        stone.setName(name);
        stone.setOrigin(origin);
        stone.setValue(value);
        stone.setColor(color);
        stone.setNumberOfFaces(numberOfFaces);
    }

    private String getTagValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = nodeList.item(0);
        return node.getNodeValue();
    }
}
