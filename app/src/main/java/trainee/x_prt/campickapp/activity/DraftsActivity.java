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
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import trainee.x_prt.campickapp.R;

import static trainee.x_prt.campickapp.R.string.drafts;

public class DraftsActivity extends Activity {
    public ArrayList<Mail> mails = new ArrayList<Mail>();
    private ActionBar supportActionBar;
    DataHandler dataHandler;
    private MyListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drafts);
        getActionBar().setTitle(drafts);
        adapter = new MyListAdapter();
        dataHandler = new DataHandler(this);
        dataHandler.open();
        ListView list = (ListView) findViewById(R.id.mailListView);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {
                Mail clickedMail = mails.get(position);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.setMails(dataHandler.getMails());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dataHandler.close();
    }

    private class MyListAdapter extends BaseAdapter {
        private List<Mail> mails = Collections.emptyList();


        @Override
        public int getCount() {
            return mails.size();
        }

        @Override
        public Mail getItem(int i) {
            return mails.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup viewGroup) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.item_view, viewGroup, false);
            }Mail mail = getItem(position);
//
//            ImageView mailListView = (ImageView) convertView.findViewById(R.id.item_icon);
//            mailListView.setImageResource(mail.getIconID());

            TextView makeAdress = (TextView) convertView.findViewById(R.id.item_txtAdress);

            TextView makeSubject = (TextView) convertView.findViewById(R.id.item_txtSubject);

            TextView tempAdres = (TextView) convertView.findViewById(R.id.item_tempAdress);
            tempAdres.setText(mail.getTo());

            TextView tempSubject = (TextView) convertView.findViewById(R.id.item_tempSubject);
            tempSubject.setText(mail.getSubject());

            return convertView;
        }

        public void setMails(ArrayList<Mail> mails) {
            this.mails = mails;
            notifyDataSetChanged();
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

