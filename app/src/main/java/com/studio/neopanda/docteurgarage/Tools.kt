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

    // We compare two dates and return the difference of time between them expressed in seconds
    // Each year having a different number of days, we need to update this method every year
    fun compareDatesInSeconds(dateStart:String, dateEnd:String) : Int{
        var differenceInSeconds: Int
        var counter: Int

        val dateStartDays = dateStart.substring(0, 2).toInt()
        val dateStartMonths = dateStart.substring(2, 4).toInt()
        val dateStartYears = dateStart.substring(4, 7).toInt()
        val dateEndDays = dateEnd.substring(0, 2).toInt()
        val dateEndMonths = dateEnd.substring(2, 4).toInt()
        val dateEndYears = dateEnd.substring(4, 7).toInt()

        var daysDifference = dateEndDays - dateStartDays
        var monthsDifference = dateEndMonths - dateStartMonths
        var yearsDifference = dateEndYears - dateStartYears

        if (yearsDifference > 0){
            counter = 0
            while (counter < yearsDifference){
                yearsDifference -= 1
                monthsDifference += 12
                counter += 1
            }
        }

        if (monthsDifference % 2 != 0 && monthsDifference > 0){
            counter = 0
            while (counter < monthsDifference) {
                monthsDifference -= 1
                daysDifference += 31
                counter += 1

                //TODO : take in charge bissextil years
            }
        }

        // Case where the start date is after the end date or is the same date
        if ((yearsDifference < 0 || monthsDifference < 0 || daysDifference < 0)
            || yearsDifference == 0 && monthsDifference == 0 && daysDifference == 0) {
            differenceInSeconds = 0
        } else {
            differenceInSeconds = daysDifference * 86400
        }

        return differenceInSeconds
    }
    // Tests fields for compareDatesInSeconds()
//    Fin	10 12 2020
//    Debut	11 12 2020
//    Resultat	-1 0 0
//
//    Fin	10 10 2020
//    Debut	11 12 2020
//    Resultat	-1 -2 0
//
//    Fin	10 12 2020
//    Debut	11 12 2021
//    Resultat	-1 0 -1
//
//    Fin	29 12 2021
//    Debut	01 01 2001
//    Resultat	28 11 20

//    2021 = 365 jours
}