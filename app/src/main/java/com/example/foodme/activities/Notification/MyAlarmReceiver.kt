//import android.R
//import android.app.Notification
//import android.app.NotificationManager
//import android.content.BroadcastReceiver
//import android.content.Context
//import android.content.Intent
//import android.widget.Toast
//import androidx.core.app.NotificationCompat
//
//
////https://developer.android.com/training/scheduling/alarms
//class MyAlarmReceiver: BroadcastReceiver() {
//
//    override fun onReceive(context: Context, intent: Intent) {
//        override fun onReceive(
//            context: Context,
//            intent: Intent
//        ) { // Build notification based on Intent
//            val notification: Notification = NotificationCompat.Builder(context)
//                .setSmallIcon(R.drawable.ic_notification_small_icon)
//                .setContentTitle(intent.getStringExtra("title", ""))
//                .setContentText(intent.getStringExtra("text", ""))
//            .build()
//            // Show notification
//            val manager =
//                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//            manager.notify(42, notification)
//        }
//
//        Toast.makeText(context, "Alarm Triggered", Toast.LENGTH_LONG).show()
//
//
//
//// With setInexactRepeating(), you have to use one of the AlarmManager interval
//// constants--in this case, AlarmManager.INTERVAL_DAY.
//
//
//        }
//
//    }
//class NotificationReceiver : BroadcastReceiver() {
//}
