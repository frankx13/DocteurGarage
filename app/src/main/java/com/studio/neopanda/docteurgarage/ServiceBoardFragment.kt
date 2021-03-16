package com.studio.neopanda.docteurgarage


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_service_board.*


class ServiceBoardFragment : Fragment() {
    private var countersOn = false
    private var levelsOn = false
    private var annotationsOn = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_service_board, container, false)

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)

        loadSelectedCar()
        setCounterListener()
        setLevelsListener()
        setAnnotationsListener()
    }

    private fun loadSelectedCar() {
        val selectedCar = activity!!.getSharedPreferences("car_selected", Context.MODE_PRIVATE)
        val selectedCarName = selectedCar.getString("car_selected_name", "Empty")
        car_name_tv.text = selectedCarName
    }

    private fun setAnnotationsListener() {
        car_annotations_tv.setOnTouchListener(OnTouchListener { v, event ->
            val DRAWABLE_LEFT = 0
            if (event.action == MotionEvent.ACTION_DOWN) {
                if (event.rawX >= car_counter_tv.left - car_counter_tv.compoundDrawables[DRAWABLE_LEFT].bounds.width()) {
                    annotationsOn = !annotationsOn
                    if (annotationsOn) {
                        car_annotations_layout.visibility = View.VISIBLE
                    } else {
                        car_annotations_layout.visibility = View.GONE
                    }
                    return@OnTouchListener true
                }
            }
            false
        })
    }

    private fun setLevelsListener() {
        car_levels_tv.setOnTouchListener(OnTouchListener { v, event ->
            val DRAWABLE_LEFT = 0
            if (event.action == MotionEvent.ACTION_DOWN) {
                if (event.rawX >= car_counter_tv.left - car_counter_tv.compoundDrawables[DRAWABLE_LEFT].bounds.width()) {
                    levelsOn = !levelsOn
                    if (levelsOn) {
                        car_levels_layout.visibility = View.VISIBLE
                    } else {
                        car_levels_layout.visibility = View.GONE
                    }
                    return@OnTouchListener true
                }
            }
            false
        })
    }

    private fun setCounterListener() {
        car_counter_tv.setOnTouchListener(OnTouchListener { v, event ->
            val DRAWABLE_LEFT = 0
            if (event.action == MotionEvent.ACTION_DOWN) {
                if (event.rawX >= car_counter_tv.left - car_counter_tv.compoundDrawables[DRAWABLE_LEFT].bounds.width()) {
                    countersOn = !countersOn
                    if (countersOn) {
                        car_counter_layout.visibility = View.VISIBLE
                    } else {
                        car_counter_layout.visibility = View.GONE
                    }
                    return@OnTouchListener true
                }
            }
            false
        })
    }
}
