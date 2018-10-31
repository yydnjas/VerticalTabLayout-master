package q.rorbin.verticaltablayoutdemo;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by lee <55669634@qq.com>
 * On 2018/10/25.
 */
public class DataUtil {
    public static ArrayList<Integer> mModeList = new ArrayList<>();
    public static ArrayList<Integer> mEndPositionList = new ArrayList<>();
    public static ArrayList<Integer> mPossionModeList = new ArrayList<>();
    public static ArrayList<String> mPossionTimeList = new ArrayList<>();
    public static ArrayList<ArrayList<Integer>> mWeekMode= new ArrayList<>();
    public static ArrayList<ArrayList<Integer>> mWeekEndPossion= new ArrayList<>();
    public static ArrayList<ArrayList<String>> mWeekTime= new ArrayList<>();
    public static int[] mon = new int[]{0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,0,0,0,0,0,0,0,1,1,1,1,1,1,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0};
    public static int[] tues = new int[]{0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,0,0,0,0,0,0,0,1,1,1,1,1,1,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0};
    public static int[] wed = new int[]{0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,0,0,0,0,0,0,0,1,1,1,1,1,1,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0};
    public static int[] thur = new int[]{0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,0,0,0,0,0,0,0,1,1,1,1,1,1,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0};
    public static int[] fri = new int[]{0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,0,0,0,0,0,0,0,1,1,1,1,1,1,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0};
    public static int[] sat = new int[]{0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,0,0,0,0,0,0,0,1,1,1,1,1,1,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0};
    public static int[] sun = new int[]{0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,0,0,0,0,0,0,0,1,1,1,1,1,1,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0};

    public static ArrayList<ArrayList<String>> getmWeekTime() {
        return mWeekTime;
    }
    public static ArrayList<ArrayList<Integer>> getmWeekEndPossion() {
        return mWeekEndPossion;
    }

    public static ArrayList<ArrayList<Integer>> getmWeekMode() {
        return mWeekMode;
    }

     public static void changeData(int i, int[] pushData,int changeStart){
         mWeekMode =new ArrayList<>();
         mWeekEndPossion=new ArrayList<>();
         mWeekTime=new ArrayList<>();
         if ( i== 0){

             System.arraycopy(pushData, 0, mon, changeStart, pushData.length);
             Log.e("changeData:", Arrays.toString(mon));
         }else if ( i== 1){

             System.arraycopy(pushData, 0, tues, changeStart, pushData.length);

         }else if ( i== 2){

             System.arraycopy(pushData, 0, wed, changeStart, pushData.length);

         }else if ( i== 3){

             System.arraycopy(pushData, 0, thur, changeStart, pushData.length);

         }else if ( i== 4){

             System.arraycopy(pushData, 0, fri, changeStart, pushData.length);

         }else if ( i== 5){

             System.arraycopy(pushData, 0, sat, changeStart, pushData.length);

         }else if ( i== 6){

             System.arraycopy(pushData, 0, sun, changeStart, pushData.length);

         }
        initListData();

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


    private static ArrayList<Integer> getData(){


        return mModeList;
    }

    public static void getPosstionByDay(int dataByDay[]){

        mEndPositionList = new ArrayList<>();
        mPossionTimeList = new ArrayList<>();
        mPossionModeList = new ArrayList<>();
        int lsatMode=-1;
        for (int i = 0;i<dataByDay.length;i++){

            if (dataByDay[i]==lsatMode){
                lsatMode = dataByDay[i];

            }else if(dataByDay[i]!=lsatMode && i!=0){
                String endTime = gettime(i);
//                    Log4j.e("EndTime:"+endTime);
                mPossionTimeList.add(endTime);
                mPossionModeList.add(lsatMode);
                mEndPositionList.add(i);
                lsatMode = dataByDay[i];
            }else if(dataByDay[i]!=lsatMode && i ==0){
                lsatMode = dataByDay[i];
            }

            if(i==47){
                String endTime = gettime(i);
                mPossionTimeList.add(endTime);
                mPossionModeList.add(lsatMode);
                mEndPositionList.add(i);
            }
        }
        mWeekEndPossion.add(mEndPositionList);
        mWeekTime.add(mPossionTimeList);
        mWeekMode.add(mPossionModeList);

    }





    public static void initListData() {

//        if (mStartList.size()==0){
//            int totalTime;
//            int hour,minute;
//            String hourStr,minuteStr;
//            for (int i = 0;i<48;i++){
//                totalTime=i*30;
//                hour = totalTime/60;
//                minute = totalTime%60;
//                if (hour<10){
//                    hourStr = "0"+hour;
//                }else {
//                    hourStr = ""+hour;
//                }
//                if (minute==0){
//
//                    minuteStr = "0"+minute;
//                }
//                else {
//                    minuteStr = ""+minute;
//                }
//                mStartList.add(hourStr+":"+minuteStr);
//            }
//
//        }

//        if (mEndList.size()==0){
//            int totalTime;
//            int hour,minute;
//            String hourStr,minuteStr;
//            for (int i = 1;i<49;i++){
//                totalTime=i*30;
//                hour = totalTime/60;
//                minute = totalTime%60;
//                if (hour<10){
//                    hourStr = "0"+hour;
//                }else {
//                    hourStr = ""+hour;
//                }
//                if (minute==0){
//
//                    minuteStr = "0"+minute;
//                }
//                else {
//                    minuteStr = ""+minute;
//                }
//                mEndList.add(hourStr+":"+minuteStr);
//            }
//
//        }


            for (int i = 0; i < 7; i++) {
            if ( i== 0){
                getPosstionByDay(mon);
            }else if ( i== 1){
                getPosstionByDay(tues);
            }else if ( i== 2){
                getPosstionByDay(wed);
            }else if ( i== 3){
                getPosstionByDay(thur);
            }else if ( i== 4){
                getPosstionByDay(fri);
            }else if ( i== 5){
                getPosstionByDay(sat);
            }else if ( i== 6){
                getPosstionByDay(sun);
            }
            }


    }


    }
