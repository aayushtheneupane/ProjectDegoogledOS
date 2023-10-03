package com.android.dialer.oem;

import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.Signature;
import com.android.dialer.common.LogUtil;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

final class CequintPackageUtils {
    private static final List<byte[]> callerIdFingerprints = new ArrayList();

    static {
        callerIdFingerprints.add(0, new byte[]{26, 12, -8, -115, 91, -30, 106, -19, 80, -123, -2, -120, -96, -98, -20, 37, 30, -54, 22, -105, 80, -38, 33, -52, 24, -55, -104, -81, 38, -51, 6, 113});
        callerIdFingerprints.add(1, new byte[]{-54, 47, -82, -12, 9, -17, 76, 121, -8, 76, -40, -105, -65, 26, 21, 15, -16, 94, 84, 116, -74, 74, -54, -51, 5, 126, 30, -104, -58, 31, 92, 69});
        callerIdFingerprints.add(2, new byte[]{-26, 122, 14, -80, 118, 78, -61, 40, -73, -63, 27, 27, -48, -124, 40, -90, 22, -39, -13, -21, -80, 32, -89, -40, -33, 20, 114, -127, 76, 19, -13, -55});
        callerIdFingerprints.add(3, new byte[]{26, -70, -94, -124, 12, 97, -106, 9, -111, 94, -111, -107, 61, 41, 60, -112, -20, -76, -119, 29, -64, -79, 35, 88, -104, -21, -26, -44, 9, -27, -114, -99});
        callerIdFingerprints.add(4, new byte[]{39, -7, 109, -70, -73, 123, 49, -10, -107, 62, 76, -46, -62, -34, -2, 21, -11, -41, -57, -113, 7, 61, -41, 22, 32, 24, -17, 71, 107, 9, 124, 52});
    }

    @SuppressLint({"PackageManagerGetSignatures"})
    static boolean isCallerIdInstalled(PackageManager packageManager, String str) {
        byte[] bArr;
        if (packageManager == null) {
            LogUtil.m9i("CequintPackageUtils.isCallerIdInstalled", "failed to get PackageManager!", new Object[0]);
            return false;
        }
        ProviderInfo resolveContentProvider = packageManager.resolveContentProvider(str, 128);
        if (resolveContentProvider == null) {
            new Object[1][0] = str;
            return false;
        }
        String str2 = resolveContentProvider.packageName;
        if (str2 == null) {
            LogUtil.m10w("CequintPackageUtils.isCallerIdInstalled", "can't get valid package name.", new Object[0]);
            return false;
        }
        LogUtil.m9i("CequintPackageUtils.isCallerIdInstalled", GeneratedOutlineSupport.outline8("content provider package name : ", str2), new Object[0]);
        try {
            Signature[] signatureArr = packageManager.getPackageInfo(str2, 64).signatures;
            if (signatureArr.length > 1) {
                LogUtil.m10w("CequintPackageUtils.isCallerIdInstalled", "package has more than one signature.", new Object[0]);
                return false;
            }
            byte[] byteArray = signatureArr[0].toByteArray();
            try {
                MessageDigest instance = MessageDigest.getInstance("SHA256", "BC");
                instance.update(byteArray);
                bArr = instance.digest();
            } catch (NoSuchAlgorithmException | NoSuchProviderException e) {
                LogUtil.m7e("CequintPackageUtils.getSHA256", "", e);
                bArr = null;
            }
            int i = 0;
            while (i < callerIdFingerprints.size()) {
                if (Arrays.equals(callerIdFingerprints.get(i), bArr)) {
                    Object[] objArr = new Object[1];
                    objArr[0] = i != 0 ? i != 1 ? i != 2 ? "SprintPackage" : "VZWPackage" : "2048-signed" : "1024-signed";
                    LogUtil.m9i("CequintPackageUtils.isCallerIdInstalled", "this is %s Caller Name ID APK.", objArr);
                    return true;
                }
                i++;
            }
            LogUtil.m10w("CequintPackageUtils.isCallerIdInstalled", "signature check failed for package: %s", str2);
            return false;
        } catch (PackageManager.NameNotFoundException e2) {
            LogUtil.m8e("CequintPackageUtils.isCallerIdInstalled", "couldn't find package info for the package: %s", str2, e2);
        }
    }
}
