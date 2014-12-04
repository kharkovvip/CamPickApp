//package trainee.x_prt.campickapp.activity;
//
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.WindowManager;
//import android.widget.EditText;
//
//import trainee.x_prt.campickapp.R;
//
//public class TempChooseOptionDialog extends android.support.v4.app.DialogFragment {
//
//    private EditText mEditText;
//
//    public TempChooseOptionDialog() {
//        // Empty constructor required for DialogFragment
//    }
//
//    public static TempChooseOptionDialog newInstance(String title) {
//        TempChooseOptionDialog frag = new TempChooseOptionDialog();
//        Bundle args = new Bundle();
//        args.putString("title", title);
//        frag.setArguments(args);
//        return frag;
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_edit_name, container);
//        mEditText = (EditText) view.findViewById(R.id.txt_your_name);
//        String title = getArguments().getString("title", "Enter Name");
//        getDialog().setTitle(title);
//        // Show soft keyboard automatically
//        mEditText.requestFocus();
//        getDialog().getWindow().setSoftInputMode(
//                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
//        return view;
//    }
//}
