package com.solvd.navigator.model;

public class Route {
    private Long id;
    private Location locationA;
    private Location locationB;
    private Transportation transportation;
    private Integer duration;
    private Integer cost;
    private Integer distance;

    public Route() {


        this.transportation = new Transportation();

    }

    public Route(Long id, Location locationA, Location locationB, Transportation transportation, Integer duration, Integer cost, Integer distance) {
        this.id = id;
        this.locationA = locationA;
        this.locationB = locationB;
        this.transportation = transportation;
        this.duration = duration;
        this.cost = cost;
        this.distance = distance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Location getLocationA() {
        return locationA;
    }

    public void setLocationA(Location locationA) {
        this.locationA = locationA;
    }

    public Location getLocationB() {
        return locationB;
    }

    public void setLocationB(Location locationB) {
        this.locationB = locationB;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Transportation getTransportation() {
        return transportation;
    }

    public void setTransportation(Transportation transportation) {
        this.transportation = transportation;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public String toString() {
        return "Route{" +
                "id=" + id +
                ", locationA=" + locationA.getName() +
                ", locationB=" + locationB.getName() +
                ", duration=" + duration +
                ", transportation=" + transportation +
                ", cost=" + cost +
                ", distance=" + distance +
                '}';
    }
}
