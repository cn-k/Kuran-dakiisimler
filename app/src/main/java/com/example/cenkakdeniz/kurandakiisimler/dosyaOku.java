
package com.example.cenkakdeniz.kurandakiisimler;

import android.content.Context;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by cenk.akdeniz on 14.04.2016.
 */
public class dosyaOku {

    Context mContext;
    Context mContextDis;
    File file = new File("dosya.txt");
    public void dosyayaYazma(){
        String str = "Bunu dosyaya yazdir";

        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file, false);
            BufferedWriter bWriter = new BufferedWriter(fileWriter);
            bWriter.write(str);
            bWriter.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public ArrayList<İsimler> dosyadanOkumaCer(Context context , String dosya){
        ArrayList<İsimler> veri = new ArrayList<İsimler>();
        mContext = context;
        InputStream is = null;
        try {

            is = mContext.getAssets().open(dosya);
            String line;
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            while ((line=br.readLine()) != null) {
                String tmp [] = line.split("--");
                veri.add(new İsimler(tmp[0], tmp[1]));
            }
            br.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return veri;

    }



}
