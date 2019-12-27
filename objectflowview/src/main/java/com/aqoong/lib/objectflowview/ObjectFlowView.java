package com.aqoong.lib.objectflowview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.HorizontalScrollView;

import androidx.annotation.Nullable;

import java.util.LinkedList;
import java.util.Queue;

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
public class ObjectFlowView extends HorizontalScrollView {
    private final String TAG = getClass().getSimpleName();

    private FlowObjectManager flowManager;
    private Queue<FlowObjectManager> flowObjectManagerQueue;

    private View contentView;
    private boolean isPlaying = false;

    private int mDuration = 0;
    private float mTextSize;
    private int mTextColor;


    public ObjectFlowView(Context context) {
        this(context, null);
    }

    public ObjectFlowView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        try {
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ObjectFlowView);
            mDuration = ta.getInt(R.styleable.ObjectFlowView_animDuration, 2000);
            mTextSize = ta.getFloat(R.styleable.ObjectFlowView_textSize, 50);
            mTextColor = ta.getColor(R.styleable.ObjectFlowView_textColor, Color.BLACK);
            ta.recycle();
        }catch(NullPointerException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            setupView();
        }
    }

    private ViewTreeObserver.OnGlobalLayoutListener addViewListener = new ViewTreeObserver.OnGlobalLayoutListener() {
        @Override
        public void onGlobalLayout() {
            Rect screenSize = new Rect();
            getWindowVisibleDisplayFrame(screenSize);

            Log.d(TAG, "View width :" + contentView.getMeasuredWidth());

            TranslateAnimation translateAnimation = new TranslateAnimation(
                    screenSize.width(),
                    -contentView.getWidth(),
                    0,
                    0
            );
            translateAnimation.setDuration(flowManager.getFlowObjectList().size() * mDuration);
            translateAnimation.setRepeatCount(Animation.INFINITE);
            translateAnimation.setFillAfter(true);
            translateAnimation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    isPlaying = true;
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    isPlaying = false;
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                    if(!flowObjectManagerQueue.isEmpty()){
                        Log.d(TAG, "change view");
                        isPlaying = false;

                        animation.cancel();
                        setFlowObjectManager(flowObjectManagerQueue.poll());
                        return;
                    }
                }
            });
            contentView.setAnimation(translateAnimation);
        }
    };

    private void setupView(){
        Log.d(TAG, "call setupView()");

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.objectflowview_layout, this);

        flowObjectManagerQueue = new LinkedList<>();
    }

    private void setAnimation(View view){

        view.getViewTreeObserver().removeOnGlobalLayoutListener(addViewListener);
        this.removeAllViews();
        this.addView(view);
        view.getViewTreeObserver().addOnGlobalLayoutListener(addViewListener);
    }


    public void setFlowObjectManager(FlowObjectManager manager){
        Log.d(TAG, "call setFlowObjectManager()");
        if(isPlaying){
            flowObjectManagerQueue.add(manager);
            return;
        }
        this.flowManager = manager;

        contentView = this.flowManager.ConvertObjectToView(mTextSize, mTextColor);
        setAnimation(contentView);

        this.invalidate();
    }

    public FlowObjectManager getFlowObjectManager(){
        return this.flowManager;
    }

    public boolean isPlaying(){return this.isPlaying;}





}
