package com.android.dialer.blockreportspam;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.support.p000v4.app.FragmentManager;
import android.widget.Toast;
import com.android.dialer.R;
import com.android.dialer.blocking.Blocking;
import com.android.dialer.common.LogUtil;
import com.android.dialer.common.concurrent.DialerExecutorComponent;
import com.android.dialer.logging.DialerImpression$Type;
import com.android.dialer.logging.Logger;
import com.android.dialer.logging.LoggingBindingsStub;
import com.android.dialer.spam.Spam;
import com.android.dialer.spam.SpamComponent;
import com.android.dialer.spam.SpamSettings;
import com.android.dialer.spam.stub.SpamSettingsStub;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import java.util.concurrent.Callable;

public final class ShowBlockReportSpamDialogReceiver extends BroadcastReceiver {
    private final FragmentManager fragmentManager;

    public ShowBlockReportSpamDialogReceiver(FragmentManager fragmentManager2) {
        this.fragmentManager = fragmentManager2;
    }

    private static void blockNumber(final Context context, BlockReportSpamDialogInfo blockReportSpamDialogInfo) {
        ((LoggingBindingsStub) Logger.get(context)).logImpression(DialerImpression$Type.USER_ACTION_BLOCKED_NUMBER);
        Futures.addCallback(DialerExecutorComponent.get(context).backgroundExecutor().submit(new Callable(blockReportSpamDialogInfo.getCountryIso(), context) {
            private final /* synthetic */ String f$1;
            private final /* synthetic */ Context f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final Object call() {
                Blocking.lambda$block$0(ImmutableCollection.this, this.f$1, this.f$2);
                return null;
            }
        }), new FutureCallback<Void>() {
            public void onFailure(Throwable th) {
                if (th instanceof Blocking.BlockingFailedException) {
                    ((LoggingBindingsStub) Logger.get(context)).logImpression(DialerImpression$Type.USER_ACTION_BLOCK_NUMBER_FAILED);
                    Toast.makeText(context, R.string.block_number_failed_toast, 1).show();
                    return;
                }
                throw new RuntimeException(th);
            }

            public void onSuccess(Object obj) {
                Void voidR = (Void) obj;
            }
        }, DialerExecutorComponent.get(context).uiExecutor());
    }

    public static IntentFilter getIntentFilter() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("show_dialog_to_block_number_and_optionally_report_spam");
        intentFilter.addAction("show_dialog_to_block_number");
        intentFilter.addAction("show_dialog_to_report_not_spam");
        intentFilter.addAction("show_dialog_to_unblock_number");
        return intentFilter;
    }

    static /* synthetic */ void lambda$showDialogToBlockNumber$1(Context context, BlockReportSpamDialogInfo blockReportSpamDialogInfo) {
        LogUtil.m9i("ShowBlockReportSpamDialogReceiver.showDialogToBlockNumber", "block number", new Object[0]);
        blockNumber(context, blockReportSpamDialogInfo);
    }

    static /* synthetic */ void lambda$showDialogToBlockNumberAndOptionallyReportSpam$0(SpamSettings spamSettings, Context context, Spam spam, BlockReportSpamDialogInfo blockReportSpamDialogInfo, boolean z) {
        LogUtil.m9i("ShowBlockReportSpamDialogReceiver.showDialogToBlockNumberAndOptionallyReportSpam", "confirmed", new Object[0]);
        if (z) {
            ((SpamSettingsStub) spamSettings).isSpamEnabled();
        }
        blockNumber(context, blockReportSpamDialogInfo);
    }

    static /* synthetic */ void lambda$showDialogToReportNotSpam$2(Context context, BlockReportSpamDialogInfo blockReportSpamDialogInfo) {
        LogUtil.m9i("ShowBlockReportSpamDialogReceiver.showDialogToReportNotSpam", "confirmed", new Object[0]);
        ((SpamSettingsStub) SpamComponent.get(context).spamSettings()).isSpamEnabled();
    }

    static /* synthetic */ void lambda$showDialogToUnblockNumber$3(final Context context, BlockReportSpamDialogInfo blockReportSpamDialogInfo) {
        LogUtil.m9i("ShowBlockReportSpamDialogReceiver.showDialogToUnblockNumber", "confirmed", new Object[0]);
        ((LoggingBindingsStub) Logger.get(context)).logImpression(DialerImpression$Type.USER_ACTION_UNBLOCKED_NUMBER);
        Futures.addCallback(Blocking.unblock(context, ImmutableList.m75of(blockReportSpamDialogInfo.getNormalizedNumber()), blockReportSpamDialogInfo.getCountryIso()), new FutureCallback<Void>() {
            public void onFailure(Throwable th) {
                if (th instanceof Blocking.BlockingFailedException) {
                    ((LoggingBindingsStub) Logger.get(context)).logImpression(DialerImpression$Type.USER_ACTION_UNBLOCK_NUMBER_FAILED);
                    Toast.makeText(context, R.string.unblock_number_failed_toast, 1).show();
                    return;
                }
                throw new RuntimeException(th);
            }

            public void onSuccess(Object obj) {
                Void voidR = (Void) obj;
            }
        }, DialerExecutorComponent.get(context).uiExecutor());
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onReceive(android.content.Context r9, android.content.Intent r10) {
        /*
            r8 = this;
            java.lang.String r0 = "ShowBlockReportSpamDialogReceiver.onReceive"
            com.android.dialer.common.LogUtil.enterBlock(r0)
            java.lang.String r0 = r10.getAction()
            com.android.dialer.common.Assert.isNotNull(r0)
            r1 = r0
            java.lang.String r1 = (java.lang.String) r1
            int r2 = r1.hashCode()
            r3 = 0
            r4 = 3
            r5 = 2
            r6 = 1
            switch(r2) {
                case -1622373455: goto L_0x0039;
                case -1360348573: goto L_0x002f;
                case -1336343841: goto L_0x0025;
                case 513212682: goto L_0x001b;
                default: goto L_0x001a;
            }
        L_0x001a:
            goto L_0x0043
        L_0x001b:
            java.lang.String r2 = "show_dialog_to_block_number"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x0043
            r1 = r3
            goto L_0x0044
        L_0x0025:
            java.lang.String r2 = "show_dialog_to_block_number_and_optionally_report_spam"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x0043
            r1 = r6
            goto L_0x0044
        L_0x002f:
            java.lang.String r2 = "show_dialog_to_unblock_number"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x0043
            r1 = r4
            goto L_0x0044
        L_0x0039:
            java.lang.String r2 = "show_dialog_to_report_not_spam"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x0043
            r1 = r5
            goto L_0x0044
        L_0x0043:
            r1 = -1
        L_0x0044:
            r2 = 0
            java.lang.String r7 = "dialog_info"
            if (r1 == 0) goto L_0x00f2
            if (r1 == r6) goto L_0x00b2
            if (r1 == r5) goto L_0x0087
            if (r1 != r4) goto L_0x007b
            java.lang.String r0 = "ShowBlockReportSpamDialogReceiver.showDialogToUnblockNumber"
            com.android.dialer.common.LogUtil.enterBlock(r0)
            boolean r0 = r10.hasExtra(r7)
            com.android.dialer.common.Assert.checkArgument(r0)
            com.android.dialer.blockreportspam.BlockReportSpamDialogInfo r0 = com.android.dialer.blockreportspam.BlockReportSpamDialogInfo.getDefaultInstance()
            com.google.protobuf.MessageLite r10 = com.android.dialer.protos.ProtoParsers.getTrusted((android.content.Intent) r10, (java.lang.String) r7, r0)
            com.android.dialer.blockreportspam.BlockReportSpamDialogInfo r10 = (com.android.dialer.blockreportspam.BlockReportSpamDialogInfo) r10
            com.android.dialer.blockreportspam.-$$Lambda$ShowBlockReportSpamDialogReceiver$ncCbHL0s18VJ-haTtwTECC3kcQs r0 = new com.android.dialer.blockreportspam.-$$Lambda$ShowBlockReportSpamDialogReceiver$ncCbHL0s18VJ-haTtwTECC3kcQs
            r0.<init>(r9, r10)
            java.lang.String r9 = r10.getNormalizedNumber()
            android.support.v4.app.DialogFragment r9 = com.android.dialer.blockreportspam.BlockReportSpamDialogs.DialogFragmentForUnblockingNumber.newInstance(r9, r0, r2)
            android.support.v4.app.FragmentManager r8 = r8.fragmentManager
            java.lang.String r10 = "UnblockDialog"
            r9.show(r8, r10)
            goto L_0x011c
        L_0x007b:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "Unsupported action: "
            java.lang.String r9 = com.android.tools.p006r8.GeneratedOutlineSupport.outline8(r9, r0)
            r8.<init>(r9)
            throw r8
        L_0x0087:
            java.lang.String r0 = "ShowBlockReportSpamDialogReceiver.showDialogToReportNotSpam"
            com.android.dialer.common.LogUtil.enterBlock(r0)
            boolean r0 = r10.hasExtra(r7)
            com.android.dialer.common.Assert.checkArgument(r0)
            com.android.dialer.blockreportspam.BlockReportSpamDialogInfo r0 = com.android.dialer.blockreportspam.BlockReportSpamDialogInfo.getDefaultInstance()
            com.google.protobuf.MessageLite r10 = com.android.dialer.protos.ProtoParsers.getTrusted((android.content.Intent) r10, (java.lang.String) r7, r0)
            com.android.dialer.blockreportspam.BlockReportSpamDialogInfo r10 = (com.android.dialer.blockreportspam.BlockReportSpamDialogInfo) r10
            com.android.dialer.blockreportspam.-$$Lambda$ShowBlockReportSpamDialogReceiver$hiIAl7DofBOa2Jj9vZc32ahpRSs r0 = new com.android.dialer.blockreportspam.-$$Lambda$ShowBlockReportSpamDialogReceiver$hiIAl7DofBOa2Jj9vZc32ahpRSs
            r0.<init>(r9, r10)
            java.lang.String r9 = r10.getNormalizedNumber()
            android.support.v4.app.DialogFragment r9 = com.android.dialer.blockreportspam.BlockReportSpamDialogs.DialogFragmentForReportingNotSpam.newInstance(r9, r0, r2)
            android.support.v4.app.FragmentManager r8 = r8.fragmentManager
            java.lang.String r10 = "NotSpamDialog"
            r9.show(r8, r10)
            goto L_0x011c
        L_0x00b2:
            java.lang.String r0 = "ShowBlockReportSpamDialogReceiver.showDialogToBlockNumberAndOptionallyReportSpam"
            com.android.dialer.common.LogUtil.enterBlock(r0)
            boolean r0 = r10.hasExtra(r7)
            com.android.dialer.common.Assert.checkArgument(r0)
            com.android.dialer.blockreportspam.BlockReportSpamDialogInfo r0 = com.android.dialer.blockreportspam.BlockReportSpamDialogInfo.getDefaultInstance()
            com.google.protobuf.MessageLite r10 = com.android.dialer.protos.ProtoParsers.getTrusted((android.content.Intent) r10, (java.lang.String) r7, r0)
            com.android.dialer.blockreportspam.BlockReportSpamDialogInfo r10 = (com.android.dialer.blockreportspam.BlockReportSpamDialogInfo) r10
            com.android.dialer.spam.SpamComponent r0 = com.android.dialer.spam.SpamComponent.get(r9)
            com.android.dialer.spam.Spam r0 = r0.spam()
            com.android.dialer.spam.SpamComponent r1 = com.android.dialer.spam.SpamComponent.get(r9)
            com.android.dialer.spam.SpamSettings r1 = r1.spamSettings()
            com.android.dialer.blockreportspam.-$$Lambda$ShowBlockReportSpamDialogReceiver$66bxHY5CP3yPhC9ZxBCJpEYGOhE r4 = new com.android.dialer.blockreportspam.-$$Lambda$ShowBlockReportSpamDialogReceiver$66bxHY5CP3yPhC9ZxBCJpEYGOhE
            r4.<init>(r9, r0, r10)
            java.lang.String r9 = r10.getNormalizedNumber()
            com.android.dialer.spam.stub.SpamSettingsStub r1 = (com.android.dialer.spam.stub.SpamSettingsStub) r1
            r1.isDialogReportSpamCheckedByDefault()
            android.support.v4.app.DialogFragment r9 = com.android.dialer.blockreportspam.BlockReportSpamDialogs.DialogFragmentForBlockingNumberAndOptionallyReportingAsSpam.newInstance(r9, r3, r4, r2)
            android.support.v4.app.FragmentManager r8 = r8.fragmentManager
            java.lang.String r10 = "BlockReportSpamDialog"
            r9.show(r8, r10)
            goto L_0x011c
        L_0x00f2:
            java.lang.String r0 = "ShowBlockReportSpamDialogReceiver.showDialogToBlockNumber"
            com.android.dialer.common.LogUtil.enterBlock(r0)
            boolean r0 = r10.hasExtra(r7)
            com.android.dialer.common.Assert.checkArgument(r0)
            com.android.dialer.blockreportspam.BlockReportSpamDialogInfo r0 = com.android.dialer.blockreportspam.BlockReportSpamDialogInfo.getDefaultInstance()
            com.google.protobuf.MessageLite r10 = com.android.dialer.protos.ProtoParsers.getTrusted((android.content.Intent) r10, (java.lang.String) r7, r0)
            com.android.dialer.blockreportspam.BlockReportSpamDialogInfo r10 = (com.android.dialer.blockreportspam.BlockReportSpamDialogInfo) r10
            com.android.dialer.blockreportspam.-$$Lambda$ShowBlockReportSpamDialogReceiver$A4nAYyY55xhWlumJPuHaIEq9tRg r0 = new com.android.dialer.blockreportspam.-$$Lambda$ShowBlockReportSpamDialogReceiver$A4nAYyY55xhWlumJPuHaIEq9tRg
            r0.<init>(r9, r10)
            java.lang.String r9 = r10.getNormalizedNumber()
            android.support.v4.app.DialogFragment r9 = com.android.dialer.blockreportspam.BlockReportSpamDialogs.DialogFragmentForBlockingNumber.newInstance(r9, r0, r2)
            android.support.v4.app.FragmentManager r8 = r8.fragmentManager
            java.lang.String r10 = "BlockDialog"
            r9.show(r8, r10)
        L_0x011c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.blockreportspam.ShowBlockReportSpamDialogReceiver.onReceive(android.content.Context, android.content.Intent):void");
    }
}
