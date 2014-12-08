package trainee.x_prt.campickapp.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import trainee.x_prt.campickapp.R;

import static trainee.x_prt.campickapp.R.string.drafts;

public class DraftsActivity extends Activity {
    private List<Car> myCars = new ArrayList<Car>();
    private ActionBar supportActionBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drafts);
        getActionBar().setTitle(drafts);

        populateCarList();
    }

    private void populateCarList() {
        myCars.add(new Car("Ford", 1990, R.drawable.ford, "Need Work"));
        myCars.add(new Car("Toyota", 1995, R.drawable.toyota, "So old"));
        myCars.add(new Car("Honda", 1999, R.drawable.honda, "Must Have"));
        myCars.add(new Car("Porsche", 1996, R.drawable.porsche, "Dream Car"));
        myCars.add(new Car("Jeep", 1980, R.drawable.jeep, "Awesome"));
        myCars.add(new Car("Rust-Bucket", 1940, R.drawable.rust_bucket, "OMG"));
        myCars.add(new Car("Moon Lander", 1945, R.drawable.moon_lander, "Landed OK"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_draft, menu);
        menu.findItem(R.id.newMsgBtn).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                startActivity(new Intent(DraftsActivity.this, ShareActivity.class));
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    public ActionBar getSupportActionBar() {
        return supportActionBar;
    }
}
