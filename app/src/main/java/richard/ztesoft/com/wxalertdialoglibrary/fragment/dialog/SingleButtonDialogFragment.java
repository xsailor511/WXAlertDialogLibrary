package richard.ztesoft.com.wxalertdialoglibrary.fragment.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.app.AlertDialog;
import android.support.v4.app.DialogFragment;
import android.util.Log;

import richard.ztesoft.com.wxalertdialoglibrary.R;
import richard.ztesoft.com.wxalertdialoglibrary.WXDialogConfig;

/**
 * Created by richard on 16/6/6.
 */
public class SingleButtonDialogFragment extends DialogFragment{
    public static final String FRAGMENT_MANAGER_TAG = "SingleButtonDialogFragment";
    public static final String TAG = "SingleButtonFragment";

    public void setOnSingleDialogButtonClickListener(OnSingleDialogButtonClickListener onSingleDialogButtonClickListener) {
        this.onSingleDialogButtonClickListener = onSingleDialogButtonClickListener;
    }

    private OnSingleDialogButtonClickListener onSingleDialogButtonClickListener;
    private static SingleButtonDialogFragment singleButtonDialogFragment;
    public static SingleButtonDialogFragment getInstance(OnSingleDialogButtonClickListener onSingleDialogButtonClickListener){
        if (singleButtonDialogFragment==null){
            singleButtonDialogFragment = new SingleButtonDialogFragment();
        }
        singleButtonDialogFragment.setOnSingleDialogButtonClickListener(onSingleDialogButtonClickListener);
        return singleButtonDialogFragment;
    }

    private String title;
    private String message;
    private int icon;
    private String positiveText;

    public void setInfos(String title,String message,int icon,String positiveText){
        this.title = title;
        this.message = message;
        this.icon = icon;
        this.positiveText = positiveText;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new AlertDialog.Builder(getActivity(), WXDialogConfig.DIALOG_THEME)
                .setTitle(title)
                .setMessage(message)
                .setIcon(icon)
                .setPositiveButton(positiveText, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (onSingleDialogButtonClickListener!=null){
                            onSingleDialogButtonClickListener.onPositiveButtonClicked();
                        }
                    }
                }).create();
        dialog.show();
        return dialog;
    }



    public interface OnSingleDialogButtonClickListener{
        void onPositiveButtonClicked();
    }
}
