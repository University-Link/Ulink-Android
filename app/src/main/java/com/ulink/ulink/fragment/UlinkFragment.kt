package com.ulink.ulink.fragment
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ulink.ulink.ClassRecycler.ClassAdapter
import com.ulink.ulink.R
import com.ulink.ulink.Ulink.BoardSearchRecycler.BoardSearchActivity
import com.ulink.ulink.Ulink.UlinkUlinkBoardActivity
import com.ulink.ulink.Ulink.UlinkInsideActivity
import com.ulink.ulink.Ulink.UlinkUniversityBoardActivity
import com.ulink.ulink.repository.*
import kotlinx.android.synthetic.main.fragment_class.rv_class
import kotlinx.android.synthetic.main.fragment_ulink.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UlinkFragment : Fragment() {
    lateinit var classAdapter : ClassAdapter
    val datas : MutableList<BoardSubject> = mutableListOf<BoardSubject>()
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ulink, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        classAdapter = ClassAdapter(view.context)
        rv_class.adapter = classAdapter
        RetrofitService.service.getBoardList(DataRepository.token).
        enqueue(object : Callback<ResponseBoardList> {
            override fun onFailure(call: Call<ResponseBoardList>, t: Throwable) {
                Log.d("tag", t.localizedMessage)
            }
            override fun onResponse(
                    call: Call<ResponseBoardList>,
                    response: Response<ResponseBoardList>

            ) {
                response.body()?.let{
                    if(it.status == 200){
                        for(i in 0 until it.data.list.size) {
                            datas.apply{
                                add(
                                        BoardSubject(
                                                subjectIdx = it.data.list[i].subjectIdx,
                                                name = it.data.list[i].name
                                        )
                                )
                            }
                            classAdapter.datas = datas
                            classAdapter.notifyDataSetChanged()
                        }
                    }
                } ?: Log.d("tag", response.message())
            }
        })
        classAdapter.setItemClickLIstener(object:ClassAdapter.ItemClickListener{
            override fun onClick(view:View, position:Int){
                val intent = Intent(getActivity(), UlinkInsideActivity::class.java)
                intent.putExtra("class", datas[position].name) //과목명
                intent.putExtra("idx", datas[position].subjectIdx.toString())
                startActivity(intent)
            }
        })
        layout_ulink_board.setOnClickListener(){
            val intent = Intent(getActivity(), UlinkUlinkBoardActivity::class.java)
            intent.putExtra("class", "Ulink")
            intent.putExtra("idx", "0")
            startActivity(intent)
        }
        layout_university_board.setOnClickListener(){
            val intent = Intent(getActivity(), UlinkUniversityBoardActivity::class.java)
            intent.putExtra("class", "우리학교")
            intent.putExtra("idx", "0")
            startActivity(intent)
        }
        btn_search.setOnClickListener {
            val intent = Intent(getActivity(), BoardSearchActivity::class.java)
            intent.putExtra("boardCategory",3)
            startActivity(intent)
        }
    }
}