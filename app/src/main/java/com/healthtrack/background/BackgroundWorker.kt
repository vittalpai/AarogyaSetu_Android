package com.healthtrack.background

import android.bluetooth.BluetoothAdapter
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.healthtrack.utility.Constants
import com.healthtrack.utility.CorUtility

/**
 *  @author Damanpreet.Singh
 * @author Niharika.Arora
 */
class BackgroundWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {
    private val mContext: Context = context

    override fun doWork(): Result {
        val intent =
            Intent(mContext, com.healthtrack.background.BluetoothScanningService::class.java)
        intent.putExtra(Constants.FROM_MY_WORKER, true)

        startService(intent)

        if (CorUtility.isBluetoothAvailable()) {
            BluetoothAdapter.getDefaultAdapter().startDiscovery()
        }
        CorUtility.remove30DaysOldData()
        return Result.success()

    }

    private fun startService(intent: Intent) {
        val uniqueId = com.healthtrack.prefs.SharedPref.getStringParams(
            com.healthtrack.CoronaApplication.getInstance(),
            com.healthtrack.prefs.SharedPrefsConstants.UNIQUE_ID,
            Constants.EMPTY
        )
        if (!BluetoothScanningService.serviceRunning && uniqueId.isNotEmpty()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                mContext.startForegroundService(intent)
            } else {
                mContext.startService(intent)
            }
        }
    }

    companion object {
        val UNIQUE_WORK_NAME = BackgroundWorker::class.java.simpleName
    }
}