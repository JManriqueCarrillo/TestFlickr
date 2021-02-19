package com.example.testflickr.features.detailPhoto.view

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.example.testflickr.databinding.ActivityDetailBinding
import com.example.testflickr.features.imageScreen.ImageActivity
import com.example.testflickr.utils.DateUtils
import com.squareup.picasso.Picasso
import com.squareup.picasso.Picasso.LoadedFrom
import com.squareup.picasso.Target
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


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
        binding.detailDate.text = DateUtils.getDateTime(imageDate)

        binding.shareButton.setOnClickListener {
            shareItem(imageUrl)
        }

        binding.detailImage.setOnClickListener{
            val intent = Intent(this, ImageActivity::class.java)
            intent.putExtra(DetailActivity.PARAM_IMAGE_URL, imageUrl)
            startActivity(intent)
        }
     }

    fun shareItem(url: String?) {
        Picasso.with(applicationContext).load(url).into(object : Target {
            override fun onBitmapLoaded(bitmap: Bitmap?, from: LoadedFrom?) {
                val intent = Intent(Intent.ACTION_SEND)
                intent.type = "image/*"
                intent.putExtra(Intent.EXTRA_STREAM, getLocalBitmapUri(bitmap!!))
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                startActivity(Intent.createChooser(intent, "Share Image"))
            }

            override fun onBitmapFailed(errorDrawable: Drawable?) {}
            override fun onPrepareLoad(placeHolderDrawable: Drawable?) {}
        })
    }

    fun getLocalBitmapUri(bmp: Bitmap): Uri? {
        var bmpUri: Uri? = null
        try {
            val file = File(
                getExternalFilesDir(Environment.DIRECTORY_PICTURES),
                "share_image_" + System.currentTimeMillis() + ".png"
            )
            val out = FileOutputStream(file)
            bmp.compress(Bitmap.CompressFormat.PNG, 90, out)
            out.close()
            bmpUri = FileProvider.getUriForFile(this, getApplicationContext().getPackageName().toString() + ".provider", file)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return bmpUri
    }

}