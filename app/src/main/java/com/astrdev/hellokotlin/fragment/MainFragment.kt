package com.astrdev.hellokotlin.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.astrdev.hellokotlin.R
import com.astrdev.hellokotlin.adapter.TaskAdapter
import com.astrdev.hellokotlin.database.TaskDaoImpl
import cz.kinst.jakub.view.StatefulLayout
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment: Fragment() {

    companion object {
        public fun newInstance() = MainFragment()
    }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater!!.inflate(R.layout.fragment_main, container, false)
        return view
    }


    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }


    override fun onResume() {
        super.onResume()
        loadTasks()
    }


    private fun initView() {
        fragment_main_add_task_bttn.setOnClickListener {
            fragmentManager.beginTransaction()
                    .replace(R.id.activity_main_fragment_holder, NewTaskFragment.newInstance())
                    .addToBackStack("MainFragment")
                    .commit()
        }

        fragment_main_todo_recycler.layoutManager = LinearLayoutManager(activity.applicationContext, LinearLayoutManager.VERTICAL, false)
    }


    private fun loadTasks() {
        setState(StatefulLayout.State.PROGRESS)
        // TODO: Async call
        var tasks = TaskDaoImpl(activity.applicationContext).getLatestTasks(0, 100)
        if (tasks != null) {
            fragment_main_todo_recycler.adapter = TaskAdapter(activity.applicationContext, tasks)
            setState(StatefulLayout.State.CONTENT)
        } else {
            setState(StatefulLayout.State.EMPTY)
        }
    }


    private fun setState(state: StatefulLayout.State) {
        fragment_main_state_layout.state = state
    }
}