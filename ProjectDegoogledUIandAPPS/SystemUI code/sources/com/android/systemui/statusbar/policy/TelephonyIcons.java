package com.android.systemui.statusbar.policy;

import com.android.systemui.C1776R$drawable;
import com.android.systemui.C1784R$string;
import com.android.systemui.statusbar.policy.MobileSignalController;
import java.util.HashMap;
import java.util.Map;

class TelephonyIcons {
    static final MobileSignalController.MobileIconGroup CARRIER_NETWORK_CHANGE;
    static final MobileSignalController.MobileIconGroup DATA_DISABLED;

    /* renamed from: E */
    static final MobileSignalController.MobileIconGroup f72E;
    static final int FLIGHT_MODE_ICON = C1776R$drawable.stat_sys_airplane_mode;
    static final MobileSignalController.MobileIconGroup FOUR_G;
    static final MobileSignalController.MobileIconGroup FOUR_G_PLUS;

    /* renamed from: G */
    static final MobileSignalController.MobileIconGroup f73G;

    /* renamed from: H */
    static final MobileSignalController.MobileIconGroup f74H;
    static final MobileSignalController.MobileIconGroup H_PLUS;
    static final int ICON_1X = C1776R$drawable.ic_1x_mobiledata;
    static final int ICON_3G = C1776R$drawable.ic_3g_mobiledata;
    static final int ICON_4G = C1776R$drawable.ic_4g_mobiledata;
    static final int ICON_4G_PLUS = C1776R$drawable.ic_4g_plus_mobiledata;
    static final int ICON_5G = C1776R$drawable.ic_5g_mobiledata;
    static final int ICON_5G_E = C1776R$drawable.ic_5g_e_mobiledata;
    static final int ICON_5G_PLUS = C1776R$drawable.ic_5g_plus_mobiledata;
    static final int ICON_E = C1776R$drawable.ic_e_mobiledata;
    static final int ICON_G = C1776R$drawable.ic_g_mobiledata;
    static final int ICON_H = C1776R$drawable.ic_h_mobiledata;
    static final int ICON_H_PLUS = C1776R$drawable.ic_h_plus_mobiledata;
    static final int ICON_LTE = C1776R$drawable.ic_lte_mobiledata;
    static final int ICON_LTE_PLUS = C1776R$drawable.ic_lte_plus_mobiledata;
    static final Map<String, MobileSignalController.MobileIconGroup> ICON_NAME_TO_ICON = new HashMap();
    static final MobileSignalController.MobileIconGroup LTE;
    static final MobileSignalController.MobileIconGroup LTE_CA_5G_E;
    static final MobileSignalController.MobileIconGroup LTE_PLUS;
    static final MobileSignalController.MobileIconGroup NOT_DEFAULT_DATA;
    static final MobileSignalController.MobileIconGroup NR_5G;
    static final MobileSignalController.MobileIconGroup NR_5G_PLUS;
    static final MobileSignalController.MobileIconGroup ONE_X;
    static final MobileSignalController.MobileIconGroup THREE_G;
    static final MobileSignalController.MobileIconGroup UNKNOWN;
    static final MobileSignalController.MobileIconGroup WFC;

    static {
        int[] iArr = AccessibilityContentDescriptions.PHONE_SIGNAL_STRENGTH;
        CARRIER_NETWORK_CHANGE = new MobileSignalController.MobileIconGroup("CARRIER_NETWORK_CHANGE", (int[][]) null, (int[][]) null, iArr, 0, 0, 0, 0, iArr[0], C1784R$string.carrier_network_change_mode, 0, false);
        int[] iArr2 = AccessibilityContentDescriptions.PHONE_SIGNAL_STRENGTH;
        THREE_G = new MobileSignalController.MobileIconGroup("3G", (int[][]) null, (int[][]) null, iArr2, 0, 0, 0, 0, iArr2[0], C1784R$string.data_connection_3g, ICON_3G, true);
        int[] iArr3 = AccessibilityContentDescriptions.PHONE_SIGNAL_STRENGTH;
        WFC = new MobileSignalController.MobileIconGroup("WFC", (int[][]) null, (int[][]) null, iArr3, 0, 0, 0, 0, iArr3[0], 0, 0, false);
        int[] iArr4 = AccessibilityContentDescriptions.PHONE_SIGNAL_STRENGTH;
        UNKNOWN = new MobileSignalController.MobileIconGroup("Unknown", (int[][]) null, (int[][]) null, iArr4, 0, 0, 0, 0, iArr4[0], 0, 0, false);
        int[] iArr5 = AccessibilityContentDescriptions.PHONE_SIGNAL_STRENGTH;
        f72E = new MobileSignalController.MobileIconGroup("E", (int[][]) null, (int[][]) null, iArr5, 0, 0, 0, 0, iArr5[0], C1784R$string.data_connection_edge, ICON_E, false);
        int[] iArr6 = AccessibilityContentDescriptions.PHONE_SIGNAL_STRENGTH;
        ONE_X = new MobileSignalController.MobileIconGroup("1X", (int[][]) null, (int[][]) null, iArr6, 0, 0, 0, 0, iArr6[0], C1784R$string.data_connection_cdma, ICON_1X, true);
        int[] iArr7 = AccessibilityContentDescriptions.PHONE_SIGNAL_STRENGTH;
        f73G = new MobileSignalController.MobileIconGroup("G", (int[][]) null, (int[][]) null, iArr7, 0, 0, 0, 0, iArr7[0], C1784R$string.data_connection_gprs, ICON_G, false);
        int[] iArr8 = AccessibilityContentDescriptions.PHONE_SIGNAL_STRENGTH;
        f74H = new MobileSignalController.MobileIconGroup("H", (int[][]) null, (int[][]) null, iArr8, 0, 0, 0, 0, iArr8[0], C1784R$string.data_connection_3_5g, ICON_H, false);
        int[] iArr9 = AccessibilityContentDescriptions.PHONE_SIGNAL_STRENGTH;
        H_PLUS = new MobileSignalController.MobileIconGroup("H+", (int[][]) null, (int[][]) null, iArr9, 0, 0, 0, 0, iArr9[0], C1784R$string.data_connection_3_5g_plus, ICON_H_PLUS, false);
        int[] iArr10 = AccessibilityContentDescriptions.PHONE_SIGNAL_STRENGTH;
        FOUR_G = new MobileSignalController.MobileIconGroup("4G", (int[][]) null, (int[][]) null, iArr10, 0, 0, 0, 0, iArr10[0], C1784R$string.data_connection_4g, ICON_4G, true);
        int[] iArr11 = AccessibilityContentDescriptions.PHONE_SIGNAL_STRENGTH;
        FOUR_G_PLUS = new MobileSignalController.MobileIconGroup("4G+", (int[][]) null, (int[][]) null, iArr11, 0, 0, 0, 0, iArr11[0], C1784R$string.data_connection_4g_plus, ICON_4G_PLUS, true);
        int[] iArr12 = AccessibilityContentDescriptions.PHONE_SIGNAL_STRENGTH;
        LTE = new MobileSignalController.MobileIconGroup("LTE", (int[][]) null, (int[][]) null, iArr12, 0, 0, 0, 0, iArr12[0], C1784R$string.data_connection_lte, ICON_LTE, true);
        int[] iArr13 = AccessibilityContentDescriptions.PHONE_SIGNAL_STRENGTH;
        LTE_PLUS = new MobileSignalController.MobileIconGroup("LTE+", (int[][]) null, (int[][]) null, iArr13, 0, 0, 0, 0, iArr13[0], C1784R$string.data_connection_lte_plus, ICON_LTE_PLUS, true);
        int[] iArr14 = AccessibilityContentDescriptions.PHONE_SIGNAL_STRENGTH;
        LTE_CA_5G_E = new MobileSignalController.MobileIconGroup("5Ge", (int[][]) null, (int[][]) null, iArr14, 0, 0, 0, 0, iArr14[0], C1784R$string.data_connection_5ge_html, ICON_5G_E, true);
        int[] iArr15 = AccessibilityContentDescriptions.PHONE_SIGNAL_STRENGTH;
        NR_5G = new MobileSignalController.MobileIconGroup("5G", (int[][]) null, (int[][]) null, iArr15, 0, 0, 0, 0, iArr15[0], C1784R$string.data_connection_5g, ICON_5G, true);
        int[] iArr16 = AccessibilityContentDescriptions.PHONE_SIGNAL_STRENGTH;
        NR_5G_PLUS = new MobileSignalController.MobileIconGroup("5G_PLUS", (int[][]) null, (int[][]) null, iArr16, 0, 0, 0, 0, iArr16[0], C1784R$string.data_connection_5g_plus, ICON_5G_PLUS, true);
        int[] iArr17 = AccessibilityContentDescriptions.PHONE_SIGNAL_STRENGTH;
        DATA_DISABLED = new MobileSignalController.MobileIconGroup("DataDisabled", (int[][]) null, (int[][]) null, iArr17, 0, 0, 0, 0, iArr17[0], C1784R$string.cell_data_off_content_description, 0, false);
        int[] iArr18 = AccessibilityContentDescriptions.PHONE_SIGNAL_STRENGTH;
        NOT_DEFAULT_DATA = new MobileSignalController.MobileIconGroup("NotDefaultData", (int[][]) null, (int[][]) null, iArr18, 0, 0, 0, 0, iArr18[0], C1784R$string.not_default_data_content_description, 0, false);
        ICON_NAME_TO_ICON.put("carrier_network_change", CARRIER_NETWORK_CHANGE);
        ICON_NAME_TO_ICON.put("3g", THREE_G);
        ICON_NAME_TO_ICON.put("wfc", WFC);
        ICON_NAME_TO_ICON.put("unknown", UNKNOWN);
        ICON_NAME_TO_ICON.put("e", f72E);
        ICON_NAME_TO_ICON.put("1x", ONE_X);
        ICON_NAME_TO_ICON.put("g", f73G);
        ICON_NAME_TO_ICON.put("h", f74H);
        ICON_NAME_TO_ICON.put("h+", H_PLUS);
        ICON_NAME_TO_ICON.put("4g", FOUR_G);
        ICON_NAME_TO_ICON.put("4g+", FOUR_G_PLUS);
        ICON_NAME_TO_ICON.put("5ge", LTE_CA_5G_E);
        ICON_NAME_TO_ICON.put("lte", LTE);
        ICON_NAME_TO_ICON.put("lte+", LTE_PLUS);
        ICON_NAME_TO_ICON.put("5g", NR_5G);
        ICON_NAME_TO_ICON.put("5g_plus", NR_5G_PLUS);
        ICON_NAME_TO_ICON.put("datadisable", DATA_DISABLED);
        ICON_NAME_TO_ICON.put("notdefaultdata", NOT_DEFAULT_DATA);
    }
}
