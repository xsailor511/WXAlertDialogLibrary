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
public class MultiChoiceItemsDialogFragment extends DialogFragment {
    public static final String FRAGMENT_MANAGER_TAG = "MultiChoiceItemsDialogFragment";

    private OnMultiChoiceSelectedListener onMultiChoiceSelectedListener;

    public void setOnMultiChoiceSelectedListener(OnMultiChoiceSelectedListener onMultiChoiceSelectedListener) {
        this.onMultiChoiceSelectedListener = onMultiChoiceSelectedListener;
    }

    private static MultiChoiceItemsDialogFragment multiChoiceItemsDialogFragment;

    public static MultiChoiceItemsDialogFragment getInstance(OnMultiChoiceSelectedListener onMultiChoiceSelectedListener){
        if (multiChoiceItemsDialogFragment==null){
            multiChoiceItemsDialogFragment = new MultiChoiceItemsDialogFragment();
        }
        multiChoiceItemsDialogFragment.setOnMultiChoiceSelectedListener(onMultiChoiceSelectedListener);
        return multiChoiceItemsDialogFragment;
    }
    private String items[];
    private boolean initState[] ;

    private String title;
    private int icon;
    public void setInfos(String title,String[] items,boolean state[],int icon){
        this.title = title;
        initState = new boolean[state.length];
        copy(state,initState);
        this.items = items;
        this.icon = icon;
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new AlertDialog.Builder(getActivity(), WXDialogConfig.DIALOG_THEME)
                .setTitle(title)
                .setIcon(icon)
                .setMultiChoiceItems(items, initState, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        //这里arrayFruitSelected[i]就会被自动修改了,不需要手动arrayFruitSelected[i]=b了
                    }
                }).setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {

                        if (onMultiChoiceSelectedListener!=null){
                            onMultiChoiceSelectedListener.onMultiChoiceSelected(initState);
                        }
                        MultiChoiceItemsDialogFragment.this.dismiss();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int whichButton) {
                        MultiChoiceItemsDialogFragment.this.dismiss();
                    }
                }).create();
        return dialog;
    }


    public interface OnMultiChoiceSelectedListener{
        void onMultiChoiceSelected(boolean state[]);
    }

    /**
     * 复制状态数组
     * @param stateFrom 源state数组
     * @param stateTo 被赋值的state数组
     */
    public void copy(boolean stateFrom[],boolean stateTo[]){
        if (stateFrom==null)
            return;
        for (int i=0;i<stateFrom.length;i++){
            stateTo[i] =  stateFrom[i];
        }
    }
}
