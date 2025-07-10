markdown

# Monitoring Stack Setup with Prometheus and Grafana

This repository contains a Docker Compose configuration to set up a monitoring stack with **Prometheus** for metrics collection, **Grafana** for visualization, and **Node Exporter** for system metrics. The setup includes a sample Node Exporter dashboard in Grafana, stored as `monitoring/dashboard-example.json`.

## Prerequisites

- **Docker** and **Docker Compose** installed on your system. [Install Docker](https://docs.docker.com/get-docker/).
- Basic familiarity with Docker, Prometheus, and Grafana.

## Directory Structure

.
├── monitoring/
│   ├── docker-compose.yml
│   ├── prometheus.yml
│   └── dashboard-example.json
|   └── README.md
├── README.md
└── [other files, e.g., Dockerfile, pom.xml]

## Setup Instructions

### Step 1: Start the Monitoring Stack

Run the following command from the `monitoring` folder to start Prometheus, Grafana, and Node Exporter:

```bash
cd monitoring
docker compose up -d

This command:Starts Prometheus on http://localhost:9090.
Starts Grafana on http://localhost:3000.
Starts Node Exporter on http://localhost:9100.

Verify the services are running:bash

docker compose ps

Step 2: Log in to PrometheusOpen your browser and navigate to http://localhost:9090.
No login is required for Prometheus by default.
Check the Status > Targets page to ensure the prometheus and node-exporter targets are up.
Test a query in the Graph tab, e.g., rate(node_cpu_seconds_total{mode='user'}[5m]).

Step 3: Log in to GrafanaOpen your browser and navigate to http://localhost:3000.
Log in with the default credentials:Username: admin
Password: admin

You'll be prompted to change the password, but you can skip this for testing.

Step 4: Verify or Configure Prometheus Data SourceThe Prometheus data source should be automatically provisioned if grafana-provisioning/datasources/datasource.yml is correctly set up and mounted. To confirm or fix:In Grafana, go to Configuration (gear icon) > Data Sources.
Look for a data source named Prometheus with the URL http://prometheus:9090.
Click Test to verify connectivity.

If the datasource does not exist (e.g., you see "datasource same does not exist"):Add it manually:Go to Configuration > Data Sources > Add data source.
Select Prometheus.
Set the URL to http://prometheus:9090.
Click Save & Test.

Ensure docker-compose.yml includes a volume for provisioning. If the grafana-provisioning folder is missing, create it with a datasources/datasource.yml file containing:yaml

apiVersion: 1
datasources:
  - name: Prometheus
    type: prometheus
    url: http://prometheus:9090
    access: proxy
    isDefault: true

And update monitoring/docker-compose.yml to mount it, e.g.:yaml

volumes:
  - ./grafana-provisioning:/etc/grafana/provisioning

Step 5: Import or Verify the Example DashboardThe sample Node Exporter dashboard is located in monitoring/dashboard-example.json. To verify or import:In Grafana, go to Dashboards > Browse.
Look for a dashboard named Node Exporter Quickstart. If it's not visible, provisioning may not be set up.

To import the dashboard manually (if not provisioned):Go to Dashboards > New > Import.
Click Upload JSON file and navigate to monitoring/dashboard-example.json from your local directory.
Select the Prometheus data source (configured in Step 4).
Click Import.

Alternatively, import a comprehensive Node Exporter dashboard from Grafana's dashboard repository:Go to Dashboards > New > Import.
Enter dashboard ID 1860 (Node Exporter Full).
Select the Prometheus data source and import.

Step 6: Explore and Customize DashboardsThe dashboard (monitoring/dashboard-example.json) shows CPU usage. Add more panels with PromQL queries like:Memory Usage: node_memory_MemAvailable_bytes / node_memory_MemTotal_bytes * 100
Disk Usage: node_filesystem_free_bytes

Save your custom dashboards in Grafana.

TroubleshootingPrometheus Targets Down: Check http://localhost:9090/targets. Ensure services are running (docker-compose ps) and ports are accessible.
No Data in Grafana: Verify the Prometheus data source URL and set the dashboard time range to "Last 5 minutes." Ensure the datasource is correctly configured (Step 4).
Port Conflicts: If ports 9090, 3000, or 9100 are in use, edit monitoring/docker-compose.yml to map to different host ports (e.g., 3001:3000 for Grafana).
Dashboard Not Provisioned: If dashboard-example.json isn't loaded, ensure grafana-provisioning/dashboards/dashboard.yml (if present) points to /etc/grafana/provisioning/monitoring/dashboard-example.json. Otherwise, use the manual import method.

Stopping the StackTo stop and remove the containers from the monitoring folder:bash

cd monitoring
docker-compose down

To remove volumes and persisted data:bash

docker-compose down -v