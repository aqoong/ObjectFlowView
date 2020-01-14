package com.aqoong.lib.objectanimationflowview;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by andy on 2020-01-14.
 * <p>
 * email : han.andy@huinno.co
 **/
public class FlowObject {
    private String  text;
    private int     imageRes;
    private int     backgroundColor;
    private int     textColor;

    private enum TYPE{
        TEXT,
        IMAGE
    }
    private TYPE type;
    private Context mContext;

    public FlowObject(Context context, Builder builder){
        this.mContext = context;
        this.text = builder.text;
        this.imageRes = builder.imageRes;
        this.backgroundColor = builder.backgroundColor;
        this.textColor = builder.textColor;
        this.type = builder.type;

    }

//    public FlowObject(String text, String textColor, String backgroundColor){
//        this.text = text;
//        this.textColor = Color.parseColor(textColor);
//        this.backgroundColor = Color.parseColor(backgroundColor);
//        type = TYPE.TEXT;
//    }
//    public FlowObject(int image, String backgroundColor){
//        this.imageRes = image;
//        this.backgroundColor = Color.parseColor(backgroundColor);
//        type = TYPE.IMAGE;
//    }

    public View createFlowObjectView(){
        switch (type){
            case IMAGE:
                ImageView imageView = new ImageView(mContext);

                return imageView;
            case TEXT:
                TextView textView = new TextView(mContext);

                return textView;
            default:
                return null;
        }
    }


    public class Builder{
        String  text;
        int     imageRes;
        int     backgroundColor;
        int     textColor;
        TYPE    type;

        public Builder(String text, String textColor, String backgroundColor){
            this.text = text;
            this.textColor = Color.parseColor(textColor);
            this.backgroundColor = Color.parseColor(backgroundColor);
            type = TYPE.TEXT;
        }
        public Builder(int image, String backgroundColor){
            this.imageRes = image;
            this.backgroundColor = Color.parseColor(backgroundColor);
            type = TYPE.IMAGE;
        }
    }
}
