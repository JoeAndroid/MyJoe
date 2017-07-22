package com.example.joe.ui.module.custom.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.joe.R;

public class PeriscopeLayoutActivity extends FragmentActivity {

    private ImageView imageview;

    private Button button,button_live;

    private static int HOT_FRAGMENT=1;
    private static int CESSION_FRAGMENT=2;

    private Fragment selectFragment;

    private FragmentManager fm;

    private FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_periscope_layout);
        imageview= (ImageView) findViewById(R.id.imageview);
        button= (Button) findViewById(R.id.button);
        button_live= (Button) findViewById(R.id.button_live);
       /* periscopeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                periscopeLayout.addHeart();
            }
        });*/
        imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PeriscopeLayoutActivity.this,"直播点赞",Toast.LENGTH_LONG).show();
            }
        });
        button_live.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PeriscopeLayoutActivity.this,"button_live",Toast.LENGTH_LONG).show();
            }
        });
        fm=getSupportFragmentManager();
        ft=fm.beginTransaction();

    }

/*

    private void selectFragment(int Tag){
        hidenFragment();
        if (Tag==HOT_FRAGMENT){
            if (hotFragment==null){
                hotFragment=new HotFragment();
                ft.add(hotfragment);
                ft.add(R.id.frameLayout_main, hotfragment);
            }
            ft.show(hotfragment);
        }

        if(Tag==CESSION_FRAGMENT){
            if (cessionfragment==null){
                cessionfragment=new cessionfragment();
                ft.add(cessionfragment);
                ft.add(R.id.frameLayout_main, cessionfragment);
            }
            ft.show(cessionfragment);
        }

        ft.commit();
    }
*/

    private void hidenFragment(){
        if (selectFragment!=null){
            ft.hide(selectFragment).commit();
        }
    }
}
