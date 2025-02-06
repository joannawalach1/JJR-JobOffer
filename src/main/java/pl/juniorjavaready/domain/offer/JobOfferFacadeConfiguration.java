package pl.juniorjavaready.domain.offer;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class JobOfferFacadeConfiguration {
    @Bean
    public JobOfferGenerator jobOfferGenerator() {
        return new JobOfferGenerator();
    }
    @Bean
    public JobOfferFacade jobOfferFacade(JobOfferRepository jobOfferRepository, JobOfferGenerator jobOfferGenerator) {
        JobOfferIdGenerator jobOfferIdGenerator = new JobOfferIdGenerator();
        return new JobOfferFacade(
                jobOfferGenerator,
                jobOfferRepository
        );
    }
}
