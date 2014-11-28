package trainee.x_prt.campickapp;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
public class SplashActivity  extends Activity{

    protected int _splashTime = 3000;
    private Thread splashTread;
    ImageView loading;
    Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        loading = (ImageView) findViewById(R.id.loading);
        animation = AnimationUtils.loadAnimation(this, R.anim.rotate);
        loading.startAnimation(animation);

        final SplashActivity splashActivity = this;
        splashTread = new Thread(){

            @Override
            public void run(){
                try{
                    synchronized (this){
                        wait(_splashTime);
                    }

                } catch (InterruptedException e){
                } finally {
                    finish();
                    Intent i = new Intent();
                    i.setClass(splashActivity, Drafts.class);
                    startActivity(i);
                    finish();
                }
            }
        };
        splashTread.start();
    }
}