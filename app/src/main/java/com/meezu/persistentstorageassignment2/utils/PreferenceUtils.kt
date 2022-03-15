package com.meezu.persistentstorageassignment2.utils

import android.content.Context
import android.content.SharedPreferences

object PreferenceUtils {

    private fun sharedPref(context: Context?): SharedPreferences? {
        return context?.getSharedPreferences(PreferencesConstant.preferences, Context.MODE_PRIVATE)
    }

    fun saveSharedPref(context: Context?, username: String, password: String) {
        val editor = sharedPref(context)?.edit()
        editor?.putString(PreferencesConstant.username, username)
        editor?.putString(PreferencesConstant.password, password)
        editor?.apply()
    }

    fun getUsername(context: Context?): String? {
        return sharedPref(context)?.getString(PreferencesConstant.username, null)
    }

    fun getPassword(context: Context?): String? {
        return sharedPref(context)?.getString(PreferencesConstant.password, null)
    }

    fun clearSharedPref(context: Context?){
        val editor = sharedPref(context)?.edit()
        editor?.clear()
        editor?.apply()
    }
}