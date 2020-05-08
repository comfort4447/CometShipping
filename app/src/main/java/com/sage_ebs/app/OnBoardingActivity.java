package com.sage_ebs.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class OnBoardingActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private LinearLayout linearLayout;
    private Slider_Adapter slider_adapter;
    private TextView[] mDots;
    private Button btn1,btn2;
    private int mCurrentPage;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);

        btn1 = findViewById(R.id.btn_back);
        btn2 = findViewById(R.id.btn_next);
        viewPager = findViewById(R.id.view_pager);
        linearLayout = findViewById(R.id.linear_layout);
        slider_adapter = new Slider_Adapter(this);
        mAuth = FirebaseAuth.getInstance();

        viewPager.setAdapter(slider_adapter);
        addDotIndicator(0);
        viewPager.addOnPageChangeListener(viewListner);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(mCurrentPage + 1);
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
        btn1.setOnClickListener(
                new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(mCurrentPage - 1);
            }
        });
    }

    @Override
    public void onStart(){
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
         if (currentUser!= null){
             startActivity(new Intent(getApplicationContext(), TryThisActivity.class));
         }
    }
    public void addDotIndicator(int position){
        mDots = new TextView[2];
        linearLayout.removeAllViews();
        int i;
        for (i = 0; i < mDots.length; i++){
            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226:"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            linearLayout.addView(mDots[i]);
        }
        if (mDots.length>0){
            mDots[position].setTextColor(getResources().getColor(R.color.colorPrimary));
        }
    }
   ViewPager.OnPageChangeListener viewListner = new ViewPager.OnPageChangeListener() {
       @Override
       public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
       }

       @Override
       public void onPageSelected(int i) {
           addDotIndicator(i);

           mCurrentPage = i;
           if (i==0){
               btn2.setEnabled(false);
               btn1.setEnabled(false);
               btn1.setVisibility(View.INVISIBLE);
               btn2.setText("");
               btn1.setText("");
           }else if (i==mDots.length-1){
               btn2.setEnabled(true);
               btn1.setEnabled(true);
               btn1.setVisibility(View.VISIBLE);
               btn2.setText("CONTINUE");
               btn1.setText("Back");
           }else {
               btn2.setEnabled(false);
               btn1.setEnabled(true);
               btn2.setVisibility(View.INVISIBLE);
               btn1.setVisibility(View.VISIBLE);
               btn2.setText("Next");
               btn1.setText("Back");
           }
       }

       @Override
       public void onPageScrollStateChanged(int state) {
       }
   };
}
