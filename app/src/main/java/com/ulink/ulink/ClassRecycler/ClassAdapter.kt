package com.ulink.ulink.ClassRecycler
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ulink.ulink.R
import com.ulink.ulink.repository.BoardSubject

class ClassAdapter(private val context: Context) :RecyclerView.Adapter<ClassViewHolder>(){
  var datas : MutableList<BoardSubject> = mutableListOf<BoardSubject>()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClassViewHolder {
    val view = LayoutInflater.from(context).inflate(R.layout.class_item,parent,false)
    return ClassViewHolder(view)
  }
  override fun getItemCount(): Int {
    return datas.size
  }

  private lateinit var itemClickListener : ItemClickListener
  interface ItemClickListener {
    fun onClick(view: View, position:Int)
  }

  fun setItemClickLIstener(itemClickListener: ItemClickListener){
    this.itemClickListener = itemClickListener
  }

  override fun onBindViewHolder(holder: ClassViewHolder, position: Int) {
    holder.bind(datas[position])
    holder.itemView.setOnClickListener { itemClickListener.onClick(it, position) }
  }
}