package com.venuebooking.bookingApp.bootstrap;

import com.venuebooking.bookingApp.service.SportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SportStartupRunner implements CommandLineRunner {

    @Autowired
    private SportService sportService;

    @Override
    public void run(String... args) throws Exception {
        sportService.saveSports();
    }
}
