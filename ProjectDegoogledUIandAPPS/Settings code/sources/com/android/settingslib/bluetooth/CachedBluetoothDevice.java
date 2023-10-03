package com.android.settingslib.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothUuid;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.ParcelUuid;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.EventLog;
import android.util.Log;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CachedBluetoothDevice implements Comparable<CachedBluetoothDevice> {
    private final Collection<Callback> mCallbacks = new CopyOnWriteArrayList();
    private long mConnectAttempted;
    private final Context mContext;
    BluetoothDevice mDevice;
    private long mHiSyncId;
    private boolean mIsActiveDeviceA2dp = false;
    private boolean mIsActiveDeviceHeadset = false;
    private boolean mIsActiveDeviceHearingAid = false;
    boolean mJustDiscovered;
    private final BluetoothAdapter mLocalAdapter;
    private boolean mLocalNapRoleConnected;
    private final Object mProfileLock = new Object();
    private final LocalBluetoothProfileManager mProfileManager;
    private final List<LocalBluetoothProfile> mProfiles = new ArrayList();
    private final List<LocalBluetoothProfile> mRemovedProfiles = new ArrayList();
    short mRssi;
    private CachedBluetoothDevice mSubDevice;
    public int mTwspBatteryLevel;
    public int mTwspBatteryState;

    public interface Callback {
        void onDeviceAttributesChanged();
    }

    private boolean isTwsBatteryAvailable(int i, int i2) {
        return i >= 0 && i2 >= 0;
    }

    CachedBluetoothDevice(Context context, LocalBluetoothProfileManager localBluetoothProfileManager, BluetoothDevice bluetoothDevice) {
        this.mContext = context;
        this.mLocalAdapter = BluetoothAdapter.getDefaultAdapter();
        this.mProfileManager = localBluetoothProfileManager;
        this.mDevice = bluetoothDevice;
        fillData();
        this.mHiSyncId = 0;
        this.mTwspBatteryState = -1;
        this.mTwspBatteryLevel = -1;
    }

    private BluetoothDevice getTwsPeerDevice() {
        if (this.mDevice.isTwsPlusDevice()) {
            return BluetoothAdapter.getDefaultAdapter().getRemoteDevice(this.mDevice.getTwsPlusPeerAddress());
        }
        return null;
    }

    private String describe(LocalBluetoothProfile localBluetoothProfile) {
        StringBuilder sb = new StringBuilder();
        sb.append("Address:");
        sb.append(this.mDevice);
        if (localBluetoothProfile != null) {
            sb.append(" Profile:");
            sb.append(localBluetoothProfile);
        }
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    public void onProfileStateChanged(LocalBluetoothProfile localBluetoothProfile, int i) {
        Log.d("CachedBluetoothDevice", "onProfileStateChanged: profile " + localBluetoothProfile + ", device=" + this.mDevice + ", newProfileState " + i);
        if (this.mLocalAdapter.getState() == 13) {
            Log.d("CachedBluetoothDevice", " BT Turninig Off...Profile conn state change ignored...");
            return;
        }
        synchronized (this.mProfileLock) {
            if (i == 2) {
                if (localBluetoothProfile instanceof MapProfile) {
                    localBluetoothProfile.setPreferred(this.mDevice, true);
                }
                if (!this.mProfiles.contains(localBluetoothProfile)) {
                    this.mRemovedProfiles.remove(localBluetoothProfile);
                    this.mProfiles.add(localBluetoothProfile);
                    if ((localBluetoothProfile instanceof PanProfile) && ((PanProfile) localBluetoothProfile).isLocalRoleNap(this.mDevice)) {
                        this.mLocalNapRoleConnected = true;
                    }
                }
            } else if ((localBluetoothProfile instanceof MapProfile) && i == 0) {
                localBluetoothProfile.setPreferred(this.mDevice, false);
            } else if (this.mLocalNapRoleConnected && (localBluetoothProfile instanceof PanProfile) && ((PanProfile) localBluetoothProfile).isLocalRoleNap(this.mDevice) && i == 0) {
                Log.d("CachedBluetoothDevice", "Removing PanProfile from device after NAP disconnect");
                this.mProfiles.remove(localBluetoothProfile);
                this.mRemovedProfiles.add(localBluetoothProfile);
                this.mLocalNapRoleConnected = false;
            } else if ((localBluetoothProfile instanceof HeadsetProfile) && i == 0) {
                this.mTwspBatteryState = -1;
                this.mTwspBatteryLevel = -1;
            }
        }
        fetchActiveDevices();
    }

    public void disconnect() {
        synchronized (this.mProfileLock) {
            for (LocalBluetoothProfile disconnect : this.mProfiles) {
                disconnect(disconnect);
            }
        }
        PbapServerProfile pbapProfile = this.mProfileManager.getPbapProfile();
        if (pbapProfile != null && isConnectedProfile(pbapProfile)) {
            pbapProfile.disconnect(this.mDevice);
        }
    }

    public void disconnect(LocalBluetoothProfile localBluetoothProfile) {
        if (localBluetoothProfile.disconnect(this.mDevice)) {
            Log.d("CachedBluetoothDevice", "Command sent successfully:DISCONNECT " + describe(localBluetoothProfile));
        }
    }

    public void connect(boolean z) {
        if (ensurePaired()) {
            this.mConnectAttempted = SystemClock.elapsedRealtime();
            Log.d("CachedBluetoothDevice", "connect: mConnectAttempted = " + this.mConnectAttempted);
            connectWithoutResettingTimer(z);
        }
    }

    public long getHiSyncId() {
        return this.mHiSyncId;
    }

    public void setHiSyncId(long j) {
        Log.d("CachedBluetoothDevice", "setHiSyncId: mDevice " + this.mDevice + ", id " + j);
        this.mHiSyncId = j;
    }

    /* access modifiers changed from: package-private */
    public void onBondingDockConnect() {
        connect(false);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0085, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void connectWithoutResettingTimer(boolean r6) {
        /*
            r5 = this;
            java.lang.Object r0 = r5.mProfileLock
            monitor-enter(r0)
            java.util.List<com.android.settingslib.bluetooth.LocalBluetoothProfile> r1 = r5.mProfiles     // Catch:{ all -> 0x0086 }
            boolean r1 = r1.isEmpty()     // Catch:{ all -> 0x0086 }
            if (r1 == 0) goto L_0x0025
            java.lang.String r6 = "CachedBluetoothDevice"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0086 }
            r1.<init>()     // Catch:{ all -> 0x0086 }
            java.lang.String r2 = "No profiles. Maybe we will connect later for device "
            r1.append(r2)     // Catch:{ all -> 0x0086 }
            android.bluetooth.BluetoothDevice r5 = r5.mDevice     // Catch:{ all -> 0x0086 }
            r1.append(r5)     // Catch:{ all -> 0x0086 }
            java.lang.String r5 = r1.toString()     // Catch:{ all -> 0x0086 }
            android.util.Log.d(r6, r5)     // Catch:{ all -> 0x0086 }
            monitor-exit(r0)     // Catch:{ all -> 0x0086 }
            return
        L_0x0025:
            android.bluetooth.BluetoothDevice r1 = r5.mDevice     // Catch:{ all -> 0x0086 }
            boolean r1 = r1.isBondingInitiatedLocally()     // Catch:{ all -> 0x0086 }
            r2 = 0
            if (r1 == 0) goto L_0x003a
            java.lang.String r1 = "CachedBluetoothDevice"
            java.lang.String r3 = "reset BondingInitiatedLocally flag"
            android.util.Log.w(r1, r3)     // Catch:{ all -> 0x0086 }
            android.bluetooth.BluetoothDevice r1 = r5.mDevice     // Catch:{ all -> 0x0086 }
            r1.setBondingInitiatedLocally(r2)     // Catch:{ all -> 0x0086 }
        L_0x003a:
            java.util.List<com.android.settingslib.bluetooth.LocalBluetoothProfile> r1 = r5.mProfiles     // Catch:{ all -> 0x0086 }
            java.util.Iterator r1 = r1.iterator()     // Catch:{ all -> 0x0086 }
        L_0x0040:
            boolean r3 = r1.hasNext()     // Catch:{ all -> 0x0086 }
            if (r3 == 0) goto L_0x0069
            java.lang.Object r3 = r1.next()     // Catch:{ all -> 0x0086 }
            com.android.settingslib.bluetooth.LocalBluetoothProfile r3 = (com.android.settingslib.bluetooth.LocalBluetoothProfile) r3     // Catch:{ all -> 0x0086 }
            if (r6 == 0) goto L_0x0055
            boolean r4 = r3.accessProfileEnabled()     // Catch:{ all -> 0x0086 }
            if (r4 == 0) goto L_0x0040
            goto L_0x005b
        L_0x0055:
            boolean r4 = r3.isAutoConnectable()     // Catch:{ all -> 0x0086 }
            if (r4 == 0) goto L_0x0040
        L_0x005b:
            android.bluetooth.BluetoothDevice r4 = r5.mDevice     // Catch:{ all -> 0x0086 }
            boolean r4 = r3.isPreferred(r4)     // Catch:{ all -> 0x0086 }
            if (r4 == 0) goto L_0x0040
            int r2 = r2 + 1
            r5.connectInt(r3)     // Catch:{ all -> 0x0086 }
            goto L_0x0040
        L_0x0069:
            java.lang.String r6 = "CachedBluetoothDevice"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0086 }
            r1.<init>()     // Catch:{ all -> 0x0086 }
            java.lang.String r3 = "Preferred profiles = "
            r1.append(r3)     // Catch:{ all -> 0x0086 }
            r1.append(r2)     // Catch:{ all -> 0x0086 }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x0086 }
            android.util.Log.d(r6, r1)     // Catch:{ all -> 0x0086 }
            if (r2 != 0) goto L_0x0084
            r5.connectAutoConnectableProfiles()     // Catch:{ all -> 0x0086 }
        L_0x0084:
            monitor-exit(r0)     // Catch:{ all -> 0x0086 }
            return
        L_0x0086:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0086 }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.settingslib.bluetooth.CachedBluetoothDevice.connectWithoutResettingTimer(boolean):void");
    }

    private void connectAutoConnectableProfiles() {
        if (ensurePaired()) {
            synchronized (this.mProfileLock) {
                for (LocalBluetoothProfile next : this.mProfiles) {
                    if (next.isAutoConnectable()) {
                        next.setPreferred(this.mDevice, true);
                        connectInt(next);
                    }
                }
            }
        }
    }

    public void connectProfile(LocalBluetoothProfile localBluetoothProfile) {
        this.mConnectAttempted = SystemClock.elapsedRealtime();
        connectInt(localBluetoothProfile);
        refresh();
    }

    /* access modifiers changed from: package-private */
    public synchronized void connectInt(LocalBluetoothProfile localBluetoothProfile) {
        if (ensurePaired()) {
            if (localBluetoothProfile.connect(this.mDevice)) {
                Log.d("CachedBluetoothDevice", "Command sent successfully:CONNECT " + describe(localBluetoothProfile));
                return;
            }
            Log.i("CachedBluetoothDevice", "Failed to connect " + localBluetoothProfile.toString() + " to " + getName());
        }
    }

    private boolean ensurePaired() {
        if (getBondState() != 10) {
            return true;
        }
        startPairing();
        return false;
    }

    public boolean startPairing() {
        if (this.mLocalAdapter.isDiscovering()) {
            this.mLocalAdapter.cancelDiscovery();
        }
        return this.mDevice.createBond();
    }

    public void unpair() {
        BluetoothDevice twsPeerDevice;
        int bondState = getBondState();
        if (bondState == 11) {
            this.mDevice.cancelBondProcess();
        }
        if (bondState != 10) {
            BluetoothDevice bluetoothDevice = this.mDevice;
            if (bluetoothDevice.isTwsPlusDevice() && (twsPeerDevice = getTwsPeerDevice()) != null && twsPeerDevice.removeBond()) {
                Log.d("CachedBluetoothDevice", "Command sent successfully:REMOVE_BOND " + twsPeerDevice.getName());
            }
            if (bluetoothDevice != null && bluetoothDevice.removeBond()) {
                Log.d("CachedBluetoothDevice", "Command sent successfully:REMOVE_BOND " + describe((LocalBluetoothProfile) null));
            }
        }
    }

    public int getProfileConnectionState(LocalBluetoothProfile localBluetoothProfile) {
        if (localBluetoothProfile != null) {
            return localBluetoothProfile.getConnectionStatus(this.mDevice);
        }
        return 0;
    }

    private void fillData() {
        updateProfiles();
        fetchActiveDevices();
        migratePhonebookPermissionChoice();
        migrateMessagePermissionChoice();
        dispatchAttributesChanged();
    }

    public BluetoothDevice getDevice() {
        return this.mDevice;
    }

    public String getAddress() {
        return this.mDevice.getAddress();
    }

    public String getName() {
        String aliasName = this.mDevice.getAliasName();
        return TextUtils.isEmpty(aliasName) ? getAddress() : aliasName;
    }

    public void setName(String str) {
        if (str != null && !TextUtils.equals(str, getName())) {
            this.mDevice.setAlias(str);
            dispatchAttributesChanged();
        }
    }

    public boolean setActive() {
        boolean z;
        A2dpProfile a2dpProfile = this.mProfileManager.getA2dpProfile();
        if (a2dpProfile == null || !isConnectedProfile(a2dpProfile) || !a2dpProfile.setActiveDevice(getDevice())) {
            z = false;
        } else {
            Log.i("CachedBluetoothDevice", "OnPreferenceClickListener: A2DP active device=" + this);
            z = true;
        }
        HeadsetProfile headsetProfile = this.mProfileManager.getHeadsetProfile();
        if (headsetProfile != null && isConnectedProfile(headsetProfile) && headsetProfile.setActiveDevice(getDevice())) {
            Log.i("CachedBluetoothDevice", "OnPreferenceClickListener: Headset active device=" + this);
            z = true;
        }
        HearingAidProfile hearingAidProfile = this.mProfileManager.getHearingAidProfile();
        if (hearingAidProfile == null || !isConnectedProfile(hearingAidProfile) || !hearingAidProfile.setActiveDevice(getDevice())) {
            return z;
        }
        Log.i("CachedBluetoothDevice", "OnPreferenceClickListener: Hearing Aid active device=" + this);
        return true;
    }

    /* access modifiers changed from: package-private */
    public void refreshName() {
        Log.d("CachedBluetoothDevice", "Device name: " + getName());
        dispatchAttributesChanged();
    }

    public boolean hasHumanReadableName() {
        return !TextUtils.isEmpty(this.mDevice.getAliasName());
    }

    public int getBatteryLevel() {
        return this.mDevice.getBatteryLevel();
    }

    /* access modifiers changed from: package-private */
    public void refresh() {
        dispatchAttributesChanged();
    }

    public void setJustDiscovered(boolean z) {
        if (this.mJustDiscovered != z) {
            this.mJustDiscovered = z;
            dispatchAttributesChanged();
        }
    }

    public int getBondState() {
        return this.mDevice.getBondState();
    }

    public void onActiveDeviceChanged(boolean z, int i) {
        boolean z2 = false;
        if (i == 1) {
            if (this.mIsActiveDeviceHeadset != z) {
                z2 = true;
            }
            this.mIsActiveDeviceHeadset = z;
        } else if (i == 2) {
            if (this.mIsActiveDeviceA2dp != z) {
                z2 = true;
            }
            this.mIsActiveDeviceA2dp = z;
        } else if (i != 21) {
            Log.w("CachedBluetoothDevice", "onActiveDeviceChanged: unknown profile " + i + " isActive " + z);
        } else {
            if (this.mIsActiveDeviceHearingAid != z) {
                z2 = true;
            }
            this.mIsActiveDeviceHearingAid = z;
        }
        if (z2) {
            dispatchAttributesChanged();
        }
    }

    /* access modifiers changed from: package-private */
    public void onAudioModeChanged() {
        dispatchAttributesChanged();
    }

    public boolean isActiveDevice(int i) {
        if (i == 1) {
            return this.mIsActiveDeviceHeadset;
        }
        if (i == 2) {
            return this.mIsActiveDeviceA2dp;
        }
        if (i == 21) {
            return this.mIsActiveDeviceHearingAid;
        }
        Log.w("CachedBluetoothDevice", "getActiveDevice: unknown profile " + i);
        return false;
    }

    /* access modifiers changed from: package-private */
    public void setRssi(short s) {
        if (this.mRssi != s) {
            this.mRssi = s;
            dispatchAttributesChanged();
        }
    }

    public boolean isConnected() {
        synchronized (this.mProfileLock) {
            for (LocalBluetoothProfile profileConnectionState : this.mProfiles) {
                if (getProfileConnectionState(profileConnectionState) == 2) {
                    return true;
                }
            }
            return false;
        }
    }

    public boolean isConnectedProfile(LocalBluetoothProfile localBluetoothProfile) {
        return getProfileConnectionState(localBluetoothProfile) == 2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0020, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x002c, code lost:
        return r3;
     */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0021 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:6:0x0010  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean isBusy() {
        /*
            r5 = this;
            java.lang.Object r0 = r5.mProfileLock
            monitor-enter(r0)
            java.util.List<com.android.settingslib.bluetooth.LocalBluetoothProfile> r1 = r5.mProfiles     // Catch:{ all -> 0x002d }
            java.util.Iterator r1 = r1.iterator()     // Catch:{ all -> 0x002d }
        L_0x0009:
            boolean r2 = r1.hasNext()     // Catch:{ all -> 0x002d }
            r3 = 1
            if (r2 == 0) goto L_0x0021
            java.lang.Object r2 = r1.next()     // Catch:{ all -> 0x002d }
            com.android.settingslib.bluetooth.LocalBluetoothProfile r2 = (com.android.settingslib.bluetooth.LocalBluetoothProfile) r2     // Catch:{ all -> 0x002d }
            int r2 = r5.getProfileConnectionState(r2)     // Catch:{ all -> 0x002d }
            if (r2 == r3) goto L_0x001f
            r4 = 3
            if (r2 != r4) goto L_0x0009
        L_0x001f:
            monitor-exit(r0)     // Catch:{ all -> 0x002d }
            return r3
        L_0x0021:
            int r5 = r5.getBondState()     // Catch:{ all -> 0x002d }
            r1 = 11
            if (r5 != r1) goto L_0x002a
            goto L_0x002b
        L_0x002a:
            r3 = 0
        L_0x002b:
            monitor-exit(r0)     // Catch:{ all -> 0x002d }
            return r3
        L_0x002d:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002d }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.settingslib.bluetooth.CachedBluetoothDevice.isBusy():boolean");
    }

    private boolean updateProfiles() {
        ParcelUuid[] uuids;
        ParcelUuid[] uuids2 = this.mDevice.getUuids();
        if (uuids2 == null || (uuids = this.mLocalAdapter.getUuids()) == null) {
            return false;
        }
        processPhonebookAccess();
        synchronized (this.mProfileLock) {
            this.mProfileManager.updateProfiles(uuids2, uuids, this.mProfiles, this.mRemovedProfiles, this.mLocalNapRoleConnected, this.mDevice);
        }
        Log.e("CachedBluetoothDevice", "updating profiles for " + this.mDevice.getAliasName() + ", " + this.mDevice);
        BluetoothClass bluetoothClass = this.mDevice.getBluetoothClass();
        if (bluetoothClass != null) {
            Log.v("CachedBluetoothDevice", "Class: " + bluetoothClass.toString());
        }
        Log.v("CachedBluetoothDevice", "UUID:");
        for (ParcelUuid parcelUuid : uuids2) {
            Log.v("CachedBluetoothDevice", "  " + parcelUuid);
        }
        return true;
    }

    private void fetchActiveDevices() {
        A2dpProfile a2dpProfile = this.mProfileManager.getA2dpProfile();
        if (a2dpProfile != null) {
            this.mIsActiveDeviceA2dp = this.mDevice.equals(a2dpProfile.getActiveDevice());
        }
        HeadsetProfile headsetProfile = this.mProfileManager.getHeadsetProfile();
        if (headsetProfile != null) {
            this.mIsActiveDeviceHeadset = this.mDevice.equals(headsetProfile.getActiveDevice());
        }
        HearingAidProfile hearingAidProfile = this.mProfileManager.getHearingAidProfile();
        if (hearingAidProfile != null) {
            this.mIsActiveDeviceHearingAid = hearingAidProfile.getActiveDevices().contains(this.mDevice);
        }
    }

    /* access modifiers changed from: package-private */
    public void onUuidChanged() {
        long j;
        updateProfiles();
        ParcelUuid[] uuids = this.mDevice.getUuids();
        if (BluetoothUuid.isUuidPresent(uuids, BluetoothUuid.Hogp)) {
            j = 30000;
        } else {
            j = BluetoothUuid.isUuidPresent(uuids, BluetoothUuid.HearingAid) ? 15000 : 5000;
        }
        Log.d("CachedBluetoothDevice", "onUuidChanged: Time since last connect=" + (SystemClock.elapsedRealtime() - this.mConnectAttempted));
        if (this.mConnectAttempted + j > SystemClock.elapsedRealtime()) {
            Log.d("CachedBluetoothDevice", "onUuidChanged: triggering connectWithoutResettingTimer");
            connectWithoutResettingTimer(false);
        }
        dispatchAttributesChanged();
    }

    /* access modifiers changed from: package-private */
    public void onBondingStateChanged(int i) {
        if (i == 10) {
            synchronized (this.mProfileLock) {
                this.mProfiles.clear();
            }
            this.mDevice.setPhonebookAccessPermission(0);
            this.mDevice.setMessageAccessPermission(0);
            this.mDevice.setSimAccessPermission(0);
        }
        refresh();
        if (i == 12) {
            boolean isBondingInitiatedLocally = this.mDevice.isBondingInitiatedLocally();
            Log.w("CachedBluetoothDevice", "mIsBondingInitiatedLocally" + isBondingInitiatedLocally);
            if (this.mDevice.isBluetoothDock()) {
                onBondingDockConnect();
            } else if (isBondingInitiatedLocally) {
                connect(false);
            }
        }
    }

    public BluetoothClass getBtClass() {
        return this.mDevice.getBluetoothClass();
    }

    public List<LocalBluetoothProfile> getProfiles() {
        return Collections.unmodifiableList(this.mProfiles);
    }

    public List<LocalBluetoothProfile> getConnectableProfiles() {
        ArrayList arrayList = new ArrayList();
        synchronized (this.mProfileLock) {
            for (LocalBluetoothProfile next : this.mProfiles) {
                if (next.accessProfileEnabled()) {
                    arrayList.add(next);
                }
            }
        }
        return arrayList;
    }

    public List<LocalBluetoothProfile> getRemovedProfiles() {
        return this.mRemovedProfiles;
    }

    public void registerCallback(Callback callback) {
        this.mCallbacks.add(callback);
    }

    public void unregisterCallback(Callback callback) {
        this.mCallbacks.remove(callback);
    }

    /* access modifiers changed from: package-private */
    public void dispatchAttributesChanged() {
        for (Callback onDeviceAttributesChanged : this.mCallbacks) {
            onDeviceAttributesChanged.onDeviceAttributesChanged();
        }
    }

    public String toString() {
        return this.mDevice.toString();
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof CachedBluetoothDevice)) {
            return false;
        }
        return this.mDevice.equals(((CachedBluetoothDevice) obj).mDevice);
    }

    public int hashCode() {
        return this.mDevice.getAddress().hashCode();
    }

    public int compareTo(CachedBluetoothDevice cachedBluetoothDevice) {
        int i = (cachedBluetoothDevice.isConnected() ? 1 : 0) - (isConnected() ? 1 : 0);
        if (i != 0) {
            return i;
        }
        int i2 = 1;
        int i3 = cachedBluetoothDevice.getBondState() == 12 ? 1 : 0;
        if (getBondState() != 12) {
            i2 = 0;
        }
        int i4 = i3 - i2;
        if (i4 != 0) {
            return i4;
        }
        int i5 = (cachedBluetoothDevice.mJustDiscovered ? 1 : 0) - (this.mJustDiscovered ? 1 : 0);
        if (i5 != 0) {
            return i5;
        }
        int i6 = cachedBluetoothDevice.mRssi - this.mRssi;
        if (i6 != 0) {
            return i6;
        }
        return getName().compareTo(cachedBluetoothDevice.getName());
    }

    private void migratePhonebookPermissionChoice() {
        SharedPreferences sharedPreferences = this.mContext.getSharedPreferences("bluetooth_phonebook_permission", 0);
        if (sharedPreferences.contains(this.mDevice.getAddress())) {
            if (this.mDevice.getPhonebookAccessPermission() == 0) {
                int i = sharedPreferences.getInt(this.mDevice.getAddress(), 0);
                if (i == 1) {
                    this.mDevice.setPhonebookAccessPermission(1);
                } else if (i == 2) {
                    this.mDevice.setPhonebookAccessPermission(2);
                }
            }
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.remove(this.mDevice.getAddress());
            edit.commit();
        }
    }

    private void migrateMessagePermissionChoice() {
        SharedPreferences sharedPreferences = this.mContext.getSharedPreferences("bluetooth_message_permission", 0);
        if (sharedPreferences.contains(this.mDevice.getAddress())) {
            if (this.mDevice.getMessageAccessPermission() == 0) {
                int i = sharedPreferences.getInt(this.mDevice.getAddress(), 0);
                if (i == 1) {
                    this.mDevice.setMessageAccessPermission(1);
                } else if (i == 2) {
                    this.mDevice.setMessageAccessPermission(2);
                }
            }
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.remove(this.mDevice.getAddress());
            edit.commit();
        }
    }

    private void processPhonebookAccess() {
        if (this.mDevice.getBondState() == 12 && BluetoothUuid.containsAnyUuid(this.mDevice.getUuids(), PbapServerProfile.PBAB_CLIENT_UUIDS) && this.mDevice.getPhonebookAccessPermission() == 0) {
            if (this.mDevice.getBluetoothClass().getDeviceClass() == 1032 || this.mDevice.getBluetoothClass().getDeviceClass() == 1028) {
                EventLog.writeEvent(1397638484, new Object[]{"138529441", -1, ""});
            }
            this.mDevice.setPhonebookAccessPermission(2);
        }
    }

    public String getConnectionSummary() {
        return getConnectionSummary(false);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:35:0x005d, code lost:
        r8 = -1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0065, code lost:
        if (r13.mDevice.isTwsPlusDevice() == false) goto L_0x009d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0067, code lost:
        r0 = r13.mTwspBatteryState;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0069, code lost:
        if (r0 == -1) goto L_0x009d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x006d, code lost:
        if (r13.mTwspBatteryLevel == -1) goto L_0x009d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x006f, code lost:
        if (r0 != 1) goto L_0x0074;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0071, code lost:
        r0 = "Charging, ";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0074, code lost:
        r0 = "Discharging, ";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0076, code lost:
        r0 = "TWSP: ".concat(r0).concat(com.android.settingslib.Utils.formatPercentage(r13.mTwspBatteryLevel));
        android.util.Log.i("CachedBluetoothDevice", "UI string" + r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x009d, code lost:
        r0 = getBatteryLevel();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00a1, code lost:
        if (r0 == -1) goto L_0x00a8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00a3, code lost:
        r0 = com.android.settingslib.Utils.formatPercentage(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00a8, code lost:
        r0 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00a9, code lost:
        r10 = com.android.settingslib.R$string.bluetooth_pairing;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00ad, code lost:
        if (r4 == false) goto L_0x0106;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00b6, code lost:
        if (com.android.settingslib.bluetooth.BluetoothUtils.getBooleanMetaData(r13.mDevice, 6) == false) goto L_0x00c7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00b8, code lost:
        r8 = com.android.settingslib.bluetooth.BluetoothUtils.getIntMetaData(r13.mDevice, 10);
        r4 = com.android.settingslib.bluetooth.BluetoothUtils.getIntMetaData(r13.mDevice, 11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00c7, code lost:
        r4 = -1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00cc, code lost:
        if (isTwsBatteryAvailable(r8, r4) == false) goto L_0x00d1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x00ce, code lost:
        r10 = com.android.settingslib.R$string.bluetooth_battery_level_untethered;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x00d1, code lost:
        if (r0 == null) goto L_0x00d5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x00d3, code lost:
        r10 = com.android.settingslib.R$string.bluetooth_battery_level;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x00d5, code lost:
        if (r5 != false) goto L_0x00db;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x00d7, code lost:
        if (r6 != false) goto L_0x00db;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x00d9, code lost:
        if (r7 == false) goto L_0x0107;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x00db, code lost:
        r5 = com.android.settingslib.Utils.isAudioModeOngoingCall(r13.mContext);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x00e3, code lost:
        if (r13.mIsActiveDeviceHearingAid != false) goto L_0x00f1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x00e7, code lost:
        if (r13.mIsActiveDeviceHeadset == false) goto L_0x00eb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x00e9, code lost:
        if (r5 != false) goto L_0x00f1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x00ed, code lost:
        if (r13.mIsActiveDeviceA2dp == false) goto L_0x0107;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x00ef, code lost:
        if (r5 != false) goto L_0x0107;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x00f5, code lost:
        if (isTwsBatteryAvailable(r8, r4) == false) goto L_0x00fc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x00f7, code lost:
        if (r14 != false) goto L_0x00fc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x00f9, code lost:
        r10 = com.android.settingslib.R$string.bluetooth_active_battery_level_untethered;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x00fc, code lost:
        if (r0 == null) goto L_0x0103;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x00fe, code lost:
        if (r14 != false) goto L_0x0103;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x0100, code lost:
        r10 = com.android.settingslib.R$string.bluetooth_active_battery_level;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x0103, code lost:
        r10 = com.android.settingslib.R$string.bluetooth_active_no_battery_level;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x0106, code lost:
        r4 = -1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x0109, code lost:
        if (r10 != com.android.settingslib.R$string.bluetooth_pairing) goto L_0x0113;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x010f, code lost:
        if (getBondState() != 11) goto L_0x0112;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x0112, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x0117, code lost:
        if (isTwsBatteryAvailable(r8, r4) == false) goto L_0x012e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x012d, code lost:
        return r13.mContext.getString(r10, new java.lang.Object[]{com.android.settingslib.Utils.formatPercentage(r8), com.android.settingslib.Utils.formatPercentage(r4)});
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x0138, code lost:
        return r13.mContext.getString(r10, new java.lang.Object[]{r0});
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getConnectionSummary(boolean r14) {
        /*
            r13 = this;
            java.lang.Object r0 = r13.mProfileLock
            monitor-enter(r0)
            java.util.List r1 = r13.getProfiles()     // Catch:{ all -> 0x0139 }
            java.util.Iterator r1 = r1.iterator()     // Catch:{ all -> 0x0139 }
            r2 = 0
            r3 = 1
            r4 = r2
            r5 = r3
            r6 = r5
            r7 = r6
        L_0x0011:
            boolean r8 = r1.hasNext()     // Catch:{ all -> 0x0139 }
            r9 = 2
            if (r8 == 0) goto L_0x005c
            java.lang.Object r8 = r1.next()     // Catch:{ all -> 0x0139 }
            com.android.settingslib.bluetooth.LocalBluetoothProfile r8 = (com.android.settingslib.bluetooth.LocalBluetoothProfile) r8     // Catch:{ all -> 0x0139 }
            int r10 = r13.getProfileConnectionState(r8)     // Catch:{ all -> 0x0139 }
            if (r10 == 0) goto L_0x003a
            if (r10 == r3) goto L_0x002e
            if (r10 == r9) goto L_0x002c
            r8 = 3
            if (r10 == r8) goto L_0x002e
            goto L_0x0011
        L_0x002c:
            r4 = r3
            goto L_0x0011
        L_0x002e:
            android.content.Context r13 = r13.mContext     // Catch:{ all -> 0x0139 }
            int r14 = com.android.settingslib.bluetooth.BluetoothUtils.getConnectionStateSummary(r10)     // Catch:{ all -> 0x0139 }
            java.lang.String r13 = r13.getString(r14)     // Catch:{ all -> 0x0139 }
            monitor-exit(r0)     // Catch:{ all -> 0x0139 }
            return r13
        L_0x003a:
            boolean r9 = r8.isProfileReady()     // Catch:{ all -> 0x0139 }
            if (r9 == 0) goto L_0x0011
            boolean r9 = r8 instanceof com.android.settingslib.bluetooth.A2dpProfile     // Catch:{ all -> 0x0139 }
            if (r9 != 0) goto L_0x005a
            boolean r9 = r8 instanceof com.android.settingslib.bluetooth.A2dpSinkProfile     // Catch:{ all -> 0x0139 }
            if (r9 == 0) goto L_0x0049
            goto L_0x005a
        L_0x0049:
            boolean r9 = r8 instanceof com.android.settingslib.bluetooth.HeadsetProfile     // Catch:{ all -> 0x0139 }
            if (r9 != 0) goto L_0x0058
            boolean r9 = r8 instanceof com.android.settingslib.bluetooth.HfpClientProfile     // Catch:{ all -> 0x0139 }
            if (r9 == 0) goto L_0x0052
            goto L_0x0058
        L_0x0052:
            boolean r8 = r8 instanceof com.android.settingslib.bluetooth.HearingAidProfile     // Catch:{ all -> 0x0139 }
            if (r8 == 0) goto L_0x0011
            r7 = r2
            goto L_0x0011
        L_0x0058:
            r6 = r2
            goto L_0x0011
        L_0x005a:
            r5 = r2
            goto L_0x0011
        L_0x005c:
            monitor-exit(r0)     // Catch:{ all -> 0x0139 }
            android.bluetooth.BluetoothDevice r0 = r13.mDevice
            boolean r0 = r0.isTwsPlusDevice()
            r1 = 0
            r8 = -1
            if (r0 == 0) goto L_0x009d
            int r0 = r13.mTwspBatteryState
            if (r0 == r8) goto L_0x009d
            int r10 = r13.mTwspBatteryLevel
            if (r10 == r8) goto L_0x009d
            if (r0 != r3) goto L_0x0074
            java.lang.String r0 = "Charging, "
            goto L_0x0076
        L_0x0074:
            java.lang.String r0 = "Discharging, "
        L_0x0076:
            java.lang.String r10 = "TWSP: "
            java.lang.String r0 = r10.concat(r0)
            int r10 = r13.mTwspBatteryLevel
            java.lang.String r10 = com.android.settingslib.Utils.formatPercentage((int) r10)
            java.lang.String r0 = r0.concat(r10)
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r11 = "UI string"
            r10.append(r11)
            r10.append(r0)
            java.lang.String r10 = r10.toString()
            java.lang.String r11 = "CachedBluetoothDevice"
            android.util.Log.i(r11, r10)
            goto L_0x00a9
        L_0x009d:
            int r0 = r13.getBatteryLevel()
            if (r0 == r8) goto L_0x00a8
            java.lang.String r0 = com.android.settingslib.Utils.formatPercentage((int) r0)
            goto L_0x00a9
        L_0x00a8:
            r0 = r1
        L_0x00a9:
            int r10 = com.android.settingslib.R$string.bluetooth_pairing
            r11 = 11
            if (r4 == 0) goto L_0x0106
            android.bluetooth.BluetoothDevice r4 = r13.mDevice
            r12 = 6
            boolean r4 = com.android.settingslib.bluetooth.BluetoothUtils.getBooleanMetaData(r4, r12)
            if (r4 == 0) goto L_0x00c7
            android.bluetooth.BluetoothDevice r4 = r13.mDevice
            r8 = 10
            int r8 = com.android.settingslib.bluetooth.BluetoothUtils.getIntMetaData(r4, r8)
            android.bluetooth.BluetoothDevice r4 = r13.mDevice
            int r4 = com.android.settingslib.bluetooth.BluetoothUtils.getIntMetaData(r4, r11)
            goto L_0x00c8
        L_0x00c7:
            r4 = r8
        L_0x00c8:
            boolean r12 = r13.isTwsBatteryAvailable(r8, r4)
            if (r12 == 0) goto L_0x00d1
            int r10 = com.android.settingslib.R$string.bluetooth_battery_level_untethered
            goto L_0x00d5
        L_0x00d1:
            if (r0 == 0) goto L_0x00d5
            int r10 = com.android.settingslib.R$string.bluetooth_battery_level
        L_0x00d5:
            if (r5 != 0) goto L_0x00db
            if (r6 != 0) goto L_0x00db
            if (r7 == 0) goto L_0x0107
        L_0x00db:
            android.content.Context r5 = r13.mContext
            boolean r5 = com.android.settingslib.Utils.isAudioModeOngoingCall(r5)
            boolean r6 = r13.mIsActiveDeviceHearingAid
            if (r6 != 0) goto L_0x00f1
            boolean r6 = r13.mIsActiveDeviceHeadset
            if (r6 == 0) goto L_0x00eb
            if (r5 != 0) goto L_0x00f1
        L_0x00eb:
            boolean r6 = r13.mIsActiveDeviceA2dp
            if (r6 == 0) goto L_0x0107
            if (r5 != 0) goto L_0x0107
        L_0x00f1:
            boolean r5 = r13.isTwsBatteryAvailable(r8, r4)
            if (r5 == 0) goto L_0x00fc
            if (r14 != 0) goto L_0x00fc
            int r10 = com.android.settingslib.R$string.bluetooth_active_battery_level_untethered
            goto L_0x0107
        L_0x00fc:
            if (r0 == 0) goto L_0x0103
            if (r14 != 0) goto L_0x0103
            int r10 = com.android.settingslib.R$string.bluetooth_active_battery_level
            goto L_0x0107
        L_0x0103:
            int r10 = com.android.settingslib.R$string.bluetooth_active_no_battery_level
            goto L_0x0107
        L_0x0106:
            r4 = r8
        L_0x0107:
            int r14 = com.android.settingslib.R$string.bluetooth_pairing
            if (r10 != r14) goto L_0x0113
            int r14 = r13.getBondState()
            if (r14 != r11) goto L_0x0112
            goto L_0x0113
        L_0x0112:
            return r1
        L_0x0113:
            boolean r14 = r13.isTwsBatteryAvailable(r8, r4)
            if (r14 == 0) goto L_0x012e
            android.content.Context r13 = r13.mContext
            java.lang.Object[] r14 = new java.lang.Object[r9]
            java.lang.String r0 = com.android.settingslib.Utils.formatPercentage((int) r8)
            r14[r2] = r0
            java.lang.String r0 = com.android.settingslib.Utils.formatPercentage((int) r4)
            r14[r3] = r0
            java.lang.String r13 = r13.getString(r10, r14)
            return r13
        L_0x012e:
            android.content.Context r13 = r13.mContext
            java.lang.Object[] r14 = new java.lang.Object[r3]
            r14[r2] = r0
            java.lang.String r13 = r13.getString(r10, r14)
            return r13
        L_0x0139:
            r13 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0139 }
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.settingslib.bluetooth.CachedBluetoothDevice.getConnectionSummary(boolean):java.lang.String");
    }

    public boolean isConnectedA2dpDevice() {
        A2dpProfile a2dpProfile = this.mProfileManager.getA2dpProfile();
        return a2dpProfile != null && a2dpProfile.getConnectionStatus(this.mDevice) == 2;
    }

    public boolean isConnectedHfpDevice() {
        HeadsetProfile headsetProfile = this.mProfileManager.getHeadsetProfile();
        return headsetProfile != null && headsetProfile.getConnectionStatus(this.mDevice) == 2;
    }

    public boolean isConnectedHearingAidDevice() {
        HearingAidProfile hearingAidProfile = this.mProfileManager.getHearingAidProfile();
        return hearingAidProfile != null && hearingAidProfile.getConnectionStatus(this.mDevice) == 2;
    }

    public CachedBluetoothDevice getSubDevice() {
        return this.mSubDevice;
    }

    public void setSubDevice(CachedBluetoothDevice cachedBluetoothDevice) {
        this.mSubDevice = cachedBluetoothDevice;
    }

    public void switchSubDeviceContent() {
        BluetoothDevice bluetoothDevice = this.mDevice;
        short s = this.mRssi;
        boolean z = this.mJustDiscovered;
        CachedBluetoothDevice cachedBluetoothDevice = this.mSubDevice;
        this.mDevice = cachedBluetoothDevice.mDevice;
        this.mRssi = cachedBluetoothDevice.mRssi;
        this.mJustDiscovered = cachedBluetoothDevice.mJustDiscovered;
        cachedBluetoothDevice.mDevice = bluetoothDevice;
        cachedBluetoothDevice.mRssi = s;
        cachedBluetoothDevice.mJustDiscovered = z;
        fetchActiveDevices();
    }
}
