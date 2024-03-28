package com.apprestaurante.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.apprestaurante.databinding.ItemItemBinding

class AddItemAdapter(private val MenuItemName:ArrayList<String>, private val MenuItemPrice:ArrayList<String>, private val MenuItemImage:ArrayList<Int>) : RecyclerView.Adapter<AddItemAdapter.AddItemViewHold>(){
    private  val itemQuantities = IntArray(MenuItemName.size){1}
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddItemViewHold {
        val binding = ItemItemBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return AddItemViewHold(binding)
    }
    override fun onBindViewHolder(holder: AddItemViewHold, position: Int) {
        holder.bind(position)
    }
    override fun getItemCount(): Int = MenuItemName.size
    inner class AddItemViewHold(private val binding: ItemItemBinding) :RecyclerView.ViewHolder(binding.root){
        fun bind(position: Int) {
            binding.apply {
                val quantity = itemQuantities[position]
                nombreComidaTextView.text=MenuItemName[position]
                precioTextView.text=MenuItemPrice[position]
                imagenComidaView.setImageResource(MenuItemImage[position])
                quantityTextView.text = quantity.toString()
                minusButton.setOnClickListener{
                    decreaseQuantity(position)
                }
                deleteButton.setOnClickListener{
                    deleteQuantity(position)
                }
                pluseButton.setOnClickListener{
                    increaseQuantity(position)
                }

            }
        }

        private fun increaseQuantity(position: Int) {
           if(itemQuantities[position]<10){
               itemQuantities[position]++
               binding.quantityTextView.text=itemQuantities[position].toString()
           }
        }
        private fun decreaseQuantity(position: Int) {
            if(itemQuantities[position]>1){
                itemQuantities[position]--
                binding.quantityTextView.text=itemQuantities[position].toString()
            }
        }
        private fun deleteQuantity(position: Int) {
            MenuItemName.removeAt(position)
            MenuItemPrice.removeAt(position)
            MenuItemImage.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position,MenuItemName.size)
        }
    }
}