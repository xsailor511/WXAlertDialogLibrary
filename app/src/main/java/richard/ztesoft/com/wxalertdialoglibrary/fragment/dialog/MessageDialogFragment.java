package richard.ztesoft.com.wxalertdialoglibrary.fragment.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.app.AlertDialog;

import richard.ztesoft.com.wxalertdialoglibrary.R;
import richard.ztesoft.com.wxalertdialoglibrary.WXDialogConfig;

/**
 * Created by richard on 16/6/4.
 */
public class MessageDialogFragment extends DialogFragment {

    public static final String FRAGMENT_MANAGER_TAG = "MessageDialogFragment";
    private static MessageDialogFragment messageDialogFragment;
    public static MessageDialogFragment getInstance(){
        if (messageDialogFragment==null){
            messageDialogFragment = new MessageDialogFragment();
        }
        return messageDialogFragment;
    }

    private String title;
    private String message;
    private int icon;

    public void setInfos(String title,String message,int icon){
        this.title = title;
        this.message = message;
        this.icon = icon;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new AlertDialog.Builder(getActivity(), WXDialogConfig.DIALOG_THEME)
                .setTitle(title)
                .setMessage(message)
                .setIcon(icon)
                .create();
        return dialog;
    }


}
