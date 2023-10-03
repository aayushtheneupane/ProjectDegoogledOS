package com.android.dialer.historyitemactions;

import android.content.Context;
import android.content.Intent;
import android.support.p000v4.content.LocalBroadcastManager;
import com.android.dialer.R;
import com.android.dialer.blockreportspam.BlockReportSpamDialogInfo;
import com.android.dialer.common.LogUtil;
import com.android.dialer.logging.DialerImpression$Type;
import com.android.dialer.logging.Logger;
import com.android.dialer.logging.LoggingBindings;
import com.android.dialer.logging.LoggingBindingsStub;
import com.android.dialer.protos.ProtoParsers;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

class BlockReportSpamModules$1 implements HistoryItemActionModule {
    final /* synthetic */ BlockReportSpamDialogInfo val$blockReportSpamDialogInfo;
    final /* synthetic */ Context val$context;
    final /* synthetic */ Optional val$impression;

    BlockReportSpamModules$1(Context context, BlockReportSpamDialogInfo blockReportSpamDialogInfo, Optional optional) {
        this.val$context = context;
        this.val$blockReportSpamDialogInfo = blockReportSpamDialogInfo;
        this.val$impression = optional;
    }

    public int getDrawableId() {
        return R.drawable.quantum_ic_report_off_vd_theme_24;
    }

    public int getStringId() {
        return R.string.not_spam;
    }

    public boolean onClick() {
        Context context = this.val$context;
        BlockReportSpamDialogInfo blockReportSpamDialogInfo = this.val$blockReportSpamDialogInfo;
        LogUtil.enterBlock("ShowBlockReportSpamDialogNotifier.notifyShowDialogToReportNotSpam");
        Intent intent = new Intent();
        intent.setAction("show_dialog_to_report_not_spam");
        ProtoParsers.put(intent, "dialog_info", blockReportSpamDialogInfo);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
        Optional optional = this.val$impression;
        LoggingBindings loggingBindings = Logger.get(this.val$context);
        Objects.requireNonNull(loggingBindings);
        optional.ifPresent(new Consumer() {
            public final void accept(Object obj) {
                ((LoggingBindingsStub) LoggingBindings.this).logImpression((DialerImpression$Type) obj);
            }
        });
        return true;
    }
}
