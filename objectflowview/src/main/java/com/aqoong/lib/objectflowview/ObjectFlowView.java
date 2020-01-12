package com.aqoong.lib.objectflowview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
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


    private View parentView; //ScrollView
    private View contentView;
    private boolean isPlaying = false;

    private long mDuration = 0;
    private float mTextSize = 0;
    private int mTextColor = -1;
    private int mObjectInterval = 0;


    public ObjectFlowView(Context context) {
        this(context, null);
    }

    public ObjectFlowView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        try {
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ObjectFlowView);

            setAnimationDuration(ta.getInt(R.styleable.ObjectFlowView_animDuration, 2000));
            setTextSize(ta.getDimension(R.styleable.ObjectFlowView_textSize, 30));
            setTextColor(ta.getString(R.styleable.ObjectFlowView_textColor));
            if(mTextColor == -1){
                setTextColor(ta.getColor(R.styleable.ObjectFlowView_textColor, Color.BLACK));
            }
            setObjectInterval(ta.getInt(R.styleable.ObjectFlowView_objectInterval, 30));

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

            TranslateAnimation translateAnimation = new TranslateAnimation(
                    parentView.getWidth(),
                    -contentView.getWidth(),
                    0,
                    0
            );
            translateAnimation.setDuration(flowManager.getFlowObjectList().size() * mDuration);
            translateAnimation.setRepeatCount(Animation.INFINITE);
            translateAnimation.setInterpolator(new LinearInterpolator());
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
            if(!isPlaying) {
                contentView.setAnimation(translateAnimation);
            }
        }
    };

    private void setupView(){
        Log.d(TAG, "call setupView()");
        removeAllViews();

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        parentView = inflater.inflate(R.layout.objectflowview_layout, this);

        flowObjectManagerQueue = new LinkedList<>();
    }
    public void setAnimationDuration(long duration){
        this.mDuration = duration;
        setupView();
    }

    public void setTextColor(int color){
        this.mTextColor = color;
        setupView();
    }
    public void setTextColor(String color){
        this.mTextColor = Color.parseColor(color);
        setupView();
    }
    public void setTextSize(float size){
        this.mTextSize = size;
        setupView();
    }
    public void setObjectInterval(int interval) {
        this.mObjectInterval = interval;
        setupView();
    }

    public void setTextSetting(String color, float size){
        setTextSetting(Color.parseColor(color), size);
    }
    public void setTextSetting(int color, float size){
        this.mTextColor = color;
        this.mTextSize = size;

        setupView();
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

        this.removeAllViews();
        clearAnimation();
        contentView = this.flowManager.ConvertObjectToView(mTextSize, mTextColor, mObjectInterval);
        setAnimation(contentView);

        this.invalidate();
    }

    public FlowObjectManager getFlowObjectManager(){
        return this.flowManager;
    }

    public boolean isPlaying(){return this.isPlaying;}

}
