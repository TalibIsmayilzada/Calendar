package com.example.calendar.ui.calendar

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.calendar.R
import kotlinx.coroutines.flow.collectLatest


class CalendarFragment : Fragment() {

    private val viewModel: CalendarViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_calendar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getData()

        val rv = view.findViewById<RecyclerView>(R.id.calendarRV)
        val date = view.findViewById<TextView>(R.id.date)
        val weekDay = view.findViewById<TextView>(R.id.weekDay)
        val next = view.findViewById<Button>(R.id.next)
        val prev = view.findViewById<Button>(R.id.prev)

        next.setOnClickListener {
            viewModel.next()
        }
        prev.setOnClickListener {
            viewModel.prev()
        }

        val mAdapter = CalendarAdapter()
        rv.adapter = mAdapter


        lifecycleScope.launchWhenStarted {
            viewModel.data.collectLatest { list ->
                if (list.isNotEmpty()) {
                    date.text = list.first().date
                    weekDay.text = list.first().day
                    Log.d("fsdfsdfsdf", list.toString())
                    val filteredList = list.filter { it.building != null }
                    mAdapter.setList(filteredList)
                }
            }
        }
    }

}