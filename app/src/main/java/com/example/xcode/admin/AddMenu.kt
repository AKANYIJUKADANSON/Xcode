package com.example.xcode.admin

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.xcode.R
import com.example.xcode.firestore.Firestore
import com.example.xcode.models.Menu
import com.example.xcode.order.Welcome
import com.example.xcode.utilities.BaseActivity
import com.example.xcode.utilities.Constants
import java.io.IOException

class AddMenu : BaseActivity(), OnClickListener {

    private var mSelectImageFileUri: Uri? = null
    lateinit var mDownloadableImageUri:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_menu)

        // Actionbar
        setUpActionBar()

        val loadMenuImageButton = findViewById<ImageView>(R.id.iv_load_menu_image)
        loadMenuImageButton.setOnClickListener(this)

        val uploadImageButton = findViewById<Button>(R.id.add_menu_button)
        uploadImageButton.setOnClickListener(this)

    }

    private fun setUpActionBar(){
        val myToolBar = findViewById<Toolbar>(R.id.add_menu_toolbar)
        setSupportActionBar(myToolBar)
        val actionBar = supportActionBar
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_back_arrow)
        }

        myToolBar.setNavigationOnClickListener { onBackPressed() }
    }


    override fun onClick(view: View?) {
        if (view != null) {
            when (view.id) {
                R.id.iv_load_menu_image -> {
                    // load image from external storage
                    loadMenuImage()
                }

                R.id.add_menu_button ->{
//                    if (validateUserData()){
                        // TODO: Add a progress dialog
                      Firestore().uploadImageToCloud(this, mSelectImageFileUri, "Menu_image")
//                    }
                }
            }
        }
    }

    private fun validateUserData():Boolean{
        val menuName = findViewById<EditText>(R.id.ed_menu_name)

        return when {
            mSelectImageFileUri == null -> {
                showErrorSnackBar(resources.getString(R.string.errorImageUrl), true)
                false
            }

            TextUtils.isEmpty(menuName.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.errorMenuName), true)
                false
            }
            else ->{
                true
            }

        }

    }

    private fun loadMenuImage(){
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED){

                // pick an image
                Constants.pickImageFromStorage(this)
            }
            else{
                // Ask for the permissions
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(
                        android.Manifest.permission.READ_EXTERNAL_STORAGE),
                    Constants.READ_EXTERNAL_STORAGE_CODE
                )
            }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == Constants.READ_EXTERNAL_STORAGE_CODE ){
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                /**
                 * grantResults.isEmpty() -> we have a result from the user
                 * grantResults[0] -> position of yes or no
                 * PackageManager.PERMISSION_GRANTED-> granted access
                 */

                //Since we are granted the permission so we choose the image to upload using the uploadImage function
                Constants.pickImageFromStorage(this)
            }else{
                Toast.makeText(this, R.string.errorPermission, Toast.LENGTH_LONG).show()
            }
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        //Checking whether the result image is ok
        if(resultCode == Activity.RESULT_OK && requestCode == Constants.PICK_IMAGE_CODE && data !=null){
            //Checking if the request code passed from the onActivityResult is the one we used to picked the image
            //Checking if the data is not equal to null coz it can happen otherwise the app can crash

            val loadMenuImageButton = findViewById<ImageView>(R.id.iv_load_menu_image)
            //Changing the icon for loading image to edit image after the image is selected and on the app
            loadMenuImageButton.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_edit))

            try {
                //selected uri for the image from storage media and this uri is one we
                // set as a parameter on setting the image
                //Picking the image
                mSelectImageFileUri = data.data!! // LOCATION OF THE FILE

                val loadedImage = findViewById<ImageView>(R.id.menu_image)
                /*
                        Using Glide loader: its fast and can smartly define the type of file that one is uploading
                *  Syntax for glide
                        Glide.with(the activity wea to oad the file).load( the Uri or location of the image)
                        .into( the exact view wea to insert the file within the selected activity)
                */
                //setting the image from the URI or use Glideloader
//                Glide.with(this).load(mSelectImageFileUri).into(loadedImage)
                // Using setImageUri option below
                loadedImage.setImageURI(mSelectImageFileUri)
            }catch (e: IOException){
                e.printStackTrace()
                Toast.makeText(this, "Image selection failure", Toast.LENGTH_LONG).show()
            }

        }else if(resultCode == Activity.RESULT_CANCELED){
            Log.e("Result cancelled", "Image selected cancelled")
        }
    }


    // After the image is uploaded, then save the details
    fun imageUploadSuccess(downloadableImageUrl:String){
        mDownloadableImageUri = downloadableImageUrl
//        Toast.makeText(this, "Image uploaded and Uri = $ImageUrl", Toast.LENGTH_LONG).show()
        uploadMenuDetails()

        Log.e("Image DownloadUrl: ", downloadableImageUrl)
    }

    private fun uploadMenuDetails() {
        val menuName = findViewById<EditText>(R.id.ed_menu_name).text.toString()
        val menuImageUrl = mDownloadableImageUri
        val menuToSave = Menu(menuImageUrl, menuName)

        Firestore().uploadMenuDetailsToCloud(this, menuToSave)
    }

    fun uploadMenuDetailsToCloudSuccess(){
        Toast.makeText(this, "Menu added to cloud successfully", Toast.LENGTH_LONG).show()
        startActivity(Intent(this, Welcome::class.java))
    }

}

