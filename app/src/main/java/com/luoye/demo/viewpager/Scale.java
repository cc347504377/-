package com.luoye.demo.viewpager;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by Luoye on 2016/10/19.
 */
class Scale implements ViewPager.PageTransformer{

    @Override
    public void transformPage(View page, float position) {
        int height = page.getHeight();
        int width = page.getWidth();
        if (position <= 0) {
            page.setAlpha(1 - Math.abs(position));
        } else if (position <= 1) {
            page.setAlpha(1-position);
            page.setTranslationX(width*-position);//X位置不变，始终在屏幕中
            page.setScaleX(0.5f+(1-position)/2);
            page.setScaleY(0.5f+(1-position)/2);
        }

    }
}
