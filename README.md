This is a project for the DevOps subject on FINKI.
The task is:

You need to choose a ready-made application that also uses a database. It could be your application from a project in another subject or an open source application found online. Then, for the application and its database, you need to create the following:

(10%) Deploy the application to a public git repository
(10%) Dockerize the application
(10%) Orchestrate the application and database with Docker Compose
(20%) To choose a CI platform (GitHub Actions, GitLab CI, Jenkins, ...) and set up a pipeline for a complete CI or CI/CD solution: with a git push to upload the new version of the Docker image to a suitable registry (eg on DockerHub). Optionally, for bonus points: the Pipeline can also contain a CD part, i.e. it ends with placing the application on a deployment environment (with Docker orchestration or with Kubernetes).
To create Kubernetes manifests for the following:
(10%) Deployment for the application with the necessary ConfigMaps/Secrets
(10%) Service for the application
(10%) Ingress for the application
(10%) StatefulSet for the base with the required ConfigMaps/Secrets
(10%) Place the manifests in a separate namespace on your cluster and demonstrate that it works
