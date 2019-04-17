package com.serifyasargmail.cevsen;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class ViewPagerAdapter extends PagerAdapter {
    Context context;
    String dua;
    int page;

    public ViewPagerAdapter(Context context, String dua, int page){
        this.context=context;
        this.dua=dua;
        this.page=page;
    }

    @Override
    public int getCount() {
        return page;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view==o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView imageView=new ImageView(context);
        if(!dua.matches("abedir")) {
            imageView.setRotationY(180);
        }

        Picasso.get()
                .load("file:///android_asset/"+dua+position+".gif")
                .fit()

                .into(imageView);

        container.addView(imageView);

        return imageView;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
