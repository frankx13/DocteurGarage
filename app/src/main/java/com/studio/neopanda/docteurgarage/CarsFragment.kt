package com.studio.neopanda.docteurgarage


import android.app.Activity
import android.app.ProgressDialog
import android.content.ActivityNotFoundException
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import kotlinx.android.synthetic.main.fragment_cars.*
import java.util.*


class CarsFragment : Fragment() {

    private val REQUEST_IMAGE_CAPTURE = 1
    private lateinit var carOne: Car
    private lateinit var carTwo: Car
    private lateinit var carThree: Car

    private var carOneIsCreated = false
    private var carTwoIsCreated = false
    private var carThreeIsCreated = false

    private var carBrand = ""
    private var carModel = ""
    private var carYear = ""
    private var carKilometers = 0
    private var carLastTechnicalControl = ""
    private var carLastEmptying = ""

    private var addSelector = 0
    private var addCarContent = ""
    private var addCarTitle = ""

    private var progressDialog: ProgressDialog? = null

    private lateinit var timestampCreation: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_cars, container, false)

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)

        lockUI()
        initCars()
        unlockUI()

        add_car_btn.setOnClickListener {
            if (!carOneIsCreated || !carTwoIsCreated || !carThreeIsCreated) {
                addCar()
            } else {
                Toast.makeText(this.activity, "Le maximum est de 3 voitures.", Toast.LENGTH_LONG)
                    .show()
            }
        }

        if (carOneIsCreated) {
            car_one_tv.setOnClickListener {
                car_one_tv.setTextColor(resources.getColor(R.color.colorItemFocus))
                car_two_tv.setTextColor(resources.getColor(R.color.colorSecondaryDark))
                car_three_tv.setTextColor(resources.getColor(R.color.colorSecondaryDark))

                val carSelected = activity!!.getSharedPreferences("car_selected", MODE_PRIVATE)
                val edt = carSelected.edit()
                edt.putBoolean("car_selected_exists", true)
                edt.putString("car_selected_name", carOne.brand + " " + carOne.model)
                edt.putString("car_selected_year", carOne.year)
                edt.putInt("car_selected_kilometers", carOne.kilometers)
                edt.putString("car_selected_last_TC", carOne.lastTC)
                edt.putString("car_selected_last_emptying", carOne.lastEmptying)
                edt.apply()

            }
        }
        if (carTwoIsCreated) {
            car_two_tv.setOnClickListener {
                car_one_tv.setTextColor(resources.getColor(R.color.colorSecondaryDark))
                car_two_tv.setTextColor(resources.getColor(R.color.colorItemFocus))
                car_three_tv.setTextColor(resources.getColor(R.color.colorSecondaryDark))

                val carSelected = activity!!.getSharedPreferences("car_selected", MODE_PRIVATE)
                val edt = carSelected.edit()
                edt.putBoolean("car_selected_exists", true)
                edt.putString("car_selected_name", carTwo.brand + " " + carTwo.model)
                edt.putString("car_selected_year", carTwo.year)
                edt.putInt("car_selected_kilometers", carTwo.kilometers)
                edt.putString("car_selected_last_TC", carTwo.lastTC)
                edt.putString("car_selected_last_emptying", carTwo.lastEmptying)
                edt.apply()
            }
        }
        if (carThreeIsCreated) {
            car_three_tv.setOnClickListener {
                car_one_tv.setTextColor(resources.getColor(R.color.colorSecondaryDark))
                car_two_tv.setTextColor(resources.getColor(R.color.colorSecondaryDark))
                car_three_tv.setTextColor(resources.getColor(R.color.colorItemFocus))

                val carSelected = activity!!.getSharedPreferences("car_selected", MODE_PRIVATE)
                val edt = carSelected.edit()
                edt.putBoolean("car_selected_exists", true)
                edt.putString("car_selected_name", carThree.brand + " " + carThree.model)
                edt.putString("car_selected_year", carThree.year)
                edt.putInt("car_selected_kilometers", carThree.kilometers)
                edt.putString("car_selected_last_TC", carThree.lastTC)
                edt.putString("car_selected_last_emptying", carThree.lastEmptying)
                edt.apply()
            }
        }

//        dispatchTakePictureIntent()
    }

    private fun lockUI() {
        disableUserInteraction()
        showProgressDialog()
        loading_mask_message.visibility = View.VISIBLE
    }

    private fun unlockUI() {
        enableUserInteraction()
        dismissProgressDialog()
        loading_mask_message.visibility = View.GONE
    }

    private fun enableUserInteraction() {
        Objects.requireNonNull<FragmentActivity>(activity).window
            .clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }

    private fun disableUserInteraction() {
        Objects.requireNonNull<FragmentActivity>(activity).window.setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
        )
    }

    private fun showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = ProgressDialog(context)
            progressDialog!!.isIndeterminate = true
            progressDialog!!.setCancelable(false)
        }
        progressDialog!!.setMessage(getString(R.string.loading_message))
        progressDialog!!.show()
    }

    private fun dismissProgressDialog() {
        progressDialog?.dismiss()
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
            carThreeIsCreated = true
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
            carTwoIsCreated = true
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
            carOneIsCreated = true
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
        generateTimestamp()

        if (!carOneIsCreated && !carTwoIsCreated && !carThreeIsCreated) {
            val car = activity!!.getSharedPreferences("car_one", MODE_PRIVATE)
            val edt = car.edit()
            edt.putBoolean("car_one_exists", true)
            edt.putString("car_brand", carBrand)
            edt.putString("car_model", carModel)
            edt.putString("car_year", carYear)
            edt.putInt("car_kilometers", carKilometers)
            edt.putString("car_last_tc", carLastTechnicalControl)
            edt.putString("car_last_emptying", carLastEmptying)
            edt.putString("car_date_creation", timestampCreation)
            edt.apply()

            Toast.makeText(this.activity, "Voiture créée !", Toast.LENGTH_LONG).show()
        } else if (carOneIsCreated && !carTwoIsCreated && !carThreeIsCreated) {
            val car = activity!!.getSharedPreferences("car_two", MODE_PRIVATE)
            val edt = car.edit()
            edt.putBoolean("car_two_exists", true)
            edt.putString("car_brand", carBrand)
            edt.putString("car_model", carModel)
            edt.putString("car_year", carYear)
            edt.putInt("car_kilometers", carKilometers)
            edt.putString("car_last_tc", carLastTechnicalControl)
            edt.putString("car_last_emptying", carLastEmptying)
            edt.putString("car_date_creation", timestampCreation)
            edt.apply()

            Toast.makeText(this.activity, "Voiture créée !", Toast.LENGTH_LONG).show()
        } else if (carOneIsCreated && carTwoIsCreated && !carThreeIsCreated) {
            val car = activity!!.getSharedPreferences("car_three", MODE_PRIVATE)
            val edt = car.edit()
            edt.putBoolean("car_three_exists", true)
            edt.putString("car_brand", carBrand)
            edt.putString("car_model", carModel)
            edt.putString("car_year", carYear)
            edt.putInt("car_kilometers", carKilometers)
            edt.putString("car_last_tc", carLastTechnicalControl)
            edt.putString("car_last_emptying", carLastEmptying)
            edt.putString("car_date_creation", timestampCreation)
            edt.apply()

            Tools().hideKeyboard(this.activity!!)
            Toast.makeText(this.activity, "Voiture créée !", Toast.LENGTH_LONG).show()
        }

    }

    private fun generateTimestamp() {
        val year: Int = Calendar.getInstance().get(Calendar.YEAR)

        val monthRaw: Int = Calendar.getInstance().get(Calendar.MONTH)
        val monthClean: String = Tools().getCleanMonth(monthRaw)

        val dayRaw: Int = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        val dayClean: String = Tools().getCleanDay(dayRaw)

        val hourRaw: Int = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        val hourClean: String = Tools().getCleanHour(hourRaw)

        val minuteRaw: Int = Calendar.getInstance().get(Calendar.MINUTE)
        val minuteClean: String = Tools().getCleanMinute(minuteRaw)

        val secondRaw: Int = Calendar.getInstance().get(Calendar.SECOND)
        val secondClean: String = Tools().getCleanSecond(secondRaw)

        timestampCreation = "$year-$monthClean-$dayClean $hourClean:$minuteClean:$secondClean"
        Log.e("Timestamp value", timestampCreation)
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
