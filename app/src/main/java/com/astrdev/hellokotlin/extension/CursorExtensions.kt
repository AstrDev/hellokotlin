package com.astrdev.hellokotlin.extension

import android.database.Cursor

public fun Cursor.getLong(column: String) = this.getLong(getColumnIndexOrThrow(column))

public fun Cursor.getString(column: String) = this.getString(getColumnIndexOrThrow(column))

public fun Cursor.getInt(column: String) = this.getInt(getColumnIndexOrThrow(column))
