package com.astrdev.hellokotlin.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.astrdev.hellokotlin.R
import com.astrdev.hellokotlin.entity.TaskEntity


class TaskAdapter(var context: Context, var items: List<TaskEntity>): RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder? {
        var inflater = LayoutInflater.from(context)
        return ViewHolder(inflater.inflate(R.layout.fragment_main_task_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder!!.bind(items[position])
    }

    override fun getItemCount() = items.size

}

class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

    var title = view.findViewById(R.id.fragment_main_task_item_title) as TextView
    var text = view.findViewById(R.id.fragment_main_task_item_text) as TextView

    public fun bind(task: TaskEntity) {
        // Bind views
        title.text = task.title
        text.text = task.text

        // Hide description if it's empty
        if (task.text.isEmpty()) {
            text.visibility = View.GONE
        } else {
            text.visibility = View.VISIBLE
        }
    }
}