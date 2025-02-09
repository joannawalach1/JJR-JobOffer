package pl.juniorjavaready;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = "pl.juniorjavaready")
@EnableScheduling
@EnableMongoRepositories(basePackages = {
        "pl.juniorjavaready.domain.offer"
}

)
public class JobOfferApplication {
    public static void main(String[] args) {
        SpringApplication.run(JobOfferApplication.class, args);
    }
}
