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
 */
public class SingleChoiceItemsDialogFragment extends DialogFragment {
    public static final String FRAGMENT_MANAGER_TAG = "SingleChoiceItemsDialogFragment";

    private OnSingleChoiceSelectedListener onSingleChoiceSelectedListener;

    public void setOnSingleChoiceSelectedListener(OnSingleChoiceSelectedListener onSingleChoiceSelectedListener) {
        this.onSingleChoiceSelectedListener = onSingleChoiceSelectedListener;
    }

    private static SingleChoiceItemsDialogFragment singleChoiceItemsDialogFragment;
    public static SingleChoiceItemsDialogFragment getInstance(OnSingleChoiceSelectedListener onSingleChoiceSelectedListener){
        if (singleChoiceItemsDialogFragment==null){
            singleChoiceItemsDialogFragment = new SingleChoiceItemsDialogFragment();
        }
        singleChoiceItemsDialogFragment.setOnSingleChoiceSelectedListener(onSingleChoiceSelectedListener);
        return singleChoiceItemsDialogFragment;
    }

    private String title;
    private String[] items;
    private int radio_button_index = 0;
    private int temp_radio_index = 0 ;
    public void setInfos(String title,String[]items,int currentIndex){
        this.title = title;
        this.items = items;
        radio_button_index = currentIndex;
        temp_radio_index = radio_button_index;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new AlertDialog.Builder(getActivity(), WXDialogConfig.DIALOG_THEME)
                .setTitle(title)
                .setIcon(R.mipmap.ic_launcher)
                .setSingleChoiceItems(items,radio_button_index, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        temp_radio_index = i;
                    }
                }).setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        radio_button_index = temp_radio_index;
                        if (onSingleChoiceSelectedListener!=null){
                            onSingleChoiceSelectedListener.onSingleChoiceSelected(radio_button_index,items[radio_button_index]);
                        }
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        temp_radio_index = radio_button_index;
                    }
                }).create();
        return dialog;
    }

    public interface OnSingleChoiceSelectedListener{
        void onSingleChoiceSelected(int index,String content);
    }
}
