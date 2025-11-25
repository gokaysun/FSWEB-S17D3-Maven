package com.workintech.zoo.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Kangaroo {
    private int id;
    private String name;
    private Double height;
    private Double weight;
    private String gender;
    private Boolean isAggressive;
}
