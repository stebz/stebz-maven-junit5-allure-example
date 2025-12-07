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
package org.stebz.example.step;

import org.stebz.annotation.Step;
import org.stebz.step.executable.ConsumerStep;
import org.stebz.step.executable.RunnableStep;
import org.stebz.step.executable.SupplierStep;

import java.time.Duration;

import static org.stebz.step.StepObj.stepOf;

public final class Steps {

  private Steps() {
  }

  // @formatter:off

  @Step("wait for {duration} seconds")
  public static RunnableStep wait_for_seconds(long duration) {
    return wait_for(Duration.ofSeconds(duration));
  }

  @Step("wait for {duration}")
  public static RunnableStep wait_for(Duration duration) { return stepOf(() ->
    Thread.sleep(duration.toMillis())
  );}

  @Step("generate a string from {character} of size {size}")
  public static SupplierStep<String> generate_a_string_of_character(char character,
                                                                    int size) { return stepOf(() -> {
    char[] array = new char[size];
    int pos = 0;
    while (pos < size) {
      array[pos] = character;
      pos++;
    }
    return new String(array);
  });}

  @Step("string has size {size}")
  public static ConsumerStep<String> string_has_size(int size) { return stepOf(string -> {
    if (string.length() != size) {
      throw new AssertionError("string size should be " + size + " but size is" + string.length());
    }
  });}

  @Step("string contains only {character} characters")
  public static ConsumerStep<String> string_contains_only(char character) { return stepOf(string -> {
    char[] chars = string.toCharArray();
    for (int idx = 0; idx < chars.length; idx++) {
      char currentChar = chars[idx];
      if (currentChar != character) {
        throw new AssertionError(idx + " character of string is " + currentChar);
      }
    }
  });}

  // @formatter:on
}
