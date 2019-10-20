package com.jumia.hellokity.service

import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class NotificationService : FirebaseMessagingService(){
    val Tag = "NOTIFICATION"

    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        super.onMessageReceived(remoteMessage)
        Log.d(TAG, "From: " + remoteMessage?.getFrom());

        if (remoteMessage?.getData()?.size!!> 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData())
        }


        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.notification)
        }

    }

    override fun onNewToken(p0: String?) {
        super.onNewToken(p0)
        Log.d(TAG,"Refresh token"+p0)
    }
}