package com.s7evensoftware.bromaschuck.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.s7evensoftware.bromaschuck.R
import com.s7evensoftware.bromaschuck.databinding.JokeRowBinding

class jokeAdapter: RecyclerView.Adapter<jokeAdapter.Joke>() {

    private var ContentList:ArrayList<JokeObject> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Joke {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.joke_row, parent, false)
        return Joke(view, parent.context)
    }

    fun addJoke(joke:JokeObject){
        ContentList.add(0, joke)
        notifyItemInserted(0)
    }


    override fun onBindViewHolder(holder: Joke, position: Int) {
        holder.setData(ContentList[position])
    }

    override fun getItemCount(): Int {
        return ContentList.size
    }


    inner class Joke(itemView: View, val context:Context): RecyclerView.ViewHolder(itemView){

        fun setData(joke:JokeObject){
            val rowBinding = JokeRowBinding.bind(itemView)
            rowBinding.jokeRowContent.text = joke.content

            Glide.with(context)
                .load(joke.image)
                .into(rowBinding.jokeRowImage)
        }

    }
}

class JokeObject {
    var image:String = ""
    var content:String = ""
}