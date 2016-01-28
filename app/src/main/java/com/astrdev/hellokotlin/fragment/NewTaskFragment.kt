package com.astrdev.hellokotlin.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import com.astrdev.hellokotlin.R
import com.astrdev.hellokotlin.database.TaskDaoImpl
import com.astrdev.hellokotlin.entity.TaskEntity
import com.astrdev.hellokotlin.extension.hideKeyboard
import kotlinx.android.synthetic.main.fragment_new_task.*

class NewTaskFragment: Fragment() {

    companion object {
        public fun newInstance() = NewTaskFragment()
    }

    private var textWatcher = object : TextWatcher {

        override fun afterTextChanged(s: Editable?) {}

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if (s!!.length > 0) {
                if (isAdded) fragment_new_task_done_bttn.isEnabled = true
            } else {
                if (isAdded) fragment_new_task_done_bttn.isEnabled = false
            }
        }
    }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater!!.inflate(R.layout.fragment_new_task, container, false)
        return view
    }


    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }


    override fun onDestroyView() {
        fragment_new_task_text_input.hideKeyboard()
        fragment_new_task_title_input.hideKeyboard()
        super.onDestroyView()
    }


    private fun init() {
        fragment_new_task_done_bttn.setOnClickListener { onDoneButtonClick() }
        fragment_new_task_title_input.addTextChangedListener(textWatcher)
        fragment_new_task_done_bttn.isEnabled = false
    }


    private fun onDoneButtonClick() {
        // Create a task from the given input
        var success = createTask(fragment_new_task_title_input.text.toString().trim(),
                fragment_new_task_text_input.text.toString().trim())

        if (success) {
            fragmentManager.popBackStack()
        } else {
            Toast.makeText(activity, "Error during creation", Toast.LENGTH_SHORT).show()
        }
    }


    private fun createTask(title: String, text: String): Boolean {
        var taskDao = TaskDaoImpl(activity.applicationContext)
        var taskToCreate = TaskEntity(0, title, text, TaskEntity.Status.CREATED, 0);
        return taskDao.createTask(taskToCreate)
    }


}
