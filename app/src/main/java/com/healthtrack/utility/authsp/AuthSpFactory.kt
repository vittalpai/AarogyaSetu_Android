package com.healthtrack.utility.authsp

import android.os.Build

object AuthSpFactory {

    @JvmStatic
    val instance : AuthSpHelper by lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            AuthSpHelperPostM()
        } else {
            AuthSpHelperPreM()
        }
    }

}