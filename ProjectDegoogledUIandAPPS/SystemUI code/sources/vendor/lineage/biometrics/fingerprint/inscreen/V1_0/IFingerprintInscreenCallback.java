package vendor.lineage.biometrics.fingerprint.inscreen.V1_0;

import android.hidl.base.V1_0.DebugInfo;
import android.hidl.base.V1_0.IBase;
import android.os.HidlSupport;
import android.os.HwBinder;
import android.os.HwBlob;
import android.os.HwParcel;
import android.os.IHwBinder;
import android.os.IHwInterface;
import android.os.NativeHandle;
import android.os.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;

public interface IFingerprintInscreenCallback extends IBase {
    IHwBinder asBinder();

    void onFingerDown() throws RemoteException;

    void onFingerUp() throws RemoteException;

    public static abstract class Stub extends HwBinder implements IFingerprintInscreenCallback {
        public IHwBinder asBinder() {
            return this;
        }

        public void debug(NativeHandle nativeHandle, ArrayList<String> arrayList) {
        }

        public final String interfaceDescriptor() {
            return "vendor.lineage.biometrics.fingerprint.inscreen@1.0::IFingerprintInscreenCallback";
        }

        public final boolean linkToDeath(IHwBinder.DeathRecipient deathRecipient, long j) {
            return true;
        }

        public final void ping() {
        }

        public final void setHALInstrumentation() {
        }

        public final boolean unlinkToDeath(IHwBinder.DeathRecipient deathRecipient) {
            return true;
        }

        public final ArrayList<String> interfaceChain() {
            return new ArrayList<>(Arrays.asList(new String[]{"vendor.lineage.biometrics.fingerprint.inscreen@1.0::IFingerprintInscreenCallback", "android.hidl.base@1.0::IBase"}));
        }

        public final ArrayList<byte[]> getHashChain() {
            return new ArrayList<>(Arrays.asList(new byte[][]{new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new byte[]{-20, Byte.MAX_VALUE, -41, -98, -48, 45, -6, -123, -68, 73, -108, 38, -83, -82, 62, -66, 35, -17, 5, 36, -13, -51, 105, 87, 19, -109, 36, -72, 59, 24, -54, 76}}));
        }

        public final DebugInfo getDebugInfo() {
            DebugInfo debugInfo = new DebugInfo();
            debugInfo.pid = HidlSupport.getPidIfSharable();
            debugInfo.ptr = 0;
            debugInfo.arch = 0;
            return debugInfo;
        }

        public final void notifySyspropsChanged() {
            HwBinder.enableInstrumentation();
        }

        public IHwInterface queryLocalInterface(String str) {
            if ("vendor.lineage.biometrics.fingerprint.inscreen@1.0::IFingerprintInscreenCallback".equals(str)) {
                return this;
            }
            return null;
        }

        public String toString() {
            return interfaceDescriptor() + "@Stub";
        }

        public void onTransact(int i, HwParcel hwParcel, HwParcel hwParcel2, int i2) throws RemoteException {
            int i3 = 0;
            boolean z = true;
            if (i == 1) {
                if ((i2 & 1) != 0) {
                    i3 = 1;
                }
                if (i3 != 1) {
                    hwParcel2.writeStatus(Integer.MIN_VALUE);
                    hwParcel2.send();
                    return;
                }
                hwParcel.enforceInterface("vendor.lineage.biometrics.fingerprint.inscreen@1.0::IFingerprintInscreenCallback");
                onFingerDown();
            } else if (i != 2) {
                switch (i) {
                    case 256067662:
                        if ((i2 & 1) == 0) {
                            z = false;
                        }
                        if (z) {
                            hwParcel2.writeStatus(Integer.MIN_VALUE);
                            hwParcel2.send();
                            return;
                        }
                        hwParcel.enforceInterface("android.hidl.base@1.0::IBase");
                        ArrayList<String> interfaceChain = interfaceChain();
                        hwParcel2.writeStatus(0);
                        hwParcel2.writeStringVector(interfaceChain);
                        hwParcel2.send();
                        return;
                    case 256131655:
                        if ((i2 & 1) == 0) {
                            z = false;
                        }
                        if (z) {
                            hwParcel2.writeStatus(Integer.MIN_VALUE);
                            hwParcel2.send();
                            return;
                        }
                        hwParcel.enforceInterface("android.hidl.base@1.0::IBase");
                        debug(hwParcel.readNativeHandle(), hwParcel.readStringVector());
                        hwParcel2.writeStatus(0);
                        hwParcel2.send();
                        return;
                    case 256136003:
                        if ((i2 & 1) == 0) {
                            z = false;
                        }
                        if (z) {
                            hwParcel2.writeStatus(Integer.MIN_VALUE);
                            hwParcel2.send();
                            return;
                        }
                        hwParcel.enforceInterface("android.hidl.base@1.0::IBase");
                        String interfaceDescriptor = interfaceDescriptor();
                        hwParcel2.writeStatus(0);
                        hwParcel2.writeString(interfaceDescriptor);
                        hwParcel2.send();
                        return;
                    case 256398152:
                        if ((i2 & 1) == 0) {
                            z = false;
                        }
                        if (z) {
                            hwParcel2.writeStatus(Integer.MIN_VALUE);
                            hwParcel2.send();
                            return;
                        }
                        hwParcel.enforceInterface("android.hidl.base@1.0::IBase");
                        ArrayList<byte[]> hashChain = getHashChain();
                        hwParcel2.writeStatus(0);
                        HwBlob hwBlob = new HwBlob(16);
                        int size = hashChain.size();
                        hwBlob.putInt32(8, size);
                        hwBlob.putBool(12, false);
                        HwBlob hwBlob2 = new HwBlob(size * 32);
                        while (i3 < size) {
                            long j = (long) (i3 * 32);
                            byte[] bArr = hashChain.get(i3);
                            if (bArr == null || bArr.length != 32) {
                                throw new IllegalArgumentException("Array element is not of the expected length");
                            }
                            hwBlob2.putInt8Array(j, bArr);
                            i3++;
                        }
                        hwBlob.putBlob(0, hwBlob2);
                        hwParcel2.writeBuffer(hwBlob);
                        hwParcel2.send();
                        return;
                    case 256462420:
                        if ((i2 & 1) != 0) {
                            i3 = 1;
                        }
                        if (i3 != 1) {
                            hwParcel2.writeStatus(Integer.MIN_VALUE);
                            hwParcel2.send();
                            return;
                        }
                        hwParcel.enforceInterface("android.hidl.base@1.0::IBase");
                        setHALInstrumentation();
                        return;
                    case 256660548:
                        if ((i2 & 1) != 0) {
                            i3 = 1;
                        }
                        if (i3 != 0) {
                            hwParcel2.writeStatus(Integer.MIN_VALUE);
                            hwParcel2.send();
                            return;
                        }
                        return;
                    case 256921159:
                        if ((i2 & 1) == 0) {
                            z = false;
                        }
                        if (z) {
                            hwParcel2.writeStatus(Integer.MIN_VALUE);
                            hwParcel2.send();
                            return;
                        }
                        hwParcel.enforceInterface("android.hidl.base@1.0::IBase");
                        ping();
                        hwParcel2.writeStatus(0);
                        hwParcel2.send();
                        return;
                    case 257049926:
                        if ((i2 & 1) == 0) {
                            z = false;
                        }
                        if (z) {
                            hwParcel2.writeStatus(Integer.MIN_VALUE);
                            hwParcel2.send();
                            return;
                        }
                        hwParcel.enforceInterface("android.hidl.base@1.0::IBase");
                        DebugInfo debugInfo = getDebugInfo();
                        hwParcel2.writeStatus(0);
                        debugInfo.writeToParcel(hwParcel2);
                        hwParcel2.send();
                        return;
                    case 257120595:
                        if ((i2 & 1) != 0) {
                            i3 = 1;
                        }
                        if (i3 != 1) {
                            hwParcel2.writeStatus(Integer.MIN_VALUE);
                            hwParcel2.send();
                            return;
                        }
                        hwParcel.enforceInterface("android.hidl.base@1.0::IBase");
                        notifySyspropsChanged();
                        return;
                    case 257250372:
                        if ((i2 & 1) != 0) {
                            i3 = 1;
                        }
                        if (i3 != 0) {
                            hwParcel2.writeStatus(Integer.MIN_VALUE);
                            hwParcel2.send();
                            return;
                        }
                        return;
                    default:
                        return;
                }
            } else {
                if ((i2 & 1) != 0) {
                    i3 = 1;
                }
                if (i3 != 1) {
                    hwParcel2.writeStatus(Integer.MIN_VALUE);
                    hwParcel2.send();
                    return;
                }
                hwParcel.enforceInterface("vendor.lineage.biometrics.fingerprint.inscreen@1.0::IFingerprintInscreenCallback");
                onFingerUp();
            }
        }
    }
}
