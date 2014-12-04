//package trainee.x_prt.campickapp.activity;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.graphics.Bitmap;
//import android.os.Bundle;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import trainee.x_prt.campickapp.R;
//
//public class ShareActivity extends Activity implements OnClickListener {
//
//    ImageView photoPick;
//    TextView fieldTo, fieldSubject, fieldMsg;
//    Button buttonSend;
//    public final int CAMERA_RESULT = 0;
//    static final int REQUEST_IMAGE_CAPTURE = 1;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_share);
//
//        buttonSend = (Button) findViewById(R.id.buttonSend);
//        photoPick = (ImageView) findViewById(R.id.photoPick);
//        fieldTo = (TextView) findViewById(R.id.fieldTo);
//        fieldSubject = (TextView) findViewById(R.id.fieldSubject);
//        fieldMsg = (TextView) findViewById(R.id.fieldMsg);
//
//        photoPick.setOnClickListener(this);
//        buttonSend.setOnClickListener(this);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (requestCode == CAMERA_RESULT) {
//            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
//            photoPick.setImageBitmap(thumbnail);
//        }
//    }
//
//
//    public void onClick(View v) {
//
//
//        String to = fieldTo.getText().toString();
//        String subject = fieldSubject.getText().toString();
//        String message = fieldMsg.getText().toString();
//        int requestCode;
//
//        if (v == photoPick) {
//            switch requestCode {
//                case 0:
//                    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                    startActivityForResult(cameraIntent, 0);
//                    break;
//                case 1:
//                    Intent pickPhoto = new Intent(Intent.ACTION_PICK,
//                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                    startActivityForResult(pickPhoto,1);
//
//
//                    break;
//            }
//        } else if (v == buttonSend) {
//            Intent email = new Intent(Intent.ACTION_SEND);
//            email.putExtra(Intent.EXTRA_EMAIL, new String[]{to});
//            // email.putExtra(Intent.EXTRA_CC, new String[]{ to});
//            // email.putExtra(Intent.EXTRA_BCC, new String[]{to});
//            email.putExtra(Intent.EXTRA_SUBJECT, subject);
//            email.putExtra(Intent.EXTRA_TEXT, message);
//            email.setType("message/rfc822");
//
//            startActivity(Intent.createChooser(email, "Choose an Email client :"));
//        }
//    }
//
//
//}
//
