package com.epam.parsing.entitys;

import com.epam.parsing.enums.Color;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({Stone.class})
public class Precious extends Stone {
    @XmlElement(name = "is-natural")
    private boolean isNatural;
    @XmlElement(name = "transparency")
    private double transparency;

    public Precious() {
    }

    public Precious(String name, String origin, double value, Color color, int numberOfFaces, boolean isNatural) {
        super(name, origin, value, color, numberOfFaces);
        this.isNatural = isNatural;
    }

    public void setNatural(boolean natural) {
        isNatural = natural;
    }

    public void setTransparency(double transparency) {
        this.transparency = transparency;
    }

    public boolean isNatural() {
        return isNatural;
    }

    public double getTransparency() {
        return transparency;
    }
}
