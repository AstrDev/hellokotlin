package com.astrdev.hellokotlin.extension

import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.EditText


public fun EditText.showKeyboard() {
    var inputManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputManager.showSoftInput(this, 0)
}

public fun EditText.hideKeyboard() {
    var inputManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputManager.hideSoftInputFromWindow(windowToken, 0)
}