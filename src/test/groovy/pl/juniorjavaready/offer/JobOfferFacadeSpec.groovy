package pl.juniorjavaready.offer

import pl.juniorjavaready.domain.offer.JobOffer
import pl.juniorjavaready.domain.offer.JobOfferFacade
import pl.juniorjavaready.domain.offer.JobOfferGenerator
import pl.juniorjavaready.domain.offer.JobOfferIdGenerator
import pl.juniorjavaready.domain.offer.JobOfferRepository
import spock.lang.Specification

class JobOfferFacadeSpec extends Specification {
    def jobOfferGenerator = Mock(JobOfferGenerator)
    def jobOfferRepository = Mock(JobOfferRepository)
    def jobOfferFacade = new JobOfferFacade(jobOfferGenerator, jobOfferRepository)

    def "should save jobs when generated job offers exist"() {
        given: "Generator zwraca listę ofert"
        def jobOffers = [
                new JobOffer(1, "Company A", "Developer", "5000", "url1"),
                new JobOffer(2, "Company B", "Tester", "4000", "url2")
        ]

        jobOfferGenerator.createJobOffers() >> jobOffers

        and: "Repozytorium symuluje zapis"
        jobOfferRepository.save(_ as JobOffer) >> { JobOffer jobOffer ->
            return new JobOffer(
                    JobOfferIdGenerator.getNextId(),
                    jobOffer.company(),
                    jobOffer.title(),
                    jobOffer.salary(),
                    jobOffer.offerUrl()
            )
        }

        when: "Wywołujemy zapisanie ofert"
        def savedOffers = jobOfferFacade.saveJobs()

        then: "Repozytorium powinno zapisać oferty i zwrócić je"
        savedOffers.size() == jobOffers.size()
        savedOffers.every { it.id != null }
    }


    def "should return empty list when generated job offers are empty"() {
        given:
        jobOfferGenerator.createJobOffers() >> []

        when:
        def savedOffers = jobOfferFacade.saveJobs()

        then:
        savedOffers.isEmpty()
        1 * jobOfferGenerator.createJobOffers()
        0 * jobOfferRepository.save(_)
    }

    def "should fetch all job offers from repository"() {
        given: "Repozytorium zawiera listę ofert"
        def jobOffers = [
                new JobOffer(1, "Company A", "Developer", "5000", "url1"),
                new JobOffer(2, "Company B", "Tester", "4000", "url2")
        ]

        jobOfferRepository.findAll() >> jobOffers

        when: "Pobieramy wszystkie oferty"
        def fetchedOffers = jobOfferFacade.fetchAllOffers()

        then: "Zwrócona lista powinna być identyczna jak w repozytorium"
        fetchedOffers == jobOffers
    }


    def "should fetch only job offers that do not exist in repository"() {
        given: "Repozytorium zawiera kilka ofert"
        def jobOffers = [
                new JobOffer(1, "Company A", "Developer", "5000", "url1"),
                new JobOffer(2, "Company B", "Tester", "4000", "url2")
        ]

        jobOfferRepository.findAll() >> jobOffers

        and: "Niektóre oferty już istnieją w bazie"
        jobOfferRepository.existsByOfferUrl("url1") >> true
        jobOfferRepository.existsByOfferUrl("url2") >> false

        when: "Pobieramy oferty, które nie istnieją w bazie"
        def fetchedOffers = jobOfferFacade.fetchAllOffersIfNotExists()

        then: "Zwrócona lista zawiera tylko oferty, które nie istnieją w repozytorium"
        fetchedOffers.size() == 1
        fetchedOffers[0].offerUrl() == "url2"
    }

}
