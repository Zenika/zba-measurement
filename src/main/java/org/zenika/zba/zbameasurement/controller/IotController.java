package org.zenika.zba.zbameasurement.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.zenika.zba.zbameasurement.database.InfluxDbRepository;
import org.zenika.zba.zbameasurement.model.Measure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Service
@EnableScheduling
@Api(description = "Iot Controller used to get data from the rapsberryPi and to map it to the model")
public class IotController {

    private ProcessBuilder processBuilder;
    private Process process;
    private InputStream inputStream;
    private String curl;
    private Measure measure;
    private InfluxDbRepository influxDb;

    public IotController(Measure measure, InfluxDbRepository influxDb) {
        this.measure = measure;
        this.influxDb = influxDb;
        getTemp();
    }

    @Scheduled(fixedDelay = 10000)
    private void getTemp() {
        curl = "curl -X GET http://192.168.1.96:8090/rest/temp";
        processBuilder = new ProcessBuilder(curl.split(" "));
        try {
            process = processBuilder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        inputStream = process.getInputStream();
        String json = getStringFromInputStream(inputStream);
        ObjectMapper mapper = new ObjectMapper();
        try {
            measure = mapper.readValue(json, Measure.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(measure.toString());
        influxDb.insertMeasure(measure);
    }

    private static String getStringFromInputStream(InputStream stream) {

        BufferedReader buffer = null;
        StringBuilder stringBuilder = new StringBuilder();
        String input;
        try {
            buffer = new BufferedReader(new InputStreamReader(stream));
            while ((input = buffer.readLine()) != null) {
                stringBuilder.append(input);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (buffer != null) {
                try {
                    buffer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return stringBuilder.toString();
    }
}
