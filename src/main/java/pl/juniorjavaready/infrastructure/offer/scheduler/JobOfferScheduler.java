package pl.juniorjavaready.infrastructure.offer.scheduler;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.juniorjavaready.domain.offer.JobOfferFacade;

@Log4j2
@Component
@RequiredArgsConstructor
public class JobOfferScheduler {
    private final JobOfferFacade jobOfferFacade;


    @Scheduled(cron = "${offer.jobRunOccurrence}")
    public void scheduleJobOffers() throws JsonProcessingException {
        log.info("Scheduler started");
        jobOfferFacade.findAllOffers();
    }
}
