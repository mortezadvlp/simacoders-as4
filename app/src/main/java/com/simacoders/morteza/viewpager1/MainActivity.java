package com.simacoders.morteza.viewpager1;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ViewPager viewPager = findViewById(R.id.viewpager);
        MyAdapter adapter = new MyAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                RadioButton radioButton;
                switch (position){
                    case 0:
                        radioButton = MainActivity.this.findViewById(R.id.radioButton0);
                        break;
                    case 1:
                        radioButton = MainActivity.this.findViewById(R.id.radioButton1);
                        break;
                    case 2:
                        radioButton = MainActivity.this.findViewById(R.id.radioButton2);
                        break;
                    case 3:
                        radioButton = MainActivity.this.findViewById(R.id.radioButton3);
                        break;
                    case 4:
                        radioButton = MainActivity.this.findViewById(R.id.radioButton4);
                        break;
                    default:
                        radioButton = MainActivity.this.findViewById(R.id.radioButton0);
                        break;
                }
                radioButton.setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        RadioGroup radioGroup = findViewById(R.id.radiogroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int buttonId) {
                RadioButton radioButton = MainActivity.this.findViewById(buttonId);
                String tag = radioButton.getTag().toString();
                int num = Integer.parseInt(tag);
                viewPager.setCurrentItem(num);
            }
        });

        RadioButton radioButton0 = findViewById(R.id.radioButton0);
        radioButton0.setChecked(true);

    }

    public class classData{
        public int imageId;
        public String title;
        public String details;
    }

    public class MyAdapter extends FragmentPagerAdapter{

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            classData data = new classData();
            switch (position){
                case 0:
                    data.imageId = R.drawable.apple;
                    data.title = "سیب";
                    data.details = "سیب قرمز است";
                    break;
                case 1:
                    data.imageId = R.drawable.strawberry;
                    data.title = "توت فرنگی";
                    data.details = "توت فرنگی قرمز است";
                    break;
                case 2:
                    data.imageId = R.drawable.orange;
                    data.title = "پرتقال";
                    data.details = "پرتقال نارنجی است";
                    break;
                case 3:
                    data.imageId = R.drawable.kiwi;
                    data.title = "کیوی";
                    data.details = "کیوی قهوه ای است";
                    break;
                case 4:
                    data.imageId = R.drawable.banana;
                    data.title = "موز";
                    data.details = "موز زرد است";
                    break;
            }
            return MyFragment.newInstance(data);
        }

        @Override
        public int getCount() {
            return 5;
        }
    }

    public static class MyFragment extends Fragment{
        public static MyFragment newInstance(classData data){
            MyFragment fragment = new MyFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("image", data.imageId);
            bundle.putString("title", data.title);
            bundle.putString("details", data.details);
            fragment.setArguments(bundle);
            return fragment;
        }

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.viewpager_item, container, false);
            ImageView imageView = view.findViewById(R.id.imageView);
            TextView textViewTitle = view.findViewById(R.id.textViewTitle);
            TextView textViewDetails = view.findViewById(R.id.textViewDetails);
            Bundle bundle = getArguments();
            imageView.setImageResource(bundle.getInt("image"));
            textViewTitle.setText(bundle.getString("title"));
            textViewDetails.setText(bundle.getString("details"));
            return view;
        }
    }
}
