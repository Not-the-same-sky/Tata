package Adapter;

import android.content.Context;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tata.R;

import butterknife.Bind;
import butterknife.OnClick;
import butterknife.OnLongClick;

/**
 * Created by 不一样的天空 on 2016/8/27.
 */
public class ReceiveTextHolder extends BaseViewHolder{
     @Bind(R.id.receive_text)
     TextView textView;
     public ReceiveTextHolder(Context context, ViewGroup parent, int position, int layoutId) {
          super(context, parent, position, layoutId);
     }
     @OnLongClick(R.id.receive_text)
      public boolean longClickOnText(){
        Toast.makeText(context,"Long Click",Toast.LENGTH_SHORT).show();
       return true;
   }
     @OnClick(R.id.receive_text)
     public void clickOnText(){
          Toast.makeText(context,"Click",Toast.LENGTH_SHORT).show();
         Log.d("=======================", "clickOnText: ");
     }
    @OnClick(R.id.rt_avatar)
    public void clickOnImage(){
        Log.d("------------------", "clickOnImage: ");
        Toast.makeText(context,"Click",Toast.LENGTH_SHORT).show();
    }
}
