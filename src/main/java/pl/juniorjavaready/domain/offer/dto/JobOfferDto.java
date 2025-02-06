package pl.juniorjavaready.domain.offer.dto;

import lombok.Builder;

@Builder
public record JobOfferDto(int id, String company, String position, String salary, String offerUrl) {
}
