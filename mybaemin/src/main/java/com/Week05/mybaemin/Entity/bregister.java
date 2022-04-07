package com.Week05.mybaemin.Entity;


import com.Week05.mybaemin.model.bregisterdto;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class bregister {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    //@Size(min = 1000,max = 100000,message = "범위를 벗어났습니다.")
    private Integer minOrderPrice;
    private Integer deliveryfee;
    public bregister(String name,Integer minOrderPrice,Integer deliveryfee){
        this.name=name;
        this.minOrderPrice=minOrderPrice;
        this.deliveryfee=deliveryfee;
    }

    public bregister(bregisterdto bregister2) {
        this.name=bregister2.getName();
        this.minOrderPrice=bregister2.getMinOrderPrice();
        this.deliveryfee=bregister2.getDeliveryfee();
    }

    public void update(bregisterdto dto){
        this.name=dto.getName();
        this.minOrderPrice=dto.getMinOrderPrice();
        this.deliveryfee=dto.getDeliveryfee();
    }
}
