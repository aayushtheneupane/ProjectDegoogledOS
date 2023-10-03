package com.android.dialer.simulator.service;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Binder;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;
import com.android.dialer.simulator.impl.SimulatorMainPortal;
import com.android.dialer.simulator.service.ISimulatorService;
import com.android.dialer.simulator.service.SimulatorService;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;

public class SimulatorService extends Service {
    private final ISimulatorService.Stub binder = new ISimulatorService.Stub() {
        private void doSecurityCheck(Runnable runnable) {
            if (SimulatorService.this.hasAccessToService()) {
                runnable.run();
                return;
            }
            throw new RuntimeException("Client doesn't have access to Simulator service!");
        }

        public void cleanDataBase() throws RemoteException {
            doSecurityCheck(new Runnable() {
                public final void run() {
                    SimulatorService.C05611.this.lambda$cleanDataBase$3$SimulatorService$1();
                }
            });
        }

        public void disableSimulatorMode() throws RemoteException {
            doSecurityCheck(new Runnable() {
                public final void run() {
                    SimulatorService.C05611.this.lambda$disableSimulatorMode$5$SimulatorService$1();
                }
            });
        }

        public void enableSimulatorMode() throws RemoteException {
            doSecurityCheck(new Runnable() {
                public final void run() {
                    SimulatorService.C05611.this.lambda$enableSimulatorMode$4$SimulatorService$1();
                }
            });
        }

        public /* synthetic */ void lambda$cleanDataBase$3$SimulatorService$1() {
            SimulatorService.this.simulatorMainPortal.execute(new String[]{"Clean database"});
        }

        public /* synthetic */ void lambda$disableSimulatorMode$5$SimulatorService$1() {
            SimulatorService.this.simulatorMainPortal.execute(new String[]{"Disable simulator mode"});
        }

        public /* synthetic */ void lambda$enableSimulatorMode$4$SimulatorService$1() {
            SimulatorService.this.simulatorMainPortal.execute(new String[]{"Enable simulator mode"});
        }

        public /* synthetic */ void lambda$makeIncomingCall$0$SimulatorService$1(String str, int i) {
            SimulatorService.this.simulatorMainPortal.setCallerId(str);
            SimulatorService.this.simulatorMainPortal.setPresentation(i);
            SimulatorService.this.simulatorMainPortal.execute(new String[]{"VoiceCall", "Customized incoming call"});
        }

        public /* synthetic */ void lambda$makeIncomingEnrichedCall$6$SimulatorService$1() {
            SimulatorService.this.simulatorMainPortal.execute(new String[]{"VoiceCall", "Incoming enriched call"});
        }

        public /* synthetic */ void lambda$makeOutgoingCall$1$SimulatorService$1(String str, int i) {
            SimulatorService.this.simulatorMainPortal.setCallerId(str);
            SimulatorService.this.simulatorMainPortal.setPresentation(i);
            SimulatorService.this.simulatorMainPortal.execute(new String[]{"VoiceCall", "Customized outgoing call"});
        }

        public /* synthetic */ void lambda$makeOutgoingEnrichedCall$7$SimulatorService$1() {
            SimulatorService.this.simulatorMainPortal.execute(new String[]{"VoiceCall", "Outgoing enriched call"});
        }

        public /* synthetic */ void lambda$populateDataBase$2$SimulatorService$1() {
            SimulatorService.this.simulatorMainPortal.execute(new String[]{"Populate database"});
        }

        public /* synthetic */ void lambda$populateMissedCall$8$SimulatorService$1(int i) {
            SimulatorService.this.simulatorMainPortal.setMissedCallNum(i);
            SimulatorService.this.simulatorMainPortal.execute(new String[]{"Notifications", "Missed calls (few)"});
        }

        public void makeIncomingCall(String str, int i) {
            doSecurityCheck(new Runnable(str, i) {
                private final /* synthetic */ String f$1;
                private final /* synthetic */ int f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void run() {
                    SimulatorService.C05611.this.lambda$makeIncomingCall$0$SimulatorService$1(this.f$1, this.f$2);
                }
            });
        }

        public void makeIncomingEnrichedCall() throws RemoteException {
            doSecurityCheck(new Runnable() {
                public final void run() {
                    SimulatorService.C05611.this.lambda$makeIncomingEnrichedCall$6$SimulatorService$1();
                }
            });
        }

        public void makeOutgoingCall(String str, int i) {
            doSecurityCheck(new Runnable(str, i) {
                private final /* synthetic */ String f$1;
                private final /* synthetic */ int f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void run() {
                    SimulatorService.C05611.this.lambda$makeOutgoingCall$1$SimulatorService$1(this.f$1, this.f$2);
                }
            });
        }

        public void makeOutgoingEnrichedCall() throws RemoteException {
            doSecurityCheck(new Runnable() {
                public final void run() {
                    SimulatorService.C05611.this.lambda$makeOutgoingEnrichedCall$7$SimulatorService$1();
                }
            });
        }

        public void populateDataBase() throws RemoteException {
            doSecurityCheck(new Runnable() {
                public final void run() {
                    SimulatorService.C05611.this.lambda$populateDataBase$2$SimulatorService$1();
                }
            });
        }

        public void populateMissedCall(int i) throws RemoteException {
            doSecurityCheck(new Runnable(i) {
                private final /* synthetic */ int f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    SimulatorService.C05611.this.lambda$populateMissedCall$8$SimulatorService$1(this.f$1);
                }
            });
        }
    };
    private ImmutableList<String> certificates;
    /* access modifiers changed from: private */
    public SimulatorMainPortal simulatorMainPortal;

    private static class NotOnlyOneSignatureException extends Exception {
        public NotOnlyOneSignatureException(String str) {
            super(str);
        }
    }

    /* access modifiers changed from: private */
    public boolean hasAccessToService() {
        Optional absent;
        int callingPid = Binder.getCallingPid();
        if (callingPid != Process.myPid()) {
            Iterator<ActivityManager.RunningAppProcessInfo> it = ((ActivityManager) getSystemService("activity")).getRunningAppProcesses().iterator();
            while (true) {
                if (!it.hasNext()) {
                    absent = Optional.absent();
                    break;
                }
                ActivityManager.RunningAppProcessInfo next = it.next();
                if (next.pid == callingPid) {
                    absent = Optional.m67of(next.processName);
                    break;
                }
            }
            if (!absent.isPresent()) {
                return false;
            }
            try {
                PackageInfo packageInfo = getPackageManager().getPackageInfo((String) absent.get(), 64);
                MessageDigest instance = MessageDigest.getInstance("MD5");
                if (packageInfo.signatures.length == 1) {
                    return isCertificateValid(instance.digest(packageInfo.signatures[0].toByteArray()), this.certificates);
                }
                throw new NotOnlyOneSignatureException("The client has more than one signature!");
            } catch (PackageManager.NameNotFoundException | NotOnlyOneSignatureException | NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new RuntimeException("Client and service have the same PID!");
        }
    }

    private static boolean isCertificateValid(byte[] bArr, ImmutableList<String> immutableList) {
        UnmodifiableIterator<String> it = immutableList.iterator();
        while (it.hasNext()) {
            String next = it.next();
            StringBuilder sb = new StringBuilder();
            int length = bArr.length;
            for (int i = 0; i < length; i++) {
                sb.append(String.format("%02X", new Object[]{Byte.valueOf(bArr[i])}));
            }
            if (next.equals(sb.toString())) {
                return true;
            }
        }
        return false;
    }

    public IBinder onBind(Intent intent) {
        return this.binder;
    }
}
