package com.example.testflickr.features.detailPhoto.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.testflickr.databinding.ActivityDetailBinding
import com.squareup.picasso.Picasso

class DetailActivity : AppCompatActivity() {

    companion object{
        val PARAM_IMAGE_URL = "param_image_url"
        val PARAM_IMAGE_TITLE = "param_image_title"
        val PARAM_IMAGE_AUTHOR = "param_image_author"
        val PARAM_IMAGE_DESCRIPTION = "param_image_description"
        val PARAM_IMAGE_DATE = "param_image_date"
    }

    private lateinit var binding: ActivityDetailBinding
    private lateinit var imageUrl: String
    private lateinit var imageTitle: String
    private lateinit var imageAuthor: String
    private lateinit var imageDescription: String
    private lateinit var imageDate: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getParams()
        loadView()
    }

    private fun getParams(){
        imageUrl = intent.getStringExtra(PARAM_IMAGE_URL)
        imageTitle = intent.getStringExtra(PARAM_IMAGE_TITLE)
        imageAuthor = intent.getStringExtra(PARAM_IMAGE_AUTHOR)
        imageDescription = intent.getStringExtra(PARAM_IMAGE_DESCRIPTION)
        imageDate = intent.getStringExtra(PARAM_IMAGE_DATE)
    }

    private fun loadView(){
        Picasso.with(this).load(imageUrl).into(binding.detailImage)
        binding.detailTitle.text = imageTitle
        binding.detailAuthor.text = imageAuthor
        binding.detailDescription.text = imageDescription
        binding.detailDate.text = imageDate
     }
}