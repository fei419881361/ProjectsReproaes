package com.example.funchoufu.classhelper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.funchoufu.classhelper.Support.Achieve;
import com.example.funchoufu.classhelper.Support.Achieves;
import com.example.funchoufu.classhelper.Support.ClassHelperOpenHelper;
import com.example.funchoufu.classhelper.Support.Excel;
import com.example.funchoufu.classhelper.Support.Menber;
import com.example.funchoufu.classhelper.Support.Menbers;
import com.example.funchoufu.classhelper.Support.TakeDbFile;
import com.example.funchoufu.classhelper.Support.Takephoto;
import com.example.funchoufu.classhelper.Tab03.Tab03Work;
import com.example.funchoufu.classhelper.Tab03.tab02_yunduan;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import jxl.Workbook;

public class Main extends Activity implements View.OnClickListener {
    Main mmain;

    LinearLayout select_people;
    LinearLayout select_function;
    LinearLayout select_score;
    ImageView select_people_img;
    ImageView select_function_img;
    ImageView select_score_img;
    View tab01;
    View tab02;
    View tab03;


    RecyclerView tab01_rv;
    tab01_Adapter tab01_adapter;
    RecyclerView tab03_rv;
    tab03_Adapter tab03_adapter;
    ViewPager midviewpager;
    PagerAdapter midviewpager_adapter;
    ArrayList<View> midviewpager_list;
    Button tab03_click;

    SQLiteDatabase db;
    Menbers menbers;
    Achieves achieves;
    public static final int STARTTAB01 = 123;
    public static final int STARTTAB01_DELETE = 1234;
    private int STARTTAB01_EXCEL = 1024;
    private int STARTTAB01_DIALOG = 2048;
    private int STARTTAB03_ADD = 512;

    String userid = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        ClassHelperOpenHelper openHelper = new ClassHelperOpenHelper(this, "main", null, 1);
        db = openHelper.getWritableDatabase();
        ((App) getApplicationContext()).setDb(db, this);
        menbers = ((App) getApplicationContext()).getMenbers();
        achieves = ((App) getApplicationContext()).getAchieves();
        mmain=this;

        Intent intent = getIntent();
        userid = intent.getStringExtra("userid");


        Iniview();
        Inievent();
        Initab01();
        Initab02();
        Initab03();
    }

    private void Initab03() {
//        Achieve achieve=new Achieve();
//        achieve.setName("测试");
//        achieve.addachieveline(new AchieveLine("付枋洲", "2015070507"));
//        achieve.addachieveline(new AchieveLine("范帅", "2015070506"));
//        achieves.AddAchieve(achieve);
        tab03_rv = (RecyclerView) tab03.findViewById(R.id.tab03_rv);
        tab03_rv.setLayoutManager(new GridLayoutManager(this, 3));
        tab03_adapter=new tab03_Adapter(this);
        tab03_rv.setAdapter(tab03_adapter);

        tab03_click = (Button) tab03.findViewById(R.id.click);
        tab03_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Tab03Click();
            }
        });
    }

    private void Tab03Click() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent, STARTTAB03_ADD);
    }

    private void Initab02() {
        tab02.findViewById(R.id.tab02_diandao).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Main.this, tab02_diandao.class));
            }
        });
        tab02.findViewById(R.id.tab02_fenzu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Main.this, tab02_fenzu.class));
            }
        });
    }

    private void Initab01() {
        tab01.findViewById(R.id.tab01_addbyuser).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(Main.this, tab01_add.class), STARTTAB01);
            }
        });
        tab01.findViewById(R.id.tab01_xlsbyuser).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("*/*");
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(intent, STARTTAB01_EXCEL);
            }
        });
        tab01.findViewById(R.id.tab01_netbyuser).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                ConnectWiththeServlet("admin", "admin");
                if(userid != null){
                    Intent go = new Intent(Main.this, tab02_yunduan.class);
                    go.putExtra("userid",userid);
                    startActivity(go);
                }else Toast.makeText(Main.this,"请登陆。。",Toast.LENGTH_SHORT).show();
            }
        });
        tab01_rv = (RecyclerView) tab01.findViewById(R.id.tab01_rv);
        tab01_rv.setLayoutManager(new GridLayoutManager(this, 3));
        tab01_adapter = new tab01_Adapter(menbers, this);
        tab01_rv.setAdapter(tab01_adapter);
    }
    private void Inievent() {
        select_people.setOnClickListener(this);
        select_function.setOnClickListener(this);
        select_score.setOnClickListener(this);
        midviewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                int now = midviewpager.getCurrentItem();
                ResetImg();
                switch (now) {
                    case 0:
                        select_people_img.setImageResource(R.drawable.select_people_pressed);
                        break;
                    case 1:
                        select_function_img.setImageResource(R.drawable.select_function_pressed);
                        break;
                    case 2:
                        select_score_img.setImageResource(R.drawable.select_score_pressed);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private void Iniview() {
        select_people = (LinearLayout) findViewById(R.id.select_people);
        select_function = (LinearLayout) findViewById(R.id.select_function);
        select_score = (LinearLayout) findViewById(R.id.select_score);

        select_people_img = (ImageView) findViewById(R.id.select_people_img);
        select_function_img = (ImageView) findViewById(R.id.select_function_img);
        select_score_img = (ImageView) findViewById(R.id.select_score_img);

        midviewpager = (ViewPager) findViewById(R.id.midviewpager);
        midviewpager_list = new ArrayList<View>();
        //Add Views Into The ViewPager
        tab01 = LayoutInflater.from(this).inflate(R.layout.tab01, null);
        tab02 = LayoutInflater.from(this).inflate(R.layout.tab02, null);
        tab03 = LayoutInflater.from(this).inflate(R.layout.tab03, null);
        midviewpager_list.add(tab01);
        midviewpager_list.add(tab02);
        midviewpager_list.add(tab03);
        midviewpager_adapter = new PagerAdapter() {
            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                View view = midviewpager_list.get(position);
                container.addView(view);
                return view;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                View view = midviewpager_list.get(position);
                container.removeView(view);
            }

            @Override
            public int getCount() {
                return midviewpager_list.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }
        };
        midviewpager.setAdapter(midviewpager_adapter);
//        scrap.measure(View.MeasureSpec.makeMeasureSpec(getWidth() * 2 / 3, View.MeasureSpec.AT_MOST), View.MeasureSpec.makeMeasureSpec(getHeight() * 7 / 24, View.MeasureSpec.AT_MOST));
//
//        midviewpager.measure(View.MeasureSpec.makeMeasureSpec(300, View.MeasureSpec.EXACTLY), View.MeasureSpec.makeMeasureSpec(300, View.MeasureSpec.EXACTLY));
//        midviewpager.invalidate();
        select_people_img.setImageResource(R.drawable.select_people_pressed);
    }

    private void ResetImg() {
        select_people_img.setImageResource(R.drawable.select_people);
        select_function_img.setImageResource(R.drawable.select_function);
        select_score_img.setImageResource(R.drawable.select_score);
    }

    @Override
    public void onClick(View view) {
        ResetImg();
        switch (view.getId()) {
            case R.id.select_people:
                midviewpager.setCurrentItem(0);
                select_people_img.setImageResource(R.drawable.select_people_pressed);
                break;
            case R.id.select_function:
                midviewpager.setCurrentItem(1);
                select_function_img.setImageResource(R.drawable.select_function_pressed);
                break;
            case R.id.select_score:
                midviewpager.setCurrentItem(2);
                select_score_img.setImageResource(R.drawable.select_score_pressed);
                break;
            default:
                break;
        }
    }

    /**
     * Created by Funchou Fu on 2016/10/16.
     */
    static class tab01_Adapter extends RecyclerView.Adapter {
        private Menbers menbers;
        private Context context;

        public tab01_Adapter(Menbers menbers, Context context) {
            this.menbers = menbers;
            this.context = context;
        }

        class tab01_Adapter_ViewHolder extends RecyclerView.ViewHolder {
            TextView name;
            TextView number;
            ImageView photo;
            CardView card;

            public tab01_Adapter_ViewHolder(View itemView) {
                super(itemView);
                card = (CardView) itemView.findViewById(R.id.tab01_rv_card);
                photo = (ImageView) itemView.findViewById(R.id.tab01_rv_photo);
                name = (TextView) itemView.findViewById(R.id.tab01_rv_name);
                number = (TextView) itemView.findViewById(R.id.tab01_rv_number);
            }

            public CardView getCard() {
                return card;
            }

            public ImageView getPhoto() {
                return photo;
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
            return new tab01_Adapter_ViewHolder(LayoutInflater.from(context).inflate(R.layout.tab01_recyclerview, null));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
            tab01_Adapter_ViewHolder holder = ((tab01_Adapter_ViewHolder) viewHolder);
            final Menber menber = menbers.get(i);
            final int ii = i;
            holder.getName().setText(menber.getName());
            holder.getNumber().setText(menber.getNumber());
            if (menber.getPhoto() == Menber.DEFAULTEPHOTO) {
                holder.getPhoto().setImageResource(R.drawable.select_tab01_add_photo_img);
            } else {
                int id = menber.getPhoto();
                try {
                    //                    FileInputStream fis = main.openFileInput("menber" + String.valueOf(id));
                    //                    byte[] in = new byte[fis.available()];
                    //                    fis.read(in);
                    //                    Bitmap bitmap = BitmapFactory.decodeByteArray(in, 0, in.length);
                    Bitmap bitmap = Takephoto.ReadBitmapFromInnerFile(id, "menber", (Activity) context);
                    holder.getPhoto().setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            //change each card's background color
            int red = (int) (120 + Math.random() * 1534 % 70);
            int green = (int) (120 + Math.random() * 1513 % 70);
            int blue = (int) (120 + Math.random() * 3541 % 70);
            holder.getCard().setCardBackgroundColor(Color.rgb(red, green, blue));

            //set each card's longclicklistener.
            holder.getCard().setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    Intent intent = new Intent(context, tab01_delete.class);
                    intent.putExtra("name", menber.getName());
                    intent.putExtra("number", menber.getNumber());
                    intent.putExtra("id", menber.getPhoto());
                    intent.putExtra("position", ii);
                    ((Activity) context).startActivityForResult(intent, STARTTAB01_DELETE);
                    return true;
                }
            });
        }

        @Override
        public int getItemCount() {
            return menbers.size();
        }
    }


    private class tab03_Adapter extends RecyclerView.Adapter {
        Context context;

        public tab03_Adapter(Context context) {
            this.context = context;
        }

        private class VH extends RecyclerView.ViewHolder {
            ImageView imageView;
            TextView textView;
            Button button;

            public VH(View itemView) {
                super(itemView);
                imageView = (ImageView) itemView.findViewById(R.id.img);
                textView = (TextView) itemView.findViewById(R.id.name);
                button = (Button) itemView.findViewById(R.id.click);
            }

            public Button getButton() {
                return button;
            }

            public ImageView getImageView() {
                return imageView;
            }

            public TextView getTextView() {
                return textView;
            }
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new VH(LayoutInflater.from(context).inflate(R.layout.tab03_recyclerview, null));
        }

        @Override
        public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, int i) {
            final int ii = i;
            ((VH)viewHolder).getButton().setVisibility(View.INVISIBLE);
            ((VH) viewHolder).getTextView().setText(achieves.getachieve(i).getName());
            ((VH)viewHolder).getImageView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Button btn=((VH)viewHolder).getButton();
                    if((btn.getVisibility()==View.INVISIBLE)){
                        btn.setVisibility(View.VISIBLE);
                        btn.startAnimation(AnimationUtils.loadAnimation(mmain,R.anim.tab03_appear));
                    }
                    else{
                        btn.setVisibility(View.INVISIBLE);
                        btn.startAnimation(AnimationUtils.loadAnimation(mmain,R.anim.tab03_disappear));
                    }
                }
            });
            ((VH) viewHolder).getButton().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    achieves.getachieve(ii).DEBUG();
                    TakeDbFile.RemoveAchiveFromDatabase(db, achieves.getachieve(ii));
                    achieves.RemoveAchieve(ii);
                    notifyItemRemoved(ii);
                    notifyItemRangeChanged(0, achieves.length());
                }
            });
        }

        @Override
        public int getItemCount() {
            return achieves.length();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == STARTTAB01) {
            if (resultCode == tab01_add.FINISHED) {
                int moveid = data.getIntExtra("moveid", 0);
//                tab01_adapter.notifyItemInserted(moveid);
                tab01_adapter.notifyItemRangeChanged(0, menbers.size());
            }
        }
        if (requestCode == STARTTAB01_DELETE) {
            if (resultCode == tab01_delete.FINISHED_SURE) {
                int from = data.getIntExtra("from", 0);
                int to = data.getIntExtra("to", 0);

                System.out.println("DEBUG:" + from + to);
//                tab01_adapter.notifyItemChanged(from);
                tab01_adapter.notifyItemMoved(from, to);
                tab01_adapter.notifyItemRangeChanged(0, menbers.size());
            }
            if (resultCode == tab01_delete.FINISHED_DELETE) {
                int from = data.getIntExtra("from", 0);
//                tab01_adapter.notifyItemRemoved(from);
                tab01_adapter.notifyItemRemoved(from);
                tab01_adapter.notifyItemRangeChanged(0, menbers.size());
            }
        }
        if (requestCode == STARTTAB01_EXCEL) {
            if (resultCode == Activity.RESULT_OK) {
                Uri uri = data.getData();
                String path = uri.getPath();
                Intent intent = new Intent(Main.this, tab01_Dialog.class);
                intent.putExtra("path", path);
                startActivityForResult(intent, STARTTAB01_DIALOG);
            }
        }
        if (requestCode == STARTTAB01_DIALOG) {
            if (resultCode == tab01_Dialog.CHANGED) {
                tab01_adapter.notifyItemRangeChanged(0, menbers.size());
            } else
                Toast.makeText(getApplicationContext(), "Not!", Toast.LENGTH_SHORT).show();
        }
        if(requestCode==STARTTAB03_ADD){
            if(resultCode==Activity.RESULT_OK){
                Uri uri=data.getData();
                String path=uri.getPath();
                String name=path.substring(Tab03Work.FindLastSlope(path)+1,path.length());
                Workbook workbook= Excel.ReturnExcelBook(path);
                if(workbook==null) {
                    Toast.makeText(getApplicationContext(),"打开错误文件",Toast.LENGTH_SHORT).show();
                    return;
                }
                Achieve achieve=Tab03Work.FromExcelMakeAchieve(workbook.getSheet(0));
                achieve.setName(name);
                TakeDbFile.AddNewAchiveIntoDatabase(db, achieve);
                Toast.makeText(getApplication(),"插入成功",Toast.LENGTH_SHORT).show();
                achieves.AddAchieve(achieve);

                tab03_adapter.notifyItemInserted(achieves.length());
            }
        }
    }
}
