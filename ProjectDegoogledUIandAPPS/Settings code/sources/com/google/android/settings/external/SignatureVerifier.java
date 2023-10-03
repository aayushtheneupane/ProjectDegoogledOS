package com.google.android.settings.external;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.text.TextUtils;
import android.util.Log;
import com.android.internal.util.ArrayUtils;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class SignatureVerifier {
    private static final byte[] RELEASE_DIGEST_GMSCORE = {-16, -3, 108, 91, 65, 15, 37, -53, 37, -61, -75, 51, 70, -56, -105, 47, -82, 48, -8, -18, 116, 17, -33, -111, 4, Byte.MIN_VALUE, -83, 107, 45, 96, -37, -125};
    private static final byte[] RELEASE_DIGEST_TIPS = {14, 68, 121, -2, 25, 61, 1, -51, 70, 33, 95, -52, -48, -39, 35, 61, -20, 119, -2, -94, 89, -5, -52, -97, 9, 33, 25, -11, 10, -125, 114, -27};

    public static String verifyCallerIsWhitelisted(Context context, int i) throws SecurityException {
        String isUidWhitelisted = isUidWhitelisted(context, i);
        if (!TextUtils.isEmpty(isUidWhitelisted)) {
            return isUidWhitelisted;
        }
        throw new SecurityException("UID is not Google Signed");
    }

    private static String isUidWhitelisted(Context context, int i) {
        String[] packagesForUid = context.getPackageManager().getPackagesForUid(i);
        if (ArrayUtils.isEmpty(packagesForUid)) {
            return null;
        }
        for (String str : packagesForUid) {
            if (isPackageWhitelisted(context, str)) {
                return str;
            }
        }
        return null;
    }

    public static boolean isPackageWhitelisted(Context context, String str) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(str, 64);
            String str2 = packageInfo.packageName;
            if (verifyWhitelistedPackage(str2)) {
                return isSignatureWhitelisted(packageInfo);
            }
            Log.e("SignatureVerifier", "Package name: " + str2 + " is not whitelisted.");
            return false;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("SignatureVerifier", "Could not find package name.", e);
            return false;
        }
    }

    private static boolean isSignatureWhitelisted(PackageInfo packageInfo) {
        Signature[] signatureArr = packageInfo.signatures;
        if (signatureArr.length == 1) {
            return isCertWhitelisted(packageInfo.packageName, signatureArr[0].toByteArray());
        }
        Log.w("SignatureVerifier", "Package has more than one signature.");
        return false;
    }

    private static boolean isCertWhitelisted(String str, byte[] bArr) {
        try {
            byte[] digest = MessageDigest.getInstance("SHA-256").digest(bArr);
            if (Log.isLoggable("SignatureVerifier", 3)) {
                Log.d("SignatureVerifier", "Checking cert for ");
            }
            return Arrays.equals(digest, getDigestBytes(str));
        } catch (NoSuchAlgorithmException e) {
            throw new SecurityException("Failed to obtain SHA-256 digest impl.", e);
        }
    }

    private static boolean verifyWhitelistedPackage(String str) {
        return "com.google.android.googlequicksearchbox".equals(str) || "com.google.android.gms".equals(str) || "com.google.android.apps.tips".equals(str);
    }

    private static byte[] getDigestBytes(String str) {
        if (((str.hashCode() != 40935373 || !str.equals("com.google.android.apps.tips")) ? (char) 65535 : 0) != 0) {
            return RELEASE_DIGEST_GMSCORE;
        }
        return RELEASE_DIGEST_TIPS;
    }
}
