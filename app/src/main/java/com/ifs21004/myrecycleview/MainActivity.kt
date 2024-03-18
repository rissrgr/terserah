package com.ifs21004.myrecycleview

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ifs21004.myrecycleview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val dataNovel = ArrayList<Novel>()

    /*Fungsi 1*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.setHasFixedSize(false)
        dataNovel.addAll(getListNovels())
        showRecyclerList()

        binding.profileIcon.setOnClickListener {
            navigateToProfile()
        }
    }

    /*fungsi 2*/
    @SuppressLint("Recycle")
    private fun navigateToProfile() {
        val intent = Intent(this, ProfileActivity::class.java)
        startActivity(intent)
    }

    /*fungsi 3*/
    private fun getListNovels(): ArrayList<Novel> {
        val dataName = resources.getStringArray(R.array.app_name)
        val dataImage = resources.obtainTypedArray(R.array.novel_image)
        val dataDesc = resources.getStringArray(R.array.novel_desc)
        val dataGenre = resources.getStringArray(R.array.novel_genre)
        val dataPenulis = resources.getStringArray(R.array.novel_author)
        val dataTahun = resources.getStringArray(R.array.novel_year)
        val dataRating = resources.getStringArray(R.array.novel_rating)

        val listNovel = ArrayList<Novel>()
        for (i in dataName.indices) {
            val novel = Novel(
                dataName[i],
                dataImage.getResourceId(i, -1),
                dataDesc[i],
                dataGenre[i],
                dataPenulis[i],
                dataTahun[i],
                dataRating[i]
            )
            listNovel.add(novel)
        }
        dataImage.recycle()
        return listNovel
    }


    /*fungsi 4*/
    private fun showRecyclerList() {
        val isLandscape =
            resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
        val layoutManager = if (isLandscape) {
            GridLayoutManager(this, 2)
        } else {
            LinearLayoutManager(this)
        }

        binding.recyclerView.layoutManager = layoutManager
        val listNovelAdapter = ListNovelAdapter(dataNovel)
        binding.recyclerView.adapter = listNovelAdapter

        listNovelAdapter.setOnItemClickCallback(object : ListNovelAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Novel) {
                showSelectedNovel(data)
            }
        })
    }

    /*fungsi 5*/
    private fun showSelectedNovel(novel: Novel) {
        val intentWithData = Intent(this@MainActivity, DetailActivity::class.java)
        intentWithData.putExtra(DetailActivity.EXTRA_NOVEL, novel)
        startActivity(intentWithData)
    }
}
