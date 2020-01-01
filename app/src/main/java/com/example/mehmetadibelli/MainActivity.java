package com.example.mehmetadibelli;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private EditText editText;
    private TextView boy_tv,durum_tv,ideal_tv,kilo_tv;
    private SeekBar seekBar;
    private RadioButton Erkek,Kadin;
    private boolean erkekmi = true;
    private double boy = 0.0;
    private int kilo=50;
    private SeekBar.OnSeekBarChangeListener seekBarOlayIsleyicisi = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            kilo=30+progress;
            guncelle();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };
    private TextWatcher editTextOlayIsleyicisi = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            try {
                boy=Double.parseDouble(s.toString())/100.0;
            }catch (NumberFormatException e){
                boy=0.0;
            }
            guncelle();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        button=(Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
            }
        });
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText=(EditText) findViewById(R.id.editText);
        boy_tv=(TextView) findViewById(R.id.boy_tv);
        durum_tv=(TextView) findViewById(R.id.durum_tv);
        ideal_tv=(TextView) findViewById(R.id.ideal_tv);
        kilo_tv=(TextView) findViewById(R.id.kilo_tv);
        Erkek=(RadioButton) findViewById(R.id.erkek);
        Erkek=(RadioButton) findViewById(R.id.kadin);
        seekBar=(SeekBar) findViewById(R.id.seekBar);


        editText.addTextChangedListener(editTextOlayIsleyicisi);
        seekBar.setOnSeekBarChangeListener(seekBarOlayIsleyicisi);
        guncelle();
    }

    public void openActivity2(){
        Intent intent=new Intent(this, Activity2.class);
                startActivity(intent);
    }

    private void guncelle() {
        kilo_tv.setText(String.valueOf(kilo)+" kg");
        boy_tv.setText(String.valueOf(boy)+" m");

        int sonuc= (int) (50 +2.3 * ((boy * 100 * 0.4) - 60));
        ideal_tv.setText(String.valueOf(sonuc));
        double vki=kilo/(boy*boy);
        if (vki<=20.7){
           durum_tv.setText(R.string.zayif);
        }
        else if (20.7<vki&&vki<=26.4){
            durum_tv.setText(R.string.ideal);
        }
        else if (26.4<vki&&vki<=27.8){
            durum_tv.setText(R.string.fazla);
        }
        else if (27.8<vki&&vki<=31.1){
            durum_tv.setText(R.string.obez);
        }
        else if (31.1<vki&&vki<=34.9){
            durum_tv.setText(R.string.doktor);
        }

    }
}
