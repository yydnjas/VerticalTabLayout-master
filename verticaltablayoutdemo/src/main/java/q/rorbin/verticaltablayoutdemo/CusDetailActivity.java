package q.rorbin.verticaltablayoutdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class CusDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private int mPosition;
    private int mLastindex;
    private String startTime = null;
    private String endTime = null;
    private ArrayList<ArrayList<String>> mWeekTime = new ArrayList<>();
    private ArrayList<ArrayList<Integer>> mWeekMode = new ArrayList<>();
    private ArrayList<ArrayList<Integer>> mWeekEndPossion = new ArrayList<>();
    private TextView tv_start_text, tv_from_text;
    private Integer mCurrentMode;
    private Integer mTimePosition;
    private int mstartPossion = 0;
    private Integer mEndPossion =47;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cus_detail);
        mWeekMode = DataUtil.getmWeekMode();
        mWeekTime = DataUtil.getmWeekTime();
        mWeekEndPossion = DataUtil.getmWeekEndPossion();
        initView();
        initData();
    }

    private ImageView iv_mode, iv_mode_up, iv_mode_down, iv_start_up, iv_start_down, iv_from_up, iv_from_down;

    private void initView() {
        iv_mode = (ImageView) findViewById(R.id.iv_mode);
        iv_mode_up = (ImageView) findViewById(R.id.iv_mode_up);
        iv_mode_down = (ImageView) findViewById(R.id.iv_mode_down);
        iv_start_up = (ImageView) findViewById(R.id.iv_start_up);
        iv_start_down = (ImageView) findViewById(R.id.iv_start_down);
        iv_from_up = (ImageView) findViewById(R.id.iv_from_up);
        iv_from_down = (ImageView) findViewById(R.id.iv_from_down);
        tv_start_text = (TextView) findViewById(R.id.tv_start_text);
        tv_from_text = (TextView) findViewById(R.id.tv_from_text);
        findViewById(R.id.iv_back).setOnClickListener(this);
        findViewById(R.id.btn_airstart).setOnClickListener(this);
        iv_mode_up.setOnClickListener(this);
        iv_mode_down.setOnClickListener(this);
        iv_start_up.setOnClickListener(this);
        iv_start_down.setOnClickListener(this);
        iv_from_up.setOnClickListener(this);
        iv_from_down.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_mode_up:
                changeMode();
                break;
            case R.id.iv_mode_down:
                changeMode();
                break;
            case R.id.iv_start_up:
                changeTime("start_up");
                break;
            case R.id.iv_start_down:
                changeTime("start_down");
                break;
            case R.id.iv_from_up:
                changeTime("from_up");
                break;
            case R.id.iv_from_down:
                changeTime("from_down");
                break;

                case R.id.btn_airstart:
                pushData();

                break;

            default:
                return;

        }
    }

    private void pushData() {
        int[] dataArray = new int[mEndPossion - mstartPossion];
        for(int i=0;i<dataArray.length;i++){
            dataArray[i]=mCurrentMode;
        }
//        ToastUtil.showToast(this, Arrays.toString(dataArray));
        DataUtil.changeData(mLastindex,dataArray,mstartPossion);
        finish();

    }

    private void changeTime(String str) {

        if(str.equals("start_up")){
         if (mstartPossion+1<mEndPossion){
             mstartPossion+=1;
             tv_start_text.setText(gettime(mstartPossion));
         }else{
             ToastUtil.showToast(this,"Out Of Range");

         }

        }else if(str.equals("start_down")){
            if (mstartPossion-1>-1){
                mstartPossion-=1;
                tv_start_text.setText(gettime(mstartPossion));
            }else{
                ToastUtil.showToast(this,"Out Of Range");

            }


        }else if(str.equals("from_up")){

            if (mEndPossion+1<48){
                mEndPossion+=1;
                tv_from_text.setText(gettime(mEndPossion));
            }else{
                tv_from_text.setText(gettime(48));
                ToastUtil.showToast(this,"Out Of Range");

            }


        }else if(str.equals("from_down")){

            if (mEndPossion-1>mstartPossion){
                mEndPossion-=1;
                tv_from_text.setText(gettime(mEndPossion));
            }else{
                ToastUtil.showToast(this,"Out Of Range");

            }

        }




    }

    public static String gettime(int i){
        int totalTime;
        int hour,minute;
        String hourStr,minuteStr;
        totalTime=i*30;
        hour = totalTime/60;
        minute = totalTime%60;
        if (hour<10){
            hourStr = "0"+hour;
        }else {
            hourStr = ""+hour;
        }
        if (minute==0){

            minuteStr = "0"+minute;
        }
        else {
            minuteStr = ""+minute;
        }
        return hourStr+":"+minuteStr;
    }

    private void changeMode() {

        if (mCurrentMode == 0) {
            mCurrentMode = 1;
            iv_mode.setImageResource(R.drawable.ic_night);
        } else {
            mCurrentMode = 0;
            iv_mode.setImageResource(R.drawable.ic_day);
        }

    }


    private void initData() {

        Intent intent = getIntent();
        mPosition = intent.getIntExtra("POSITION", -1);
        mLastindex = intent.getIntExtra("LASTINDEX", -1);
        mCurrentMode = mWeekMode.get(mLastindex).get(mPosition);

       mWeekEndPossion.get(mLastindex).get(mPosition);

        if (mCurrentMode == 0) {
            iv_mode.setImageResource(R.drawable.ic_day);
        } else {
            iv_mode.setImageResource(R.drawable.ic_night);
        }
        String mCurrentTime = mWeekTime.get(mLastindex).get(mPosition);
        if (mPosition == 0&& mPosition != mWeekTime.get(mLastindex).size()-1) {
            startTime = "00:00";
            mstartPossion = 0;
            mEndPossion =  mWeekEndPossion.get(mLastindex).get(mPosition);
            endTime = mWeekTime.get(mLastindex).get(mPosition);
        }else if (mPosition == 0 && mPosition == mWeekTime.get(mLastindex).size()-1){
            startTime ="00:00";
            endTime = "24:00";
        } else if (mPosition == mWeekTime.get(mLastindex).size()-1) {
            mstartPossion = mWeekEndPossion.get(mLastindex).get(mPosition-1);
            startTime = mWeekTime.get(mLastindex).get(mPosition - 1);
            mEndPossion = 47;
            endTime = "24:00";
        } else {
            mstartPossion = mWeekEndPossion.get(mLastindex).get(mPosition-1);
            mEndPossion = mWeekEndPossion.get(mLastindex).get(mPosition);
            startTime = mWeekTime.get(mLastindex).get(mPosition - 1);
            endTime = mWeekTime.get(mLastindex).get(mPosition);
        }

        tv_start_text.setText(startTime);

        tv_from_text.setText(endTime);
//        ToastUtil.showToast(this,"mstartPossion:"+mstartPossion+
//            "  mEndPossion:"+mEndPossion);
    }



}
