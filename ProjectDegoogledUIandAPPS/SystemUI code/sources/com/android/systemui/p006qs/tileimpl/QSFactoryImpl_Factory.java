package com.android.systemui.p006qs.tileimpl;

import com.android.systemui.p006qs.tiles.AODTile;
import com.android.systemui.p006qs.tiles.AdbOverNetworkTile;
import com.android.systemui.p006qs.tiles.AirplaneModeTile;
import com.android.systemui.p006qs.tiles.BatterySaverTile;
import com.android.systemui.p006qs.tiles.BluetoothTile;
import com.android.systemui.p006qs.tiles.CPUInfoTile;
import com.android.systemui.p006qs.tiles.CaffeineTile;
import com.android.systemui.p006qs.tiles.CastTile;
import com.android.systemui.p006qs.tiles.CellularTile;
import com.android.systemui.p006qs.tiles.ColorInversionTile;
import com.android.systemui.p006qs.tiles.CompassTile;
import com.android.systemui.p006qs.tiles.DataSaverTile;
import com.android.systemui.p006qs.tiles.DataSwitchTile;
import com.android.systemui.p006qs.tiles.DcDimmingTile;
import com.android.systemui.p006qs.tiles.DndTile;
import com.android.systemui.p006qs.tiles.FPSInfoTile;
import com.android.systemui.p006qs.tiles.FlashlightTile;
import com.android.systemui.p006qs.tiles.GamingModeTile;
import com.android.systemui.p006qs.tiles.HWKeysTile;
import com.android.systemui.p006qs.tiles.HeadsUpTile;
import com.android.systemui.p006qs.tiles.HotspotTile;
import com.android.systemui.p006qs.tiles.LiveDisplayTile;
import com.android.systemui.p006qs.tiles.LocationTile;
import com.android.systemui.p006qs.tiles.MusicTile;
import com.android.systemui.p006qs.tiles.NavBarTile;
import com.android.systemui.p006qs.tiles.NfcTile;
import com.android.systemui.p006qs.tiles.NightDisplayTile;
import com.android.systemui.p006qs.tiles.PowerShareTile;
import com.android.systemui.p006qs.tiles.ReadingModeTile;
import com.android.systemui.p006qs.tiles.RebootTile;
import com.android.systemui.p006qs.tiles.RotationLockTile;
import com.android.systemui.p006qs.tiles.ScreenRecordTile;
import com.android.systemui.p006qs.tiles.ScreenStabilizationTile;
import com.android.systemui.p006qs.tiles.ScreenshotTile;
import com.android.systemui.p006qs.tiles.SmartPixelsTile;
import com.android.systemui.p006qs.tiles.SoundSearchTile;
import com.android.systemui.p006qs.tiles.SoundTile;
import com.android.systemui.p006qs.tiles.SyncTile;
import com.android.systemui.p006qs.tiles.UiModeNightTile;
import com.android.systemui.p006qs.tiles.UsbTetherTile;
import com.android.systemui.p006qs.tiles.UserTile;
import com.android.systemui.p006qs.tiles.VpnTile;
import com.android.systemui.p006qs.tiles.WifiTile;
import com.android.systemui.p006qs.tiles.WorkModeTile;
import com.android.systemui.util.leak.GarbageMonitor;
import dagger.internal.Factory;
import javax.inject.Provider;

/* renamed from: com.android.systemui.qs.tileimpl.QSFactoryImpl_Factory */
public final class QSFactoryImpl_Factory implements Factory<QSFactoryImpl> {
    private final Provider<AdbOverNetworkTile> adbOverNetworkProvider;
    private final Provider<AirplaneModeTile> airplaneModeTileProvider;
    private final Provider<AODTile> aodTileProvider;
    private final Provider<BatterySaverTile> batterySaverTileProvider;
    private final Provider<BluetoothTile> bluetoothTileProvider;
    private final Provider<CaffeineTile> caffeineTileProvider;
    private final Provider<CastTile> castTileProvider;
    private final Provider<CellularTile> cellularTileProvider;
    private final Provider<ColorInversionTile> colorInversionTileProvider;
    private final Provider<CompassTile> compassTileProvider;
    private final Provider<CPUInfoTile> cpuInfoTileProvider;
    private final Provider<DataSaverTile> dataSaverTileProvider;
    private final Provider<DataSwitchTile> dataSwitchTileProvider;
    private final Provider<DcDimmingTile> dcDimTileProvider;
    private final Provider<DndTile> dndTileProvider;
    private final Provider<FlashlightTile> flashlightTileProvider;
    private final Provider<FPSInfoTile> fpsInfoTileProvider;
    private final Provider<GamingModeTile> gamingModeTileProvider;
    private final Provider<HWKeysTile> hWKeysTileProvider;
    private final Provider<HeadsUpTile> headsUpTileProvider;
    private final Provider<HotspotTile> hotspotTileProvider;
    private final Provider<LiveDisplayTile> liveDisplayTileProvider;
    private final Provider<LocationTile> locationTileProvider;
    private final Provider<GarbageMonitor.MemoryTile> memoryTileProvider;
    private final Provider<MusicTile> musicTileProvider;
    private final Provider<NavBarTile> navBarTileProvider;
    private final Provider<NfcTile> nfcTileProvider;
    private final Provider<NightDisplayTile> nightDisplayTileProvider;
    private final Provider<PowerShareTile> powerShareTileProvider;
    private final Provider<ReadingModeTile> readingModeTileProvider;
    private final Provider<RebootTile> rebootTileProvider;
    private final Provider<RotationLockTile> rotationLockTileProvider;
    private final Provider<ScreenRecordTile> screenRecordTileProvider;
    private final Provider<ScreenStabilizationTile> screenStabilizationTileProvider;
    private final Provider<ScreenshotTile> screenshotTileProvider;
    private final Provider<SmartPixelsTile> smartPixelsTileProvider;
    private final Provider<SoundSearchTile> soundSearchTileProvider;
    private final Provider<SoundTile> soundTileProvider;
    private final Provider<SyncTile> syncTileProvider;
    private final Provider<UiModeNightTile> uiModeNightTileProvider;
    private final Provider<UsbTetherTile> usbTetherTileProvider;
    private final Provider<UserTile> userTileProvider;
    private final Provider<VpnTile> vpnTileProvider;
    private final Provider<WifiTile> wifiTileProvider;
    private final Provider<WorkModeTile> workModeTileProvider;

    public QSFactoryImpl_Factory(Provider<WifiTile> provider, Provider<BluetoothTile> provider2, Provider<CellularTile> provider3, Provider<DndTile> provider4, Provider<ColorInversionTile> provider5, Provider<AirplaneModeTile> provider6, Provider<WorkModeTile> provider7, Provider<RotationLockTile> provider8, Provider<FlashlightTile> provider9, Provider<LocationTile> provider10, Provider<CastTile> provider11, Provider<HotspotTile> provider12, Provider<UserTile> provider13, Provider<BatterySaverTile> provider14, Provider<DataSaverTile> provider15, Provider<NightDisplayTile> provider16, Provider<NfcTile> provider17, Provider<GarbageMonitor.MemoryTile> provider18, Provider<UiModeNightTile> provider19, Provider<CaffeineTile> provider20, Provider<LiveDisplayTile> provider21, Provider<ReadingModeTile> provider22, Provider<HeadsUpTile> provider23, Provider<UsbTetherTile> provider24, Provider<SmartPixelsTile> provider25, Provider<CPUInfoTile> provider26, Provider<ScreenshotTile> provider27, Provider<HWKeysTile> provider28, Provider<DcDimmingTile> provider29, Provider<ScreenRecordTile> provider30, Provider<CompassTile> provider31, Provider<MusicTile> provider32, Provider<RebootTile> provider33, Provider<SoundSearchTile> provider34, Provider<GamingModeTile> provider35, Provider<DataSwitchTile> provider36, Provider<AdbOverNetworkTile> provider37, Provider<SyncTile> provider38, Provider<ScreenStabilizationTile> provider39, Provider<AODTile> provider40, Provider<SoundTile> provider41, Provider<FPSInfoTile> provider42, Provider<NavBarTile> provider43, Provider<VpnTile> provider44, Provider<PowerShareTile> provider45) {
        this.wifiTileProvider = provider;
        this.bluetoothTileProvider = provider2;
        this.cellularTileProvider = provider3;
        this.dndTileProvider = provider4;
        this.colorInversionTileProvider = provider5;
        this.airplaneModeTileProvider = provider6;
        this.workModeTileProvider = provider7;
        this.rotationLockTileProvider = provider8;
        this.flashlightTileProvider = provider9;
        this.locationTileProvider = provider10;
        this.castTileProvider = provider11;
        this.hotspotTileProvider = provider12;
        this.userTileProvider = provider13;
        this.batterySaverTileProvider = provider14;
        this.dataSaverTileProvider = provider15;
        this.nightDisplayTileProvider = provider16;
        this.nfcTileProvider = provider17;
        this.memoryTileProvider = provider18;
        this.uiModeNightTileProvider = provider19;
        this.caffeineTileProvider = provider20;
        this.liveDisplayTileProvider = provider21;
        this.readingModeTileProvider = provider22;
        this.headsUpTileProvider = provider23;
        this.usbTetherTileProvider = provider24;
        this.smartPixelsTileProvider = provider25;
        this.cpuInfoTileProvider = provider26;
        this.screenshotTileProvider = provider27;
        this.hWKeysTileProvider = provider28;
        this.dcDimTileProvider = provider29;
        this.screenRecordTileProvider = provider30;
        this.compassTileProvider = provider31;
        this.musicTileProvider = provider32;
        this.rebootTileProvider = provider33;
        this.soundSearchTileProvider = provider34;
        this.gamingModeTileProvider = provider35;
        this.dataSwitchTileProvider = provider36;
        this.adbOverNetworkProvider = provider37;
        this.syncTileProvider = provider38;
        this.screenStabilizationTileProvider = provider39;
        this.aodTileProvider = provider40;
        this.soundTileProvider = provider41;
        this.fpsInfoTileProvider = provider42;
        this.navBarTileProvider = provider43;
        this.vpnTileProvider = provider44;
        this.powerShareTileProvider = provider45;
    }

    public QSFactoryImpl get() {
        return provideInstance(this.wifiTileProvider, this.bluetoothTileProvider, this.cellularTileProvider, this.dndTileProvider, this.colorInversionTileProvider, this.airplaneModeTileProvider, this.workModeTileProvider, this.rotationLockTileProvider, this.flashlightTileProvider, this.locationTileProvider, this.castTileProvider, this.hotspotTileProvider, this.userTileProvider, this.batterySaverTileProvider, this.dataSaverTileProvider, this.nightDisplayTileProvider, this.nfcTileProvider, this.memoryTileProvider, this.uiModeNightTileProvider, this.caffeineTileProvider, this.liveDisplayTileProvider, this.readingModeTileProvider, this.headsUpTileProvider, this.usbTetherTileProvider, this.smartPixelsTileProvider, this.cpuInfoTileProvider, this.screenshotTileProvider, this.hWKeysTileProvider, this.dcDimTileProvider, this.screenRecordTileProvider, this.compassTileProvider, this.musicTileProvider, this.rebootTileProvider, this.soundSearchTileProvider, this.gamingModeTileProvider, this.dataSwitchTileProvider, this.adbOverNetworkProvider, this.syncTileProvider, this.screenStabilizationTileProvider, this.aodTileProvider, this.soundTileProvider, this.fpsInfoTileProvider, this.navBarTileProvider, this.vpnTileProvider, this.powerShareTileProvider);
    }

    public static QSFactoryImpl provideInstance(Provider<WifiTile> provider, Provider<BluetoothTile> provider2, Provider<CellularTile> provider3, Provider<DndTile> provider4, Provider<ColorInversionTile> provider5, Provider<AirplaneModeTile> provider6, Provider<WorkModeTile> provider7, Provider<RotationLockTile> provider8, Provider<FlashlightTile> provider9, Provider<LocationTile> provider10, Provider<CastTile> provider11, Provider<HotspotTile> provider12, Provider<UserTile> provider13, Provider<BatterySaverTile> provider14, Provider<DataSaverTile> provider15, Provider<NightDisplayTile> provider16, Provider<NfcTile> provider17, Provider<GarbageMonitor.MemoryTile> provider18, Provider<UiModeNightTile> provider19, Provider<CaffeineTile> provider20, Provider<LiveDisplayTile> provider21, Provider<ReadingModeTile> provider22, Provider<HeadsUpTile> provider23, Provider<UsbTetherTile> provider24, Provider<SmartPixelsTile> provider25, Provider<CPUInfoTile> provider26, Provider<ScreenshotTile> provider27, Provider<HWKeysTile> provider28, Provider<DcDimmingTile> provider29, Provider<ScreenRecordTile> provider30, Provider<CompassTile> provider31, Provider<MusicTile> provider32, Provider<RebootTile> provider33, Provider<SoundSearchTile> provider34, Provider<GamingModeTile> provider35, Provider<DataSwitchTile> provider36, Provider<AdbOverNetworkTile> provider37, Provider<SyncTile> provider38, Provider<ScreenStabilizationTile> provider39, Provider<AODTile> provider40, Provider<SoundTile> provider41, Provider<FPSInfoTile> provider42, Provider<NavBarTile> provider43, Provider<VpnTile> provider44, Provider<PowerShareTile> provider45) {
        return new QSFactoryImpl(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12, provider13, provider14, provider15, provider16, provider17, provider18, provider19, provider20, provider21, provider22, provider23, provider24, provider25, provider26, provider27, provider28, provider29, provider30, provider31, provider32, provider33, provider34, provider35, provider36, provider37, provider38, provider39, provider40, provider41, provider42, provider43, provider44, provider45);
    }

    public static QSFactoryImpl_Factory create(Provider<WifiTile> provider, Provider<BluetoothTile> provider2, Provider<CellularTile> provider3, Provider<DndTile> provider4, Provider<ColorInversionTile> provider5, Provider<AirplaneModeTile> provider6, Provider<WorkModeTile> provider7, Provider<RotationLockTile> provider8, Provider<FlashlightTile> provider9, Provider<LocationTile> provider10, Provider<CastTile> provider11, Provider<HotspotTile> provider12, Provider<UserTile> provider13, Provider<BatterySaverTile> provider14, Provider<DataSaverTile> provider15, Provider<NightDisplayTile> provider16, Provider<NfcTile> provider17, Provider<GarbageMonitor.MemoryTile> provider18, Provider<UiModeNightTile> provider19, Provider<CaffeineTile> provider20, Provider<LiveDisplayTile> provider21, Provider<ReadingModeTile> provider22, Provider<HeadsUpTile> provider23, Provider<UsbTetherTile> provider24, Provider<SmartPixelsTile> provider25, Provider<CPUInfoTile> provider26, Provider<ScreenshotTile> provider27, Provider<HWKeysTile> provider28, Provider<DcDimmingTile> provider29, Provider<ScreenRecordTile> provider30, Provider<CompassTile> provider31, Provider<MusicTile> provider32, Provider<RebootTile> provider33, Provider<SoundSearchTile> provider34, Provider<GamingModeTile> provider35, Provider<DataSwitchTile> provider36, Provider<AdbOverNetworkTile> provider37, Provider<SyncTile> provider38, Provider<ScreenStabilizationTile> provider39, Provider<AODTile> provider40, Provider<SoundTile> provider41, Provider<FPSInfoTile> provider42, Provider<NavBarTile> provider43, Provider<VpnTile> provider44, Provider<PowerShareTile> provider45) {
        return new QSFactoryImpl_Factory(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12, provider13, provider14, provider15, provider16, provider17, provider18, provider19, provider20, provider21, provider22, provider23, provider24, provider25, provider26, provider27, provider28, provider29, provider30, provider31, provider32, provider33, provider34, provider35, provider36, provider37, provider38, provider39, provider40, provider41, provider42, provider43, provider44, provider45);
    }
}
