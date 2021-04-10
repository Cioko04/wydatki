package com.example.finanse;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class HomeFragment extends Fragment {
    private  TextView textView;
    private Button dodaj, minus;
    private EditText textKwota;
    private int budzet = 2500, wydane = 0;
    private ProgressBar progressBar;
    private View view;


    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_home,
                container,false);

        setUp();

        dodaj.setOnClickListener(v -> setDodaj());
        minus.setOnClickListener(v -> setMinus());

        return view;
    }
    public void setUp(){
        textView = view.findViewById(R.id.text);
        textKwota = view.findViewById(R.id.kwota);
        dodaj = view.findViewById(R.id.dodaj);
        minus = view.findViewById(R.id.minus);
        progressBar = view.findViewById(R.id.progress);
        progressBar.setMax(budzet);
        textView.setText("Budżet: " + budzet + " zł");
    }

    public int getKwota(){
        int kwota = 0;
        try{
            kwota = Integer.parseInt(textKwota.getText().toString());
        }catch (Exception e){
            Toast.makeText(getActivity(),"Błędne dane!",Toast.LENGTH_SHORT).show();
        }
        return kwota;
    }

    public void setDodaj(){
        wydane += getKwota();
        minus.setEnabled(true);
        if(wydane <=(budzet/2)) progressBar.setSecondaryProgress(wydane);
        if(wydane >(budzet/2)) progressBar.setProgress(wydane);
        if(wydane >= budzet) dodaj.setEnabled(false);
        textView.setText("Wydane: " + wydane + " zł\n"+
                "Budżet: " + budzet + " zł") ;
    }

    public void setMinus(){
        wydane -= getKwota();
        dodaj.setEnabled(true);
        if(wydane <= (budzet/2)) {
            progressBar.setSecondaryProgress(wydane);
            progressBar.setProgress(0);
        }
        if(wydane > (budzet/2)) progressBar.setProgress(wydane);
        if(wydane <=0) minus.setEnabled(false);
        textView.setText("Wydane: "+ wydane + " zł\n"+
                "Budżet: " + budzet + " zł");
    }

}