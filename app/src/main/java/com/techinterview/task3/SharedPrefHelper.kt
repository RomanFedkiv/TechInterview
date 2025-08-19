package com.techinterview.task3

import android.content.Context
import android.content.SharedPreferences

private const val USER_PREFS_KEY = "user_prefs"
private const val USER_TOKEN_KEY = "user_token"

class SharedPrefHelper(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences(USER_PREFS_KEY, Context.MODE_PRIVATE)

    fun saveUserToken(token: String) {
        prefs.edit().putString(USER_TOKEN_KEY, token).apply()
    }

    fun getUserToken(): String? {
        return prefs.getString(USER_TOKEN_KEY, null)
    }
}
