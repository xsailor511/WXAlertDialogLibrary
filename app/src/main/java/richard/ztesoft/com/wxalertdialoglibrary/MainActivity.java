package richard.ztesoft.com.wxalertdialoglibrary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private static String TAG = "TestCustomDialog";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        FragmentManager manager = getSupportFragmentManager();

    }

    /**
     * 测试系统自带的AlertDialog
     */
    @OnClick(R.id.test_custom_dialog)
    public void testCustomDialogClicked(){

        Intent intent = new Intent(MainActivity.this,AlertDialogTestActivity.class);
        this.startActivity(intent);
    }

    /**
     * 测试普通的Activity Dialog 库
     */
    @OnClick(R.id.test_activity_dialog)
    public void activityDialogClicked(){
        Intent intent = new Intent(MainActivity.this,ActivityDialogTestActivity.class);
        this.startActivity(intent);
    }


    /**
     * 测试Fragment Dialog 库
     */
    @OnClick(R.id.test_fragment_dialog)
    public void fragmentDialogClicked(){
        Intent intent = new Intent(MainActivity.this,FragmentDialogTestActivity.class);
        this.startActivity(intent);
    }
}
