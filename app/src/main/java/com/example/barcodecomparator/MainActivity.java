package com.example.barcodecomparator;

import android.app.Dialog;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.Button;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;


public class MainActivity extends AppCompatActivity {
    private EditText editTextMaster;
    private EditText editTexSlave;
    private Button buttonReset;
    private MediaPlayer errorSound;
    private final String TAG = "Main Activity";

    private Button buttonNgDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextMaster = findViewById(R.id.result_master);
        editTexSlave = findViewById(R.id.result_slave);
        buttonReset = findViewById(R.id.button_reset);
        buttonNgDialog = findViewById(R.id.btn_ng_dialog);
        errorSound = MediaPlayer.create(this, R.raw.invalid);

        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
            }
        });

//        buttonNgDialog.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                errorSound.setLooping(false);
//                errorSound.stop();
//                try {
//                    errorSound.prepare();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        });

        editTextMaster.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if (editTextMaster.getText().toString().isEmpty()) {
                editTextMaster.requestFocus();
            } else {
                Log.d(TAG, "Master barcode has been set");
            }
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
            MediaPlayer successSound = MediaPlayer.create(this, R.raw.success2);
            successSound.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.release();
                }
            });
            showOkDialog();
            successSound.start();
        } else {
            Log.d(TAG, "Not Equal");
            errorSound.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.release();
                }
            });
            showNgDialog();
            errorSound.start();
            errorSound.setLooping(true);
        }
    }

    private void timerDelayRemoveDialog(long time, final Dialog d) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
            d.dismiss();
            reset();
            }
        }, time);
    }

//    public void onClickNgButtonDialog(View v) {
//        errorSound.setLooping(false);
//        errorSound.stop();
//        try {
//            errorSound.prepare();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    private void showOkDialog() {
        AlertDialog.Builder okDialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View okDialogView = inflater.inflate(R.layout.ok_custom_dialog, null);
        okDialog.setView(okDialogView);
        timerDelayRemoveDialog(600, okDialog.show());
    }

    private void showNgDialog() {
        AlertDialog.Builder ngDialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View ngDialogView = inflater.inflate(R.layout.ng_custom_dialog, null);
        ngDialog.setView(ngDialogView);
        ngDialog.show();
    }

    private void reset() {
        editTextMaster.setText("");
        editTexSlave.setText("");
        editTextMaster.requestFocus();
    }
}
