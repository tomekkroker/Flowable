package com.example.flowable.service.workflow;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

public class CreateApplicationUserTask implements JavaDelegate {
  @Override
  public void execute(DelegateExecution execution) {
    System.out.println("Executing service task create application");
  }
}
