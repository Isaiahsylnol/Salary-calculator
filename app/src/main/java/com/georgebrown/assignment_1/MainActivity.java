package com.georgebrown.assignment_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText txtRate, txtHours;
    TextView display;
    Button btnDisplay, button_secAct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtRate = findViewById(R.id.editTextAmount);
        txtHours = findViewById(R.id.editTextHours);
        display =findViewById(R.id.tvDisplay);
        btnDisplay = findViewById(R.id.btnDisplay);

        btnDisplay.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(txtRate.getText().toString().isEmpty() || txtHours.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this, "Empty fields", Toast.LENGTH_SHORT).show();
                }else{
                    Double rate = Double.parseDouble(txtRate.getText().toString());
                    Double hours = Double.parseDouble(txtHours.getText().toString());
                    Double income = 0.0;
                    Double tax = 0.0;
                    Double overtime = 0.0;

                    // Calculation logic & conditions
                    if(hours <= 40){
                        income = rate * hours;
                        tax = income * 0.18;
                    }else{
                        overtime = (hours - 40) * rate * 1.5;
                        income =  (hours - 40) * rate * 1.5 + 40 * rate;
                        tax = income * 0.18;
                    }

                    // Output formatting for display
                    String output = "Pay hourly: $" + String.format("%.2f", rate) + "\n"
                            + "Overtime Pay: $" + String.format("%.2f", overtime) + "\n"
                            + "Total Pay: $" + String.format("%.2f", income) + "\n"
                            + "Tax: $" + String.format("%.2f", tax);

                    TextView txtView = (TextView)findViewById(R.id.tvDisplay);
                    InputMethodManager inputManager = (InputMethodManager)
                            getSystemService(Context.INPUT_METHOD_SERVICE);

                    // Hide the virtual keyboard upon calculate click
                    inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);

                    // Toggle for calculation output
                    if (txtView.getVisibility() == View.INVISIBLE)
                        txtView.setVisibility(View.VISIBLE);
                    display.setText(output);
                }
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.nav_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.item1:
                // Launch About activity upon click
                startActivity(new Intent(MainActivity.this, AboutActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}