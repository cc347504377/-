package com.luoye.demo.viewpager;

import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

/**
 * Created by Luoye on 2016/10/19.
 */
//侧滑放大虚化特效
class Scale implements ViewPager.PageTransformer{

    @Override
    public void transformPage(View page, float position) {
        int width = page.getWidth();
//        Log.i("haha","position: "+position + "view :" + page.toString());
        if (position < -1) {
            page.setAlpha(0);
        } else if (position <= 0) {
            Log.i("haha","position: "+position + "view :" + page.toString());
            page.setAlpha(1 - Math.abs(position));
//            page.setAlpha(1);
            //必须加下面的初始化设置 否则会出现直接跳转到页面白屏
            page.setTranslationX(0);
            page.setScaleX(1);
            page.setScaleY(1);
        } else if (position <= 1) {
            Log.i("haha","position: "+position + "view :" + page.toString());
            page.setAlpha(1 - position);
            page.setTranslationX(width * -position);//X位置不变，始终在屏幕中
            page.setScaleX(0.5f + ((1 - position) / 2));
            page.setScaleY(0.5f + ((1 - position) / 2));

        } else  {
            page.setAlpha(0);
        }

    }
}
