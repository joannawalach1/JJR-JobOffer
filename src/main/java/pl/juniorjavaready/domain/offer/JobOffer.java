package pl.juniorjavaready.domain.offer;

import lombok.Builder;
import org.springframework.data.annotation.Id;

@Builder
public record JobOffer(   @Id
                          int id,
                          String company,
                          String title,
                          String salary,
                          String offerUrl) {
}
