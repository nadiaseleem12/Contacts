package com.example.routecontacts

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.routecontacts.databinding.ActivityContactsBinding
import com.example.routecontacts.model.Contact
import com.example.routecontacts.utils.Constants

class ContactsActivity:AppCompatActivity() {

    private lateinit var binding: ActivityContactsBinding
    private lateinit var name:String
    private lateinit var phone:String
    private lateinit var description: String
    private val contactsList = mutableListOf<Contact>()
    private var adapter=ContactsAdapter(contactsList)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRV()
        onSaveBtnClick()
    }

    private fun initRV() {
        binding.rvContacts.adapter = adapter
        adapter.onItemClicked = OnItemClicked { contact ->
            navigateToContactDetailsActivity(contact)
        }

    }

    private fun navigateToContactDetailsActivity(contact: Contact) {
        val intent = Intent(this,ContactDetailsActivity::class.java)
        intent.putExtra(Constants.CONTACT,contact)
        startActivity(intent)
    }

    private fun onSaveBtnClick() {
        binding.saveBtn.setOnClickListener{
            if(!validateTextFields()){
                return@setOnClickListener
            }

            name = binding.nameEdt.text?.trim().toString()
            phone = binding.phoneEdt.text?.trim().toString()
            description = binding.descriptionEdt.text?.trim().toString()

            val contact  = Contact(name,description= description,phone=phone)
            addContactToRV(contact)
            clearTextFields()
        }
    }

    private fun addContactToRV(contact: Contact) {
        contactsList.add(contact)
        adapter.notifyItemInserted(contactsList.size - 1)
    }

    private fun clearTextFields() {
        binding.nameEdt.text?.clear()
        binding.phoneEdt.text?.clear()
        binding.descriptionEdt.text?.clear()
    }

    private fun validateTextFields():Boolean{
        name = binding.nameEdt.text?.trim().toString()
        phone = binding.phoneEdt.text?.trim().toString()
        binding.nameTil.error = validateName(name)
        binding.phoneTil.error = validatePhone(phone)

        return validateName(name)==null&&validatePhone(phone)==null
    }

    private fun validateName(name:String):String?{
        if(name.isEmpty()){
            return "Required"
        }
        if (name.length<3){
            return "Name can't be less than three characters"
        }

        val namePattern = "^[a-zA-Z]+$"
        if (!name.matches(namePattern.toRegex())){
            return "Name can only contain letters"
        }
        return null
    }

    private fun validatePhone(phone:String):String?{
        if(phone.isEmpty()){
            return "Required"
        }
        if (phone.length<11){
            return "Phone can't be less than eleven digits"
        }
        val phonePattern="^[0-9]+$"
        if (!phone.matches(phonePattern.toRegex())){
            return "Phone can only contain digits"
        }
        return null
    }
}
