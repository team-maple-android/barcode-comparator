package com.example.barcodecomparator;

import android.app.Dialog;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.Button;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    private EditText editTextMaster;
    private EditText editTexSlave;
    private Button buttonReset;
    private Button button_ng;
    private Button button_ok;
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
        button_ok = findViewById(R.id.button_sam1);

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
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable( false );
                LayoutInflater inflater = getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.alert_dialog_custom_view,null);
                builder.setView(dialogView);
                final AlertDialog dialog = builder.create();
                dialog.show();

                Button dialogButton = dialog.findViewById(R.id.btn_dialog);
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //close alert dialog
                        dialog.cancel();
                        //clear first edit text
                        editTextMaster.setText("");
                        // Clear the second EditText
                        editTexSlave.getText().clear();
                        // Focus the text field after clear
                        editTextMaster.requestFocus();
                    }
                });

            }
        });

        /* pop-up message for Matched*/
        button_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable( false );
                LayoutInflater inflater = getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.alert_dialog_custom_view_success,null);
                builder.setView(dialogView);
                final AlertDialog dialog = builder.create();
                dialog.show();
                //clear first edit text
                editTextMaster.setText("");
                // Clear the second EditText
                editTexSlave.getText().clear();
                // Focus the text field after clear
                editTextMaster.requestFocus();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dialog.cancel();
                    }
                },500);


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
