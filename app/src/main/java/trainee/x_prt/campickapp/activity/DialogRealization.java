package trainee.x_prt.campickapp.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;

import trainee.x_prt.campickapp.R;

public class DialogRealization extends android.support.v4.app.DialogFragment {

    public DialogRealization() {
        // Empty constructor required for DialogFragment
    }

    public static DialogRealization newInstance(String title) {
        DialogRealization frag = new DialogRealization();
        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);
        return frag;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setItems(R.array.numbers, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(cameraIntent, ShareActivity.REQUEST_IMAGE_CAPTURE);
                        break;
                    case 1:
                        Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(pickPhoto, 1);
                        break;
                    default:
                        //cancel dialog
                }
            }
        });
        return builder.create();
    }
}