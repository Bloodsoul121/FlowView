package com.example.bloodsoul.flowingview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewTreeObserver;

import com.example.bloodsoul.flowingview.sweepview.IOnAnimEndListener;
import com.example.bloodsoul.flowingview.sweepview.RotationSweepView;

public class SweepActivity
        extends AppCompatActivity
{

    private RotationSweepView rotationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sweep);

        int width = getMetricsWidth(this)*7/5;

        rotationView = (RotationSweepView) findViewById(R.id.rotationView);
        rotationView.setIOnAnimEndListener(new IOnAnimEndListener() {
            @Override
            public void onAnimEnd() {
                animStart();
            }
        });
        rotationView.setDistanceXYR(0,0.35f*width,0.3f*width,0.11f*width);
        rotationView.setDistanceXYR(1,0.75f*width,0.32f*width,0.105f*width);
        rotationView.setDistanceXYR(2,0.25f*width,0.57f*width,0.14f*width);
        rotationView.setDistanceXYR(3,0.68f*width,0.75f*width,0.12f*width);
        rotationView.setDistanceXYR(4,0.42f*width,0.8f*width,0.1f*width);
        rotationView.setDistanceXYR(5,0.57f*width,0.5f*width,0.13f*width);
        rotationView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int width = rotationView.getWidth();
                int height = rotationView.getHeight();
                rotationView.setCenterXY(width/2,height/2);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    rotationView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            }
        });
    }

    private void animStart(){
        ValueAnimator animator = ValueAnimator.ofFloat(255, 0);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                rotationView.setAlpha((Float) animation.getAnimatedValue());
                if(((Float) animation.getAnimatedValue())==0){
                    rotationView.setVisibility(View.GONE);
                }
            }
        });
        animator.setDuration(500);
        animator.start();
    }

    public static int getMetricsWidth(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.widthPixels;
    }

}
