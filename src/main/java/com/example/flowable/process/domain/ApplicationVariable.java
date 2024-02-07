package com.example.flowable.process.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ApplicationVariable {
  CONTRACT_ID("contractId"),
  APPLICATION_TYPE("applicationType"),
  COST_TYPE("costType"),
  DESCRIPTION("description"),
  ACCEPTED("accepted");

  @Getter
  private final String name;
}
