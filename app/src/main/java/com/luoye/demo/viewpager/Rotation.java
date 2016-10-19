package com.luoye.demo.viewpager;

import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

/**
 * Created by Luoye on 2016/10/19.
 */
class Rotation implements ViewPager.PageTransformer{

    @Override
    public void transformPage(View page, float position) {
        Log.i("Tag","position: "+ position);
        Log.i("Tag", "view: " + page.toString());
        if (position < -1) {
            //其实并没有什么用
        } else if (position <= 0) {
            //左侧图
//                    page.setRotationX(0.5f);
//                    page.setRotationY(0.5f);
            page.setRotation(Math.abs(position)*120);
            page.setAlpha(1-Math.abs(position));
        } else if (position <= 1) {
            //右侧图
            page.setRotation(position*120);
            page.setAlpha(1-position);
        } else if (position > 1) {
            //没卵用
        }
    }
}
