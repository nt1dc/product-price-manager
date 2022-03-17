package com.okenit.productpricemanager.parsers;

import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;

public interface ProductParser {
    @Scheduled(cron = ("$(SCHEDULE)"))
    void parse() throws IOException;
}
