package com.ulink.ulink.Ulink.BoardRecycler

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ulink.ulink.R
import com.ulink.ulink.Ulink.BoardData
import com.ulink.ulink.Ulink.onClickLike

const val VIEWTYPE_ULINK = 0
const val VIEWTYPE_UNIV = 1
const val VIEWTYPE_SUBJECT = 2
const val VIEWTYPE_NOTICE = 3

class AllBoardAdapter(private val context: Context, val searchType: Int, val search: Boolean) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var datas_ulink: MutableList<BoardData> = mutableListOf()
    var datas_university: MutableList<BoardData> = mutableListOf()
    var datas_class: MutableList<BoardData> = mutableListOf()


    private var dataSearchAllBoard: MutableList<BoardData> = mutableListOf()

    var mListener : onClickLike? = null
    fun setListener(onClickLike: onClickLike){
        mListener = onClickLike

    }
    fun setUlinkData(list : List<BoardData>){
        datas_ulink.clear()
        datas_ulink.addAll(list)
        notifyDataSetChanged()
    }
    fun addUlinkData(list : List<BoardData>){
        datas_ulink.addAll(list)
        notifyDataSetChanged()
    }
    fun addUnivData(list : List<BoardData>){
        datas_university.addAll(list)
        notifyDataSetChanged()
    }
    fun addSubjectData(list : List<BoardData>){
        datas_class.addAll(list)
        notifyDataSetChanged()
    }

    fun setUnivData(list : List<BoardData>){
        datas_university.clear()
        datas_university.addAll(list)
        notifyDataSetChanged()
    }
    fun setSubjectData(list : List<BoardData>){
        datas_class.clear()
        datas_class.addAll(list)
        notifyDataSetChanged()
    }

    fun setAllDataSearch(list : MutableList<BoardData>){
        dataSearchAllBoard.clear()
        dataSearchAllBoard.addAll(list)
        notifyDataSetChanged()
    }


    override fun getItemViewType(position: Int): Int {
        if (search) {
            return when (searchType) {
                0 -> super.getItemViewType(position)
                1 -> super.getItemViewType(position)
                2 -> super.getItemViewType(position)
                else -> {
                    when (dataSearchAllBoard[position].category) {

                        0-> VIEWTYPE_ULINK
                        1 -> VIEWTYPE_UNIV
                        2 -> VIEWTYPE_SUBJECT
                        else -> VIEWTYPE_NOTICE
                    }
                }
            }
        } else {
            return super.getItemViewType(position)
        }
    }


    override fun getItemCount(): Int {
        return if (search) {
            when (searchType) {
                0 -> datas_ulink.size
                1 -> datas_university.size
                2 -> datas_class.size
                else -> dataSearchAllBoard.size
            }
        } else {
            when (searchType) {
                0 -> datas_ulink.size
                1 -> datas_university.size
                else -> datas_class.size
            }
        }
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (search) {
            when (searchType) {
                0 -> (holder as BoardViewHolder).bind(datas_ulink[position], false) //태그있는
                1 -> (holder as BoardViewHolder).bind(datas_university[position], false) //태그없는
                2 ->  (holder as BoardClassViewHolder).bind(datas_class[position], false)
                else -> {
                    when(getItemViewType(position)){
                        0 -> (holder as BoardViewHolder).bind(dataSearchAllBoard[position], true) //태그있는
                        1 -> (holder as BoardViewHolder).bind(dataSearchAllBoard[position], true) //태그없는
                        2 ->  (holder as BoardClassViewHolder).bind(dataSearchAllBoard[position], true)
                        else -> {
                        }
                    }
                }

            }

        } else {
            when (searchType) {
                0 -> (holder as BoardViewHolder).bind(datas_ulink[position], false) //태그있는
                1 -> (holder as BoardViewHolder).bind(datas_university[position], false) //태그없는
                else -> (holder as BoardClassViewHolder).bind(datas_class[position], false)
            }
        }

        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it, position)
        }
    }

    // searchType = 어느 게시판인지 ,  search = 검색인지 아닌
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (search) {
            return when (searchType) {
                0, 1 -> BoardViewHolder(LayoutInflater.from(context).inflate(R.layout.item_searched_ulink_board_ulink_data, parent, false),mListener)
                2 -> BoardClassViewHolder(LayoutInflater.from(context).inflate(R.layout.item_ulink_board_class_data, parent, false),mListener)
                else -> {
                    when(viewType){
                        VIEWTYPE_ULINK, VIEWTYPE_UNIV -> BoardViewHolder(LayoutInflater.from(context).inflate(R.layout.item_searched_ulink_board_ulink_data, parent, false),mListener)
                        VIEWTYPE_SUBJECT -> BoardClassViewHolder(LayoutInflater.from(context).inflate(R.layout.item_ulink_board_class_data, parent, false),mListener)
                        //                      FIXME 여기 공지 뷰 추가하기!!
                        //  VIEWTYPE_NOTICE -> return
                        else -> BoardClassViewHolder(LayoutInflater.from(context).inflate(R.layout.item_ulink_board_class_data, parent, false),mListener)

                    }

                }
            }
        } else {
            return when (searchType) {
                0, 1 -> BoardViewHolder(LayoutInflater.from(context).inflate(R.layout.item_ulink_board_ulink_data, parent, false),mListener)
                else -> BoardClassViewHolder(LayoutInflater.from(context).inflate(R.layout.item_ulink_board_class_data, parent, false),mListener)
            }
        }

    }

    private lateinit var itemClickListener: ItemClickListener

    interface ItemClickListener {
        fun onClick(view: View, position: Int)
    }

    fun setItemClickLIstener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }


}