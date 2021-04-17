package com.studio.neopanda.docteurgarage

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager


class Tools {
    // --------------------
    // KEYBOARD HIDE
    // --------------------
    fun hideKeyboard(activity: Activity) {
        val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view = activity.currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun getCleanSecond(secondInput: Int): String {
        return if (secondInput > 9) {
            secondInput.toString()
        } else {
            "0$secondInput"
        }
    }

    fun getCleanMinute(minuteInput: Int): String {
        return if (minuteInput > 9) {
            minuteInput.toString()
        } else {
            "0$minuteInput"
        }
    }

    fun getCleanHour(hourInput: Int): String {
        return if (hourInput > 9) {
            hourInput.toString()
        } else {
            "0$hourInput"
        }
    }

    fun getCleanDay(dayInput: Int): String {
        return if (dayInput > 9) {
            dayInput.toString()
        } else {
            "0$dayInput"
        }
    }

    fun getCleanMonth(monthInput: Int): String {
        val monthCleaned = monthInput + 1
        val monthCleanedStringed = monthCleaned.toString()

        return if (monthCleanedStringed == "10" || monthCleanedStringed == "11"
            || monthCleanedStringed == "12"
        ) {
            monthCleanedStringed
        } else {
            "0$monthCleanedStringed"
        }
    }
}