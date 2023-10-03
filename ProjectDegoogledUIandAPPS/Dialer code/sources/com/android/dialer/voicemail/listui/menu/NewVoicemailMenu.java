package com.android.dialer.voicemail.listui.menu;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import com.android.dialer.calllogutils.CallLogDates;
import com.android.dialer.historyitemactions.HistoryItemActionBottomSheet;
import com.android.dialer.historyitemactions.HistoryItemActionModuleInfo;
import com.android.dialer.historyitemactions.HistoryItemActionModulesBuilder;
import com.android.dialer.historyitemactions.HistoryItemBottomSheetHeaderInfo;
import com.android.dialer.voicemail.model.VoicemailEntry;

public final class NewVoicemailMenu {
    static /* synthetic */ void lambda$createOnClickListener$0(Context context, VoicemailEntry voicemailEntry, View view) {
        HistoryItemBottomSheetHeaderInfo.Builder newBuilder = HistoryItemBottomSheetHeaderInfo.newBuilder();
        newBuilder.setNumber(voicemailEntry.getNumber());
        newBuilder.setPhotoInfo(CallLogDates.fromVoicemailEntry(voicemailEntry));
        StringBuilder sb = new StringBuilder();
        if (!TextUtils.isEmpty(voicemailEntry.getNumberAttributes().getName())) {
            sb.append(voicemailEntry.getNumberAttributes().getName());
        } else if (!TextUtils.isEmpty(voicemailEntry.getFormattedNumber())) {
            sb.append(voicemailEntry.getFormattedNumber());
        } else {
            sb.append("Unknown");
        }
        newBuilder.setPrimaryText(sb.toString());
        newBuilder.setSecondaryText(voicemailEntry.getGeocodedLocation());
        HistoryItemActionModuleInfo.Builder newBuilder2 = HistoryItemActionModuleInfo.newBuilder();
        newBuilder2.setNormalizedNumber(voicemailEntry.getNumber().getNormalizedNumber());
        newBuilder2.setCountryIso(voicemailEntry.getNumber().getCountryIso());
        newBuilder2.setName(voicemailEntry.getNumberAttributes().getName());
        newBuilder2.setCallType(voicemailEntry.getCallType());
        newBuilder2.setLookupUri(voicemailEntry.getNumberAttributes().getLookupUri());
        newBuilder2.setPhoneAccountComponentName(voicemailEntry.getPhoneAccountComponentName());
        newBuilder2.setCanReportAsInvalidNumber(voicemailEntry.getNumberAttributes().getCanReportAsInvalidNumber());
        newBuilder2.setCanSupportAssistedDialing(!TextUtils.isEmpty(voicemailEntry.getNumberAttributes().getLookupUri()));
        newBuilder2.setCanSupportCarrierVideoCall(voicemailEntry.getNumberAttributes().getCanSupportCarrierVideoCall());
        newBuilder2.setIsBlocked(voicemailEntry.getNumberAttributes().getIsBlocked());
        newBuilder2.setIsEmergencyNumber(voicemailEntry.getNumberAttributes().getIsEmergencyNumber());
        newBuilder2.setIsSpam(voicemailEntry.getNumberAttributes().getIsSpam());
        newBuilder2.setIsVoicemailCall(false);
        newBuilder2.setContactSource(voicemailEntry.getNumberAttributes().getContactSource());
        newBuilder2.setHost(HistoryItemActionModuleInfo.Host.VOICEMAIL);
        HistoryItemActionModulesBuilder historyItemActionModulesBuilder = new HistoryItemActionModulesBuilder(context, (HistoryItemActionModuleInfo) newBuilder2.build());
        historyItemActionModulesBuilder.addModuleForAddingToContacts();
        historyItemActionModulesBuilder.addModuleForSendingTextMessage();
        historyItemActionModulesBuilder.addModuleForDivider();
        historyItemActionModulesBuilder.addModuleForBlockedOrSpamNumber();
        historyItemActionModulesBuilder.addModuleForCopyingNumber();
        HistoryItemActionBottomSheet.show(context, (HistoryItemBottomSheetHeaderInfo) newBuilder.build(), historyItemActionModulesBuilder.build());
    }
}
