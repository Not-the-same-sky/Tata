package ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.example.tata.R;

import base.BaseActivity;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 不一样的天空 on 2016/9/1.
 */
public class ChatWithYouActivity extends BaseActivity {
     @Bind(R.id.et_input_msg)
    EditText editText;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chatwithyou_main);
        ButterKnife.bind(this);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);//默认键盘不弹出
    }
}
