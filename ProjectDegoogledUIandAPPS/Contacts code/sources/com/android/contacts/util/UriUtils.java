package com.android.contacts.util;

import android.net.Uri;
import java.util.List;

public class UriUtils {
    public static boolean areEqual(Uri uri, Uri uri2) {
        if (uri == null && uri2 == null) {
            return true;
        }
        if (uri == null || uri2 == null) {
            return false;
        }
        return uri.equals(uri2);
    }

    public static Uri parseUriOrNull(String str) {
        if (str == null) {
            return null;
        }
        return Uri.parse(str);
    }

    public static boolean isEncodedContactUri(Uri uri) {
        String lastPathSegment;
        if (uri == null || (lastPathSegment = uri.getLastPathSegment()) == null) {
            return false;
        }
        return lastPathSegment.equals("encoded");
    }

    public static String getLookupKeyFromUri(Uri uri) {
        if (uri == null || isEncodedContactUri(uri)) {
            return null;
        }
        List<String> pathSegments = uri.getPathSegments();
        if (pathSegments.size() < 3) {
            return null;
        }
        return Uri.encode(pathSegments.get(2));
    }
}
