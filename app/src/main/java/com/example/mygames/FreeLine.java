package com.example.mygames;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class FreeLine extends AppCompatActivity {
    private MyView paint;
    ArrayList<Vertex> arVertex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        paint = new MyView(this);
        setContentView(paint);

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

            mPaint = new Paint();
            mPaint.setColor(Color.BLACK);
            mPaint.setStrokeWidth(3);
            mPaint.setAntiAlias(true);
        }

        public void onDraw(Canvas canvas) {
            canvas.drawColor(Color.LTGRAY);

            for (int i=0;i<arVertex.size();i++) {
                if (arVertex.get(i).draw) {
                    canvas.drawLine(arVertex.get(i-1).x, arVertex.get(i-1).y,
                            arVertex.get(i).x, arVertex.get(i).y, mPaint);
                }
            }
        }

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
