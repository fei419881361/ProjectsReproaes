package com.example.funchoufu.classhelper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.funchoufu.classhelper.Support.Achieve;
import com.example.funchoufu.classhelper.Support.Achieves;
import com.example.funchoufu.classhelper.Support.Menber;
import com.example.funchoufu.classhelper.Support.Menbers;
import com.example.funchoufu.classhelper.Tab02.FenzuWork;

import java.io.Serializable;
import java.util.ArrayList;

public class tab02_fenzu extends Activity{
    EditText groupname;
    SQLiteDatabase db;
    ArrayList<Menber> menbers;
    ArrayList<Menber>[] groups;
    Activity mActivity;
    RecyclerView list;
    Achieves achieves;
    MyAdapter adapter;
    ArrayList<String> dependlist;
    ArrayList<String> dependsublist;
    ArrayList<String> powerlist;

    int grouplength=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab02_fenzu);

        Inivalue();
        Iniview();
        Inievent();
    }

    private void Inivalue() {
        db=((App)getApplicationContext()).getDb();
        achieves=((App)getApplicationContext()).getAchieves();
        Menbers M=((App)getApplicationContext()).getMenbers();
        mActivity=this;
        menbers=new ArrayList<>();
        for(int i=0;i<M.size();i++){
            menbers.add(M.get(i));
        }
        achieves=((App)getApplicationContext()).getAchieves();
    }

    private void Inievent() {

        findViewById(R.id.div_number).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DivByNumber();
            }
        });
        findViewById(R.id.div_random).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DivByRandom();
            }
        });
        findViewById(R.id.div_self).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DivBySelf();
            }
        });



        findViewById(R.id.hide_area).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View viewli = findViewById(R.id.list);
                if (viewli.getVisibility() == View.VISIBLE)
                    viewli.setVisibility(View.INVISIBLE);
                else
                    viewli.setVisibility(View.VISIBLE);
            }
        });
    }

    private void DivBySelf() {

        FenzuWork work=new FenzuWork(menbers,achieves,mActivity,grouplength);

        int reactlength=work.divideGroupwithSelf(dependlist, dependsublist,powerlist);
        groups=work.getGroups();
        Intent intent=new Intent(tab02_fenzu.this,tab02_fenzu_finally.class);
        intent.putExtra("reactlength", reactlength);
        ((App)getApplication()).setGroups(groups);
        startActivity(intent);
        finish();
    }

    private void DivByRandom() {
        try {
            grouplength = Integer.parseInt(((EditText) findViewById(R.id.grouplength)).getText().toString());
        }catch (NumberFormatException e){}
        if(grouplength<=0||grouplength>=20)
            return;
        FenzuWork work=new FenzuWork(menbers,achieves,mActivity,grouplength);
        int reactlength=work.divideGroupwithRandom();
        groups=work.getGroups();
        Intent intent=new Intent(tab02_fenzu.this,tab02_fenzu_finally.class);
        intent.putExtra("reactlength", reactlength);
        ((App)getApplication()).setGroups(groups);
        startActivity(intent);
        finish();
    }

    private void DivByNumber() {
        try {
            grouplength = Integer.parseInt(((EditText) findViewById(R.id.grouplength)).getText().toString());
        }catch (NumberFormatException e){}
        if(grouplength<=0||grouplength>=20)
            return;
        FenzuWork work=new FenzuWork(menbers,achieves,mActivity,grouplength);
        int reactlength=work.divideGroupwithNumber();
        groups=work.getGroups();
        Intent intent=new Intent(tab02_fenzu.this,tab02_fenzu_finally.class);
        intent.putExtra("reactlength", reactlength);
        ((App)getApplication()).setGroups(groups);
        startActivity(intent);
        finish();
    }

    private void Iniview() {
        dependlist=new ArrayList<>();
        dependsublist=new ArrayList<>();
        powerlist=new ArrayList<>();
        groupname= (EditText) findViewById(R.id.groupname);
        findViewById(R.id.list).setVisibility(View.INVISIBLE);
        list= (RecyclerView) findViewById(R.id.list);
        list.setLayoutManager(new LinearLayoutManager(this));
        adapter=new MyAdapter(this,dependlist);
        list.setAdapter(adapter);
    }

    private class MyAdapter extends RecyclerView.Adapter {
        public static final int ENDTYPE=1024;
        public static final int MIDTYPE=512;

        Context mContext;
        ArrayList<String> dependlist;
        public MyAdapter(Context context,ArrayList<String> dependlist)
        {
            mContext=context;
            this.dependlist=dependlist;
        }

        class VH extends RecyclerView.ViewHolder{
            TextView textView;
            Button click;
            EditText power;
            Spinner spinner;
            public VH(View itemView) {
                super(itemView);
                textView= (TextView) itemView.findViewById(R.id.name);
                power= (EditText) itemView.findViewById(R.id.power);
                spinner= (Spinner) itemView.findViewById(R.id.spinner);
                click= (Button) itemView.findViewById(R.id.click);
            }

            public TextView getTextView() {
                return textView;
            }

            public Button getClick() {
                return click;
            }

            public EditText getPower() {
                return power;
            }
            public Spinner getSpinner() {
                return spinner;
            }
        }

        class VH2 extends RecyclerView.ViewHolder{
            Button click;
            public VH2(View itemView) {
                super(itemView);
                click= (Button) itemView;
            }

            public Button getClick() {
                return click;
            }
        }

        @Override
        public int getItemViewType(int position) {
            if (position==dependlist.size())
                return ENDTYPE;
            else
                return MIDTYPE;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            if(i==MIDTYPE)
                return new VH(LayoutInflater.from(mContext).inflate(R.layout.tab02_fenzu_recyclerview,null));
            else
                return new VH2(new Button(mContext));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
            if(viewHolder instanceof VH) {
                final int ii = i;
                VH vh=((VH) viewHolder);
                vh.getTextView().setText(dependlist.get(i));
                vh.getPower().addTextChangedListener(new EditListenner(vh.getPower(),i));
                vh.getClick().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dependlist.remove(ii);
                        dependsublist.remove(ii);
                        powerlist.remove(ii);
                        notifyItemRemoved(ii);
                        notifyItemRangeChanged(0, dependlist.size());
                    }
                });
                Spinner spinner=vh.getSpinner();
                final ArrayList<String> spinnerlist=achieves.getachieve(dependlist.get(i)).lookThrough();
                ArrayAdapter<String> adapter=new ArrayAdapter<String>(mContext,android.R.layout.simple_spinner_item,spinnerlist);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        dependsublist.set(ii, spinnerlist.get(i));
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
            }
            else{
                ((VH2)viewHolder).getClick().setText("添加");
                ((VH2)viewHolder).getClick().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ArrayList<String> put=new ArrayList<String>();
                        for(int i=0;i<achieves.length();i++){
                            put.add(achieves.getachieve(i).getName());
                        }
                        for(int i=0;i<dependlist.size();i++){
                            for(int j=0;j<put.size();j++) {
                                if (put.get(j) == dependlist.get(i)) {
                                    put.remove(j);
                                    j--;
                                }
                            }
                        }
                        if(put.size()==0) {
                            Toast.makeText(mContext, "你已经没有多余的成绩表可以被添加了!", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        new tab02_fenzu_dialog(mContext,put).show();
                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            return dependlist.size()+1;
        }

        private class EditListenner implements TextWatcher{
            EditText editText;
            int changge;
            public EditListenner(EditText editText,int i){
                this.editText=editText;
                this.changge=i;
            }
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                powerlist.set(changge,editText.getText().toString());
            }
        }
    }

    public void Add(String name){
        dependlist.add(name);
        powerlist.add("0");
        Achieve a= achieves.getachieve(name);
        dependsublist.add(a.getitemname(0));
        adapter.notifyItemRangeChanged(0, dependlist.size());
    }

//    private void DEBUG() {
//        for(int i=0;i<dependlist.size();i++){
//            System.out.println(dependlist.get(i));
//            System.out.println(dependsublist.get(i));
//            System.out.println(dependlist.size()+" "+dependsublist.size());
//        }
//    }
}
