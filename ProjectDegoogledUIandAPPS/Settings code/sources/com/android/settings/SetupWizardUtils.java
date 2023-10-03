package com.android.settings;

import android.content.Intent;
import android.os.Bundle;
import android.sysprop.SetupWizardProperties;
import com.google.android.setupcompat.util.WizardManagerHelper;
import com.havoc.config.center.C1715R;
import java.util.Arrays;

public class SetupWizardUtils {
    public static String getThemeString(Intent intent) {
        String stringExtra = intent.getStringExtra("theme");
        return stringExtra == null ? (String) SetupWizardProperties.theme().orElse("") : stringExtra;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x00c5 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int getTheme(android.content.Intent r16) {
        /*
            java.lang.String r0 = getThemeString(r16)
            r1 = 2131951880(0x7f130108, float:1.9540187E38)
            if (r0 == 0) goto L_0x00c5
            boolean r2 = com.google.android.setupcompat.util.WizardManagerHelper.isAnySetupWizard(r16)
            r3 = 2131951882(0x7f13010a, float:1.9540191E38)
            r4 = 2131951888(0x7f130110, float:1.9540203E38)
            java.lang.String r5 = "glif_light"
            java.lang.String r6 = "glif_v3"
            java.lang.String r7 = "glif_v2"
            java.lang.String r8 = "glif"
            java.lang.String r9 = "glif_v3_light"
            java.lang.String r10 = "glif_v2_light"
            r12 = 5
            r13 = 4
            r14 = 3
            r15 = 2
            r11 = 1
            if (r2 == 0) goto L_0x007c
            int r2 = r0.hashCode()
            switch(r2) {
                case -2128555920: goto L_0x0056;
                case -1241052239: goto L_0x004e;
                case 3175618: goto L_0x0046;
                case 115650329: goto L_0x003e;
                case 115650330: goto L_0x0036;
                case 767685465: goto L_0x002e;
                default: goto L_0x002d;
            }
        L_0x002d:
            goto L_0x005e
        L_0x002e:
            boolean r0 = r0.equals(r5)
            if (r0 == 0) goto L_0x005e
            r0 = r13
            goto L_0x005f
        L_0x0036:
            boolean r0 = r0.equals(r6)
            if (r0 == 0) goto L_0x005e
            r0 = r11
            goto L_0x005f
        L_0x003e:
            boolean r0 = r0.equals(r7)
            if (r0 == 0) goto L_0x005e
            r0 = r14
            goto L_0x005f
        L_0x0046:
            boolean r0 = r0.equals(r8)
            if (r0 == 0) goto L_0x005e
            r0 = r12
            goto L_0x005f
        L_0x004e:
            boolean r0 = r0.equals(r9)
            if (r0 == 0) goto L_0x005e
            r0 = 0
            goto L_0x005f
        L_0x0056:
            boolean r0 = r0.equals(r10)
            if (r0 == 0) goto L_0x005e
            r0 = r15
            goto L_0x005f
        L_0x005e:
            r0 = -1
        L_0x005f:
            if (r0 == 0) goto L_0x0078
            if (r0 == r11) goto L_0x0077
            if (r0 == r15) goto L_0x0073
            if (r0 == r14) goto L_0x0072
            if (r0 == r13) goto L_0x006e
            if (r0 == r12) goto L_0x006d
            goto L_0x00c5
        L_0x006d:
            return r1
        L_0x006e:
            r0 = 2131951881(0x7f130109, float:1.954019E38)
            return r0
        L_0x0072:
            return r3
        L_0x0073:
            r0 = 2131951883(0x7f13010b, float:1.9540193E38)
            return r0
        L_0x0077:
            return r4
        L_0x0078:
            r0 = 2131951890(0x7f130112, float:1.9540207E38)
            return r0
        L_0x007c:
            int r2 = r0.hashCode()
            switch(r2) {
                case -2128555920: goto L_0x00ac;
                case -1241052239: goto L_0x00a4;
                case 3175618: goto L_0x009c;
                case 115650329: goto L_0x0094;
                case 115650330: goto L_0x008c;
                case 767685465: goto L_0x0084;
                default: goto L_0x0083;
            }
        L_0x0083:
            goto L_0x00b4
        L_0x0084:
            boolean r0 = r0.equals(r5)
            if (r0 == 0) goto L_0x00b4
            r0 = r13
            goto L_0x00b5
        L_0x008c:
            boolean r0 = r0.equals(r6)
            if (r0 == 0) goto L_0x00b4
            r0 = r11
            goto L_0x00b5
        L_0x0094:
            boolean r0 = r0.equals(r7)
            if (r0 == 0) goto L_0x00b4
            r0 = r14
            goto L_0x00b5
        L_0x009c:
            boolean r0 = r0.equals(r8)
            if (r0 == 0) goto L_0x00b4
            r0 = r12
            goto L_0x00b5
        L_0x00a4:
            boolean r0 = r0.equals(r9)
            if (r0 == 0) goto L_0x00b4
            r0 = 0
            goto L_0x00b5
        L_0x00ac:
            boolean r0 = r0.equals(r10)
            if (r0 == 0) goto L_0x00b4
            r0 = r15
            goto L_0x00b5
        L_0x00b4:
            r0 = -1
        L_0x00b5:
            if (r0 == 0) goto L_0x00c4
            if (r0 == r11) goto L_0x00c4
            if (r0 == r15) goto L_0x00c3
            if (r0 == r14) goto L_0x00c3
            if (r0 == r13) goto L_0x00c2
            if (r0 == r12) goto L_0x00c2
            goto L_0x00c5
        L_0x00c2:
            return r1
        L_0x00c3:
            return r3
        L_0x00c4:
            return r4
        L_0x00c5:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.settings.SetupWizardUtils.getTheme(android.content.Intent):int");
    }

    public static int getTransparentTheme(Intent intent) {
        int theme = getTheme(intent);
        if (theme == C1715R.style.GlifV3Theme) {
            return 2131951892;
        }
        if (theme == 2131951890) {
            return 2131951891;
        }
        if (theme == C1715R.style.GlifV2Theme) {
            return 2131951885;
        }
        if (theme == 2131951881) {
            return 2131952001;
        }
        return theme == C1715R.style.GlifTheme ? 2131952002 : 2131951884;
    }

    public static void copySetupExtras(Intent intent, Intent intent2) {
        WizardManagerHelper.copyWizardManagerExtras(intent, intent2);
    }

    public static Bundle copyLifecycleExtra(Bundle bundle, Bundle bundle2) {
        for (String str : Arrays.asList(new String[]{WizardManagerHelper.EXTRA_IS_FIRST_RUN, WizardManagerHelper.EXTRA_IS_SETUP_FLOW})) {
            bundle2.putBoolean(str, bundle.getBoolean(str, false));
        }
        return bundle2;
    }
}
