package com.example.flowable.service.workflow;

import lombok.RequiredArgsConstructor;
import org.flowable.engine.TaskService;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FlowableTaskService {

  private final TaskService taskService;

  public List<Task> getTasksByProcessInstanceId(String processInstanceId) {
    return taskService.createTaskQuery()
            .processInstanceId(processInstanceId)
            .list();
  }

  public Task getTaskById(String taskId) {
    return taskService.createTaskQuery()
            .taskId(taskId)
            .singleResult();
  }
}
