package com.s7evensoftware.bromaschuck

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.s7evensoftware.bromaschuck.adapters.JokeObject
import com.s7evensoftware.bromaschuck.adapters.jokeAdapter
import com.s7evensoftware.bromaschuck.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val apiRequestQueue by lazy { Volley.newRequestQueue(this) }
    private val apiURL = "https://api.chucknorris.io/jokes/random"
    private val jokeAdapter:jokeAdapter by lazy { jokeAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        prepareViews()
        setContentView(binding.root)
    }

    private fun callJokesApi(){
        val request = JsonObjectRequest(
            Request.Method.GET,
            apiURL,
            null,
            { responseObject ->
                val newJoke = JokeObject()
                newJoke.image = responseObject.getString("icon_url")
                newJoke.content = responseObject.getString("value")
                jokeAdapter.addJoke(newJoke)
            },
            { error ->
                Log.e("Main","Error: ${error.message}")
            }
        )

        apiRequestQueue.add(request)
    }

    private fun prepareViews() {
        binding.mainJokesContainer.layoutManager = LinearLayoutManager(this)
        binding.mainJokesContainer.adapter = jokeAdapter
        fillJokes()
    }

    private fun fillJokes() {
        for( i in 1..20){
            callJokesApi()
        }
    }


}