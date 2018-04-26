package com.example.ayben.myapplication;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class PopActivity extends Activity {
    Button btn_ileri;
    TextView textView;
    private ArrayList<String[]> arrayLists;
    int sayac=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop);
        btn_ileri = (Button) findViewById(R.id.btn_ileri);
        textView=(TextView)findViewById(R.id.textView);


        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) (width * .8), (int) (height * .7));


        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = -20;
        getWindow().setAttributes(params);

    }

    public void btn_ileri(View view) {
    if(sayac==6){
        finish();
    }
    else{
        sayac++;
        textView.setText(arrayLists.get(sayac)[0].toString());

    }


    }
    //Ekranda gösterilecek solan sorular burada okunup ArrayListe taşınıyor.
    private void readFile() {
        arrayLists = new ArrayList<>();
        try {

            InputStream inputStream = getAssets().open("sorular.txt");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String satir = "";
            while ((satir = bufferedReader.readLine()) != null) {
                arrayLists.add(satir.split("!"));
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    @Override  //Uygulama başlarken dosyadan veri çekiyorum.
    protected void onStart() {
        super.onStart();
        readFile();
        textView.setText(arrayLists.get(0)[0].toString());

    }

    public void btn_geri(View view) {

        if(sayac==0){
            textView.setText(arrayLists.get(sayac)[0].toString());}
        else{
            sayac--;
            textView.setText(arrayLists.get(sayac)[0].toString());}
    }
}