package pl.juniorjavaready.domain.offer.dto;

import lombok.Builder;

@Builder
public record JobOfferDto(int id, String title, String company, String salary, String offerUrl) {
}
