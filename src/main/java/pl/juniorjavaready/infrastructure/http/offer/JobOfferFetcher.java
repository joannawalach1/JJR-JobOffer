package pl.juniorjavaready.infrastructure.http.offer;

import org.springframework.web.client.RestTemplate;

public class JobOfferFetcher {
    private final RestTemplate restTemplate;

    public JobOfferFetcher(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String fetchApiData(String url) {
        return restTemplate.getForObject(url, String.class);
    }
}
