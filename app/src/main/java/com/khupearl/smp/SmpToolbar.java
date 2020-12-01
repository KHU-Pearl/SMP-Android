package com.khupearl.smp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.khupearl.smp.databinding.LayoutSmpToolbarBinding;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

public class SmpToolbar extends ConstraintLayout {

    private LayoutSmpToolbarBinding binding;
    private Context context;

    public SmpToolbar(@NonNull Context context) {
        super(context);
        this.context = context;
        init();
    }

    private void init() {
        binding = LayoutSmpToolbarBinding.inflate(LayoutInflater.from(context));
        binding.toolbarRightButton.setVisibility(View.GONE);
        binding.toolbarTitleTextView.setVisibility(View.INVISIBLE);
    }

    public void setLeftButton(int imageResource, final OnClickListener onClickListener) {
        binding.toolbarLeftButton.setImageResource(imageResource);
        binding.toolbarLeftButton.setOnClickListener(onClickListener);
    }

    public void setTitleTextView(String text) {
        binding.toolbarTitleTextView.setText(text);
        binding.toolbarTitleTextView.setVisibility(View.VISIBLE);
    }

    public void setRightButton(int imageResource, final OnClickListener onClickListener) {
        binding.toolbarRightButton.setImageResource(imageResource);
        binding.toolbarRightButton.setVisibility(View.VISIBLE);
        binding.toolbarRightButton.setOnClickListener(onClickListener);
    }
}
