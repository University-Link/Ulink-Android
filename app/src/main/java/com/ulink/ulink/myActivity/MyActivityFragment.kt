package com.ulink.ulink.myActivity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ulink.ulink.R
import com.ulink.ulink.Ulink.UlinkBoardRecycler.UlinkBoardData
import kotlinx.android.synthetic.main.fragment_my_activity.*

class MyActivityFragment : Fragment() {

    private val data : MutableList<UlinkBoardData> = arrayListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_my_activity, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setArticle()
    }

    fun setData(data : UlinkBoardData){
        this.data.add(data)
    }

    fun setArticle(){
        val mAdapter =  MyActivityArticleAdapter(requireContext())
        rv_myarticle.adapter = mAdapter
        mAdapter.datas.addAll(data)
    }

}