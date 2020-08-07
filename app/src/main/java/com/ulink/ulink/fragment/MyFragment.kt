package com.ulink.ulink.fragment

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.ulink.ulink.Activity.*
import com.ulink.ulink.MainActivity
import com.ulink.ulink.R
import com.ulink.ulink.adapter.FAQExpandableAdapter
import com.ulink.ulink.myActivity.MyActivityActivity
import com.ulink.ulink.register.CollectAgreeActivity
import com.ulink.ulink.repository.DataRepository
import com.ulink.ulink.utils.DialogBuilder
import com.ulink.ulink.withdrawal.WithdrawalActivity
import kotlinx.android.synthetic.main.fragment_my.*

class MyFragment : Fragment() {

    lateinit var nickName : String
    lateinit var university : String
    var majorIdx = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadProfile()
    }

    override fun onResume() {
        super.onResume()
        loadProfile()
    }

    fun loadProfile(){
//        TODO 여기 서버 연결해서 getProfile
        DataRepository.getProfile(
                onSuccess = {
                    tv_nickname.text = it.data.nickname
                    tv_name.text = it.data.name
                    tv_schoolmajor.text = it.data.university +  " " + it.data.major
                    Glide.with(this).load(it.data.profileImage).circleCrop()
                            .error(R.drawable.mypage_img_profile).fallback(R.drawable.mypage_img_profile).into(my_profile_picture)

                    if (it.data.emailCheck == 0) {
                        btn_school_certification.setOnClickListener {
                            val intent = Intent(context, SchoolCertificateActivity::class.java)
                            startActivity(intent)
                        }
                    } else {
                        btn_school_certification.setOnClickListener {
                            DialogBuilder().apply {
                                build(requireContext())
                                setContent("이미 학교인증을 하였습니다.")
                                setClickListener {
                                    dismiss()
                                }
                                show()
                            }
                        }
                    }


                    nickName = it.data.nickname
                    university = it.data.university
                    majorIdx = it.data.majorIdx

                    setOnClick(true)
                },
                onFailure = {
                    setOnClick(false)
                }
        )

    }

    fun setOnClick(success : Boolean){

        layout_writingmine.setOnClickListener {
            val intent = Intent(context, MyActivityActivity::class.java)
            intent.putExtra("type",0)
            startActivity(intent)
        }
        tv_writingmine.setOnClickListener{
            val intent = Intent(context, MyActivityActivity::class.java)
            intent.putExtra("type",0)
            startActivity(intent)
        }

        layout_commentmine.setOnClickListener {
            val intent = Intent(context, MyActivityActivity::class.java)
            intent.putExtra("type",1)
            startActivity(intent)
        }
        tv_commentmine.setOnClickListener {
            val intent = Intent(context, MyActivityActivity::class.java)
            intent.putExtra("type",1)
            startActivity(intent)
        }

        layout_writinglike.setOnClickListener {
            val intent = Intent(context, MyActivityActivity::class.java)
            intent.putExtra("type",2)
            startActivity(intent)
        }

        tv_writinglike.setOnClickListener {
            val intent = Intent(context, MyActivityActivity::class.java)
            intent.putExtra("type",2)
            startActivity(intent)
        }

        if (success){
            btn_changemajor.setOnClickListener {
                val intent = Intent(context, ChangeMajorActivity::class.java)
                startActivity(intent)
            }

            btn_changenickname.setOnClickListener {
                val intent = Intent(context, ChangeNickNameActivity::class.java)
                intent.putExtra("nickName", nickName)
                intent.putExtra("university", university)
                intent.putExtra("majorIdx", majorIdx)
                startActivity(intent)
            }

            btn_changepassword.setOnClickListener {
                val intent = Intent(context, ChangePasswordActivity::class.java)
                startActivity(intent)
            }
        } else{
            btn_changemajor.setOnClickListener {
                setServerErrorDialog()
            }

            btn_changenickname.setOnClickListener {
                setServerErrorDialog()
            }

            btn_changepassword.setOnClickListener {
                setServerErrorDialog()
            }

        }


        btn_communityguide.setOnClickListener {
            val intent = Intent(context, CommunityGuideActivity::class.java)
            startActivity(intent)
        }

        btn_faq.setOnClickListener {
            val intent = Intent(context, FAQActivity::class.java)
            startActivity(intent)
        }

        btn_logout.setOnClickListener {
            DialogBuilder().apply {
                build(requireContext())
                setContent("로그아웃 되었습니다.")
                setClickListener {
                    dismiss()
                    val sharedPref: SharedPreferences = requireContext().getSharedPreferences("pref", Context.MODE_PRIVATE)
                    val sharedEdit = sharedPref.edit()
                    sharedEdit.putBoolean("autoLogin", false)
                    sharedEdit.commit()

                    val intent = Intent(context, LoginActivity::class.java)
                    startActivity(intent)
                    (context as MainActivity).finish()
                }
                show()
            }

        }
        btn_withdrawal.setOnClickListener {
            val intent = Intent(context, WithdrawalActivity::class.java)
            startActivity(intent)
        }
        btn_privacy.setOnClickListener{
            val intent = Intent(context, CollectAgreeActivity::class.java)
            intent.putExtra("prevView", "myPage")
            startActivity(intent)
        }

        btn_notifysetting.setOnClickListener {
            DialogBuilder().apply {
                build(requireContext())
                setContent("준비 중입니다.")
                setClickListener {
                    dismiss()
                }
                show()
            }
        }
    }

    fun setServerErrorDialog(){
        DialogBuilder().apply {
            build(requireContext())
            setContent("서버 연결을 확인해주세요")
            setClickListener {
                dismiss()
            }
            show()
        }
    }
}