package fptu.summer.skypeapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;

public class MasterActivity extends AppCompatActivity {
    Toolbar toolbar ;


    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        setToolbar();
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        setToolbar();
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        setToolbar();
    }

    protected void setToolbar(){
        toolbar = findViewById(R.id.toolBar);
        if(toolbar == null) return;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back_black_24dp));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
