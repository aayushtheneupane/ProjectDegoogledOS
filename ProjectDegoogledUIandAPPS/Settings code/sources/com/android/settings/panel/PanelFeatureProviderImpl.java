package com.android.settings.panel;

import android.content.Context;

public class PanelFeatureProviderImpl implements PanelFeatureProvider {
    public PanelContent getPanel(Context context, String str, String str2) {
        if (context == null) {
            return null;
        }
        char c = 65535;
        switch (str.hashCode()) {
            case -1722354363:
                if (str.equals("android.settings.panel.action.MOBILE_DATA")) {
                    c = 5;
                    break;
                }
                break;
            case -1419278804:
                if (str.equals("android.settings.panel.action.BLUETOOTH")) {
                    c = 6;
                    break;
                }
                break;
            case 66351017:
                if (str.equals("android.settings.panel.action.NFC")) {
                    c = 2;
                    break;
                }
                break;
            case 464243859:
                if (str.equals("android.settings.panel.action.INTERNET_CONNECTIVITY")) {
                    c = 0;
                    break;
                }
                break;
            case 1215888444:
                if (str.equals("android.settings.panel.action.VOLUME")) {
                    c = 4;
                    break;
                }
                break;
            case 1827023883:
                if (str.equals("com.android.settings.panel.action.MEDIA_OUTPUT")) {
                    c = 1;
                    break;
                }
                break;
            case 2057152695:
                if (str.equals("android.settings.panel.action.WIFI")) {
                    c = 3;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return InternetConnectivityPanel.create(context);
            case 1:
                return MediaOutputPanel.create(context, str2);
            case 2:
                return NfcPanel.create(context);
            case 3:
                return WifiPanel.create(context);
            case 4:
                return VolumePanel.create(context);
            case 5:
                return MobileDataPanel.create(context);
            case 6:
                return BluetoothPanel.create(context);
            default:
                throw new IllegalStateException("No matching panel for: " + str);
        }
    }
}
