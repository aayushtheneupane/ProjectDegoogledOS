package com.android.dialer.blocking;

import android.app.FragmentManager;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.provider.BlockedNumberContract;
import android.telecom.TelecomManager;
import com.android.dialer.blocking.BlockedNumbersMigrator;
import com.android.dialer.common.LogUtil;
import com.android.dialer.configprovider.ConfigProviderComponent;
import com.android.dialer.configprovider.SharedPrefConfigProvider;
import com.android.dialer.database.FilteredNumberContract;
import com.android.dialer.function.Supplier;
import com.android.dialer.strictmode.StrictModeUtils;
import com.android.dialer.telecom.TelecomUtil;
import java.util.ArrayList;

@Deprecated
public class FilteredNumberCompat {
    public static final String HAS_MIGRATED_TO_NEW_BLOCKING_KEY = "migratedToNewBlocking";
    private static Boolean canAttemptBlockOperationsForTest;

    public static boolean canAttemptBlockOperations(Context context) {
        boolean z;
        Boolean bool = canAttemptBlockOperationsForTest;
        if (bool != null) {
            return bool.booleanValue();
        }
        if (!TelecomUtil.isDefaultDialer(context)) {
            return false;
        }
        try {
            z = BlockedNumberContract.canCurrentUserBlockNumbers(context);
        } catch (Exception e) {
            LogUtil.m7e("FilteredNumberCompat.safeBlockedNumbersContractCanCurrentUserBlockNumbers", "Exception while querying BlockedNumberContract", (Throwable) e);
            z = false;
        }
        if (z) {
            return true;
        }
        return false;
    }

    public static boolean canCurrentUserOpenBlockSettings(Context context) {
        boolean z;
        if (!TelecomUtil.isDefaultDialer(context)) {
            return false;
        }
        try {
            z = BlockedNumberContract.canCurrentUserBlockNumbers(context);
        } catch (Exception e) {
            LogUtil.m7e("FilteredNumberCompat.safeBlockedNumbersContractCanCurrentUserBlockNumbers", "Exception while querying BlockedNumberContract", (Throwable) e);
            z = false;
        }
        if (z) {
            return true;
        }
        return false;
    }

    public static Intent createManageBlockedNumbersIntent(Context context) {
        if (hasMigratedToNewBlocking(context)) {
            return ((TelecomManager) context.getSystemService(TelecomManager.class)).createManageBlockedNumbersIntent();
        }
        Intent intent = new Intent("com.android.dialer.action.BLOCKED_NUMBERS_SETTINGS");
        intent.setPackage(context.getPackageName());
        return intent;
    }

    public static String[] filter(String[] strArr) {
        if (strArr == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (String str : strArr) {
            if (str != null) {
                arrayList.add(str);
            }
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    public static Uri getContentUri(Context context, Integer num) {
        if (num == null) {
            return useNewFiltering(context) ? BlockedNumberContract.BlockedNumbers.CONTENT_URI : FilteredNumberContract.FilteredNumber.CONTENT_URI;
        }
        return ContentUris.withAppendedId(useNewFiltering(context) ? BlockedNumberContract.BlockedNumbers.CONTENT_URI : FilteredNumberContract.FilteredNumber.CONTENT_URI, (long) num.intValue());
    }

    public static String getE164NumberColumnName(Context context) {
        return useNewFiltering(context) ? "e164_number" : "normalized_number";
    }

    public static String getIdColumnName(Context context) {
        boolean useNewFiltering = useNewFiltering(context);
        return "_id";
    }

    public static String getOriginalNumberColumnName(Context context) {
        return useNewFiltering(context) ? "original_number" : "number";
    }

    public static String getTypeColumnName(Context context) {
        if (useNewFiltering(context)) {
            return null;
        }
        return "type";
    }

    public static boolean hasMigratedToNewBlocking(Context context) {
        return ((Boolean) StrictModeUtils.bypass(new Supplier(context) {
            private final /* synthetic */ Context f$0;

            {
                this.f$0 = r1;
            }

            public final Object get() {
                return Boolean.valueOf(PreferenceManager.getDefaultSharedPreferences(this.f$0).getBoolean(FilteredNumberCompat.HAS_MIGRATED_TO_NEW_BLOCKING_KEY, false));
            }
        })).booleanValue();
    }

    public static boolean maybeShowBlockNumberMigrationDialog(Context context, FragmentManager fragmentManager, BlockedNumbersMigrator.Listener listener) {
        if (!(!hasMigratedToNewBlocking(context))) {
            return false;
        }
        LogUtil.m9i("FilteredNumberCompat.maybeShowBlockNumberMigrationDialog", "maybeShowBlockNumberMigrationDialog - showing migration dialog", new Object[0]);
        MigrateBlockedNumbersDialogFragment.newInstance(new BlockedNumbersMigrator(context), listener).show(fragmentManager, "MigrateBlockedNumbers");
        return true;
    }

    public static void setCanAttemptBlockOperationsForTest(boolean z) {
        canAttemptBlockOperationsForTest = Boolean.valueOf(z);
    }

    public static void setHasMigratedToNewBlocking(Context context, boolean z) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean(HAS_MIGRATED_TO_NEW_BLOCKING_KEY, z).apply();
    }

    public static boolean useNewFiltering(Context context) {
        if (((SharedPrefConfigProvider) ConfigProviderComponent.get(context).getConfigProvider()).getBoolean("debug_force_dialer_filtering", false) || !hasMigratedToNewBlocking(context)) {
            return false;
        }
        return true;
    }
}
