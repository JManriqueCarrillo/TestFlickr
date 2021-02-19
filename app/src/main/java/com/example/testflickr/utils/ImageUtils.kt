package com.example.testflickr.utils

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Environment
import androidx.core.content.FileProvider
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class ImageUtils {

    companion object{

        fun getLocalBitmapUri(ctx: Context, bmp: Bitmap): Uri? {
            var bmpUri: Uri? = null
            try {
                val file = File(
                    ctx.getExternalFilesDir(Environment.DIRECTORY_PICTURES),
                    "share_image_" + System.currentTimeMillis() + ".png"
                )
                val out = FileOutputStream(file)
                bmp.compress(Bitmap.CompressFormat.PNG, 90, out)
                out.close()
                bmpUri = FileProvider.getUriForFile(ctx, ctx.applicationContext.packageName.toString() + ".provider", file)
            } catch (e: IOException) {
                e.printStackTrace()
            }
            return bmpUri
        }
    }

}