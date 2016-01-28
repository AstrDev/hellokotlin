package com.astrdev.hellokotlin.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import com.astrdev.hellokotlin.database.contract.*
import com.astrdev.hellokotlin.entity.TaskEntity
import com.astrdev.hellokotlin.extension.*
import java.util.*

public interface TaskDao {

    public fun getLatestTasks(offset: Int, limit: Int): List<TaskEntity>?
    public fun deleteTask(entity: TaskEntity): Boolean
    public fun createTask(entity: TaskEntity): Boolean
}


public class TaskDaoImpl(val context: Context): TaskDao {

    override fun deleteTask(entity: TaskEntity): Boolean {
        var openHelper = DatabaseOpenHelper(context)
        var result = openHelper.delete(TASK_TABLE_NAME, "$TASK_ID = '${entity.id}'")
        return result
    }


    override fun getLatestTasks(offset: Int, limit: Int): List<TaskEntity>? {
        var openHelper = DatabaseOpenHelper(context)
        var query = "SELECT * FROM $TASK_TABLE_NAME ORDER BY $TASK_CREATED_AT DESC LIMIT $offset, $limit"
        var result: List<TaskEntity>? = null
        openHelper.performQuery(query) {
            result = if (count > 0) getTasksListFromCursor(this) else null
        }
        return result
    }


    override fun createTask(entity: TaskEntity): Boolean {
        var openHelper = DatabaseOpenHelper(context)
        var contentValues = toContentValues(entity)
        contentValues.put(TASK_CREATED_AT, System.currentTimeMillis())
        var result = openHelper.insert(TASK_TABLE_NAME, contentValues)
        return result != DATABASE_DEFAULT_RESULT_ID
    }


    private fun getTaskFromCursor(cursor: Cursor) = TaskEntity(
            cursor.getLong(TASK_ID), cursor.getString(TASK_TITLE),
            cursor.getString(TASK_TEXT), TaskEntity.Status.values()[cursor.getInt(TASK_STATUS)],
            cursor.getLong(TASK_CREATED_AT))


    private fun getTasksListFromCursor(cursor: Cursor): List<TaskEntity> {
        var result = ArrayList<TaskEntity>()
        cursor.moveToFirst()
        do {
            result.add(getTaskFromCursor(cursor))
        } while (cursor.moveToNext())
        return result
    }


    private fun toContentValues(entity: TaskEntity): ContentValues {
        var result = ContentValues()
        result.put(TASK_TITLE, entity.title)
        result.put(TASK_TEXT, entity.text)
        result.put(TASK_STATUS, entity.status.ordinal)
        return result
    }
}
