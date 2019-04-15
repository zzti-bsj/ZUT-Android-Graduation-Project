package com.codeac.otnw.rgt_lgn;


import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

public class Sd extends AppCompatActivity{

    //定义控件
    EditText edit_usr,edit_pwd;
    Button btn_save,btn_read;
    TextView tv_data;

    //获取外存路径
    private java.io.File sdCard = Environment.getExternalStorageDirectory();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sd);

        //获取控件
        edit_usr = (EditText)findViewById(R.id.edit_usr);
        edit_pwd = (EditText)findViewById(R.id.edit_pwd);
        btn_save = (Button)findViewById(R.id.btn_save);
        btn_read = (Button)findViewById(R.id.btn_read);
        tv_data = (TextView)findViewById(R.id.tv_data);

        //保存数据
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                File newFile = new File(sdCard,"info.txt");
                if(sdCard.exists()){
                    try {
                        newFile.createNewFile();
                        Toast.makeText(getApplicationContext(),"文件info.txt创建成功!",Toast.LENGTH_SHORT).show();
                        //打开字节流
                        FileOutputStream fileOutputStream = new FileOutputStream(newFile);
                        //包装成字符流
                        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream,"UTF-8");
                        outputStreamWriter.write("username:"+edit_usr.getText().toString()+"\n"+"password:"+edit_pwd.getText().toString());
                        outputStreamWriter.flush();
                        fileOutputStream.flush();
                        outputStreamWriter.close();
                        fileOutputStream.close();
                        Toast.makeText(getApplicationContext(),"数据成功写入!",Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"不存在外部存储路径!",Toast.LENGTH_SHORT).show();
                }
            }
        });

        //读取数据
        btn_read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    File newFile = new File(sdCard,"info.txt");
                    if(newFile.exists()){
                        FileInputStream fileInputStream = new FileInputStream(newFile);
                        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream,"UTF-8");
                        char content[] = new char[fileInputStream.available()];
                        inputStreamReader.read(content);
                        inputStreamReader.close();
                        fileInputStream.close();
                        String listResult = new String(content);
                        tv_data.setText("读取数据如下：\n"+listResult);
                    }else{
                        Toast.makeText(getApplicationContext(),"文件不存在!",Toast.LENGTH_SHORT).show();
                    }
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
