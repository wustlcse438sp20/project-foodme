package com.example.foodme.Activities

import android.Manifest
import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.foodme.R
import kotlinx.android.synthetic.main.activity_gallery.*
import android.content.ContentValues.TAG
import android.util.Log

class GalleryActivity : AppCompatActivity() {
    val REQUEST_CAMERA_PERMISSIONS = 1000;
    private val IMAGE_CAPTURE_CODE = 1001
    var image_uri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)

        capture_btn.setOnClickListener {
            var permission = ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)
            if(permission != PackageManager.PERMISSION_GRANTED) {  // Not granted yet.
                ActivityCompat.requestPermissions(this,
                    arrayOf(android.Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE),REQUEST_CAMERA_PERMISSIONS)
            } else { // Granted
                dispatchTakePictureIntent()
            }
        }
    }

    private fun dispatchTakePictureIntent() {
        val values = ContentValues()
        values.put(MediaStore.Images.Media.TITLE, "Food picture")
        values.put(MediaStore.Images.Media.DESCRIPTION, "Photo from FoodMe")
        image_uri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
        Log.e(TAG, "this is the uri of image")
        Log.e(TAG,image_uri.toString())

        //camera intent
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, image_uri)
        startActivityForResult(cameraIntent, IMAGE_CAPTURE_CODE)
    }

    // This is needed for a user who grants the permissions at first time
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        //called when user presses ALLOW or DENY from Permission Request Popup
        when(requestCode){
            REQUEST_CAMERA_PERMISSIONS -> {
                if (grantResults.size > 0 && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED){ // permission granted
                    Toast.makeText(this, "Permission granted. Please click the button again.", Toast.LENGTH_SHORT).show()
                }
                else{ // permission denied
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        //called when image was captured from camera intent
        if (resultCode == Activity.RESULT_OK){
            //set image captured to image view
            image_view.setImageURI(image_uri)
            Toast.makeText(this, "This image is saved in your local gallery ! Congrats.", Toast.LENGTH_SHORT).show()

        }
    }
}
