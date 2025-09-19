# Example of Stebz + Maven, JUnit5, Allure

## Contents

* [Structure](#structure)
* [How to run](#how-to-run)

### Structure

| Class                                 | Description                            |
|---------------------------------------|----------------------------------------|
| `org.stebz.example.step.Steps`        | Steps                                  |
| `org.stebz.example.StebzTests`        | Tests without keywords                 |
| `org.stebz.example.StebzAAATests`     | Tests with Arrange-Act-Assert keywords |
| `org.stebz.example.StebzGherkinTests` | Tests with Gherkin keywords            |

### How to run

Run tests:

```bash
mvn clean test
```

Generate Allure report:

```bash
mvn allure:report
```

Then open `/target/site/index.html` file.
