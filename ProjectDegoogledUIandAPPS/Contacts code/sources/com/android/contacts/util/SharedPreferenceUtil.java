package com.android.contacts.util;

import android.app.backup.BackupManager;
import android.content.Context;
import android.content.SharedPreferences;
import com.android.contacts.model.SimCard;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SharedPreferenceUtil {
    public static boolean getHamburgerPromoDisplayedBefore(Context context) {
        return getSharedPreferences(context).getBoolean("hamburgerPromoDisplayed", false);
    }

    public static void setHamburgerPromoDisplayedBefore(Context context) {
        getSharedPreferences(context).edit().putBoolean("hamburgerPromoDisplayed", true).apply();
        new BackupManager(context).dataChanged();
    }

    public static boolean getHamburgerMenuClickedBefore(Context context) {
        return getSharedPreferences(context).getBoolean("hamburgerMenuClicked", false);
    }

    public static void setHamburgerMenuClickedBefore(Context context) {
        getSharedPreferences(context).edit().putBoolean("hamburgerMenuClicked", true).apply();
        new BackupManager(context).dataChanged();
    }

    public static boolean getHamburgerPromoTriggerActionHappenedBefore(Context context) {
        return getSharedPreferences(context).getBoolean("hamburgerPromoTriggerActionHappened", false);
    }

    public static void setHamburgerPromoTriggerActionHappenedBefore(Context context) {
        getSharedPreferences(context).edit().putBoolean("hamburgerPromoTriggerActionHappened", true).apply();
        new BackupManager(context).dataChanged();
    }

    public static boolean getShouldShowHamburgerPromo(Context context) {
        return !getHamburgerMenuClickedBefore(context) && getHamburgerPromoTriggerActionHappenedBefore(context) && !getHamburgerPromoDisplayedBefore(context);
    }

    protected static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(getSharedPreferencesFilename(context), 0);
    }

    public static String getSharedPreferencesFilename(Context context) {
        return context.getPackageName();
    }

    public static int getNumOfDismissesForAutoSyncOff(Context context) {
        return getSharedPreferences(context).getInt("num-of-dismisses-auto-sync-off", 0);
    }

    public static void resetNumOfDismissesForAutoSyncOff(Context context) {
        if (getSharedPreferences(context).getInt("num-of-dismisses-auto-sync-off", 0) != 0) {
            getSharedPreferences(context).edit().putInt("num-of-dismisses-auto-sync-off", 0).apply();
        }
    }

    public static void incNumOfDismissesForAutoSyncOff(Context context) {
        getSharedPreferences(context).edit().putInt("num-of-dismisses-auto-sync-off", getSharedPreferences(context).getInt("num-of-dismisses-auto-sync-off", 0) + 1).apply();
    }

    private static String buildSharedPrefsName(String str) {
        return str + "-" + "num-of-dismisses-account-sync-off";
    }

    public static int getNumOfDismissesforAccountSyncOff(Context context, String str) {
        return getSharedPreferences(context).getInt(buildSharedPrefsName(str), 0);
    }

    public static void resetNumOfDismissesForAccountSyncOff(Context context, String str) {
        if (getSharedPreferences(context).getInt(buildSharedPrefsName(str), 0) != 0) {
            getSharedPreferences(context).edit().putInt(buildSharedPrefsName(str), 0).apply();
        }
    }

    public static void incNumOfDismissesForAccountSyncOff(Context context, String str) {
        getSharedPreferences(context).edit().putInt(buildSharedPrefsName(str), getSharedPreferences(context).getInt(buildSharedPrefsName(str), 0) + 1).apply();
    }

    public static void persistSimStates(Context context, Collection<SimCard> collection) {
        HashSet hashSet = new HashSet(getImportedSims(context));
        HashSet hashSet2 = new HashSet(getDismissedSims(context));
        for (SimCard next : collection) {
            String simId = next.getSimId();
            if (simId != null) {
                if (next.isImported()) {
                    hashSet.add(simId);
                } else {
                    hashSet.remove(simId);
                }
                if (next.isDismissed()) {
                    hashSet2.add(simId);
                } else {
                    hashSet2.remove(simId);
                }
            }
        }
        getSharedPreferences(context).edit().putStringSet("importedSimCards", hashSet).putStringSet("dismissedSimCards", hashSet2).apply();
    }

    public static List<SimCard> restoreSimStates(Context context, List<SimCard> list) {
        Set<String> importedSims = getImportedSims(context);
        Set<String> dismissedSims = getDismissedSims(context);
        ArrayList arrayList = new ArrayList();
        for (SimCard next : list) {
            arrayList.add(next.withImportAndDismissStates(importedSims.contains(next.getSimId()), dismissedSims.contains(next.getSimId())));
        }
        return arrayList;
    }

    private static Set<String> getImportedSims(Context context) {
        return getSharedPreferences(context).getStringSet("importedSimCards", Collections.emptySet());
    }

    private static Set<String> getDismissedSims(Context context) {
        return getSharedPreferences(context).getStringSet("dismissedSimCards", Collections.emptySet());
    }

    public static boolean isWelcomeCardDismissed(Context context) {
        return getSharedPreferences(context).getBoolean("welcome-reminder-card-dismissed", false);
    }
}
