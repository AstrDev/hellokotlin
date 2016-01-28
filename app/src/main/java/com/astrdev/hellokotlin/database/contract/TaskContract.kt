package com.astrdev.hellokotlin.database.contract

import android.provider.BaseColumns

const val TASK_TABLE_NAME = "tasks"
const public val TASK_ID = BaseColumns._ID
const public val TASK_TITLE = "title"
const public val TASK_TEXT = "text"
const public val TASK_STATUS = "status"
const public val TASK_CREATED_AT = "created_at"


const val TASK_CREATE_TABLE = "CREATE TABLE $TASK_TABLE_NAME ($TASK_ID INTEGER PRIMARY KEY, $TASK_TITLE TEXT, $TASK_TEXT TEXT, " +
        "$TASK_STATUS TEXT, $TASK_CREATED_AT INTEGER)";
const val TASK_DROP_TABLE = "DROP TABLE $TASK_TABLE_NAME"
