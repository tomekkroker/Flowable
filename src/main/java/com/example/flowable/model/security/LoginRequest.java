package com.example.flowable.model.security;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LoginRequest {
  private String username;
  private String password;

}
