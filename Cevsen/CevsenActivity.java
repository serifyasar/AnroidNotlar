package com.serifyasargmail.cevsen;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class CevsenActivity extends AppCompatActivity {
    ViewPager viewPager;
    String dua;
    int sayfa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cevsen);

        //getSupportActionBar().hide();
        getSupportActionBar().setTitle("");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Bundle extras = getIntent().getExtras();
        assert extras != null;
        dua = extras.getString("dua");
        Database database=new Database(this);
        sayfa=database.sayfaAl(dua);
        database.close();
        int page=0;
        viewPager=findViewById(R.id.viewPager);
        assert dua != null;
        if(dua.matches("cevsen")){
            getSupportActionBar().setSubtitle("Cevşen");
            viewPager.setRotationY(180);
            page=47;
        }
        else if(dua.matches("abedir")){
            getSupportActionBar().setSubtitle("Ashab-ı Bedir");
           page=13;
        }
        else if(dua.matches("yasin")){
            getSupportActionBar().setSubtitle("Yasin Süresi");
            viewPager.setRotationY(180);
            page=6;
        }
        else if(dua.matches("fetih")){
            getSupportActionBar().setSubtitle("Fetih Suresi");
            viewPager.setRotationY(180);
            page=5;
        }
        else if(dua.matches("sabah")){
            getSupportActionBar().setSubtitle("Sabah Duaları");
            viewPager.setRotationY(180);
            page=21;
        }
        else if(dua.matches("aksam")){
            getSupportActionBar().setSubtitle("Akşam Duaları");
            viewPager.setRotationY(180);
            page=13;
        }

        ViewPagerAdapter viewPagerAdapter=new ViewPagerAdapter(this,dua,page);
        viewPager.setAdapter(viewPagerAdapter);

        viewPager.setCurrentItem(sayfa);






    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btn_yenile:

                viewPager.setCurrentItem(0);

                break;

            case android.R.id.home:
                this.finish();

        }
        return true;
    }

    @Override
    protected void onDestroy() {


        super.onDestroy();

        Database database=new Database(this);
        database.durumKaydet(dua, viewPager.getCurrentItem());
        database.close();
        Log.d("mesaj", "onDestroy: "+viewPager.getCurrentItem()+ " "+ dua);


    }



}
