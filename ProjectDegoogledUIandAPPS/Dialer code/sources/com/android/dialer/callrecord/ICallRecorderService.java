package com.android.dialer.callrecord;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface ICallRecorderService extends IInterface {

    public static abstract class Stub extends Binder implements ICallRecorderService {

        private static class Proxy implements ICallRecorderService {
            public static ICallRecorderService sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public CallRecording getActiveRecording() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.android.dialer.callrecord.ICallRecorderService");
                    if (!this.mRemote.transact(4, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return sDefaultImpl.getActiveRecording();
                    }
                    obtain2.readException();
                    CallRecording createFromParcel = obtain2.readInt() != 0 ? CallRecording.CREATOR.createFromParcel(obtain2) : null;
                    obtain2.recycle();
                    obtain.recycle();
                    return createFromParcel;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean isRecording() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.android.dialer.callrecord.ICallRecorderService");
                    boolean z = false;
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return sDefaultImpl.isRecording();
                    }
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean startRecording(String str, long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.android.dialer.callrecord.ICallRecorderService");
                    obtain.writeString(str);
                    obtain.writeLong(j);
                    boolean z = false;
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return sDefaultImpl.startRecording(str, j);
                    }
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public CallRecording stopRecording() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.android.dialer.callrecord.ICallRecorderService");
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return sDefaultImpl.stopRecording();
                    }
                    obtain2.readException();
                    CallRecording createFromParcel = obtain2.readInt() != 0 ? CallRecording.CREATOR.createFromParcel(obtain2) : null;
                    obtain2.recycle();
                    obtain.recycle();
                    return createFromParcel;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, "com.android.dialer.callrecord.ICallRecorderService");
        }

        public static ICallRecorderService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.android.dialer.callrecord.ICallRecorderService");
            if (queryLocalInterface == null || !(queryLocalInterface instanceof ICallRecorderService)) {
                return new Proxy(iBinder);
            }
            return (ICallRecorderService) queryLocalInterface;
        }

        public static ICallRecorderService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            int i3 = 0;
            if (i == 1) {
                parcel.enforceInterface("com.android.dialer.callrecord.ICallRecorderService");
                boolean startRecording = startRecording(parcel.readString(), parcel.readLong());
                parcel2.writeNoException();
                if (startRecording) {
                    i3 = 1;
                }
                parcel2.writeInt(i3);
                return true;
            } else if (i == 2) {
                parcel.enforceInterface("com.android.dialer.callrecord.ICallRecorderService");
                CallRecording stopRecording = stopRecording();
                parcel2.writeNoException();
                if (stopRecording != null) {
                    parcel2.writeInt(1);
                    stopRecording.writeToParcel(parcel2, 1);
                } else {
                    parcel2.writeInt(0);
                }
                return true;
            } else if (i == 3) {
                parcel.enforceInterface("com.android.dialer.callrecord.ICallRecorderService");
                boolean isRecording = isRecording();
                parcel2.writeNoException();
                if (isRecording) {
                    i3 = 1;
                }
                parcel2.writeInt(i3);
                return true;
            } else if (i == 4) {
                parcel.enforceInterface("com.android.dialer.callrecord.ICallRecorderService");
                CallRecording activeRecording = getActiveRecording();
                parcel2.writeNoException();
                if (activeRecording != null) {
                    parcel2.writeInt(1);
                    activeRecording.writeToParcel(parcel2, 1);
                } else {
                    parcel2.writeInt(0);
                }
                return true;
            } else if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                parcel2.writeString("com.android.dialer.callrecord.ICallRecorderService");
                return true;
            }
        }
    }

    CallRecording getActiveRecording() throws RemoteException;

    boolean isRecording() throws RemoteException;

    boolean startRecording(String str, long j) throws RemoteException;

    CallRecording stopRecording() throws RemoteException;
}
