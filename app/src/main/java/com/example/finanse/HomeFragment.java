package com.example.finanse;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class HomeFragment extends Fragment {
    private TextView textView;
    private Button dodaj;
    private EditText textKwota;
    private int budzetPr = 2500;
    private double budzet = 2500, wydane = 0;
    private ProgressBar progressBar;
    private View view;
    private Spinner spinner;
    private String[] typ;


    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_home,
                container, false);

        setUp();
        spinnerSetUp();
        dodaj.setOnClickListener(v -> setDodaj());

        return view;
    }

    private void spinnerSetUp() {
        typ = new String[]{"Żywność", "Opłaty", "Ubrania", "Prezenty"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, typ);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
    }

    public void setUp() {
        spinner = view.findViewById(R.id.spinner);
        textView = view.findViewById(R.id.text);
        textKwota = view.findViewById(R.id.kwota);
        dodaj = view.findViewById(R.id.dodaj);
        progressBar = view.findViewById(R.id.progress);
        progressBar.setMax((int) (budzet));
        textView.setText("Budżet: " + budzet + " zł");
    }

    public double getKwota() {
        double kwota = 0;
        try {
            kwota = Double.parseDouble(textKwota.getText().toString());
        } catch (Exception e) {
            Toast.makeText(getActivity(), "Wprowadź kwotę!", Toast.LENGTH_SHORT).show();
        }
        return kwota;
    }

    public void setDodaj() {
        wydane += getKwota();
        progressBar.setSecondaryProgress((int) wydane);
        if ((wydane / budzet) * 100 > 50) {
            progressBar.setProgress((int) (wydane));
        }
        textView.setText("Wydane: " + wydane + " zł\n" +
                "Budżet: " + budzet + " zł");

    }


}