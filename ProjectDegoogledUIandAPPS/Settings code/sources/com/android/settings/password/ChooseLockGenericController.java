package com.android.settings.password;

import android.app.admin.DevicePolicyManager;
import android.app.admin.PasswordMetrics;
import android.content.ComponentName;
import android.content.Context;
import android.os.UserHandle;
import com.android.internal.widget.LockPatternUtils;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;
import java.util.List;

public class ChooseLockGenericController {
    private final Context mContext;
    private DevicePolicyManager mDpm;
    private final LockPatternUtils mLockPatternUtils;
    private ManagedLockPasswordProvider mManagedPasswordProvider;
    private final int mRequestedMinComplexity;
    private final int mUserId;

    public ChooseLockGenericController(Context context, int i) {
        this(context, i, 0, new LockPatternUtils(context));
    }

    public ChooseLockGenericController(Context context, int i, int i2, LockPatternUtils lockPatternUtils) {
        this(context, i, i2, (DevicePolicyManager) context.getSystemService(DevicePolicyManager.class), ManagedLockPasswordProvider.get(context, i), lockPatternUtils);
    }

    ChooseLockGenericController(Context context, int i, int i2, DevicePolicyManager devicePolicyManager, ManagedLockPasswordProvider managedLockPasswordProvider, LockPatternUtils lockPatternUtils) {
        this.mContext = context;
        this.mUserId = i;
        this.mRequestedMinComplexity = i2;
        this.mManagedPasswordProvider = managedLockPasswordProvider;
        this.mDpm = devicePolicyManager;
        this.mLockPatternUtils = lockPatternUtils;
    }

    public int upgradeQuality(int i) {
        return Math.max(Math.max(i, this.mDpm.getPasswordQuality((ComponentName) null, this.mUserId)), PasswordMetrics.complexityLevelToMinQuality(this.mRequestedMinComplexity));
    }

    /* renamed from: com.android.settings.password.ChooseLockGenericController$1 */
    static /* synthetic */ class C11141 {
        static final /* synthetic */ int[] $SwitchMap$com$android$settings$password$ScreenLockType = new int[ScreenLockType.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|(3:11|12|14)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0040 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0035 */
        static {
            /*
                com.android.settings.password.ScreenLockType[] r0 = com.android.settings.password.ScreenLockType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$android$settings$password$ScreenLockType = r0
                int[] r0 = $SwitchMap$com$android$settings$password$ScreenLockType     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.android.settings.password.ScreenLockType r1 = com.android.settings.password.ScreenLockType.NONE     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = $SwitchMap$com$android$settings$password$ScreenLockType     // Catch:{ NoSuchFieldError -> 0x001f }
                com.android.settings.password.ScreenLockType r1 = com.android.settings.password.ScreenLockType.SWIPE     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = $SwitchMap$com$android$settings$password$ScreenLockType     // Catch:{ NoSuchFieldError -> 0x002a }
                com.android.settings.password.ScreenLockType r1 = com.android.settings.password.ScreenLockType.MANAGED     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = $SwitchMap$com$android$settings$password$ScreenLockType     // Catch:{ NoSuchFieldError -> 0x0035 }
                com.android.settings.password.ScreenLockType r1 = com.android.settings.password.ScreenLockType.PIN     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                int[] r0 = $SwitchMap$com$android$settings$password$ScreenLockType     // Catch:{ NoSuchFieldError -> 0x0040 }
                com.android.settings.password.ScreenLockType r1 = com.android.settings.password.ScreenLockType.PATTERN     // Catch:{ NoSuchFieldError -> 0x0040 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0040 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0040 }
            L_0x0040:
                int[] r0 = $SwitchMap$com$android$settings$password$ScreenLockType     // Catch:{ NoSuchFieldError -> 0x004b }
                com.android.settings.password.ScreenLockType r1 = com.android.settings.password.ScreenLockType.PASSWORD     // Catch:{ NoSuchFieldError -> 0x004b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x004b }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x004b }
            L_0x004b:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.settings.password.ChooseLockGenericController.C11141.<clinit>():void");
        }
    }

    public boolean isScreenLockVisible(ScreenLockType screenLockType) {
        boolean z = this.mUserId != UserHandle.myUserId();
        switch (C11141.$SwitchMap$com$android$settings$password$ScreenLockType[screenLockType.ordinal()]) {
            case 1:
                if (this.mContext.getResources().getBoolean(C1715R.bool.config_hide_none_security_option) || z) {
                    return false;
                }
                return true;
            case 2:
                if (this.mContext.getResources().getBoolean(C1715R.bool.config_hide_swipe_security_option) || z) {
                    return false;
                }
                return true;
            case 3:
                return this.mManagedPasswordProvider.isManagedPasswordChoosable();
            case 4:
            case 5:
            case 6:
                return this.mLockPatternUtils.hasSecureLockScreen();
            default:
                return true;
        }
    }

    public boolean isScreenLockEnabled(ScreenLockType screenLockType, int i) {
        return screenLockType.maxQuality >= i;
    }

    public boolean isScreenLockDisabledByAdmin(ScreenLockType screenLockType, int i) {
        boolean z = screenLockType.maxQuality < i;
        if (screenLockType == ScreenLockType.MANAGED) {
            return z || !this.mManagedPasswordProvider.isManagedPasswordChoosable();
        }
        return z;
    }

    public CharSequence getTitle(ScreenLockType screenLockType) {
        switch (C11141.$SwitchMap$com$android$settings$password$ScreenLockType[screenLockType.ordinal()]) {
            case 1:
                return this.mContext.getText(C1715R.string.unlock_set_unlock_off_title);
            case 2:
                return this.mContext.getText(C1715R.string.unlock_set_unlock_none_title);
            case 3:
                return this.mManagedPasswordProvider.getPickerOptionTitle(false);
            case 4:
                return this.mContext.getText(C1715R.string.unlock_set_unlock_pin_title);
            case 5:
                return this.mContext.getText(C1715R.string.unlock_set_unlock_pattern_title);
            case 6:
                return this.mContext.getText(C1715R.string.unlock_set_unlock_password_title);
            default:
                return null;
        }
    }

    public List<ScreenLockType> getVisibleScreenLockTypes(int i, boolean z) {
        int upgradeQuality = upgradeQuality(i);
        ArrayList arrayList = new ArrayList();
        for (ScreenLockType screenLockType : ScreenLockType.values()) {
            if (isScreenLockVisible(screenLockType) && (z || isScreenLockEnabled(screenLockType, upgradeQuality))) {
                arrayList.add(screenLockType);
            }
        }
        return arrayList;
    }
}
