package com.kien.demo.dao;

import com.kien.demo.model.Unit;

public class Path {
    private int pathLength;
    private Unit next;

    Path setNext(Unit next){
        this.next = next;
        return this;
    }

    Path setPathLength(int pathLength){
        this.pathLength = pathLength;
        return this;
    }

    public int getPathLength() {
        return pathLength;
    }

    public Unit getNext() {
        return next;
    }

    @Override
    public String toString() {
        return next.toString();
    }
}
