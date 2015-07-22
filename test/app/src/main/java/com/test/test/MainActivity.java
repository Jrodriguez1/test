package com.test.test;

import android.media.AudioManager;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

//import java.security.KeyManagementException;
//import java.security.NoSuchAlgorithmException;
import java.util.Map;
import org.json.JSONObject;
import java.util.HashMap;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.data.JPushLocalNotification;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;
//import java.io.IOException;

import android.os.Vibrator;
import android.content.Context;
import android.media.SoundPool;

public class MainActivity extends ActionBarActivity {
    Button button_c;
    Button button_p;
    Button vibra_b;
    Button cancel_b;

    private Vibrator vib;
    private SoundPool sp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);

        button_c = (Button)findViewById(R.id.button1);
        button_p = (Button)findViewById(R.id.button2);
        vibra_b = (Button)findViewById(R.id.button_v);
        cancel_b = (Button)findViewById(R.id.button_c);

        sp = new SoundPool(10, AudioManager.STREAM_SYSTEM, 5);
        sp.load(this,R.raw.test,1);

        button_c.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                JPushLocalNotification ln = new JPushLocalNotification();
                ln.setBuilderId(0);
                ln.setContent("hhh");
                ln.setTitle("ln");
                ln.setNotificationId(11111111) ;
                ln.setBroadcastTime(System.currentTimeMillis());

                Map<String , Object> map = new HashMap<String, Object>() ;
                map.put("name", "jpush") ;
                map.put("test", "111") ;
                JSONObject json = new JSONObject(map) ;
                ln.setExtras(json.toString()) ;
                JPushInterface.addLocalNotification(getApplicationContext(), ln);
            }
        });

        button_p.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                    //String[] str = {"all", "all", "tst"};
                    //push.main(str);
                //try {
                    //String[] str = {"123", "123"};
                    push.sendPostRequest("https://api.jpush.cn/v3/push", "{\"platform\":\"all\",\"audience\":\"all\",\"notification\":{\"alert\":\"Hello,JPush!\"}}");
                //} catch (Exception e) {
                //    e.printStackTrace();
                //} catch (NoSuchAlgorithmException n) {
                //    n.printStackTrace();
               // } catch (KeyManagementException k) {
               //     k.printStackTrace();
               //}
                }
            //    push.pushtest2();

        });

        vibra_b.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                vib = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
                long [] pattern = {1000,1000,1000,1000};   // Í£Ö¹ ¿ªÆô Í£Ö¹ ¿ªÆô
                vibra.Vibrate(vib, pattern);
                sp.play(1, 1, 1, 0, -1, 1);
            }
        });

        cancel_b.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                vibra.cancel(vib, sp);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        JPushInterface.onResume(this);
    }
    @Override
    protected void onPause() {
        super.onPause();
        JPushInterface.onPause(this);
    }

}
