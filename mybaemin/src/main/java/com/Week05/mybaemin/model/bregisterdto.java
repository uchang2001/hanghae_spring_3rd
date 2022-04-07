package com.Week05.mybaemin.model;

import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class bregisterdto {
    private String name;
    private Integer minOrderPrice;
    private Integer deliveryfee;
    public bregisterdto(String name, Integer minOrderPrice, Integer deliveryfee){
        this.name=name;
        this.minOrderPrice=minOrderPrice;
        this.deliveryfee=deliveryfee;
    }

}

