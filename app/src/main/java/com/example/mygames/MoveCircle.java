package com.example.mygames;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class MoveCircle extends AppCompatActivity {
    private MyView move;
    Button exitButton;
    LinearLayout linearLayout;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        move = new MyView(this);
        move.setFocusable(true);
        move.setFocusableInTouchMode(true);
        linearLayout.addView(move);
        setContentView(linearLayout);
    }

    protected class MyView extends View {
        float mX,mY;
        int mColor;

        public MyView(Context context) {
            super(context);
            mX = 100;
            mY = 100;
            mColor = Color.BLUE;

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            exitButton = new Button(context);
            exitButton.setText(R.string.exit);
            exitButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
            linearLayout.addView(exitButton, layoutParams);
        }

        public void onDraw(Canvas canvas) {
            canvas.drawColor(Color.LTGRAY);
            Paint Pnt = new Paint();
            Pnt.setColor(mColor);
            Pnt.setAntiAlias(true);
            canvas.drawCircle(mX,mY,16,Pnt);
        }

        public boolean onKeyDown(int KeyCode, KeyEvent event) {
            super.onKeyDown(KeyCode, event);
            if (event.getAction() == KeyEvent.ACTION_DOWN) {
                switch (KeyCode) {
                    case KeyEvent.KEYCODE_A:
                        mX-=5;
                        invalidate();
                        return true;
                    case KeyEvent.KEYCODE_D:
                        mX+=5;
                        invalidate();
                        return true;
                    case KeyEvent.KEYCODE_W:
                        mY-=5;
                        invalidate();
                        return true;
                    case KeyEvent.KEYCODE_S:
                        mY+=5;
                        invalidate();
                        return true;
                    case KeyEvent.KEYCODE_SPACE:
                        if (mColor == Color.BLUE) {
                            mColor = Color.RED;
                        } else {
                            mColor = Color.BLUE;
                        }
                        invalidate();
                        return true;
                }
            }
            return false;
        }
    }
}
