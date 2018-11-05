package com.epam.parsing.entitys;

import com.epam.parsing.enums.Color;
import com.epam.parsing.enums.Source;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({Stone.class})
public class Semiprecious extends Stone {
    @XmlElement(name = "source")
    private Source source;

    public Semiprecious() {
    }

    public Semiprecious(String name, String origin, double value, Color color, int numberOfFaces, Source source) {
        super(name, origin, value, color, numberOfFaces);
        this.source = source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public Source getSource() {
        return source;
    }
}
