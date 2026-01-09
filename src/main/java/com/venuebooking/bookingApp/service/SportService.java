package com.venuebooking.bookingApp.service;

import com.venuebooking.bookingApp.http.HttpRequest;
import com.venuebooking.bookingApp.http.HttpServiceEngine;
import com.venuebooking.bookingApp.model.Sport;
import com.venuebooking.bookingApp.repository.SportRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SportService {

    @Autowired
    private SportRepository sportRepository;

    @Autowired
    private HttpServiceEngine httpServiceEngine;

    public void saveSports(){
        List<HttpRequest> httpRequests=httpServiceEngine.fetchSports();

        if(httpRequests.isEmpty()){
            log.error("No sports received from public API");
            return;
        }

        for(HttpRequest request:httpRequests){
            Sport sport=Sport.builder()
                    .sportId(request.getSportId())
                    .sportCode(request.getSportCode())
                    .sportName(request.getSportName())
                    .build();
            sportRepository.save(sport);

            log.info("Sports are successfully save to DB");
        }
    }


}
