package com.ilyes.littlelemon

import android.content.Context


object UserSession {
    private const val PREF_NAME = "user_session"
    private const val IS_LOGGED_IN = "is_logged_in"
    private const val FIRST_NAME = "first_name"
    private const val LAST_NAME = "last_name"
    private const val EMAIL = "email"

    // Save login state
    fun setLoggedIn(context: Context, isLoggedIn: Boolean) {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        sharedPreferences.edit().putBoolean(IS_LOGGED_IN, isLoggedIn).apply()
    }

    // Get login state
    fun isLoggedIn(context: Context): Boolean {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean(IS_LOGGED_IN, false)
    }

    // Save user data (first name, last name, email)
    fun saveUserData(context: Context, firstName: String, lastName: String, email: String) {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        sharedPreferences.edit()
            .putString(FIRST_NAME, firstName)
            .putString(LAST_NAME, lastName)
            .putString(EMAIL, email)
            .apply()
    }

    // Get saved user data
    fun getUserData(context: Context): UserData {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val firstName = sharedPreferences.getString(FIRST_NAME, "") ?: ""
        val lastName = sharedPreferences.getString(LAST_NAME, "") ?: ""
        val email = sharedPreferences.getString(EMAIL, "") ?: ""
        return UserData(firstName, lastName, email)
    }
}

// A simple data class to store user data
data class UserData(val firstName: String, val lastName: String, val email: String)