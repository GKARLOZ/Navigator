package com.solvd.navigator.model;

public class RouteBuilder {
    private Long id;
    private Location locationA;
    private Location locationB;
    private Transportation transportation;
    private Integer duration;
    private Integer cost;
    private Integer distance;

    public RouteBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public RouteBuilder withLocationA(Location locationA) {
        this.locationA = locationA;
        return this;
    }

    public RouteBuilder withLocationB(Location locationB) {
        this.locationB = locationB;
        return this;
    }

    public RouteBuilder withTransportation(Transportation transportation) {
        this.transportation = transportation;
        return this;
    }

    public RouteBuilder withDuration(Integer duration) {
        this.duration = duration;
        return this;
    }

    public RouteBuilder withCost(Integer cost) {
        this.cost = cost;
        return this;
    }

    public RouteBuilder withDistance(Integer distance) {
        this.distance = distance;
        return this;
    }

    public Route createRoute() {
        return new Route(id, locationA, locationB, transportation, duration, cost, distance);
    }
}