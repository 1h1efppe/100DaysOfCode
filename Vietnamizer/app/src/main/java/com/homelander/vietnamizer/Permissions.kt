package com.homelander.vietnamizer

import android.Manifest
import android.app.Activity
import android.os.Build
import androidx.annotation.RequiresApi
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener

class Permissions {
    @RequiresApi(Build.VERSION_CODES.Q)
    fun getPermissions(activity: Activity) {
        Dexter.withActivity(activity)
            .withPermissions(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_MEDIA_LOCATION
            ).withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport) {

                }

                override fun onPermissionRationaleShouldBeShown(permissions: List<PermissionRequest?>?, token: PermissionToken?) { /* ... */
                }
            }).check()
    }
}