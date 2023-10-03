package com.android.systemui.statusbar.policy;

import com.android.systemui.C1776R$drawable;

public class WifiIcons {
    public static final int[][] QS_WIFI_SIGNAL_STRENGTH = {WIFI_NO_INTERNET_ICONS, WIFI_FULL_ICONS};
    static final int[] WIFI_FULL_ICONS = {17302858, 17302859, 17302860, 17302861, 17302862};
    static final int WIFI_LEVEL_COUNT = WIFI_SIGNAL_STRENGTH[0].length;
    private static final int[] WIFI_NO_INTERNET_ICONS = {C1776R$drawable.ic_qs_wifi_0, C1776R$drawable.ic_qs_wifi_1, C1776R$drawable.ic_qs_wifi_2, C1776R$drawable.ic_qs_wifi_3, C1776R$drawable.ic_qs_wifi_4};
    static final int[][] WIFI_SIGNAL_STRENGTH = QS_WIFI_SIGNAL_STRENGTH;
}
