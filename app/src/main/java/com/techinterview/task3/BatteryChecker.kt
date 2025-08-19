package com.techinterview.task3

import android.content.Context
import android.os.BatteryManager

class BatteryChecker(context: Context) {

    private val batteryManager = context.getSystemService(Context.BATTERY_SERVICE) as BatteryManager

    fun isBatteryLow(): Boolean {
        val isLow = batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY) < 20
        return isLow
    }
}

