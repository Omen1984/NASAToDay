package org.ivanplehanov.nasa.api;

import org.ivanplehanov.nasa.api.entity.InformationObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component("communication")
public class Communication {

    private static final String REMOTE_SERVICE_URL = "https://api.nasa.gov/planetary/apod?api_key=";
    private static final String TOKEN = "vBnsVZosMJgCzV897tsZr8nsj7LPOsMJXPNYqlwn";

    @Autowired
    private RestTemplate restTemplate;

    public InformationObject getNasaInformationObject() {

        ResponseEntity<InformationObject> responseEntity = restTemplate.exchange(REMOTE_SERVICE_URL + TOKEN,
                HttpMethod.GET, null, new ParameterizedTypeReference<InformationObject>() {});
        InformationObject nasaToDay = responseEntity.getBody();

        return nasaToDay;
    }


}
