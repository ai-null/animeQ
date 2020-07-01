package com.gabutproject.animeq.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.gabutproject.animeq.R
import com.gabutproject.animeq.databinding.ActivityMainBinding
import com.gabutproject.animeq.network.AnimeProperty

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val adapter: SeasonalAnimeAdapter = SeasonalAnimeAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // set the activity to use the layout
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val viewModel = MainActivityViewModel()

        // define viewModel and lifecycleOwner to observe changed data
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        binding.seasonalAnimeList.adapter = adapter
        viewModel.seasonalAnime.observe(this, Observer {
            it?.let {
                adapter.data = it.anime
                Log.i("anime data", "${adapter.data[0]}")
                Log.i("anime list", "${it.anime[0]}")
            }
        })
    }
}

class SeasonalAnimeViewHolder(item: View) : RecyclerView.ViewHolder(item) {
    val title: TextView = item.findViewById(R.id.item_title)

    companion object {
        @LayoutRes
        val LAYOUT = R.layout.seasonal_anime_item
    }
}

class SeasonalAnimeAdapter : RecyclerView.Adapter<SeasonalAnimeViewHolder>() {
    var data = listOf<AnimeProperty>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeasonalAnimeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val withDataBinding = layoutInflater.inflate(
            SeasonalAnimeViewHolder.LAYOUT,
            parent,
            false
        )

        return SeasonalAnimeViewHolder(withDataBinding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: SeasonalAnimeViewHolder, position: Int) {
        val item = data[position]
        holder.title.text = item.title
        Log.i("anime title", item.title)
    }

}