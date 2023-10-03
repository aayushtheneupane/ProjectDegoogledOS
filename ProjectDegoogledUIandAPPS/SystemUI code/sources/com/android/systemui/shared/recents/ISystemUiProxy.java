package com.android.systemui.shared.recents;

import android.graphics.Rect;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.view.MotionEvent;

public interface ISystemUiProxy extends IInterface {
    Rect getNonMinimizedSplitScreenSecondaryBounds() throws RemoteException;

    Bundle monitorGestureInput(String str, int i) throws RemoteException;

    void notifyAccessibilityButtonClicked(int i) throws RemoteException;

    void notifyAccessibilityButtonLongClicked() throws RemoteException;

    void onAssistantGestureCompletion(float f) throws RemoteException;

    void onAssistantProgress(float f) throws RemoteException;

    void onOverviewShown(boolean z) throws RemoteException;

    void onSplitScreenInvoked() throws RemoteException;

    void onStatusBarMotionEvent(MotionEvent motionEvent) throws RemoteException;

    void setBackButtonAlpha(float f, boolean z) throws RemoteException;

    void setNavBarButtonAlpha(float f, boolean z) throws RemoteException;

    void startAssistant(Bundle bundle) throws RemoteException;

    void startScreenPinning(int i) throws RemoteException;

    void stopScreenPinning() throws RemoteException;

    public static abstract class Stub extends Binder implements ISystemUiProxy {
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, "com.android.systemui.shared.recents.ISystemUiProxy");
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v3, resolved type: android.view.MotionEvent} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v6, resolved type: android.os.Bundle} */
        /* JADX WARNING: type inference failed for: r0v2 */
        /* JADX WARNING: type inference failed for: r0v9 */
        /* JADX WARNING: type inference failed for: r0v10 */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onTransact(int r5, android.os.Parcel r6, android.os.Parcel r7, int r8) throws android.os.RemoteException {
            /*
                r4 = this;
                r0 = 2
                r1 = 1
                java.lang.String r2 = "com.android.systemui.shared.recents.ISystemUiProxy"
                if (r5 == r0) goto L_0x0107
                r0 = 1598968902(0x5f4e5446, float:1.4867585E19)
                if (r5 == r0) goto L_0x0103
                r0 = 0
                r3 = 0
                switch(r5) {
                    case 6: goto L_0x00f9;
                    case 7: goto L_0x00e8;
                    case 8: goto L_0x00d1;
                    case 9: goto L_0x00bc;
                    case 10: goto L_0x00a3;
                    default: goto L_0x0010;
                }
            L_0x0010:
                switch(r5) {
                    case 13: goto L_0x0095;
                    case 14: goto L_0x007c;
                    case 15: goto L_0x005d;
                    case 16: goto L_0x004f;
                    case 17: goto L_0x0045;
                    case 18: goto L_0x003b;
                    case 19: goto L_0x002d;
                    case 20: goto L_0x0018;
                    default: goto L_0x0013;
                }
            L_0x0013:
                boolean r4 = super.onTransact(r5, r6, r7, r8)
                return r4
            L_0x0018:
                r6.enforceInterface(r2)
                float r5 = r6.readFloat()
                int r6 = r6.readInt()
                if (r6 == 0) goto L_0x0026
                r3 = r1
            L_0x0026:
                r4.setNavBarButtonAlpha(r5, r3)
                r7.writeNoException()
                return r1
            L_0x002d:
                r6.enforceInterface(r2)
                float r5 = r6.readFloat()
                r4.onAssistantGestureCompletion(r5)
                r7.writeNoException()
                return r1
            L_0x003b:
                r6.enforceInterface(r2)
                r4.stopScreenPinning()
                r7.writeNoException()
                return r1
            L_0x0045:
                r6.enforceInterface(r2)
                r4.notifyAccessibilityButtonLongClicked()
                r7.writeNoException()
                return r1
            L_0x004f:
                r6.enforceInterface(r2)
                int r5 = r6.readInt()
                r4.notifyAccessibilityButtonClicked(r5)
                r7.writeNoException()
                return r1
            L_0x005d:
                r6.enforceInterface(r2)
                java.lang.String r5 = r6.readString()
                int r6 = r6.readInt()
                android.os.Bundle r4 = r4.monitorGestureInput(r5, r6)
                r7.writeNoException()
                if (r4 == 0) goto L_0x0078
                r7.writeInt(r1)
                r4.writeToParcel(r7, r1)
                goto L_0x007b
            L_0x0078:
                r7.writeInt(r3)
            L_0x007b:
                return r1
            L_0x007c:
                r6.enforceInterface(r2)
                int r5 = r6.readInt()
                if (r5 == 0) goto L_0x008e
                android.os.Parcelable$Creator r5 = android.os.Bundle.CREATOR
                java.lang.Object r5 = r5.createFromParcel(r6)
                r0 = r5
                android.os.Bundle r0 = (android.os.Bundle) r0
            L_0x008e:
                r4.startAssistant(r0)
                r7.writeNoException()
                return r1
            L_0x0095:
                r6.enforceInterface(r2)
                float r5 = r6.readFloat()
                r4.onAssistantProgress(r5)
                r7.writeNoException()
                return r1
            L_0x00a3:
                r6.enforceInterface(r2)
                int r5 = r6.readInt()
                if (r5 == 0) goto L_0x00b5
                android.os.Parcelable$Creator r5 = android.view.MotionEvent.CREATOR
                java.lang.Object r5 = r5.createFromParcel(r6)
                r0 = r5
                android.view.MotionEvent r0 = (android.view.MotionEvent) r0
            L_0x00b5:
                r4.onStatusBarMotionEvent(r0)
                r7.writeNoException()
                return r1
            L_0x00bc:
                r6.enforceInterface(r2)
                float r5 = r6.readFloat()
                int r6 = r6.readInt()
                if (r6 == 0) goto L_0x00ca
                r3 = r1
            L_0x00ca:
                r4.setBackButtonAlpha(r5, r3)
                r7.writeNoException()
                return r1
            L_0x00d1:
                r6.enforceInterface(r2)
                android.graphics.Rect r4 = r4.getNonMinimizedSplitScreenSecondaryBounds()
                r7.writeNoException()
                if (r4 == 0) goto L_0x00e4
                r7.writeInt(r1)
                r4.writeToParcel(r7, r1)
                goto L_0x00e7
            L_0x00e4:
                r7.writeInt(r3)
            L_0x00e7:
                return r1
            L_0x00e8:
                r6.enforceInterface(r2)
                int r5 = r6.readInt()
                if (r5 == 0) goto L_0x00f2
                r3 = r1
            L_0x00f2:
                r4.onOverviewShown(r3)
                r7.writeNoException()
                return r1
            L_0x00f9:
                r6.enforceInterface(r2)
                r4.onSplitScreenInvoked()
                r7.writeNoException()
                return r1
            L_0x0103:
                r7.writeString(r2)
                return r1
            L_0x0107:
                r6.enforceInterface(r2)
                int r5 = r6.readInt()
                r4.startScreenPinning(r5)
                r7.writeNoException()
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.shared.recents.ISystemUiProxy.Stub.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
        }
    }
}
