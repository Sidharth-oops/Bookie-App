package com.example.bookie

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.provider.MediaStore.Audio.Media
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.widget.Adapter
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatCheckBox
import com.google.android.material.textfield.TextInputEditText
import de.hdodenhof.circleimageview.CircleImageView
import java.io.IOException

class Form : AppCompatActivity() {
    val alertString = arrayOf("Photo with camera", "Image from Gallery", "Cancel")
    val year = arrayOf("2016", "2017")
    lateinit var imgCamera: CircleImageView
    val stream = arrayOf("Electrical", "Mechanical")
    lateinit var button: AppCompatButton
    lateinit var autoCompleteTextViewYear: AutoCompleteTextView
    lateinit var autoCompleteTextViewBranch: AutoCompleteTextView

    lateinit var adapterYear: Adapter
    lateinit var saveButton: AppCompatButton
    lateinit var adapterBranch: Adapter
    var camera_request_code = 100
    var galleryRequestCode = 1000
    lateinit var consentCheckBox: AppCompatCheckBox
    lateinit var enteredName: TextInputEditText

    lateinit var enteredAdmissionNo: TextInputEditText

    lateinit var enteredPhoneNo: TextInputEditText
    lateinit var enteredEmailId: TextInputEditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)
        saveButton = findViewById(R.id.my_button)
        saveButton.isEnabled=false
        enteredName = findViewById(R.id.enteredName)
        enteredAdmissionNo = findViewById(R.id.enteredAdmissionNo)
        enteredPhoneNo = findViewById(R.id.enteredPhoneNumber)
        enteredEmailId = findViewById(R.id.enteredEmailId)
        consentCheckBox = findViewById(R.id.consentCheckBox)
        imgCamera = findViewById(R.id.circle_image_view)
        autoCompleteTextViewBranch = findViewById(R.id.autoComplete_branch)
        autoCompleteTextViewYear=findViewById(R.id.autoComplete_year)
        button = findViewById(R.id.add_profile_image)


        val inputFields = arrayOf(
            enteredName, enteredAdmissionNo, enteredPhoneNo,
            enteredEmailId, autoCompleteTextViewBranch, autoCompleteTextViewYear
        )

        // Function to check and update the state of the saveButton
        fun checkAndUpdateSaveButtonState() {
            val anyFieldEmpty = inputFields.any { it.text.toString().isEmpty() }
            val isConsentChecked = consentCheckBox.isChecked
            saveButton.isEnabled = !anyFieldEmpty && isConsentChecked
        }

        // Set listeners for each input field to trigger the check
        inputFields.forEach { editText ->
            editText.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    checkAndUpdateSaveButtonState()
                }

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                    // Unused
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    // Unused
                }
            })
        }

        // Set a listener for the consent checkbox
        consentCheckBox.setOnCheckedChangeListener { _, _ ->
            checkAndUpdateSaveButtonState()
        }

        saveButton.setOnClickListener {

        }
        button.setOnClickListener {
            val inflater = LayoutInflater.from(this)
            val customView = inflater.inflate(R.layout.custom_alert_dialogs, null)
            val builder = AlertDialog.Builder(this)
            builder.setView(customView)
            builder.setTitle("Create Post")

            val buttonOption1 = customView.findViewById<Button>(R.id.buttonOption1)
            val buttonOption2 = customView.findViewById<Button>(R.id.buttonOption2)
            val buttonOption3 = customView.findViewById<Button>(R.id.buttonOption3)
            val dialog = builder.create()
            dialog.show()

            buttonOption1.setOnClickListener {
                // Handle Option 1 click
                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(intent, camera_request_code)
                dialog.dismiss()

            }

            buttonOption2.setOnClickListener {
                val intent = Intent(Intent.ACTION_PICK)
                intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(intent, galleryRequestCode)
                dialog.dismiss()

            }

            buttonOption3.setOnClickListener {
                // Handle Option 3 click
                dialog.dismiss()
            }
        }
        adapterBranch = ArrayAdapter<String>(this, R.layout.drop_down_text_view, stream)
        autoCompleteTextViewBranch.setAdapter(adapterBranch as ArrayAdapter<*>)
        autoCompleteTextViewYear = findViewById(R.id.autoComplete_year)
        adapterYear = ArrayAdapter<String>(this, R.layout.drop_down_text_view, year)
        autoCompleteTextViewYear.setAdapter(adapterYear as ArrayAdapter<String>)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            if (requestCode == camera_request_code) {
                var pic: Bitmap? = data?.getParcelableExtra<Bitmap>("data")
                imgCamera.setImageBitmap(pic)

            } else if (requestCode == galleryRequestCode) {

                imgCamera.setImageURI(data?.data)
            }
        }
    }
}