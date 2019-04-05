package org.zenika.zba.zbameasurement.database;

import org.influxdb.InfluxDB;
import org.influxdb.dto.Point;
import org.springframework.stereotype.Repository;
import org.zenika.zba.zbameasurement.model.Measure;
import java.util.concurrent.TimeUnit;

@Repository
public class InfluxDbRepository {

    private InfluxDB influxDB;

    public InfluxDbRepository(InfluxDB influxDB) {
        this.influxDB = influxDB;
    }

    public void insertMeasure(Measure measure) {
        Point point = Point.measurement(measure.getName())
                .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                .addField("sensor", measure.getSensor())
                .addField("host", measure.getTag())
                .addField("value",measure.getValue())
                .build();
        influxDB.write(point);
        System.out.println("point written");
    }
}
