package com.example.dictionarynew.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.dictionarynew.R
import com.example.dictionarynew.databinding.ActivityMainRecyclerviewItemBinding
import com.example.dictionarynew.model.DataModel
import com.example.dictionarynew.viewmodel.convertMeaningsToString
import kotlinx.android.synthetic.main.activity_main_recyclerview_item.view.*

//class MainAdapter(
//    private var onListItemClickListener: OnListItemClickListener
////    private var data: List<DataModel>
//) : RecyclerView.Adapter<MainAdapter.RecyclerItemViewHolder>() {
//
//    private var data: List<DataModel> = arrayListOf()
//    fun setData(data: List<DataModel>) {
//        this.data = data
//        notifyDataSetChanged()
//    }
//
//    inner class RecyclerItemViewHolder(val v: ActivityMainRecyclerviewItemBinding) :
//        RecyclerView.ViewHolder(v.root) {
//
//        fun bind(data: DataModel) {
//            if (layoutPosition != RecyclerView.NO_POSITION) {
//                v.headerTextviewRecyclerItem.text = data.text
//                v.descriptionTextviewRecyclerItem.text = convertMeaningsToString(data.meanings!!)
////                itemView.header_textview_recycler_item.text = data.text
////                itemView.description_textview_recycler_item.text = convertMeaningsToString(data.meanings!!)
//                itemView.setOnClickListener { openInNewWindow(data) }
//            }
//        }
//    }
//
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemViewHolder {
//        val inflater = LayoutInflater.from(parent.context)
//        var v = DataBindingUtil.inflate<ActivityMainRecyclerviewItemBinding>(
//            inflater,
//            R.layout.activity_main_recyclerview_item,
//            parent,
//            false
//        )
//
//        return RecyclerItemViewHolder(v)
//    }
//
//    override fun onBindViewHolder(holder: RecyclerItemViewHolder, position: Int) {
//        holder.v.dataModelItem = data[position]
////        holder.bind(data[position])
//    }
//
//    override fun getItemCount(): Int {
//        return data.size
//    }
//
//    private fun openInNewWindow(listItemData: DataModel) {
//        onListItemClickListener.onItemClick(listItemData)
//    }
//
//    interface OnListItemClickListener {
//        fun onItemClick(data: DataModel)
//    }
//}

// *** !!! From Alexey !!! *** //

//class MainAdapter(
//    private var onListItemClickListener: OnListItemClickListener
//) : RecyclerView.Adapter<MainAdapter.RecyclerItemViewHolder>() {
//
//    private var data: List<DataModel> = arrayListOf()
//    fun setData(data: List<DataModel>) {
//        this.data = data
//        notifyDataSetChanged()
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemViewHolder {
//        return RecyclerItemViewHolder(
//            LayoutInflater.from(parent.context)
//                .inflate(R.layout.activity_main_recyclerview_item, parent, false) as View
//        )
//    }
//
//    override fun onBindViewHolder(holder: RecyclerItemViewHolder, position: Int) {
//        holder.bind(data[position])
//    }
//
//    override fun getItemCount(): Int {
//        return data.size
//    }
//
//    inner class RecyclerItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//        private val vb: ActivityMainRecyclerviewItemBinding by viewBinding()
//        fun bind(data: DataModel) {
//            if (layoutPosition != RecyclerView.NO_POSITION) {
//                vb.headerTextviewRecyclerItem.text = data.text
//                vb.descriptionTextviewRecyclerItem.text = convertMeaningsToString(data.meanings!!)
//
//                itemView.setOnClickListener { openInNewWindow(data) }
//            }
//        }
//    }
//
//    private fun openInNewWindow(listItemData: DataModel) {
//        onListItemClickListener.onItemClick(listItemData)
//    }
//
//    interface OnListItemClickListener {
//        fun onItemClick(data: DataModel)
//    }
//}

//  *** !!! lesson3 !!! ***
//
class MainAdapter(private var onListItemClickListener: OnListItemClickListener) :
    RecyclerView.Adapter<MainAdapter.RecyclerItemViewHolder>() {


    private var data: List<DataModel> = arrayListOf()

    fun setData(data: List<DataModel>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemViewHolder {
        return RecyclerItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.activity_main_recyclerview_item, parent, false) as View
        )
    }

    override fun onBindViewHolder(holder: RecyclerItemViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class RecyclerItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(data: DataModel) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                itemView.header_textview_recycler_item.text = data.text
                itemView.description_textview_recycler_item.text = convertMeaningsToString(data.meanings!!)
                itemView.setOnClickListener { openInNewWindow(data) }
            }
        }
    }

    private fun openInNewWindow(listItemData: DataModel) {
        onListItemClickListener.onItemClick(listItemData)
    }

    interface OnListItemClickListener {
        fun onItemClick(data: DataModel)
    }
}
