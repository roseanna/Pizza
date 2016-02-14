package com.example.roseanna.pizza;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import java.util.*;
import android.util.Log;
import android.app.Activity;

public class MainActivity extends Activity implements AdapterView.OnItemSelectedListener{

    TextView txtMsg;
    Spinner spinner;
    TextView result;

    int sizeCost    = 0;
    int vChosen     = 0;
    int mChosen     = 0;
    int vMult       = 1;
    int mMult       = 1;
    int total       = 0;

    String pSize    = "Small";
    String [] sizeOptions   = {"Small", "Medium", "Large"};
    String [] vegToppings   = {"Lettuce", "Spinach", "Mushroom"};
    String [] meatToppings  = {"Sausage", "Pepperroni", "Chicken"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtMsg  = (TextView) findViewById(R.id.txtMsg);
        spinner = (Spinner)  findViewById(R.id.spinner1);
        result  = (TextView) findViewById(R.id.result);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                sizeOptions);

        // bind everything together
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        txtMsg.setText(sizeOptions[position]);
        switch (position){
            case 0:
                sizeCost = 5;
                pSize    = "Small";
                break;
            case 1:
                sizeCost = 7;
                pSize    = "Medium";
                break;
            case 2:
                sizeCost = 10;
                pSize    = "Large";
                break;
        }
        Log.i("sizeCost", String.valueOf(sizeCost));
        setText();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // Do nothing
    }

    public void onCheckboxClicked(View view) {

        Log.i("oncheckbox", "clicked");
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();


        boolean isMeat = (view.getId() == R.id.checkbox_m1 || view.getId() == R.id.checkbox_m2 || view.getId() == R.id.checkbox_m3);
        boolean isVeg  = (view.getId() == R.id.checkbox_v1 || view.getId() == R.id.checkbox_v2 || view.getId() == R.id.checkbox_v3);
        if (isMeat){
            if (checked)
                mChosen++;
            else
                mChosen--;
        }
        if(isVeg){
            if(checked)
                vChosen++;
            else
                vChosen--;
        }
        Log.i("Meat", String.valueOf(mChosen));
        Log.i("Veggie", String.valueOf(vChosen));
        setText();
    }


    public void createCB(String[] array){

    }

    // setText calls calculate. Gets called whenever anything changes with
    // spinner or checkboxes
    public void setText(){
        calculate();
        String resultString = "Your total for a " + pSize + " pizza with ";
        resultString += String.valueOf(mChosen) + " meat toppings, " + String.valueOf(vChosen) + " veggie toppings is $";
        resultString += String.valueOf(total);

        result.setText(resultString);
    }
    public void calculate(){
        total = 0;
        if (sizeCost == 5){
            vMult = 1;
            mMult = 2;
        }
        else if (sizeCost == 7){
            vMult = 2;
            mMult = 4;
        }
        else{
            vMult = 3;
            mMult = 6;
        }
        total = sizeCost + (vMult * vChosen) + (mMult * mChosen);
    }
}
