package com.kien.tft.dao.preload;

import java.util.List;

import com.kien.tft.model.*;

public interface Preload {
    public List<Query> getQueries();
    public List<Unit> getUnits();
    public List<Trait> getTraits();
}
