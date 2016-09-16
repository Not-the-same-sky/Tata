package base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import butterknife.ButterKnife;

/**
 * Created by 不一样的天空 on 2016/9/1.
 */
public class BaseActivity extends Activity{

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
    }
    private Toast toast;
    public void toast(final Object obj) {
        try {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (toast == null)
                        toast = Toast.makeText(BaseActivity.this,"", Toast.LENGTH_SHORT);
                    toast.setText(obj.toString());
                    toast.show();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void startActivity(Class<? extends Activity> target, Bundle bundle,boolean finish) {
        Intent intent = new Intent();
        intent.setClass(this, target);
        if (bundle != null)
            intent.putExtra(getPackageName(), bundle);
            startActivity(intent);
        if (finish)
            finish();
    }

    public Bundle getBundle() {
        if (getIntent() != null && getIntent().hasExtra(getPackageName()))
            return getIntent().getBundleExtra(getPackageName());
        else
            return null;
    }

    /**
     * 隐藏软键盘
     */
    public void hideSoftInputView() {
        InputMethodManager manager = ((InputMethodManager) this.getSystemService(Activity.INPUT_METHOD_SERVICE));
        if (getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
            if (getCurrentFocus() != null)
                manager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**隐藏软键盘-一般是EditText.getWindowToken()
     * @param token
     */
    public void hideSoftInput(IBinder token) {
        if (token != null) {
            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
