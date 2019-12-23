package com.aqoong.lib.objectflowview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

/**
 * [ObjectFlowViewSample]
 * <p>
 * Class: ObjectFlowView
 * <p>
 * Created by aqoong on 2019-12-23.
 * - Email  : cooldnjsdn@gmail.com
 * - GitHub : https://github.com/aqoong
 * <p>
 * Description:
 */
public class ObjectFlowView extends LinearLayout {
    private final String TAG = getClass().getSimpleName();


    public ObjectFlowView(Context context) {
        this(context, null);
    }

    public ObjectFlowView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        try {
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ObjectFlowView);


        }catch(NullPointerException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            setupView();
        }
    }

    private void setupView(){
        Log.d(TAG, "call setupView()");

    }
}
