package com.aqoong.lib.objectflowview;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.widget.TextViewCompat;

import java.util.ArrayList;

/**
 * [ObjectFlowViewSample]
 * <p>
 * Class: FlowObjectManager
 * <p>
 * Created by aqoong on 2019-12-23.
 * - Email  : cooldnjsdn@gmail.com
 * - GitHub : https://github.com/aqoong
 * <p>
 * Description:
 */
public class FlowObjectManager {
    private final String TAG = getClass().getSimpleName();

    private Context mContext;
    private ArrayList<FlowObject> objectList;


    public FlowObjectManager(Context context, FlowObject... objects){
        this.mContext = context;
        this.objectList = new ArrayList<>();

        for(FlowObject obj : objects){
            objectList.add(obj);
        }

        Log.d(TAG, "init FlowObjectManager / add object size : " + objects.length);

    }

    public View ConvertObjectToView(float textSize, int textColor){
        LinearLayout.LayoutParams commonParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        commonParams.setMarginEnd(20);
        commonParams.gravity = Gravity.CENTER_VERTICAL;

        LinearLayout resultView = new LinearLayout(mContext);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        resultView.setLayoutParams(params);
        resultView.setOrientation(LinearLayout.HORIZONTAL);

        for(FlowObject object : objectList){
            if(object.getType() == FlowObject.ObjectType.TYPE_TEXT){
                AppCompatTextView textView = new AppCompatTextView(mContext);
                textView.setLayoutParams(commonParams);
                textView.setText(object.getStrText());
                textView.setTextColor(textColor);
                textView.setTextSize(textSize);
                resultView.addView(textView);
            }else{
                ImageView imageView = new ImageView(mContext);
                imageView.setLayoutParams(commonParams);
                imageView.setImageResource(object.getImgSrc());
                resultView.addView(imageView);
            }

        }

        return resultView;
    }

    public ArrayList<FlowObject> getFlowObjectList(){
        return this.objectList;
    }

    public void addFlowObject(FlowObject object){
        if(this.objectList == null) {
            this.objectList = new ArrayList<>();
        }
        this.objectList.add(object);
    }

}
