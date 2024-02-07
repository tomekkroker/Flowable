package com.example.flowable.service.workflow;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

public class AcceptApplicationUserTask implements JavaDelegate {
  @Override
  public void execute(DelegateExecution execution) {
    System.out.println("Executing service task accept application");
  }
}
