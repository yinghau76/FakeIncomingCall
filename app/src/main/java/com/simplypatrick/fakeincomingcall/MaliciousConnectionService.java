package com.simplypatrick.fakeincomingcall;

import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.telecom.Connection;
import android.telecom.ConnectionRequest;
import android.telecom.ConnectionService;
import android.telecom.PhoneAccountHandle;
import android.telecom.TelecomManager;

public class MaliciousConnectionService extends ConnectionService {
    @Override
    public Connection onCreateIncomingConnection(PhoneAccountHandle connectionManagerPhoneAccount, ConnectionRequest request) {
        final TextToSpeech textToSpeech = new TextToSpeech(this, null);

        Connection conn = new Connection() {
            @Override
            public void onAnswer() {
                setActive();

                Bundle params = new Bundle();
                params.putInt(TextToSpeech.Engine.KEY_PARAM_STREAM, AudioManager.STREAM_VOICE_CALL);
                textToSpeech.speak("How are you doing?", TextToSpeech.QUEUE_FLUSH, params, "howdy");
            }

            @Override
            public void onDisconnect() {
                destroy();
            }
        };
        conn.setAddress(Uri.parse("tel:01234567890"), TelecomManager.PRESENTATION_ALLOWED);
        conn.setCallerDisplayName("Fake Name", TelecomManager.PRESENTATION_ALLOWED);
        return conn;
    }
}
