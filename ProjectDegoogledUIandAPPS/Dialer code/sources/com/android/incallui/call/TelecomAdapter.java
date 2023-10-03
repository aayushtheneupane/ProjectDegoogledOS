package com.android.incallui.call;

import android.annotation.TargetApi;
import android.app.Notification;
import android.bluetooth.BluetoothDevice;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Looper;
import android.telecom.Call;
import android.telecom.InCallService;
import com.android.dialer.common.Assert;
import com.android.dialer.common.LogUtil;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.util.List;

public class TelecomAdapter implements InCallServiceListener {
    private static TelecomAdapter instance;
    private InCallService inCallService;

    private TelecomAdapter() {
    }

    public static TelecomAdapter getInstance() {
        if (Looper.getMainLooper().isCurrentThread()) {
            if (instance == null) {
                instance = new TelecomAdapter();
            }
            return instance;
        }
        throw new IllegalStateException();
    }

    private Call getTelecomCallById(String str) {
        DialerCall callById = CallList.getInstance().getCallById(str);
        if (callById == null) {
            return null;
        }
        return callById.getTelecomCall();
    }

    public static void setInstanceForTesting(TelecomAdapter telecomAdapter) {
        instance = telecomAdapter;
    }

    public void addCall() {
        if (this.inCallService != null) {
            Intent intent = new Intent("android.intent.action.DIAL");
            intent.addFlags(268435456);
            intent.putExtra("add_call_mode", true);
            try {
                this.inCallService.startActivity(intent);
            } catch (ActivityNotFoundException e) {
                LogUtil.m7e("TelecomAdapter.addCall", "Activity for adding calls isn't found.", (Throwable) e);
            }
        }
    }

    public boolean canAddCall() {
        InCallService inCallService2 = this.inCallService;
        if (inCallService2 != null) {
            return inCallService2.canAddCall();
        }
        return false;
    }

    public void clearInCallService() {
        this.inCallService = null;
    }

    public void merge(String str) {
        Call telecomCallById = getTelecomCallById(str);
        if (telecomCallById != null) {
            List<Call> conferenceableCalls = telecomCallById.getConferenceableCalls();
            if (!conferenceableCalls.isEmpty()) {
                telecomCallById.conference(conferenceableCalls.get(0));
                DialerCall.clearRestrictedCount();
            } else if (telecomCallById.getDetails().can(4)) {
                telecomCallById.mergeConference();
                DialerCall.clearRestrictedCount();
            }
        } else {
            LogUtil.m8e("TelecomAdapter.merge", GeneratedOutlineSupport.outline8("call not in call list ", str), new Object[0]);
        }
    }

    public void mute(boolean z) {
        InCallService inCallService2 = this.inCallService;
        if (inCallService2 != null) {
            inCallService2.setMuted(z);
        } else {
            LogUtil.m8e("TelecomAdapter.mute", "mInCallService is null", new Object[0]);
        }
    }

    public void playDtmfTone(String str, char c) {
        Call telecomCallById = getTelecomCallById(str);
        if (telecomCallById != null) {
            telecomCallById.playDtmfTone(c);
        } else {
            LogUtil.m8e("TelecomAdapter.playDtmfTone", GeneratedOutlineSupport.outline8("call not in call list ", str), new Object[0]);
        }
    }

    public void postDialContinue(String str, boolean z) {
        Call telecomCallById = getTelecomCallById(str);
        if (telecomCallById != null) {
            telecomCallById.postDialContinue(z);
        } else {
            LogUtil.m8e("TelecomAdapter.postDialContinue", GeneratedOutlineSupport.outline8("call not in call list ", str), new Object[0]);
        }
    }

    @TargetApi(28)
    public void requestBluetoothAudio(BluetoothDevice bluetoothDevice) {
        InCallService inCallService2 = this.inCallService;
        if (inCallService2 != null) {
            inCallService2.requestBluetoothAudio(bluetoothDevice);
        } else {
            LogUtil.m8e("TelecomAdapter.requestBluetoothAudio", "inCallService is null", new Object[0]);
        }
    }

    public void setAudioRoute(int i) {
        InCallService inCallService2 = this.inCallService;
        if (inCallService2 != null) {
            inCallService2.setAudioRoute(i);
        } else {
            LogUtil.m8e("TelecomAdapter.setAudioRoute", "mInCallService is null", new Object[0]);
        }
    }

    public void setInCallService(InCallService inCallService2) {
        this.inCallService = inCallService2;
    }

    public void startForegroundNotification(int i, Notification notification) {
        Assert.isNotNull(this.inCallService, "No inCallService available for starting foreground notification", new Object[0]);
        this.inCallService.startForeground(i, notification);
    }

    public void stopDtmfTone(String str) {
        Call telecomCallById = getTelecomCallById(str);
        if (telecomCallById != null) {
            telecomCallById.stopDtmfTone();
        } else {
            LogUtil.m8e("TelecomAdapter.stopDtmfTone", GeneratedOutlineSupport.outline8("call not in call list ", str), new Object[0]);
        }
    }

    public void stopForegroundNotification() {
        InCallService inCallService2 = this.inCallService;
        if (inCallService2 != null) {
            inCallService2.stopForeground(true);
        } else {
            LogUtil.m8e("TelecomAdapter.stopForegroundNotification", "no inCallService available for stopping foreground notification", new Object[0]);
        }
    }

    public void swap(String str) {
        Call telecomCallById = getTelecomCallById(str);
        if (telecomCallById == null) {
            LogUtil.m8e("TelecomAdapter.swap", GeneratedOutlineSupport.outline8("call not in call list ", str), new Object[0]);
        } else if (telecomCallById.getDetails().can(8)) {
            telecomCallById.swapConference();
        }
    }
}
