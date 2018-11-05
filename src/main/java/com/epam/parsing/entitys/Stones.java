package com.epam.parsing.entitys;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "stones")
public class Stones {
    @XmlElements({
            @XmlElement(name = "precious", type = Precious.class),
            @XmlElement(name = "semiprecious", type = Semiprecious.class)
    })
    private List<Stone> stones;

    public Stones() {
        stones = new ArrayList<>();
    }

    public Stones(List<Stone> stones) {
        this.stones = stones;
    }

    public void setStones(List<Stone> stones) {
        this.stones = stones;
    }

    public List<Stone> getStones() {
        return stones;
    }
}
