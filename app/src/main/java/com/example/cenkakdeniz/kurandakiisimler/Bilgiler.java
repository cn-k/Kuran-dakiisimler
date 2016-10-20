package com.example.cenkakdeniz.kurandakiisimler;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by cenk.akdeniz on 18.10.2016.
 */
public class Bilgiler extends Activity {


    private  DatabaseHelper myDb;
    TextView txt1;
    TextView txt2;

    Button btnHome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bilgiler);

        btnHome = (Button) findViewById(R.id.btnHomeEcodes);
        txt1 = (TextView) findViewById(R.id.txtTitle);
        txt2 = (TextView) findViewById(R.id.txtContent);

        Intent intent = getIntent();
        String baslik = intent.getStringExtra("isim_adi");
        myDb = new DatabaseHelper(this);


        Cursor cursor = myDb.getAllData("select * from ISIMLER where AD LIKE '%" + baslik + "%'");
        cursor.moveToFirst();

        String header = cursor.getString(1).toString();
        txt1.setText(header);

        String content = cursor.getString(2).toString();
        txt2.setText(content);

        btnHome.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
