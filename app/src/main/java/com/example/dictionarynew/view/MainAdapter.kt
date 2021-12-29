package com.example.dictionarynew.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.dictionarynew.R
import com.example.dictionarynew.databinding.ActivityMainRecyclerviewItemBinding
import com.example.dictionarynew.model.DataModel
import kotlinx.android.synthetic.main.activity_main_recyclerview_item.view.*

class MainAdapter(
//    private var onListItemClickListener: OnListItemClickListener,
    private var data: List<DataModel>) : RecyclerView.Adapter<MainAdapter.RecyclerItemViewHolder>() {

    fun setData(data: List<DataModel>) {
        this.data = data
        notifyDataSetChanged()
    }

    inner class RecyclerItemViewHolder(val v: ActivityMainRecyclerviewItemBinding) : RecyclerView.ViewHolder(v.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        var v = DataBindingUtil.inflate<ActivityMainRecyclerviewItemBinding>(inflater, R.layout.activity_main_recyclerview_item, parent, false)
//        val vb = DataBindingUtil.inflate<ActivityMainRecyclerviewItemBinding>(
//            inflater,
//            R.layout.activity_main_recyclerview_item,
//            parent,
//            false
//        )
        return RecyclerItemViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerItemViewHolder, position: Int) {
        holder.v.dataModelItem = data[position]
    }

    override fun getItemCount(): Int {
        return data.size
    }

//    private fun openInNewWindow(listItemData: DataModel) {
//        onListItemClickListener.onItemClick(listItemData)
//    }

    interface OnListItemClickListener {
        fun onItemClick(data: DataModel)
    }
}
