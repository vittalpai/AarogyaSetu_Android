package com.healthtrack.utility.authsp

import android.content.Context
import com.healthtrack.CoronaApplication
import com.healthtrack.models.Converters
import com.healthtrack.utility.DecryptionUtil
import com.healthtrack.utility.EncryptionUtil

/**
 * @author Aman kapoor
 */
class AuthSpHelperPreM : AuthSpHelper {

    companion object {
        private const val SHARED_PREFERENCE_NAME = "auth_pref_lollipop"
    }

    private val encryptionUtil by lazy {
        EncryptionUtil.getInstance()
    }

    private val decryptionUtil by lazy {
        DecryptionUtil()
    }

    private val sharedPreferences by lazy {
        CoronaApplication.getInstance().context.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)
    }

    override fun getString(key: String, defaultValue: String?) : String? {
        return try {
            val encryptedValue = sharedPreferences.getString(key, defaultValue)
            if (encryptedValue.isNullOrBlank()) {
                defaultValue
            } else {
                val encryptedInfo = Converters.fromString(encryptedValue)
                decryptionUtil.decryptData(encryptedInfo)
            }
        } catch (e : Exception) {
            defaultValue
        }
    }

    override fun putString(key: String, value: String?) {
        try {
            val encryptedInfo = encryptionUtil.encrypt(value)
            val encryptedValue = Converters.fromArrayList(encryptedInfo)
            sharedPreferences.edit().putString(key, encryptedValue).apply()
        } catch (e : Exception) {
            //do nothing
        }
    }

    override fun removeKey(key: String) {
        sharedPreferences.edit().remove(key).apply()
    }
}