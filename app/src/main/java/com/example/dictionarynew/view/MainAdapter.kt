package com.example.dictionarynew.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.dictionarynew.R
import com.example.dictionarynew.databinding.ActivityMainRecyclerviewItemBinding
import com.example.dictionarynew.model.DataModel
import com.example.dictionarynew.viewmodel.convertMeaningsToString

class MainAdapter(
    private var onListItemClickListener: OnListItemClickListener
//    private var data: List<DataModel>
) : RecyclerView.Adapter<MainAdapter.RecyclerItemViewHolder>() {

    private var data: List<DataModel> = arrayListOf()
    fun setData(data: List<DataModel>) {
        this.data = data
        notifyDataSetChanged()
    }

    inner class RecyclerItemViewHolder(val v: ActivityMainRecyclerviewItemBinding) :
        RecyclerView.ViewHolder(v.root) {
        fun bind(data: DataModel) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                v.headerTextviewRecyclerItem.text = data.text
                v.descriptionTextviewRecyclerItem.text = convertMeaningsToString(data.meanings!!)
//                itemView.header_textview_recycler_item.text = data.text
//                itemView.description_textview_recycler_item.text = convertMeaningsToString(data.meanings!!)
                itemView.setOnClickListener { openInNewWindow(data) }
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        var v = DataBindingUtil.inflate<ActivityMainRecyclerviewItemBinding>(
            inflater,
            R.layout.activity_main_recyclerview_item,
            parent,
            false
        )

        return RecyclerItemViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerItemViewHolder, position: Int) {
        holder.v.dataModelItem = data[position]
    }

    override fun getItemCount(): Int {
        return data.size
    }

    private fun openInNewWindow(listItemData: DataModel) {
        onListItemClickListener.onItemClick(listItemData)
    }

    interface OnListItemClickListener {
        fun onItemClick(data: DataModel)
    }
}
