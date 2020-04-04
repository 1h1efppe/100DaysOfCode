package com.homelander.vietnamizer

import android.content.Context
import android.graphics.*
import android.net.Uri
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.core.content.FileProvider
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import java.util.*


class ImageProcessing {
    fun getGrayscale(src: Bitmap): Bitmap? {

        //Custom color matrix to convert to GrayScale
        val matrix = floatArrayOf(
            0.3f, 0.59f, 0.11f, 0f, 0f,
            0.3f, 0.59f, 0.11f, 0f, 0f,
            0.3f, 0.59f, 0.11f, 0f, 0f, 0f, 0f, 0f, 1f, 0f
        )
        val dest = Bitmap.createBitmap(
            src.width,
            src.height,
            src.config
        )
        val canvas = Canvas(dest)
        val paint = Paint()
        val filter = ColorMatrixColorFilter(matrix)
        paint.colorFilter = filter
        canvas.drawBitmap(src, 0F, 0F, paint)
        return dest
    }


    fun overlayBitmapToCenter(bitmap1: Bitmap, bitmap2: Bitmap): Bitmap? {
        val bitmap1Width = bitmap1.width
        val bitmap1Height = bitmap1.height
        val bitmap2Width = bitmap1.width
        val bitmap2Height = bitmap1.height
        val marginLeft = (bitmap1Width * 0.5 - bitmap2Width * 0.5).toFloat()
        val marginTop = (bitmap1Height * 0.5 - bitmap2Height * 0.5).toFloat()
        val overlayBitmap =
            Bitmap.createBitmap(bitmap1Width, bitmap1Height, bitmap1.config)
        val canvas = Canvas(overlayBitmap)
        val paint = Paint()
        paint.alpha = 160
        canvas.drawBitmap(bitmap1, Matrix(), null)
        canvas.drawBitmap(bitmap2, marginLeft, marginTop, paint)
        return overlayBitmap
    }

    fun saveImageToInternalStorage(bitmap: Bitmap, context: Context): Uri {
        var file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
        if (!file.exists()) {
            file.mkdir()
        }
        file = File(file, "${UUID.randomUUID()}.jpg")
        try {
            val stream: OutputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
            stream.flush()
            stream.close()
            Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show()
            Log.d("PATH:", Uri.parse(file.absolutePath).toString())
        } catch (e: IOException){
            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
            e.printStackTrace()
        }
        return FileProvider.getUriForFile(
            context,
            "com.homelander.vietnamizer.provider",
            file
        )
    }
}