package com.example.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {

  String text;
  String context;
  Messenger messenger = new Messenger(new incommingMessenger());
  MainActivity n = new MainActivity();


  @Override
  public IBinder onBind(Intent intent) {
    return messenger.getBinder();
  }

  public void OnStart() {
    Toast.makeText(this, "service started", Toast.LENGTH_SHORT).show();
  }

  public void onDestroy() {
    super.onDestroy();
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    Log.d(this.toString(), text);


  }


  public class incommingMessenger extends Handler {

    @Override
    public void handleMessage(Message msg) {
      super.handleMessage(msg);
      if (msg.what == 1) {
        Bundle bundle = msg.getData();
        text = bundle.getString("data");
        context = bundle.getString("context");
        OnStart();
      }
      if (msg.what == 2) {
        onDestroy();

      }
    }


  }

}




