package com.android.dialer.app.voicemail;

import android.content.Context;
import android.media.AudioDeviceInfo;
import android.media.AudioManager;
import android.telecom.CallAudioState;
import com.android.dialer.app.voicemail.WiredHeadsetManager;
import com.android.dialer.common.LogUtil;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.util.concurrent.RejectedExecutionException;

public final class VoicemailAudioManager implements AudioManager.OnAudioFocusChangeListener, WiredHeadsetManager.Listener {
    private AudioManager audioManager;
    private boolean bluetoothScoEnabled;
    private CallAudioState callAudioState;
    private VoicemailPlaybackPresenter voicemailPlaybackPresenter;
    private boolean wasSpeakerOn;
    private WiredHeadsetManager wiredHeadsetManager;

    public VoicemailAudioManager(Context context, VoicemailPlaybackPresenter voicemailPlaybackPresenter2) {
        this.audioManager = (AudioManager) context.getSystemService("audio");
        this.voicemailPlaybackPresenter = voicemailPlaybackPresenter2;
        this.wiredHeadsetManager = new WiredHeadsetManager(context);
        this.wiredHeadsetManager.setListener(this);
        int calculateSupportedRoutes = calculateSupportedRoutes();
        this.callAudioState = new CallAudioState(false, selectWiredOrEarpiece(5, calculateSupportedRoutes), calculateSupportedRoutes);
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("Initial audioState = ");
        outline13.append(this.callAudioState);
        LogUtil.m9i("VoicemailAudioManager.VoicemailAudioManager", outline13.toString(), new Object[0]);
    }

    private void applyBluetoothScoState() {
        if (this.bluetoothScoEnabled) {
            this.audioManager.startBluetoothSco();
            this.audioManager.setBluetoothScoOn(true);
            return;
        }
        this.audioManager.setBluetoothScoOn(false);
        this.audioManager.stopBluetoothSco();
    }

    private int calculateSupportedRoutes() {
        return this.wiredHeadsetManager.isPluggedIn() ? 12 : 9;
    }

    private int selectWiredOrEarpiece(int i, int i2) {
        if (i != 5) {
            return i;
        }
        int i3 = i2 & 5;
        if (i3 != 0) {
            return i3;
        }
        LogUtil.m8e("VoicemailAudioManager.selectWiredOrEarpiece", "One of wired headset or earpiece should always be valid.", new Object[0]);
        return 1;
    }

    private void setSystemAudioState(CallAudioState callAudioState2) {
        CallAudioState callAudioState3 = this.callAudioState;
        this.callAudioState = callAudioState2;
        LogUtil.m9i("VoicemailAudioManager.setSystemAudioState", "changing from " + callAudioState3 + " to " + this.callAudioState, new Object[0]);
        if (this.callAudioState.getRoute() == 8) {
            turnOnSpeaker(true);
        } else if (this.callAudioState.getRoute() == 1 || this.callAudioState.getRoute() == 4) {
            turnOnSpeaker(false);
            applyBluetoothScoState();
        }
    }

    private void turnOnSpeaker(boolean z) {
        if (this.audioManager.isSpeakerphoneOn() != z) {
            LogUtil.m9i("VoicemailAudioManager.turnOnSpeaker", GeneratedOutlineSupport.outline10("turning speaker phone on: ", z), new Object[0]);
            this.audioManager.setSpeakerphoneOn(z);
        }
    }

    private void updateBluetoothScoState(boolean z) {
        boolean z2;
        if (z) {
            AudioDeviceInfo[] devices = this.audioManager.getDevices(2);
            int length = devices.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    z2 = false;
                    break;
                } else if (devices[i].getType() == 8) {
                    z2 = true;
                    break;
                } else {
                    i++;
                }
            }
            if (z2) {
                this.bluetoothScoEnabled = false;
            } else {
                this.bluetoothScoEnabled = true;
                LogUtil.m9i("VoicemailAudioManager.updateBluetoothScoState", "bluetooth device doesn't support media, using SCO instead", new Object[0]);
            }
        } else {
            this.bluetoothScoEnabled = false;
        }
        applyBluetoothScoState();
    }

    public void abandonAudioFocus() {
        updateBluetoothScoState(false);
        this.audioManager.abandonAudioFocus(this);
    }

    public boolean isWiredHeadsetPluggedIn() {
        return this.wiredHeadsetManager.isPluggedIn();
    }

    public void onAudioFocusChange(int i) {
        "focusChange=" + i;
        VoicemailPlaybackPresenter voicemailPlaybackPresenter2 = this.voicemailPlaybackPresenter;
        boolean z = true;
        if (i != 1) {
            z = false;
        }
        voicemailPlaybackPresenter2.onAudioFocusChange(z);
    }

    public void onWiredHeadsetPluggedInChanged(boolean z, boolean z2) {
        int i;
        LogUtil.m9i("VoicemailAudioManager.onWiredHeadsetPluggedInChanged", "wired headset was plugged in changed: " + z + " -> " + z2, new Object[0]);
        if (z != z2) {
            this.callAudioState.getRoute();
            boolean z3 = true;
            if (z2) {
                i = 4;
            } else {
                this.voicemailPlaybackPresenter.pausePlayback();
                i = this.wasSpeakerOn ? 8 : 1;
            }
            VoicemailPlaybackPresenter voicemailPlaybackPresenter2 = this.voicemailPlaybackPresenter;
            if (i != 8) {
                z3 = false;
            }
            voicemailPlaybackPresenter2.setSpeakerphoneOn(z3);
            setSystemAudioState(new CallAudioState(false, i, this.wiredHeadsetManager.isPluggedIn() ? 12 : 9));
        }
    }

    public void registerReceivers() {
        this.wiredHeadsetManager.registerReceiver();
    }

    public void requestAudioFocus() {
        if (this.audioManager.requestAudioFocus(this, 0, 2) == 1) {
            updateBluetoothScoState(true);
            return;
        }
        throw new RejectedExecutionException("Could not capture audio focus.");
    }

    public void setSpeakerphoneOn(boolean z) {
        int i = z ? 8 : 5;
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("route: ");
        outline13.append(CallAudioState.audioRouteToString(i));
        outline13.toString();
        int selectWiredOrEarpiece = selectWiredOrEarpiece(i, this.callAudioState.getSupportedRouteMask());
        if ((this.callAudioState.getSupportedRouteMask() | selectWiredOrEarpiece) == 0) {
            LogUtil.m10w("VoicemailAudioManager.setAudioRoute", GeneratedOutlineSupport.outline5("Asking to set to a route that is unsupported: ", selectWiredOrEarpiece), new Object[0]);
            return;
        }
        this.wasSpeakerOn = selectWiredOrEarpiece == 8;
        setSystemAudioState(new CallAudioState(false, selectWiredOrEarpiece, this.callAudioState.getSupportedRouteMask()));
    }

    public void unregisterReceivers() {
        this.wiredHeadsetManager.unregisterReceiver();
    }
}
