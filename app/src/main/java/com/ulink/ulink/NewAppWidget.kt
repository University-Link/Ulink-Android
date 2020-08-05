package com.ulink.ulink

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import android.content.ComponentName
import android.view.LayoutInflater

import com.ulink.ulink.fragment.TimeTableFragment
import com.ulink.ulink.repository.Subject
import com.ulink.ulink.repository.TimeTable
import com.ulink.ulink.timetable.TimeTableDrawer
import com.ulink.ulink.timetable.TimeTableDrawerWidget


/**
 * Implementation of App Widget functionality.
 * App Widget Configuration implemented in [NewAppWidgetConfigureActivity]
 */
class NewAppWidget : AppWidgetProvider() {


    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        // AppWidgetProviderInfo의 updatePeriodMillis 속성에 의해 정의된 간격으로 앱 위젯을 업데이트하기 위해 호출
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onDeleted(context: Context, appWidgetIds: IntArray) {
        // 앱 위젯이 앱 위젯 호스트에서 삭제될 때마다 호출
        for (appWidgetId in appWidgetIds) {
            deleteTitlePref(context, appWidgetId)
        }
    }
    override fun onEnabled(context: Context) {
        // 앱 위젯의 인스턴스가 처음으로 생성될 때 호출


    }

    override fun onDisabled(context: Context) {
        // 앱 위젯의 마지막 인스턴스가 앱 위젯 호스트에서 삭제될 때 호출
    }
}

internal fun updateAppWidget(context: Context, appWidgetManager: AppWidgetManager, appWidgetId: Int) {
    // val widgetText = loadTitlePref(context, appWidgetId)

    val views = RemoteViews(context.packageName, R.layout.new_app_widget)
    //views.setTextViewText(R.id.appwidget_text, widgetText)

    val timeTable =  TimeTable(id=185, semester="2020-1", name="hfh", isMain=1, startTime="09:00",endTime="20:30", subjectList= mutableListOf(Subject(id=293, name="스터디", startTime= listOf("09:00"), endTime= listOf("10:00"), day= listOf(0), place= listOf(""), color=4, subject=false, credit=0.0f, professor="", course="", isSample=false, number="", subjectIdx=0),
            Subject(id=293, name="스터디", startTime= listOf("11:00"), endTime= listOf("15:00"), day= listOf(0), place= listOf(""), color=4, subject=false, credit=0.0f, professor="", course="", isSample=false, number="", subjectIdx=0)))


    val drawer = TimeTableDrawerWidget(context, LayoutInflater.from(context),timeTable)
    drawer.setMinMax()
    drawer.drawForWidget(views)


    //위젯 누르면 시간표로 이
    val intent = Intent(Intent.ACTION_VIEW)
    //intent.addCategory(Intent.CATEGORY_LAUNCHER)
    intent.component = ComponentName(context,"com.ulink.ulink.MainActivity")
    val pi = PendingIntent.getActivity(context,0,intent,0)
    views.setOnClickPendingIntent(R.id.layout_timetablewhole,pi)
    appWidgetManager.updateAppWidget(appWidgetId, views)

}
