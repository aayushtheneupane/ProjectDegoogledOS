package com.android.dialer.enrichedcall.stub;

import com.android.dialer.calldetails.CallDetailsEntries;
import com.android.dialer.common.Assert;
import com.android.dialer.enrichedcall.EnrichedCallCapabilities;
import com.android.dialer.enrichedcall.EnrichedCallManager;
import com.android.dialer.enrichedcall.Session;
import com.android.dialer.multimedia.MultimediaData;
import java.util.Collections;
import java.util.List;

public final class EnrichedCallManagerStub implements EnrichedCallManager {
    public void clearCachedData() {
    }

    public EnrichedCallManager.Filter createIncomingCallComposerFilter() {
        return $$Lambda$EnrichedCallManagerStub$QaqQ6Zuml6Y0OqVHvRhKh7hfdUA.INSTANCE;
    }

    public EnrichedCallManager.Filter createOutgoingCallComposerFilter() {
        return $$Lambda$EnrichedCallManagerStub$aWpBO8FbbCemShWZ2_QuPw1V7P0.INSTANCE;
    }

    public List<String> getAllSessionsForDisplay() {
        Assert.isMainThread();
        return Collections.emptyList();
    }

    public EnrichedCallCapabilities getCapabilities(String str) {
        return null;
    }

    public Session getSession(long j) {
        return null;
    }

    public Session getSession(String str, String str2, EnrichedCallManager.Filter filter) {
        return null;
    }

    public boolean hasStoredData() {
        Assert.isMainThread();
        return false;
    }

    public void registerCapabilitiesListener(EnrichedCallManager.CapabilitiesListener capabilitiesListener) {
    }

    public void registerHistoricalDataChangedListener(EnrichedCallManager.HistoricalDataChangedListener historicalDataChangedListener) {
    }

    public void registerStateChangedListener(EnrichedCallManager.StateChangedListener stateChangedListener) {
    }

    public void requestAllHistoricalData(String str, CallDetailsEntries callDetailsEntries) {
        Assert.isMainThread();
    }

    public void requestCapabilities(String str) {
    }

    public void sendCallComposerData(long j, MultimediaData multimediaData) {
    }

    public void sendPostCallNote(String str, String str2) {
    }

    public long startCallComposerSession(String str) {
        return -1;
    }

    public void unregisterCapabilitiesListener(EnrichedCallManager.CapabilitiesListener capabilitiesListener) {
    }

    public void unregisterHistoricalDataChangedListener(EnrichedCallManager.HistoricalDataChangedListener historicalDataChangedListener) {
    }

    public void unregisterStateChangedListener(EnrichedCallManager.StateChangedListener stateChangedListener) {
    }
}
