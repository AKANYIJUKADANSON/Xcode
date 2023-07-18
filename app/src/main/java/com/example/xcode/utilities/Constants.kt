package com.example.xcode.utilities

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.webkit.MimeTypeMap

object Constants {

    const val PICK_IMAGE_CODE = 101
    const val READ_EXTERNAL_STORAGE_CODE = 202

    const val MENU_COLLECTION:String = "menu"


    fun pickImageFromStorage(activity: Activity){
        /**
         * we are passing the activity parameter coz there is need to use it in any other activities
         * Reading the image from phone media storage
         */
        val imagePicker = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        activity.startActivityForResult(imagePicker, PICK_IMAGE_CODE)
    }


    fun getFileExtension(activity: Activity, url: Uri?): String? {
        /*
         * MimeTypeMap: Two-way map that maps MIME-types to file extensions and vice versa.
         *
         * getSingleton(): Get the singleton instance of MimeTypeMap.
         *
         * getExtensionFromMimeType: Return the registered extension for the given MIME type.
         *
         * contentResolver.getType: Return the MIME type of the given content URL.
         */
        return MimeTypeMap.getSingleton()
            .getExtensionFromMimeType(activity.contentResolver.getType(url!!))

        // Uri is the link to the image
    }
}