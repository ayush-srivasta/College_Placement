package com.example.collegeplacement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

public class Splash_screen extends AppCompatActivity {
    private ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        image=findViewById(R.id.imageView);
        image.clearAnimation();
        TranslateAnimation transAnim = new TranslateAnimation(0, 0, 0,
                getDisplayHeight()/2);
        transAnim.setStartOffset(500);
        transAnim.setDuration(3000);
        transAnim.setFillAfter(true);
        transAnim.setInterpolator(new BounceInterpolator());
        transAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                image.clearAnimation();
                final int left = image.getLeft();
                final int top = image.getTop();
                final int right = image.getRight();
                final int bottom = image.getBottom();
                image.layout(left, top, right, bottom);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        image.startAnimation(transAnim);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(Splash_screen.this,Login_form.class);
                startActivity(intent);
                finish();
            }
        },3000);
    }

    private float getDisplayHeight() {
        return this.getResources().getDisplayMetrics().heightPixels;
    }
}