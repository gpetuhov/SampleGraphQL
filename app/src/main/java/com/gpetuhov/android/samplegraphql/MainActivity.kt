package com.gpetuhov.android.samplegraphql

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.apollographql.apollo.ApolloClient
import okhttp3.OkHttpClient

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val okHttpClient = OkHttpClient.Builder().build()

        val apolloClient = ApolloClient.builder()
            .serverUrl("https://graphql-pokemon.now.sh/graphql")
            .okHttpClient(okHttpClient)
            .build()
    }
}
