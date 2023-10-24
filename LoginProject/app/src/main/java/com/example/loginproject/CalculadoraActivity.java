package com.example.loginproject;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.os.Bundle;

public class CalculadoraActivity extends AppCompatActivity {
    private EditText editTextNumber1;
    private EditText editTextNumber2;
    private TextView textViewResult;
    private TextView totalTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora);

        editTextNumber1 = findViewById(R.id.editTextNumber1);
        editTextNumber2 = findViewById(R.id.editTextNumber2);
        textViewResult = findViewById(R.id.textViewResult);
        totalTotal = findViewById(R.id.totalTotal);
    }

    public void calculateSum(View view) {
        try {
            double num1 = Double.parseDouble(editTextNumber1.getText().toString());
            double num2 = Double.parseDouble(editTextNumber2.getText().toString());
            double result = num1 + num2;
            double total = 0;
            total = result + total;
            textViewResult.setText("Resultado: " + result);
            totalTotal.setText("Resultado TOTAL: " + total);
        } catch (NumberFormatException e) {
            textViewResult.setText("Por favor, insira números válidos.");
        }
    }
}