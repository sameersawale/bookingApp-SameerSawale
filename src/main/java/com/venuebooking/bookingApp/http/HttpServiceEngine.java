package com.venuebooking.bookingApp.http;

import com.venuebooking.bookingApp.DTOs.SportsApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class HttpServiceEngine {

    private final RestClient restClient;

    @Value("${sports.api.base-url}")
    private String baseUrl;



    public List<HttpRequest> fetchSports(){
        try {
            SportsApiResponse response=restClient.get()
                    .uri(baseUrl)
                    .retrieve()
                    .body(SportsApiResponse.class);

            if (response==null || response.getData()==null){
                log.error("Received null or empty response from sports API");
                throw new IllegalArgumentException("Invalid response from sport API");
            }
            log.info("Successfully fetch {} sports from public API", response.getData());

            return response.getData();
        }
        catch (Exception ex){
            log.error("Error occurred while fetching sports from public API", ex);
            throw new RuntimeException("Failed to fetch sports", ex);
        }
    }



}
