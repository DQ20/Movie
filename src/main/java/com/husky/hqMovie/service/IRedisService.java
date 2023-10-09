package com.husky.hqMovie.service;

public interface IRedisService {
    void setValue(String key, String value);
    String getValue(String key);
}
