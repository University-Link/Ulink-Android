package com.ulink.ulink.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import com.ulink.ulink.R

class FAQExpandableAdapter(val context : Context) : BaseExpandableListAdapter() {

    val bigList = listOf("공지 내용이 부정확해요. 오류 보고는 어떻게 하나요??", "시간표 테마 및 색상을 변경하고 싶어요.","온라인 강의는 시간표에 추가할 수 없나요?",
    "삭제된 게시글 및 댓글을 복구하고 싶어요.", "개인정보 침해, 허위사실 유표, 명예 훼손 등에 법적인 조취를 치하고 싶어요.", "신고된 게시글 및 댓글은 어떻게 처리되나요?",
    "새로 만든 시간표를 대표 시간표로 설정하고 싶어요.","실수로 캘린더 일정을 제거했어요. 다시 등록하려면 어떻게 해야 하나요?", "수강 편람 자료는 어떤 경로를 통해 수집하나요?",
    "학교 이메일이 기억나지 않아요. 다른 방법으로 인증할 수 있나요?", "유링크 게시판의 닉네임 옆 이니셜은 무엇인가요?")

    val smallList = listOf("수업별 소통공간에서 수정 권한은 공지 작성자에게만 주어집니다. 공지 내용이 부정확하다면, 댓글을 통해 작성자에게 수정을 요청할 수 있습니다. 만약 공지 작성자가 내용을 수정하지 않는다면, 내 캘린더에서 해당 공지를 삭제 후 새로운 공지를 등록해주세요.",
    "시간표 테마 및 색상을 변경하시려면, 대표 시간표에서 과목 블록을 길게 탭해주세요. 그 후 팝업 창에서 ‘시간표 커스터마이징’ 버튼을 눌러 시간표를 다양하게 꾸밀 수 있습니다.",
            "강의 시간이 확정되지 않은 온라인 강의의 경우, 시간표에 추가할 수 없습니다.", "유링크 게시판에서 삭제된 글과 댓글은 영구 폐기 처리되며, 복구할 수 없습니다.", "유링크는 정보통신망 이용촉진 및 정보보호 등에 관한 법률에 근거하여, 개인정보 침해, 허위사실 유포, 명예 훼손 등 개인의 권리 침해에 대한 게시글 및 댓글에 대한 신고를 받고 있습니다. 보다 강력한 법적 조치를 원하실 경우, 직접 수사기관에 의뢰하여 주시기 바랍니다. 유링크는 수사기관에서 유효하다고 판단한 법적 협조 요청에 대해 최대한의 노력을 다할 것임을 약속드립니다.",
    "신고된 게시글 및 댓글은 최초 신고가 접수되면 24간 이내 운영진 측에서 신고 사유를 검토 후, 부적절하다고 판단될 경우 삭제됩니다. 신고가 누적된 사용자의 경우 유링크 서비스 이용에 제한을 받을 수 있습니다.",
    "개별 시간표를 대표 시간표로 설정하려면, 우측 상단의 톱니바퀴 이모티콘을 눌러주세요. 하단 팝업창에서 ‘대표 시간표로 설정’을 눌러 해당 시간표를 메인 화면에서 확인할 수 있습니다.",
    "제거했던 공지를 캘린더에 다시 추가하려면 해당 과목의 공지게시판에 들어가서 추가하고 싶은 공지에 ‘추가’를 눌러 본인의캘린더에 다시 추가할 수 있습니다.",
    "유링크 서비스는 대학 측에서 제공하는 수강 편람 데이터를 활용합니다. 학교 별 수강신청 웹사이트와 교무처에서 전달 받은 자료를 바탕으로 수강 편람 데이터를 확보합니다.",
    "현재 유링크 서비스는 학교 이메일을 유일한 인증 수단으로 활용하고 있습니다. 학교 이메일 이외에 재학생 및 졸업생을 증명할 수 있는 다른 방법은 제공하지 않으며, 유링크 서비스 이용을 위해 대학 홈페이지에서 학교 이메일 찾기를 먼저 진행해주시기 바랍니다.",
    "유링크 게시판에서는 학교 별 이니셜이 닉네임 옆에 노출됩니다. 자신의 대학 이니셜을 확인해보고 다른 유저들의 대학 이니셜도 확인해보세요!")

    override fun getGroup(groupPosition: Int): Any {
        return bigList[groupPosition]
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun getGroupView(groupPosition: Int, isExpanded: Boolean, convertView: View?, parent: ViewGroup?): View {
        val majorContent = getGroup(groupPosition) as String
        var view = convertView
        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.item_faq_big, null)
        }
        view!!.findViewById<TextView>(R.id.tv_content).text = majorContent
        return view
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return 1
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        return smallList[groupPosition]
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildView(groupPosition: Int, childPosition: Int, isLastChild: Boolean, convertView: View?, parent: ViewGroup?): View {
        val minorContent = getChild(groupPosition, childPosition) as String
        var view = convertView
        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.item_faq_small, null)
        }
        view!!.findViewById<TextView>(R.id.tv_content).text = minorContent
        return view

    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getGroupCount(): Int {
        return bigList.size
    }
}