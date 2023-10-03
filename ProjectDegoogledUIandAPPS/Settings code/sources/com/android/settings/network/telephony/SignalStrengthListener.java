package com.android.settings.network.telephony;

import android.content.Context;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.util.ArraySet;
import com.google.common.collect.Sets;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class SignalStrengthListener {
    private TelephonyManager mBaseTelephonyManager;
    /* access modifiers changed from: private */
    public Callback mCallback;
    private Map<Integer, PhoneStateListener> mListeners = new TreeMap();

    public interface Callback {
        void onSignalStrengthChanged();
    }

    public SignalStrengthListener(Context context, Callback callback) {
        this.mBaseTelephonyManager = (TelephonyManager) context.getSystemService(TelephonyManager.class);
        this.mCallback = callback;
    }

    public void resume() {
        for (Integer intValue : this.mListeners.keySet()) {
            startListening(intValue.intValue());
        }
    }

    public void pause() {
        for (Integer intValue : this.mListeners.keySet()) {
            stopListening(intValue.intValue());
        }
    }

    public void updateSubscriptionIds(Set<Integer> set) {
        ArraySet arraySet = new ArraySet(this.mListeners.keySet());
        Iterator it = Sets.difference(arraySet, set).iterator();
        while (it.hasNext()) {
            int intValue = ((Integer) it.next()).intValue();
            stopListening(intValue);
            this.mListeners.remove(Integer.valueOf(intValue));
        }
        Iterator it2 = Sets.difference(set, arraySet).iterator();
        while (it2.hasNext()) {
            int intValue2 = ((Integer) it2.next()).intValue();
            this.mListeners.put(Integer.valueOf(intValue2), new PhoneStateListener() {
                public void onSignalStrengthsChanged(SignalStrength signalStrength) {
                    SignalStrengthListener.this.mCallback.onSignalStrengthChanged();
                }
            });
            startListening(intValue2);
        }
    }

    private void startListening(int i) {
        this.mBaseTelephonyManager.createForSubscriptionId(i).listen(this.mListeners.get(Integer.valueOf(i)), 256);
    }

    private void stopListening(int i) {
        this.mBaseTelephonyManager.createForSubscriptionId(i).listen(this.mListeners.get(Integer.valueOf(i)), 0);
    }
}
