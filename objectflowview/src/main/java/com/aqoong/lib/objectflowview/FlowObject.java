package com.aqoong.lib.objectflowview;

/**
 * [ObjectFlowViewSample]
 * <p>
 * Class: FlowObject
 * <p>
 * Created by aqoong on 2019-12-23.
 * - Email  : cooldnjsdn@gmail.com
 * - GitHub : https://github.com/aqoong
 * <p>
 * Description:
 */
public class FlowObject {
    public static class ObjectType{
        public static final int TYPE_TEXT = 0;
        public static final int TYPE_EMOTICON = 1;
    }

    private int viewTime;
    private int type;
    private String strText;
    private int imgSrc;


    public int getViewTime() {
        return viewTime;
    }

    public void setViewTime(int viewTime) {
        this.viewTime = viewTime;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getStrText() {
        return strText;
    }

    public void setStrText(String strText) {
        this.strText = strText;
    }

    public int getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(int imgSrc) {
        this.imgSrc = imgSrc;
    }
}
