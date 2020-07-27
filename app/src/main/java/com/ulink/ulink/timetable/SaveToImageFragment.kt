package com.ulink.ulink.timetable

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import com.ulink.ulink.R
import com.ulink.ulink.repository.TimeTable

class SaveToImageFragment(val fragmentListener: fragmentListener) : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_save_to_image, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getParcelable<TimeTable>("timeTable")?.let {
            val drawer = TimeTableDrawer(requireContext(), LayoutInflater.from(requireContext()), it)
            drawer.drawForSaveToImage(view.findViewById(R.id.layout_timetable))
        }
    }

    override fun onResume() {
        super.onResume()
        fragmentListener.onGenerated(this)
    }
}