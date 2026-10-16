package com.productivity.testwind2.Imports

import com.productivity.testwind2.Imports.Utils.Log.*
import com.productivity.testwind2.Imports.Utils.Renames.*
import com.productivity.testwind2.Imports.Utils.AppsAndDevice.*
import android.app.*
import androidx.core.app.*
import android.os.*
import android.content.*
import kotlinx.coroutines.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import android.content.Intent
import com.productivity.testwind2.Imports.Utils.*
import com.productivity.testwind2.Imports.UI_visible.*

//region LATER USE

fun Context.start(service: Class<out Service>) {
    val intent = Intent(this, service)

	Permission.ignoreOptimizations()

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        startForegroundService(intent)
    } else {
        startService(intent)
    }
}

fun Context.stop(service: Class<out Service>) {
    stopService(Intent(this, service))
}


/*
class AppBackground : Service() {
	
	val serviceScope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    var job: Job? = null
	var timeRan by m(1)

	
	override fun onCreate() {
		super.onCreate()

		try {
   		   val notif = Notifi("Background Tasks: [testing]", "running...", 1)

           Vlog("service create")
           notif.startForeground(this)
		} catch (e: Exception) {
            LogCrash(e)
		}
	}

	
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {  
		try {
           if (job == null || job?.isActive == no) {
               job = serviceScope.launch {
				   val notif = Notifi("Background Tasks: [testing]", "running...", 1)

				   var lastUsed = LastUsedApp()

					log("lastUsed: $lastUsed")
				   
				   AppBackground(notif)
               }
           }
		} catch (e: Exception) {
            LogCrash(e)
		}

        return START_STICKY
    }

    override fun onDestroy() { 
		serviceScope.cancel() 
		super.onDestroy()
	}
	override fun onBind(intent: Intent?) = null
}
*/


class AppBackground : Service() {

    companion object {
        private const val CHANNEL_ID = "background_tasks"
        private const val NOTIFICATION_ID = 1
    }

    private val serviceScope = CoroutineScope(Dispatchers.Main + SupervisorJob())
    private var job: Job? = null
	
    private lateinit var notificationBuilder: NotificationCompat.Builder

    override fun onCreate() {
        super.onCreate()

		Permission.notification()

        createNotificationChannel()

        notificationBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(myAppRes) // REQUIRED
            .setContentTitle("Background Tasks")
            .setContentText("Running...")
            .setOngoing(true)
            .setPriority(NotificationCompat.PRIORITY_LOW)

        startForeground(NOTIFICATION_ID, notificationBuilder.build())
    }

    private fun updateNotification(text: String) {
        val updated = notificationBuilder
            .setContentText(text)
            .setOnlyAlertOnce(true)
            .build()

        NotificationManagerCompat.from(this)
            .notify(NOTIFICATION_ID, updated)
    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY
    }

    override fun onDestroy() {
        job?.cancel()
        serviceScope.cancel()

        super.onDestroy()
    }

    override fun onBind(intent: Intent?) = null

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Background Tasks",
                NotificationManager.IMPORTANCE_LOW
            ).apply {
                description = "Background service"
            }

            getSystemService(NotificationManager::class.java)
                .createNotificationChannel(channel)
        }
    }
}



class BootReceiver : BroadcastReceiver() {
	override fun onReceive(context: Context, intent: Intent) {
	}
}






/*
class WatchdogService : Service() {
    private val serviceJob = SupervisorJob()
    private val serviceScope = CoroutineScope(Dispatchers.Main + serviceJob)
    private var OneJob: Job? = null

    override fun onBind(intent: Intent?): IBinder? {
        Timber.d("onBind: $intent")
        return null
    }

    fun GOtowindAPP(){
        val intent = Intent(Global1.context, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        Global1.context.startActivity(intent)
    }

    override fun onStartCommand(
        intent: Intent?,
        flags: Int,
        startId: Int,
    ): Int {
        NotificationHelper(this).createNotificationChannel()
        startForeground(1, NotificationHelper(this).buildNotification(),)
        Global1.context = this
        if (Bar.BlockingEnabled) {
            if (OneJob == null || OneJob?.isActive == false) {
                OneJob = serviceScope.launch {
                    while (true) {

                        //region SAFETY PURPOSES

                        delay(1000L)
                        Bar.COUNT +=1

                        //endregion


                        //region CURRENT APP

                        val usageStatsManager = Global1.context.getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager
                        val NowTime = System.currentTimeMillis()

                        /*
                        * THIS IS NOT SUPER ACCURATE
                        ? If you want better precision, you’ll need an Accessibility Service.
                        !THIS REQUIRES NAVIGATING USER TO IT*/
                        val AppsUsed = usageStatsManager.queryUsageStats(UsageStatsManager.INTERVAL_DAILY, NowTime - 20_000, NowTime)
                        val currentApp = AppsUsed?.maxByOrNull { it.lastTimeUsed }?.packageName

                        //LOG WHEN WANT TOOO
                        if (currentApp == Global1.context.packageName || currentApp == null) { } else { log("BACKGROUND — CURRENT APP: $currentApp", "bad") }

                        /*

                        val blocked = apps.any { it.packageName == currentApp && it.Block }

                        if (blocked) {
                            if (Bar.funTime > 0) {
                                Bar.funTime -= 1
                                log("BACKGROUND---Spending Time??:::${Bar.funTime};", "bad")
                            } else {
                                GOtowindAPP()
                                log("BACKGROUND---Blocking APP:::${currentApp}; ${Bar.COUNT}", "bad")
                            }
                        }


                    if (currentApp == "com.seekrtech.waterapp") {

					}*/   }}
            }
        }

        return START_STICKY
    }

    override fun onDestroy() { super.onDestroy(); serviceScope.cancel() }
}

*/







