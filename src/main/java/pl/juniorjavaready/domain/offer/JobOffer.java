package pl.juniorjavaready.domain.offer;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Builder
@Document(collection = "job_offers")
public record JobOffer(@Id int id,
                          @Field String title,
                          @Field String company,
                          @Field String salary,
                          @Field String offerUrl) {
}
