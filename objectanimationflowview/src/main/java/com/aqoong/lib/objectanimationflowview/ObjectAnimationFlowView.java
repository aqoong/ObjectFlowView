package com.aqoong.lib.objectanimationflowview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

/**
 * Created by andy on 2020-01-14.
 * <p>
 * email : han.andy@huinno.co
 **/
public class ObjectAnimationFlowView extends LinearLayout {
    private final String TAG = getClass().getSimpleName();

    private float   mTextSize;
    private int     mItemDuration;      //개체당 애니메이션 시간
    private int     mImageStopDuration; //이미지 정지 애니메이션 시간


    public ObjectAnimationFlowView(Context context) {
        this(context, null);
    }

    public ObjectAnimationFlowView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ObjectAnimationFlowView);
        try{
            mItemDuration = ta.getInt(R.styleable.ObjectAnimationFlowView_itemDuration, 3000);
            mTextSize = ta.getDimension(R.styleable.ObjectAnimationFlowView_textSize, 30);
            mImageStopDuration = ta.getInt(R.styleable.ObjectAnimationFlowView_imageStopDuration, 2000);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            setup();
        }

    }

    private void setup(){

    }
}
