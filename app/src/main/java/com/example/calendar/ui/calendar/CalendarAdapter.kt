package com.example.calendar.ui.calendar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.calendar.R

class CalendarAdapter : RecyclerView.Adapter<CalendarAdapter.CalendarVH>() {


    val list = mutableListOf<CalendarItem>()


    fun setList(newList: List<CalendarItem>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    inner class CalendarVH(view: View) : RecyclerView.ViewHolder(view) {

        val date: TextView = view.findViewById(R.id.date)
        val room: TextView = view.findViewById(R.id.room)
        val desc: TextView = view.findViewById(R.id.desc)
        val blue: View = view.findViewById(R.id.blueView)

        fun bind(model: CalendarItem) {
            date.text = "${model.timeBegin} - ${model.timeEnd}"
            room.text = "${model.room} (${model.building})"
            desc.text = "${model.typeWorkName}${model.description}\n${model.fio}"

            if (model.weekTypeName ==  "Красная неделя"){
                blue.setBackgroundColor(itemView.context.resources.getColor(R.color.red))
            }
            if (model.weekTypeName ==  "Goy"){
                blue.setBackgroundColor(itemView.context.resources.getColor(R.color.blue))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarVH {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view: View = layoutInflater.inflate(R.layout.calendar_list_item, parent, false)
        return CalendarVH(view)
    }

    override fun onBindViewHolder(holder: CalendarVH, position: Int) {
        val model = list.get(position)
        holder.bind(model)
    }

    override fun getItemCount(): Int = list.size

}