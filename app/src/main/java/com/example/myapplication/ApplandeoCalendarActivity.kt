package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.applandeo.materialcalendarview.CalendarView
import com.applandeo.materialcalendarview.EventDay
import com.applandeo.materialcalendarview.builders.DatePickerBuilder
import com.applandeo.materialcalendarview.listeners.OnSelectDateListener
import kotlinx.android.synthetic.main.activity_applandeo_calendar.*
import java.util.*


class ApplandeoCalendarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_applandeo_calendar)
        val calendar = Calendar.getInstance()
        val calendars: ArrayList<Calendar> = ArrayList()

        val events: MutableList<EventDay> = ArrayList()
        calendar.set(2019, 0, 2)

        calendarView.setDate(calendar)

        calendarView.setOnDayClickListener {

            calendars.add(it.calendar)
          //  calendarView.setSelectedDates(calendars);
            calendarView.setHighlightedDays(calendars);

            calendars.forEach {
                Log.d("selectedDates = ", " = ${it.time}")
            }


            events.add(EventDay(it.calendar, R.drawable.ic_android_black_24dp))
            calendarView.setEvents(events)

        }

        val listener = object : OnSelectDateListener {
            override fun onSelect(calendar: MutableList<Calendar>?) {
                Log.d("CalendarPicker", " = ${calendar?.size}")

                calendar?.forEach {
                    events.add(EventDay(it, R.drawable.ic_android_black_24dp))
                    calendarView.setEvents(events)
                }

            }
        }

//        val builder = DatePickerBuilder(this, listener).setSelectionColor(R.color.black)
//            .pickerType(CalendarView.MANY_DAYS_PICKER)
//
//        val datePicker = builder.build()
//        datePicker.show()
//

    }
}