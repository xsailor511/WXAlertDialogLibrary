package richard.ztesoft.com.wxalertdialoglibrary.fragment.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

import richard.ztesoft.com.wxalertdialoglibrary.WXDialogConfig;

/**
 * Created by richard on 16/6/6.
 */
public class ThreeButtonsDialogFragment extends DialogFragment {
    public static final String FRAGMENT_MANAGER_TAG = "ThreeButtonsDialogFragment";
    public static final String TAG = "3ButtonsDialogFragment";

    private OnThreeButtonsClickedListener onThreeButtonsClickedListener;

    public void setOnThreeButtonsClickedListener(OnThreeButtonsClickedListener onThreeButtonsClickedListener) {
        this.onThreeButtonsClickedListener = onThreeButtonsClickedListener;
    }

    private static ThreeButtonsDialogFragment threeButtonsDialogFragment;
    public static ThreeButtonsDialogFragment getInstance(OnThreeButtonsClickedListener onThreeButtonsClickedListener){
        if (threeButtonsDialogFragment==null){
            threeButtonsDialogFragment = new ThreeButtonsDialogFragment();
        }
        threeButtonsDialogFragment.setOnThreeButtonsClickedListener(onThreeButtonsClickedListener);
        return threeButtonsDialogFragment;
    }

    private String title;
    private String message;
    private int icon;
    private String positiveText;
    private String negativeText;
    private String neutralText;

    public void setInfos(String title,String message,int icon,String positiveText,String negativeText,String neutralText){
        this.title = title;
        this.message = message;
        this.icon = icon;
        this.positiveText = positiveText;
        this.negativeText = negativeText;
        this.neutralText = neutralText;
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
                        if (onThreeButtonsClickedListener!=null){
                            onThreeButtonsClickedListener.on3positiveButtonClicked();
                        }
                    }
                }).setNegativeButton(negativeText, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (onThreeButtonsClickedListener!=null){
                            onThreeButtonsClickedListener.on3negativeButtonClicked();
                        }
                    }
                }).setNeutralButton(neutralText, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (onThreeButtonsClickedListener!=null){
                            onThreeButtonsClickedListener.on3neutralButtonClicked();
                        }
                    }
                })
                .create();
        dialog.show();
        return dialog;
    }

    public interface OnThreeButtonsClickedListener{
        void on3positiveButtonClicked();
        void on3negativeButtonClicked();
        void on3neutralButtonClicked();
    }
}
