package com.iflytek.librarystudy.libphonenumber

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.i18n.phonenumbers.NumberParseException
import com.google.i18n.phonenumbers.PhoneNumberUtil
import com.google.i18n.phonenumbers.geocoding.PhoneNumberOfflineGeocoder
import com.iflytek.librarystudy.R
import java.util.*


class LibPhoneNumberActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lib_phonenumber)

        val cnNumberStr = "13205693712"
        val phoneNumberUtil = PhoneNumberUtil.getInstance()
        try {
            // parse into a PhoneNumber object
            val phoneNumber = phoneNumberUtil.parse(cnNumberStr, "CN")
            Log.e(TAG, "parse: ${phoneNumber.countryCode} ${phoneNumber.nationalNumber}")

            // validate whether the number is valid
            val isValidNumber = phoneNumberUtil.isValidNumber(phoneNumber)
            Log.e(TAG, "isValidNumber: $isValidNumber")

            // There are a few formats supported by the formatting method
            val internationalFormat =
                phoneNumberUtil.format(phoneNumber, PhoneNumberUtil.PhoneNumberFormat.INTERNATIONAL)
            Log.e(TAG, "internationalFormat: $internationalFormat")
            val nationalFormat =
                phoneNumberUtil.format(phoneNumber, PhoneNumberUtil.PhoneNumberFormat.NATIONAL)
            Log.e(TAG, "nationalFormat: $nationalFormat")
            val e164Format =
                phoneNumberUtil.format(phoneNumber, PhoneNumberUtil.PhoneNumberFormat.E164)
            Log.e(TAG, "e164Format: $e164Format")
            val rfc3966Format =
                phoneNumberUtil.format(phoneNumber, PhoneNumberUtil.PhoneNumberFormat.RFC3966)
            Log.e(TAG, "rfc3966Format: $rfc3966Format")

            // choose to format the number in the way it is dialed from another country
//            val formatOutOfCountryCallingNumber =
//                phoneNumberUtil.formatOutOfCountryCallingNumber(phoneNumber, "NG")
            val formatOutOfCountryCallingNumber =
                phoneNumberUtil.formatOutOfCountryCallingNumber(phoneNumber, "US")
            Log.e(TAG, "formatOutOfCountryCallingNumber: $formatOutOfCountryCallingNumber")

            // Formatting Phone Numbers 'as you type'
            val asYouTypeFormatter = phoneNumberUtil.getAsYouTypeFormatter("CN")
//            val inputDigit = asYouTypeFormatter.inputDigit('6')
            val inputDigit = asYouTypeFormatter.inputDigit('3')
            Log.e(TAG, "inputDigit: $inputDigit")

            // Geocoding Phone Numbers
            val geocoder = PhoneNumberOfflineGeocoder.getInstance()
            val descriptionForNumber = geocoder.getDescriptionForNumber(phoneNumber, Locale.CHINA)
            Log.e(TAG, "descriptionForNumber: $descriptionForNumber")
            val usDescriptionForNumber = geocoder.getDescriptionForNumber(phoneNumber, Locale.US)
            Log.e(TAG, "usDescriptionForNumber: $usDescriptionForNumber")


        } catch (e: NumberParseException) {
            e.printStackTrace()
        }
    }

    companion object {
        private const val TAG = "LibPhoneNumberActivity"
    }
}