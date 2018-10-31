package q.rorbin.verticaltablayoutdemo;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by lee <55669634@qq.com>
 * On 2018/10/24.
 */
public class Cus48Adapter extends BaseAdapter {

    private ArrayList<String> mPossionTimeList = new ArrayList<>();
    private ArrayList<Integer> mModeList = new ArrayList<>();

    public Cus48Adapter(ArrayList<Integer> possionmodeList, ArrayList<String> possionTimeList){

       this.mModeList = possionmodeList;
       this.mPossionTimeList = possionTimeList;
    }
    @Override
    public int getCount() {
        Log.e("getCount",":"+mModeList.size());
        return mModeList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public void changeListData(ArrayList<Integer> possionmodeList, ArrayList<String> possionTimeList) {

        this.mModeList = possionmodeList;
        this.mPossionTimeList = possionTimeList;

    }


    class ViewHoder{
        TextView start_tv,end_tv;
        ImageView item_mode_iv;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHoder viewHoder = null;
        if (convertView!=null){
            viewHoder= (ViewHoder) convertView.getTag();

        } else  {
            viewHoder = new ViewHoder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_cus48,null);
            viewHoder.start_tv = (TextView) convertView.findViewById(R.id.textView1);
            viewHoder.end_tv = (TextView)convertView.findViewById(R.id.textView3);
            viewHoder.item_mode_iv = (ImageView) convertView.findViewById(R.id.item_mode_iv);
            convertView.setTag(viewHoder);
        }
        String startTime = null;
        String endTime = null;
        if (position == 0 && position != mPossionTimeList.size()-1){
            startTime ="00:00";
            endTime = mPossionTimeList.get(position);
        }else if (position == 0 && position == mPossionTimeList.size()-1){
            startTime ="00:00";
            endTime = "24:00";
        }else if (position == mPossionTimeList.size()-1){
            startTime = mPossionTimeList.get(position-1);
            endTime = "24:00";
        }else{
            startTime = mPossionTimeList.get(position-1);
            endTime =  mPossionTimeList.get(position);
        }

        viewHoder.start_tv.setText(startTime);
        viewHoder.end_tv.setText(endTime);
        if (mModeList.get(position)==0) {

            viewHoder.item_mode_iv.setImageResource(R.drawable.ic_day);

        }else{

            viewHoder.item_mode_iv.setImageResource(R.drawable.ic_night);

        }
        return convertView;
    }
}
