package com.codeac.otnw.rgt_lgn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    //定义三个button控件
    Button btn1,btn2,btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //加载主页面，后续再由主页面调到各个页面

        //找到三个btn按钮控件
        btn1 = (Button)findViewById(R.id.first_method);
        btn2 = (Button)findViewById(R.id.second_method);
        btn3 = (Button)findViewById(R.id.third_method);

        //设置三个按钮点击事件的监听

        //btn1对应的点击事件
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转到SharedPreference活动
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,SharedPreference.class);
                startActivity(intent);//创建intent对象之后，开启一个新的活动
            }
        });

        //btn2对应的点击事件
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转到File活动
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,Fileactivity.class);
                startActivity(intent);
            }
        });

        //btn3对应的点击事件
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转到SD活动
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,Sd.class);
                startActivity(intent);
            }
        });
    }
}
