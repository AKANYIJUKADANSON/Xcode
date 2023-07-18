package com.example.xcode.firestore

import android.app.Activity
import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.Toast
import com.example.xcode.MealsMenu
import com.example.xcode.admin.AddMenu
import com.example.xcode.models.Menu
import com.example.xcode.utilities.BaseActivity
import com.example.xcode.utilities.Constants
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class Firestore:BaseActivity() {
    private val mFirestore =  FirebaseFirestore.getInstance()
    private val authentication = FirebaseAuth.getInstance()
    private val mFireStorageReference = FirebaseStorage.getInstance()


    fun getCurrentUserID(): String {
        // An Instance of currentUser using FirebaseAuth
        val currentUser = FirebaseAuth.getInstance().currentUser

        // A variable to assign the currentUserId if it is not null or else it will be blank.
        var currentUserID = ""
        if (currentUser != null) {
            currentUserID = currentUser.uid
        }
        return currentUserID
    }

    /**
     * Uploading the image to the firebase
     * */
    // To use this function we shall just provide the activity and Uri(image location on media) as the parameters
    fun uploadImageToCloud(activity: Activity, imageUri: Uri?, imageType:String){
        val storageReference : StorageReference = mFireStorageReference.reference.child(

            imageType + System.currentTimeMillis() + "." + Constants
                .getFileExtension(activity,imageUri)
        )

        //taskSnapShot is what we will receive
        storageReference.putFile(imageUri!!)
            .addOnSuccessListener { TaskSnapshot ->
                TaskSnapshot.metadata!!.reference!!.downloadUrl
                    .addOnSuccessListener { downloadableImageUrl ->
                        when(activity){
                            // if ts the reg activity then upload the user profile img
                            is AddMenu ->{
                                activity.imageUploadSuccess(downloadableImageUrl.toString())
                            }
                        }

                    }

            }.addOnFailureListener{
                // We shall dismiss/hide the progress dialog in the activity
                when(activity){
                    is AddMenu->{
                        Toast.makeText(activity, "Image upload Failure", Toast.LENGTH_LONG).show()
                    }

                    // then also log the error
                }
                Log.e(
                    activity.javaClass.simpleName,
                    it.message,
                    it
                )
            }

    }


    fun uploadMenuDetailsToCloud(activity: AddMenu, menu:Menu){
        mFirestore.collection(Constants.MENU_COLLECTION)
            .document()
            .set(menu)
            .addOnSuccessListener {
                activity.uploadMenuDetailsToCloudSuccess()
            }
            .addOnFailureListener {
                Log.e(
                    activity.javaClass.simpleName,
                    it.message,
                    it
                )
            }
    }


    fun getMenuFromFireStore(activity:MealsMenu){
        mFirestore.collection(Constants.MENU_COLLECTION)
            .get()
            .addOnSuccessListener {menuICollection ->
                val menuItemsList:ArrayList<Menu> = ArrayList()

                for (i in menuICollection.documents){
                    val singleMenuObj = i.toObject(Menu::class.java)

                    singleMenuObj!!.menu_id = i.id

                    // Add the single menu from firestore to the menuItemsList created above
                    menuItemsList.add(singleMenuObj)
                }

                // Head to the menu listing activity if successful
                activity.menuDownloadSuccess(menuItemsList)
            }
    }
}