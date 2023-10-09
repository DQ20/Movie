package com.husky.hqMovie.util;

import com.husky.hqMovie.service.ex.ServiceException;

import java.io.Serializable;

public class JsonResult<E> implements Serializable {
    private Integer state;
    private String massage;
    private E data;

    public JsonResult() {
    }

    public JsonResult(Integer state) {
        this.state = state;
    }

    public JsonResult(Integer state, E data) {
        this.state = state;
        this.data = data;
    }

    public JsonResult(Integer state, String massage, E data) {
        this.state = state;
        this.massage = massage;
        this.data = data;
    }

    public JsonResult(ServiceException e) {
        this.massage = e.getMessage();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "JsonResult{" +
                "state=" + state +
                ", massage='" + massage + '\'' +
                ", data=" + data +
                '}';
    }
}
