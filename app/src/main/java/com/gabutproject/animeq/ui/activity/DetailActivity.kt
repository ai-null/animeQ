package com.gabutproject.animeq.ui.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.gabutproject.animeq.R
import com.gabutproject.animeq.databinding.DetailActivityBinding
import com.gabutproject.animeq.viewmodel.DetailViewModel

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: DetailActivityBinding
    private lateinit var viewModel: DetailViewModel
    private var globalMenu: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // set content view
        binding = DataBindingUtil.setContentView(this, R.layout.detail_activity)

        // set lifeCycleOwner so the activity can observe data change
        binding.lifecycleOwner = this

        // null-safety - check if the data is ready or not
        // mal_id should be ready the moment detail activity accessed. this is for safety
        if (intent.hasExtra("mal_id")) {
            val application = requireNotNull(this).application
            viewModel = DetailViewModel(intent.extras!!.getInt("mal_id"), application)

            updateLiveData()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.share_and_save_item, menu)
        this.globalMenu = menu

        viewModel.bookmarked.observe(this, Observer { bookmarked ->
            bookmarked?.let {
                setBookmarked(it)
            }
        })

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val url = viewModel.animeProperty.value?.url

        when (item.itemId) {
            R.id.bookmark_item -> viewModel.bookmark()

            R.id.share_item -> {
                startActivity(
                    ShareCompat.IntentBuilder.from(this)
                        .setText(url)
                        .setType("text/plain")
                        .intent
                )
            }
        }

        return true
    }

    private fun updateLiveData() {
        viewModel.animeProperty.observe(this, Observer {
            it?.let {
                binding.property = it
                title = it.title
            }
        })

        viewModel.error.observe(this, Observer { error ->
            error.message?.let {
                showToast(it)
            }
        })
    }

    private fun showToast(message: String) {
        Toast.makeText(
            this,
            message,
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun setBookmarked(bookmarked: Boolean) {
        val item = globalMenu?.getItem(0)
        val color = this.getColor(R.color.colorPrimaryText)

        globalMenu?.let {
            if (bookmarked) {
                val icon = getDrawable(R.drawable.ic_baseline_bookmark_24)
                icon!!.setTint(color)
                item?.icon = icon
                showToast("Bookmarked")
            } else {
                val icon = getDrawable(R.drawable.ic_bookmark_border_24)
                icon!!.setTint(color)
                item?.icon = icon
                showToast("Removed from Bookmark")
            }
        }
    }
}