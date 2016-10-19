package com.luoye.demo.viewpager;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private float px;
    @BindView(R.id.viewpager)
    ViewPager viewpager;

    @BindDrawable(R.drawable.view1)
    Drawable drawable1;

    @BindDrawable(R.drawable.view2)
    Drawable drawable2;

    @BindDrawable(R.drawable.view3)
    Drawable drawable3;
    @BindView(R.id.point)
    TextView point;
    private ImageView imageView;
    private List<View> datas;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    viewpager.setCurrentItem(msg.arg1);
                    break;
                default:
                    return;
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        px = getResources().getDimension(R.dimen.pointmargin);
        ButterKnife.bind(this);
        datas = new ArrayList<>();
        addview(drawable1);
        addview(drawable2);
        addview(drawable3);
        viewpager.setPageTransformer(true, new Scale());
        viewpager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return datas.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return object == view;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(datas.get(position));
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(datas.get(position));
                return datas.get(position);
            }
        });
//        autoplay();
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.i("TAG", "position: " + position + " positionoffset: " + positionOffset + " positionOffsetPixels: " + positionOffsetPixels);
                point.setTranslationX(px*position+px*positionOffset);
            }

            @Override
            public void onPageSelected(int position) {
//                point.setTranslationX(px*position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void addview(Drawable drawable) {
        imageView = new ImageView(this);
        imageView.setBackground(drawable);
        datas.add(imageView);
    }

    private void autoplay() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; ; i++) {
                    Message message = Message.obtain();
                    message.what = 1;
                    message.arg1 = i;
                    handler.sendMessage(message);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public static int dip2px(Context context) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (context.getResources().getDimension(R.dimen.pointmargin) * scale + 0.5f);
    }

    public int getDpi(Context context) {
        DisplayMetrics metrics = new DisplayMetrics();
        Display display = ((Activity) context).getWindowManager().getDefaultDisplay();
        display.getMetrics(metrics);
        return metrics.densityDpi;
    }

    public float getpx(){
        Log.i("TAG","DPI: "+ getDpi(this));
        return getResources().getDimension(R.dimen.pointmargin) * getDpi(this) / 160;
    }


}

