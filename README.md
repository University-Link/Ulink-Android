# :purple_heart:  팀 Ulink :purple_heart:

## :clock1: 시간표 기반 대학생 정보교환 플랫폼 

![슬라이드2](https://user-images.githubusercontent.com/53978090/87794101-1a8ce880-c881-11ea-9895-c490fe49f7cd.png)

![슬라이드5](https://user-images.githubusercontent.com/53978090/86901042-08a39b00-c147-11ea-93a9-327a0abdcd2b.png)

---

##  👨‍👨‍👧  Ulink Android 개발자들 github :fire:

- [김영민](https://github.com/kym1924)
- [박규희](https://github.com/gch0925)
- [이지은](https://github.com/leejieun1121)

---

## Android 과제​ :books:

### :pushpin:A-1 ConstraintLayout을 사용한 화면 개발

**1. Match_constraint, chain, guideLine 등 constraintLayout의 다양한 속성 활용**

**item_subject_child.xml**

~~~
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout
    android:id="@+id/layout_cart"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:background="@color/white">
    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:id="@+id/tv_class_name"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintLeft_toLeftOf="parent"
        android:layout_marginTop="15dp"
        android:layout_marginLeft="17dp"
        android:textColor="@color/text1"
        android:textStyle="bold"
        android:text="전자회로 I"/>
    <TextView
        app:layout_constraintTop_toTopOf="@id/tv_class_name"
        app:layout_constraintBottom_toBottomOf="@id/tv_class_name"
        app:layout_constraintLeft_toRightOf="@id/tv_class_name"
        android:id="@+id/tv_professor_name"
        android:layout_marginLeft="6dp"
        android:textSize="12sp"
        android:textColor="#898989"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="박상규"/>
    <androidx.constraintlayout.widget.Guideline
        android:layout_width="1dp"
        android:layout_height="match_parent"
        android:id="@+id/gl_1"
        android:orientation="vertical"
        app:layout_constraintGuide_percent = "0.7"/>
    <TextView
        android:id="@+id/tv_time"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:textSize="12sp"
        android:textColor="#898989"
        app:layout_constraintTop_toBottomOf="@id/tv_class_name"
        app:layout_constraintLeft_toLeftOf="@id/tv_class_name"/>
    <TextView
        android:id="@+id/tv_place"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_marginBottom="14dp"
        app:layout_constraintTop_toBottomOf="@id/tv_time"
        app:layout_constraintLeft_toLeftOf="@id/tv_class_name"
        app:layout_constraintRight_toLeftOf="@id/gl_1"
        android:textSize="12sp"
        android:textColor="#898989" />
    <TextView
        android:id="@+id/tv_category"
        app:layout_constraintLeft_toRightOf="@id/tv_place"
        app:layout_constraintTop_toTopOf="@id/tv_place"
        android:layout_width="wrap_content"
        android:text="전공선택"
        android:textSize="12sp"
        android:layout_marginLeft="6dp"
        android:textColor="#898989"
        android:layout_height="wrap_content"/>
    <TextView
        android:id="@+id/tv_credit"
        app:layout_constraintLeft_toRightOf="@id/tv_category"
        app:layout_constraintTop_toTopOf="@id/tv_category"
        android:layout_width="wrap_content"
        android:text="3학점"
        android:textColor="#898989"
        android:textSize="12sp"
        android:layout_marginLeft="6dp"
        android:layout_height="wrap_content"/>
    <Button
        android:id="@+id/btn_assess"
        android:background="@drawable/timetableadd_filterandsearch_touch_btn_star"
        android:layout_width="64dp"
        android:layout_height="24dp"
        android:layout_marginTop="9dp"
        android:layout_marginBottom="12dp"
        app:layout_constraintLeft_toLeftOf="@id/tv_place"
        app:layout_constraintTop_toBottomOf="@id/tv_place"
        app:layout_constraintBottom_toBottomOf="parent"
        android:visibility="gone"/>
    <Button
        android:id="@+id/btn_cart"
        android:background="@drawable/timetableadd_filterandsearch_btn_cart"
        android:layout_width="82dp"
        android:layout_height="24dp"
        android:layout_marginLeft="4dp"
        app:layout_constraintTop_toTopOf="@id/btn_assess"
        app:layout_constraintBottom_toBottomOf="@id/btn_assess"
        app:layout_constraintLeft_toRightOf="@id/btn_assess"
        android:visibility="gone"/>
    <Button
        android:id="@+id/btn_totable"
        android:layout_width="82dp"
        android:layout_height="24dp"
        android:layout_marginRight="14dp"
        android:background="@drawable/timetableadd_filterandsearch_touch_btn_add"
        app:layout_constraintTop_toTopOf="@id/btn_assess"
        app:layout_constraintBottom_toBottomOf="@id/btn_assess"
        app:layout_constraintRight_toRightOf="parent"
        android:visibility="gone"/>
    <Button
        android:id="@+id/btn_delete"
        android:background="@drawable/btn_cart_delete"
        android:layout_width="57dp"
        android:layout_height="24dp"
        android:layout_marginLeft="4dp"
        app:layout_constraintTop_toTopOf="@id/btn_assess"
        app:layout_constraintBottom_toBottomOf="@id/btn_assess"
        app:layout_constraintLeft_toRightOf="@id/btn_assess"
        android:visibility="gone"/>
    <TextView
        android:id="@+id/tv_classnumber"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="34234234-342"
        android:textColor="#727272"
        android:layout_marginRight="15dp"
        app:layout_constraintTop_toTopOf="@id/tv_class_name"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintBottom_toBottomOf="@id/tv_class_name"/>
    <View
        android:layout_width="0dp"
        android:layout_height="1dp"
        android:background="@color/line"
        android:layout_marginTop="10dp"
        android:layout_marginHorizontal="5dp"
        app:layout_constraintTop_toBottomOf="@id/btn_assess"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"/>
</androidx.constraintlayout.widget.ConstraintLayout>
~~~

장소가 길어지면 카테고리와 너무 가까워지는것을 방지하기 위해서 guideline적용

<img width="193" alt="스크린샷 2020-07-17 오후 9 53 57" src="https://user-images.githubusercontent.com/53978090/87793444-4065bd80-c880-11ea-981d-4e6cb47a109c.png">

<img width="327" alt="스크린샷 2020-07-17 오후 9 52 41" src="https://user-images.githubusercontent.com/53978090/87793525-5d01f580-c880-11ea-997c-77a8f0dce7fa.png">

**2. 제약조건은 연관성이 있어야 함**

**activity_notice.xml**

~~~
<?xml version="1.0" encoding="UTF-8"?>

<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:background="@color/white"
    tools:context=".NoticeMoreActivity">

    <include
        android:id="@+id/toolbar_notice"
        layout="@layout/toolbar_notice" />

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="0dp"
        android:layout_marginTop="10.5dp"
        android:layout_marginBottom="10.5dp"
        android:layout_marginHorizontal="16dp"
        app:layout_constraintStart_toStartOf="@id/toolbar_notice"
        app:layout_constraintTop_toBottomOf="@id/toolbar_notice"
        app:layout_constraintBottom_toBottomOf="parent"
        android:orientation="vertical">

        <androidx.constraintlayout.widget.ConstraintLayout
            android:id="@+id/layout_task_notice"
            android:layout_width="match_parent"
            android:layout_height="200dp"
            android:layout_weight="1"
            android:paddingBottom="18dp"
            android:background="@drawable/chatting_notice_bg_hw">

            <TextView
                android:id="@+id/tv_task_notice"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="과제공지"
                android:textColor="#e64b87"
                android:textSize="18sp"
                android:layout_marginTop="15dp"
                android:layout_marginLeft="15dp"
                app:layout_constraintTop_toTopOf="parent"
                app:layout_constraintStart_toStartOf="parent"/>

            <TextView
                android:id="@+id/tv_task_notice_more"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_marginRight="6dp"
                android:paddingHorizontal="12dp"
                android:paddingVertical="9dp"
                android:text="더보기"
                android:visibility="invisible"
                android:textColor="@color/text1"
                android:textSize="12sp"
                app:layout_constraintBottom_toBottomOf="@+id/tv_task_notice"
                app:layout_constraintEnd_toEndOf="parent"
                app:layout_constraintTop_toTopOf="@+id/tv_task_notice" />

            <androidx.recyclerview.widget.RecyclerView
                android:id="@+id/rv_task_notice"
                android:layout_width="match_parent"
                android:layout_height="0dp"
                android:layout_marginTop="13dp"
                android:layout_marginLeft="15dp"
                android:clipToPadding = "true"
                app:layoutManager="androidx.recyclerview.widget.LinearLayoutManager"
                tools:listitem="@layout/item_notice"
                app:layout_constraintTop_toBottomOf="@id/tv_task_notice"
                app:layout_constraintBottom_toBottomOf="@id/layout_task_notice"
                app:layout_constraintStart_toStartOf="@id/tv_task_notice" />

            <TextView
                android:id="@+id/tv_task_notice_empty"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="등록된 공지가 없습니다."
                android:textColor="#c9c9c9"
                android:textSize="20sp"
                app:layout_constraintTop_toTopOf="parent"
                app:layout_constraintBottom_toBottomOf="parent"
                app:layout_constraintStart_toStartOf="parent"
                app:layout_constraintEnd_toEndOf="parent"/>

        </androidx.constraintlayout.widget.ConstraintLayout>

        <androidx.constraintlayout.widget.ConstraintLayout
            android:id="@+id/layout_test_notice"
            android:layout_width="match_parent"
            android:layout_height="200dp"
            android:layout_weight="1"
            android:layout_marginTop="10dp"
            android:background="@drawable/chatting_notice_bg_test">

            <TextView
                android:id="@+id/tv_test_notice"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="시험공지"
                android:textColor="#8f43e5"
                android:textSize="18sp"
                android:layout_marginTop="15dp"
                android:layout_marginLeft="15dp"
                app:layout_constraintTop_toTopOf="parent"
                app:layout_constraintStart_toStartOf="parent"/>

            <TextView
                android:id="@+id/tv_test_notice_more"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:paddingHorizontal="12dp"
                android:paddingVertical="9dp"
                android:text="더보기"
                android:visibility="invisible"
                android:textColor="@color/text1"
                android:textSize="12sp"
                android:layout_marginRight="6dp"
                app:layout_constraintBottom_toBottomOf="@+id/tv_test_notice"
                app:layout_constraintEnd_toEndOf="parent"
                app:layout_constraintTop_toTopOf="@+id/tv_test_notice" />

            <androidx.recyclerview.widget.RecyclerView
                android:id="@+id/rv_test_notice"
                android:layout_width="match_parent"
                android:layout_height="0dp"
                android:layout_marginTop="13dp"
                android:layout_marginBottom="18dp"
                android:layout_marginLeft="15dp"
                android:clipToPadding = "true"
                android:visibility="invisible"
                app:layoutManager="androidx.recyclerview.widget.LinearLayoutManager"
                tools:listitem="@layout/item_notice"
                app:layout_constraintTop_toBottomOf="@id/tv_test_notice"
                app:layout_constraintBottom_toBottomOf="@id/layout_test_notice"
                app:layout_constraintStart_toStartOf="@id/tv_test_notice" />

            <TextView
                android:id="@+id/tv_test_notice_empty"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="등록된 공지가 없습니다."
                android:textColor="#c9c9c9"
                android:textSize="20sp"
                app:layout_constraintTop_toTopOf="parent"
                app:layout_constraintBottom_toBottomOf="parent"
                app:layout_constraintStart_toStartOf="parent"
                app:layout_constraintEnd_toEndOf="parent" />

        </androidx.constraintlayout.widget.ConstraintLayout>


        <androidx.constraintlayout.widget.ConstraintLayout
            android:id="@+id/layout_class_notice"
            android:layout_width="match_parent"
            android:layout_height="200dp"
            android:layout_weight="1"
            android:layout_marginTop="10dp"
            android:paddingBottom="18dp"
            android:background="@drawable/chatting_notice_bg_class">

            <TextView
                android:id="@+id/tv_class_notice"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="수업공지"
                android:textColor="#13afc3"
                android:textSize="18sp"
                android:layout_marginTop="15dp"
                android:layout_marginLeft="15dp"
                app:layout_constraintTop_toTopOf="parent"
                app:layout_constraintStart_toStartOf="parent"/>

            <TextView
                android:id="@+id/tv_class_notice_more"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_marginRight="6dp"
                android:paddingHorizontal="12dp"
                android:paddingVertical="9dp"
                android:text="더보기"
                android:visibility="invisible"
                android:textColor="@color/text1"
                android:textSize="12sp"
                app:layout_constraintBottom_toBottomOf="@+id/tv_class_notice"
                app:layout_constraintEnd_toEndOf="parent"
                app:layout_constraintTop_toTopOf="@id/tv_class_notice" />

            <androidx.recyclerview.widget.RecyclerView
                android:id="@+id/rv_class_notice"
                android:layout_width="match_parent"
                android:layout_height="0dp"
                android:layout_marginTop="13dp"
                android:layout_marginLeft="15dp"
                android:clipToPadding = "true"
                android:visibility="invisible"
                app:layoutManager="androidx.recyclerview.widget.LinearLayoutManager"
                tools:listitem="@layout/item_notice"
                app:layout_constraintTop_toBottomOf="@id/tv_class_notice"
                app:layout_constraintBottom_toBottomOf="@id/layout_class_notice"
                app:layout_constraintStart_toStartOf="@id/tv_class_notice" />

            <TextView
                android:id="@+id/tv_class_notice_empty"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="등록된 공지가 없습니다."
                android:textColor="#c9c9c9"
                android:textSize="20sp"
                app:layout_constraintTop_toTopOf="parent"
                app:layout_constraintBottom_toBottomOf="parent"
                app:layout_constraintStart_toStartOf="parent"
                app:layout_constraintEnd_toEndOf="parent"/>
        </androidx.constraintlayout.widget.ConstraintLayout>
    </LinearLayout>
</androidx.constraintlayout.widget.ConstraintLayout>
~~~

<img width="163" alt="스크린샷 2020-07-17 오후 10 02 59" src="https://user-images.githubusercontent.com/53978090/87793549-6723f400-c880-11ea-994a-02e8176d39cc.png">

**3. width, height 속성에 dp 단위 적용은 필요한 경우 아니면 match_parent, wrap_content, match_constraint 위주로 사용할것**

**activity_schedule.xml**

~~~
<?xml version="1.0" encoding="UTF-8"?>

<androidx.constraintlayout.widget.ConstraintLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="@drawable/calendar_img_bg_under"
    tools:context=".ScheduleActivity">

    <include
        android:id="@+id/toolbar_schedule"
        layout="@layout/toolbar_schedule" />

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="0dp"
        android:layout_marginTop="10.5dp"
        android:background="@drawable/white_background"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@id/toolbar_schedule">

        <androidx.recyclerview.widget.RecyclerView
            android:id="@+id/rv_schedule_date"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:paddingTop="15dp"
            app:layoutManager="androidx.recyclerview.widget.LinearLayoutManager"
            tools:listitem="@layout/schedule_day_item" />

    </LinearLayout>

</androidx.constraintlayout.widget.ConstraintLayout>
~~~

<img width="163" alt="스크린샷 2020-07-17 오후 10 10 00" src="https://user-images.githubusercontent.com/53978090/87793584-6e4b0200-c880-11ea-8614-7a7303b780b0.png">



---



### :pushpin:A-2 코틀린으로 안드로이드 앱 개발

**1. kotlin collection 의 확장함수 사용**

~~~
subjectList.retainAll { !it.isSample }
        subjectList.sortBy {formatToFloat(it.startTime[0])}
        for (a in 0 until subjectList.size) {
            if (a == 0) {
                subjectstarttime = formatToFloat(subjectList[a].startTime[0]) * 4
                presubjectendtimeortablestarttime = formatToFloat(timeTable.startTime) * 4
            } else {
                subjectstarttime = formatToFloat(subjectList[a].startTime[0]) * 4
                presubjectendtimeortablestarttime = formatToFloat((subjectList[a - 1].endTime[0])) * 4
            }
            DrawDummy(linearLayout, subjectstarttime - presubjectendtimeortablestarttime)
            DrawSubject(linearLayout, subjectList[a])
        }

~~~

시간표 과목중에서 시간표에 진짜 표시할 과목과 미리보기로 보여질 과목을 구분하기 위해서 retainAll {!it.isSample}사용

시간순으로 정렬하기위해 sortBy{formatToFloat(it.startTime[0])}사용

**2. custom 확장 함수 사용**

~~~
fun Button.textResetButton(ed : EditText) {
    this.visibility = View.INVISIBLE
    this.setOnClickListener(){
        if (ed.text.toString() != null) ed.setText("")
    }
    ed.textChangedListener{
        if(ed.text.isNullOrBlank()) this.visibility = View.INVISIBLE
        else this.visibility = View.VISIBLE
    }
}
~~~

텍스트가 입력되면 우측 x 버튼이 생겼다가 누르면 텍스트가 삭제, 그리고 텍스트가 공백이면 x버튼이 사라지는 기능을 custom확장함수로 구현

![ulink1](https://user-images.githubusercontent.com/53978090/87793484-4cea1600-c880-11ea-8af2-c19119748a80.gif)



---



### :pushpin:A-3 

**1. 프로젝트 사용 라이브러리**

**Retrofit라이브러리**

implementation 'com.squareup.retrofit2:retrofit:2.6.2'

implementation 'androidx.recyclerview:recyclerview:1.1.0'

implementation 'com.squareup.retrofit2:retrofit-mock:2.6.2'

implementation 'com.squareup.retrofit2:converter-gson:2.4.0'

**객체 시리얼라이즈를 위한 *Gson* 라이브러리**

implementation 'com.google.code.gson:gson:2.8.6'

***Retrofit*에서 *Gson*을 사용하기 위한 라이브러리**

implementation 'com.squareup.retrofit2:converter-gson:2.6.2'

**이미지 로딩 라이브러리**

implementation "com.github.bumptech.glide:glide:4.10.0"

kapt "com.github.bumptech.glide:compiler:4.10.0"

**동그란 이미지뷰**

implementation 'de.hdodenhof:circleimageview:3.1.0'

implementation 'com.google.android.material:material:1.2.0-alpha02'

**BottomSheet**

implementation 'com.android.support:design:28.0.0'

**viewpager2**

implementation 'androidx.viewpager2:viewpager2:1.0.0'

**splash 애니메이션**

implementation 'com.airbnb.android:lottie:3.0.0'



**2.프로그램 구조 - package 분류 이미지**


<img width="356" alt="스크린샷 2020-07-17 오후 10 15 01" src="https://user-images.githubusercontent.com/53978090/87793619-79059700-c880-11ea-96e3-ee7e712f2d76.png">

- **Activity** 

  Activity끼리 묶어놓기

- **Recycler**

  Data,ViewHolder,Adapter묶어놓기

- **Fragment**

  fragment끼리 묶어놓기

- **repository**

  서버통신

- **tiemtable**

  시간표부분 다 묶어놓기
  

**3. 핵심 기능 구현 방법**

**:fire:워크플로우**:fire:

<img width="989" alt="스크린샷 2020-07-17 오후 10 24 22" src="https://user-images.githubusercontent.com/53978090/87793713-92a6de80-c880-11ea-9dc1-f0a2b71711fa.png">


<img width="1004" alt="스크린샷 2020-07-17 오후 10 24 44" src="https://user-images.githubusercontent.com/53978090/87793743-9dfa0a00-c880-11ea-82b6-37833685feb0.png">


<img width="832" alt="스크린샷 2020-07-17 오후 10 25 06" src="https://user-images.githubusercontent.com/53978090/87793756-a3575480-c880-11ea-8612-cc93770407de.png">


* **splash** 

  lottieAnimationView에 json 파일 적용

* **시간표**

  클릭 이벤트를 감지하여 과목을 작성할 수 있는 기능 구현

  시간표를 작성하면 해당 시간표를 수강하는 학생들끼리 채팅방을 형성(서버 통신+ RecyclerView)

* **캘린더**

  매달 1일의 인덱스를 구해서 그 달의 일 수를 찍어주고, 전 후 달력을 그려주는 방식 (ConstraintLayout+LinearLayout)

- **공지** 

  공지를 올리면 다른사람도 공지를 함께 볼 수 있음

  -> 해당 공지 날짜별로 D-day를 체크해서 얼마 남지않은 날 순서대로 공지리스트를 보여줌(카테고리별로 표시를 다르게 해줬음)

- **커스터마이징**

  BottomSheet + ViewPager + Indicator 를 사용하여 시간표 색을 커스텀할 수 있게 구현

---

