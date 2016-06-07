package richard.ztesoft.com.wxalertdialoglibrary.fragment.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.util.Log;

import richard.ztesoft.com.wxalertdialoglibrary.CustomDialog;

/**
 * Created by richard on 16/6/6.
 */
public class YesNoSelfDesignDialogFragment extends DialogFragment{

    public static final String FRAGMENT_MANAGER_TAG = "YesNoSelfDesignDialogFragment";
    public static final String TAG = "YesNoSelfDesign";

    private OnYesNoSelfDesignDialogButtonClickListener onYesNoSelfDesignDialogButtonClickListener;

    public void setOnYesNoSelfDesignDialogButtonClickListener(OnYesNoSelfDesignDialogButtonClickListener onYesNoSelfDesignDialogButtonClickListener) {
        this.onYesNoSelfDesignDialogButtonClickListener = onYesNoSelfDesignDialogButtonClickListener;
    }

    private static YesNoSelfDesignDialogFragment yesNoSelfDesignDialogFragment;
    public static YesNoSelfDesignDialogFragment getInstance(OnYesNoSelfDesignDialogButtonClickListener onYesNoSelfDesignDialogButtonClickListener){
        if (yesNoSelfDesignDialogFragment==null){
            yesNoSelfDesignDialogFragment = new YesNoSelfDesignDialogFragment();
        }
        yesNoSelfDesignDialogFragment.setOnYesNoSelfDesignDialogButtonClickListener(onYesNoSelfDesignDialogButtonClickListener);
        return yesNoSelfDesignDialogFragment;
    }

    private String title;
    private String message;
    private String yesText;
    private String noText;

    public void setInfos(String title,String message,String yesText,String noText){
        this.title = title;
        this.message = message;
        this.yesText = yesText;
        this.noText = noText;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        CustomDialog.Builder builder = new CustomDialog.Builder(getActivity());
        builder.setMessage(message);
        builder.setTitle(title);
        builder.setPositiveButton(yesText, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                if (onYesNoSelfDesignDialogButtonClickListener!=null){
                    onYesNoSelfDesignDialogButtonClickListener.onMyYesButtonClicked();
                }
            }
        });

        builder.setNegativeButton(noText,
                new android.content.DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        if (onYesNoSelfDesignDialogButtonClickListener!=null){
                            onYesNoSelfDesignDialogButtonClickListener.onMyNoButtonClicked();
                        }
                    }
                });

        Dialog dialog = builder.create();
        dialog.setCancelable(false);
        return dialog;
    }

    public interface OnYesNoSelfDesignDialogButtonClickListener{
        void onMyYesButtonClicked();
        void onMyNoButtonClicked();
    }
}
