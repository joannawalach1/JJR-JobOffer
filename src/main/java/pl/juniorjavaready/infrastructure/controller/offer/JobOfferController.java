package pl.juniorjavaready.infrastructure.controller.offer;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.juniorjavaready.domain.offer.JobOffer;
import pl.juniorjavaready.domain.offer.JobOfferFacade;
import pl.juniorjavaready.domain.offer.dto.JobOfferDto;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/jobOffers")
public class JobOfferController {

    private final JobOfferFacade jobOfferFacade;

    public JobOfferController(JobOfferFacade jobOfferFacade) {
        this.jobOfferFacade = jobOfferFacade;
    }

    @GetMapping("/all")
    public ResponseEntity<List<JobOfferDto>> getAllJobOffers() {
        List<JobOffer> jobOfferList = jobOfferFacade.findAllOffers();
        List<JobOfferDto> jobOfferDtoList = JobOfferMapper.toDtos(jobOfferList);
        return ResponseEntity.status(HttpStatus.OK).body(jobOfferDtoList);
    }


    @PostMapping("/save")
    public ResponseEntity<List<JobOfferDto>> saveJobOffers() throws JsonProcessingException {
        List<JobOffer> savedJobOffers = jobOfferFacade.saveJobs();

        if (savedJobOffers.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(Collections.emptyList());
        }

        List<JobOfferDto> jobOfferDtoList = JobOfferMapper.toDtos(savedJobOffers);
        return ResponseEntity.status(HttpStatus.CREATED).body(jobOfferDtoList);
    }
}
