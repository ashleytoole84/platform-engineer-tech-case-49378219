# Hazelcast Platform Engineer Tech Case

Welcome! This repository contains a simple Java application (using Maven) with deliberately flaky tests and a basic CI setup. Your task is to improve the CI/CD pipeline, implement monitoring, and enhance flaky test detection.

## Context

This repository is intentionally set up with:
- A simple Java app (`src/main/java/com/example/Welcome.java`) with a few methods, some of which are flaky (randomly fail or throw exceptions).
- Unit tests in `src/test/java/com/example/WelcomeTest.java`, including tests that are deliberately flaky.
- A basic GitHub Actions workflow (`.github/workflows/ci.yml`) that runs tests on push and pull request, without implementing any best practices into CI.

## Your Tasks

You are expected to:

### 1. Improve the CI Workflow
- Make the GitHub Actions workflow more resilient, apply best practices

### 2. Add Docker Support & Image Publishing
- Implement a `Dockerfile` to containerize the application.
- Update the CI pipeline to build and publish a Docker image to the GitHub Container Registry for this repository.
- Ensure the process aligns with best practices.

### 3. Add Monitoring & Alerting
- Deploy a local Prometheus + Grafana stack (using Docker Compose or similar) to monitor workflow health in a CI Dashboard
- Using Prometheus and Grafana is not mandatory; you may use any observability stack. However, you should ensure the solution is easily reproducible. The preferred approach is to use any observability stack with Infrastructure as Code.

### 4. Flaky Test Detection
- Develop a reusable GitHub Actions workflow in a different repository (in your own GitHub account) that:
  - Summarizes and reports the results (e.g., which tests are flaky, how often they fail).
  - (Optionally) Publishes it to the GitHub Marketplace.
- Create a workflow in this repository that uses the reusable workflow you developed to identify flaky tests.

## Deliverables
- Improved GitHub Actions workflows (in `.github/workflows/`).
- Dockerfile for the application and updated CI pipeline for image publishing.
- Docker Compose or similar setup for Prometheus + Grafana or any other observability stack, with example dashboards.
- Documentation or scripts for running and monitoring the stack locally.
- A reusable GitHub Actions workflow for flaky test detection (in a separate repository) and a workflow in this repository that uses it to identify flaky tests.

## Getting Started

1. **Build the app locally:**
   ```sh
   mvn clean package
   ```

2. **Run the app:**
   ```sh
   java -cp target/platform-engineer-tech-case-1.0-SNAPSHOT.jar com.example.Welcome
   ```

3. **Run the tests:**
   ```sh
   mvn test
   ```
   Note: Some tests are flaky and may fail or throw exceptions randomly.

4. **Build and run the Docker image locally:**
   ```sh
   docker build -t your-image-name .
   docker run --rm your-image-name
   ```

5. **View the current CI workflow:**
   See `.github/workflows/ci.yml`.

## Notes
- You are free to use any open-source tools or libraries.
- Please document any assumptions or design decisions in this README or a separate file.
- Focus on clarity, maintainability, and best practices.

---

Good luck! If you have any questions, please document them as if you were raising them in a real-world scenario. 