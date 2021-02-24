package com.studio.neopanda.docteurgarage


import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_cars.*
import android.R.id
import android.R.id.edit
import android.content.Context
import android.content.SharedPreferences
import android.content.Context.MODE_PRIVATE



class CarsFragment : Fragment() {

    private val REQUEST_IMAGE_CAPTURE = 1
    private lateinit var carOne: Car

    private var carBrand = ""
    private var carModel = ""
    private var carYear = ""
    private var carKilometers = 0
    private var carLastTechnicalControl = ""
    private var carLastEmptying = ""

    private var addSelector = 0
    private var addCarContent = ""
    private var addCarTitle = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_cars, container, false)

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)

        initCars()

        add_car_btn.setOnClickListener {
            addCar()
        }

//        dispatchTakePictureIntent()
    }

    private fun initCars() {
        val car = activity!!.getSharedPreferences("my_car", MODE_PRIVATE)
        val carOneExistBrand = car.getBoolean("car_one_exists", false)
        if (carOneExistBrand){
            loadCarOne(car)
            car_one_tv.text = carOne.brand + "" + carOne.model
            car_one_tv.visibility = View.VISIBLE
        }
    }

    private fun loadCarOne(car: SharedPreferences) {
        carOne = Car("", "", "", 0, "", "")
        carOne.brand = car.getString("car_brand", "None").toString()
        carOne.model = car.getString("car_model", "None").toString()
        carOne.year = car.getString("car_year", "None").toString()
        carOne.kilometers = car.getInt("car_kilometers", 0).toString().toInt()
        carOne.lastTC = car.getString("car_last_tc", "None").toString()
        carOne.lastEmptying = car.getString("car_last_emptying", "None").toString()
    }

    private fun addCar() {
        addSelector = 0
        add_car_btn.visibility = View.GONE
        add_car_title_tv.visibility = View.VISIBLE
        add_car_content_et.visibility = View.VISIBLE
        add_car_controls.visibility = View.VISIBLE

        navigationControls()
    }

    private fun navigationControls() {
        add_control_cancel.setOnClickListener {
            add_car_btn.visibility = View.VISIBLE
            add_car_title_tv.visibility = View.GONE
            add_car_content_et.visibility = View.GONE
            add_car_controls.visibility = View.GONE
        }

        add_control_next.setOnClickListener {
            nextControl()
        }

        add_control_previous.setOnClickListener {
            previousControl()
        }
    }

    private fun previousControl() {
        when (addSelector) {
            0 -> {
                Toast.makeText(this.activity, "Aucune étape précédente", Toast.LENGTH_LONG).show()
            }
            1 -> {
                addSelector -= 1
                addCarContent = "Rentrer la marque"
                addCarTitle = "Marque du véhicule :"
                add_car_title_tv.text = addCarTitle
                add_car_content_et.hint = addCarContent
            }
            2 -> {
                addSelector -= 1
                addCarContent = "Rentrer le modèle"
                addCarTitle = "Modèle du véhicule :"
                add_car_title_tv.text = addCarTitle
                add_car_content_et.hint = addCarContent
            }
            3 -> {
                addSelector -= 1
                addCarContent = "Rentrer l'année"
                addCarTitle = "Année du véhicule :"
                add_car_title_tv.text = addCarTitle
                add_car_content_et.hint = addCarContent
            }
            4 -> {
                addSelector -= 1
                addCarContent = "Rentrer le kilométrage"
                addCarTitle = "Kilométrage :"
                add_car_title_tv.text = addCarTitle
                add_car_content_et.hint = addCarContent
            }
            5 -> {
                addSelector -= 1
                addCarContent = "Rentrer le dernier CT"
                addCarTitle = "Dernier CT :"
                add_car_title_tv.text = addCarTitle
                add_car_content_et.hint = addCarContent
            }
        }
    }

    private fun nextControl() {
        when (addSelector) {
            0 -> {
                addSelector += 1
                addCarContent = "Rentrer le modèle"
                addCarTitle = "Modèle du véhicule :"
                add_car_title_tv.text = addCarTitle
                add_car_content_et.hint = addCarContent
                carBrand = add_car_content_et.text.toString()
            }
            1 -> {
                addSelector += 1
                addCarContent = "Rentrer l'année"
                addCarTitle = "Année du véhicule :"
                add_car_title_tv.text = addCarTitle
                add_car_content_et.hint = addCarContent
                carModel = add_car_content_et.text.toString()
            }
            2 -> {
                addSelector += 1
                addCarContent = "Rentrer le kilométrage"
                addCarTitle = "Kilométrage :"
                add_car_title_tv.text = addCarTitle
                add_car_content_et.hint = addCarContent
                carYear = add_car_content_et.text.toString()
            }
            3 -> {
                addSelector += 1
                addCarContent = "Rentrer le dernier CT"
                addCarTitle = "Dernier CT :"
                add_car_title_tv.text = addCarTitle
                add_car_content_et.hint = addCarContent
                carKilometers = add_car_content_et.text.toString().toInt()
            }
            4 -> {
                addSelector += 1
                addCarContent = "Dernière vidange"
                addCarTitle = "Dernière vidange :"
                add_car_title_tv.text = addCarTitle
                add_car_content_et.hint = addCarContent
                carLastTechnicalControl = add_car_content_et.text.toString()
            }
            5 -> {
                addSelector = 0
                carLastEmptying = add_car_content_et.text.toString()
                add_car_btn.visibility = View.VISIBLE
                add_car_title_tv.visibility = View.GONE
                add_car_content_et.visibility = View.GONE
                add_car_controls.visibility = View.GONE

                createCar()
            }
        }
    }

    private fun createCar() {
        val car = activity!!.getSharedPreferences("my_car", MODE_PRIVATE)
        val edt = car.edit()
        edt.putBoolean("car_one_exists", true)
        edt.putString("car_brand", carBrand)
        edt.putString("car_model", carModel)
        edt.putString("car_year", carYear)
        edt.putInt("car_kilometers", carKilometers)
        edt.putString("car_last_tc", carLastTechnicalControl)
        edt.putString("car_last_emptying", carLastEmptying)
        edt.apply()
    }

    private fun dispatchTakePictureIntent() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        try {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        } catch (e: ActivityNotFoundException) {
            // display error state to the user
        }
    }
}
