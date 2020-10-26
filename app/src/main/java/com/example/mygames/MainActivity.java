package com.example.mygames;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
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
    ArrayList<MyGame> myGames;
    MyListAdapter myAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);
        setTitle("Game List");
        myGames = new ArrayList<>();
        myGames.add(new MyGame(R.drawable.line, R.string.title01, R.string.desc01));
        myGames.add(new MyGame(R.drawable.ball, R.string.title02, R.string.desc02));

        myAdapter = new MyListAdapter(this, R.layout.activity_main, myGames);

        ListView list = (ListView)findViewById(R.id.list);
        list.setAdapter(myAdapter);
    }
}

class MyGame {
    int Img;
    int Title;
    int Des;

    public MyGame(int Img, int Title, int Des) {
        this.Img = Img;
        this.Title = Title;
        this.Des = Des;
    }
}

class  MyListAdapter extends BaseAdapter {
    Context mContext;
    int mLayout;
    ArrayList<MyGame> mDatas;
    LayoutInflater mInflater;

    public MyListAdapter(Context context, int layout, ArrayList<MyGame> datas) {
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
        final TextView title = (TextView)convertView.findViewById(R.id.title);
        TextView desc = (TextView)convertView.findViewById(R.id.desc);
        Button startBtn = (Button)convertView.findViewById(R.id.startBtn);

        img.setImageResource(mDatas.get(position).Img);
        title.setText(mDatas.get(position).Title);
        desc.setText(mDatas.get(position).Des);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtTitle = title.getText().toString();
                switch (txtTitle) {
                    case "자유 곡선":
                        Intent FreeLineIntent = new Intent(v.getContext(), FreeLine.class);
                        mContext.startActivity(FreeLineIntent);
                        Toast.makeText(context, R.string.toast1, Toast.LENGTH_SHORT).show();
                        break;
                    case "공 움직이기":
                        Intent MoveCircleIntent = new Intent(v.getContext(), MoveCircle.class);
                        mContext.startActivity(MoveCircleIntent);
                        Toast.makeText(context, R.string.toast2, Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        return convertView;
    }
}