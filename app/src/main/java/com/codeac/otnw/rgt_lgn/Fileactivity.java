package com.codeac.otnw.rgt_lgn;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;


public class Fileactivity extends AppCompatActivity {
    //定义控件
    EditText edit_pwd,edit_usr;
    Button btn_save,btn_read;
    TextView tv_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.file);

        //获取控件
        edit_usr = (EditText) findViewById(R.id.edit_usr);
        edit_pwd = (EditText) findViewById(R.id.edit_pwd);
        btn_save = (Button)findViewById(R.id.btn_save);
        btn_read = (Button) findViewById(R.id.btn_read);
        tv_data = (TextView)findViewById(R.id.tv_data);

        //写入保存
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    //字节流
                    FileOutputStream fileOutputStream =openFileOutput("info.txt",Context.MODE_PRIVATE);
                    //字符流
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream,"UTF-8");
                    outputStreamWriter.write(edit_usr.getText().toString());
                    outputStreamWriter.write(edit_pwd.getText().toString());
                    outputStreamWriter.flush();
                    fileOutputStream.flush();
                    outputStreamWriter.close();
                    fileOutputStream.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(),"写入成功!",Toast.LENGTH_SHORT).show();
            }
        });

        //读取数据
        btn_read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    FileInputStream fileInputStream = openFileInput("info.txt");
                    InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream,"UTF-8");
                    char content [] = new char[fileInputStream.available()];
                    inputStreamReader.read(content);
                    inputStreamReader.close();
                    fileInputStream.close();
                    String listResult = new String(content);

                    //显示在tv上
                    tv_data.setText("从文件中读取的数据如下："+"\n"+listResult);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
