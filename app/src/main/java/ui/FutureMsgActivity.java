package ui;

import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

import com.example.tata.R;

import base.BaseActivity;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by hasee on 2016/9/16.
 */
public class FutureMsgActivity extends BaseActivity {
    @Bind(R.id.txv_future_msg)
    TextView txv_future_msg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.future_msg);
        ButterKnife.bind(this);

    }
}
