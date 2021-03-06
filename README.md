# ocr-races
[![CircleCI](https://circleci.com/gh/ryanjbaxter/ocr-races.svg?style=svg)](https://circleci.com/gh/ryanjbaxter/ocr-races)

Repo for OCR Races service from my [blog](http://ryanjbaxter.com).

## Running

```
$ git clone https://github.com/ryanjbaxter/ocr-races
$ cd ocr-races
$ ./mvnw spring-boot:run
```

Alternatively you can run this application using the [image](https://hub.docker.com/r/ryanjbaxter/ocr-races/) from Docker Hub.

```
$ docker run -i -p 8282:8282 ryanjbaxter/ocr-races
```

# Concourse CI

To use the Concourse pipeline run

`$ fly -t local set-pipeline --pipeline ocr-races --config ci/pipelines/ocr-races.yml --var "SONATYPE_USER=username" --var "SONATYPE_PASSWORD=password`


## Using
This service has 2 API endpoints

### Race Data
To return race data hit the root of the application, for example

```
$ curl http://localhost:8282
```

In addition you can get race data including participant information for each race.  To use this endpoint you must also have
[Eureka](https://github.com/ryanjbaxter/ocr-eureka) and the [OCR Participants](https://github.com/ryanjbaxter/ocr-participants)
service running.

```
$ curl http://localhost:8282/participants
```
