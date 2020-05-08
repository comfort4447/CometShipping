package com.sage_ebs.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class Slider_Adapter extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;

    public Slider_Adapter (Context context){
        this.context = context ;

    }
    public int[] slide_images={
            R.drawable.sage2,
            R.drawable.sage1

    };
    public String[] slide_heading = {
            "",

            "SageEBS is a single port community e-commerce platform " +
                    "that promotes inclusive collaboration between customers," +
                    " importers, c&f agents, terminal owners, shipping agencies, " +
                    "ICD operators for exchanging regulated information " +
                    "ranging from port arrival information, cargo-location information, " +
                    "cargo import invoice and terminal delivery processing, vessel operations S.O.F," +
                    " TDR and realtime intermodal moves reporting. " +
                    "Sage EBS is designed with the world's port ecosystem in perspectives " +
                    "and available as a private hosted Saas"
    };



    @Override
    public int getCount() {
        return slide_heading.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (RelativeLayout)object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slider_layout, container,false);

        ImageView slideImage =(ImageView) view.findViewById(R.id.slide_image);
        TextView slideDesc =  (TextView) view.findViewById(R.id.slide_title);


        slideImage.setImageResource(slide_images[position]);
        slideDesc.setText(slide_heading[position]);


        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout)object);
    }
}
