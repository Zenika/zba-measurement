package org.zenika.zba.zbameasurement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.zenika.zba.zbameasurement.configuration.InfluxDbConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(InfluxDbConfigurationProperties.class)
public class ZbaMeasurementApplication {
	public static void main(String[] args) { SpringApplication.run(ZbaMeasurementApplication.class, args); }
}
