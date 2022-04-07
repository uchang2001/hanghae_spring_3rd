package com.Week05.mybaemin.model;

import lombok.Data;

@Data

public class Responsemodel {
    private String restaurantName;
    private foodmodel2[] foods;
    private Integer deliveryFee;
    private Integer totalPrice;
}
