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

public class HomeFragment extends Fragment {
    private  TextView textView;
    private Button dodaj, minus;
    private EditText kwota;
    private int budzet = 2500, wydane = 0;
    private ProgressBar progressBar;


    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,
                container,false);
        textView = view.findViewById(R.id.text);
        kwota = view.findViewById(R.id.kwota);
        dodaj = view.findViewById(R.id.dodaj);
        minus = view.findViewById(R.id.minus);
        progressBar = view.findViewById(R.id.progress);
        progressBar.setMax(budzet);

        dodaj.setOnClickListener(v -> setDodaj());
        minus.setOnClickListener(v -> setMinus());
        return view;
    }


    public void setDodaj(){
        wydane += Integer.parseInt(kwota.getText().toString());
        minus.setEnabled(true);
        if(wydane <=(budzet/2)) progressBar.setSecondaryProgress(wydane);
        if(wydane >(budzet/2)) progressBar.setProgress(wydane);
        if(wydane >= budzet) dodaj.setEnabled(false);
        textView.setText("Wydane: " + wydane + " zł\n"+
                "Budżet: " + budzet + " zł") ;
    }
    public void setMinus(){
        wydane -= Integer.parseInt(kwota.getText().toString());
        dodaj.setEnabled(true);
        if(wydane <= (budzet/2)) {
            progressBar.setSecondaryProgress(wydane);
            progressBar.setProgress(0);
        }
        if(wydane > (budzet/2)) progressBar.setProgress(wydane);
        if(wydane <=0) minus.setEnabled(false);
        textView.setText("Wydane: "+ wydane + " zł\n"+
                "Budżet" + budzet + " zł");
    }

}