# SafeScope — Java Port

SafeScope is a content-safety microservice ported to Java. This repository
contains a minimal Spring Boot implementation of the original Python
prototype, focused on providing a small, runnable service for URL and
text classification.

Key points
- Lightweight Spring Boot API (`safescope-java`) with a rule-based
  classifier suitable for experiments and as a starting point for a
  full Java ML integration.
- Maven-based build targeting Java 17.
- Simple unit tests using JUnit.

Project structure
- `safescope-java/pom.xml` — Maven build file
- `safescope-java/src/main/java/com/safescope` — application sources
- `safescope-java/src/test/java` — unit tests

Quick start

```powershell
cd safescope-java
mvn spring-boot:run
```

The service listens on port 8080 by default. Example request:

```bash
curl -X POST http://localhost:8080/api/classify \
  -H "Content-Type: application/json" \
  -d '{"url":"http://example.com/login/verify"}'
```

Endpoints
- `POST /api/classify` — Accepts JSON with either `url` or `text` and
  returns a JSON classification `{ "label": "safe|suspicious|malicious", "score": 0.0 }`.

Testing

```powershell
cd safescope-java
mvn test
```

Notes and next steps
- The Java implementation currently uses deterministic heuristics in
  `ClassifierService`. For production-quality accuracy you can:
  - Reimplement the ML model using a Java ML library (Smile, Weka, DL4J), or
  - Call the original Python model via a service/process bridge.
- If you'd like, I can commit these changes and push them to a new GitHub
  repository and set up a simple GitHub Actions workflow for CI.


## Java Port (safescope-java)

A minimal Java port of this project is available in the `safescope-java` directory. It provides a lightweight Spring Boot service with a rule-based classifier that mirrors the Python project's functionality as an initial conversion.

Quick start (from repository root):

```powershell
cd safescope-java
mvn spring-boot:run
```

Endpoints:
- `POST /api/classify` with JSON `{ "url": "..." }` or `{ "text": "..." }` returns a JSON classification.

Notes:
- The Java version uses simple heuristics for classification as a starting point. You can replace the `ClassifierService` implementation with a Java ML library or an interop to the original Python model.

