package com.android.dialer.historyitemactions;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.p002v7.appcompat.R$style;
import android.text.TextUtils;
import com.android.dialer.R;
import com.android.dialer.blockreportspam.BlockReportSpamDialogInfo;
import com.android.dialer.callintent.CallInitiationType$Type;
import com.android.dialer.callintent.CallIntentBuilder;
import com.android.dialer.common.Assert;
import com.android.dialer.duo.DuoComponent;
import com.android.dialer.duo.stub.DuoStub;
import com.android.dialer.historyitemactions.HistoryItemActionModuleInfo;
import com.android.dialer.logging.DialerImpression$Type;
import com.android.dialer.logging.ReportingLocation$Type;
import com.android.dialer.spam.Spam;
import com.android.dialer.util.CallUtil;
import com.android.dialer.util.PermissionsUtil;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public final class HistoryItemActionModulesBuilder {
    private static final ImmutableMap<Integer, DialerImpression$Type> CALL_LOG_IMPRESSIONS = new ImmutableMap.Builder().put(1, DialerImpression$Type.ADD_TO_A_CONTACT_FROM_CALL_LOG).put(2, DialerImpression$Type.CALL_LOG_BLOCK_NUMBER).put(3, DialerImpression$Type.CALL_LOG_BLOCK_REPORT_SPAM).put(4, DialerImpression$Type.CALL_LOG_REPORT_AS_NOT_SPAM).put(5, DialerImpression$Type.IMS_VIDEO_REQUESTED_FROM_CALL_LOG).put(6, DialerImpression$Type.LIGHTBRINGER_VIDEO_REQUESTED_FROM_CALL_LOG).put(7, DialerImpression$Type.LIGHTBRINGER_NON_CONTACT_VIDEO_REQUESTED_FROM_CALL_LOG).put(8, DialerImpression$Type.CALL_LOG_SEND_MESSAGE).put(9, DialerImpression$Type.CALL_LOG_UNBLOCK_NUMBER).build();
    /* access modifiers changed from: private */
    public final Context context;
    /* access modifiers changed from: private */
    public final HistoryItemActionModuleInfo moduleInfo;
    private final List<HistoryItemActionModule> modules;

    public HistoryItemActionModulesBuilder(Context context2, HistoryItemActionModuleInfo historyItemActionModuleInfo) {
        Assert.checkArgument(historyItemActionModuleInfo.getHost() != HistoryItemActionModuleInfo.Host.UNKNOWN, "A host must be specified.", new Object[0]);
        this.context = context2;
        this.moduleInfo = historyItemActionModuleInfo;
        this.modules = new ArrayList();
    }

    private CallInitiationType$Type getCallInitiationType() {
        int ordinal = this.moduleInfo.getHost().ordinal();
        if (ordinal == 1) {
            return CallInitiationType$Type.CALL_LOG;
        }
        if (ordinal == 2) {
            return CallInitiationType$Type.VOICEMAIL_LOG;
        }
        throw new UnsupportedOperationException(String.format("Unsupported host: %s", new Object[]{this.moduleInfo.getHost()}));
    }

    private Optional<DialerImpression$Type> getImpression(int i) {
        int ordinal = this.moduleInfo.getHost().ordinal();
        if (ordinal == 1) {
            return Optional.of(CALL_LOG_IMPRESSIONS.get(Integer.valueOf(i)));
        }
        if (ordinal == 2) {
            return Optional.empty();
        }
        throw new UnsupportedOperationException(String.format("Unsupported host: %s", new Object[]{this.moduleInfo.getHost()}));
    }

    private ImmutableList<DialerImpression$Type> getImpressions(int... iArr) {
        Assert.isNotNull(iArr);
        ImmutableList.Builder builder = new ImmutableList.Builder();
        for (int impression : iArr) {
            getImpression(impression).ifPresent(new Consumer() {
                public final void accept(Object obj) {
                    ImmutableList.Builder.this.add((Object) (DialerImpression$Type) obj);
                }
            });
        }
        return builder.build();
    }

    private boolean isExistingContact() {
        return !TextUtils.isEmpty(this.moduleInfo.getLookupUri()) && !R$style.isEncodedContactUri(Uri.parse(this.moduleInfo.getLookupUri()));
    }

    public HistoryItemActionModulesBuilder addModuleForAddingToContacts() {
        if (PermissionsUtil.hasPermission(this.context, "android.permission.WRITE_CONTACTS") && !this.moduleInfo.getIsEmergencyNumber() && !this.moduleInfo.getIsVoicemailCall() && !Spam.shouldShowAsSpam(this.moduleInfo.getIsSpam(), this.moduleInfo.getCallType()) && !this.moduleInfo.getIsBlocked() && !isExistingContact() && !TextUtils.isEmpty(this.moduleInfo.getNormalizedNumber())) {
            Intent intent = new Intent("android.intent.action.INSERT_OR_EDIT");
            intent.setType("vnd.android.cursor.item/contact");
            intent.putExtra("phone", this.moduleInfo.getNormalizedNumber());
            if (!TextUtils.isEmpty(this.moduleInfo.getName())) {
                intent.putExtra("name", this.moduleInfo.getName());
            }
            this.modules.add(new IntentModule(this.context, intent, R.string.add_to_contacts, R.drawable.quantum_ic_person_add_vd_theme_24, getImpressions(1)));
        }
        return this;
    }

    public HistoryItemActionModulesBuilder addModuleForBlockedOrSpamNumber() {
        ReportingLocation$Type reportingLocation$Type;
        Object obj;
        if (!this.moduleInfo.getIsEmergencyNumber() && !this.moduleInfo.getIsVoicemailCall()) {
            BlockReportSpamDialogInfo.Builder newBuilder = BlockReportSpamDialogInfo.newBuilder();
            newBuilder.setNormalizedNumber(this.moduleInfo.getNormalizedNumber());
            newBuilder.setCountryIso(this.moduleInfo.getCountryIso());
            newBuilder.setCallType(this.moduleInfo.getCallType());
            int ordinal = this.moduleInfo.getHost().ordinal();
            if (ordinal == 1) {
                reportingLocation$Type = ReportingLocation$Type.CALL_LOG_HISTORY;
            } else if (ordinal == 2) {
                reportingLocation$Type = ReportingLocation$Type.VOICEMAIL_HISTORY;
            } else {
                throw new UnsupportedOperationException(String.format("Unsupported host: %s", new Object[]{this.moduleInfo.getHost()}));
            }
            newBuilder.setReportingLocation(reportingLocation$Type);
            newBuilder.setContactSource(this.moduleInfo.getContactSource());
            BlockReportSpamDialogInfo blockReportSpamDialogInfo = (BlockReportSpamDialogInfo) newBuilder.build();
            if (Spam.shouldShowAsSpam(this.moduleInfo.getIsSpam(), this.moduleInfo.getCallType())) {
                this.modules.add(new BlockReportSpamModules$1(this.context, blockReportSpamDialogInfo, getImpression(4)));
                List<HistoryItemActionModule> list = this.modules;
                if (this.moduleInfo.getIsBlocked()) {
                    obj = new BlockReportSpamModules$3(this.context, blockReportSpamDialogInfo, getImpression(9));
                } else {
                    obj = new BlockReportSpamModules$2(this.context, blockReportSpamDialogInfo, getImpression(2));
                }
                list.add(obj);
                return this;
            } else if (this.moduleInfo.getIsBlocked()) {
                this.modules.add(new BlockReportSpamModules$3(this.context, blockReportSpamDialogInfo, getImpression(9)));
                return this;
            } else {
                this.modules.add(new BlockReportSpamModules$4(this.context, blockReportSpamDialogInfo, getImpression(3)));
            }
        }
        return this;
    }

    public HistoryItemActionModulesBuilder addModuleForCopyingNumber() {
        if (TextUtils.isEmpty(this.moduleInfo.getNormalizedNumber())) {
            return this;
        }
        this.modules.add(new HistoryItemActionModule() {
            public int getDrawableId() {
                return R.drawable.quantum_ic_content_copy_vd_theme_24;
            }

            public int getStringId() {
                return R.string.copy_number;
            }

            public boolean onClick() {
                R$style.copyText(HistoryItemActionModulesBuilder.this.context, (CharSequence) null, HistoryItemActionModulesBuilder.this.moduleInfo.getNormalizedNumber(), true);
                return false;
            }
        });
        return this;
    }

    public HistoryItemActionModulesBuilder addModuleForDivider() {
        if (this.modules.isEmpty()) {
            return this;
        }
        this.modules.add(new DividerModule());
        return this;
    }

    public HistoryItemActionModulesBuilder addModuleForSendingTextMessage() {
        if (PermissionsUtil.hasPermission(this.context, "android.permission.SEND_SMS") && !this.moduleInfo.getIsEmergencyNumber() && !this.moduleInfo.getIsVoicemailCall() && !this.moduleInfo.getIsBlocked() && !TextUtils.isEmpty(this.moduleInfo.getNormalizedNumber())) {
            List<HistoryItemActionModule> list = this.modules;
            Context context2 = this.context;
            String normalizedNumber = this.moduleInfo.getNormalizedNumber();
            list.add(new IntentModule(context2, CallUtil.getSendSmsIntent(normalizedNumber), R.string.send_a_message, R.drawable.quantum_ic_message_vd_theme_24, getImpressions(8)));
        }
        return this;
    }

    public HistoryItemActionModulesBuilder addModuleForVideoCall() {
        if (!this.moduleInfo.getIsEmergencyNumber() && !this.moduleInfo.getIsVoicemailCall() && !Spam.shouldShowAsSpam(this.moduleInfo.getIsSpam(), this.moduleInfo.getCallType()) && !this.moduleInfo.getIsBlocked()) {
            CallIntentBuilder isVideoCall = new CallIntentBuilder(this.moduleInfo.getNormalizedNumber(), getCallInitiationType()).setAllowAssistedDial(this.moduleInfo.getCanSupportAssistedDialing()).setIsVideoCall(true);
            if ((this.moduleInfo.getFeatures() & 1) == 1) {
                ((DuoStub) DuoComponent.get(this.context).getDuo()).isDuoAccount(this.moduleInfo.getPhoneAccountComponentName());
                this.modules.add(IntentModule.newCallModule(this.context, isVideoCall.setIsDuoCall(false), getImpressions(5)));
                return this;
            }
            int videoCallingAvailability = CallUtil.getVideoCallingAvailability(this.context);
            if (((videoCallingAvailability & 1) == 1) && ((videoCallingAvailability & 2) == 2) && this.moduleInfo.getCanSupportCarrierVideoCall()) {
                this.modules.add(IntentModule.newCallModule(this.context, isVideoCall, getImpressions(5)));
            } else {
                ((DuoStub) DuoComponent.get(this.context).getDuo()).isInstalled(this.context);
            }
        }
        return this;
    }

    public HistoryItemActionModulesBuilder addModuleForVoiceCall() {
        if (this.moduleInfo.getIsBlocked()) {
            return this;
        }
        this.modules.add(IntentModule.newCallModule(this.context, new CallIntentBuilder(this.moduleInfo.getNormalizedNumber(), getCallInitiationType()).setAllowAssistedDial(this.moduleInfo.getCanSupportAssistedDialing())));
        return this;
    }

    public List<HistoryItemActionModule> build() {
        return new ArrayList(this.modules);
    }
}
