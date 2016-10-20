package com.example.cenkakdeniz.kurandakiisimler;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDb ;
    dosyaOku oku= new dosyaOku();
    ArrayList<İsimler> isim_listesi;
    İsimler isim_nesnesi;
    String ad;
    String anlami;
    String dosya = "isimler.txt";

    Context context ;

    ArrayList<String> listItems = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    ListView listView;
    EditText editText;


    String TABLE_NAME="ISIMLER";

    TextView txtSonuc ;
    TextView txtSearch ;
    Button btn ;

    StringBuilder tut = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDatabaseHelper();

        listView = (ListView) findViewById(R.id.listİsimler);
        editText = (EditText) findViewById(R.id.txtSearchİsimler);

        dosyadanOku(dosya);

        initList();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String str = (String) listView.getItemAtPosition(position);
                intentYaratıcı(str);

            }
        });

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                MainActivity.this.adapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }



    public void initList(){
       Cursor res = myDb.getAllData("select * from " + TABLE_NAME );
       //   Cursor res = myDb.getAllData("select * from ISIMLER ");
        if (res.moveToFirst() && listItems.size() < 20) {
            do {

                String tut = res.getString(1).toString();
                listItems.add(tut);
            } while (res.moveToNext());
        }

        adapter = new ArrayAdapter<String>(this,R.layout.list_item ,R.id.txtitem ,listItems);
        listView.setAdapter(adapter);
        myDb.close();
        }

    public void dosyadanOku(String dosya) {
        isim_listesi=oku.dosyadanOkumaCer(this , dosya);
        for(int i = 0; i<isim_listesi.size() ; i++) {
            isim_nesnesi = isim_listesi.get(i);
            ad = isim_nesnesi.getAd();
            anlami = isim_nesnesi.getAnlami();
            myDb.insertData(ad ,anlami);
            myDb.cursor.close();
            myDb.db.close();
        }
    }

    private void initDatabaseHelper() {
        if (myDb == null) {
            myDb = new DatabaseHelper(this);
        }
    }

    public void intentYaratıcı(String str){
        Intent intent = new Intent(this, Bilgiler.class);
        intent.putExtra("isim_adi", str);
        startActivity(intent);

    }
}
