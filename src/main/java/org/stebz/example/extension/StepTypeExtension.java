/*
 * MIT License
 *
 * Copyright (c) 2025 Evgenii Plugatar
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.stebz.example.extension;

import org.stebz.attribute.StepAttribute;
import org.stebz.extension.InterceptStep;
import org.stebz.step.StepObj;
import org.stebz.util.container.NullableOptional;

public class StepTypeExtension implements InterceptStep {
  public static final StepAttribute<StepType> STEP_TYPE = StepAttribute.nullable("extension:test_type");
  private static final StepAttribute<WithStepType> STEP_TYPE_ANNOTATION = StepAttribute.nullable(WithStepType.KEY);

  public StepTypeExtension() {
  }

  @Override
  public StepObj<?> interceptStep(StepObj<?> step,
                                  NullableOptional<Object> context) {
    WithStepType withStepType = step.get(STEP_TYPE_ANNOTATION);
    if (withStepType != null) {
      return step.withName(withStepType.value() + ": " + step.getName())
        .withAddedParam("step type", withStepType.value());
    }
    StepType stepType = step.get(STEP_TYPE);
    if (stepType != null) {
      return step.withName(stepType + ": " + step.getName())
        .withAddedParam("step type", withStepType.value());
    }
    return step;
  }
}
