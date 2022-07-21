package com.ivg.banktest.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.ivg.banktest.R;

public class AddNewCard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_card);
        MyToolbar.show(this, "Bankapp",true);
    }
}