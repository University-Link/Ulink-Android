package com.ulink.ulink.timetable

import android.content.ContentValues
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.ulink.ulink.R
import com.ulink.ulink.repository.TimeTable
import kotlinx.android.synthetic.main.fragment_save_to_image.*
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*

class SaveToImageFragment() : Fragment() {

    lateinit var mGlobalListner : ViewTreeObserver.OnGlobalLayoutListener

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

            mGlobalListner = ViewTreeObserver.OnGlobalLayoutListener {
                val sdf = SimpleDateFormat( "yyyyMMddHHmmss"); //년,월,일,시간 포멧 설정
                val time = Date(); //파일명 중복 방지를 위해 사용될 현재시간
                val fileName = sdf.format(time);

                val values = ContentValues().apply {
                    put(MediaStore.Images.Media.DISPLAY_NAME, it.name + fileName)
                    put(MediaStore.Images.Media.MIME_TYPE, "image/*")
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    values.put(MediaStore.Images.Media.IS_PENDING, 1);
                }

                val collection = MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)
                val item = requireContext().contentResolver.insert(collection, values)!!

                requireContext().contentResolver.openFileDescriptor(item, "w", null).use {
                    FileOutputStream(it!!.fileDescriptor).use { fos ->

                        var bitmap = Bitmap.createBitmap(view.measuredWidth,
                                view.measuredHeight,
                                Bitmap.Config.ARGB_8888)
                        var canvas = Canvas(bitmap)
                        val bgDrawable: Drawable? = view.background
                        if (bgDrawable != null) bgDrawable.draw(canvas) else canvas.drawColor(Color.WHITE)

                        view.draw(canvas)

                        bitmap.compress(Bitmap.CompressFormat.PNG, 80, fos)
                        fos.close()
                    }
                }
                values.clear()
                values.put(MediaStore.Images.Media.IS_PENDING, 0)
                requireContext().contentResolver.update(item, values, null, null)
                fragmentManager?.beginTransaction()?.remove(this)?.commit()
                Toast.makeText(context, "시간표가 저장되었습니다", Toast.LENGTH_SHORT).show();
            }
            layout_timetable.viewTreeObserver.addOnGlobalLayoutListener(mGlobalListner)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        layout_timetable.viewTreeObserver.removeOnGlobalLayoutListener(mGlobalListner)

    }

}







