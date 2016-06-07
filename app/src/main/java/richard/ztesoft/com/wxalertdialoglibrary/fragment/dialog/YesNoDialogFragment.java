package richard.ztesoft.com.wxalertdialoglibrary.fragment.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.util.Log;

import richard.ztesoft.com.wxalertdialoglibrary.R;
import richard.ztesoft.com.wxalertdialoglibrary.WXDialogConfig;

/**
 * Created by richard on 16/6/6.
 * 两个按钮的Fragment Dialog
 */
public class YesNoDialogFragment extends DialogFragment{

    public static final String FRAGMENT_MANAGER_TAG = "YesNoDialogFragment";
    public static final String TAG = "YesNoDialogFragment";

    public void setOnYesNoDialogButtonClickListener(OnYesNoDialogButtonClickListener onYesNoDialogButtonClickListener) {
        this.onYesNoDialogButtonClickListener = onYesNoDialogButtonClickListener;
    }

    private OnYesNoDialogButtonClickListener onYesNoDialogButtonClickListener;
    private static YesNoDialogFragment yesNoDialogFragment;
    public static YesNoDialogFragment getInstance(OnYesNoDialogButtonClickListener onYesNoDialogButtonClickListener){
        if (yesNoDialogFragment==null){
            yesNoDialogFragment = new YesNoDialogFragment();
        }
        yesNoDialogFragment.setOnYesNoDialogButtonClickListener(onYesNoDialogButtonClickListener);
        return yesNoDialogFragment;
    }


    private String title;
    private String message;
    private int icon;
    private String yesText;
    private String noText;

    public void setInfos(String title,String message,int icon,String yesText,String noText){
        this.title = title;
        this.message = message;
        this.icon = icon;
        this.yesText = yesText;
        this.noText = noText;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new AlertDialog.Builder(getActivity(), WXDialogConfig.DIALOG_THEME)
                .setTitle(title)
                .setMessage(message)
                .setIcon(icon)
                .setPositiveButton(yesText, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (onYesNoDialogButtonClickListener!=null){
                            onYesNoDialogButtonClickListener.onYesButtonClicked();
                        }
                    }
                }).setNegativeButton(noText, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (onYesNoDialogButtonClickListener!=null){
                            onYesNoDialogButtonClickListener.onNoButtonClicked();
                        }
                    }
                })
                .create();
        dialog.show();
        return dialog;
    }



    public interface OnYesNoDialogButtonClickListener{
        void onYesButtonClicked();
        void onNoButtonClicked();
    }
}
