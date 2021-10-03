package com.etisalat.merchant.paginglibraryv3.data.source

import android.content.Context
import android.content.SharedPreferences
import com.etisalat.merchant.paginglibraryv3.MainPaging

/**
 * Created by Amir.jehangir on 27,September,2021
 */
inline fun <reified T> SharedPreferences.put(key: String, value: T) {
    val editor = this.edit()

    when (T::class) {
        String::class -> editor.putString(key, value as String)
        Boolean::class -> editor.putBoolean(key, value as Boolean)
        Int::class -> editor.putInt(key, value as Int)
        Float::class -> editor.putFloat(key, value as Float)
        Long::class -> editor.putLong(key, value as Long)
        else -> "Not able to perform operation"
    }
    editor.apply()
}

inline fun <reified T> SharedPreferences.get(key: String, defaultValue: T): T {
    when (T::class) {
        String::class -> return this.getString(key, defaultValue as String) as T
        Boolean::class -> return this.getBoolean(key, defaultValue as Boolean) as T
        Int::class -> return this.getInt(key, defaultValue as Int) as T
        Float::class -> return this.getFloat(key, defaultValue as Float) as T
        Long::class -> return this.getLong(key, defaultValue as Long) as T
    }
    return defaultValue
}

class SharedPrefDataSource(val preferences: SharedPreferences) {
    val context: Context = MainPaging.applicationContext()

    var dummyData: Boolean
        get() = preferences.get("dummy", false)
        set(value) = preferences.put("dummy", value)

    var currLang: String
        get() = preferences.get("APP_LANGUAGE", "")
        set(value) = preferences.put("APP_LANGUAGE", value)

    var onPauseTime: Long
        get() = preferences.get("onPauseTime", 0L)
        set(value) = preferences.put("onPauseTime", value)

    var accountNumberReceivable: String
        get() = preferences.get("accountReceivable", "")
        set(value) = preferences.put("accountReceivable", value)
    var accountNumberPayable: String
        get() = preferences.get("accountPayable", "")
        set(value) = preferences.put("accountPayable", value)
    var accountAmountBalanceInquire: String
        get() = preferences.get("accountAmount", "0")
        set(value) = preferences.put("accountAmount", value)
}