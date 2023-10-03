package com.android.dialer.app.voicemail;

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.os.Handler;
import android.telecom.PhoneAccountHandle;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.TelephonyManager;
import android.util.ArrayMap;
import com.android.dialer.app.calllog.CallLogAlertManager;
import com.android.dialer.app.calllog.CallLogModalAlertManager;
import com.android.dialer.common.Assert;
import com.android.dialer.common.LogUtil;
import com.android.dialer.database.CallLogQueryHandler;
import com.android.dialer.voicemail.listui.error.VoicemailErrorAlert;
import com.android.dialer.voicemail.listui.error.VoicemailErrorMessageCreator;
import com.android.dialer.voicemail.listui.error.VoicemailStatus;
import com.android.dialer.voicemail.listui.error.VoicemailStatusReader;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.android.voicemail.VoicemailComponent;
import java.util.ArrayList;
import java.util.Map;

public class VoicemailErrorManager implements CallLogQueryHandler.Listener, VoicemailStatusReader {
    private final VoicemailErrorAlert alertItem;
    private final CallLogQueryHandler callLogQueryHandler;
    private final Context context;
    private boolean isForeground;
    private final Map<PhoneAccountHandle, ServiceStateListener> listeners = new ArrayMap();
    private boolean statusInvalidated;
    private final ContentObserver statusObserver = new ContentObserver(new Handler()) {
        public void onChange(boolean z) {
            super.onChange(z);
            VoicemailErrorManager.this.fetchStatus();
        }
    };

    private class ServiceStateListener extends PhoneStateListener {
        /* synthetic */ ServiceStateListener(C03441 r2) {
        }

        public void onServiceStateChanged(ServiceState serviceState) {
            VoicemailErrorManager.this.fetchStatus();
        }
    }

    public VoicemailErrorManager(Context context2, CallLogAlertManager callLogAlertManager, CallLogModalAlertManager callLogModalAlertManager) {
        this.context = context2;
        this.alertItem = new VoicemailErrorAlert(context2, callLogAlertManager, callLogModalAlertManager, new VoicemailErrorMessageCreator());
        this.callLogQueryHandler = new CallLogQueryHandler(context2, context2.getContentResolver(), this, -1);
        fetchStatus();
    }

    /* access modifiers changed from: private */
    public void fetchStatus() {
        if (!this.isForeground) {
            this.statusInvalidated = true;
        } else {
            this.callLogQueryHandler.fetchVoicemailStatus();
        }
    }

    public ContentObserver getContentObserver() {
        return this.statusObserver;
    }

    public boolean onCallsFetched(Cursor cursor) {
        return false;
    }

    public void onDestroy() {
        TelephonyManager telephonyManager = (TelephonyManager) this.context.getSystemService(TelephonyManager.class);
        for (ServiceStateListener listen : this.listeners.values()) {
            telephonyManager.listen(listen, 0);
        }
    }

    public void onMissedCallsUnreadCountFetched(Cursor cursor) {
    }

    public void onPause() {
        this.isForeground = false;
        this.statusInvalidated = false;
    }

    public void onResume() {
        this.isForeground = true;
        if (this.statusInvalidated) {
            fetchStatus();
        }
    }

    public void onVoicemailStatusFetched(Cursor cursor) {
        ArrayList arrayList = new ArrayList();
        while (cursor.moveToNext()) {
            VoicemailStatus voicemailStatus = new VoicemailStatus(this.context, cursor);
            if (voicemailStatus.isActive(this.context)) {
                arrayList.add(voicemailStatus);
                Assert.isMainThread();
                if (!VoicemailComponent.get(this.context).getVoicemailClient().isVoicemailModuleEnabled()) {
                    LogUtil.m9i("VoicemailErrorManager.addServiceStateListener", "VVM module not enabled", new Object[0]);
                } else if (!voicemailStatus.sourcePackage.equals(this.context.getPackageName())) {
                    LogUtil.m9i("VoicemailErrorManager.addServiceStateListener", "non-dialer source", new Object[0]);
                } else {
                    TelephonyManager createForPhoneAccountHandle = ((TelephonyManager) this.context.getSystemService(TelephonyManager.class)).createForPhoneAccountHandle(voicemailStatus.getPhoneAccountHandle());
                    if (createForPhoneAccountHandle == null) {
                        LogUtil.m8e("VoicemailErrorManager.addServiceStateListener", "invalid PhoneAccountHandle", new Object[0]);
                    } else {
                        PhoneAccountHandle phoneAccountHandle = voicemailStatus.getPhoneAccountHandle();
                        if (!this.listeners.containsKey(phoneAccountHandle)) {
                            LogUtil.m9i("VoicemailErrorManager.addServiceStateListener", GeneratedOutlineSupport.outline6("adding listener for ", phoneAccountHandle), new Object[0]);
                            ServiceStateListener serviceStateListener = new ServiceStateListener((C03441) null);
                            createForPhoneAccountHandle.listen(serviceStateListener, 1);
                            this.listeners.put(phoneAccountHandle, serviceStateListener);
                        }
                    }
                }
            } else {
                LogUtil.m9i("VisualVoicemailCallLogFragment.shouldAutoSync", "inactive source ignored", new Object[0]);
            }
        }
        this.alertItem.updateStatus(arrayList, this);
    }

    public void onVoicemailUnreadCountFetched(Cursor cursor) {
    }

    public void refresh() {
        fetchStatus();
    }
}
