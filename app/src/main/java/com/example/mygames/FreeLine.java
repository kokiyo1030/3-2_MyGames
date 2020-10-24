package com.example.mygames;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class FreeLine extends AppCompatActivity {
    private MyView vw;
    ArrayList<Vertex> arVertex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        vw = new MyView(this);
        setContentView(vw);

//        LinearLayout linear = new LinearLayout(this);
//        linear.setOrientation(LinearLayout.VERTICAL);
//        LinearLayout.LayoutParams params =
//                new LinearLayout.LayoutParams(
//                        LinearLayout.LayoutParams.MATCH_PARENT,
//                        LinearLayout.LayoutParams.MATCH_PARENT);
//        linear.setLayoutParams(params);
//
//        LinearLayout.LayoutParams params2 =
//                new LinearLayout.LayoutParams(
//                        LinearLayout.LayoutParams.WRAP_CONTENT,
//                        LinearLayout.LayoutParams.WRAP_CONTENT);
//
//        Button button = new Button(this);
//        button.setLayoutParams(params2);
//        button.setText(R.string.exit);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(v.getContext(), MainActivity.class);
//            }
//        });
//        linear.addView(button);

        arVertex = new ArrayList<Vertex>();
    }

    public class Vertex {
        Vertex(float ax, float ay, boolean ad) {
            x = ax;
            y = ay;
            draw = ad;
        }
        float x;
        float y;
        boolean draw;
    }

    protected class MyView extends View {
        Paint mPaint;

        public MyView(Context context) {
            super(context);

            // Paint 객체 미리 초기화
            mPaint = new Paint();
            mPaint.setColor(Color.BLACK);
            mPaint.setStrokeWidth(3);
            mPaint.setAntiAlias(true);
        }

        public void onDraw(Canvas canvas) {
            canvas.drawColor(Color.LTGRAY);

            // 정점을 순회하면서 선분으로 잇는다.
            for (int i=0;i<arVertex.size();i++) {
                if (arVertex.get(i).draw) {
                    canvas.drawLine(arVertex.get(i-1).x, arVertex.get(i-1).y,
                            arVertex.get(i).x, arVertex.get(i).y, mPaint);
                }
            }
        }

        // 터치 이동 시마다 정점을 추가한다.
        public boolean onTouchEvent(MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                arVertex.add(new Vertex(event.getX(), event.getY(), false));
                return true;
            }
            if (event.getAction() == MotionEvent.ACTION_MOVE) {
                arVertex.add(new Vertex(event.getX(), event.getY(), true));
                invalidate();
                return true;
            }
            return false;
        }
    }
}
