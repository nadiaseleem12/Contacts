package com.example.routecontacts

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.IntentCompat
import com.example.routecontacts.databinding.ActivityContactDetailsBinding
import com.example.routecontacts.model.Contact
import com.example.routecontacts.utils.Constants

class ContactDetailsActivity:AppCompatActivity() {
    private lateinit var binding:ActivityContactDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setActivityViews()
        navigateBack()


    }

    private fun navigateBack() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true

    }


    private fun setActivityViews() {
        val contact = IntentCompat.getParcelableExtra(intent,Constants.CONTACT,Contact::class.java)
        contact?.let { contact ->
            binding.nameValue.text = contact.name
            binding.phoneValue.text = contact.phone
            binding.descriptionValue.text = contact.description
        }
    }
}
