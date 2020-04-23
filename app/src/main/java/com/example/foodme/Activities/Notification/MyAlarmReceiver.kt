import android.app.AlarmManager
import android.app.PendingIntent
import android.widget.Toast
import android.content.Intent
import android.content.BroadcastReceiver
import android.content.Context
import java.util.*


//https://developer.android.com/training/scheduling/alarms
class MyAlarmReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        Toast.makeText(context, "Alarm Triggered", Toast.LENGTH_LONG).show()


// With setInexactRepeating(), you have to use one of the AlarmManager interval
// constants--in this case, AlarmManager.INTERVAL_DAY.


        }
    }
