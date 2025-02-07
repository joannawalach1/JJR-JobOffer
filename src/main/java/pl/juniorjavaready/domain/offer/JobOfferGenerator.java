package pl.juniorjavaready.domain.offer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.client.RestTemplate;
import pl.juniorjavaready.infrastructure.offer.http.JobOfferFetcher;

import java.util.ArrayList;
import java.util.List;

public class JobOfferGenerator {
    public final JobOfferFetcher jobOfferFetcher = new JobOfferFetcher(new RestTemplate());
    private static final String BASE_URL = "http://ec2-3-120-147-150.eu-central-1.compute.amazonaws.com:9090/offers";
    public int lastTicketId = 0;

    public List<JobOffer> createJobOffers() throws JsonProcessingException {
        String responseData = jobOfferFetcher.fetchApiData(BASE_URL);
        ObjectMapper objectMapper = new ObjectMapper();
        List<JobOffer> jobOfferList = objectMapper.readValue(responseData, new TypeReference<List<JobOffer>>() {
        });

        List<JobOffer> updatedJobOfferList = new ArrayList<>();
        for (JobOffer jobOffer : jobOfferList) {
            JobOffer updatedJobOffer = new JobOffer(lastTicketId, jobOffer.company(), jobOffer.title(), jobOffer.salary(), jobOffer.offerUrl());
            updatedJobOfferList.add(updatedJobOffer);
        }
        return updatedJobOfferList;
    }

    private int generateTicketId() {
        return ++lastTicketId;
    }

}
