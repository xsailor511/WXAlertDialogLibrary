package richard.ztesoft.com.wxalertdialoglibrary;

import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.MenuItem;

import butterknife.ButterKnife;
import butterknife.OnClick;
import richard.ztesoft.com.wxalertdialoglibrary.fragment.dialog.ListDialogFragment;
import richard.ztesoft.com.wxalertdialoglibrary.fragment.dialog.MessageDialogFragment;
import richard.ztesoft.com.wxalertdialoglibrary.fragment.dialog.MultiChoiceItemsDialogFragment;
import richard.ztesoft.com.wxalertdialoglibrary.fragment.dialog.SingleButtonDialogFragment;
import richard.ztesoft.com.wxalertdialoglibrary.fragment.dialog.SingleChoiceItemsDialogFragment;
import richard.ztesoft.com.wxalertdialoglibrary.fragment.dialog.ThreeButtonsDialogFragment;
import richard.ztesoft.com.wxalertdialoglibrary.fragment.dialog.TwoEditTextDialogFragment;
import richard.ztesoft.com.wxalertdialoglibrary.fragment.dialog.YesNoDialogFragment;
import richard.ztesoft.com.wxalertdialoglibrary.fragment.dialog.YesNoSelfDesignDialogFragment;

/**
 * 测试Fragment Dialog ,这些FragmentDialog使用onCreateDialog()方法创建
 * 为啥要用Fragment包装一下？因为AlertDialog无法实现生命周期等一些功能
 */
public class FragmentDialogTestActivity extends AppCompatActivity implements
        SingleButtonDialogFragment.OnSingleDialogButtonClickListener,
        YesNoDialogFragment.OnYesNoDialogButtonClickListener,
        ThreeButtonsDialogFragment.OnThreeButtonsClickedListener,
        YesNoSelfDesignDialogFragment.OnYesNoSelfDesignDialogButtonClickListener,
        ListDialogFragment.OnListItemClickListener,
        SingleChoiceItemsDialogFragment.OnSingleChoiceSelectedListener,
        MultiChoiceItemsDialogFragment.OnMultiChoiceSelectedListener,
        TwoEditTextDialogFragment.OnInputFinishedListener{

    private static String TAG = "FragmentDialogTestActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_dialog_test);
        ButterKnife.bind(this);
        setTitle("测试Fragment Dialog");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(item.getItemId() == android.R.id.home)
        {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.fragment_alert_dialog_self_design)
    public void fragmentSelfDesignDialog(){
        YesNoSelfDesignDialogFragment yesNoSelfDesignDialogFragment = YesNoSelfDesignDialogFragment.getInstance(this);
        yesNoSelfDesignDialogFragment.setInfos("this is titile","some message","yes","no");
        yesNoSelfDesignDialogFragment.setCancelable(false);
        yesNoSelfDesignDialogFragment.show(getSupportFragmentManager(),YesNoSelfDesignDialogFragment.FRAGMENT_MANAGER_TAG);
    }

    /**
     * 测试fragment dialog message
     */
    @OnClick(R.id.fragment_alert_dialog_message)
    public void fragmentMessageDialog(){
        //保存全局配置
        int tempTheme = WXDialogConfig.DIALOG_THEME;
        WXDialogConfig.DIALOG_THEME = R.style.AlertDialogCustom;
        MessageDialogFragment messageDialogFragment = MessageDialogFragment.getInstance();
        messageDialogFragment.setInfos("消息","网络已经断开",R.mipmap.ic_launcher);
        messageDialogFragment.show(getSupportFragmentManager(), MessageDialogFragment.FRAGMENT_MANAGER_TAG);
        //恢复全局配置
        WXDialogConfig.DIALOG_THEME = tempTheme;

    }


    /**
     *
     * 显示单个按钮的fragment dialog
     */
    @OnClick(R.id.fragment_alert_dialog_single_button)
    public void fragmentDialogSingleButton(){
        SingleButtonDialogFragment singleButtonDialogFragment=SingleButtonDialogFragment.getInstance(this);
        singleButtonDialogFragment.setInfos("请注意","删除失败",R.mipmap.ic_launcher,"确认");
        singleButtonDialogFragment.show(getSupportFragmentManager(),SingleButtonDialogFragment.FRAGMENT_MANAGER_TAG);
    }


    /**
     * 显示两个按钮的fragment dialog
     */
    @OnClick(R.id.fragment_alert_dialog_yes_no)
    public void fragmentYesNoDialog(){
        YesNoDialogFragment yesNoDialogFragment = YesNoDialogFragment.getInstance(this);
        yesNoDialogFragment.setInfos("删除确认","该文件可能在使用中，是否删除？",R.mipmap.ic_launcher,"是","否");
        yesNoDialogFragment.setCancelable(false);
        yesNoDialogFragment.show(getSupportFragmentManager(),YesNoDialogFragment.FRAGMENT_MANAGER_TAG);
    }

    /**
     * 三个按钮的Fragment dialog
     */
    @OnClick(R.id.fragment_alert_dialog_three_buttons)
    public void fragment3ButtonsDialog(){
        ThreeButtonsDialogFragment threeButtonsDialogFragment = ThreeButtonsDialogFragment.getInstance(this);
        threeButtonsDialogFragment.setInfos("确认添加吗？","请确认",R.mipmap.ic_launcher,"yes","no","detail");
        threeButtonsDialogFragment.setCancelable(false);
        threeButtonsDialogFragment.show(getSupportFragmentManager(),ThreeButtonsDialogFragment.FRAGMENT_MANAGER_TAG);
    }

    /**
     * list列表Fragment Dialog测试
     */
    @OnClick(R.id.fragment_alert_dialog_list)
    public void fragmentListDialog(){
        final String arrayFruit[] = new String[]{
                "苹果", "橘子", "草莓", "橘子", "草莓",
                "橘子", "草莓", "橘子", "草莓", "橘子",
                "草莓", "橘子", "草莓", "橘子", "草莓",
                "橘子", "草莓", "橘子", "草莓", "香蕉"
        };

        ListDialogFragment listDialogFragment = ListDialogFragment.getInstance(this);
        listDialogFragment.setInfos("你喜欢那种水果",R.mipmap.ic_launcher,arrayFruit);
        listDialogFragment.setCancelable(false);
        listDialogFragment.show(getSupportFragmentManager(),ListDialogFragment.FRAGMENT_MANAGER_TAG);

    }


    private int initIndex = 0;
    @OnClick(R.id.fragment_alert_dialog_radio_buttons)
    public void fragmentRadioButtonDialog(){
        final String arrayFruit[] = new String[]{
                "苹果", "橘子", "草莓", "橘子", "草莓",
        };

        SingleChoiceItemsDialogFragment singleChoiceItemsDialogFragment = SingleChoiceItemsDialogFragment.getInstance(this);
        singleChoiceItemsDialogFragment.setInfos("请选择水果",arrayFruit,initIndex);
        singleChoiceItemsDialogFragment.setCancelable(true);
        singleChoiceItemsDialogFragment.show(getSupportFragmentManager(),SingleButtonDialogFragment.FRAGMENT_MANAGER_TAG);

    }

    boolean state[] = {
            false,false,false,false
    };
    @OnClick(R.id.fragment_alert_dialog_check_box)
    public void fragmentMultiChoiceDialog(){
        final String[] arrayFruit = new String[] { "苹果", "橘子", "草莓", "香蕉" };

        MultiChoiceItemsDialogFragment multiChoiceItemsDialogFragment = MultiChoiceItemsDialogFragment.getInstance(this);
        multiChoiceItemsDialogFragment.setInfos("请选择几种水果",arrayFruit,state,R.mipmap.ic_launcher);
        multiChoiceItemsDialogFragment.setCancelable(true);
        multiChoiceItemsDialogFragment.show(getSupportFragmentManager(),MessageDialogFragment.FRAGMENT_MANAGER_TAG);
    }

    /**
     * 两个输入框的的弹框
     * 可定制
     */
    @OnClick(R.id.fragment_alert_dialog_username_password)
    public void fragmentTowEditTextDialog(){
        TwoEditTextDialogFragment twoEditTextDialogFragment =  TwoEditTextDialogFragment.getInstance(this);
        twoEditTextDialogFragment.setInfos("title",R.mipmap.ic_launcher,"用户名","密码",
                InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL,
                InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        twoEditTextDialogFragment.setCancelable(false);
        twoEditTextDialogFragment.show(getSupportFragmentManager(),TwoEditTextDialogFragment.FRAGMENT_MANAGER_TAG);
    }


    /**
     * 对话框button点击回调方法
     * @see SingleButtonDialogFragment.OnSingleDialogButtonClickListener
     * 同时测试二次弹框
     */
    @Override
    public void onPositiveButtonClicked() {
        fragmentMessageDialog();//二次弹框测试，使用的是message dialog Fragment
        Log.i(TAG,"SingleButtonDialogFragment onPositiveButtonClicked");
    }

    /**
     * 两个按钮的dialog 回调方法
     * @see richard.ztesoft.com.wxalertdialoglibrary.fragment.dialog.YesNoDialogFragment.OnYesNoDialogButtonClickListener
     */
    @Override
    public void onYesButtonClicked() {
        Log.i(TAG,"YesNoDialogFragment onYesButtonClicked");
    }

    @Override
    public void onNoButtonClicked() {
        Log.i(TAG,"YesNoDialogFragment onNoButtonClicked");
    }


    /**
     * 三个按钮的dialog Fragment回调方法
     * @see richard.ztesoft.com.wxalertdialoglibrary.fragment.dialog.ThreeButtonsDialogFragment.OnThreeButtonsClickedListener
     */
    @Override
    public void on3positiveButtonClicked() {
        Log.i(TAG,"ThreeButtonsDialogFragment on3positiveButtonClicked");
    }

    @Override
    public void on3negativeButtonClicked() {
        Log.i(TAG,"ThreeButtonsDialogFragment on3negativeButtonClicked");
    }

    @Override
    public void on3neutralButtonClicked() {
        Log.i(TAG,"ThreeButtonsDialogFragment on3neutralButtonClicked");
    }


    /**
     * 自定义的dialog 包装成Fragment
     * @see richard.ztesoft.com.wxalertdialoglibrary.fragment.dialog.YesNoSelfDesignDialogFragment.OnYesNoSelfDesignDialogButtonClickListener
     */
    @Override
    public void onMyYesButtonClicked() {
        Log.i(TAG,"YesNoSelfDesignDialogFragment onMyYesButtonClicked");
    }

    @Override
    public void onMyNoButtonClicked() {
        Log.i(TAG,"YesNoSelfDesignDialogFragment onMyNoButtonClicked");
    }


    /**
     * list列表点击回调函数
     * @param item the index in items
     * @param content the string value of the selected item
     * @see richard.ztesoft.com.wxalertdialoglibrary.fragment.dialog.ListDialogFragment.OnListItemClickListener
     */
    @Override
    public void onItemClicked(int item, String content) {
        Log.i(TAG,"YesNoSelfDesignDialogFragment onItemClicked = "+item);
    }

    /**
     * 单选框
     * @param index
     * @param content
     * @see richard.ztesoft.com.wxalertdialoglibrary.fragment.dialog.SingleChoiceItemsDialogFragment.OnSingleChoiceSelectedListener
     */
    @Override
    public void onSingleChoiceSelected(int index, String content) {
        initIndex = index;
        Log.i(TAG,"SingleChoiceItemsDialogFragment onSingleChoiceSelected = "+index);
    }

    /**
     * 多选弹框回调函数
     * @param state
     */
    @Override
    public void onMultiChoiceSelected(boolean[] state) {
        StringBuilder builder = new StringBuilder();
        for (int i=0;i<state.length;i++){
            builder.append(state[i]).append(";");
            this.state[i] = state[i];//保存修改之后的值
        }
        Log.i(TAG,builder.toString());
    }

    /**
     * 两个输入框的回调函数
     * @param firstText
     * @param secondText
     */
    @Override
    public void onInputFinished(String firstText, String secondText) {
        Log.i(TAG,"firstText = " + firstText);
        Log.i(TAG,"secondText = " + secondText);
    }
}
