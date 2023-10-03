package com.google.android.setupcompat.util;

import android.content.Intent;
import android.os.Build;
import java.util.Arrays;

public class WizardManagerHelper {
    static final String EXTRA_ACTION_ID = "actionId";
    static final String EXTRA_IS_DEFERRED_SETUP = "deferredSetup";
    public static final String EXTRA_IS_FIRST_RUN = "firstRun";
    static final String EXTRA_IS_PRE_DEFERRED_SETUP = "preDeferredSetup";
    public static final String EXTRA_IS_SETUP_FLOW = "isSetupFlow";
    static final String EXTRA_SCRIPT_URI = "scriptUri";
    static final String EXTRA_WIZARD_BUNDLE = "wizardBundle";

    public static void copyWizardManagerExtras(Intent intent, Intent intent2) {
        intent2.putExtra(EXTRA_WIZARD_BUNDLE, intent.getBundleExtra(EXTRA_WIZARD_BUNDLE));
        for (String str : Arrays.asList(new String[]{EXTRA_IS_FIRST_RUN, EXTRA_IS_DEFERRED_SETUP, EXTRA_IS_PRE_DEFERRED_SETUP, EXTRA_IS_SETUP_FLOW})) {
            intent2.putExtra(str, intent.getBooleanExtra(str, false));
        }
        for (String str2 : Arrays.asList(new String[]{"theme", EXTRA_SCRIPT_URI, EXTRA_ACTION_ID})) {
            intent2.putExtra(str2, intent.getStringExtra(str2));
        }
    }

    @Deprecated
    public static boolean isSetupWizardIntent(Intent intent) {
        return intent.getBooleanExtra(EXTRA_IS_FIRST_RUN, false);
    }

    public static boolean isDeferredSetupWizard(Intent intent) {
        return intent != null && intent.getBooleanExtra(EXTRA_IS_DEFERRED_SETUP, false);
    }

    public static boolean isPreDeferredSetupWizard(Intent intent) {
        return intent != null && intent.getBooleanExtra(EXTRA_IS_PRE_DEFERRED_SETUP, false);
    }

    public static boolean isInitialSetupWizard(Intent intent) {
        return intent.getBooleanExtra(EXTRA_IS_FIRST_RUN, false);
    }

    public static boolean isAnySetupWizard(Intent intent) {
        if (intent == null) {
            return false;
        }
        if (Build.VERSION.SDK_INT >= 29) {
            return intent.getBooleanExtra(EXTRA_IS_SETUP_FLOW, false);
        }
        if (isInitialSetupWizard(intent) || isPreDeferredSetupWizard(intent) || isDeferredSetupWizard(intent)) {
            return true;
        }
        return false;
    }
}
