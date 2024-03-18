package com.ifs21004.myrecycleview

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.ifs21004.myrecycleview.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private var novel: Novel? = null

    /*fungsi 1*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        novel = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(
                EXTRA_NOVEL,
                Novel::class.java
            )
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_NOVEL)
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        if (novel != null) {
            supportActionBar?.title = "Novel ${novel!!.name}"
            loadData(novel!!)
            binding.tvDetailTitle.text = novel!!.name
        } else {
            finish()
        }
        binding.btnShare.setOnClickListener {
            shareNovel()
        }
    }

    private fun loadData(novel: Novel) {
        binding.ivDetailImage.setImageResource(novel.image)
        binding.tvDetailName.text = novel.name
        binding.tvDetailDesc.text = novel.desc
        binding.tvDetailGenre.text = novel.genre
        binding.tvDetailAuthor.text = novel.author
        binding.tvDetailYear.text = novel.year
        binding.tvDetailRating.text = novel.rating
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    /*fungsi 2*/
    private fun shareNovel() {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "text/plain"
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Info Novel: ${novel?.name}")
        val shareMessage = "Judul: ${novel?.name}\n" +
                "Deskripsi: ${novel?.desc}\n" +
                "Genre: ${novel?.genre}\n" +
                "Penulis: ${novel?.author}\n" +
                "Tahun Terbit: ${novel?.year}\n" +
                "Rating: ${novel?.rating}"
        shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
        startActivity(Intent.createChooser(shareIntent, "Bagikan melalui"))
    }

    companion object {
        const val EXTRA_NOVEL = "extra_novel"

    }
    fun onBackPressed(view : View){
        finish()
    }
}
