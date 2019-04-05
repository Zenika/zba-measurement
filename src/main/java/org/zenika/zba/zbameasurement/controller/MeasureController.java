package org.zenika.zba.zbameasurement.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import org.zenika.zba.zbameasurement.database.InfluxDbRepository;
import org.zenika.zba.zbameasurement.model.Measure;

@Api(description = "Receive the CRUD command")
@RestController
@RequestMapping("/measure")
public class MeasureController {

    private InfluxDbRepository influxDb;

    public MeasureController(InfluxDbRepository influxDb) {
        this.influxDb = influxDb;
    }

    @PostMapping(value = "/temp")
    public Measure addMeasure(@RequestBody Measure measure) {
        System.out.println("Post :\n" + measure.toString());
        influxDb.insertMeasure(measure);
        return measure;
    }
}