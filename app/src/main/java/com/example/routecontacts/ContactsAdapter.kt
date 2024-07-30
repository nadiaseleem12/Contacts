package com.example.routecontacts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.routecontacts.databinding.ItemContactBinding
import com.example.routecontacts.model.Contact

class ContactsAdapter(private val contactsList:MutableList<Contact>):RecyclerView.Adapter<ContactsAdapter.ViewHolder>() {
    class ViewHolder(val itemContactBinding: ItemContactBinding):RecyclerView.ViewHolder(itemContactBinding.root){
        fun bind(contact: Contact){
            itemContactBinding.nameTv.text = contact.name
            itemContactBinding.phoneTv.text = contact.phone
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemContactBinding.inflate(LayoutInflater.from(parent.context),parent,false)
       return ViewHolder(binding)
    }

    override fun getItemCount(): Int = contactsList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val contact = contactsList[position]
        holder.bind(contact)
        holder.itemContactBinding.root.setOnClickListener {
            onItemClicked?.onClick(contact)
        }
    }


    var onItemClicked:OnItemClicked?=null

}

fun interface OnItemClicked{
    fun onClick(contact:Contact)
}
