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
        myItems = new ArrayList<>();
        myItems.add(new MyItem(R.drawable.line, R.string.title01, R.string.desc01));
        myItems.add(new MyItem(R.drawable.ball, R.string.title02, R.string.desc02));

        myAdapter = new MyListAdapter(this, R.layout.activity_main, myItems);

        ListView list = (ListView)findViewById(R.id.list);
        list.setAdapter(myAdapter);
    }
}

class MyItem {
    int Img;
    int Name;
    int Des;

    public MyItem(int Img, int Name, int Des) {
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
                Intent FreeLineIntent = new Intent(v.getContext(), FreeLine.class);
                mContext.startActivity(FreeLineIntent);
//                Intent MoveCircleIntent = new Intent(v.getContext(), MoveCircle.class);
//                mContext.startActivity(MoveCircleIntent);
            }
        });
        return convertView;
    }
}