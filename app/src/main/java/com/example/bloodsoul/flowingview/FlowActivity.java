package com.example.bloodsoul.flowingview;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.bloodsoul.flowingview.flow.ContainerView;
import com.example.bloodsoul.flowingview.flow.PreferenceFloatingDrawer;

public class FlowActivity
        extends AppCompatActivity
{

    private ContainerView containerView;
    
    private Button        mBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flow);

        mBtn = (Button) findViewById(R.id.btn);

        containerView = (ContainerView) findViewById(R.id.floatingView);
        ViewGroup.LayoutParams params = containerView.getLayoutParams();
        params.width = getMetricsWidth(this)* 7/5;
        containerView.setLayoutParams(params);
        containerView.setDrawer(new PreferenceFloatingDrawer(this));
    }

    @Override
    protected void onResume() {
        super.onResume();
        containerView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        containerView.onPause();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        containerView.onDestroy();
    }

    public static int getMetricsWidth(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.widthPixels;
    }

    public void clickBtn(View view) {
        containerView.start();
        containerView.setDrawerStop(false);
        mBtn.setVisibility(View.GONE);
    }

}
