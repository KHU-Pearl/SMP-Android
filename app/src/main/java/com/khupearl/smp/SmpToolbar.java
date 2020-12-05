package com.khupearl.smp;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

public class SmpToolbar extends ConstraintLayout {

    private Context context;

    private ImageButton toolbarLeftButton;
    private ImageButton toolbarRightButton;
    private TextView toolbarTitleTextView;
    private TextView toolbarBadgeTextView;

    public SmpToolbar(@NonNull Context context) {
        super(context);
        this.context = context;
        init();
    }

    public SmpToolbar(@NonNull Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public SmpToolbar(@NonNull Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    private void init() {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_smp_toolbar, this, true);
        toolbarLeftButton = view.findViewById(R.id.toolbarLeftButton);
        toolbarRightButton = view.findViewById(R.id.toolbarRightButton);
        toolbarTitleTextView = view.findViewById(R.id.toolbarTitleTextView);
        toolbarBadgeTextView = view.findViewById(R.id.toolbarBadgeTextView);

        toolbarRightButton.setVisibility(View.GONE);
        toolbarTitleTextView.setVisibility(View.INVISIBLE);
        toolbarBadgeTextView.setVisibility(View.INVISIBLE);
    }

    public void setLeftButton(int imageResource, final OnClickListener onClickListener) {
        toolbarLeftButton.setImageResource(imageResource);
        toolbarLeftButton.setOnClickListener(onClickListener);
    }

    public void setTitleTextView(String text) {
        toolbarTitleTextView.setText(text);
        toolbarTitleTextView.setVisibility(View.VISIBLE);
    }

    public void setToolbarBadgeTextView(String text) {
        toolbarBadgeTextView.setText(text);
        toolbarBadgeTextView.setVisibility(View.VISIBLE);
    }

    public void setRightButton(int imageResource, final OnClickListener onClickListener) {
        toolbarRightButton.setImageResource(imageResource);
        toolbarRightButton.setVisibility(View.VISIBLE);
        toolbarRightButton.setOnClickListener(onClickListener);
    }
}
