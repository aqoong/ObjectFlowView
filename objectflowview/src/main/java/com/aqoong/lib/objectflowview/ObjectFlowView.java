package com.aqoong.lib.objectflowview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;

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

    private FlowObjectManager flowManager;
    private Animation animation;
    private View contentView;

    public ObjectFlowView(Context context) {
        this(context, null);
    }

    public ObjectFlowView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        try {
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ObjectFlowView);

            ta.recycle();


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

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.objectflowview_layout, this);

        setAnimation();
    }


    public void setFlowObjectManager(FlowObjectManager manager){
        Log.d(TAG, "call setFlowObjectManager()");
        this.flowManager = manager;
        this.removeAllViews();
        contentView = this.flowManager.ConvertObjectToView();
        this.addView(contentView);

        this.invalidate();

        this.startAnimation(animation);

    }


    private void setAnimation(){
        animation = AnimationUtils.loadAnimation(getContext(), R.anim.anim_flow);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        this.setAnimation(animation);
    }

}
