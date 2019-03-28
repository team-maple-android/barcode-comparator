package com.example.barcodecomparator;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Button;
import android.view.View;
import android.widget.LinearLayout;


public class MainActivity extends AppCompatActivity {
    public EditText editTextMaster;
    public EditText editTexSlave;
    public Button buttonReset;
    public Button button_ng;
    //private final String TAG = "AUC_SIMPLE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        editTextMaster = findViewById(R.id.result_master);
        editTexSlave = findViewById(R.id.result_slave);
        buttonReset = findViewById(R.id.button_reset);
        button_ng = findViewById(R.id.button_sam2);

        /*Reset values in all  edit text*/
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Set the first EditText empty
                editTextMaster.setText("");
                // Clear the second EditText
                editTexSlave.getText().clear();
                // Focus the text field after clear
                editTextMaster.requestFocus();
            }
        });
        /*End - clear values*/



        /* pop-up message for MisMatched*/
        button_ng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ContextThemeWrapper ctw = new ContextThemeWrapper(MainActivity.this, R.style.MyTheme);

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                //LayoutInflater inflater = getLayoutInflater();
                //View dialogView = inflater.inflate(R.layout.alert_dialog_custom_view,null);
                //builder.setView(dialogView);

                builder.setTitle("SOMETHING WENT WRONG!");
                builder.setMessage("\n\t\t\t\t\t\t Barcode doesn't match.");
                builder.setIcon(R.drawable.error);
                builder.setCancelable( false );

                //actions when button clicked
                builder.setPositiveButton("N G", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        editTextMaster.setText("");
                        // Clear the second EditText
                        editTexSlave.getText().clear();
                        // Focus the text field after clear
                        editTextMaster.requestFocus();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();

                //button formatting
                final Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                positiveButton.setTextSize(TypedValue.COMPLEX_UNIT_SP,15);
               // positiveButton.setTextColor(Color.rgb(255,255,255));
              //  positiveButton.setBackgroundColor(Color.rgb(128,195,196));


            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
