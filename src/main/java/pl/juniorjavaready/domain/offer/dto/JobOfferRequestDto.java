package pl.juniorjavaready.domain.offer.dto;

import lombok.Builder;

@Builder
public record JobOfferRequestDto(String title, String company, String salary, String offerUrl) {
}
