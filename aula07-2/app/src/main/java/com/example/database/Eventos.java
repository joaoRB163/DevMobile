package com.example.database;

import java.util.ArrayList;

public class Eventos {

    private Integer id;
    private Float values[] = new Float[3];

    public Eventos(Integer id, Float[] values) {
        this.id = id;
        this.values = values;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float[] getValues() {
        return values;
    }

    public void setValues(Float[] values) {
        this.values = values;
    }
}
