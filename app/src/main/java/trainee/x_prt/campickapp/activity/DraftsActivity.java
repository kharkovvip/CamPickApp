package trainee.x_prt.campickapp.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import DataBase.DataHandler;
import DataBase.Mail;
import trainee.x_prt.campickapp.R;

import static trainee.x_prt.campickapp.R.string.drafts;

public class DraftsActivity extends Activity {
    public ArrayList<Mail> myMails = new ArrayList<Mail>();
    private DataHandler dataHandler;
    private MyListAdapter adapter;
    Button removeBtn;
    TextView removeBtnTextView;
    FrameLayout removeBtnFrame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drafts);
        getActionBar().setTitle(drafts);
        adapter = new MyListAdapter();
        dataHandler = new DataHandler(this);
        dataHandler.open();

        //Clicked Item to enter Draft
        ListView mailListView = (ListView) findViewById(R.id.mailListView);
        mailListView.setAdapter(adapter);
        mailListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {
                Mail mail = adapter.getItem(position);
                Bundle bundle = new Bundle();
                bundle.putString(ShareActivity.MAIL_ID, mail.getDraftsID());
                Intent intent = new Intent();
                intent.putExtras(bundle);
                intent.setClass(getBaseContext(), ShareActivity.class);
                startActivity(intent);

            }
        });

        //Longclicked Item to remove
        mailListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DraftsActivity.this);
                builder.setMessage("Are you sure you want to remove this message?")
                        .setPositiveButton(R.string.btnNoTxt, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        })

                        .setNegativeButton(R.string.btnYesTxt, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Mail mail = adapter.getItem(position);
                                dataHandler.removeMail(mail.getDraftsID());
                                adapter.setMails(dataHandler.getMails());
                            }
                        })
                        .setCancelable(false);
                AlertDialog alert = builder.create();
                alert.show();
                return true;
            }
        });

        //Remove All Button
        removeBtn = (Button) findViewById(R.id.removeBtn);
        removeBtnTextView = (TextView) findViewById(R.id.removeBtnTextView);
        removeBtnFrame = (FrameLayout) findViewById(R.id.removeBtnFrame);
        removeBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DraftsActivity.this);
                builder.setMessage("Are you sure you want to remove all messages?")
                        .setPositiveButton(R.string.btnNoTxt, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        })

                        .setNegativeButton(R.string.btnYesTxt, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dataHandler.removeMails();
                                adapter.setMails(dataHandler.getMails());
                                removeBtnFrame.setVisibility(View.GONE);
                            }
                        })
                        .setCancelable(false);
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        removeBtn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                removeBtn.setActivated(false);
                Toast.makeText(getApplicationContext(), "Click once, please.",
                        Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    //Hiding Remove Button when list is empty
    @Override
    protected void onResume() {
        super.onResume();
        adapter.setMails(dataHandler.getMails());
        if (dataHandler.getMails().isEmpty()) {
            removeBtnFrame.setVisibility(View.GONE);
        } else {
            removeBtnFrame.setVisibility(View.VISIBLE);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dataHandler.close();
    }

    //Drafts list
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
            }
            Mail mail = getItem(position);

            ImageView mailListView = (ImageView) convertView.findViewById(R.id.item_icon);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
            Bitmap bitmap = BitmapFactory.decodeFile(mail.getFilePath(), options);
            mailListView.setImageBitmap(bitmap);

            TextView makeAdress = (TextView) convertView.findViewById(R.id.item_txtAdress);

            TextView makeSubject = (TextView) convertView.findViewById(R.id.item_txtSubject);

            TextView tempAdress = (TextView) convertView.findViewById(R.id.item_tempAdress);
            tempAdress.setText(mail.getTo());

            TextView tempSubject = (TextView) convertView.findViewById(R.id.item_tempSubject);
            tempSubject.setText(mail.getSubject());

            return convertView;
        }

        public void setMails(ArrayList<Mail> mails) {
            this.mails = mails;
            notifyDataSetChanged();
        }
    }

    //Button PLUS - New Message
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

}

