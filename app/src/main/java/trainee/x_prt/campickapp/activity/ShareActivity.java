package trainee.x_prt.campickapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import trainee.x_prt.campickapp.R;

public class ShareActivity extends Activity implements OnClickListener {

    private static final int ACTIVITY_SELECT_IMAGE = 1;
    private static final int REQ_CODE_PICK_IMAGE = 2;
    ImageView photoPick;
    TextView fieldTo, fieldSubject, fieldMsg;
    Button buttonSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

        photoPick = (ImageView) findViewById(R.id.photoPick);
        fieldTo = (TextView) findViewById(R.id.fieldTo);
        fieldSubject = (TextView) findViewById(R.id.fieldSubject);
        fieldMsg = (TextView) findViewById(R.id.fieldMsg);
        buttonSend = (Button) findViewById(R.id.buttonSend);

        photoPick.setOnClickListener(this);
        buttonSend.setOnClickListener(this);
    }


    public void onClick(View v) {

        String to = fieldTo.getText().toString();
        String subject = fieldSubject.getText().toString();
        String message = fieldMsg.getText().toString();

        switch (v.getId()) {

            case R.id.photoPick:
                Intent i = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(i, ACTIVITY_SELECT_IMAGE);
                break;

            case R.id.buttonSend:
                //тут будет отправка...
                break;
        }
    }

//    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
//        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
//
//        switch (requestCode) {
//            case REQ_CODE_PICK_IMAGE:
//                if (resultCode == RESULT_OK) {
//                    Uri selectedImage = imageReturnedIntent.getData();
//                    String[] filePathColumn = {MediaStore.Images.Media.DATA};
//
//                    Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
//                    cursor.moveToFirst();
//
//                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
//                    String filePath = cursor.getString(columnIndex);
//                    cursor.close();
//                    Bitmap yourSelectedImage = BitmapFactory.decodeFile(filePath);
//                }
//        }
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQ_CODE_PICK_IMAGE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            photoPick.setImageBitmap(imageBitmap);
        }
    }

}