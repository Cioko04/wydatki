package com.example.finanse;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class HomeFragment extends Fragment {
    private  TextView textView;
    private Button dodaj, minus;
    private int progres = 0;
    private ProgressBar progressBar;


    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,
                container,false);
        textView = view.findViewById(R.id.text);
        dodaj = view.findViewById(R.id.dodaj);
        minus = view.findViewById(R.id.minus);
        progressBar = view.findViewById(R.id.progress);

        dodaj.setOnClickListener(v -> setDodaj());
        minus.setOnClickListener(v -> setMinus());
        return view;
    }

    public void setDodaj(){
        progres +=10;
        minus.setEnabled(true);
        if(progres<=50) progressBar.setSecondaryProgress(progres);
        if(progres>50) progressBar.setProgress(progres);
        if(progres==100) dodaj.setEnabled(false);
        textView.setText(progres + "%");
    }
    public void setMinus(){
        progres -=10;
        dodaj.setEnabled(true);
        if(progres<=50) {
            progressBar.setSecondaryProgress(progres);
            progressBar.setProgress(0);
        }
        if(progres>50) progressBar.setProgress(progres);
        if(progres==0) minus.setEnabled(false);
        textView.setText(progres + "%");
    }

}