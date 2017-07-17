package com.example.funchoufu.classhelper;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.funchoufu.classhelper.Support.Menber;
import com.example.funchoufu.classhelper.Support.Takephoto;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class tab02_fenzu_finally extends Activity {
    ArrayList<Menber>[] groups;
    ArrayList<View> viewlist;
    PagerAdapter pAdapter;
    int reactlength;
    ViewPager vp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab02_fenzu_finally);
        Iniview();
        Inivalue();


    }

    private void Iniview() {
        vp= (ViewPager) findViewById(R.id.vp);

    }

    @SuppressWarnings("unchecked")
    private void Inivalue() {
        viewlist=new ArrayList<>();
        reactlength = getIntent().getIntExtra("reactlength", 1);
        ArrayList<Menber>[] receivegroups = ((App)getApplication()).getGroups();
        groups = new ArrayList[reactlength];
        for (int i = 0; i < reactlength; i++) {
            groups[i] = receivegroups[i];
        }


        RecyclerView[] rvs=new RecyclerView[reactlength];
        for(int i=0;i<rvs.length;i++){
            rvs[i]=new RecyclerView(this);
            rvs[i].setLayoutManager(new GridLayoutManager(this, 3));
            rvs[i].setAdapter(new MyAdapter(groups[i], this));
            viewlist.add(rvs[i]);
        }
        pAdapter=new PagerAdapter() {
            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                View view = viewlist.get(position);
                container.addView(view);
                return view;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                View view = viewlist.get(position);
                container.removeView(view);
            }

            @Override
            public int getCount() {
                return viewlist.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }
        };
        vp.setAdapter(pAdapter);
    }

    private class MyAdapter extends RecyclerView.Adapter {
        Activity mActivity;
        ArrayList<Menber> list;
        public MyAdapter(ArrayList<Menber> list,Activity activity){
            this.list=list;
            this.mActivity=activity;
        }
        private class VH extends RecyclerView.ViewHolder{
            CardView card;
            TextView name;
            TextView number;
            ImageView img;
            public VH(View itemView) {
                super(itemView);
                card= (CardView) itemView.findViewById(R.id.tab02_rv_card);
                name= (TextView) itemView.findViewById(R.id.tab02_rv_name);
                number= (TextView) itemView.findViewById(R.id.tab02_rv_number);
                img= (ImageView) itemView.findViewById(R.id.tab02_rv_photo);
            }

            public CardView getCard() {
                return card;
            }

            public TextView getName() {
                return name;
            }

            public TextView getNumber() {
                return number;
            }

            public ImageView getImg() {
                return img;
            }
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new VH(LayoutInflater.from(mActivity).inflate(R.layout.tab02_recyclerview,null));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
            ((VH)viewHolder).getNumber().setText(list.get(i).getNumber());
            ((VH)viewHolder).getName().setText(list.get(i).getName());
            try {
                ((VH)viewHolder).getImg().setImageBitmap(Takephoto.ReadBitmapFromInnerFile(list.get(i).getPhoto(),"menber",mActivity));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }
}
