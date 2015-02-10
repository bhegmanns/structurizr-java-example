# Getting started with Structurizr for Java

This is a simple example of how to get started with Structurizr for Java. There is one Java source file (`StructurizrExample.java`) that shows how to create a C4 software architecture model, a system context view and then upload it to [structurizr.com](http://www.structurizr.com) through the API.

See [structurizr.com](http://www.structurizr.com) for more information about Structurizr and creating software architecture models as code.

## Building

To build this example from the sources (you'll need Java 8):

```
git clone https://github.com/simonbrowndotje/structurizr-java-example.git
cd structurizr-java-example
chmod u+x gradlew
./gradlew build
```

## Run

To run the example:

```
./gradlew run
```

__Note__: In order to upload your model to [structurizr.com](http://www.structurizr.com) through the  API, you'll need your own API key and secret. Structurizr is currently in development and will be open for closed beta testing soon.
