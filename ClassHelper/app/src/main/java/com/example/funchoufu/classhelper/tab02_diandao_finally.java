package com.example.funchoufu.classhelper;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.funchoufu.classhelper.Tab02.Tab02Work;
import com.example.funchoufu.classhelper.Tab02.Print;
import com.example.funchoufu.classhelper.Support.Menber;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

public class tab02_diandao_finally extends Activity {
    RecyclerView tab02_diandao_finally_rv;
    RecyclerView tab02_diandao_finally_rv2;
    Button tab02_diandao_finally_print;
    ArrayList<Menber> arrived;
    ArrayList<Menber> lated;
    int which;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab02_diandao_finally);

        Inivalue();
        Iniview();
        Inievent();
    }

    private void Inievent() {
        findViewById(R.id.tab02_diandao_finally_print).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar=Calendar.getInstance();
                int Hour=calendar.get(Calendar.HOUR_OF_DAY);
                int Day=calendar.get(Calendar.DAY_OF_MONTH);
                which=Print.WhichModule(Hour);
                Print print=new Print(new File(Environment.getExternalStorageDirectory(),String.format("%d号第%d,%d节课.txt",Day,which,which+1)));
                try {
                    print.ExplodeTxt(lated);
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),"你的数据输出出现了问题",Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(getApplicationContext(),"已经成功了打印了名单",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void Inivalue() {
        Intent intent=getIntent();
        arrived= (ArrayList<Menber>) intent.getSerializableExtra("arrive");
        lated= (ArrayList<Menber>) intent.getSerializableExtra("late");
        for (int i=0;i<lated.size();i++){
            System.out.println(lated.get(i).getName());
            System.out.println(lated.get(i).getNumber());
        }
    }

    private void Iniview() {
        tab02_diandao_finally_rv= (RecyclerView) findViewById(R.id.tab02_diandao_finally_rv);
        tab02_diandao_finally_rv.setLayoutManager(new LinearLayoutManager(this));
        tab02_diandao_finally_adapter a=new tab02_diandao_finally_adapter(this);
        tab02_diandao_finally_rv.setAdapter(a);

        tab02_diandao_finally_rv2= (RecyclerView) findViewById(R.id.tab02_diandao_finally_rv2);
        tab02_diandao_finally_rv2.setLayoutManager(new LinearLayoutManager(this));
        tab02_diandao_support_adapter b=new tab02_diandao_support_adapter(this);
        tab02_diandao_finally_rv2.setAdapter(b);
        b.setAnother(a);
        a.setAnother(b);

        tab02_diandao_finally_print= (Button) findViewById(R.id.tab02_diandao_finally_print);
    }

    private class tab02_diandao_finally_adapter extends RecyclerView.Adapter {
        RecyclerView.Adapter another;
        Activity activity;
        private final int FOOT=32;
        private final int NOTFOOT=16;
        tab02_diandao_finally_adapter me;

        public tab02_diandao_finally_adapter(Activity activity){
            this.activity=activity;
            me=this;
        }
        public void setAnother(RecyclerView.Adapter adapter){
            this.another=adapter;
        }
        public class tab02_diandao_finally_adapter_ViewHolder extends RecyclerView.ViewHolder{
            TextView name;
            TextView number;
            CardView card;
            Button button;
            public tab02_diandao_finally_adapter_ViewHolder(View itemView) {
                super(itemView);
                this.name= (TextView) itemView.findViewById(R.id.name);
                this.number= (TextView) itemView.findViewById(R.id.number);
                this.card= (CardView) itemView.findViewById(R.id.card);
                this.button= (Button) itemView.findViewById(R.id.move);
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

            public Button getButton() {
                return button;
            }
        }
        public class tab02_diandao_finally_adapter_footerholder extends RecyclerView.ViewHolder{
            ImageButton button;
            public tab02_diandao_finally_adapter_footerholder(View itemView) {
                super(itemView);
                button= (ImageButton) itemView.findViewById(R.id.print);
            }

            public ImageButton getButton() {
                return button;
            }
        }

        @Override
        public int getItemViewType(int position) {
            if(position==lated.size())
                return FOOT;
            else
                return NOTFOOT;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            if(i==NOTFOOT)
                return new tab02_diandao_finally_adapter_ViewHolder(LayoutInflater.from(getApplicationContext()).inflate(R.layout.tab02_support_recyclerview,null));
            else
                return new tab02_diandao_finally_adapter_footerholder(LayoutInflater.from(getApplicationContext()).inflate(R.layout.tab02_diandao_footer,null));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
            if(viewHolder instanceof tab02_diandao_finally_adapter_ViewHolder) {
                final int ii=i;
                final Tab02Work work=new Tab02Work(lated,arrived);
                tab02_diandao_finally_adapter_ViewHolder V = ((tab02_diandao_finally_adapter_ViewHolder) viewHolder);
                V.getName().setText(lated.get(i).getName());
                V.getNumber().setText(lated.get(i).getNumber());
                int red = (int) (120 + Math.random() * 1534 % 70);
                int green = (int) (120 + Math.random() * 1513 % 70);
                int blue = (int) (120 + Math.random() * 3541 % 70);
                V.getCard().setCardBackgroundColor(Color.rgb(red, green, blue));
                V.getButton().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        work.ItemClick(ii,another,me);
                    }
                });
            }
            else{
                tab02_diandao_finally_adapter_footerholder V=((tab02_diandao_finally_adapter_footerholder) viewHolder);
                V.getButton().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(tab02_diandao_finally_rv2.getVisibility()==View.VISIBLE) {
                            tab02_diandao_finally_rv2.setVisibility(View.GONE);
                        }
                        else{
                            tab02_diandao_finally_rv2.setVisibility(View.VISIBLE);
                        }
                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            return lated.size()+1;
        }
    }
    private class tab02_diandao_support_adapter extends RecyclerView.Adapter{
        RecyclerView.Adapter another;
        RecyclerView.Adapter me;
        Activity activity;
        public void setAnother(RecyclerView.Adapter adapter){
            this.another=adapter;
        }
        public tab02_diandao_support_adapter(Activity activity){
            this.activity=activity;
            me=this;
        }
        class MyHolder extends RecyclerView.ViewHolder{
            TextView name;
            TextView number;
            CardView card;
            Button button;
            public MyHolder(View itemView) {
                super(itemView);
                this.name= (TextView) itemView.findViewById(R.id.name);
                this.number= (TextView) itemView.findViewById(R.id.number);
                this.card= (CardView) itemView.findViewById(R.id.card);
                this.button= (Button) itemView.findViewById(R.id.move);
                if(this.number==null)
                    System.out.println("DEBUG--------------------------");
            }

            public Button getButton() {
                return button;
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
        }
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new MyHolder(LayoutInflater.from(getApplicationContext()).inflate(R.layout.tab02_support_recyclerview,null));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
            int red = (int) (120 + Math.random() * 1534 % 70);
            int green = (int) (120 + Math.random() * 1513 % 70);
            int blue = (int) (120 + Math.random() * 3541 % 70);
            final Tab02Work work=new Tab02Work(arrived,lated);
            final int ii=i;
            ((MyHolder)viewHolder).getName().setText(arrived.get(i).getName());
            ((MyHolder)viewHolder).getNumber().setText(arrived.get(i).getNumber());
            ((MyHolder)viewHolder).getCard().setCardBackgroundColor(Color.rgb(red,green,blue));
            ((MyHolder)viewHolder).getButton().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    work.ItemClick(ii,another,me);
                }
            });
        }

        @Override
        public int getItemCount() {
            return arrived.size();
        }
    }
}
