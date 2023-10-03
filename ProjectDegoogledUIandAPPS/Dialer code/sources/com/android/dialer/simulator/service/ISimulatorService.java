package com.android.dialer.simulator.service;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.android.dialer.searchfragment.list.NewSearchFragment;
import com.android.dialer.simulator.service.SimulatorService;

public interface ISimulatorService extends IInterface {

    public static abstract class Stub extends Binder implements ISimulatorService {
        public Stub() {
            attachInterface(this, "com.android.dialer.simulator.service.ISimulatorService");
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1598968902) {
                switch (i) {
                    case NewSearchFragment.READ_CONTACTS_PERMISSION_REQUEST_CODE:
                        parcel.enforceInterface("com.android.dialer.simulator.service.ISimulatorService");
                        ((SimulatorService.C05611) this).makeIncomingCall(parcel.readString(), parcel.readInt());
                        parcel2.writeNoException();
                        return true;
                    case 2:
                        parcel.enforceInterface("com.android.dialer.simulator.service.ISimulatorService");
                        ((SimulatorService.C05611) this).makeOutgoingCall(parcel.readString(), parcel.readInt());
                        parcel2.writeNoException();
                        return true;
                    case 3:
                        parcel.enforceInterface("com.android.dialer.simulator.service.ISimulatorService");
                        ((SimulatorService.C05611) this).makeIncomingEnrichedCall();
                        parcel2.writeNoException();
                        return true;
                    case 4:
                        parcel.enforceInterface("com.android.dialer.simulator.service.ISimulatorService");
                        ((SimulatorService.C05611) this).makeOutgoingEnrichedCall();
                        parcel2.writeNoException();
                        return true;
                    case 5:
                        parcel.enforceInterface("com.android.dialer.simulator.service.ISimulatorService");
                        ((SimulatorService.C05611) this).populateMissedCall(parcel.readInt());
                        parcel2.writeNoException();
                        return true;
                    case 6:
                        parcel.enforceInterface("com.android.dialer.simulator.service.ISimulatorService");
                        ((SimulatorService.C05611) this).populateDataBase();
                        parcel2.writeNoException();
                        return true;
                    case 7:
                        parcel.enforceInterface("com.android.dialer.simulator.service.ISimulatorService");
                        ((SimulatorService.C05611) this).cleanDataBase();
                        parcel2.writeNoException();
                        return true;
                    case 8:
                        parcel.enforceInterface("com.android.dialer.simulator.service.ISimulatorService");
                        ((SimulatorService.C05611) this).enableSimulatorMode();
                        parcel2.writeNoException();
                        return true;
                    case 9:
                        parcel.enforceInterface("com.android.dialer.simulator.service.ISimulatorService");
                        ((SimulatorService.C05611) this).disableSimulatorMode();
                        parcel2.writeNoException();
                        return true;
                    default:
                        return super.onTransact(i, parcel, parcel2, i2);
                }
            } else {
                parcel2.writeString("com.android.dialer.simulator.service.ISimulatorService");
                return true;
            }
        }
    }
}
