package com.android.contacts.util;

import android.content.Intent;
import android.net.Uri;

public class StructuredPostalUtils {
    public static Intent getViewPostalAddressIntent(String str) {
        return new Intent("android.intent.action.VIEW", getPostalAddressUri(str));
    }

    public static Uri getPostalAddressUri(String str) {
        return Uri.parse("geo:0,0?q=" + Uri.encode(str));
    }

    public static Intent getViewPostalAddressDirectionsIntent(String str) {
        return new Intent("android.intent.action.VIEW", getPostalAddressDirectionsUri(str));
    }

    public static Uri getPostalAddressDirectionsUri(String str) {
        return Uri.parse("https://maps.google.com/maps?daddr=" + Uri.encode(str));
    }
}
