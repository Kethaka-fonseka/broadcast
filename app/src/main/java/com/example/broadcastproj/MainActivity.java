package com.example.broadcastproj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textViewMsg;
    Reciever localListener;
    Button start;
public static  final String BROADCAST_ACTION="2122";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewMsg=findViewById(R.id.textViewMsg);
        start=findViewById(R.id.btnBroadcast);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BackgroundService.startAction(getApplicationContext());
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
   localListener=new Reciever();
        IntentFilter intentFilter=new IntentFilter(BROADCAST_ACTION);
        this.registerReceiver(localListener,intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        this.unregisterReceiver(localListener);
    }



    public class Reciever extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            String currentText=textViewMsg.getText().toString();
            String message=intent.getStringExtra("value");
            currentText+="\nRecieved "+ message;
            textViewMsg.setText(currentText);
        }
    }
}