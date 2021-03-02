package com.example.testflickr.features.detailPhoto

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.testflickr.R
import com.example.testflickr.databinding.ActivityDetailBinding
import com.example.testflickr.features.imageScreen.ImageActivity
import com.example.testflickr.utils.DateUtils
import com.example.testflickr.utils.ImageUtils
import com.squareup.picasso.Picasso
import com.squareup.picasso.Picasso.LoadedFrom
import com.squareup.picasso.Target

class DetailActivity : AppCompatActivity() {

    companion object{
        const val PARAM_IMAGE_URL = "param_image_url"
        const val PARAM_IMAGE_TITLE = "param_image_title"
        const val PARAM_IMAGE_AUTHOR = "param_image_author"
        const val PARAM_IMAGE_DESCRIPTION = "param_image_description"
        const val PARAM_IMAGE_DATE = "param_image_date"
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
        setListeners()
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
        binding.detailTitle.text = getString(R.string.detail_title, imageTitle)
        binding.detailAuthor.text = getString(R.string.detail_author, imageAuthor)
        binding.detailDescription.text = getString(R.string.detail_description, imageDescription)
        binding.detailDate.text = getString(R.string.detail_date, DateUtils.getDateTime(imageDate))
     }

    private fun setListeners(){
        binding.shareButton.setOnClickListener {
            shareItem(imageUrl)
        }

        binding.detailImage.setOnClickListener{
            val intent = Intent(this, ImageActivity::class.java)
            intent.putExtra(PARAM_IMAGE_URL, imageUrl)
            startActivity(intent)
        }
    }

    private fun shareItem(url: String?) {
        Picasso.with(applicationContext).load(url).into(object : Target {
            override fun onBitmapLoaded(bitmap: Bitmap?, from: LoadedFrom?) {
                val intent = Intent(Intent.ACTION_SEND)
                intent.type = "image/*"
                intent.putExtra(Intent.EXTRA_STREAM, ImageUtils.getLocalBitmapUri(applicationContext, bitmap!!))
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                startActivity(Intent.createChooser(intent, "Share Image"))
            }

            override fun onBitmapFailed(errorDrawable: Drawable?) {}
            override fun onPrepareLoad(placeHolderDrawable: Drawable?) {}
        })
    }

}