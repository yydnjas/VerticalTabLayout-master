package q.rorbin.verticaltablayoutdemo;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.adapter.TabAdapter;
import q.rorbin.verticaltablayout.widget.TabView;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ViewPager viewpager;
    private ListView cus48_lv;
    private Cus48Adapter cus48Adapter;
    private ArrayList<ArrayList<String>> mWeekTime= new ArrayList<>();
    private ArrayList<ArrayList<Integer>> mWeekMode= new ArrayList<>();
    private int mLastIndex = 0;
    private MyPagerAdapter mAadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DataUtil.initListData();
        mWeekMode = DataUtil.getmWeekMode();
        mWeekTime = DataUtil.getmWeekTime();
        viewpager = (ViewPager) findViewById(R.id.viewpager2);
        mAadapter=new MyPagerAdapter();
        viewpager.setAdapter(mAadapter);
        initTab0();
//        initTab1();
//        initTab2();
//        initTab3();
    }

    private void initTab0() {
        final VerticalTabLayout tablayout = (VerticalTabLayout) findViewById(R.id.tablayout0);
        tablayout.setupWithViewPager(viewpager);
//        tablayout.setTabBadge(7, 32);
//        tablayout.setTabBadge(2, -1);
//        tablayout.setTabBadge(3, -1);
//        tablayout.setTabBadge(4, -1);
    }


//    private void initTab1() {
//        VerticalTabLayout tablayout = (VerticalTabLayout) findViewById(R.id.tablayout1);
//        tablayout.setupWithViewPager(viewpager);
//    }
//
//    private void initTab2() {
//        VerticalTabLayout tablayout = (VerticalTabLayout) findViewById(R.id.tablayout2);
//        tablayout.setupWithViewPager(viewpager);
//        tablayout.setTabBadge(2, -1);
//        tablayout.setTabBadge(8, -1);
//        tablayout.getTabAt(3).setBadge(new TabView.TabBadge.Builder().setBadgeGravity(Gravity.START | Gravity.TOP)
//                .setBadgeNumber(999)
//                .setOnDragStateChangedListener(new Badge.OnDragStateChangedListener() {
//                    @Override
//                    public void onDragStateChanged(int dragState, Badge badge, View targetView) {
//                        if (dragState == STATE_SUCCEED) {
//                            badge.setBadgeNumber(-1).stroke(0xFFFFFFFF,1,true);
//                        }
//                    }
//                }).build());
//    }
//
//    private void initTab3() {
//        VerticalTabLayout tablayout = (VerticalTabLayout) findViewById(R.id.tablayout);
//        tablayout.setTabAdapter(new MyTabAdapter());
//    }

    private class MyTabAdapter implements TabAdapter {

        List<MenuBean> menus;

        public MyTabAdapter() {
            menus = new ArrayList<>();
            Collections.addAll(menus, new MenuBean(R.drawable.man_01_pressed, R.drawable.man_01_none, "汇总")
                    , new MenuBean(R.drawable.man_02_pressed, R.drawable.man_02_none, "图表")
                    , new MenuBean(R.drawable.man_03_pressed, R.drawable.man_03_none, "收藏")
                    , new MenuBean(R.drawable.man_04_pressed, R.drawable.man_04_none, "竞拍"));
        }

        @Override
        public int getCount() {
            return menus.size();
        }

        @Override
        public TabView.TabBadge getBadge(int position) {
            return new TabView.TabBadge.Builder().setBadgeNumber(999).setBackgroundColor(0xff2faae5)
                    .build();
        }

        @Override
        public TabView.TabIcon getIcon(int position) {
            MenuBean menu = menus.get(position);
            return new TabView.TabIcon.Builder()
                    .setIcon(menu.mSelectIcon, menu.mNormalIcon)
                    .setIconGravity(Gravity.START)
                    .setIconMargin(dp2px(5))
                    .setIconSize(dp2px(20), dp2px(20))
                    .build();
        }

        @Override
        public TabView.TabTitle getTitle(int position) {
            MenuBean menu = menus.get(position);
            return new TabView.TabTitle.Builder()
                    .setContent(menu.mTitle)
                    .setTextColor(0xFF36BC9B, 0xFF757575)
                    .build();
        }

        @Override
        public int getBackground(int position) {
            return -1;
        }

    }

    private class MyPagerAdapter extends PagerAdapter implements TabAdapter {
        String[] titles = new String[]{};

        public MyPagerAdapter() {

            titles = getResources().getStringArray(R.array.day_by_week_logogram);
//            Collections.addAll(titles, "Android", "IOS", "Web", "JAVA", "C++",
//                    ".NET", "JavaScript");
//            Collections.addAll(titles, "Android", "IOS", "Web", "JAVA", "C++",
//                    ".NET", "JavaScript", "Swift", "PHP", "Python", "C#", "Groovy", "SQL", "Ruby");
        }

        @Override
        public int getCount() {
            return titles.length;
        }

        @Override
        public TabView.TabBadge getBadge(int position) {
//            if (position == 5) return new TabView.TabBadge.Builder().setBadgeNumber(666)
//                    .setExactMode(true)
//                    .setOnDragStateChangedListener(new Badge.OnDragStateChangedListener() {
//                        @Override
//                        public void onDragStateChanged(int dragState, Badge badge, View targetView) {
//                        }
//                    }).build();
            return null;
        }

        @Override
        public TabView.TabIcon getIcon(int position) {
            return null;
        }

        @Override
        public TabView.TabTitle getTitle(int position) {

            return new TabView.TabTitle.Builder()
                    .setContent(titles[position])
                    .setTextColor(Color.WHITE, 0xBBFFFFFF)
                    .build();
        }

        @Override
        public int getBackground(int position) {
            return 0;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            final View view = LayoutInflater.from(
                getBaseContext()).inflate(R.layout.item_vp, null, false);

            final TextView txtPage = (TextView) view.findViewById(R.id.txt_vp_item_page);
            txtPage.setText(getResources().getStringArray(R.array.day_by_week)[position]);
            cus48_lv = (ListView) view.findViewById(R.id.cus48_list);
            cus48Adapter = new Cus48Adapter(mWeekMode.get(position),mWeekTime.get(position));
            cus48_lv.setAdapter(cus48Adapter);
            cus48_lv.setOnItemClickListener(MainActivity.this);
            container.addView(view);

            Log.e("instantiateItem","Possion:"+position);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }
    }

    protected int dp2px(float dp) {
        final float scale = this.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        Intent intent = new Intent(this, CusDetailActivity.class);
        mLastIndex =viewpager.getCurrentItem();

        intent.putExtra("POSITION",i);
        intent.putExtra("LASTINDEX",mLastIndex);
        Log.e("onItemClick","mLastIndex:"+mLastIndex);
        startActivityForResult(intent,0);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("onActivityResult","mLastIndex");
        mWeekMode = new ArrayList<>();
        mWeekTime = new ArrayList<>();
        mWeekMode = DataUtil.getmWeekMode();
        mWeekTime = DataUtil.getmWeekTime();
//        cus48Adapter.changeListData(mWeekMode.get(mLastIndex),mWeekTime.get(mLastIndex));
//        cus48Adapter.notifyDataSetChanged();
        mAadapter.notifyDataSetChanged();

    }

    @Override
    protected void onResume() {
        super.onResume();


    }
}
