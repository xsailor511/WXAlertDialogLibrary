package richard.ztesoft.com.wxalertdialoglibrary.fragment.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import richard.ztesoft.com.wxalertdialoglibrary.R;
import richard.ztesoft.com.wxalertdialoglibrary.WXDialogConfig;

/**
 * Created by richard on 16/6/6.
 */
public class TwoEditTextDialogFragment extends DialogFragment {

    public static final String FRAGMENT_MANAGER_TAG = "TwoEditTextDialogFragment";

    private OnInputFinishedListener onInputFinishedListener;

    public void setOnInputFinishedListener(OnInputFinishedListener onInputFinishedListener) {
        this.onInputFinishedListener = onInputFinishedListener;
    }

    private static TwoEditTextDialogFragment twoEditTextDialogFragment;
    public static TwoEditTextDialogFragment getInstance(OnInputFinishedListener onInputFinishedListener){
        if (twoEditTextDialogFragment==null){
            twoEditTextDialogFragment = new TwoEditTextDialogFragment();
        }
        twoEditTextDialogFragment.setOnInputFinishedListener(onInputFinishedListener);
        return twoEditTextDialogFragment;
    }

    private String title;
    private int icon;
    private String hint1;
    private String hint2;
    private int inputType1;
    private int inputType2;
    public void setInfos(String title,int icon,String hint1,String hint2,int inputType1,int inputType2){
        this.title = title;
        this.icon = icon;
        this.hint1 = hint1;
        this.hint2 = hint2;
        this.inputType1 = inputType1;
        this.inputType2 = inputType2;
    }

    private EditText usernameET ;
    private EditText passwordET ;
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View inputView = LayoutInflater.from(getActivity()).inflate(R.layout.username_password_dialog,null);

        usernameET = (EditText)inputView.findViewById(R.id.username);
        passwordET = (EditText)inputView.findViewById(R.id.password);
        usernameET.setHint(hint1);
        passwordET.setHint(hint2);

        usernameET.setInputType(inputType1);
        passwordET.setInputType(inputType2);

        Dialog dialog = new AlertDialog.Builder(getActivity(), WXDialogConfig.DIALOG_THEME)
                .setTitle(title)
                .setIcon(icon)
                .setView(inputView)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(onInputFinishedListener!=null){
                            onInputFinishedListener.onInputFinished(usernameET.getText().toString(),passwordET.getText().toString());
                        }
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        TwoEditTextDialogFragment.this.dismiss();
                    }
                })
                .create();

        return dialog;
    }

    public interface OnInputFinishedListener{
        void onInputFinished(String firstText,String secondText);
    }
}
