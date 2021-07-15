package com.example.recyclertest

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.recyclertest.fragments.ItemModel
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.activity_new_item.*
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

class NewItemActivity : AppCompatActivity() {

    private val calendar: Calendar = Calendar.getInstance()
    private val viewModel: NewItemViewModel by viewModels(factoryProducer = {
        NewItemViewModel.Factory()
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_item)

        val saveButton = findViewById<Button>(R.id.saveButton)
        val editDate = findViewById<EditText>(R.id.editDate)

        val date = SimpleDateFormat("dd.MM.yyyy")
        val dateDefault = date.format(calendar.timeInMillis)
        editDate.setText(dateDefault)

        editDate.setOnClickListener {
            showDatePickerDialog()
        }

        saveButton.setOnClickListener {
            checkStateTitleLayout()
            checkStateDescriptionLayout()

            val newItem = ItemModel(
                title = editTitle.text.toString(),
                description = editDescription.text.toString(),
                isFavorite = false,
                date = Date()
            )

            viewModel.saveNewItem(newItem)

            Toast.makeText(this, "New item added", Toast.LENGTH_SHORT).show()
            finish()
        }

        textChangedListener()
    }

    private fun showDatePickerDialog() {
        val datePickerDialog = DatePickerDialog(
            this@NewItemActivity,
            { _, year, monthOfYear, dayOfMonth ->
                val selectedDate: String =
                    dayOfMonth.toString() + "." + (monthOfYear + 1) + "." + year
                editDate?.setText(selectedDate)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.datePicker.maxDate = calendar.timeInMillis
        datePickerDialog.show()
    }

    private fun checkStateTitleLayout() {
        val titleLayout = findViewById<TextInputLayout>(R.id.editTitleLayout)
        val checkTitleLayoutState = titleLayout.editText?.text?.toString()
        val fieldIsRequired = getString(R.string.fieldIsRequired)

        if (checkTitleLayoutState!!.isEmpty()) titleLayout.error = fieldIsRequired
    }

    private fun checkStateDescriptionLayout() {
        val descriptionLayout = findViewById<TextInputLayout>(R.id.editDescriptionLayout)
        val checkDescriptionLayoutState = descriptionLayout.editText?.text?.toString()
        val fieldIsRequired = getString(R.string.fieldIsRequired)

        if (checkDescriptionLayoutState!!.isEmpty()) descriptionLayout.error = fieldIsRequired
    }

    private fun textChangedListener() {
        val titleLayout = findViewById<TextInputLayout>(R.id.editTitleLayout)
        val descriptionLayout = findViewById<TextInputLayout>(R.id.editDescriptionLayout)

        titleLayout.editText?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                titleLayout.error = null
                titleLayout.isErrorEnabled = false
            }
        })

        descriptionLayout.editText?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                descriptionLayout.error = null
                descriptionLayout.isErrorEnabled = false
            }
        })
    }
}