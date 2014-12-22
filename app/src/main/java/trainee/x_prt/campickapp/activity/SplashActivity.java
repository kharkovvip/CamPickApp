package trainee.x_prt.campickapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import trainee.x_prt.campickapp.R;

public class SplashActivity extends Activity {

    private final static int SPLASH_TIME = 3000;
    ImageView loading;
    ImageView blinkView;
    ImageView flashLightView;
    Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        loading = (ImageView) findViewById(R.id.loading);
        animation = AnimationUtils.loadAnimation(this, R.anim.loading_anim);
        loading.startAnimation(animation);

        blinkView = (ImageView) findViewById(R.id.blinkView);
        animation = AnimationUtils.loadAnimation(this, R.anim.blink_animation);
        blinkView.startAnimation(animation);

        flashLightView = (ImageView) findViewById(R.id.flashLightView);
        animation = AnimationUtils.loadAnimation(this, R.anim.flash_animation);
        flashLightView.startAnimation(animation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, DraftsActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_TIME);
    }
}