package org.zenika.zba.zbameasurement.model;

import io.swagger.annotations.Api;
import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Api(description = "Object Temperature used to transfer data between the Rest API and the database")
@Repository
@Measurement(name = "measure")
public class Measure implements Serializable {

    @Column(name = "name")
    private String name;

    @Column(name = "sensor")
    private String sensor;

    @Column(name = "tag")
    private String tag;

    @Column(name = "value")
    private float value;

    public String getName() { return name; }

    public String getSensor() {
        return sensor;
    }

    public String getTag() {
        return tag;
    }

    public float getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Measure{" +
                "name='" + name + '\'' +
                ", sensor='" + sensor + '\'' +
                ", tag='" + tag + '\'' +
                ", value=" + value +
                '}';
    }
}