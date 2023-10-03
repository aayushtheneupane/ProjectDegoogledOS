package com.android.systemui.p006qs.tileimpl;

import android.view.ContextThemeWrapper;
import com.android.systemui.C1785R$style;
import com.android.systemui.p006qs.QSTileHost;
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
import com.android.systemui.plugins.p005qs.QSFactory;
import com.android.systemui.plugins.p005qs.QSIconView;
import com.android.systemui.plugins.p005qs.QSTile;
import com.android.systemui.plugins.p005qs.QSTileView;
import com.android.systemui.util.leak.GarbageMonitor;
import javax.inject.Provider;

/* renamed from: com.android.systemui.qs.tileimpl.QSFactoryImpl */
public class QSFactoryImpl implements QSFactory {
    private final Provider<AODTile> mAODTileProvider;
    private final Provider<AdbOverNetworkTile> mAdbOverNetworkProvider;
    private final Provider<AirplaneModeTile> mAirplaneModeTileProvider;
    private final Provider<BatterySaverTile> mBatterySaverTileProvider;
    private final Provider<BluetoothTile> mBluetoothTileProvider;
    private final Provider<CPUInfoTile> mCPUInfoTileProvider;
    private final Provider<CaffeineTile> mCaffeineTileProvider;
    private final Provider<CastTile> mCastTileProvider;
    private final Provider<CellularTile> mCellularTileProvider;
    private final Provider<ColorInversionTile> mColorInversionTileProvider;
    private final Provider<CompassTile> mCompassTileProvider;
    private final Provider<DataSaverTile> mDataSaverTileProvider;
    private final Provider<DataSwitchTile> mDataSwitchTileProvider;
    private final Provider<DcDimmingTile> mDcDimmingTileProvider;
    private final Provider<DndTile> mDndTileProvider;
    private final Provider<FPSInfoTile> mFPSInfoTileProvider;
    private final Provider<FlashlightTile> mFlashlightTileProvider;
    private final Provider<GamingModeTile> mGamingModeTileProvider;
    private final Provider<HWKeysTile> mHWKeysTileProvider;
    private final Provider<HeadsUpTile> mHeadsUpTileProvider;
    private QSTileHost mHost;
    private final Provider<HotspotTile> mHotspotTileProvider;
    private final Provider<LiveDisplayTile> mLiveDisplayTileProvider;
    private final Provider<LocationTile> mLocationTileProvider;
    private final Provider<GarbageMonitor.MemoryTile> mMemoryTileProvider;
    private final Provider<MusicTile> mMusicTileProvider;
    private final Provider<NavBarTile> mNavBarTileProvider;
    private final Provider<NfcTile> mNfcTileProvider;
    private final Provider<NightDisplayTile> mNightDisplayTileProvider;
    private final Provider<PowerShareTile> mPowerShareTileProvider;
    private final Provider<ReadingModeTile> mReadingModeTileProvider;
    private final Provider<RebootTile> mRebootTileProvider;
    private final Provider<RotationLockTile> mRotationLockTileProvider;
    private final Provider<ScreenRecordTile> mScreenRecordTileProvider;
    private final Provider<ScreenStabilizationTile> mScreenStabilizationTileProvider;
    private final Provider<ScreenshotTile> mScreenshotTileProvider;
    private final Provider<SmartPixelsTile> mSmartPixelsTileProvider;
    private final Provider<SoundSearchTile> mSoundSearchTileProvider;
    private final Provider<SoundTile> mSoundTileProvider;
    private final Provider<SyncTile> mSyncTileProvider;
    private final Provider<UiModeNightTile> mUiModeNightTileProvider;
    private final Provider<UsbTetherTile> mUsbTetherTileProvider;
    private final Provider<UserTile> mUserTileProvider;
    private final Provider<VpnTile> mVpnTileProvider;
    private final Provider<WifiTile> mWifiTileProvider;
    private final Provider<WorkModeTile> mWorkModeTileProvider;

    public QSFactoryImpl(Provider<WifiTile> provider, Provider<BluetoothTile> provider2, Provider<CellularTile> provider3, Provider<DndTile> provider4, Provider<ColorInversionTile> provider5, Provider<AirplaneModeTile> provider6, Provider<WorkModeTile> provider7, Provider<RotationLockTile> provider8, Provider<FlashlightTile> provider9, Provider<LocationTile> provider10, Provider<CastTile> provider11, Provider<HotspotTile> provider12, Provider<UserTile> provider13, Provider<BatterySaverTile> provider14, Provider<DataSaverTile> provider15, Provider<NightDisplayTile> provider16, Provider<NfcTile> provider17, Provider<GarbageMonitor.MemoryTile> provider18, Provider<UiModeNightTile> provider19, Provider<CaffeineTile> provider20, Provider<LiveDisplayTile> provider21, Provider<ReadingModeTile> provider22, Provider<HeadsUpTile> provider23, Provider<UsbTetherTile> provider24, Provider<SmartPixelsTile> provider25, Provider<CPUInfoTile> provider26, Provider<ScreenshotTile> provider27, Provider<HWKeysTile> provider28, Provider<DcDimmingTile> provider29, Provider<ScreenRecordTile> provider30, Provider<CompassTile> provider31, Provider<MusicTile> provider32, Provider<RebootTile> provider33, Provider<SoundSearchTile> provider34, Provider<GamingModeTile> provider35, Provider<DataSwitchTile> provider36, Provider<AdbOverNetworkTile> provider37, Provider<SyncTile> provider38, Provider<ScreenStabilizationTile> provider39, Provider<AODTile> provider40, Provider<SoundTile> provider41, Provider<FPSInfoTile> provider42, Provider<NavBarTile> provider43, Provider<VpnTile> provider44, Provider<PowerShareTile> provider45) {
        this.mWifiTileProvider = provider;
        this.mBluetoothTileProvider = provider2;
        this.mCellularTileProvider = provider3;
        this.mDndTileProvider = provider4;
        this.mColorInversionTileProvider = provider5;
        this.mAirplaneModeTileProvider = provider6;
        this.mWorkModeTileProvider = provider7;
        this.mRotationLockTileProvider = provider8;
        this.mFlashlightTileProvider = provider9;
        this.mLocationTileProvider = provider10;
        this.mCastTileProvider = provider11;
        this.mHotspotTileProvider = provider12;
        this.mUserTileProvider = provider13;
        this.mBatterySaverTileProvider = provider14;
        this.mDataSaverTileProvider = provider15;
        this.mNightDisplayTileProvider = provider16;
        this.mNfcTileProvider = provider17;
        this.mMemoryTileProvider = provider18;
        this.mUiModeNightTileProvider = provider19;
        this.mCaffeineTileProvider = provider20;
        this.mLiveDisplayTileProvider = provider21;
        this.mReadingModeTileProvider = provider22;
        this.mHeadsUpTileProvider = provider23;
        this.mUsbTetherTileProvider = provider24;
        this.mSmartPixelsTileProvider = provider25;
        this.mCPUInfoTileProvider = provider26;
        this.mScreenshotTileProvider = provider27;
        this.mHWKeysTileProvider = provider28;
        this.mScreenRecordTileProvider = provider30;
        this.mCompassTileProvider = provider31;
        this.mMusicTileProvider = provider32;
        this.mRebootTileProvider = provider33;
        this.mSoundSearchTileProvider = provider34;
        this.mGamingModeTileProvider = provider35;
        this.mDataSwitchTileProvider = provider36;
        this.mAdbOverNetworkProvider = provider37;
        this.mSyncTileProvider = provider38;
        this.mScreenStabilizationTileProvider = provider39;
        this.mAODTileProvider = provider40;
        this.mSoundTileProvider = provider41;
        this.mFPSInfoTileProvider = provider42;
        this.mNavBarTileProvider = provider43;
        this.mVpnTileProvider = provider44;
        this.mDcDimmingTileProvider = provider29;
        this.mPowerShareTileProvider = provider45;
    }

    public void setHost(QSTileHost qSTileHost) {
        this.mHost = qSTileHost;
    }

    public QSTile createTile(String str) {
        QSTileImpl createTileInternal = createTileInternal(str);
        if (createTileInternal != null) {
            createTileInternal.handleStale();
        }
        return createTileInternal;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.android.systemui.p006qs.tileimpl.QSTileImpl createTileInternal(java.lang.String r2) {
        /*
            r1 = this;
            int r0 = r2.hashCode()
            switch(r0) {
                case -2099331234: goto L_0x01fd;
                case -2016941037: goto L_0x01f3;
                case -1906414349: goto L_0x01e8;
                case -1812239730: goto L_0x01dd;
                case -1467619465: goto L_0x01d2;
                case -1319150730: goto L_0x01c7;
                case -1291458275: goto L_0x01bc;
                case -1253231569: goto L_0x01b1;
                case -1204348029: goto L_0x01a6;
                case -1183073498: goto L_0x019a;
                case -1180580266: goto L_0x018e;
                case -1114859577: goto L_0x0182;
                case -1052566512: goto L_0x0176;
                case -934938715: goto L_0x016a;
                case -805491779: goto L_0x015e;
                case -677011630: goto L_0x0153;
                case -647998185: goto L_0x0147;
                case -416447130: goto L_0x013b;
                case -349438983: goto L_0x012f;
                case -331239923: goto L_0x0123;
                case -40300674: goto L_0x0118;
                case 3154: goto L_0x010d;
                case 96758: goto L_0x0101;
                case 99610: goto L_0x00f6;
                case 108971: goto L_0x00ea;
                case 116980: goto L_0x00de;
                case 3046207: goto L_0x00d2;
                case 3049826: goto L_0x00c7;
                case 3075958: goto L_0x00bb;
                case 3545755: goto L_0x00af;
                case 3599307: goto L_0x00a3;
                case 3649301: goto L_0x0098;
                case 3655441: goto L_0x008d;
                case 104263205: goto L_0x0081;
                case 104817688: goto L_0x0075;
                case 109211285: goto L_0x0069;
                case 109627663: goto L_0x005d;
                case 329701814: goto L_0x0051;
                case 459055610: goto L_0x0045;
                case 950484242: goto L_0x0039;
                case 986305110: goto L_0x002d;
                case 1099603663: goto L_0x0021;
                case 1901043637: goto L_0x0015;
                case 1906931501: goto L_0x0009;
                default: goto L_0x0007;
            }
        L_0x0007:
            goto L_0x0208
        L_0x0009:
            java.lang.String r0 = "usb_tether"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x0208
            r0 = 22
            goto L_0x0209
        L_0x0015:
            java.lang.String r0 = "location"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x0208
            r0 = 9
            goto L_0x0209
        L_0x0021:
            java.lang.String r0 = "hotspot"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x0208
            r0 = 11
            goto L_0x0209
        L_0x002d:
            java.lang.String r0 = "cpuinfo"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x0208
            r0 = 24
            goto L_0x0209
        L_0x0039:
            java.lang.String r0 = "compass"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x0208
            r0 = 28
            goto L_0x0209
        L_0x0045:
            java.lang.String r0 = "powershare"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x0208
            r0 = 43
            goto L_0x0209
        L_0x0051:
            java.lang.String r0 = "smartpixels"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x0208
            r0 = 23
            goto L_0x0209
        L_0x005d:
            java.lang.String r0 = "sound"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x0208
            r0 = 38
            goto L_0x0209
        L_0x0069:
            java.lang.String r0 = "saver"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x0208
            r0 = 14
            goto L_0x0209
        L_0x0075:
            java.lang.String r0 = "night"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x0208
            r0 = 15
            goto L_0x0209
        L_0x0081:
            java.lang.String r0 = "music"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x0208
            r0 = 29
            goto L_0x0209
        L_0x008d:
            java.lang.String r0 = "work"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x0208
            r0 = 6
            goto L_0x0209
        L_0x0098:
            java.lang.String r0 = "wifi"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x0208
            r0 = 0
            goto L_0x0209
        L_0x00a3:
            java.lang.String r0 = "user"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x0208
            r0 = 12
            goto L_0x0209
        L_0x00af:
            java.lang.String r0 = "sync"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x0208
            r0 = 35
            goto L_0x0209
        L_0x00bb:
            java.lang.String r0 = "dark"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x0208
            r0 = 17
            goto L_0x0209
        L_0x00c7:
            java.lang.String r0 = "cell"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x0208
            r0 = 2
            goto L_0x0209
        L_0x00d2:
            java.lang.String r0 = "cast"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x0208
            r0 = 10
            goto L_0x0209
        L_0x00de:
            java.lang.String r0 = "vpn"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x0208
            r0 = 41
            goto L_0x0209
        L_0x00ea:
            java.lang.String r0 = "nfc"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x0208
            r0 = 16
            goto L_0x0209
        L_0x00f6:
            java.lang.String r0 = "dnd"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x0208
            r0 = 3
            goto L_0x0209
        L_0x0101:
            java.lang.String r0 = "aod"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x0208
            r0 = 37
            goto L_0x0209
        L_0x010d:
            java.lang.String r0 = "bt"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x0208
            r0 = 1
            goto L_0x0209
        L_0x0118:
            java.lang.String r0 = "rotation"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x0208
            r0 = 7
            goto L_0x0209
        L_0x0123:
            java.lang.String r0 = "battery"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x0208
            r0 = 13
            goto L_0x0209
        L_0x012f:
            java.lang.String r0 = "caffeine"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x0208
            r0 = 18
            goto L_0x0209
        L_0x013b:
            java.lang.String r0 = "screenshot"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x0208
            r0 = 25
            goto L_0x0209
        L_0x0147:
            java.lang.String r0 = "fpsinfo"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x0208
            r0 = 39
            goto L_0x0209
        L_0x0153:
            java.lang.String r0 = "airplane"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x0208
            r0 = 5
            goto L_0x0209
        L_0x015e:
            java.lang.String r0 = "screenrecord"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x0208
            r0 = 27
            goto L_0x0209
        L_0x016a:
            java.lang.String r0 = "reboot"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x0208
            r0 = 30
            goto L_0x0209
        L_0x0176:
            java.lang.String r0 = "navbar"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x0208
            r0 = 40
            goto L_0x0209
        L_0x0182:
            java.lang.String r0 = "heads_up"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x0208
            r0 = 21
            goto L_0x0209
        L_0x018e:
            java.lang.String r0 = "livedisplay"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x0208
            r0 = 19
            goto L_0x0209
        L_0x019a:
            java.lang.String r0 = "flashlight"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x0208
            r0 = 8
            goto L_0x0209
        L_0x01a6:
            java.lang.String r0 = "hwkeys"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x0208
            r0 = 26
            goto L_0x0209
        L_0x01b1:
            java.lang.String r0 = "gaming"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x0208
            r0 = 32
            goto L_0x0209
        L_0x01bc:
            java.lang.String r0 = "dc_dimming"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x0208
            r0 = 42
            goto L_0x0209
        L_0x01c7:
            java.lang.String r0 = "reading_mode"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x0208
            r0 = 20
            goto L_0x0209
        L_0x01d2:
            java.lang.String r0 = "soundsearch"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x0208
            r0 = 31
            goto L_0x0209
        L_0x01dd:
            java.lang.String r0 = "adb_network"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x0208
            r0 = 34
            goto L_0x0209
        L_0x01e8:
            java.lang.String r0 = "screenstabilization"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x0208
            r0 = 36
            goto L_0x0209
        L_0x01f3:
            java.lang.String r0 = "inversion"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x0208
            r0 = 4
            goto L_0x0209
        L_0x01fd:
            java.lang.String r0 = "dataswitch"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x0208
            r0 = 33
            goto L_0x0209
        L_0x0208:
            r0 = -1
        L_0x0209:
            switch(r0) {
                case 0: goto L_0x039e;
                case 1: goto L_0x0395;
                case 2: goto L_0x038c;
                case 3: goto L_0x0383;
                case 4: goto L_0x037a;
                case 5: goto L_0x0371;
                case 6: goto L_0x0368;
                case 7: goto L_0x035f;
                case 8: goto L_0x0356;
                case 9: goto L_0x034d;
                case 10: goto L_0x0344;
                case 11: goto L_0x033b;
                case 12: goto L_0x0332;
                case 13: goto L_0x0329;
                case 14: goto L_0x0320;
                case 15: goto L_0x0317;
                case 16: goto L_0x030e;
                case 17: goto L_0x0305;
                case 18: goto L_0x02fc;
                case 19: goto L_0x02f3;
                case 20: goto L_0x02ea;
                case 21: goto L_0x02e1;
                case 22: goto L_0x02d8;
                case 23: goto L_0x02cf;
                case 24: goto L_0x02c6;
                case 25: goto L_0x02bd;
                case 26: goto L_0x02b4;
                case 27: goto L_0x02ab;
                case 28: goto L_0x02a2;
                case 29: goto L_0x0299;
                case 30: goto L_0x0290;
                case 31: goto L_0x0287;
                case 32: goto L_0x027e;
                case 33: goto L_0x0275;
                case 34: goto L_0x026c;
                case 35: goto L_0x0263;
                case 36: goto L_0x025a;
                case 37: goto L_0x0251;
                case 38: goto L_0x0248;
                case 39: goto L_0x023f;
                case 40: goto L_0x0236;
                case 41: goto L_0x022d;
                case 42: goto L_0x0224;
                case 43: goto L_0x021b;
                default: goto L_0x020c;
            }
        L_0x020c:
            java.lang.String r0 = "intent("
            boolean r0 = r2.startsWith(r0)
            if (r0 == 0) goto L_0x03a7
            com.android.systemui.qs.QSTileHost r1 = r1.mHost
            com.android.systemui.qs.tiles.IntentTile r1 = com.android.systemui.p006qs.tiles.IntentTile.create(r1, r2)
            return r1
        L_0x021b:
            javax.inject.Provider<com.android.systemui.qs.tiles.PowerShareTile> r1 = r1.mPowerShareTileProvider
            java.lang.Object r1 = r1.get()
            com.android.systemui.qs.tileimpl.QSTileImpl r1 = (com.android.systemui.p006qs.tileimpl.QSTileImpl) r1
            return r1
        L_0x0224:
            javax.inject.Provider<com.android.systemui.qs.tiles.DcDimmingTile> r1 = r1.mDcDimmingTileProvider
            java.lang.Object r1 = r1.get()
            com.android.systemui.qs.tileimpl.QSTileImpl r1 = (com.android.systemui.p006qs.tileimpl.QSTileImpl) r1
            return r1
        L_0x022d:
            javax.inject.Provider<com.android.systemui.qs.tiles.VpnTile> r1 = r1.mVpnTileProvider
            java.lang.Object r1 = r1.get()
            com.android.systemui.qs.tileimpl.QSTileImpl r1 = (com.android.systemui.p006qs.tileimpl.QSTileImpl) r1
            return r1
        L_0x0236:
            javax.inject.Provider<com.android.systemui.qs.tiles.NavBarTile> r1 = r1.mNavBarTileProvider
            java.lang.Object r1 = r1.get()
            com.android.systemui.qs.tileimpl.QSTileImpl r1 = (com.android.systemui.p006qs.tileimpl.QSTileImpl) r1
            return r1
        L_0x023f:
            javax.inject.Provider<com.android.systemui.qs.tiles.FPSInfoTile> r1 = r1.mFPSInfoTileProvider
            java.lang.Object r1 = r1.get()
            com.android.systemui.qs.tileimpl.QSTileImpl r1 = (com.android.systemui.p006qs.tileimpl.QSTileImpl) r1
            return r1
        L_0x0248:
            javax.inject.Provider<com.android.systemui.qs.tiles.SoundTile> r1 = r1.mSoundTileProvider
            java.lang.Object r1 = r1.get()
            com.android.systemui.qs.tileimpl.QSTileImpl r1 = (com.android.systemui.p006qs.tileimpl.QSTileImpl) r1
            return r1
        L_0x0251:
            javax.inject.Provider<com.android.systemui.qs.tiles.AODTile> r1 = r1.mAODTileProvider
            java.lang.Object r1 = r1.get()
            com.android.systemui.qs.tileimpl.QSTileImpl r1 = (com.android.systemui.p006qs.tileimpl.QSTileImpl) r1
            return r1
        L_0x025a:
            javax.inject.Provider<com.android.systemui.qs.tiles.ScreenStabilizationTile> r1 = r1.mScreenStabilizationTileProvider
            java.lang.Object r1 = r1.get()
            com.android.systemui.qs.tileimpl.QSTileImpl r1 = (com.android.systemui.p006qs.tileimpl.QSTileImpl) r1
            return r1
        L_0x0263:
            javax.inject.Provider<com.android.systemui.qs.tiles.SyncTile> r1 = r1.mSyncTileProvider
            java.lang.Object r1 = r1.get()
            com.android.systemui.qs.tileimpl.QSTileImpl r1 = (com.android.systemui.p006qs.tileimpl.QSTileImpl) r1
            return r1
        L_0x026c:
            javax.inject.Provider<com.android.systemui.qs.tiles.AdbOverNetworkTile> r1 = r1.mAdbOverNetworkProvider
            java.lang.Object r1 = r1.get()
            com.android.systemui.qs.tileimpl.QSTileImpl r1 = (com.android.systemui.p006qs.tileimpl.QSTileImpl) r1
            return r1
        L_0x0275:
            javax.inject.Provider<com.android.systemui.qs.tiles.DataSwitchTile> r1 = r1.mDataSwitchTileProvider
            java.lang.Object r1 = r1.get()
            com.android.systemui.qs.tileimpl.QSTileImpl r1 = (com.android.systemui.p006qs.tileimpl.QSTileImpl) r1
            return r1
        L_0x027e:
            javax.inject.Provider<com.android.systemui.qs.tiles.GamingModeTile> r1 = r1.mGamingModeTileProvider
            java.lang.Object r1 = r1.get()
            com.android.systemui.qs.tileimpl.QSTileImpl r1 = (com.android.systemui.p006qs.tileimpl.QSTileImpl) r1
            return r1
        L_0x0287:
            javax.inject.Provider<com.android.systemui.qs.tiles.SoundSearchTile> r1 = r1.mSoundSearchTileProvider
            java.lang.Object r1 = r1.get()
            com.android.systemui.qs.tileimpl.QSTileImpl r1 = (com.android.systemui.p006qs.tileimpl.QSTileImpl) r1
            return r1
        L_0x0290:
            javax.inject.Provider<com.android.systemui.qs.tiles.RebootTile> r1 = r1.mRebootTileProvider
            java.lang.Object r1 = r1.get()
            com.android.systemui.qs.tileimpl.QSTileImpl r1 = (com.android.systemui.p006qs.tileimpl.QSTileImpl) r1
            return r1
        L_0x0299:
            javax.inject.Provider<com.android.systemui.qs.tiles.MusicTile> r1 = r1.mMusicTileProvider
            java.lang.Object r1 = r1.get()
            com.android.systemui.qs.tileimpl.QSTileImpl r1 = (com.android.systemui.p006qs.tileimpl.QSTileImpl) r1
            return r1
        L_0x02a2:
            javax.inject.Provider<com.android.systemui.qs.tiles.CompassTile> r1 = r1.mCompassTileProvider
            java.lang.Object r1 = r1.get()
            com.android.systemui.qs.tileimpl.QSTileImpl r1 = (com.android.systemui.p006qs.tileimpl.QSTileImpl) r1
            return r1
        L_0x02ab:
            javax.inject.Provider<com.android.systemui.qs.tiles.ScreenRecordTile> r1 = r1.mScreenRecordTileProvider
            java.lang.Object r1 = r1.get()
            com.android.systemui.qs.tileimpl.QSTileImpl r1 = (com.android.systemui.p006qs.tileimpl.QSTileImpl) r1
            return r1
        L_0x02b4:
            javax.inject.Provider<com.android.systemui.qs.tiles.HWKeysTile> r1 = r1.mHWKeysTileProvider
            java.lang.Object r1 = r1.get()
            com.android.systemui.qs.tileimpl.QSTileImpl r1 = (com.android.systemui.p006qs.tileimpl.QSTileImpl) r1
            return r1
        L_0x02bd:
            javax.inject.Provider<com.android.systemui.qs.tiles.ScreenshotTile> r1 = r1.mScreenshotTileProvider
            java.lang.Object r1 = r1.get()
            com.android.systemui.qs.tileimpl.QSTileImpl r1 = (com.android.systemui.p006qs.tileimpl.QSTileImpl) r1
            return r1
        L_0x02c6:
            javax.inject.Provider<com.android.systemui.qs.tiles.CPUInfoTile> r1 = r1.mCPUInfoTileProvider
            java.lang.Object r1 = r1.get()
            com.android.systemui.qs.tileimpl.QSTileImpl r1 = (com.android.systemui.p006qs.tileimpl.QSTileImpl) r1
            return r1
        L_0x02cf:
            javax.inject.Provider<com.android.systemui.qs.tiles.SmartPixelsTile> r1 = r1.mSmartPixelsTileProvider
            java.lang.Object r1 = r1.get()
            com.android.systemui.qs.tileimpl.QSTileImpl r1 = (com.android.systemui.p006qs.tileimpl.QSTileImpl) r1
            return r1
        L_0x02d8:
            javax.inject.Provider<com.android.systemui.qs.tiles.UsbTetherTile> r1 = r1.mUsbTetherTileProvider
            java.lang.Object r1 = r1.get()
            com.android.systemui.qs.tileimpl.QSTileImpl r1 = (com.android.systemui.p006qs.tileimpl.QSTileImpl) r1
            return r1
        L_0x02e1:
            javax.inject.Provider<com.android.systemui.qs.tiles.HeadsUpTile> r1 = r1.mHeadsUpTileProvider
            java.lang.Object r1 = r1.get()
            com.android.systemui.qs.tileimpl.QSTileImpl r1 = (com.android.systemui.p006qs.tileimpl.QSTileImpl) r1
            return r1
        L_0x02ea:
            javax.inject.Provider<com.android.systemui.qs.tiles.ReadingModeTile> r1 = r1.mReadingModeTileProvider
            java.lang.Object r1 = r1.get()
            com.android.systemui.qs.tileimpl.QSTileImpl r1 = (com.android.systemui.p006qs.tileimpl.QSTileImpl) r1
            return r1
        L_0x02f3:
            javax.inject.Provider<com.android.systemui.qs.tiles.LiveDisplayTile> r1 = r1.mLiveDisplayTileProvider
            java.lang.Object r1 = r1.get()
            com.android.systemui.qs.tileimpl.QSTileImpl r1 = (com.android.systemui.p006qs.tileimpl.QSTileImpl) r1
            return r1
        L_0x02fc:
            javax.inject.Provider<com.android.systemui.qs.tiles.CaffeineTile> r1 = r1.mCaffeineTileProvider
            java.lang.Object r1 = r1.get()
            com.android.systemui.qs.tileimpl.QSTileImpl r1 = (com.android.systemui.p006qs.tileimpl.QSTileImpl) r1
            return r1
        L_0x0305:
            javax.inject.Provider<com.android.systemui.qs.tiles.UiModeNightTile> r1 = r1.mUiModeNightTileProvider
            java.lang.Object r1 = r1.get()
            com.android.systemui.qs.tileimpl.QSTileImpl r1 = (com.android.systemui.p006qs.tileimpl.QSTileImpl) r1
            return r1
        L_0x030e:
            javax.inject.Provider<com.android.systemui.qs.tiles.NfcTile> r1 = r1.mNfcTileProvider
            java.lang.Object r1 = r1.get()
            com.android.systemui.qs.tileimpl.QSTileImpl r1 = (com.android.systemui.p006qs.tileimpl.QSTileImpl) r1
            return r1
        L_0x0317:
            javax.inject.Provider<com.android.systemui.qs.tiles.NightDisplayTile> r1 = r1.mNightDisplayTileProvider
            java.lang.Object r1 = r1.get()
            com.android.systemui.qs.tileimpl.QSTileImpl r1 = (com.android.systemui.p006qs.tileimpl.QSTileImpl) r1
            return r1
        L_0x0320:
            javax.inject.Provider<com.android.systemui.qs.tiles.DataSaverTile> r1 = r1.mDataSaverTileProvider
            java.lang.Object r1 = r1.get()
            com.android.systemui.qs.tileimpl.QSTileImpl r1 = (com.android.systemui.p006qs.tileimpl.QSTileImpl) r1
            return r1
        L_0x0329:
            javax.inject.Provider<com.android.systemui.qs.tiles.BatterySaverTile> r1 = r1.mBatterySaverTileProvider
            java.lang.Object r1 = r1.get()
            com.android.systemui.qs.tileimpl.QSTileImpl r1 = (com.android.systemui.p006qs.tileimpl.QSTileImpl) r1
            return r1
        L_0x0332:
            javax.inject.Provider<com.android.systemui.qs.tiles.UserTile> r1 = r1.mUserTileProvider
            java.lang.Object r1 = r1.get()
            com.android.systemui.qs.tileimpl.QSTileImpl r1 = (com.android.systemui.p006qs.tileimpl.QSTileImpl) r1
            return r1
        L_0x033b:
            javax.inject.Provider<com.android.systemui.qs.tiles.HotspotTile> r1 = r1.mHotspotTileProvider
            java.lang.Object r1 = r1.get()
            com.android.systemui.qs.tileimpl.QSTileImpl r1 = (com.android.systemui.p006qs.tileimpl.QSTileImpl) r1
            return r1
        L_0x0344:
            javax.inject.Provider<com.android.systemui.qs.tiles.CastTile> r1 = r1.mCastTileProvider
            java.lang.Object r1 = r1.get()
            com.android.systemui.qs.tileimpl.QSTileImpl r1 = (com.android.systemui.p006qs.tileimpl.QSTileImpl) r1
            return r1
        L_0x034d:
            javax.inject.Provider<com.android.systemui.qs.tiles.LocationTile> r1 = r1.mLocationTileProvider
            java.lang.Object r1 = r1.get()
            com.android.systemui.qs.tileimpl.QSTileImpl r1 = (com.android.systemui.p006qs.tileimpl.QSTileImpl) r1
            return r1
        L_0x0356:
            javax.inject.Provider<com.android.systemui.qs.tiles.FlashlightTile> r1 = r1.mFlashlightTileProvider
            java.lang.Object r1 = r1.get()
            com.android.systemui.qs.tileimpl.QSTileImpl r1 = (com.android.systemui.p006qs.tileimpl.QSTileImpl) r1
            return r1
        L_0x035f:
            javax.inject.Provider<com.android.systemui.qs.tiles.RotationLockTile> r1 = r1.mRotationLockTileProvider
            java.lang.Object r1 = r1.get()
            com.android.systemui.qs.tileimpl.QSTileImpl r1 = (com.android.systemui.p006qs.tileimpl.QSTileImpl) r1
            return r1
        L_0x0368:
            javax.inject.Provider<com.android.systemui.qs.tiles.WorkModeTile> r1 = r1.mWorkModeTileProvider
            java.lang.Object r1 = r1.get()
            com.android.systemui.qs.tileimpl.QSTileImpl r1 = (com.android.systemui.p006qs.tileimpl.QSTileImpl) r1
            return r1
        L_0x0371:
            javax.inject.Provider<com.android.systemui.qs.tiles.AirplaneModeTile> r1 = r1.mAirplaneModeTileProvider
            java.lang.Object r1 = r1.get()
            com.android.systemui.qs.tileimpl.QSTileImpl r1 = (com.android.systemui.p006qs.tileimpl.QSTileImpl) r1
            return r1
        L_0x037a:
            javax.inject.Provider<com.android.systemui.qs.tiles.ColorInversionTile> r1 = r1.mColorInversionTileProvider
            java.lang.Object r1 = r1.get()
            com.android.systemui.qs.tileimpl.QSTileImpl r1 = (com.android.systemui.p006qs.tileimpl.QSTileImpl) r1
            return r1
        L_0x0383:
            javax.inject.Provider<com.android.systemui.qs.tiles.DndTile> r1 = r1.mDndTileProvider
            java.lang.Object r1 = r1.get()
            com.android.systemui.qs.tileimpl.QSTileImpl r1 = (com.android.systemui.p006qs.tileimpl.QSTileImpl) r1
            return r1
        L_0x038c:
            javax.inject.Provider<com.android.systemui.qs.tiles.CellularTile> r1 = r1.mCellularTileProvider
            java.lang.Object r1 = r1.get()
            com.android.systemui.qs.tileimpl.QSTileImpl r1 = (com.android.systemui.p006qs.tileimpl.QSTileImpl) r1
            return r1
        L_0x0395:
            javax.inject.Provider<com.android.systemui.qs.tiles.BluetoothTile> r1 = r1.mBluetoothTileProvider
            java.lang.Object r1 = r1.get()
            com.android.systemui.qs.tileimpl.QSTileImpl r1 = (com.android.systemui.p006qs.tileimpl.QSTileImpl) r1
            return r1
        L_0x039e:
            javax.inject.Provider<com.android.systemui.qs.tiles.WifiTile> r1 = r1.mWifiTileProvider
            java.lang.Object r1 = r1.get()
            com.android.systemui.qs.tileimpl.QSTileImpl r1 = (com.android.systemui.p006qs.tileimpl.QSTileImpl) r1
            return r1
        L_0x03a7:
            java.lang.String r0 = "custom("
            boolean r0 = r2.startsWith(r0)
            if (r0 == 0) goto L_0x03b6
            com.android.systemui.qs.QSTileHost r1 = r1.mHost
            com.android.systemui.qs.external.CustomTile r1 = com.android.systemui.p006qs.external.CustomTile.create(r1, r2)
            return r1
        L_0x03b6:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r0 = "No stock tile spec: "
            r1.append(r0)
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            java.lang.String r2 = "QSFactory"
            android.util.Log.w(r2, r1)
            r1 = 0
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.p006qs.tileimpl.QSFactoryImpl.createTileInternal(java.lang.String):com.android.systemui.qs.tileimpl.QSTileImpl");
    }

    public QSTileView createTileView(QSTile qSTile, boolean z) {
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(this.mHost.getContext(), C1785R$style.qs_theme);
        QSIconView createTileView = qSTile.createTileView(contextThemeWrapper);
        if (z) {
            return new QSTileBaseView(contextThemeWrapper, createTileView, z);
        }
        return new QSTileView(contextThemeWrapper, createTileView);
    }
}
