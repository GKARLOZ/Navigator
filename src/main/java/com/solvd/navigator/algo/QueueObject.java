package com.solvd.navigator.algo;


import com.solvd.navigator.model.Location;

public class QueueObject implements  Comparable<QueueObject>{

        private Location location;
        private Integer qWeight;

    public QueueObject(Location location, Integer duration) {
        this.location = location;
        this.qWeight = duration;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Integer getqWeight() {
        return qWeight;
    }

    public void setqWeight(Integer qWeight) {
        this.qWeight = qWeight;
    }

    @Override
    public int compareTo(QueueObject o) {
        return Integer.compare(this.qWeight, o.qWeight);
    }

    @Override
    public String toString() {
        return "QueueObject{" +
                "location Name =" + location.getName() +
                '}';
    }
}
