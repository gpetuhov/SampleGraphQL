package com.gpetuhov.android.samplegraphql

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import com.pawegio.kandroid.toast
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.OkHttpClient

class MainActivity : AppCompatActivity() {

    companion object {
        const val HOST = "https://graphql-pokemon.now.sh/graphql"
        const val POKEMON = "Pikachu"
    }

    private lateinit var apolloClient: ApolloClient
    private lateinit var pokemonNumberQuery: PokemonNumberQuery

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initApolloClient()
        initQuery()

        queryButton.setOnClickListener { queryPokemonNumber() }
        clearButton.setOnClickListener { clearText() }
    }

    private fun initApolloClient() {
        val okHttpClient = OkHttpClient.Builder().build()

        apolloClient = ApolloClient.builder()
            .serverUrl(HOST)
            .okHttpClient(okHttpClient)
            .build()
    }

    private fun initQuery() {
        pokemonNumberQuery = PokemonNumberQuery.builder()
            .name(POKEMON)
            .build()
    }

    private fun queryPokemonNumber() {
        apolloClient.query(pokemonNumberQuery).enqueue(object : ApolloCall.Callback<PokemonNumberQuery.Data>() {
            override fun onFailure(e: ApolloException) {
                // Changing UI must be on UI thread
                runOnUiThread { toast("Error fetching Pikachu") }
            }

            override fun onResponse(response: Response<PokemonNumberQuery.Data>) {
                // Changing UI must be on UI thread
                runOnUiThread {
                    val text = "Pikachu number is: ${response.data()?.pokemon()?.number()}"
                    textView.text = text
                }
            }
        })
    }

    private fun clearText() {
        textView.text = ""
    }
}
