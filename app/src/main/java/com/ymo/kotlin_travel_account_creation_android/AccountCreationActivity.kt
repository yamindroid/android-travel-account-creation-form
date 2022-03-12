package com.ymo.kotlin_travel_account_creation_android

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import com.ymo.kotlin_travel_account_creation_android.databinding.ActivityAccountCreationBinding
import com.ymo.kotlin_travel_account_creation_android.utils.checkValidEmail
import java.util.*

class AccountCreationActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener {
    private var _binding: ActivityAccountCreationBinding? = null
    private val binding get() = _binding!!
    private lateinit var gender: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityAccountCreationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpUIs();
    }

    private fun setUpUIs() {
        gender = binding.swGender.labelOff
        binding.swGender.setOnToggledListener { _, isOn ->
            gender = if (isOn) {
                binding.swGender.labelOn
            } else {
                binding.swGender.labelOff
            }
        }
        binding.edtDob.setOnClickListener {
            chooseDOB();
        }
        binding.createAccountBtn.setOnClickListener {
            accountCreation();
        }
        binding.ivBack.setOnClickListener { finish() }
    }

    private fun accountCreation() {
        val firstName: String = binding.edtFirstName.text.toString().trim()
        val lastName: String = binding.edtLastName.text.toString().trim()
        val emailAddress: String = binding.edtEmailAddress.text.toString().trim()
        val dateOfBirth: String = binding.edtDob.text.toString().trim()
        val nationality: String = binding.edtNationality.text.toString().trim()
        val countryOfResidence: String = binding.edtCountryOfResidence.text.toString().trim()
        var error = false
        if (TextUtils.isEmpty(firstName)) {
            binding.edtFirstName.error = getString(R.string.error_field_required)
            error = true
            return
        } else if (TextUtils.isEmpty(lastName)) {
            binding.edtLastName.error = getString(R.string.error_field_required)
            error = true
            return
        } else if (TextUtils.isEmpty(emailAddress)) {
            binding.edtEmailAddress.error = getString(R.string.error_field_required)
            error = true
            return
        } else if (TextUtils.isEmpty(nationality)) {
            binding.edtNationality.error = getString(R.string.error_field_required)
            error = true
            return
        } else if (TextUtils.isEmpty(countryOfResidence)) {
            binding.edtCountryOfResidence.error = getString(R.string.error_field_required)
            error = true
            return
        } else if (dateOfBirth.equals(R.string.dob_sample)) {
            binding.edtDob.error = getString(R.string.error_field_required)
            error = true
            return
        } else if (!checkValidEmail(emailAddress)) {
            binding.edtEmailAddress.error =
                getString(R.string.invalid_email)
            error = true
            return
        }
        if (!error) {
            Toast.makeText(applicationContext, "Account Created Successfully !", Toast.LENGTH_LONG)
                .show()
        }
    }

    private fun chooseDOB() {
        val now: Calendar = Calendar.getInstance()
        val dpd: DatePickerDialog = DatePickerDialog.newInstance(
            this,
            now.get(Calendar.YEAR),
            now.get(Calendar.MONTH),
            now.get(Calendar.DAY_OF_MONTH)
        )
        dpd.show(supportFragmentManager, "DatePickerDialog")
    }

    override fun onDateSet(
        view: DatePickerDialog?,
        year: Int,
        monthOfYear: Int,
        dayOfMonth: Int
    ) {
        val date = dayOfMonth.toString() + "/" + (monthOfYear + 1).toString() + "/" + year
        binding.edtDob.setText(date)
    }

}

