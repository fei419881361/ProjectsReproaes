package com.example.funchoufu.classhelper.Tab02;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.funchoufu.classhelper.R;
import com.example.funchoufu.classhelper.Support.Menber;
import com.example.funchoufu.classhelper.Support.Takephoto;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Funchou Fu on 2016/10/17.
 */
public class Tab02Work {
    ArrayList<Menber> decrease;
    ArrayList<Menber> increase;

    public Tab02Work(ArrayList<Menber> decrease, ArrayList<Menber> increase) {
        this.decrease = decrease;
        this.increase = increase;
    }

    //当初始化的时候 减少列表中没有资源时，返还true;
    public boolean Inithree(CardView top, CardView left, CardView right, Activity activity) {
        ((TextView) left.findViewById(R.id.leftname)).setText("[No]");
        ((TextView) left.findViewById(R.id.leftnumber)).setText("[None]");
        ((TextView) right.findViewById(R.id.rightname)).setText("[No]");
        ((TextView) right.findViewById(R.id.rightnumber)).setText("[None]");
        ((TextView) top.findViewById(R.id.topnumber)).setText("[None]");
        ((TextView) top.findViewById(R.id.topnumber)).setText("[None]");
        switch (decrease.size()) {
            case 0:
                return true;
            default:
                ((TextView) right.findViewById(R.id.rightname)).setText(decrease.get(1).getName());
                ((TextView) right.findViewById(R.id.rightnumber)).setText(decrease.get(1).getNumber());
                try {
                    ((ImageView) right.findViewById(R.id.rightimg)).setImageBitmap(Takephoto.ReadBitmapFromInnerFile(decrease.get(1).getPhoto(), "menber", activity));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            case 1:
                ((TextView) left.findViewById(R.id.leftname)).setText(decrease.get(0).getName());
                ((TextView) left.findViewById(R.id.leftnumber)).setText(decrease.get(0).getNumber());
                if(decrease.size()==1) {
                    ((TextView) right.findViewById(R.id.rightname)).setText("[No]");
                    ((TextView) right.findViewById(R.id.rightnumber)).setText("[None]");
                }
                try {
                    ((ImageView) left.findViewById(R.id.leftimg)).setImageBitmap(Takephoto.ReadBitmapFromInnerFile(decrease.get(0).getPhoto(), "menber", activity));
                    if(decrease.size()==1)
                        ((ImageView) right.findViewById(R.id.rightimg)).setImageResource(R.drawable.select_tab01_add_photo_img);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return false;
        }
    }

    //点击 触发的效果 当 被减少的列表 的个数 没有的时候，此时返还true;
    public boolean Click(CardView top, CardView left, CardView right, Activity activity) {
        String buf1 = decrease.get(0).getName();
        String buf2 = decrease.get(0).getNumber();
        int buf3 = decrease.get(0).getPhoto();
        increase.add(decrease.get(0));
        decrease.remove(0);
        ((TextView) top.findViewById(R.id.topname)).setText(buf1);
        ((TextView) top.findViewById(R.id.topnumber)).setText(buf2);
        try {
            ((ImageView) top.findViewById(R.id.topimg)).setImageBitmap(Takephoto.ReadBitmapFromInnerFile(buf3, "menber", activity));
        } catch (IOException e) {
            e.printStackTrace();
        }
        switch (decrease.size()) {
            case 0:
                return true;
            default:
                Menber m1=decrease.get(1);
                ((TextView) right.findViewById(R.id.rightname)).setText(m1.getName());
                ((TextView) right.findViewById(R.id.rightnumber)).setText(m1.getNumber());
                try {
                    ((ImageView) right.findViewById(R.id.rightimg)).setImageBitmap(Takephoto.ReadBitmapFromInnerFile(m1.getPhoto(), "menber", activity));
                    System.out.println("DEBUG:WOWO");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            case 1:
                ((TextView) left.findViewById(R.id.leftname)).setText(decrease.get(0).getName());
                ((TextView) left.findViewById(R.id.leftnumber)).setText(decrease.get(0).getNumber());
                if(decrease.size()==1) {
                    ((TextView) right.findViewById(R.id.rightname)).setText("[No]");
                    ((TextView) right.findViewById(R.id.rightnumber)).setText("[None]");
                }
                try {
                    ((ImageView) left.findViewById(R.id.leftimg)).setImageBitmap(Takephoto.ReadBitmapFromInnerFile(decrease.get(0).getPhoto(), "menber", activity));
                    if(decrease.size()==1)
                        ((ImageView)right.findViewById(R.id.rightimg)).setImageResource(R.drawable.select_tab01_add_photo_img);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                STARTANIMATION(top,left,right,activity);
        }
        return false;
    }

    public void ItemClick(int i, RecyclerView.Adapter another, RecyclerView.Adapter me) {
        increase.add(decrease.get(i));
        decrease.remove(i);
        another.notifyItemRemoved(i);
        another.notifyItemRangeChanged(0, decrease.size());
        me.notifyItemRangeChanged(0, increase.size());
    }

    private void STARTANIMATION(CardView top, CardView left, CardView right, Activity activity) {
        top.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.top));
        left.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.left));
        right.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.right));
    }
}
