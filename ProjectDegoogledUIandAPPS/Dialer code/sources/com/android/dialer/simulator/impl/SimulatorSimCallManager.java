package com.android.dialer.simulator.impl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telecom.Connection;
import android.telecom.ConnectionRequest;
import android.telecom.PhoneAccount;
import android.telecom.PhoneAccountHandle;
import android.telecom.TelecomManager;
import com.android.dialer.common.Assert;
import com.android.dialer.common.LogUtil;
import com.android.dialer.strictmode.StrictModeUtils;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.util.Arrays;
import java.util.Random;

public class SimulatorSimCallManager {
    public static String addNewIncomingCall(Context context, String str, int i) {
        return addNewIncomingCall(context, str, i, new Bundle());
    }

    public static String addNewOutgoingCall(Context context, String str, int i) {
        return addNewOutgoingCall(context, str, i, new Bundle());
    }

    static Bundle createSimulatorConnectionExtras(int i) {
        Bundle bundle = new Bundle();
        bundle.putBoolean("is_simulator_connection", true);
        String format = String.format("simulator_phone_call_%x", new Object[]{Integer.valueOf(Math.abs(new Random().nextInt()))});
        bundle.putString("connection_tag", format);
        bundle.putBoolean(format, true);
        bundle.putInt("connection_call_type", i);
        if (i == 3) {
            bundle.putBoolean("android.telecom.extra.START_CALL_WITH_RTT", true);
        }
        return bundle;
    }

    public static SimulatorConnection findConnectionByTag(String str) {
        Assert.isNotNull(str);
        for (Connection next : SimulatorConnectionService.getInstance().getAllConnections()) {
            if (next.getExtras().getBoolean(str)) {
                return (SimulatorConnection) next;
            }
        }
        throw new IllegalStateException();
    }

    public static String getConnectionTag(Connection connection) {
        String string = connection.getExtras().getString("connection_tag");
        Assert.isNotNull(string);
        return string;
    }

    public static PhoneAccountHandle getSimCallManagerHandle(Context context) {
        return new PhoneAccountHandle(new ComponentName(context, SimulatorConnectionService.class), "SIMULATOR_ACCOUNT_ID");
    }

    public static PhoneAccountHandle getSystemPhoneAccountHandle(Context context) {
        TelecomManager telecomManager = (TelecomManager) context.getSystemService(TelecomManager.class);
        try {
            for (PhoneAccountHandle next : telecomManager.getCallCapablePhoneAccounts()) {
                if (telecomManager.getPhoneAccount(next).hasCapabilities(4)) {
                    return next;
                }
            }
            throw new IllegalStateException("no SIM phone account available");
        } catch (SecurityException e) {
            throw new IllegalStateException(GeneratedOutlineSupport.outline6("Unable to get phone accounts: ", e));
        }
    }

    static PhoneAccountHandle getVideoProviderHandle(Context context) {
        return new PhoneAccountHandle(new ComponentName(context, SimulatorConnectionService.class), "SIMULATOR_VIDEO_ACCOUNT_ID");
    }

    public static boolean isSimulatorConnectionRequest(ConnectionRequest connectionRequest) {
        return connectionRequest.getExtras() != null && connectionRequest.getExtras().getBoolean("is_simulator_connection");
    }

    static /* synthetic */ void lambda$register$0(Context context) {
        TelecomManager telecomManager = (TelecomManager) context.getSystemService(TelecomManager.class);
        telecomManager.registerPhoneAccount(new PhoneAccount.Builder(getSimCallManagerHandle(context), "Simulator SIM call manager").setCapabilities(4097).setShortDescription("Simulator SIM call manager").setSupportedUriSchemes(Arrays.asList(new String[]{"tel"})).build());
        telecomManager.registerPhoneAccount(new PhoneAccount.Builder(getVideoProviderHandle(context), "Simulator video provider").setCapabilities(1034).setShortDescription("Simulator video provider").setSupportedUriSchemes(Arrays.asList(new String[]{"tel"})).build());
    }

    static /* synthetic */ void lambda$sharePersistentLog$0(Context context, String str) {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/plain");
        intent.putExtra("android.intent.extra.TEXT", str);
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
        }
    }

    static /* synthetic */ void lambda$unregister$1(Context context) {
        TelecomManager telecomManager = (TelecomManager) context.getSystemService(TelecomManager.class);
        telecomManager.unregisterPhoneAccount(getSimCallManagerHandle(context));
        telecomManager.unregisterPhoneAccount(getVideoProviderHandle(context));
    }

    public static void register(Context context) {
        LogUtil.enterBlock("SimulatorSimCallManager.register");
        Assert.isNotNull(context);
        StrictModeUtils.bypass((Runnable) new Runnable(context) {
            private final /* synthetic */ Context f$0;

            {
                this.f$0 = r1;
            }

            public final void run() {
                SimulatorSimCallManager.lambda$register$0(this.f$0);
            }
        });
    }

    public static void unregister(Context context) {
        LogUtil.enterBlock("SimulatorSimCallManager.unregister");
        Assert.isNotNull(context);
        StrictModeUtils.bypass((Runnable) new Runnable(context) {
            private final /* synthetic */ Context f$0;

            {
                this.f$0 = r1;
            }

            public final void run() {
                SimulatorSimCallManager.lambda$unregister$1(this.f$0);
            }
        });
    }

    public static String addNewIncomingCall(Context context, String str, int i, Bundle bundle) {
        PhoneAccountHandle phoneAccountHandle;
        LogUtil.enterBlock("SimulatorSimCallManager.addNewIncomingCall");
        Assert.isNotNull(context);
        Assert.isNotNull(str);
        Assert.isNotNull(bundle);
        register(context);
        Bundle bundle2 = new Bundle(bundle);
        bundle2.putString("incoming_number", str);
        bundle2.putAll(createSimulatorConnectionExtras(i));
        TelecomManager telecomManager = (TelecomManager) context.getSystemService(TelecomManager.class);
        if (i == 2) {
            phoneAccountHandle = getVideoProviderHandle(context);
        } else {
            phoneAccountHandle = getSystemPhoneAccountHandle(context);
        }
        telecomManager.addNewIncomingCall(phoneAccountHandle, bundle2);
        return bundle2.getString("connection_tag");
    }

    public static String addNewOutgoingCall(Context context, String str, int i, Bundle bundle) {
        PhoneAccountHandle phoneAccountHandle;
        LogUtil.enterBlock("SimulatorSimCallManager.addNewOutgoingCall");
        Assert.isNotNull(context);
        Assert.isNotNull(bundle);
        Assert.isNotNull(str);
        Assert.isNotNull(bundle);
        register(context);
        Bundle bundle2 = new Bundle(bundle);
        bundle2.putAll(createSimulatorConnectionExtras(i));
        Bundle bundle3 = new Bundle();
        bundle3.putBundle("android.telecom.extra.OUTGOING_CALL_EXTRAS", bundle2);
        if (i == 2) {
            phoneAccountHandle = getVideoProviderHandle(context);
        } else {
            phoneAccountHandle = getSimCallManagerHandle(context);
        }
        bundle3.putParcelable("android.telecom.extra.PHONE_ACCOUNT_HANDLE", phoneAccountHandle);
        if (i == 3) {
            bundle3.putBoolean("android.telecom.extra.START_CALL_WITH_RTT", true);
        }
        try {
            ((TelecomManager) context.getSystemService(TelecomManager.class)).placeCall(Uri.fromParts("tel", str, (String) null), bundle3);
            return bundle2.getString("connection_tag");
        } catch (SecurityException e) {
            throw new IllegalStateException(GeneratedOutlineSupport.outline6("Unable to place call: ", e));
        }
    }
}
