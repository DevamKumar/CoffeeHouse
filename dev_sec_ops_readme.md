# 🚀 DevSecOps Pipeline Overview

This repository provides a complete overview of a **DevSecOps Pipeline** for a cloud-native application using modern tools and best practices. It automates the provisioning, deployment, security scanning, and monitoring of a **Java Spring Boot application** backed by **MongoDB**, hosted on an **EKS (Elastic Kubernetes Service)** cluster using **Infrastructure as Code (IaC)** principles.

---

## 🧹 Application Stack

| Component      | Technology             |
|----------------|------------------------|
| 📦 Repository  | GitHub                 |
| 🔧 Backend     | Java, Spring Boot      |
| 🗃️ Database    | MongoDB                |

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

| Tool           | Purpose                                                                 |
|----------------|-------------------------------------------------------------------------|
| 🧪 Jenkins     | Automates the CI/CD pipeline                                            |
| 🛡️ Trivy       | Scans Docker images for vulnerabilities                                 |
| 🕷️ OWASP ZAP   | Performs dynamic application security testing (DAST)                    |
| 📈 SonarQube   | Analyzes code quality and security issues                               |
| 🐳 Docker      | Containerizes the application                                           |
| 📧 Mail        | Sends pipeline alerts and notifications via email                       |

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

| Tool          | Function                          |
|---------------|-----------------------------------|
| 📈 Prometheus | Collects and scrapes metrics      |
| 📉 Grafana    | Visualizes performance dashboards |

---

## 📌 Pipeline Flow Summary

1. **Code Commit** to GitHub triggers Jenkins CI pipeline.
2. Jenkins:
   - Builds app using Maven/Gradle
   - Runs **SonarQube** for code quality checks
   - Builds Docker image
   - Scans it with **Trivy**
   - Deploys to **EKS** using manifests
3. **ArgoCD** watches the Git repo for changes and deploys to EKS (GitOps model).
4. **Monitoring** with Prometheus + Grafana.
5. Alerts sent via **Email** on failures or anomalies.