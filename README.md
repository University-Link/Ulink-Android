# Ulink-Andorid


![슬라이드2](https://user-images.githubusercontent.com/53978090/86901061-0d684f00-c147-11ea-9a83-89c0e900341f.png)

![슬라이드5](https://user-images.githubusercontent.com/53978090/86901042-08a39b00-c147-11ea-93a9-327a0abdcd2b.png)


## :computer: :information_desk_person: Ulink Android 개발자들 :fire:

- [김영민](https://github.com/kym1924)
- [박규희](https://github.com/gch0925)
- [이지은](https://github.com/leejieun1121)

### A-1 ConstraintLayout을 사용한 화면 개발

1. **match_constraint, chain, guideLine 등 constraintLayout의 다양한 속성 활용**

   (match_constratint)를 이용하여 @day_header 밑에서부터 끝까지 지정!

   ~~~
   <androidx.viewpager2.widget.ViewPager2
               android:id="@+id/calendar_viewPager"
               android:layout_width="0dp"
               android:layout_height="0dp"
               app:layout_constraintTop_toBottomOf="@id/day_header"
               app:layout_constraintBottom_toBottomOf="parent"
               app:layout_constraintStart_toStartOf="@id/day_header"
               app:layout_constraintEnd_toEndOf="@id/day_header">
           </androidx.viewpager2.widget.ViewPager2>
   android:layout_height="0dp" 
   ~~~

   

 2. **제약조건은 연관성이 있어야함**

    fragment_calendar
    전체 레이아웃
    ConstraintLayout
    구성 요소

    1. calendar_header (일정 버튼, 년/월 표시, 오늘날짜)
    2. day_header (일, 월, 화, 수, 목, 금, 토, 일)
    3. calendar_viewPager (달력)


    ~~~
    <LinearLayout
                android:id="@+id/day_header"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:paddingHorizontal="10dp"
                android:layout_marginTop="10dp"
                android:orientation="horizontal"
                android:weightSum="7"
                app:layout_constraintTop_toBottomOf="@id/calendar_header"
                app:layout_constraintStart_toStartOf="@id/calendar_header"
                app:layout_constraintEnd_toEndOf="@id/calendar_header">
    <androidx.viewpager2.widget.ViewPager2
                android:id="@+id/calendar_viewPager"
                android:layout_width="0dp"
                android:layout_height="0dp"
                app:layout_constraintTop_toBottomOf="@id/day_header"
                app:layout_constraintBottom_toBottomOf="parent"
                app:layout_constraintStart_toStartOf="@id/day_header"
                app:layout_constraintEnd_toEndOf="@id/day_header">
    </androidx.viewpager2.widget.ViewPager2>
    ~~~
    
    

<img width="308" alt="스크린샷 2020-07-07 오후 11 20 51" src="https://user-images.githubusercontent.com/53978090/86795452-8b294d80-c0a8-11ea-8f91-cfcf7989d36c.png">


3. **width, height 속성에 dp단위 적용은 필요한 경우 아니면 macrh_parent, wrap_content, match_constraint 위주로 사용할 것**

   Activity_chatting.xml	

~~~
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">


    <androidx.drawerlayout.widget.DrawerLayout
        android:id="@+id/drawer_layout_main"
        android:layout_width="match_parent"
        android:layout_height="match_parent">
        <include
            android:layout_height="match_parent"
            android:layout_width="match_parent"
            layout="@layout/activity_main_content" />

        <com.google.android.material.navigation.NavigationView
            android:id="@+id/nav_view"
            android:layout_width="wrap_content"
            android:layout_height="match_parent"
            android:layout_gravity="right"
            app:headerLayout="@layout/nav_header_main"
            app:menu="@menu/navi_menu">

            <androidx.constraintlayout.widget.ConstraintLayout
                xmlns:android="http://schemas.android.com/apk/res/android"
                xmlns:app="http://schemas.android.com/apk/res-auto"
                xmlns:tools="http://schemas.android.com/tools"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_gravity="bottom">
                <View
                    android:id="@+id/view"
                    app:layout_constraintStart_toStartOf="parent"
                    app:layout_constraintTop_toTopOf="parent"
                    android:layout_width="match_parent"
                    android:layout_height="2px"
                    android:background="#4682B4"
                    />

                <TextView
                    android:id="@+id/tv_back"
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:layout_marginLeft="40dp"
                    android:layout_marginVertical="10dp"
                    android:text="나가기"
                    android:textColor="@color/colorAccent"
                    app:layout_constraintStart_toStartOf="parent"
                    app:layout_constraintTop_toTopOf="parent" />

                <ImageButton
                    android:id="@+id/btn_alert"
                    android:layout_marginLeft="60dp"
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:src="@drawable/alert"
                    app:layout_constraintStart_toEndOf="@+id/tv_back"
                    app:layout_constraintTop_toTopOf="parent" />

                <ImageButton
                    android:id="@+id/btn_setting"
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:layout_marginLeft="20dp"
                    android:src="@drawable/settings"
                    app:layout_constraintStart_toEndOf="@+id/btn_alert"
                    app:layout_constraintTop_toTopOf="parent" />
            </androidx.constraintlayout.widget.ConstraintLayout>
        </com.google.android.material.navigation.NavigationView>
    </androidx.drawerlayout.widget.DrawerLayout>


</androidx.constraintlayout.widget.ConstraintLayout>




~~~

<img width="308" alt="스크린샷 2020-07-07 오후 11 12 29" src="https://user-images.githubusercontent.com/53978090/86794939-03dbda00-c0a8-11ea-9674-1a0494e303ab.png">




