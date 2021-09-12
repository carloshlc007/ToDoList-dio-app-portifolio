package com.wolftiger.todolist.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import com.wolftiger.todolist.databinding.ActivityAddTaskBinding
import com.wolftiger.todolist.extensions.format
import com.wolftiger.todolist.extensions.text
import java.util.*


class AddTaskActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddTaskBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        insertListeners()
    }

    private fun insertListeners() {
        binding.tilDate.editText?.setOnClickListener{
           val datePicker = MaterialDatePicker.Builder.datePicker().build()
            datePicker.addOnPositiveButtonClickListener {
                val timeZone = TimeZone.getDefault()
                val offset = timeZone.getOffset(Date().time) * -1
                binding.tilDate.text = Date(it + offset).format()
            }
            datePicker.show(supportFragmentManager, "DATE_PICKER_TAG")
        }

        binding.tilHour.editText?.setOnClickListener{
            val timerPicker = MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .build()

            timerPicker.addOnPositiveButtonClickListener{
                binding.tilHour.text = "${timerPicker.hour}:${timerPicker.minute}"
            }
            timerPicker.show(supportFragmentManager, null)
        }
    }
}