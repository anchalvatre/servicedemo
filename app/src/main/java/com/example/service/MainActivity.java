package com.example.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
  MyService myservice;
  private Button stop;
  private TextView text;
  Messenger messenger;
  Message msg;
  static Context appcontext;
  public void onstart(View v) throws RemoteException {
    Bundle bundle = new Bundle();
    bundle.putString("data",text.getText().toString());
    msg = (Message) Message.obtain(null, 1);

    msg.setData(bundle);

    messenger.send(msg);
  }
  public void onstop(View v) throws RemoteException {

  msg = (Message) Message.obtain(null,2);
  messenger.send(msg);

  }
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    appcontext = getApplicationContext();

    stop = (Button) findViewById(R.id.stop);

    text = (TextView) findViewById(R.id.text);
    Intent intent = new Intent(this,MyService.class);
    this.bindService(intent,myserviceconnection, Context.BIND_AUTO_CREATE);

  }

  ServiceConnection myserviceconnection = new ServiceConnection() {
    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
      messenger = new Messenger(service);
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }
  };

  public static Context getAppcontext(){
    return appcontext;
  }

}
