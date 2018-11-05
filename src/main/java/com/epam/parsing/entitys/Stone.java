package com.epam.parsing.entitys;

import com.epam.parsing.enums.Color;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public abstract class Stone {
    @XmlElement(name = "name")
    private String name;

    @XmlElement(name = "origin")
    private String origin;

    @XmlElement(name = "value")
    private double value;

    @XmlElement(name = "color")
    private Color color;

    @XmlElement(name = "number-of-faces")
    private int numberOfFaces;

    public Stone() {
    }

    public Stone(String name, String origin, double value, Color color, int numberOfFaces) {
        this.name = name;
        this.origin = origin;
        this.value = value;
        this.color = color;
        this.numberOfFaces = numberOfFaces;
    }

    public void setNumberOfFaces(int numberOfFaces) {
        this.numberOfFaces = numberOfFaces;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public String getOrigin() {
        return origin;
    }

    public double getValue() {
        return value;
    }

    public Color getColor() {
        return color;
    }

    public int getNumberOfFaces() {
        return numberOfFaces;
    }
}
