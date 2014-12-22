# dropwizard-api-examples

A repository with results of my work with Java Dropwizard framework. The work was a reference implementation of the basic functional required for a new project at Wikia.

## Usage

To run the server, run `mvn package` and then `java -jar target/dropwizard-api-examples-0.0.1-SNAPSHOT.jar server mercury.yml`. The latter should start an HTTP server on port 8080.

## Routes

The application uses http://muppet.wikia.com as the source for Mercury requests. To get an article from the Muppet Wikia:

`curl http://localhost:8080/article/50`

This will return a representation of the Kermit the Frog article in `application/hal+json` that contains the `content`, `id`, `title`, and `thumbnail`.

Executing:

`curl http://localhost:8080/article/50?fromFile=true`

will get a mocked data from a static file.
