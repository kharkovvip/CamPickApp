package trainee.x_prt.campickapp;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;

/**
 * Created by x_prt on 24.11.14.
 */
public class SplashActivity  extends Activity {

    ImageView splash;
    protected int _splashTime = 3000;
    private Thread splashTread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        final SplashActivity splashActivity = this;
        splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    synchronized (this) {

                        wait(_splashTime);
                    }

                } catch (InterruptedException e) {
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