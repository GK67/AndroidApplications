package com.example.gk.draw2;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private PaintView paintView;
    private Button clearButton;
    private Button emBossButton;
    private Button blurButton;
    private Button normalButton;
    private Button greenPenButton;
    private Button addPenWidthButton;
    private Button reducePenWidthButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        paintView = (PaintView) findViewById(R.id.paintView);
        clearButton = (Button) findViewById(R.id.Clear);
        emBossButton = (Button) findViewById(R.id.emBoss);
        blurButton = (Button)findViewById(R.id.blur);
        normalButton = (Button)findViewById(R.id.normal);
        greenPenButton = (Button)findViewById(R.id.green);
        addPenWidthButton = (Button)findViewById(R.id.addPenWidth);
        reducePenWidthButton = (Button)findViewById(R.id.reducePenWidth);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        paintView.init(metrics);

        clearButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                paintView.clear();
            }
        });
        emBossButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                paintView.emboss();
            }
        });
        blurButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                paintView.blur();
            }
        });
        normalButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                paintView.normal();
            }
        });
        greenPenButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                paintView.greenPen();
            }
        });
        addPenWidthButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                paintView.addStrokeWidth();
            }
        });
        reducePenWidthButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                paintView.reduceStrokeWidth();
            }
        });


    }


}
