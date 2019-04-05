package org.zenika.zba.zbameasurement.configuration;

import org.influxdb.BatchOptions;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InfluxDbConfiguration {

    @Bean
    public InfluxDB configureInfluxDb(InfluxDbConfigurationProperties prop) {
        InfluxDB influxDB = InfluxDBFactory.connect(prop.getUrl(), prop.getUser(), prop.getPassword());
        influxDB.setDatabase(prop.getDatabase());
        influxDB.createRetentionPolicy("RetentionPolicy", prop.getDatabase(), "30d", "30m", 2, true);
        influxDB.setRetentionPolicy("RetentionPolicy");
        influxDB.enableBatch(BatchOptions.DEFAULTS.actions(2000).flushDuration(100));
        return influxDB;
    }
}
