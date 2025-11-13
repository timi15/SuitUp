package hu.unideb.inf.suitup.dto;

import lombok.Data;

@Data
public class WardrobeItemFilter {

    private String type;
    private String season;
    private String color;
    private String brand;
    private String material;
}
