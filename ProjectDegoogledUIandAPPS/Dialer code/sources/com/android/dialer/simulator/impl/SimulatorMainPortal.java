package com.android.dialer.simulator.impl;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.provider.VoicemailContract;
import android.support.p002v7.app.AppCompatActivity;
import android.view.ActionProvider;
import com.android.dialer.common.LogUtil;
import com.android.dialer.common.concurrent.DefaultDialerExecutorFactory;
import com.android.dialer.common.concurrent.DialerExecutor;
import com.android.dialer.common.concurrent.DialerExecutorComponent;
import com.android.dialer.databasepopulator.VoicemailPopulator;
import com.android.dialer.enrichedcall.simulator.EnrichedCallSimulatorActivity;
import com.android.dialer.simulator.SimulatorComponent;
import com.android.dialer.simulator.impl.SimulatorDialogFragment;
import com.android.dialer.simulator.impl.SimulatorPortalEntryGroup;
import com.google.common.collect.ImmutableMap;
import com.google.common.util.concurrent.AbstractListeningExecutorService;
import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class SimulatorMainPortal {
    private final AppCompatActivity activity;
    private String callerId = "";
    private final Context context;
    private int missedCallNum = 1;
    private int presentation = 1;
    private SimulatorPortalEntryGroup simulatorPortalEntryGroup;

    public SimulatorMainPortal(AppCompatActivity appCompatActivity) {
        this.activity = appCompatActivity;
        this.context = appCompatActivity.getApplicationContext();
        SimulatorPortalEntryGroup.Builder builder = SimulatorPortalEntryGroup.builder();
        builder.setMethods(ImmutableMap.builder().put("Populate database", new Runnable() {
            public final void run() {
                SimulatorMainPortal.this.lambda$buildMainPortal$1$SimulatorMainPortal();
            }
        }).put("Populate voicemail", new Runnable() {
            public final void run() {
                SimulatorMainPortal.this.lambda$buildMainPortal$2$SimulatorMainPortal();
            }
        }).put("Fast Populate database", new Runnable() {
            public final void run() {
                SimulatorMainPortal.this.lambda$buildMainPortal$3$SimulatorMainPortal();
            }
        }).put("Fast populate voicemail database", new Runnable() {
            public final void run() {
                SimulatorMainPortal.this.lambda$buildMainPortal$4$SimulatorMainPortal();
            }
        }).put("Clean database", new Runnable() {
            public final void run() {
                SimulatorMainPortal.this.lambda$buildMainPortal$5$SimulatorMainPortal();
            }
        }).put("clear preferred SIM", new Runnable() {
            public final void run() {
                SimulatorMainPortal.this.lambda$buildMainPortal$6$SimulatorMainPortal();
            }
        }).put("Sync voicemail", new Runnable() {
            public final void run() {
                SimulatorMainPortal.this.lambda$buildMainPortal$7$SimulatorMainPortal();
            }
        }).put("Share persistent log", new Runnable() {
            public final void run() {
                SimulatorMainPortal.this.lambda$buildMainPortal$8$SimulatorMainPortal();
            }
        }).put("Enriched call simulator", new Runnable() {
            public final void run() {
                SimulatorMainPortal.this.lambda$buildMainPortal$9$SimulatorMainPortal();
            }
        }).put("Enable simulator mode", new Runnable() {
            public final void run() {
                SimulatorMainPortal.this.lambda$buildMainPortal$10$SimulatorMainPortal();
            }
        }).put("Disable simulator mode", new Runnable() {
            public final void run() {
                SimulatorMainPortal.this.lambda$buildMainPortal$11$SimulatorMainPortal();
            }
        }).build());
        SimulatorPortalEntryGroup.Builder builder2 = SimulatorPortalEntryGroup.builder();
        builder2.setMethods(ImmutableMap.builder().put("Incoming call", new Runnable() {
            public final void run() {
                SimulatorMainPortal.this.lambda$buildSimulatorVoiceCallPortal$12$SimulatorMainPortal();
            }
        }).put("Outgoing call", new Runnable() {
            public final void run() {
                SimulatorMainPortal.this.lambda$buildSimulatorVoiceCallPortal$13$SimulatorMainPortal();
            }
        }).put("Customized incoming call (Dialog)", new Runnable() {
            public final void run() {
                SimulatorMainPortal.this.lambda$buildSimulatorVoiceCallPortal$14$SimulatorMainPortal();
            }
        }).put("Customized outgoing call (Dialog)", new Runnable() {
            public final void run() {
                SimulatorMainPortal.this.lambda$buildSimulatorVoiceCallPortal$15$SimulatorMainPortal();
            }
        }).put("Customized incoming call", new Runnable() {
            public final void run() {
                SimulatorMainPortal.this.lambda$buildSimulatorVoiceCallPortal$16$SimulatorMainPortal();
            }
        }).put("Customized outgoing call", new Runnable() {
            public final void run() {
                SimulatorMainPortal.this.lambda$buildSimulatorVoiceCallPortal$17$SimulatorMainPortal();
            }
        }).put("Incoming enriched call", new Runnable() {
            public final void run() {
                SimulatorMainPortal.this.lambda$buildSimulatorVoiceCallPortal$18$SimulatorMainPortal();
            }
        }).put("Outgoing enriched call", new Runnable() {
            public final void run() {
                SimulatorMainPortal.this.lambda$buildSimulatorVoiceCallPortal$19$SimulatorMainPortal();
            }
        }).put("Spam incoming call", new Runnable() {
            public final void run() {
                SimulatorMainPortal.this.lambda$buildSimulatorVoiceCallPortal$20$SimulatorMainPortal();
            }
        }).put("Emergency call back", new Runnable() {
            public final void run() {
                SimulatorMainPortal.this.lambda$buildSimulatorVoiceCallPortal$21$SimulatorMainPortal();
            }
        }).put("GSM conference", new Runnable() {
            public final void run() {
                SimulatorMainPortal.this.lambda$buildSimulatorVoiceCallPortal$22$SimulatorMainPortal();
            }
        }).put("VoLTE conference", new Runnable() {
            public final void run() {
                SimulatorMainPortal.this.lambda$buildSimulatorVoiceCallPortal$23$SimulatorMainPortal();
            }
        }).build());
        SimulatorPortalEntryGroup build = builder2.build();
        SimulatorPortalEntryGroup.Builder builder3 = SimulatorPortalEntryGroup.builder();
        builder3.setMethods(ImmutableMap.builder().put("Incoming one way", new Runnable() {
            public final void run() {
                SimulatorMainPortal.this.lambda$buildSimulatorVideoCallPortal$24$SimulatorMainPortal();
            }
        }).put("Incoming two way", new Runnable() {
            public final void run() {
                SimulatorMainPortal.this.lambda$buildSimulatorVideoCallPortal$25$SimulatorMainPortal();
            }
        }).put("Outgoing one way", new Runnable() {
            public final void run() {
                SimulatorMainPortal.this.lambda$buildSimulatorVideoCallPortal$26$SimulatorMainPortal();
            }
        }).put("Outgoing two way", new Runnable() {
            public final void run() {
                SimulatorMainPortal.this.lambda$buildSimulatorVideoCallPortal$27$SimulatorMainPortal();
            }
        }).build());
        SimulatorPortalEntryGroup build2 = builder3.build();
        SimulatorPortalEntryGroup.Builder builder4 = SimulatorPortalEntryGroup.builder();
        builder4.setMethods(ImmutableMap.builder().put("Incoming call", new Runnable() {
            public final void run() {
                SimulatorMainPortal.this.lambda$buildSimulatorRttCallPortal$28$SimulatorMainPortal();
            }
        }).put("Outgoing call", new Runnable() {
            public final void run() {
                SimulatorMainPortal.this.lambda$buildSimulatorRttCallPortal$29$SimulatorMainPortal();
            }
        }).put("Emergency call", new Runnable() {
            public final void run() {
                SimulatorMainPortal.this.lambda$buildSimulatorRttCallPortal$30$SimulatorMainPortal();
            }
        }).build());
        SimulatorPortalEntryGroup build3 = builder4.build();
        SimulatorPortalEntryGroup.Builder builder5 = SimulatorPortalEntryGroup.builder();
        builder5.setMethods(ImmutableMap.builder().put("Missed calls", new Runnable() {
            public final void run() {
                SimulatorMainPortal.this.lambda$buildSimulatorNotificationsPortal$31$SimulatorMainPortal();
            }
        }).put("Missed calls (few)", new Runnable() {
            public final void run() {
                SimulatorMainPortal.this.lambda$buildSimulatorNotificationsPortal$32$SimulatorMainPortal();
            }
        }).put("Voicemails", new Runnable() {
            public final void run() {
                SimulatorMainPortal.this.lambda$buildSimulatorNotificationsPortal$33$SimulatorMainPortal();
            }
        }).build());
        builder.setSubPortals(ImmutableMap.m85of("VoiceCall", build, "VideoCall", build2, "RttCall", build3, "Notifications", builder5.build()));
        this.simulatorPortalEntryGroup = builder.build();
    }

    public void execute(String[] strArr) {
        ((AbstractListeningExecutorService) DialerExecutorComponent.get(this.context).backgroundExecutor()).submit((Runnable) new Runnable(strArr) {
            private final /* synthetic */ String[] f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                SimulatorMainPortal.this.lambda$execute$0$SimulatorMainPortal(this.f$1);
            }
        });
    }

    public ActionProvider getActionProvider() {
        return new SimulatorMenu(this.context, this.simulatorPortalEntryGroup);
    }

    public /* synthetic */ void lambda$buildMainPortal$1$SimulatorMainPortal() {
        Context context2 = this.context;
        ((DefaultDialerExecutorFactory) DialerExecutorComponent.get(context2).dialerExecutorFactory()).createNonUiTaskBuilder(new SimulatorUtils$PopulateDatabaseWorker((SimulatorUtils$1) null)).build().executeSerial(new SimulatorUtils$PopulateDatabaseWorkerInput(context2, false));
    }

    public /* synthetic */ void lambda$buildMainPortal$10$SimulatorMainPortal() {
        ((SimulatorImpl) SimulatorComponent.get(this.context).getSimulator()).enableSimulatorMode();
        SimulatorSimCallManager.register(this.context);
    }

    public /* synthetic */ void lambda$buildMainPortal$11$SimulatorMainPortal() {
        ((SimulatorImpl) SimulatorComponent.get(this.context).getSimulator()).disableSimulatorMode();
        SimulatorSimCallManager.unregister(this.context);
    }

    public /* synthetic */ void lambda$buildMainPortal$2$SimulatorMainPortal() {
        Context context2 = this.context;
        ((DefaultDialerExecutorFactory) DialerExecutorComponent.get(context2).dialerExecutorFactory()).createNonUiTaskBuilder(new SimulatorUtils$PopulateVoicemailWorker((SimulatorUtils$1) null)).build().executeSerial(new SimulatorUtils$PopulateDatabaseWorkerInput(context2, false));
    }

    public /* synthetic */ void lambda$buildMainPortal$3$SimulatorMainPortal() {
        Context context2 = this.context;
        ((DefaultDialerExecutorFactory) DialerExecutorComponent.get(context2).dialerExecutorFactory()).createNonUiTaskBuilder(new SimulatorUtils$PopulateDatabaseWorker((SimulatorUtils$1) null)).build().executeSerial(new SimulatorUtils$PopulateDatabaseWorkerInput(context2, true));
    }

    public /* synthetic */ void lambda$buildMainPortal$4$SimulatorMainPortal() {
        Context context2 = this.context;
        ((DefaultDialerExecutorFactory) DialerExecutorComponent.get(context2).dialerExecutorFactory()).createNonUiTaskBuilder(new SimulatorUtils$PopulateVoicemailWorker((SimulatorUtils$1) null)).build().executeSerial(new SimulatorUtils$PopulateDatabaseWorkerInput(context2, true));
    }

    public /* synthetic */ void lambda$buildMainPortal$5$SimulatorMainPortal() {
        Context context2 = this.context;
        ((DefaultDialerExecutorFactory) DialerExecutorComponent.get(context2).dialerExecutorFactory()).createNonUiTaskBuilder(new SimulatorUtils$CleanDatabaseWorker((SimulatorUtils$1) null)).build().executeSerial(context2);
    }

    public /* synthetic */ void lambda$buildMainPortal$6$SimulatorMainPortal() {
        Context context2 = this.context;
        ((DefaultDialerExecutorFactory) DialerExecutorComponent.get(context2).dialerExecutorFactory()).createNonUiTaskBuilder(new SimulatorUtils$ClearPreferredSimWorker((SimulatorUtils$1) null)).build().executeSerial(context2);
    }

    public /* synthetic */ void lambda$buildMainPortal$7$SimulatorMainPortal() {
        this.context.sendBroadcast(new Intent("android.provider.action.SYNC_VOICEMAIL"));
    }

    public /* synthetic */ void lambda$buildMainPortal$8$SimulatorMainPortal() {
        Context context2 = this.context;
        ((DefaultDialerExecutorFactory) DialerExecutorComponent.get(context2).dialerExecutorFactory()).createNonUiTaskBuilder(new SimulatorUtils$ShareLogWorker((SimulatorUtils$1) null)).onSuccess(new DialerExecutor.SuccessListener(context2) {
            private final /* synthetic */ Context f$0;

            {
                this.f$0 = r1;
            }

            public final void onSuccess(Object obj) {
                SimulatorSimCallManager.lambda$sharePersistentLog$0(this.f$0, (String) obj);
            }
        }).build().executeSerial(null);
    }

    public /* synthetic */ void lambda$buildMainPortal$9$SimulatorMainPortal() {
        Context context2 = this.context;
        context2.startActivity(EnrichedCallSimulatorActivity.newIntent(context2));
    }

    public /* synthetic */ void lambda$buildSimulatorNotificationsPortal$31$SimulatorMainPortal() {
        new SimulatorMissedCallCreator(this.context).start(12);
    }

    public /* synthetic */ void lambda$buildSimulatorNotificationsPortal$32$SimulatorMainPortal() {
        new SimulatorMissedCallCreator(this.context).start(this.missedCallNum);
    }

    public /* synthetic */ void lambda$buildSimulatorNotificationsPortal$33$SimulatorMainPortal() {
        Context context2 = this.context;
        LogUtil.enterBlock("SimulatorNotifications.addVoicemailNotifications");
        ArrayList arrayList = new ArrayList();
        for (int i = 12; i > 0; i--) {
            VoicemailPopulator.Voicemail.Builder builder = VoicemailPopulator.Voicemail.builder();
            builder.setPhoneNumber(String.format(Locale.ENGLISH, "+%d", new Object[]{Integer.valueOf(i)}));
            builder.setTranscription(String.format(Locale.ENGLISH, "Short transcript %d", new Object[]{Integer.valueOf(i)}));
            builder.setDurationSeconds(60);
            builder.setIsRead(false);
            builder.setPhoneAccountComponentName("");
            builder.setTimeMillis(System.currentTimeMillis() - TimeUnit.HOURS.toMillis((long) i));
            arrayList.add(builder.build().getAsContentValues(context2));
        }
        context2.getContentResolver().bulkInsert(VoicemailContract.Voicemails.buildSourceUri(context2.getPackageName()), (ContentValues[]) arrayList.toArray(new ContentValues[arrayList.size()]));
    }

    public /* synthetic */ void lambda$buildSimulatorRttCallPortal$28$SimulatorMainPortal() {
        new SimulatorRttCall(this.context).addNewIncomingCall(false);
    }

    public /* synthetic */ void lambda$buildSimulatorRttCallPortal$29$SimulatorMainPortal() {
        new SimulatorRttCall(this.context).addNewOutgoingCall();
    }

    public /* synthetic */ void lambda$buildSimulatorRttCallPortal$30$SimulatorMainPortal() {
        new SimulatorRttCall(this.context).addNewEmergencyCall();
    }

    public /* synthetic */ void lambda$buildSimulatorVideoCallPortal$24$SimulatorMainPortal() {
        new SimulatorVideoCall(this.context, 2).addNewIncomingCall();
    }

    public /* synthetic */ void lambda$buildSimulatorVideoCallPortal$25$SimulatorMainPortal() {
        new SimulatorVideoCall(this.context, 3).addNewIncomingCall();
    }

    public /* synthetic */ void lambda$buildSimulatorVideoCallPortal$26$SimulatorMainPortal() {
        new SimulatorVideoCall(this.context, 1).addNewOutgoingCall();
    }

    public /* synthetic */ void lambda$buildSimulatorVideoCallPortal$27$SimulatorMainPortal() {
        new SimulatorVideoCall(this.context, 3).addNewOutgoingCall();
    }

    public /* synthetic */ void lambda$buildSimulatorVoiceCallPortal$12$SimulatorMainPortal() {
        new SimulatorVoiceCall(this.context).addNewIncomingCall();
    }

    public /* synthetic */ void lambda$buildSimulatorVoiceCallPortal$13$SimulatorMainPortal() {
        new SimulatorVoiceCall(this.context).addNewOutgoingCall();
    }

    public /* synthetic */ void lambda$buildSimulatorVoiceCallPortal$14$SimulatorMainPortal() {
        SimulatorDialogFragment.newInstance(new SimulatorDialogFragment.DialogCallback() {
            public final void createCustomizedCall(String str, int i) {
                SimulatorVoiceCall.this.lambda$addCustomizedIncomingCallWithDialog$2$SimulatorVoiceCall(str, i);
            }
        }).show(this.activity.getSupportFragmentManager(), "SimulatorDialog");
    }

    public /* synthetic */ void lambda$buildSimulatorVoiceCallPortal$15$SimulatorMainPortal() {
        SimulatorDialogFragment.newInstance(new SimulatorDialogFragment.DialogCallback() {
            public final void createCustomizedCall(String str, int i) {
                SimulatorVoiceCall.this.lambda$addCustomizedOutgoingCallWithDialog$3$SimulatorVoiceCall(str, i);
            }
        }).show(this.activity.getSupportFragmentManager(), "SimulatorDialog");
    }

    public /* synthetic */ void lambda$buildSimulatorVoiceCallPortal$16$SimulatorMainPortal() {
        new SimulatorVoiceCall(this.context).addCustomizedIncomingCall(this.callerId, this.presentation);
    }

    public /* synthetic */ void lambda$buildSimulatorVoiceCallPortal$17$SimulatorMainPortal() {
        new SimulatorVoiceCall(this.context).addCustomizedOutgoingCall(this.callerId, this.presentation);
    }

    public /* synthetic */ void lambda$buildSimulatorVoiceCallPortal$18$SimulatorMainPortal() {
        new SimulatorVoiceCall(this.context).incomingEnrichedCall();
    }

    public /* synthetic */ void lambda$buildSimulatorVoiceCallPortal$19$SimulatorMainPortal() {
        new SimulatorVoiceCall(this.context).outgoingEnrichedCall();
    }

    public /* synthetic */ void lambda$buildSimulatorVoiceCallPortal$20$SimulatorMainPortal() {
        new SimulatorVoiceCall(this.context).addSpamIncomingCall();
    }

    public /* synthetic */ void lambda$buildSimulatorVoiceCallPortal$21$SimulatorMainPortal() {
        new SimulatorVoiceCall(this.context).addNewEmergencyCallBack();
    }

    public /* synthetic */ void lambda$buildSimulatorVoiceCallPortal$22$SimulatorMainPortal() {
        new SimulatorConferenceCreator(this.context, 1).start(5);
    }

    public /* synthetic */ void lambda$buildSimulatorVoiceCallPortal$23$SimulatorMainPortal() {
        new SimulatorConferenceCreator(this.context, 2).start(5);
    }

    public /* synthetic */ void lambda$execute$0$SimulatorMainPortal(String[] strArr) {
        execute(this.simulatorPortalEntryGroup, strArr, 0);
    }

    public void setCallerId(String str) {
        this.callerId = str;
    }

    public void setMissedCallNum(int i) {
        this.missedCallNum = i;
    }

    public void setPresentation(int i) {
        this.presentation = i;
    }

    private void execute(SimulatorPortalEntryGroup simulatorPortalEntryGroup2, String[] strArr, int i) {
        if (simulatorPortalEntryGroup2.methods().containsKey(strArr[i])) {
            simulatorPortalEntryGroup2.methods().get(strArr[i]).run();
        } else if (simulatorPortalEntryGroup2.subPortals().containsKey(strArr[i])) {
            execute(simulatorPortalEntryGroup2.subPortals().get(strArr[i]), strArr, i + 1);
        }
    }
}
