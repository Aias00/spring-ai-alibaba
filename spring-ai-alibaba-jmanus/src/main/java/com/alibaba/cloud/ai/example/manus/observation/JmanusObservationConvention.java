package com.alibaba.cloud.ai.example.manus.observation;

import com.google.gson.Gson;
import io.micrometer.common.KeyValue;
import io.micrometer.common.KeyValues;
import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationConvention;

public class JmanusObservationConvention implements ObservationConvention<JmanusPlanObservationContext> {
  @Override
  public boolean supportsContext(Observation.Context context) {
    return context instanceof JmanusPlanObservationContext;
  }

  @Override
  public String getName() {
    return "jmanus.plan.execution";
  }

  @Override
  public String getContextualName(JmanusPlanObservationContext context) {
    return "planId:" + context.getExecutionContext().getPlanId();
  }

  @Override
  public KeyValues getLowCardinalityKeyValues(JmanusPlanObservationContext context) {
    Gson gson = new Gson();
    return KeyValues.of(
        KeyValue.of("planId", context.getExecutionContext().getPlanId()),
        KeyValue.of("context", gson.toJson(context.getExecutionContext())),
        KeyValue.of("userId", "unknown"));
  }

  @Override
  public KeyValues getHighCardinalityKeyValues(JmanusPlanObservationContext context) {
    return KeyValues.of(
        KeyValue.of("query", context.getExecutionContext().getUserRequest()));
  }
}