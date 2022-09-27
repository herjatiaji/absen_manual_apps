package com.pad1.absen_manual_apps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class loginActivity extends AppCompatActivity {

    TabLayoutMediator mediator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        TabLayout tabLayout =findViewById(R.id.tabLayout);
        ViewPager2 viewPager2 =findViewById(R.id.viewpager2);

        SampleAdapter adapter = new SampleAdapter(getSupportFragmentManager(), getLifecycle());
        viewPager2.setAdapter(adapter);
        mediator = new TabLayoutMediator(tabLayout, viewPager2, ((tab, position) -> {
            if (position == 0){
                tab.setText("Masuk");
            }
            else if (position == 1){
                tab.setText("Daftar");
            }
        }));
        mediator.attach();


    }
}