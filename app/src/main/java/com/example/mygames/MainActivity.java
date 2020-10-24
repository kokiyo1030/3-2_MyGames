package com.example.mygames;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<MyItem> myItems;
    MyListAdapter myAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);
        setTitle("Game List");
//        String tag = (String)view.getTag();
//
//        int id_picture = res.getIdentifier("title"+tag, "string", getPackageName());
//        String picture = res.getString(id_picture).toString();
//
//        int id_img = res.getIdentifier(picture, "drawable", getPackageName());
//
//        myItems = new ArrayList<>();
//        myItems.add(new MyItem(R.drawable.line, "title"+tag, "ex"+tag));
//        myItems.add(new MyItem(R.drawable.ball, "title"+tag, "ex"+tag));
//
//        myAdapter = new MyListAdapter(this, R.layout.activity_main, myItems);
//
//        ListView list = (ListView)findViewById(R.id.list);
//        list.setAdapter(myAdapter);
        myItems = new ArrayList<>();
        myItems.add(new MyItem(R.drawable.line, "자유 곡선", "터치를 입력받아 자유 곡선을 그리기"));
        myItems.add(new MyItem(R.drawable.ball, "공 움직이기", "키 A(왼쪽), D(오른쪽), W(위쪽), S(아래쪽)를 입력받아 공 움직이기"));

        myAdapter = new MyListAdapter(this, R.layout.activity_main, myItems);

        ListView list = (ListView)findViewById(R.id.list);
        list.setAdapter(myAdapter);

//        ActionBar ab = getSupportActionBar();
//        ab.setTitle("My Games");
//        ab.setDisplayShowHomeEnabled(true);
    }
}

class MyItem {
    int Img;
    String Name;
    String Des;

    public MyItem(int Img, String Name, String Des) {
        this.Img = Img;
        this.Name = Name;
        this.Des = Des;
    }
}

class  MyListAdapter extends BaseAdapter {
    Context mContext;
    int mLayout;
    ArrayList<MyItem> mDatas;
    LayoutInflater mInflater;

    public MyListAdapter(Context context, int layout, ArrayList<MyItem> datas) {
        this.mContext = context;
        this.mLayout = layout;
        this.mDatas = datas;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public  int getCount() {
        return  mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(mLayout, parent, false);
        }

        ImageView img = (ImageView)convertView.findViewById(R.id.img);
        img.setImageResource(mDatas.get(position).Img);

        TextView txt = (TextView)convertView.findViewById(R.id.text);
        txt.setText(mDatas.get(position).Name);

        TextView txt2 = (TextView)convertView.findViewById(R.id.desc);
        txt2.setText(mDatas.get(position).Des);

        Button btn = (Button)convertView.findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), FreeLine.class);
                mContext.startActivity(intent);
                Toast.makeText(context, "그림을 그리세요", Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;
    }
}