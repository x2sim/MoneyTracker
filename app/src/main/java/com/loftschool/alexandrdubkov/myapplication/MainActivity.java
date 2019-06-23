package com.loftschool.alexandrdubkov.myapplication;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText titletxt;
    private EditText pricetxt;
    private String title;
    private String price;
    private Button butt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titletxt = findViewById(R.id.goods);
        pricetxt = findViewById(R.id.name_price);
        butt = findViewById(R.id.button_add);

        titletxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
             title=s.toString();
             changetextcolorbutton();
            }
        });
        pricetxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
            price=s.toString();
            changetextcolorbutton();
            }
        });
    }
    private void changetextcolorbutton() {
        if (!TextUtils.isEmpty(title) && !TextUtils.isEmpty(price)){
            butt.setTextColor(ContextCompat.getColor(this, R.color.colorButton));
        }
        else
            {
            butt.setTextColor(ContextCompat.getColor(this, R.color.add_button_color_inactive));
            }
    }
}
