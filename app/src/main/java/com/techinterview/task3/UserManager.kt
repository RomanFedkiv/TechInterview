package com.techinterview.task3

import android.content.Context

class UserManager(context: Context) {

    private val sharedPrefsHelper = SharedPrefHelper(context)

    fun login(userToken: String) {
        sharedPrefsHelper.saveUserToken(userToken)
        println("User logged in. Token saved.")
    }

    fun isLoggedIn(): Boolean {
        return !sharedPrefsHelper.getUserToken().isNullOrEmpty()
    }
}
