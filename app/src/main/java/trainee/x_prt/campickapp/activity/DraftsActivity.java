package trainee.x_prt.campickapp.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
        populateListView();
        registerClickCallBack();
    }


    private void populateCarList() {
        myCars.add(new Car(R.string.item_adress, 1990, R.drawable.ford, "Need Work"));
        myCars.add(new Car("Toyota", 1995, R.drawable.toyota, "So old"));
        myCars.add(new Car("Honda", 1999, R.drawable.honda, "Must Have"));
        myCars.add(new Car("Porsche", 1996, R.drawable.porsche, "Dream Car"));
        myCars.add(new Car("Jeep", 1980, R.drawable.jeep, "Awesome"));
        myCars.add(new Car("Rust-Bucket", 1940, R.drawable.rust_bucket, "OMG"));
        myCars.add(new Car("Moon Lander", 1945, R.drawable.moon_lander, "Landed OK"));
    }

    private void populateListView() {
        BaseAdapter adapter = new MyListAdapter();
        ListView list = (ListView) findViewById(R.id.carListView);
        list.setAdapter(adapter);
    }

    private void registerClickCallBack() {
        ListView list = (ListView) findViewById(R.id.carListView);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {
                Car clickedCar = myCars.get(position);
                String message = "You clicked position " + (position + 1) + " Which is car make: " + clickedCar.getAdressID();
                Toast.makeText(DraftsActivity.this, message, Toast.LENGTH_LONG).show();
            }
        });

    }

    private class MyListAdapter extends BaseAdapter {
        public MyListAdapter() {
        }

        @Override
        public int getCount() {
            return myCars.size();
        }

        @Override
        public Car getItem(int i) {
            return myCars.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup viewGroup) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.item_view, viewGroup, false);
            }
            Car car = getItem(position);

            ImageView carView = (ImageView) convertView.findViewById(R.id.item_icon);
            carView.setImageResource(car.getIconID());

            TextView makeText = (TextView) convertView.findViewById(R.id.item_txtAdress);
            makeText.setText(car.getAdressID());

            TextView yearText = (TextView) convertView.findViewById(R.id.item_txtSubject);
            yearText.setText(Integer.toString(car.getSubjectID()));

            TextView conditionText = (TextView) convertView.findViewById(R.id.item_txtCondition);
            conditionText.setText(car.getAdressField());

            return convertView;

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.newmsg_actionbar, menu);
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

