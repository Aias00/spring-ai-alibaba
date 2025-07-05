package com.alibaba.cloud.ai.example.manus.observation;

import io.micrometer.observation.Observation;
import com.alibaba.cloud.ai.example.manus.planning.model.vo.ExecutionContext;

public class JmanusPlanObservationContext extends Observation.Context {
  private final ExecutionContext executionContext;

  public JmanusPlanObservationContext(ExecutionContext executionContext) {
    this.executionContext = executionContext;
  }

  public ExecutionContext getExecutionContext() {
    return executionContext;
  }
}