# 🚀 DevSecOps Pipeline Overview

This repository provides a complete overview of a **DevSecOps Pipeline** for a cloud-native application using modern tools and best practices. It automates the provisioning, deployment, security scanning, and monitoring of a **Java Spring Boot application** backed by **MongoDB**, hosted on an **EKS (Elastic Kubernetes Service)** cluster using **Infrastructure as Code (IaC)** principles.

---

## 🧹 Application Stack

| Component     | Technology        |
| ------------- | ----------------- |
| 📦 Repository | GitHub            |
| 🔧 Backend    | Java, Spring Boot |
| 🗃️ Database   | MongoDB           |

---

## 👥 Master Machine Setup

The master node provisions cloud infrastructure and initiates CI/CD tasks.

### 🔨 Infrastructure as Code (IaC)

Using **Terraform** to provision AWS resources:

- ✅ IAM Roles & Policies
- 🔑 Key Pairs
- 🔐 Security Groups

---

## 🔁 DevSecOps Core Components

| Tool         | Purpose                                              |
| ------------ | ---------------------------------------------------- |
| 🧪 Jenkins   | Automates the CI/CD pipeline                         |
| 🛡️ Trivy     | Scans Docker images for vulnerabilities              |
| 🕷️ OWASP ZAP | Performs dynamic application security testing (DAST) |
| 📈 SonarQube | Analyzes code quality and security issues            |
| 🐳 Docker    | Containerizes the application                        |
| 📧 Mail      | Sends pipeline alerts and notifications via email    |

---

## ☸️ Kubernetes Cluster

Hosted on **Amazon EKS**, providing scalability and resilience.

- **Nodes:**
  - Node 1
  - Node 2

---

## 🔁 GitOps with ArgoCD

Implements GitOps for continuous deployment:

- 🔄 **GitRepo**: Source of truth for application manifests
- 🚀 **ArgoCD**: Syncs Kubernetes manifests from GitHub to EKS

---

## 📈 Monitoring and Observability

| Tool          | Function                                                |
| ------------- | ------------------------------------------------------- |
| 📊 Prometheus | Metrics collection and alerting                         |
| 📉 Grafana    | Dashboard and visualization layer                       |
| 📦 Helm       | Package manager for deploying Prometheus, Grafana, etc. |

---

## 📌 Pipeline Flow Summary

1. **Code Push** to GitHub triggers Jenkins pipeline.
2. Jenkins:
   - Builds Spring Boot application
   - Runs **SonarQube** for code quality
   - Builds Docker image
   - Scans image using **Trivy**
   - Deploys manifests to GitHub for ArgoCD
3. **ArgoCD** detects manifest updates and syncs to EKS.
4. **Prometheus** and **Grafana** monitor app health and metrics.
5. Notifications sent via **Mail** upon failures or alerts.