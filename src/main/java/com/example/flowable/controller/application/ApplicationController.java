package com.example.flowable.controller.application;

import com.example.flowable.process.domain.ApplicationVariable;
import com.example.flowable.service.workflow.FlowableTaskService;
import lombok.RequiredArgsConstructor;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/applications")
@RequiredArgsConstructor
public class ApplicationController {

  private final RuntimeService runtimeService;
  private final TaskService taskService;
  private final FlowableTaskService flowableTaskService;

  @PostMapping("/start")
  public String startProcess(@RequestBody Map<String, String> initialVariables) {
    Map<String, Object> variables = new HashMap<>();
    variables.put(ApplicationVariable.CONTRACT_ID.getName(), initialVariables.get("contractId"));
    variables.put(ApplicationVariable.APPLICATION_TYPE.getName(), initialVariables.get("applicationType"));
    variables.put(ApplicationVariable.COST_TYPE.getName(), initialVariables.get("costType"));
    variables.put(ApplicationVariable.DESCRIPTION.getName(), initialVariables.get("description"));

    String processInstanceId = runtimeService.startProcessInstanceByKey("simple_application", variables).getId();
    return "Process started. Instance ID: " + processInstanceId;
  }

  @PostMapping("/accept/{taskId}")
  public String acceptForm(@PathVariable String taskId, @RequestParam boolean accepted) {
    Map<String, Object> variables = new HashMap<>();
    variables.put(ApplicationVariable.ACCEPTED.getName(), accepted);
    taskService.complete(taskId, variables);
    return "Task " + taskId + " completed. Form accepted: " + accepted;
  }

  @GetMapping("/process-instances")
  public List<String> getAllProcessInstances() {
    List<ProcessInstance> instances = runtimeService.createProcessInstanceQuery().list();
    return instances.stream()
            .map(instance -> "Process instance ID: " + instance.getProcessInstanceId() + ", Process definition ID: " + instance.getProcessDefinitionId())
            .collect(Collectors.toList());
  }

  @GetMapping("/process-instance/{processInstanceId}/tasks")
  public List<String> getTaskIdsForProcessInstance(@PathVariable String processInstanceId) {
    List<Task> tasks = flowableTaskService.getTasksByProcessInstanceId(processInstanceId);
    return tasks.stream()
            .map(Task::getId)
            .collect(Collectors.toList());
  }

  @GetMapping("/tasks/{taskId}")
  public Task getTaskDetails(@PathVariable String taskId) {
    return flowableTaskService.getTaskById(taskId);
  }
}