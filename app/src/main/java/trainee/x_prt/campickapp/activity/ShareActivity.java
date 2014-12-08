package trainee.x_prt.campickapp.activity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import trainee.x_prt.campickapp.R;


public class ShareActivity extends FragmentActivity {

    public static final int REQUEST_IMAGE_CAPTURE = 1;
    ImageView photoPick;
    Button buttonSend;
    EditText textTo, textSubject, textMessage;
    private String filePath;


    //Button SEND realization:
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        getActionBar().setTitle(R.string.share);

        buttonSend = (Button) findViewById(R.id.buttonSend);
        textTo = (EditText) findViewById(R.id.editTextTo);
        textSubject = (EditText) findViewById(R.id.editTextSubject);
        textMessage = (EditText) findViewById(R.id.editTextMessage);
        buttonSend.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String to = textTo.getText().toString();
                String subject = textSubject.getText().toString();
                String message = textMessage.getText().toString();
                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{to});
                email.putExtra(Intent.EXTRA_SUBJECT, subject);
                email.putExtra(Intent.EXTRA_TEXT, message);
                email.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(filePath)));
                email.setType("image/*");
                startActivity(Intent.createChooser(email, "Choose an Email client :"));
            }
        });

//      PhotoPick realization:
        photoPick = (ImageView) findViewById(R.id.photoPick);
        photoPick.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                FragmentManager fm = getSupportFragmentManager();
                DialogRealization alertDialog = DialogRealization.newInstance("Some title");
                alertDialog.show(fm, "fragment_alert");
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap;
            if (extras == null) {
                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                Cursor cursor = getContentResolver().query(
                        selectedImage, filePathColumn, null, null, null);
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                filePath = cursor.getString(columnIndex);
                cursor.close();
                imageBitmap = BitmapFactory.decodeFile(filePath);
            } else {
                imageBitmap = (Bitmap) extras.get("data");
                saveBitmap(imageBitmap);
            }
            photoPick.setImageBitmap(imageBitmap);
        }
    }

    private void saveBitmap(Bitmap imageBitmap) {
        try {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();
            filePath = Environment.getExternalStorageDirectory() + "/_camera.png";
            FileOutputStream fo = new FileOutputStream(new File(filePath));
            fo.write(byteArray);
            fo.flush();
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
