package com.codeac.otnw.rgt_lgn;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class SharedPreference extends AppCompatActivity {
    //定义所需控件
    EditText edit_pwd,edit_usr;
    TextView tv_data;
    Button btn_save,btn_read;

    //定义存入到SharedPreference中的key值
    static final String Key1 = "username";
    static final String Key2 = "password";

    //定义SharedPreference对象
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sp);//加载sp的布局

        //获取布局中的控件
        edit_usr = (EditText)findViewById(R.id.edit_usr);
        edit_pwd = (EditText)findViewById(R.id.edit_pwd);
        btn_save = (Button)findViewById(R.id.btn_save);
        btn_read = (Button)findViewById(R.id.btn_read);
        tv_data = (TextView)findViewById(R.id.tv_data);

        //获取SharedPreference对象
        preferences = getPreferences(Activity.MODE_PRIVATE);

        final SharedPreferences.Editor editor = preferences.edit();

        //设置btn_save的监听
        //保存数据
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString(Key1, edit_usr.getText().toString());
                editor.putString(Key2, edit_pwd.getText().toString());
                editor.commit();
                Toast.makeText(getApplicationContext(),"保存成功！",Toast.LENGTH_SHORT).show();
            }
        });

        //设置btn_read的监听
        //读取数据
        btn_read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = preferences.getString(Key1,"不存在此数据!");
                String password = preferences.getString(Key2,"不存在此数据!");
                tv_data.setText("The data of your information："+"\n"+"\tuser_name:"+username+"\n"+"\tpassword:"+password+"\n");
            }
        });
    }
}
