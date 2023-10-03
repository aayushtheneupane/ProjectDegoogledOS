package com.android.settings.external;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Log;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class SignatureVerifier {
    private static final byte[] RELEASE_DIGEST_GMSCORE = {-16, -3, 108, 91, 65, 15, 37, -53, 37, -61, -75, 51, 70, -56, -105, 47, -82, 48, -8, -18, 116, 17, -33, -111, 4, Byte.MIN_VALUE, -83, 107, 45, 96, -37, -125};
    private static final byte[] RELEASE_DIGEST_TIPS = {14, 68, 121, -2, 25, 61, 1, -51, 70, 33, 95, -52, -48, -39, 35, 61, -20, 119, -2, -94, 89, -5, -52, -97, 9, 33, 25, -11, 10, -125, 114, -27};

    private static byte[] getDigestBytes(String str) {
        if (str.hashCode() != 40935373 || !str.equals("com.google.android.apps.tips")) {
            return RELEASE_DIGEST_GMSCORE;
        }
        return RELEASE_DIGEST_TIPS;
    }

    private static boolean isCertWhitelisted(String str, byte[] bArr) {
        try {
            return Arrays.equals(MessageDigest.getInstance("SHA-256").digest(bArr), getDigestBytes(str));
        } catch (NoSuchAlgorithmException e) {
            throw new SecurityException("Failed to obtain SHA-256 digest impl.", e);
        }
    }

    public static boolean isPackageWhitelisted(Context context, String str) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(str, 64);
            if (!verifyWhitelistedPackage(packageInfo.packageName)) {
                return false;
            }
            return isSignatureWhitelisted(packageInfo);
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("SignatureVerifier", "Could not find package name.", e);
            return false;
        }
    }

    private static boolean isSignatureWhitelisted(PackageInfo packageInfo) {
        Signature[] signatureArr = packageInfo.signatures;
        if (signatureArr.length != 1) {
            return false;
        }
        return isCertWhitelisted(packageInfo.packageName, signatureArr[0].toByteArray());
    }

    private static boolean verifyWhitelistedPackage(String str) {
        return "com.google.android.googlequicksearchbox".equals(str) || "com.google.android.gms".equals(str) || "com.google.android.apps.tips".equals(str);
    }
}
