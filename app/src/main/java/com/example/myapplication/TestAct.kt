package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.applandeo.materialcalendarview.EventDay
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.gson.Gson
import kotlinx.android.synthetic.main.frame.*
import java.util.*

class TestAct: AppCompatActivity() {

    lateinit var datePicker: AppDatePicker
    val events: MutableList<EventDay> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = LayoutInflater.from(this).inflate(
            R.layout.frame, null, false
        )

        setContentView(
            view
        )

        datePicker = view.findViewById<AppDatePicker>(R.id.dtp)
        val dataa = Calendar.getInstance().apply {
            add(Calendar.DATE, 1)
        }

        val cal = Calendar.getInstance()
        cal.add(Calendar.DAY_OF_YEAR, +14);
        calendarView.setMaximumDate(cal)

        Log.d("datada", " = ${dataa}")

        val dateTemp = Calendar.getInstance();
        dateTemp.set(Calendar.DAY_OF_MONTH, 27);
        dateTemp.set(Calendar.YEAR, 2022);
        dateTemp.set(Calendar.MONTH, 8)

        val dateTemp2 = Calendar.getInstance();
        dateTemp2.set(Calendar.DAY_OF_MONTH, 28);
        dateTemp2.set(Calendar.YEAR, 2022);
        dateTemp2.set(Calendar.MONTH, 8)

        val dateTemp3 = Calendar.getInstance();
        dateTemp3.set(Calendar.DAY_OF_MONTH, 15);
        dateTemp3.set(Calendar.YEAR, 2022);
        dateTemp3.set(Calendar.MONTH, 8)

        val data = arrayListOf<DateData>()
        data.add(DateData(dateTemp, arrayListOf("Mitra 1", "Agen")))
        data.add(DateData(dateTemp2, arrayListOf("Mitra 2", "Agen")))
        data.add(DateData(dateTemp3, arrayListOf("Mitra 3", "Agen", "R", "sd", "dsdsdsd")))

        Log.d("dattt", " = ${Gson().toJson(data)}")

        datePicker.fillData(
            data.sortedByDescending { it.date }
        )

        events.add(EventDay(dateTemp3, R.drawable.ic_baseline_circle_24))
        calendarView.setEvents(events)

        view.findViewById<AppDatePicker>(R.id.dtp).addOnClickListener(object : OnItemClickListener {
            override fun onItemClick(date: Calendar, data: List<String>?) {
                Log.e("iLOG", "${date} ${data}")
            }
        })

        calendarView.setOnDayClickListener {
            datePicker.focusTo(it.calendar.timeInMillis)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        MenuInflater(this).inflate(R.menu.test,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.tgl){
            val picker = MaterialDatePicker.Builder.datePicker()
            picker.setTitleText("Pilih Tanggal")
            val dialog = picker.build()
            dialog.addOnPositiveButtonClickListener{
                Log.d("lonnngg", " = ${it}")
                datePicker.focusTo(it)
            }
            dialog.show(supportFragmentManager,picker.toString())
        }
        return true
    }
}