package com.studio.neopanda.docteurgarage


import android.content.ActivityNotFoundException
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_cars.*


class CarsFragment : Fragment() {

    private val REQUEST_IMAGE_CAPTURE = 1
    private lateinit var carOne: Car
    private lateinit var carTwo: Car
    private lateinit var carThree: Car

    private var carOneExists = false
    private var carTwoExists = false
    private var carThreeExists = false

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
            if (!carOneExists || !carTwoExists || !carThreeExists) {
                addCar()
            } else {
                Toast.makeText(this.activity, "Le maximum est de 3 voitures.", Toast.LENGTH_LONG)
                    .show()
            }
        }

//        dispatchTakePictureIntent()
    }

    private fun initCars() {
        initCarOne()
        initCarTwo()
        initCarThree()
    }

    private fun initCarThree() {
        val car = activity!!.getSharedPreferences("car_three", MODE_PRIVATE)
        val carThreeExist = car.getBoolean("car_three_exists", false)
        if (carThreeExist) {
            loadCarThree(car)
            car_three_tv.text = carThree.brand + " " + carThree.model
            car_three_tv.visibility = View.VISIBLE
            delete_car_three_btn.visibility = View.VISIBLE
            delete_car_three_btn.setOnClickListener {
                val preferences = activity!!.getSharedPreferences("car_three", MODE_PRIVATE)
                preferences.edit().clear().apply()
                car_three_tv.text = ""
                car_three_tv.visibility = View.GONE
                delete_car_three_btn.visibility = View.GONE
            }
        } else {
            car_three_tv.visibility = View.GONE
            delete_car_three_btn.visibility = View.GONE
        }
    }

    private fun initCarTwo() {
        val car = activity!!.getSharedPreferences("car_two", MODE_PRIVATE)
        val carTwoExist = car.getBoolean("car_two_exists", false)
        if (carTwoExist) {
            loadCarTwo(car)
            car_two_tv.text = carTwo.brand + " " + carTwo.model
            car_two_tv.visibility = View.VISIBLE
            delete_car_two_btn.visibility = View.VISIBLE
            delete_car_two_btn.setOnClickListener {
                val preferences = activity!!.getSharedPreferences("car_two", MODE_PRIVATE)
                preferences.edit().clear().apply()
                car_two_tv.text = ""
                car_two_tv.visibility = View.GONE
                delete_car_two_btn.visibility = View.GONE
            }
        } else {
            car_three_tv.visibility = View.GONE
            delete_car_three_btn.visibility = View.GONE
        }
    }

    private fun initCarOne() {
        val car = activity!!.getSharedPreferences("car_one", MODE_PRIVATE)
        val carOneExist = car.getBoolean("car_one_exists", false)
        if (carOneExist) {
            loadCarOne(car)
            car_one_tv.text = carOne.brand + " " + carOne.model
            car_one_tv.visibility = View.VISIBLE
            delete_car_one_btn.visibility = View.VISIBLE
            delete_car_one_btn.setOnClickListener {
                val preferences = activity!!.getSharedPreferences("car_one", MODE_PRIVATE)
                preferences.edit().clear().apply()
                car_one_tv.text = ""
                car_one_tv.visibility = View.GONE
                delete_car_one_btn.visibility = View.GONE
            }
        } else {
            car_one_tv.visibility = View.GONE
            delete_car_one_btn.visibility = View.GONE
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

    private fun loadCarTwo(car: SharedPreferences) {
        carTwo = Car("", "", "", 0, "", "")
        carTwo.brand = car.getString("car_brand", "None").toString()
        carTwo.model = car.getString("car_model", "None").toString()
        carTwo.year = car.getString("car_year", "None").toString()
        carTwo.kilometers = car.getInt("car_kilometers", 0).toString().toInt()
        carTwo.lastTC = car.getString("car_last_tc", "None").toString()
        carTwo.lastEmptying = car.getString("car_last_emptying", "None").toString()
    }

    private fun loadCarThree(car: SharedPreferences) {
        carThree = Car("", "", "", 0, "", "")
        carThree.brand = car.getString("car_brand", "None").toString()
        carThree.model = car.getString("car_model", "None").toString()
        carThree.year = car.getString("car_year", "None").toString()
        carThree.kilometers = car.getInt("car_kilometers", 0).toString().toInt()
        carThree.lastTC = car.getString("car_last_tc", "None").toString()
        carThree.lastEmptying = car.getString("car_last_emptying", "None").toString()
    }

    private fun addCar() {
        addSelector = 0
        add_car_btn.visibility = View.GONE
        car_one_tv.visibility = View.GONE
        car_two_tv.visibility = View.GONE
        car_three_tv.visibility = View.GONE
        delete_car_one_btn.visibility = View.GONE
        delete_car_two_btn.visibility = View.GONE
        delete_car_three_btn.visibility = View.GONE
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
                add_car_content_et.text.clear()
            }
            2 -> {
                addSelector -= 1
                addCarContent = "Rentrer le modèle"
                addCarTitle = "Modèle du véhicule :"
                add_car_title_tv.text = addCarTitle
                add_car_content_et.hint = addCarContent
                add_car_content_et.text.clear()
            }
            3 -> {
                addSelector -= 1
                addCarContent = "Rentrer l'année"
                addCarTitle = "Année du véhicule :"
                add_car_title_tv.text = addCarTitle
                add_car_content_et.hint = addCarContent
                add_car_content_et.text.clear()
            }
            4 -> {
                addSelector -= 1
                addCarContent = "Rentrer le kilométrage"
                addCarTitle = "Kilométrage :"
                add_car_title_tv.text = addCarTitle
                add_car_content_et.hint = addCarContent
                add_car_content_et.text.clear()
            }
            5 -> {
                addSelector -= 1
                addCarContent = "Rentrer le dernier CT"
                addCarTitle = "Dernier CT :"
                add_car_title_tv.text = addCarTitle
                add_car_content_et.hint = addCarContent
                add_car_content_et.text.clear()
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
                add_car_content_et.text.clear()
            }
            1 -> {
                addSelector += 1
                addCarContent = "Rentrer l'année"
                addCarTitle = "Année du véhicule :"
                add_car_title_tv.text = addCarTitle
                add_car_content_et.hint = addCarContent
                carModel = add_car_content_et.text.toString()
                add_car_content_et.text.clear()
            }
            2 -> {
                addSelector += 1
                addCarContent = "Rentrer le kilométrage"
                addCarTitle = "Kilométrage :"
                add_car_title_tv.text = addCarTitle
                add_car_content_et.hint = addCarContent
                carYear = add_car_content_et.text.toString()
                add_car_content_et.text.clear()
            }
            3 -> {
                addSelector += 1
                addCarContent = "Rentrer le dernier CT"
                addCarTitle = "Dernier CT :"
                add_car_title_tv.text = addCarTitle
                add_car_content_et.hint = addCarContent
                carKilometers = add_car_content_et.text.toString().toInt()
                add_car_content_et.text.clear()
            }
            4 -> {
                addSelector += 1
                addCarContent = "Dernière vidange"
                addCarTitle = "Dernière vidange :"
                add_car_title_tv.text = addCarTitle
                add_car_content_et.hint = addCarContent
                carLastTechnicalControl = add_car_content_et.text.toString()
                add_car_content_et.text.clear()
            }
            5 -> {
                addSelector = 0
                carLastEmptying = add_car_content_et.text.toString()
                add_car_btn.visibility = View.VISIBLE
                car_one_tv.visibility = View.VISIBLE
                car_two_tv.visibility = View.VISIBLE
                car_three_tv.visibility = View.VISIBLE
                delete_car_one_btn.visibility = View.VISIBLE
                delete_car_two_btn.visibility = View.VISIBLE
                delete_car_three_btn.visibility = View.VISIBLE
                add_car_title_tv.visibility = View.GONE
                add_car_content_et.visibility = View.GONE
                add_car_controls.visibility = View.GONE

                createCar()
            }
        }
    }

    private fun createCar() {
        if (!carOneExists && !carTwoExists && !carThreeExists) {
            val car = activity!!.getSharedPreferences("car_one", MODE_PRIVATE)
            val edt = car.edit()
            edt.putBoolean("car_one_exists", true)
            edt.putString("car_brand", carBrand)
            edt.putString("car_model", carModel)
            edt.putString("car_year", carYear)
            edt.putInt("car_kilometers", carKilometers)
            edt.putString("car_last_tc", carLastTechnicalControl)
            edt.putString("car_last_emptying", carLastEmptying)
            edt.apply()

            Toast.makeText(this.activity, "Voiture créée !", Toast.LENGTH_LONG).show()
        } else if (carOneExists && !carTwoExists && !carThreeExists) {
            val car = activity!!.getSharedPreferences("car_two", MODE_PRIVATE)
            val edt = car.edit()
            edt.putBoolean("car_two_exists", true)
            edt.putString("car_brand", carBrand)
            edt.putString("car_model", carModel)
            edt.putString("car_year", carYear)
            edt.putInt("car_kilometers", carKilometers)
            edt.putString("car_last_tc", carLastTechnicalControl)
            edt.putString("car_last_emptying", carLastEmptying)
            edt.apply()

            Toast.makeText(this.activity, "Voiture créée !", Toast.LENGTH_LONG).show()
        } else if (carOneExists && carTwoExists && !carThreeExists) {
            val car = activity!!.getSharedPreferences("car_three", MODE_PRIVATE)
            val edt = car.edit()
            edt.putBoolean("car_three_exists", true)
            edt.putString("car_brand", carBrand)
            edt.putString("car_model", carModel)
            edt.putString("car_year", carYear)
            edt.putInt("car_kilometers", carKilometers)
            edt.putString("car_last_tc", carLastTechnicalControl)
            edt.putString("car_last_emptying", carLastEmptying)
            edt.apply()

            Toast.makeText(this.activity, "Voiture créée !", Toast.LENGTH_LONG).show()
        }

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
