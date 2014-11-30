package trainee.x_prt.campickapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import trainee.x_prt.campickapp.R;

public class DraftsActivity extends Activity implements OnClickListener {
    private static final int PICK_IMAGE = 1;
    Button buttonSend;
    EditText textTo, textSubject, textMessage;
    private String imageFilePath;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drafts);
        buttonSend = (Button) findViewById(R.id.buttonSend);
        textTo = (EditText) findViewById(R.id.editTextTo);
        textSubject = (EditText) findViewById(R.id.editTextSubject);
        textMessage = (EditText) findViewById(R.id.editTextMessage);
        buttonSend.setOnClickListener(this);
        findViewById(R.id.photoPick).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
            }
        });
    }

    @Override
    public void onClick(View v) {
        String to = textTo.getText().toString();
        String subject = textSubject.getText().toString();
        String message = textMessage.getText().toString();

        Intent email = new Intent(Intent.ACTION_SEND);
        email.putExtra(Intent.EXTRA_EMAIL, new String[]{to});
        email.putExtra(Intent.EXTRA_SUBJECT, subject);
        email.putExtra(Intent.EXTRA_TEXT, message);
        email.putExtra(Intent.EXTRA_STREAM, Uri.parse(imageFilePath));
        email.setType("text/plain");
        email.setType("image/*");
        startActivity(Intent.createChooser(email, "Choose an Email client:"));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_IMAGE && data != null && data.getData() != null) {
            Uri uri = data.getData();

            //User had pick an image.
            Cursor cursor = getContentResolver().query(uri, new String[]{android.provider.MediaStore.Images.ImageColumns.DATA}, null, null, null);
            cursor.moveToFirst();

            //Link to the image
            imageFilePath = cursor.getString(0);
            cursor.close();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}