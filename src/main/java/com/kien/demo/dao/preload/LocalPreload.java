package com.kien.demo.dao.preload;

import java.util.List;

public class LocalPreload extends AbstractPreload{
    private List<String> queries;
    
    @Override
    public void setQueries(List<String> queries) {
        this.queries = queries;
    }
    
    @Override
    public List<String> getQueries() {
        return queries;
    }

    
}
