package com.alibaba.cloud.ai.example.manus.observation;

import io.micrometer.common.KeyValue;
import io.micrometer.common.KeyValues;
import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationConvention;
import com.alibaba.cloud.ai.example.manus.planning.model.vo.ExecutionContext;

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
    var ec = context.getExecutionContext();
    return KeyValues.of(
        KeyValue.of("plan.id", ec.getPlanId()),
        KeyValue.of("success", String.valueOf(ec.isSuccess())),
        KeyValue.of("needSummary", String.valueOf(ec.isNeedSummary())));
  }

  @Override
  public KeyValues getHighCardinalityKeyValues(JmanusPlanObservationContext context) {
    var ec = context.getExecutionContext();
    return KeyValues.of(
        KeyValue.of("user.request", ec.getUserRequest() == null ? "" : ec.getUserRequest()),
        KeyValue.of("result.summary", ec.getResultSummary() == null ? "" : ec.getResultSummary()));
  }
}
