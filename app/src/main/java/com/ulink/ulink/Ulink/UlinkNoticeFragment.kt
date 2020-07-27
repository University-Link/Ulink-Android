package com.ulink.ulink.Ulink

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ulink.ulink.R
import com.ulink.ulink.Ulink.UlinkBoardRecycler.UlinkBoardAdapter
import com.ulink.ulink.Ulink.UlinkBoardRecycler.UlinkBoardData
import com.ulink.ulink.Ulink.UlinkNoticeRecycler.UlinkNoticeAdapter
import com.ulink.ulink.Ulink.UlinkNoticeRecycler.UlinkNoticeData
import kotlinx.android.synthetic.main.fragment_ulink_board.*
import kotlinx.android.synthetic.main.fragment_ulink_notice.*

class UlinkNoticeFragment : Fragment() {
    lateinit var notice_adapter : UlinkNoticeAdapter
    val datas : MutableList<UlinkNoticeData> = mutableListOf<UlinkNoticeData>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ulink_notice, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        notice_adapter = UlinkNoticeAdapter(view.context)

        rv_ulink_notice.adapter = notice_adapter

        datas.apply{
        add(
            UlinkNoticeData(
                date = "5/10",
                notice_name = "휴강",
                start_time = "15:00",
                end_time="17:00"
            )
        )
            add(
                UlinkNoticeData(
                    date = "5/10",
                    notice_name = "휴강",
                    start_time = "15:00",
                    end_time="17:00"
                )
            )
            add(
                UlinkNoticeData(
                    date = "5/10",
                    notice_name = "휴강",
                    start_time = "15:00",
                    end_time="17:00"
                )
            )
            add(
                UlinkNoticeData(
                    date = "5/10",
                    notice_name = "휴강",
                    start_time = "15:00",
                    end_time="17:00"
                )
            )
            notice_adapter.datas = datas
            notice_adapter.notifyDataSetChanged()
    }

    }
}