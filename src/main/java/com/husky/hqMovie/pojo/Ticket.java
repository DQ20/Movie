package com.husky.hqMovie.pojo;

import lombok.Data;

import java.util.Date;
@Data
public class Ticket {
    private String name;
    private Double price;
    private Date startTime;
    private Integer duration;
    private Integer room;
    private String seat;
}
