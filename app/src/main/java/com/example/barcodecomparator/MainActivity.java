package com.example.barcodecomparator;

import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.Button;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    private EditText editTextMaster;
    private EditText editTexSlave;
    private Button buttonReset;
    private Button button_ng;
    private AlertDialog.Builder ngDialog;
    private final String TAG = "Main Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextMaster = findViewById(R.id.result_master);
        editTexSlave = findViewById(R.id.result_slave);
        buttonReset = findViewById(R.id.button_reset);
        button_ng = findViewById(R.id.button_sam2);

        ngDialog = new AlertDialog.Builder(this)
            .setTitle("MISMATCH")
            .setMessage("Barcodes are mismatched")
            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Log.d(TAG, "Test func");
                }
            })
            .setNegativeButton(android.R.string.no, null)
            .setIcon(R.drawable.error);

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

        editTextMaster.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                Log.d(TAG, "Master barcode has been set");
                return false;
            }
        });

        editTexSlave.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                Log.d(TAG, "Slave barcode has been set");
                compareBarcodes();
                return false;
            }
        });
    }

    private void compareBarcodes() {
        if (editTextMaster.getText().toString().equals(editTexSlave.getText().toString())) {
            Log.d(TAG, "Equal");
            MediaPlayer successSound = MediaPlayer.create(this, R.raw.success);
            successSound.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.release();
                }
            });
            successSound.start();
        } else {
            Log.d(TAG, "Not Equal");
            ngDialog.show();
            MediaPlayer errorSound = MediaPlayer.create(this, R.raw.invalid);
            errorSound.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.release();
                }
            });
            errorSound.start();
            errorSound.setLooping(true);
        }
    }
}
