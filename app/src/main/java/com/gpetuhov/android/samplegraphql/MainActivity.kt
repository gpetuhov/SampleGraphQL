package com.gpetuhov.android.samplegraphql

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import com.pawegio.kandroid.toast
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

        val query = PokemonNumberQuery.builder()
            .name("Pikachu")
            .build()

        apolloClient.query(query).enqueue(object : ApolloCall.Callback<PokemonNumberQuery.Data>() {
            override fun onFailure(e: ApolloException) {
                runOnUiThread { toast("Error fetching Pikachu") }
            }

            override fun onResponse(response: Response<PokemonNumberQuery.Data>) {
                runOnUiThread { toast("Pikachu number is: ${response.data()?.pokemon()?.number()}") }
            }
        })
    }
}
