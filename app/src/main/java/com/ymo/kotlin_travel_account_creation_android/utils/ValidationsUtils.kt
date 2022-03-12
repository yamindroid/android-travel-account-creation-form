package com.ymo.kotlin_travel_account_creation_android.utils

import android.text.TextUtils
import android.util.Patterns


fun checkValidEmail(target: CharSequence?): Boolean {
    return if (TextUtils.isEmpty(target)) {
        false
    } else {
        Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }
}
