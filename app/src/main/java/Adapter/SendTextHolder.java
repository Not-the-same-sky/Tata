package Adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tata.R;

import butterknife.Bind;
import butterknife.OnLongClick;

/**
 * Created by 不一样的天空 on 2016/8/27.
 */
public class SendTextHolder extends BaseViewHolder {
    @Bind(R.id.send_text)
    TextView textView;

    public SendTextHolder(Context context, ViewGroup parent, int position, int layoutId) {
        super(context, parent, position, layoutId);
    }

    @OnLongClick(R.id.send_text)
    public boolean longClickOnText() {
        Toast.makeText(context, "LONG", Toast.LENGTH_SHORT).show();
        return true;
    }
}
