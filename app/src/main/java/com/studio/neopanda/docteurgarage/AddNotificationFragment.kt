package com.studio.neopanda.docteurgarage


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_add_notification.*


class AddNotificationFragment : Fragment() {
//    val NOTIFICATION_CHANNEL_ID = "10001"
//    private val default_notification_channel_id = "default"

    var yearNotification: Int = 0
    var monthNotification: Int = 0
    var dayNotification: Int = 0

    var notificationName: String = ""
    var notificationNote: String = ""
    var notificationDate: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_notification, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        validateNotificationInput()
    }

    private fun validateNotificationInput() {
        add_notification_time_btn.setOnClickListener {
            add_notification_date_dp.visibility = View.VISIBLE
            add_notification_name_et.visibility = View.GONE
            add_notification_note_et.visibility = View.GONE
            add_notification_time_btn.visibility = View.GONE
            add_notification_validate_btn.visibility = View.GONE
        }

        getDateInformation()

        add_notification_validate_btn.setOnClickListener {
            notificationName = add_notification_name_et.editableText.toString()
            notificationNote = add_notification_note_et.editableText.toString()

            if (notificationName != "" && notificationNote != "" && notificationDate != "") {
//                createNotification(notificationName, notificationNote, notificationDate)
            } else {
                Toast.makeText(
                    this.activity,
                    "Un ou plusieurs paramètres sont manquants !",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }


//    private fun createNotification(
//        notificationName: String,
//        notificationNote: String,
//        notificationDate: String
//    ) {
//        var builder = NotificationCompat.Builder(this.activity!!.applicationContext, CHANNEL_ID)
//            .setSmallIcon(R.drawable.ic_car_manager_blue_24dp)
//            .setContentTitle(notificationName)
//            .setContentText(notificationNote)
//            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
//
//
//        Toast.makeText(this.activity, "La notification a bien été créée !", Toast.LENGTH_LONG)
//            .show()
//    }


    private fun getDateInformation() {
        add_notification_date_dp.setOnDateChangedListener { view, year, monthOfYear, dayOfMonth ->
            yearNotification = year
            monthNotification = monthOfYear
            dayNotification = dayOfMonth

            add_notification_date_dp.visibility = View.GONE
            add_notification_name_et.visibility = View.VISIBLE
            add_notification_note_et.visibility = View.VISIBLE
            add_notification_validate_btn.visibility = View.VISIBLE

            add_notification_date_chosen_tv.text =
                "$yearNotification/$monthNotification/$dayNotification"
            add_notification_date_chosen_tv.visibility = View.VISIBLE
            notificationDate = "$yearNotification-$monthNotification-$dayNotification"
        }
    }

}
