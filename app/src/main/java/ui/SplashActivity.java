package ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.example.tata.R;

import base.BaseActivity;

/**
 * Created by 不一样的天空 on 2016/9/1.
 */
public class SplashActivity extends BaseActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_main);
        Handler handler =new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                String user =null;
                if (user == null) {
                    startActivity(MainActivity.class, null, true);
                } else {
                    startActivity(ChatWithYouActivity.class, null, true);
                }
            }
        }, 1000);
    }
    }

