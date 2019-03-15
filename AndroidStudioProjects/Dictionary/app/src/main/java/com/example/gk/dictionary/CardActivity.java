package com.example.gk.dictionary;


import android.app.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;


import java.util.List;

//https://developer.android.com/reference/android/support/v4/app/FragmentPagerAdapter

//https://www.jianshu.com/p/6b8ba217f2a1  view pager

//http://www.voidcn.com/article/p-enmyequt-up.html  card
//read
public class CardActivity extends FragmentActivity {

    private static final String EXTRA_MEDICINE_GENERIC_NAME =
            "extra: medicine generic name";
    private List<Medicine> mMedicines;

    // to create a new activity with an intent (a medicine name)
    public static Intent newIntent(Context packageContext, String genericName) {
        Intent intent = new Intent(packageContext, CardActivity.class);
        intent.putExtra(EXTRA_MEDICINE_GENERIC_NAME, genericName);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);

        String medicineGenericName = getIntent().getStringExtra(EXTRA_MEDICINE_GENERIC_NAME);
        mMedicines = MedicineLab.get(this).getMedicines();

        CardPagerAdapter adapter = new CardPagerAdapter(getSupportFragmentManager());
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(adapter);

        for (int i = 0; i < mMedicines.size(); i++) {
            if (mMedicines.get(i).getGenericName().equals(medicineGenericName)) {
                viewPager.setCurrentItem(i);
                break;
            }
        }
    }

    public class CardPagerAdapter extends FragmentPagerAdapter {


        public CardPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }



        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            Medicine medicine = mMedicines.get(position);
       //     CardFrontFragment fragment = CardFrontFragment.newInstance(medicine);
           CardContainerFragment fragment = CardContainerFragment.newInstance(medicine);
            //Log.i("test2", medicine.getGenericName());
            return fragment;
            //return new CardContainerFragment();
        }

        @Override
        public int getCount() {
            return mMedicines.size();
        }
    }
}

