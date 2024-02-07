package com.example.flowable.model.application;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class ApplicationCostDto {

  private String contractId;
  private String applicationType;
  private String costType;
  private String description;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
  private LocalDate plannedDate;
}
