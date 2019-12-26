package com.aqoong.lib.objectflowview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.HorizontalScrollView;

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
public class ObjectFlowView extends HorizontalScrollView {
    private final String TAG = getClass().getSimpleName();

    private FlowObjectManager flowManager;
    private Animation animation;
    private View contentView;
    private boolean isPlaying = false;

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
    }

    private void setAnimation(View view){
        Rect screenSize = new Rect();
        getWindowVisibleDisplayFrame(screenSize);

        this.addView(view);
        animation = AnimationUtils.loadAnimation(getContext(), R.anim.anim_flow);
        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Log.d(TAG, "View width :" + view.getMeasuredWidth());

                TranslateAnimation translateAnimation = new TranslateAnimation(
                        screenSize.width(),
                        -view.getWidth(),
                        0,
                        0
                );
                translateAnimation.setDuration(flowManager.getFlowObjectList().size() * 2000);
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

                    }
                });
                view.setAnimation(translateAnimation);
            }
        });




    }


    public void setFlowObjectManager(FlowObjectManager manager){
        Log.d(TAG, "call setFlowObjectManager()");
        this.flowManager = manager;
        this.removeAllViews();
        contentView = this.flowManager.ConvertObjectToView();
        setAnimation(contentView);


        this.invalidate();
    }

    public FlowObjectManager getFlowObjectManager(){
        return this.flowManager;
    }

    public boolean isPlaying(){return this.isPlaying;}





}
