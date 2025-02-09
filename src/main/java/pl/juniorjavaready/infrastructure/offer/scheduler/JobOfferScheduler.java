package pl.juniorjavaready.infrastructure.offer.scheduler;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.juniorjavaready.domain.offer.JobOffer;
import pl.juniorjavaready.domain.offer.JobOfferFacade;

import java.util.List;

@Log4j2
@Component
@RequiredArgsConstructor
public class JobOfferScheduler {
    private final JobOfferFacade jobOfferFacade;

    @Scheduled(fixedDelayString = "${http.offer.scheduler.request.delay}")
    public void scheduleJobOffers() throws JsonProcessingException {
        log.info("Scheduler started");
        List<JobOffer> jobOffers = jobOfferFacade.fetchAllOffers();
        log.info("Offers added{}", jobOffers);
    }
}
