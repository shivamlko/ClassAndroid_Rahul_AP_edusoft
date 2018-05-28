package com.example.navoki.storage;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private TextView text;
    private EditText editText;
    private Button sharedpref;
    private Button file;
      SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        text = (TextView) findViewById(R.id.text);
        editText = (EditText) findViewById(R.id.editText);
        sharedpref = (Button) findViewById(R.id.sharedpref);
        file = (Button) findViewById(R.id.file);


        sharedPreferences=getSharedPreferences("USER_PREF",MODE_PRIVATE);


        String str=sharedPreferences.getString("name","N/A");
        text.setText(str);

        if(sharedPreferences.getBoolean("isLogon",false))
        {
            Toast.makeText(MainActivity.this,"User loged IN",Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(MainActivity.this,"Opened first time",Toast.LENGTH_SHORT).show();

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M) {
            String[] per = {Manifest.permission.WRITE_EXTERNAL_STORAGE};

            requestPermissions(per,1);

        }





        sharedpref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s=editText.getText().toString();

                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("name",s);
                editor.putBoolean("isLogon",true);
                editor.commit();
            }
        });





        file.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s=editText.getText().toString();

                File folder=new File(Environment.getExternalStorageDirectory()+File.separator+"MyApp"
                        +File.separator+"TextFiles");

                if(!folder.isDirectory())
                    folder.mkdirs();

                File file=new File(Environment.getExternalStorageDirectory()+File.separator+"MyApp"
                        +File.separator+"TextFiles"
                +File.separator+"abc.txt");

                if(!file.exists()) {
                    try {
                        file.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                try {
                    FileOutputStream fileOutputStream=new FileOutputStream(file);
                    fileOutputStream.write(s.getBytes());
                    fileOutputStream.close();;

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });



    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode==1 && grantResults[0]== PackageManager.PERMISSION_GRANTED)
        {
            Toast.makeText(MainActivity.this,"Pemisson Allowed ",Toast.LENGTH_SHORT).show();

        }
        else
            Toast.makeText(MainActivity.this,"Pemisson Denied ",Toast.LENGTH_SHORT).show();


    }
}
