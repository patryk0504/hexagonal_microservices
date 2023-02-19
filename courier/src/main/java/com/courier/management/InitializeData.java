package com.courier.management;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
@RequiredArgsConstructor
@Slf4j
public class InitializeData {
    private final DataSource dataSource;

    @EventListener(ApplicationReadyEvent.class)
    public void loadData() {
        log.info("Init DATABASE");
        ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator(false, false, "UTF-8",
                new ClassPathResource("data2.sql"));
        resourceDatabasePopulator.execute(dataSource);
    }
}
