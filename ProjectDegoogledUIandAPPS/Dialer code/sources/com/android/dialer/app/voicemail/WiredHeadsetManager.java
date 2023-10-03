package com.android.dialer.app.voicemail;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import com.android.tools.p006r8.GeneratedOutlineSupport;

class WiredHeadsetManager {
    private Context context;
    private boolean isPluggedIn;
    private Listener listener;
    private final WiredHeadsetBroadcastReceiver receiver = new WiredHeadsetBroadcastReceiver((C03551) null);

    interface Listener {
    }

    private class WiredHeadsetBroadcastReceiver extends BroadcastReceiver {
        /* synthetic */ WiredHeadsetBroadcastReceiver(C03551 r2) {
        }

        public void onReceive(Context context, Intent intent) {
            if ("android.intent.action.HEADSET_PLUG".equals(intent.getAction())) {
                boolean z = false;
                if (intent.getIntExtra("state", 0) == 1) {
                    z = true;
                }
                "ACTION_HEADSET_PLUG event, plugged in: " + z;
                WiredHeadsetManager.access$200(WiredHeadsetManager.this, z);
            }
        }
    }

    WiredHeadsetManager(Context context2) {
        this.context = context2;
        this.isPluggedIn = ((AudioManager) context2.getSystemService("audio")).isWiredHeadsetOn();
    }

    static /* synthetic */ void access$200(WiredHeadsetManager wiredHeadsetManager, boolean z) {
        if (wiredHeadsetManager.isPluggedIn != z) {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("onHeadsetPluggedInChanged, mIsPluggedIn: ");
            outline13.append(wiredHeadsetManager.isPluggedIn);
            outline13.append(" -> ");
            outline13.append(z);
            outline13.toString();
            boolean z2 = wiredHeadsetManager.isPluggedIn;
            wiredHeadsetManager.isPluggedIn = z;
            Listener listener2 = wiredHeadsetManager.listener;
            if (listener2 != null) {
                ((VoicemailAudioManager) listener2).onWiredHeadsetPluggedInChanged(z2, wiredHeadsetManager.isPluggedIn);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isPluggedIn() {
        return this.isPluggedIn;
    }

    /* access modifiers changed from: package-private */
    public void registerReceiver() {
        this.context.registerReceiver(this.receiver, new IntentFilter("android.intent.action.HEADSET_PLUG"));
    }

    /* access modifiers changed from: package-private */
    public void setListener(Listener listener2) {
        this.listener = listener2;
    }

    /* access modifiers changed from: package-private */
    public void unregisterReceiver() {
        this.context.unregisterReceiver(this.receiver);
    }
}
