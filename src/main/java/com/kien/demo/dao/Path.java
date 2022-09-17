package com.kien.demo.dao;

import com.kien.demo.model.Unit;

public class Path {
    private Integer pathLength;
    private Unit next;

    Path next(Unit next){
        this.next = next;
        return this;
    }

    Path pathLength(Integer pathLength){
        this.pathLength = pathLength;
        return this;
    }

    public Integer getPathLength() {
        return pathLength;
    }

    public Unit getNext() {
        return next;
    }
}
