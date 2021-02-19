package com.example.testflickr.features.imageScreen

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.testflickr.databinding.ActivityImageBinding
import com.example.testflickr.features.detailPhoto.view.DetailActivity
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target


class ImageActivity  : AppCompatActivity() {

    private lateinit var binding: ActivityImageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Picasso.with(applicationContext).load(intent.getStringExtra(DetailActivity.PARAM_IMAGE_URL)).into(object : Target {
            override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                binding.photoView.setImageBitmap(bitmap)
            }

            override fun onBitmapFailed(errorDrawable: Drawable?) {}
            override fun onPrepareLoad(placeHolderDrawable: Drawable?) {}
        })
    }
}