package Dialogs;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import trainee.x_prt.campickapp.R;


public class DialogBody extends FragmentActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        showAlertDialog();
    }

    private void showAlertDialog() {
        FragmentManager fm = getSupportFragmentManager();
        DialogRealization alertDialog = DialogRealization.newInstance("Choose");
        alertDialog.show(fm, "fragment_alert");
    }
}

