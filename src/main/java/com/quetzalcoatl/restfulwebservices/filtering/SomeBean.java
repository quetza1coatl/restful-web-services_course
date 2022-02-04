package com.quetzalcoatl.restfulwebservices.filtering;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonFilter("SomeBeanFilter")
public class SomeBean {
    private Integer field1;
    private Integer field2;

//    @JsonIgnore
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Integer field3;

    public SomeBean() {
    }


    public SomeBean(Integer field1, Integer field2, Integer field3) {
        this.field1 = field1;
        this.field2 = field2;
        this.field3 = field3;
    }


    public Integer getField1() {
        return field1;
    }

    public Integer getField2() {
        return field2;
    }

    public Integer getField3() {
        return field3;
    }

    public void setField1(Integer field1) {
        this.field1 = field1;
    }

    public void setField2(Integer field2) {
        this.field2 = field2;
    }

    public void setField3(Integer field3) {
        this.field3 = field3;
    }

    @Override
    public String toString() {
        return "SomeBean{" +
                "field1=" + field1 +
                ", field2=" + field2 +
                ", field3=" + field3 +
                '}';
    }
}
