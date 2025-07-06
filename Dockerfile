FROM registry.access.redhat.com/ubi8/ubi-minimal
WORKDIR /work/
COPY target/quarkus-app/*-runner /work/application
CMD ["./application", "-Dquarkus.http.host=0.0.0.0"]
