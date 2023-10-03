package com.android.dialer.app.calllog;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.support.p000v4.app.FragmentManager;
import android.support.p002v7.widget.RecyclerView;
import android.view.View;
import com.android.dialer.app.calllog.CallLogListItemViewHolder;
import com.android.dialer.blocking.FilteredNumberAsyncQueryHandler;
import com.android.dialer.blockreportspam.BlockReportSpamDialogs;
import com.android.dialer.common.LogUtil;
import com.android.dialer.logging.ContactSource$Type;
import com.android.dialer.logging.DialerImpression$Type;
import com.android.dialer.logging.Logger;
import com.android.dialer.logging.LoggingBindingsStub;
import com.android.dialer.spam.Spam;
import com.android.dialer.spam.SpamComponent;
import com.android.dialer.spam.SpamSettings;
import com.android.dialer.spam.promo.SpamBlockingPromoHelper;
import com.android.dialer.spam.stub.SpamSettingsStub;

public class BlockReportSpamListener implements CallLogListItemViewHolder.OnClickListener {
    private final RecyclerView.Adapter adapter;
    private final Context context;
    private final FilteredNumberAsyncQueryHandler filteredNumberAsyncQueryHandler;
    private final FragmentManager fragmentManager;
    private final View rootView;
    private final Spam spam;
    private final SpamBlockingPromoHelper spamBlockingPromoHelper;
    private final SpamSettings spamSettings;

    public BlockReportSpamListener(Context context2, View view, FragmentManager fragmentManager2, RecyclerView.Adapter adapter2, FilteredNumberAsyncQueryHandler filteredNumberAsyncQueryHandler2) {
        this.context = context2;
        this.rootView = view;
        this.fragmentManager = fragmentManager2;
        this.adapter = adapter2;
        this.filteredNumberAsyncQueryHandler = filteredNumberAsyncQueryHandler2;
        this.spam = SpamComponent.get(context2).spam();
        this.spamSettings = SpamComponent.get(context2).spamSettings();
        this.spamBlockingPromoHelper = new SpamBlockingPromoHelper(context2, this.spamSettings);
    }

    public /* synthetic */ void lambda$onBlock$2$BlockReportSpamListener(Uri uri) {
        ((LoggingBindingsStub) Logger.get(this.context)).logImpression(DialerImpression$Type.USER_ACTION_BLOCKED_NUMBER);
        this.adapter.notifyDataSetChanged();
    }

    public /* synthetic */ void lambda$onBlock$3$BlockReportSpamListener(String str, String str2, int i, ContactSource$Type contactSource$Type) {
        LogUtil.m9i("BlockReportSpamListener.onBlock", "onClick", new Object[0]);
        ((SpamSettingsStub) this.spamSettings).isSpamEnabled();
        this.filteredNumberAsyncQueryHandler.blockNumber(new FilteredNumberAsyncQueryHandler.OnBlockNumberListener() {
            public final void onBlockComplete(Uri uri) {
                BlockReportSpamListener.this.lambda$onBlock$2$BlockReportSpamListener(uri);
            }
        }, str, str2);
        this.spamBlockingPromoHelper.shouldShowSpamBlockingPromo();
    }

    public /* synthetic */ void lambda$onBlockReportSpam$0$BlockReportSpamListener(Uri uri) {
        ((LoggingBindingsStub) Logger.get(this.context)).logImpression(DialerImpression$Type.USER_ACTION_BLOCKED_NUMBER);
        this.adapter.notifyDataSetChanged();
    }

    public /* synthetic */ void lambda$onBlockReportSpam$1$BlockReportSpamListener(String str, String str2, int i, ContactSource$Type contactSource$Type, boolean z) {
        LogUtil.m9i("BlockReportSpamListener.onBlockReportSpam", "onClick", new Object[0]);
        if (z) {
            ((SpamSettingsStub) this.spamSettings).isSpamEnabled();
        }
        this.filteredNumberAsyncQueryHandler.blockNumber(new FilteredNumberAsyncQueryHandler.OnBlockNumberListener() {
            public final void onBlockComplete(Uri uri) {
                BlockReportSpamListener.this.lambda$onBlockReportSpam$0$BlockReportSpamListener(uri);
            }
        }, str, str2);
        if (z) {
            this.spamBlockingPromoHelper.shouldShowSpamBlockingPromo();
        }
    }

    public /* synthetic */ void lambda$onReportNotSpam$6$BlockReportSpamListener(String str, String str2, int i, ContactSource$Type contactSource$Type) {
        LogUtil.m9i("BlockReportSpamListener.onReportNotSpam", "onClick", new Object[0]);
        ((SpamSettingsStub) this.spamSettings).isSpamEnabled();
        this.adapter.notifyDataSetChanged();
    }

    public /* synthetic */ void lambda$onUnblock$4$BlockReportSpamListener(int i, ContentValues contentValues) {
        ((LoggingBindingsStub) Logger.get(this.context)).logImpression(DialerImpression$Type.USER_ACTION_UNBLOCKED_NUMBER);
        this.adapter.notifyDataSetChanged();
    }

    public /* synthetic */ void lambda$onUnblock$5$BlockReportSpamListener(boolean z, String str, String str2, int i, ContactSource$Type contactSource$Type, Integer num) {
        LogUtil.m9i("BlockReportSpamListener.onUnblock", "onClick", new Object[0]);
        if (z) {
            ((SpamSettingsStub) this.spamSettings).isSpamEnabled();
        }
        this.filteredNumberAsyncQueryHandler.unblock((FilteredNumberAsyncQueryHandler.OnUnblockNumberListener) new FilteredNumberAsyncQueryHandler.OnUnblockNumberListener() {
            public final void onUnblockComplete(int i, ContentValues contentValues) {
                BlockReportSpamListener.this.lambda$onUnblock$4$BlockReportSpamListener(i, contentValues);
            }
        }, num);
    }

    public void onBlock(String str, String str2, String str3, int i, ContactSource$Type contactSource$Type) {
        ((SpamSettingsStub) this.spamSettings).isSpamEnabled();
        BlockReportSpamDialogs.DialogFragmentForBlockingNumberAndReportingAsSpam.newInstance(str, false, new BlockReportSpamDialogs.OnConfirmListener(str2, str3, i, contactSource$Type) {
            private final /* synthetic */ String f$1;
            private final /* synthetic */ String f$2;
            private final /* synthetic */ int f$3;
            private final /* synthetic */ ContactSource$Type f$4;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
                this.f$4 = r5;
            }

            public final void onClick() {
                BlockReportSpamListener.this.lambda$onBlock$3$BlockReportSpamListener(this.f$1, this.f$2, this.f$3, this.f$4);
            }
        }, (DialogInterface.OnDismissListener) null).show(this.fragmentManager, "BlockDialog");
    }

    public void onBlockReportSpam(String str, String str2, String str3, int i, ContactSource$Type contactSource$Type) {
        ((SpamSettingsStub) this.spamSettings).isDialogReportSpamCheckedByDefault();
        BlockReportSpamDialogs.DialogFragmentForBlockingNumberAndOptionallyReportingAsSpam.newInstance(str, false, new BlockReportSpamDialogs.OnSpamDialogClickListener(str2, str3, i, contactSource$Type) {
            private final /* synthetic */ String f$1;
            private final /* synthetic */ String f$2;
            private final /* synthetic */ int f$3;
            private final /* synthetic */ ContactSource$Type f$4;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
                this.f$4 = r5;
            }

            public final void onClick(boolean z) {
                BlockReportSpamListener.this.lambda$onBlockReportSpam$1$BlockReportSpamListener(this.f$1, this.f$2, this.f$3, this.f$4, z);
            }
        }, (DialogInterface.OnDismissListener) null).show(this.fragmentManager, "BlockReportSpamDialog");
    }

    public void onReportNotSpam(String str, String str2, String str3, int i, ContactSource$Type contactSource$Type) {
        BlockReportSpamDialogs.DialogFragmentForReportingNotSpam.newInstance(str, new BlockReportSpamDialogs.OnConfirmListener(str2, str3, i, contactSource$Type) {
            private final /* synthetic */ String f$1;
            private final /* synthetic */ String f$2;
            private final /* synthetic */ int f$3;
            private final /* synthetic */ ContactSource$Type f$4;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
                this.f$4 = r5;
            }

            public final void onClick() {
                BlockReportSpamListener.this.lambda$onReportNotSpam$6$BlockReportSpamListener(this.f$1, this.f$2, this.f$3, this.f$4);
            }
        }, (DialogInterface.OnDismissListener) null).show(this.fragmentManager, "NotSpamDialog");
    }

    public void onUnblock(String str, String str2, String str3, int i, ContactSource$Type contactSource$Type, boolean z, Integer num) {
        String str4 = str;
        BlockReportSpamDialogs.DialogFragmentForUnblockingNumberAndReportingAsNotSpam.newInstance(str, z, new BlockReportSpamDialogs.OnConfirmListener(z, str2, str3, i, contactSource$Type, num) {
            private final /* synthetic */ boolean f$1;
            private final /* synthetic */ String f$2;
            private final /* synthetic */ String f$3;
            private final /* synthetic */ int f$4;
            private final /* synthetic */ ContactSource$Type f$5;
            private final /* synthetic */ Integer f$6;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
                this.f$4 = r5;
                this.f$5 = r6;
                this.f$6 = r7;
            }

            public final void onClick() {
                BlockReportSpamListener.this.lambda$onUnblock$5$BlockReportSpamListener(this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6);
            }
        }, (DialogInterface.OnDismissListener) null).show(this.fragmentManager, "UnblockDialog");
    }
}
