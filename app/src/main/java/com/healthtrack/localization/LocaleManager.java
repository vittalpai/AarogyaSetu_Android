package com.healthtrack.localization;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;

import com.healthtrack.prefs.SharedPref;
import com.healthtrack.prefs.SharedPrefsConstants;

import java.util.Locale;

import static com.healthtrack.prefs.SharedPrefsConstants.DEFAULT_LANGUAGE_CODE;

/**
 * Created by Kshitij Khatri on 23/03/20.
 * Updated by Niharika.Arora
 */
public class LocaleManager {

    public static void setNewLocale(Context c, String language) {
        updateResources(c, language);
    }

    public static String getLanguage(Context c) {
        return SharedPref.getStringParams(c, SharedPrefsConstants.USER_SELECTED_LANGUAGE_CODE, DEFAULT_LANGUAGE_CODE);
    }

    private static void updateResources(Context context, String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        if (context != null) {
            Resources res = context.getResources();
            Configuration config = new Configuration(res.getConfiguration());
            config.locale = locale;
            res.updateConfiguration(config, res.getDisplayMetrics());
        }
    }
}
