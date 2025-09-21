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
package org.stebz.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.stebz.annotation.gherkin.Background;
import org.stebz.annotation.gherkin.Conclusion;

import static org.stebz.StebzGherkinMethods.And;
import static org.stebz.StebzGherkinMethods.Then;
import static org.stebz.StebzGherkinMethods.When;
import static org.stebz.StebzGherkinMethods.around;
import static org.stebz.StebzMethods.step;
import static org.stebz.example.step.Steps.generate_a_string_of_character;
import static org.stebz.example.step.Steps.string_contains_only;
import static org.stebz.example.step.Steps.string_has_size;
import static org.stebz.example.step.Steps.wait_for_seconds;
import static org.stebz.extension.HiddenStepsExtension.hiddenSteps;

class StebzGherkinTests {

  @Background
  @BeforeEach
  void some_preconditions() {
    hiddenSteps(() -> {
      step("Hidden step 1");
      step("Hidden step 2");
    });
    When("precondition step");
    Then(wait_for_seconds(1));
  }

  @Conclusion
  @AfterEach
  void some_postconditions() {
    When("Postcondition step 1");
    Then("Postcondition step 2", () -> {
      step("Inner step 1");
      step("Inner step 2");
    });
  }

  @Test
  void separateSteps() {
    String string =
      When(generate_a_string_of_character('*', 5)
        .withName("I generate a ***** string"));
    Then(string_has_size(5), string);
    And(string_contains_only('*'), string);
  }

  @Test
  void aroundContext() {
    String string =
      When(generate_a_string_of_character('*', 5));
    around(string).
      Then(string_has_size(5));
    around(string).
      And(string_contains_only('*'));
  }

  @Test
  void aroundContext2() {
    around(
      When(generate_a_string_of_character('*', 5)
        .withName("I generate a ***** string"))).
      Then(string_has_size(5)).
      And(string_contains_only('*'));
  }
}