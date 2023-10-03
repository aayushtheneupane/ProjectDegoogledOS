package com.android.settings.homepage.contextualcards.slices;

import com.android.settingslib.bluetooth.CachedBluetoothDevice;
import java.util.function.Predicate;

/* renamed from: com.android.settings.homepage.contextualcards.slices.-$$Lambda$BluetoothDevicesSlice$V6QlkQR9oKiRxn_TrNtToTfoHoA  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$BluetoothDevicesSlice$V6QlkQR9oKiRxn_TrNtToTfoHoA implements Predicate {
    public static final /* synthetic */ $$Lambda$BluetoothDevicesSlice$V6QlkQR9oKiRxn_TrNtToTfoHoA INSTANCE = new $$Lambda$BluetoothDevicesSlice$V6QlkQR9oKiRxn_TrNtToTfoHoA();

    private /* synthetic */ $$Lambda$BluetoothDevicesSlice$V6QlkQR9oKiRxn_TrNtToTfoHoA() {
    }

    public final boolean test(Object obj) {
        return ((CachedBluetoothDevice) obj).getDevice().isConnected();
    }
}
