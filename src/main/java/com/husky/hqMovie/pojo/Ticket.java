package com.husky.hqMovie.pojo;

import lombok.Data;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
@Data
public class Ticket implements Serializable {
    private Integer id;
    private String name;
    private Double price;
    private Date startDate;
    private Integer duration;
    private Integer room;
    private Time startTime;
}
