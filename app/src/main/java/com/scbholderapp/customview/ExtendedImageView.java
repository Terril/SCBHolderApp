package com.scbholderapp.customview;

import android.content.Context;
import android.widget.ImageView;

import static android.view.View.MeasureSpec.getSize;


public class ExtendedImageView extends ImageView {
    public ExtendedImageView(Context context) {
        super(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = getSize(widthMeasureSpec);
        setMeasuredDimension(width, width);
    }
}
