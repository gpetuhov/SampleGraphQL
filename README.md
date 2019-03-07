# SampleGraphQL
The very basics of building an Android client for the GraphQL API with Apollo.

## Requirements
* Android Studio 3.5 Canary 6
* Kotlin 1.3.30-eap-11
* Android Gradle Plugin 3.5.0-alpha06
* Gradle wrapper 5.2.1
* AAPT 2

## Notes

In this sample we send GraphQL queries to a public demo Pokemon API:

https://graphql-pokemon.now.sh/

https://github.com/lucasbento/graphql-pokemon

First of all get schema.json file from the server and include it in the project. To do it install Apollo command-line tools by executing command: 

npm install -g apollo

(as described here: https://github.com/apollographql/apollo-tooling )

Then use apollo from the apollo-cli package to download a GraphQL schema by sending an introspection query to the server:

apollo schema:download --endpoint=https://graphql-pokemon.now.sh/ schema.json

The downloaded schema.json file should be placed inside /src/main/graphql directory.

Note that graphql folder should include BOTH schema and queries or the project will not build.

Note that qraphql folder MUST have subdirectories according to your package hierarchy. Schema and queries must be inside the inner subdirectory, so that the generated classes could be imported into the project.  

## References
https://www.apollographql.com/docs/android/essentials/get-started.html

https://android.jlelse.eu/hello-apollo-writing-your-first-android-app-with-graphql-d8edabb35a2

https://medium.com/mindorks/what-is-graphql-and-using-it-on-android-ab8e493abdd7