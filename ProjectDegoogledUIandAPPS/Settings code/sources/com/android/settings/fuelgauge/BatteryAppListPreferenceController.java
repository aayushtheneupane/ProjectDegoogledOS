package com.android.settings.fuelgauge;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.BatteryStats;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.UserHandle;
import android.os.UserManager;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.Log;
import android.util.SparseArray;
import androidx.preference.Preference;
import androidx.preference.PreferenceGroup;
import androidx.preference.PreferenceScreen;
import com.android.internal.os.BatterySipper;
import com.android.internal.os.BatteryStatsHelper;
import com.android.internal.os.PowerProfile;
import com.android.settings.SettingsActivity;
import com.android.settings.core.InstrumentedPreferenceFragment;
import com.android.settings.core.PreferenceControllerMixin;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnDestroy;
import com.android.settingslib.core.lifecycle.events.OnPause;
import com.android.settingslib.utils.StringUtil;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;
import java.util.List;

public class BatteryAppListPreferenceController extends AbstractPreferenceController implements PreferenceControllerMixin, LifecycleObserver, OnPause, OnDestroy {
    static final boolean USE_FAKE_DATA = false;
    /* access modifiers changed from: private */
    public SettingsActivity mActivity;
    PreferenceGroup mAppListGroup;
    private BatteryStatsHelper mBatteryStatsHelper;
    BatteryUtils mBatteryUtils;
    private InstrumentedPreferenceFragment mFragment;
    private Handler mHandler = new Handler(Looper.getMainLooper()) {
        public void handleMessage(Message message) {
            SettingsActivity access$100;
            int i = message.what;
            if (i == 1) {
                BatteryEntry batteryEntry = (BatteryEntry) message.obj;
                PowerGaugePreference powerGaugePreference = (PowerGaugePreference) BatteryAppListPreferenceController.this.mAppListGroup.findPreference(Integer.toString(batteryEntry.sipper.uidObj.getUid()));
                if (powerGaugePreference != null) {
                    powerGaugePreference.setIcon(BatteryAppListPreferenceController.this.mUserManager.getBadgedIconForUser(batteryEntry.getIcon(), new UserHandle(UserHandle.getUserId(batteryEntry.sipper.getUid()))));
                    powerGaugePreference.setTitle((CharSequence) batteryEntry.name);
                    if (batteryEntry.sipper.drainType == BatterySipper.DrainType.APP) {
                        powerGaugePreference.setContentDescription(batteryEntry.name);
                    }
                }
            } else if (i == 2 && (access$100 = BatteryAppListPreferenceController.this.mActivity) != null) {
                access$100.reportFullyDrawn();
            }
            super.handleMessage(message);
        }
    };
    private Context mPrefContext;
    private ArrayMap<String, Preference> mPreferenceCache;
    private final String mPreferenceKey;
    /* access modifiers changed from: private */
    public UserManager mUserManager;

    public boolean isAvailable() {
        return true;
    }

    public BatteryAppListPreferenceController(Context context, String str, Lifecycle lifecycle, SettingsActivity settingsActivity, InstrumentedPreferenceFragment instrumentedPreferenceFragment) {
        super(context);
        if (lifecycle != null) {
            lifecycle.addObserver(this);
        }
        this.mPreferenceKey = str;
        this.mBatteryUtils = BatteryUtils.getInstance(context);
        this.mUserManager = (UserManager) context.getSystemService("user");
        this.mActivity = settingsActivity;
        this.mFragment = instrumentedPreferenceFragment;
    }

    public void onPause() {
        BatteryEntry.stopRequestQueue();
        this.mHandler.removeMessages(1);
    }

    public void onDestroy() {
        if (this.mActivity.isChangingConfigurations()) {
            BatteryEntry.clearUidCache();
        }
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mPrefContext = preferenceScreen.getContext();
        this.mAppListGroup = (PreferenceGroup) preferenceScreen.findPreference(this.mPreferenceKey);
    }

    public String getPreferenceKey() {
        return this.mPreferenceKey;
    }

    public boolean handlePreferenceTreeClick(Preference preference) {
        if (!(preference instanceof PowerGaugePreference)) {
            return USE_FAKE_DATA;
        }
        PowerGaugePreference powerGaugePreference = (PowerGaugePreference) preference;
        AdvancedPowerUsageDetail.startBatteryDetailPage(this.mActivity, this.mBatteryUtils, this.mFragment, this.mBatteryStatsHelper, 0, powerGaugePreference.getInfo(), powerGaugePreference.getPercent());
        return true;
    }

    public void refreshAppListGroup(BatteryStatsHelper batteryStatsHelper, boolean z) {
        boolean z2;
        double d;
        boolean z3;
        if (isAvailable()) {
            this.mBatteryStatsHelper = batteryStatsHelper;
            this.mAppListGroup.setTitle((int) C1715R.string.power_usage_list_summary);
            PowerProfile powerProfile = batteryStatsHelper.getPowerProfile();
            BatteryStats stats = batteryStatsHelper.getStats();
            double averagePower = powerProfile.getAveragePower("screen.full");
            int dischargeAmount = stats != null ? stats.getDischargeAmount(0) : 0;
            cacheRemoveAllPrefs(this.mAppListGroup);
            this.mAppListGroup.setOrderingAsAdded(USE_FAKE_DATA);
            if (averagePower < 10.0d) {
                z2 = false;
            } else {
                List<BatterySipper> coalescedUsageList = getCoalescedUsageList(batteryStatsHelper.getUsageList());
                if (z) {
                    d = 0.0d;
                } else {
                    d = this.mBatteryUtils.removeHiddenBatterySippers(coalescedUsageList);
                }
                double d2 = d;
                this.mBatteryUtils.sortUsageList(coalescedUsageList);
                int size = coalescedUsageList.size();
                int i = 0;
                z2 = false;
                while (i < size) {
                    BatterySipper batterySipper = coalescedUsageList.get(i);
                    int i2 = i;
                    BatterySipper batterySipper2 = batterySipper;
                    int i3 = size;
                    double calculateBatteryPercent = this.mBatteryUtils.calculateBatteryPercent(batterySipper.totalPowerMah, batteryStatsHelper.getTotalPower(), d2, dischargeAmount);
                    if (((int) (0.5d + calculateBatteryPercent)) >= 1 && !shouldHideSipper(batterySipper2)) {
                        UserHandle userHandle = new UserHandle(UserHandle.getUserId(batterySipper2.getUid()));
                        BatteryEntry batteryEntry = new BatteryEntry(this.mActivity, this.mHandler, this.mUserManager, batterySipper2);
                        Drawable badgedIconForUser = this.mUserManager.getBadgedIconForUser(batteryEntry.getIcon(), userHandle);
                        CharSequence badgedLabelForUser = this.mUserManager.getBadgedLabelForUser(batteryEntry.getLabel(), userHandle);
                        String extractKeyFromSipper = extractKeyFromSipper(batterySipper2);
                        PowerGaugePreference powerGaugePreference = (PowerGaugePreference) getCachedPreference(extractKeyFromSipper);
                        if (powerGaugePreference == null) {
                            powerGaugePreference = new PowerGaugePreference(this.mPrefContext, badgedIconForUser, badgedLabelForUser, batteryEntry);
                            powerGaugePreference.setKey(extractKeyFromSipper);
                        }
                        batterySipper2.percent = calculateBatteryPercent;
                        powerGaugePreference.setTitle((CharSequence) batteryEntry.getLabel());
                        powerGaugePreference.setOrder(i2 + 1);
                        powerGaugePreference.setPercent(calculateBatteryPercent);
                        z3 = USE_FAKE_DATA;
                        powerGaugePreference.shouldShowAnomalyIcon(USE_FAKE_DATA);
                        if (batterySipper2.usageTimeMs == 0 && batterySipper2.drainType == BatterySipper.DrainType.APP) {
                            batterySipper2.usageTimeMs = this.mBatteryUtils.getProcessTimeMs(1, batterySipper2.uidObj, 0);
                        }
                        setUsageSummary(powerGaugePreference, batterySipper2);
                        this.mAppListGroup.addPreference(powerGaugePreference);
                        z2 = true;
                        if (this.mAppListGroup.getPreferenceCount() - getCachedCount() > 21) {
                            break;
                        }
                    } else {
                        z3 = USE_FAKE_DATA;
                    }
                    i = i2 + 1;
                    boolean z4 = z3;
                    size = i3;
                }
            }
            if (!z2) {
                addNotAvailableMessage();
            }
            removeCachedPrefs(this.mAppListGroup);
            BatteryEntry.startRequestQueue();
        }
    }

    private List<BatterySipper> getCoalescedUsageList(List<BatterySipper> list) {
        String str;
        SparseArray sparseArray = new SparseArray();
        ArrayList arrayList = new ArrayList();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            BatterySipper batterySipper = list.get(i);
            if (batterySipper.getUid() > 0) {
                int uid = batterySipper.getUid();
                if (isSharedGid(batterySipper.getUid())) {
                    uid = UserHandle.getUid(0, UserHandle.getAppIdFromSharedAppGid(batterySipper.getUid()));
                }
                if (isSystemUid(uid) && !"mediaserver".equals(batterySipper.packageWithHighestDrain)) {
                    uid = 1000;
                }
                if (uid != batterySipper.getUid()) {
                    BatterySipper batterySipper2 = new BatterySipper(batterySipper.drainType, new FakeUid(uid), 0.0d);
                    batterySipper2.add(batterySipper);
                    batterySipper2.packageWithHighestDrain = batterySipper.packageWithHighestDrain;
                    batterySipper2.mPackages = batterySipper.mPackages;
                    batterySipper = batterySipper2;
                }
                int indexOfKey = sparseArray.indexOfKey(uid);
                if (indexOfKey < 0) {
                    sparseArray.put(uid, batterySipper);
                } else {
                    BatterySipper batterySipper3 = (BatterySipper) sparseArray.valueAt(indexOfKey);
                    batterySipper3.add(batterySipper);
                    if (batterySipper3.packageWithHighestDrain == null && (str = batterySipper.packageWithHighestDrain) != null) {
                        batterySipper3.packageWithHighestDrain = str;
                    }
                    String[] strArr = batterySipper3.mPackages;
                    int length = strArr != null ? strArr.length : 0;
                    String[] strArr2 = batterySipper.mPackages;
                    int length2 = strArr2 != null ? strArr2.length : 0;
                    if (length2 > 0) {
                        String[] strArr3 = new String[(length + length2)];
                        if (length > 0) {
                            System.arraycopy(batterySipper3.mPackages, 0, strArr3, 0, length);
                        }
                        System.arraycopy(batterySipper.mPackages, 0, strArr3, length, length2);
                        batterySipper3.mPackages = strArr3;
                    }
                }
            } else {
                arrayList.add(batterySipper);
            }
        }
        int size2 = sparseArray.size();
        for (int i2 = 0; i2 < size2; i2++) {
            arrayList.add((BatterySipper) sparseArray.valueAt(i2));
        }
        this.mBatteryUtils.sortUsageList(arrayList);
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    public void setUsageSummary(Preference preference, BatterySipper batterySipper) {
        long j = batterySipper.usageTimeMs;
        if (j >= 60000) {
            CharSequence formatElapsedTime = StringUtil.formatElapsedTime(this.mContext, (double) j, USE_FAKE_DATA);
            if (batterySipper.drainType == BatterySipper.DrainType.APP && !this.mBatteryUtils.shouldHideSipper(batterySipper)) {
                formatElapsedTime = TextUtils.expandTemplate(this.mContext.getText(C1715R.string.battery_used_for), new CharSequence[]{formatElapsedTime});
            }
            preference.setSummary(formatElapsedTime);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean shouldHideSipper(BatterySipper batterySipper) {
        BatterySipper.DrainType drainType = batterySipper.drainType;
        if (drainType == BatterySipper.DrainType.OVERCOUNTED || drainType == BatterySipper.DrainType.UNACCOUNTED || this.mBatteryUtils.isHiddenSystemModule(batterySipper)) {
            return true;
        }
        return USE_FAKE_DATA;
    }

    /* access modifiers changed from: package-private */
    public String extractKeyFromSipper(BatterySipper batterySipper) {
        if (batterySipper.uidObj != null) {
            return extractKeyFromUid(batterySipper.getUid());
        }
        BatterySipper.DrainType drainType = batterySipper.drainType;
        if (drainType == BatterySipper.DrainType.USER) {
            return batterySipper.drainType.toString() + batterySipper.userId;
        } else if (drainType != BatterySipper.DrainType.APP) {
            return drainType.toString();
        } else {
            if (batterySipper.getPackages() != null) {
                return TextUtils.concat(batterySipper.getPackages()).toString();
            }
            Log.w("PrefControllerMixin", "Inappropriate BatterySipper without uid and package names: " + batterySipper);
            return "-1";
        }
    }

    /* access modifiers changed from: package-private */
    public String extractKeyFromUid(int i) {
        return Integer.toString(i);
    }

    private void cacheRemoveAllPrefs(PreferenceGroup preferenceGroup) {
        this.mPreferenceCache = new ArrayMap<>();
        int preferenceCount = preferenceGroup.getPreferenceCount();
        for (int i = 0; i < preferenceCount; i++) {
            Preference preference = preferenceGroup.getPreference(i);
            if (!TextUtils.isEmpty(preference.getKey())) {
                this.mPreferenceCache.put(preference.getKey(), preference);
            }
        }
    }

    private static boolean isSharedGid(int i) {
        if (UserHandle.getAppIdFromSharedAppGid(i) > 0) {
            return true;
        }
        return USE_FAKE_DATA;
    }

    private static boolean isSystemUid(int i) {
        int appId = UserHandle.getAppId(i);
        if (appId < 1000 || appId >= 10000) {
            return USE_FAKE_DATA;
        }
        return true;
    }

    private Preference getCachedPreference(String str) {
        ArrayMap<String, Preference> arrayMap = this.mPreferenceCache;
        if (arrayMap != null) {
            return arrayMap.remove(str);
        }
        return null;
    }

    private void removeCachedPrefs(PreferenceGroup preferenceGroup) {
        for (Preference removePreference : this.mPreferenceCache.values()) {
            preferenceGroup.removePreference(removePreference);
        }
        this.mPreferenceCache = null;
    }

    private int getCachedCount() {
        ArrayMap<String, Preference> arrayMap = this.mPreferenceCache;
        if (arrayMap != null) {
            return arrayMap.size();
        }
        return 0;
    }

    private void addNotAvailableMessage() {
        if (getCachedPreference("not_available") == null) {
            Preference preference = new Preference(this.mPrefContext);
            preference.setKey("not_available");
            preference.setTitle((int) C1715R.string.power_usage_not_available);
            preference.setSelectable(USE_FAKE_DATA);
            this.mAppListGroup.addPreference(preference);
        }
    }
}
