package com.solvd.navigator.model;

import java.util.ArrayList;
import java.util.List;

public class Result {

    private List<Route> routeList;

    public Result(){
        this.routeList = new ArrayList<>();
    };

    public List<Route> getRouteList() {
        return routeList;
    }

    public void setRouteList(List<Route> routeList) {
        this.routeList = routeList;
    }

    public int total(){
        return 0;
    }

    @Override
    public String toString() {
        return "Result{" +
                "routeList=" + routeList +
                '}';
    }
}
