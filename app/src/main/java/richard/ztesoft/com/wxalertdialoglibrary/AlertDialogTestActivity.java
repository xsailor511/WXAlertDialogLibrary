package richard.ztesoft.com.wxalertdialoglibrary;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 原生 AlertDialog 测试
 */
public class AlertDialogTestActivity extends AppCompatActivity {

    private static String TAG = "AlertDialogTestActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_dialog_test);
        ButterKnife.bind(this);
        setTitle("测试Custom Dialog");
        //显示actionbar的返回按钮
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 测试自定义确认，取消dialog
     */
    @OnClick(R.id.alert_dialog_self_design)
    public void showSelfDesignDialog(){
        CustomDialog.Builder builder = new CustomDialog.Builder(this);
        builder.setMessage("这个就是自定义的提示框");
        builder.setTitle("提示");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Log.i(TAG,"setPositiveButton确定");
                //设置你的操作事项
            }
        });

        builder.setNegativeButton("取消",
                new android.content.DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Log.i(TAG,"setNegativeButton取消");
                    }
                });

        Dialog dialog = builder.create();
        dialog.setCancelable(false);
        dialog.show();
    }

    /**
     * 测试系统AlertDialog message
     */
    @OnClick(R.id.alert_dialog_message)
    public void showAlertDialogMessage(){
        Dialog dialog = new AlertDialog.Builder(this,WXDialogConfig.DIALOG_THEME)
                .setTitle("请注意")
                .setMessage("网络已经断开，请连接网络")
                .setIcon(R.mipmap.ic_launcher)
                .create();
        dialog.setCancelable(true);
        dialog.show();
    }

    /**
     *
     * 显示单个按钮的dialog
     */
    @OnClick(R.id.alert_dialog_single_button)
    public void showAlertDialogSingleButton(){
        Dialog dialog = new AlertDialog.Builder(this,WXDialogConfig.DIALOG_THEME)
                .setTitle("消息")
                .setMessage("资源绑定成功！")
                .setIcon(R.mipmap.ic_launcher)
                .setPositiveButton("好的", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.i(TAG,"showAlertDialogSingleButton好的");
                    }
                }).create();
        dialog.setCancelable(false);
        dialog.show();
    }


    /**
     * 测试系统AlertDialog 确认，取消
     */
    @OnClick(R.id.alert_dialog_yes_no)
    public void showAlertDialogYesNo(){
        Dialog dialog = new AlertDialog.Builder(this,WXDialogConfig.DIALOG_THEME)
                .setTitle("消息")
                .setMessage("是否删除文件？")
                .setIcon(R.mipmap.ic_launcher)
                .setPositiveButton("删除", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.i(TAG,"showAlertDialogSingleButton删除");
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.i(TAG,"showAlertDialogSingleButton取消");
                    }
                })

                .create();
        dialog.setCancelable(false);
        dialog.show();
    }

    /**
     * 三个按钮的AlertDialog,删除，取消，详情
     */
    @OnClick(R.id.alert_dialog_three_buttons)
    public void showThreeButtonsAlertDialog(){
        Dialog dialog = new AlertDialog.Builder(this,WXDialogConfig.DIALOG_THEME)
                .setTitle("消息")
                .setMessage("是否删除文件？")
                .setIcon(R.mipmap.ic_launcher)
                .setPositiveButton("删除", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.i(TAG,"showAlertDialogSingleButton删除");
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.i(TAG,"showAlertDialogSingleButton取消");
                    }
                }).setNeutralButton("详情", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.i(TAG,"showAlertDialogSingleButton详情");
                    }
                })

                .create();
        dialog.setCancelable(false);
        dialog.show();
    }

    /**
     * 选择列表AlertDialog
     * 由于列表可能过长，导致无法点击背景来使对话框消失，
     * 必须要添加一个取消按钮
     */
    @OnClick(R.id.alert_dialog_list)
    public void showDialogList(){
        final String arrayFruit[] = new String[]{
                "苹果", "橘子", "草莓", "橘子", "草莓",
                "橘子", "草莓", "橘子", "草莓", "橘子",
                "草莓", "橘子", "草莓", "橘子", "草莓",
                "橘子", "草莓", "橘子", "草莓", "香蕉"
        };

        Dialog dialog = new AlertDialog.Builder(this,WXDialogConfig.DIALOG_THEME)
                .setTitle("你喜欢什么水果？")
                .setIcon(R.mipmap.ic_launcher)
                .setItems(arrayFruit, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.i(TAG,"your choose fruit: "+arrayFruit[i]);
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.i(TAG,"choose fruit canceled");
                    }
                }).create();

        dialog.setCancelable(true);
        dialog.show();
    }

    private int radio_button_index = 0;
    private int temp_radio_index = 0 ;

    /**
     * 单选按钮列表AlertDialog
     */
    @OnClick(R.id.alert_dialog_radio_buttons)
    public void showAlertDialogRadioButtons(){
        final String arrayFruit[] = new String[]{
                "苹果", "橘子", "草莓", "橘子", "草莓",
        };

        Dialog dialog = new AlertDialog.Builder(this,WXDialogConfig.DIALOG_THEME)
                .setTitle("你喜欢什么水果？")
                .setIcon(R.mipmap.ic_launcher)
                .setSingleChoiceItems(arrayFruit,radio_button_index, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.i(TAG,"your choose fruit: "+arrayFruit[i]);
                        temp_radio_index = i;
                    }
                }).setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.i(TAG,"your choose fruit radio: "+arrayFruit[radio_button_index]);
                        Log.i(TAG,"selected button = "+i);
                        radio_button_index = temp_radio_index;
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.i(TAG,"choose fruit canceled");
                        Log.i(TAG,"selected button = "+i);
                        temp_radio_index = radio_button_index;
                    }
                }).create();

        dialog.setCancelable(false);
        dialog.show();
    }


    final String[] arrayFruit = new String[] { "苹果", "橘子", "草莓", "香蕉" };
    final boolean[] arrayFruitSelected = new boolean[] {false, false, false, false};
    //arrayFruitSelected_temp用于点击取消之后恢复arrayFruitSelected
    final boolean[] arrayFruitSelected_temp = new boolean[] {false, false, false, false};

    /**
     * checkbox 多选Dialog
     */
    @OnClick(R.id.alert_dialog_check_box)
    public void showCheckBoxAlertBox(){


        Dialog dialog = new AlertDialog.Builder(this,WXDialogConfig.DIALOG_THEME)
                .setTitle("你喜欢什么水果？")
                .setIcon(R.mipmap.ic_launcher)
                .setMultiChoiceItems(arrayFruit, arrayFruitSelected, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        Log.i(TAG,"item " + i + "checked");
                        //这里arrayFruitSelected[i]就会被自动修改了,不需要手动arrayFruitSelected[i]=b了
                    }
                }).setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        StringBuffer state = new StringBuffer().append("/n");
                        //点击确认之后就修改状态数据，将正式数组赋给临时数组,保持二者一致，以便用来恢复
                        for (int i=0;i<arrayFruitSelected.length;i++){
                            arrayFruitSelected_temp[i] = arrayFruitSelected[i];
                        }
                        //log输入选择状态
                        for (int i=0;i<arrayFruitSelected.length;i++){
                            state.append(i).append(arrayFruitSelected[i]).append("\n");
                        }
                        Log.i(TAG,"your checked fruits: "+state.toString());
                        Log.i(TAG,"selected button = "+which);

                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int whichButton) {
                        StringBuffer state = new StringBuffer().append("/n");
                        Log.i(TAG,"choose fruit canceled");
                        //点击取消之后就修改状态数据，将正式数组恢复到和临时数组相同的值
                        for (int i=0;i<arrayFruitSelected.length;i++){
                            arrayFruitSelected[i] = arrayFruitSelected_temp[i];
                        }

                        //log输入选择状态
                        for (int i=0;i<arrayFruitSelected.length;i++){
                            state.append(i).append(arrayFruitSelected[i]).append("\n");
                        }
                        Log.i(TAG,"your checked fruits: "+state.toString());
                        Log.i(TAG,"selected button = "+whichButton);
                    }
                }).create();

        //设置无法自动消失，必须点按取消和确认按钮
        dialog.setCancelable(false);
        dialog.show();
    }

    /**
     * 输入框dialog
     *
     */
    @OnClick(R.id.alert_dialog_username_password)
    public void showUsernamePasswordDialog(){
        View inputView = LayoutInflater.from(this).inflate(R.layout.username_password_dialog,null);

        final EditText usernameET = (EditText)inputView.findViewById(R.id.username);
        final EditText passwordET = (EditText)inputView.findViewById(R.id.password);
        Dialog dialog = new AlertDialog.Builder(this,WXDialogConfig.DIALOG_THEME)
                .setTitle("你喜欢什么水果？")
                .setIcon(R.mipmap.ic_launcher)
                .setView(inputView)
                .setPositiveButton("登陆", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.i(TAG,"username = "+usernameET.getText().toString());
                        Log.i(TAG,"password = "+passwordET.getText().toString());
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.i(TAG,"input login canceled");
                    }
                })
                .create();

        dialog.setCancelable(false);
        dialog.show();

    }


    @OnClick(R.id.alert_dialog_second_dialog)
    public void showSecondDialog(){
        Dialog dialog = new AlertDialog.Builder(this,WXDialogConfig.DIALOG_THEME)
                .setTitle("改端口确认")
                .setIcon(R.mipmap.ic_launcher)
                .setMessage("这是第一个弹框")
                .setPositiveButton("修改", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Dialog dialog2 = new AlertDialog.Builder(AlertDialogTestActivity.this,WXDialogConfig.DIALOG_THEME)
                                .setTitle("请选择段端口")
                                .setIcon(R.mipmap.ic_launcher)
                                .setMessage("请选择端口，第二个dialog")
                                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Log.i(TAG,"second dialog sure");
                                    }
                                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Log.i(TAG,"second dialog canceled");
                                    }
                                })
                                .create();
                        dialog2.setCancelable(false);
                        dialog2.show();
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.i(TAG,"first dialog canceled");
                    }
                }).create();
        dialog.setCancelable(false);
        dialog.show();
    }

}
