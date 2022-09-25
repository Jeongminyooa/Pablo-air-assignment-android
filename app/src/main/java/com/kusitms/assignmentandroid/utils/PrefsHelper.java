package com.kusitms.assignmentandroid.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefsHelper {

    private Context context;

    public static final String PREFERENCE_NAME="pref";
    private static SharedPreferences prefs;
    private static SharedPreferences.Editor prefsEditor;

    private static PrefsHelper instance;

    public static synchronized PrefsHelper init(Context context) {
        if(instance == null) {
            instance = new PrefsHelper(context);
        }
        return instance;
    }

    private PrefsHelper(Context context) {
        this.context = context;
        this.prefs = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        this.prefsEditor = prefs.edit();
    }

    public static String read(String key, String defValue) {
        return prefs.getString(key, defValue);
    }

    public static void write(String key, String value) {
        prefsEditor.putString(key, value).apply();
    }
}
