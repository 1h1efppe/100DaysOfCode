package com.homelander.vietnamizer

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.*
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.toBitmap
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import android.net.Uri
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    lateinit var imageView: ImageView
    lateinit var pickButton: Button
    lateinit var save: Button
    lateinit var uri: Uri
    lateinit var seekBar: SeekBar
    lateinit var textView: TextView
    lateinit var resultImage: Bitmap
    lateinit var cropped: Bitmap
    lateinit var cropped2: Bitmap
    lateinit var bw: Switch
    var canBeShared = false
    var blackAndWhite = false

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageView = findViewById(R.id.image_view);
        pickButton = findViewById(R.id.img_pick_btn);
        save = findViewById(R.id.save_image);
        seekBar = findViewById(R.id.seekBar)
        textView = findViewById(R.id.textView)
        bw = findViewById(R.id.blackAndWhiteSwitch)

        imageView.visibility = View.GONE
        save.visibility = View.GONE
        seekBar.visibility = View.GONE
        textView.visibility = View.GONE
        bw.visibility = View.GONE

        Permissions().getPermissions(this)
        pickButton.setOnClickListener {
            imageView.setImageResource(android.R.color.transparent)
            pickImageFromGallery()
        }
    }

    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, 1000)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode){
            Values.PERMISSION_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] ==
                        PackageManager.PERMISSION_GRANTED){
                    pickImageFromGallery()
                }
                else{
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.share_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.share -> {
            if (canBeShared) {
                Log.d("Share", "HERE")
                val intent = Intent().apply {
                    this.action = Intent.ACTION_SEND
                    this.putExtra(Intent.EXTRA_STREAM, uri)
                    this.type = "image/jpg"
                }
                Log.d("URI:", uri.toString())
                startActivity(Intent.createChooser(intent, "Share image via"))
            }
            else {
                Toast.makeText(this,"You need to save photo first", Toast.LENGTH_SHORT).show()
            }
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    fun returnImage(res: Bitmap, resource: Bitmap, bw: Boolean, alpha: Int) : Bitmap {
        cropped2 = Bitmap.createScaledBitmap(res, 1600, 2000, false);
        cropped = if (bw) {
            blackAndWhite = true
            ImageProcessing().getGrayscale(Bitmap.createScaledBitmap(resource, 1600, 2000, false))!!
        } else {
            blackAndWhite = false
            Bitmap.createScaledBitmap(resource, 1600, 2000, false)!!
        }
        resultImage = ImageProcessing().overlayBitmapToCenter(cropped2, cropped, alpha)!!
        return resultImage
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK && requestCode == Values.IMAGE_PICK_CODE) {
            val drawableResource: Bitmap =
                ResourcesCompat.getDrawable(resources, R.drawable.viet, null)!!.toBitmap();
            val image = data?.data

            Glide.with(this)
                .asBitmap()
                .load(image)
                .into(object : CustomTarget<Bitmap>(){
                    override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                        imageView.setImageBitmap(returnImage(drawableResource, resource, blackAndWhite, 125))
                    bw.setOnCheckedChangeListener { buttonView, isChecked ->
                        if (isChecked) {
                            imageView.setImageBitmap(returnImage(drawableResource, resource, true, 125))
                        }
                        else {
                            imageView.setImageBitmap(returnImage(drawableResource, resource, false, 125))
                        }
                    }
                            seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

                                override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                                    imageView.setImageBitmap(returnImage(drawableResource, resource, blackAndWhite, i))
                                }

                                override fun onStartTrackingTouch(seekBar: SeekBar) {
                                }

                                override fun onStopTrackingTouch(seekBar: SeekBar) {
                                }
                            })

                            save.setOnClickListener {
                                uri = ImageProcessing().saveImageToInternalStorage(resultImage, this@MainActivity)
                                canBeShared = true
                            }
                        }

                    override fun onLoadCleared(placeholder: Drawable?) {
                        this@MainActivity.onRestart()
                    }
                })

            imageView.visibility = View.VISIBLE
            seekBar.visibility = View.VISIBLE
            save.visibility = View.VISIBLE
            textView.visibility = View.VISIBLE
            bw.visibility = View.VISIBLE
        }
    }



}
