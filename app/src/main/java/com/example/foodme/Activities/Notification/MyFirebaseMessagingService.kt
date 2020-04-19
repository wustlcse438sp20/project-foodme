package com.example.foodme.Activities.Notification

import android.app.PendingIntent
import android.content.ContentValues.TAG
import android.content.Intent
import android.util.Log
import com.example.foodme.Activities.MainActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


// All based from the given insturction
class MyFirebaseMessagingService : FirebaseMessagingService() {
    val TAG = String::class.java.simpleName

    override fun onMessageReceived(remoteMessage: RemoteMessage?) { // ...
// TODO(developer): Handle FCM messages here.
// Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "From: " + remoteMessage!!.from)
        // Check if message contains a data payload.
        if (remoteMessage!!.data.size > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.data)
        }
        // Check if message contains a notification payload.
        if (remoteMessage!!.notification != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.notification!!.body)
        }
        // Also if you intend on generating your own notifications as a result of a received FCM
// message, here is where that should be initiated. See sendNotification method below.
        remoteMessage?.notification?.let{
            sendNotification(remoteMessage.notification!!.body)
        }

    }

    private fun sendNotification(messageBody: String?){
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pending_Intent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT)
//        val channelID

    }




}