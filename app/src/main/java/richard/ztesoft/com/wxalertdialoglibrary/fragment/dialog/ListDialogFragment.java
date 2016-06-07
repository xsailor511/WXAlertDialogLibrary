package richard.ztesoft.com.wxalertdialoglibrary.fragment.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.app.AlertDialog;
import android.util.Log;

import richard.ztesoft.com.wxalertdialoglibrary.R;
import richard.ztesoft.com.wxalertdialoglibrary.WXDialogConfig;

/**
 * Created by richard on 16/6/6.
 * 一个list弹框
 */
public class ListDialogFragment extends DialogFragment{

    public static final String FRAGMENT_MANAGER_TAG = "ListDialogFragment";
    public static final String TAG = "ListDialogFragment";

    private  OnListItemClickListener onListItemClickListener;

    public void setOnListItemClickListener(OnListItemClickListener onListItemClickListener) {
        this.onListItemClickListener = onListItemClickListener;
    }


    private static ListDialogFragment listDialogFragment;
    public static ListDialogFragment getInstance(OnListItemClickListener onListItemClickListener){
        if (listDialogFragment==null){
            listDialogFragment = new ListDialogFragment();
        }
        listDialogFragment.setOnListItemClickListener(onListItemClickListener);
        return listDialogFragment;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Dialog dialog = new AlertDialog.Builder(getActivity(), WXDialogConfig.DIALOG_THEME)
                .setTitle(title)
                .setIcon(icon)
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (onListItemClickListener!=null){
                            onListItemClickListener.onItemClicked(i,items[i]);
                        }
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //do nothing
                    }
                }).create();

        return dialog;
    }

    private String title;
    private int icon;
    private String[] items;

    public void setInfos(String title,int icon,String [] items){
        this.title = title;
        this.icon = icon;
        this.items = items;
    }

    public interface OnListItemClickListener{
        void onItemClicked(int item,String content);
    }

}
