package br.com.rfelipe.fuelcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    private NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();

    private TextInputEditText resEditText;
    private TextView gasPriceTextView;
    private TextView AlcoholPriceTextView;
    private Double gasPrice = 0.0;
    private Double AlcoholPrice = 0.0;
    private Double ratioPrice = 0.0;
    private Double  mostAdvantageousFuel = 0.0;
    private ImageView res_ImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resEditText = findViewById(R.id.resEditText);
        res_ImageView = findViewById(R.id.res_ImageView);
        gasPriceTextView = findViewById(R.id.gasPriceTextView);
        AlcoholPriceTextView = findViewById(R.id.AlcoholPriceTextView);

        SeekBar gasPriceSeekBar = findViewById(R.id.gasPriceSeekBar);
        gasPriceSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                gasPrice = progress / 100D;
                gasPriceTextView.setText(currencyFormat.format(gasPrice));
                evaluationBetterPrice();
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        final SeekBar alcoholPriceSeekBar = findViewById(R.id.alcoholPriceSeekBar);
        alcoholPriceSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                AlcoholPrice = progress / 100D;
                AlcoholPriceTextView.setText(currencyFormat.format(AlcoholPrice));
                evaluationBetterPrice();
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

    }


    private SeekBar.OnSeekBarChangeListener onSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {}
        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
        }
        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
        }
    };

    private void evaluationBetterPrice(){

        ratioPrice = AlcoholPrice / gasPrice;

        if(ratioPrice < 0.7){
            resEditText.setText(R.string.resultTextEthanol);
            res_ImageView.setImageResource(R.drawable.alcool);

        }else{
            resEditText.setText(R.string.resultTextGasoline);
            res_ImageView.setImageResource(R.drawable.gasolina);
        }

    }

}
