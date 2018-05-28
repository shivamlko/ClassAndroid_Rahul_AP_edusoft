package com.navoki.myservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Name name on 5/23/2018.
 */
public class MyServices extends Service {

    public MyServices()
    {

    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        int n=intent.getIntExtra("data",0);

        int i=0;
        for (;i<n;i++)
            Log.e("MSG",i+"");

        Intent intent2=new Intent("ABC");
        intent2.putExtra("i",i);
        sendBroadcast(intent2);


        return  START_STICKY;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.e("MSG","onDestroy");
    }
}
