package pl.juniorjavaready.domain.offer;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@AllArgsConstructor
public class JobOfferFacade {
    private final JobOfferGenerator jobOfferGenerator;
    private final JobOfferRepository jobOfferRepository;

    public List<JobOffer> saveJobs() throws JsonProcessingException {
        List<JobOffer> generatedJobOffers = jobOfferGenerator.createJobOffers();

        if (generatedJobOffers == null || generatedJobOffers.isEmpty()) {
            return Collections.emptyList();
        }

        List<JobOffer> savedJobOffers = new ArrayList<>();
        for (JobOffer jobOffer : generatedJobOffers) {
            int newId = JobOfferIdGenerator.getNextId();
            JobOffer jobOfferWithNewId = new JobOffer(newId, jobOffer.company(), jobOffer.title(), jobOffer.salary(), jobOffer.offerUrl());
            JobOffer savedJobOffer = jobOfferRepository.save(jobOfferWithNewId);
            savedJobOffers.add(savedJobOffer);
        }
        log.info("Saved job offers: {}", savedJobOffers);
        return savedJobOffers;
    }

    public List<JobOffer> fetchAllOffers() {
        return jobOfferRepository.findAll();
    }

    public List<JobOffer> fetchAllOffersIfNotExists() throws JsonProcessingException {
        List<JobOffer> allJobOffers = fetchAllOffers();

        return allJobOffers.stream()
                .filter(jobOffer -> !jobOffer.offerUrl().isEmpty())
                .filter(jobOffer -> !jobOfferRepository.existsByOfferUrl(jobOffer.offerUrl()))
                .toList();
    }
}
