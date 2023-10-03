package com.android.dialer.calllog.p004ui.menu;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import com.android.dialer.CoalescedIds;
import com.android.dialer.R;
import com.android.dialer.calldetails.CallDetailsActivity;
import com.android.dialer.calldetails.CallDetailsHeaderInfo;
import com.android.dialer.calllog.CallLogComponent;
import com.android.dialer.calllog.model.CoalescedRow;
import com.android.dialer.calllogutils.CallLogDates;
import com.android.dialer.common.concurrent.DefaultFutureCallback;
import com.android.dialer.historyitemactions.HistoryItemActionBottomSheet;
import com.android.dialer.historyitemactions.HistoryItemActionModule;
import com.android.dialer.historyitemactions.HistoryItemActionModuleInfo;
import com.android.dialer.historyitemactions.HistoryItemActionModulesBuilder;
import com.android.dialer.historyitemactions.HistoryItemBottomSheetHeaderInfo;
import com.android.dialer.historyitemactions.IntentModule;
import com.android.dialer.phonenumberutil.PhoneNumberHelper;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.MoreExecutors;
import java.util.List;

/* renamed from: com.android.dialer.calllog.ui.menu.NewCallLogMenu */
public final class NewCallLogMenu {
    private static boolean canSupportAssistedDialing(CoalescedRow coalescedRow) {
        return !TextUtils.isEmpty(coalescedRow.getNumberAttributes().getLookupUri());
    }

    static /* synthetic */ void lambda$createOnClickListener$0(Context context, CoalescedRow coalescedRow, View view) {
        HistoryItemBottomSheetHeaderInfo.Builder newBuilder = HistoryItemBottomSheetHeaderInfo.newBuilder();
        newBuilder.setNumber(coalescedRow.getNumber());
        newBuilder.setPhotoInfo(CallLogDates.fromCoalescedRow(context, coalescedRow));
        newBuilder.setPrimaryText(CallLogDates.buildPrimaryText(context, coalescedRow).toString());
        newBuilder.setSecondaryText(CallLogDates.buildSecondaryTextForBottomSheet(context, coalescedRow).toString());
        HistoryItemBottomSheetHeaderInfo historyItemBottomSheetHeaderInfo = (HistoryItemBottomSheetHeaderInfo) newBuilder.build();
        HistoryItemActionModuleInfo.Builder newBuilder2 = HistoryItemActionModuleInfo.newBuilder();
        newBuilder2.setNormalizedNumber(coalescedRow.getNumber().getNormalizedNumber());
        newBuilder2.setCountryIso(coalescedRow.getNumber().getCountryIso());
        newBuilder2.setName(coalescedRow.getNumberAttributes().getName());
        newBuilder2.setCallType(coalescedRow.getCallType());
        newBuilder2.setFeatures(coalescedRow.getFeatures());
        newBuilder2.setLookupUri(coalescedRow.getNumberAttributes().getLookupUri());
        newBuilder2.setPhoneAccountComponentName(coalescedRow.getPhoneAccountComponentName());
        newBuilder2.setCanReportAsInvalidNumber(coalescedRow.getNumberAttributes().getCanReportAsInvalidNumber());
        newBuilder2.setCanSupportAssistedDialing(canSupportAssistedDialing(coalescedRow));
        newBuilder2.setCanSupportCarrierVideoCall(coalescedRow.getNumberAttributes().getCanSupportCarrierVideoCall());
        newBuilder2.setIsBlocked(coalescedRow.getNumberAttributes().getIsBlocked());
        newBuilder2.setIsEmergencyNumber(coalescedRow.getNumberAttributes().getIsEmergencyNumber());
        newBuilder2.setIsSpam(coalescedRow.getNumberAttributes().getIsSpam());
        newBuilder2.setIsVoicemailCall(coalescedRow.getIsVoicemailCall());
        newBuilder2.setContactSource(coalescedRow.getNumberAttributes().getContactSource());
        newBuilder2.setHost(HistoryItemActionModuleInfo.Host.CALL_LOG);
        HistoryItemActionModulesBuilder historyItemActionModulesBuilder = new HistoryItemActionModulesBuilder(context, (HistoryItemActionModuleInfo) newBuilder2.build());
        if (PhoneNumberHelper.canPlaceCallsTo(coalescedRow.getNumber().getNormalizedNumber(), coalescedRow.getNumberPresentation())) {
            historyItemActionModulesBuilder.addModuleForVoiceCall();
            historyItemActionModulesBuilder.addModuleForVideoCall();
            historyItemActionModulesBuilder.addModuleForSendingTextMessage();
            historyItemActionModulesBuilder.addModuleForDivider();
            historyItemActionModulesBuilder.addModuleForAddingToContacts();
            historyItemActionModulesBuilder.addModuleForBlockedOrSpamNumber();
            historyItemActionModulesBuilder.addModuleForCopyingNumber();
        }
        List<HistoryItemActionModule> build = historyItemActionModulesBuilder.build();
        boolean z = !coalescedRow.getIsVoicemailCall() && coalescedRow.getNumberAttributes().getCanReportAsInvalidNumber();
        CoalescedIds coalescedIds = coalescedRow.getCoalescedIds();
        CallDetailsHeaderInfo.Builder newBuilder3 = CallDetailsHeaderInfo.newBuilder();
        newBuilder3.setDialerPhoneNumber(coalescedRow.getNumber());
        newBuilder3.setPhotoInfo(CallLogDates.fromCoalescedRow(context, coalescedRow));
        newBuilder3.setPrimaryText(CallLogDates.buildPrimaryText(context, coalescedRow).toString());
        newBuilder3.setSecondaryText(CallLogDates.buildSecondaryTextForBottomSheet(context, coalescedRow).toString());
        build.add(new IntentModule(context, CallDetailsActivity.newInstance(context, coalescedIds, (CallDetailsHeaderInfo) newBuilder3.build(), z, canSupportAssistedDialing(coalescedRow)), R.string.call_details_menu_label, R.drawable.quantum_ic_info_outline_vd_theme_24));
        build.add(new DeleteCallLogItemModule(context, coalescedRow.getCoalescedIds()));
        HistoryItemActionBottomSheet.show(context, historyItemBottomSheetHeaderInfo, build);
        if (!coalescedRow.getIsRead() && coalescedRow.getCallType() == 3) {
            Futures.addCallback(CallLogComponent.get(context).getClearMissedCalls().clearBySystemCallLogId(coalescedRow.getCoalescedIds().getCoalescedIdList()), new DefaultFutureCallback(), MoreExecutors.directExecutor());
        }
    }
}
