package com.astrdev.hellokotlin.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.astrdev.hellokotlin.database.contract.*;

const val DATABASE_VERSION = 2
const val DATABASE_NAME = "hellokotlin"

const val DROP_TABLE = "DROP TABLE $TASK_TABLE_NAME"

const val DATABASE_DEFAULT_RESULT_ID = -1L

class DatabaseOpenHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL(TASK_CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL(TASK_DROP_TABLE)
        db.execSQL(TASK_CREATE_TABLE)
    }


    inline fun performQuery(query: String, handleResult: Cursor.() -> Unit) {
        accessReadableDatabase {
            var cursor = rawQuery(query, null)
            cursor.handleResult()
            cursor.close()
        }
    }


    fun insert(tableName: String, values: ContentValues): Long {
        var result = DATABASE_DEFAULT_RESULT_ID
        accessWritableDatabase {
            result = insert(tableName, null, values)
        }
        return result
    }


    fun delete(tableName: String, where: String): Boolean {
        var rowsAffected = 0
        accessWritableDatabase {
            rowsAffected = delete(tableName, where, null)
        }
        return rowsAffected > 0
    }


    /**
     *  Run an action on readable database. Database will be automatically closed after the action.
     */
    inline fun accessReadableDatabase(action: SQLiteDatabase.() -> Unit) {
        var database = readableDatabase
        database.action()
        database.close()
    }


    inline fun accessWritableDatabase(action: SQLiteDatabase.() -> Unit) {
        var database = writableDatabase
        database.action()
        database.close()
    }
}