package com.android.dialer.voicemail.listui.error;

import android.content.Context;
import android.database.Cursor;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.p002v7.appcompat.R$style;
import android.telecom.PhoneAccountHandle;
import android.view.View;
import com.android.dialer.R;
import com.android.dialer.common.LogUtil;
import com.android.dialer.common.PerAccountSharedPreferences;
import com.android.dialer.configprovider.ConfigProviderComponent;
import com.android.dialer.configprovider.SharedPrefConfigProvider;
import com.android.dialer.logging.DialerImpression$Type;
import com.android.dialer.logging.Logger;
import com.android.dialer.logging.LoggingBindingsStub;
import com.android.dialer.voicemail.listui.error.VoicemailErrorMessage;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.android.voicemail.VoicemailClient;
import com.android.voicemail.VoicemailComponent;
import java.util.ArrayList;
import java.util.List;

public class OmtpVoicemailMessageCreator {
    public static VoicemailErrorMessage create(Context context, VoicemailStatus voicemailStatus, VoicemailStatusReader voicemailStatusReader) {
        String str;
        String str2;
        int i;
        boolean z;
        CharSequence charSequence;
        String str3;
        DialerImpression$Type dialerImpression$Type;
        String str4;
        DialerImpression$Type dialerImpression$Type2;
        Context context2 = context;
        VoicemailStatus voicemailStatus2 = voicemailStatus;
        VoicemailErrorMessage maybeCreateTosMessage = new VoicemailTosMessageCreator(context2, voicemailStatus2, voicemailStatusReader).maybeCreateTosMessage();
        if (maybeCreateTosMessage != null) {
            return maybeCreateTosMessage;
        }
        if (voicemailStatus2.configurationState == 0 && voicemailStatus2.dataChannelState == 0 && voicemailStatus2.notificationChannelState == 0) {
            int i2 = voicemailStatus2.quotaOccupied;
            if (i2 == -1 || (i = voicemailStatus2.quotaTotal) == -1) {
                ((LoggingBindingsStub) Logger.get(context)).logImpression(DialerImpression$Type.VVM_QUOTA_CHECK_UNAVAILABLE);
                return null;
            }
            float f = ((float) i2) / ((float) i);
            if (f < 0.9f) {
                return null;
            }
            boolean z2 = f >= 0.99f;
            PhoneAccountHandle phoneAccountHandle = voicemailStatus.getPhoneAccountHandle();
            PerAccountSharedPreferences perAccountSharedPreferences = new PerAccountSharedPreferences(phoneAccountHandle, PreferenceManager.getDefaultSharedPreferences(context));
            VoicemailClient voicemailClient = VoicemailComponent.get(context).getVoicemailClient();
            if (z2) {
                z = perAccountSharedPreferences.getBoolean("voicemail_archive_promo_was_dismissed", false);
            } else {
                z = perAccountSharedPreferences.getBoolean("voicemail_archive_almost_full_promo_was_dismissed", false);
            }
            if (!z && !voicemailClient.isVoicemailArchiveEnabled(context2, phoneAccountHandle) && voicemailClient.isVoicemailArchiveAvailable(context2)) {
                if (z2) {
                    ((LoggingBindingsStub) Logger.get(context)).logImpression(DialerImpression$Type.VVM_USER_SHOWN_VM_FULL_PROMO);
                    String string = context2.getString(R.string.voicemail_error_inbox_full_turn_archive_on_title);
                    CharSequence text = context2.getText(R.string.voicemail_error_inbox_full_turn_archive_on_message);
                    DialerImpression$Type dialerImpression$Type3 = DialerImpression$Type.VVM_USER_ENABLED_ARCHIVE_FROM_VM_FULL_PROMO;
                    dialerImpression$Type = DialerImpression$Type.VVM_USER_DISMISSED_VM_FULL_PROMO;
                    str3 = "voicemail_archive_promo_was_dismissed";
                    charSequence = text;
                    dialerImpression$Type2 = dialerImpression$Type3;
                    str4 = string;
                } else {
                    ((LoggingBindingsStub) Logger.get(context)).logImpression(DialerImpression$Type.VVM_USER_SHOWN_VM_ALMOST_FULL_PROMO);
                    String string2 = context2.getString(R.string.voicemail_error_inbox_almost_full_turn_archive_on_title);
                    CharSequence text2 = context2.getText(R.string.voicemail_error_inbox_almost_full_turn_archive_on_message);
                    DialerImpression$Type dialerImpression$Type4 = DialerImpression$Type.VVM_USER_ENABLED_ARCHIVE_FROM_VM_ALMOST_FULL_PROMO;
                    dialerImpression$Type = DialerImpression$Type.VVM_USER_DISMISSED_VM_ALMOST_FULL_PROMO;
                    charSequence = text2;
                    str3 = "voicemail_archive_almost_full_promo_was_dismissed";
                    str4 = string2;
                    dialerImpression$Type2 = dialerImpression$Type4;
                }
                VoicemailErrorMessage.C05986 r16 = r0;
                String string3 = context2.getString(R.string.voicemail_action_turn_archive_on);
                Context context3 = context;
                String str5 = str4;
                VoicemailErrorMessage.C05986 r0 = new View.OnClickListener(context3, dialerImpression$Type2, voicemailClient, phoneAccountHandle, voicemailStatus, voicemailStatusReader) {
                    final /* synthetic */ Context val$context;
                    final /* synthetic */ DialerImpression$Type val$impressionToLog;
                    final /* synthetic */ PhoneAccountHandle val$phoneAccountHandle;
                    final /* synthetic */ VoicemailStatus val$status;
                    final /* synthetic */ VoicemailStatusReader val$statusReader;
                    final /* synthetic */ VoicemailClient val$voicemailClient;

                    public void onClick(
/*
Method generation error in method: com.android.dialer.voicemail.listui.error.VoicemailErrorMessage.6.onClick(android.view.View):void, dex: classes.dex
                    jadx.core.utils.exceptions.JadxRuntimeException: Method args not loaded: com.android.dialer.voicemail.listui.error.VoicemailErrorMessage.6.onClick(android.view.View):void, class status: UNLOADED
                    	at jadx.core.dex.nodes.MethodNode.getArgRegs(MethodNode.java:278)
                    	at jadx.core.codegen.MethodGen.addDefinition(MethodGen.java:116)
                    	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:313)
                    	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:271)
                    	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:240)
                    	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
                    	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                    	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                    	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:485)
                    	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:474)
                    	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
                    	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
                    	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
                    	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:497)
                    	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
                    	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
                    	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:676)
                    	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:607)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:364)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:250)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:221)
                    	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                    	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
                    	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:142)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                    	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
                    	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:142)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                    	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:211)
                    	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:204)
                    	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:318)
                    	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:271)
                    	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:240)
                    	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
                    	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                    	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                    	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:485)
                    	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:474)
                    	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
                    	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
                    	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
                    	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:497)
                    	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
                    	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
                    	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:112)
                    	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:78)
                    	at jadx.core.codegen.CodeGen.wrapCodeGen(CodeGen.java:44)
                    	at jadx.core.codegen.CodeGen.generateJavaCode(CodeGen.java:33)
                    	at jadx.core.codegen.CodeGen.generate(CodeGen.java:21)
                    	at jadx.core.ProcessClass.generateCode(ProcessClass.java:61)
                    	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:273)
                    
*/
                };
                return new VoicemailErrorMessage((CharSequence) str5, charSequence, new VoicemailErrorMessage.Action(string3, r0), new VoicemailErrorMessage.Action(context2.getString(R.string.voicemail_action_dimiss), new View.OnClickListener(context3, dialerImpression$Type, perAccountSharedPreferences, str3, voicemailStatusReader) {
                    final /* synthetic */ Context val$context;
                    final /* synthetic */ DialerImpression$Type val$impressionToLog;
                    final /* synthetic */ String val$preferenceKeyToUpdate;
                    final /* synthetic */ PerAccountSharedPreferences val$sharedPreferenceForAccount;
                    final /* synthetic */ VoicemailStatusReader val$statusReader;

                    public void onClick(
/*
Method generation error in method: com.android.dialer.voicemail.listui.error.VoicemailErrorMessage.7.onClick(android.view.View):void, dex: classes.dex
                    jadx.core.utils.exceptions.JadxRuntimeException: Method args not loaded: com.android.dialer.voicemail.listui.error.VoicemailErrorMessage.7.onClick(android.view.View):void, class status: UNLOADED
                    	at jadx.core.dex.nodes.MethodNode.getArgRegs(MethodNode.java:278)
                    	at jadx.core.codegen.MethodGen.addDefinition(MethodGen.java:116)
                    	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:313)
                    	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:271)
                    	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:240)
                    	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
                    	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                    	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                    	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:485)
                    	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:474)
                    	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
                    	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
                    	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
                    	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:497)
                    	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
                    	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
                    	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:676)
                    	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:607)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:364)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:231)
                    	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:123)
                    	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                    	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:787)
                    	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:640)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:364)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:231)
                    	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:123)
                    	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                    	at jadx.core.codegen.InsnGen.processVarArg(InsnGen.java:871)
                    	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:784)
                    	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:640)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:364)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:231)
                    	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:123)
                    	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:314)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:250)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:221)
                    	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                    	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
                    	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:142)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                    	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
                    	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:142)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                    	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:211)
                    	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:204)
                    	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:318)
                    	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:271)
                    	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:240)
                    	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
                    	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                    	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                    	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:485)
                    	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:474)
                    	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
                    	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
                    	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
                    	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:497)
                    	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
                    	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
                    	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:112)
                    	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:78)
                    	at jadx.core.codegen.CodeGen.wrapCodeGen(CodeGen.java:44)
                    	at jadx.core.codegen.CodeGen.generateJavaCode(CodeGen.java:33)
                    	at jadx.core.codegen.CodeGen.generate(CodeGen.java:21)
                    	at jadx.core.ProcessClass.generateCode(ProcessClass.java:61)
                    	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:273)
                    
*/
                }));
            } else if (z2) {
                ((LoggingBindingsStub) Logger.get(context)).logImpression(DialerImpression$Type.VVM_USER_SHOWN_VM_FULL_ERROR_MESSAGE);
                return new VoicemailErrorMessage((CharSequence) context2.getString(R.string.voicemail_error_inbox_full_title), (CharSequence) context2.getString(R.string.voicemail_error_inbox_full_message), new VoicemailErrorMessage.Action[0]);
            } else {
                ((LoggingBindingsStub) Logger.get(context)).logImpression(DialerImpression$Type.VVM_USER_SHOWN_VM_ALMOST_FULL_ERROR_MESSAGE);
                return new VoicemailErrorMessage((CharSequence) context2.getString(R.string.voicemail_error_inbox_near_full_title), (CharSequence) context2.getString(R.string.voicemail_error_inbox_near_full_message), new VoicemailErrorMessage.Action[0]);
            }
        } else if (3 == voicemailStatus2.configurationState && voicemailStatus2.dataChannelState == 0 && voicemailStatus2.notificationChannelState == 0) {
            return new VoicemailErrorMessage((CharSequence) context2.getString(R.string.voicemail_error_activating_title), (CharSequence) context2.getString(R.string.voicemail_error_activating_message), VoicemailErrorMessage.createCallVoicemailAction(context2, voicemailStatus.getPhoneAccountHandle()));
        } else if (1 == voicemailStatus2.notificationChannelState) {
            ArrayList arrayList = new ArrayList();
            if (voicemailStatus2.configurationState != 0) {
                str2 = context2.getString(R.string.voicemail_error_not_activate_no_signal_title);
                if (voicemailStatus2.isAirplaneMode) {
                    str = context2.getString(R.string.voicemail_error_not_activate_no_signal_airplane_mode_message);
                } else {
                    str = context2.getString(R.string.voicemail_error_not_activate_no_signal_message);
                    arrayList.add(VoicemailErrorMessage.createRetryAction(context, voicemailStatus));
                }
            } else if (2 == voicemailStatus2.dataChannelState) {
                str2 = context2.getString(R.string.voicemail_error_no_signal_title);
                str = context2.getString(R.string.voicemail_error_no_signal_cellular_required_message);
            } else {
                str2 = context2.getString(R.string.voicemail_error_no_signal_title);
                if (voicemailStatus2.isAirplaneMode) {
                    str = context2.getString(R.string.voicemail_error_no_signal_airplane_mode_message);
                } else {
                    str = context2.getString(R.string.voicemail_error_no_signal_message);
                }
                arrayList.add(new VoicemailErrorMessage.Action(context2.getString(R.string.voicemail_action_sync), new View.OnClickListener(context2, voicemailStatus2) {
                    final /* synthetic */ Context val$context;
                    final /* synthetic */ VoicemailStatus val$status;

                    public void onClick(
/*
Method generation error in method: com.android.dialer.voicemail.listui.error.VoicemailErrorMessage.4.onClick(android.view.View):void, dex: classes.dex
                    jadx.core.utils.exceptions.JadxRuntimeException: Method args not loaded: com.android.dialer.voicemail.listui.error.VoicemailErrorMessage.4.onClick(android.view.View):void, class status: UNLOADED
                    	at jadx.core.dex.nodes.MethodNode.getArgRegs(MethodNode.java:278)
                    	at jadx.core.codegen.MethodGen.addDefinition(MethodGen.java:116)
                    	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:313)
                    	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:271)
                    	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:240)
                    	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
                    	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                    	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                    	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:485)
                    	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:474)
                    	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
                    	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
                    	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
                    	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:497)
                    	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
                    	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
                    	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:676)
                    	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:607)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:364)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:231)
                    	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:123)
                    	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                    	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:787)
                    	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:640)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:364)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:231)
                    	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:123)
                    	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                    	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:787)
                    	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:728)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:368)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:250)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:221)
                    	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                    	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
                    	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:156)
                    	at jadx.core.codegen.RegionGen.connectElseIf(RegionGen.java:175)
                    	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:152)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                    	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
                    	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:142)
                    	at jadx.core.codegen.RegionGen.connectElseIf(RegionGen.java:175)
                    	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:152)
                    	at jadx.core.codegen.RegionGen.connectElseIf(RegionGen.java:175)
                    	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:152)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                    	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:211)
                    	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:204)
                    	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:318)
                    	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:271)
                    	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:240)
                    	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
                    	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                    	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                    	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:485)
                    	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:474)
                    	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
                    	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
                    	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
                    	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:497)
                    	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
                    	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
                    	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:112)
                    	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:78)
                    	at jadx.core.codegen.CodeGen.wrapCodeGen(CodeGen.java:44)
                    	at jadx.core.codegen.CodeGen.generateJavaCode(CodeGen.java:33)
                    	at jadx.core.codegen.CodeGen.generate(CodeGen.java:21)
                    	at jadx.core.ProcessClass.generateCode(ProcessClass.java:61)
                    	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:273)
                    
*/
                }));
            }
            if (voicemailStatus2.isAirplaneMode) {
                arrayList.add(new VoicemailErrorMessage.Action(context2.getString(R.string.voicemail_action_turn_off_airplane_mode), new View.OnClickListener(context2) {
                    final /* synthetic */ Context val$context;

                    public void onClick(
/*
Method generation error in method: com.android.dialer.voicemail.listui.error.VoicemailErrorMessage.1.onClick(android.view.View):void, dex: classes.dex
                    jadx.core.utils.exceptions.JadxRuntimeException: Method args not loaded: com.android.dialer.voicemail.listui.error.VoicemailErrorMessage.1.onClick(android.view.View):void, class status: UNLOADED
                    	at jadx.core.dex.nodes.MethodNode.getArgRegs(MethodNode.java:278)
                    	at jadx.core.codegen.MethodGen.addDefinition(MethodGen.java:116)
                    	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:313)
                    	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:271)
                    	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:240)
                    	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
                    	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                    	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                    	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:485)
                    	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:474)
                    	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
                    	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
                    	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
                    	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:497)
                    	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
                    	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
                    	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:676)
                    	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:607)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:364)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:231)
                    	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:123)
                    	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                    	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:787)
                    	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:640)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:364)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:231)
                    	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:123)
                    	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                    	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:787)
                    	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:728)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:368)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:250)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:221)
                    	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                    	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
                    	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:142)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                    	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
                    	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:142)
                    	at jadx.core.codegen.RegionGen.connectElseIf(RegionGen.java:175)
                    	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:152)
                    	at jadx.core.codegen.RegionGen.connectElseIf(RegionGen.java:175)
                    	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:152)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                    	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:211)
                    	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:204)
                    	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:318)
                    	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:271)
                    	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:240)
                    	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
                    	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                    	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                    	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:485)
                    	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:474)
                    	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
                    	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
                    	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
                    	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:497)
                    	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
                    	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
                    	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:112)
                    	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:78)
                    	at jadx.core.codegen.CodeGen.wrapCodeGen(CodeGen.java:44)
                    	at jadx.core.codegen.CodeGen.generateJavaCode(CodeGen.java:33)
                    	at jadx.core.codegen.CodeGen.generate(CodeGen.java:21)
                    	at jadx.core.ProcessClass.generateCode(ProcessClass.java:61)
                    	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:273)
                    
*/
                }));
            }
            return new VoicemailErrorMessage((CharSequence) str2, (CharSequence) str, (List<VoicemailErrorMessage.Action>) arrayList);
        } else if (4 == voicemailStatus2.configurationState) {
            return new VoicemailErrorMessage((CharSequence) context2.getString(R.string.voicemail_error_activation_failed_title), (CharSequence) context2.getString(R.string.voicemail_error_activation_failed_message), VoicemailErrorMessage.createCallVoicemailAction(context2, voicemailStatus.getPhoneAccountHandle()), VoicemailErrorMessage.createRetryAction(context, voicemailStatus));
        } else {
            int i3 = voicemailStatus2.dataChannelState;
            if (1 == i3) {
                return new VoicemailErrorMessage((CharSequence) context2.getString(R.string.voicemail_error_no_data_title), (CharSequence) context2.getString(R.string.voicemail_error_no_data_message), VoicemailErrorMessage.createCallVoicemailAction(context2, voicemailStatus.getPhoneAccountHandle()), VoicemailErrorMessage.createRetryAction(context, voicemailStatus));
            } else if (2 == i3) {
                return new VoicemailErrorMessage((CharSequence) context2.getString(R.string.voicemail_error_no_data_title), (CharSequence) context2.getString(R.string.voicemail_error_no_data_cellular_required_message), VoicemailErrorMessage.createCallVoicemailAction(context2, voicemailStatus.getPhoneAccountHandle()), VoicemailErrorMessage.createRetryAction(context, voicemailStatus));
            } else if (3 == i3) {
                return new VoicemailErrorMessage((CharSequence) context2.getString(R.string.voicemail_error_bad_config_title), (CharSequence) context2.getString(R.string.voicemail_error_bad_config_message), VoicemailErrorMessage.createCallVoicemailAction(context2, voicemailStatus.getPhoneAccountHandle()), VoicemailErrorMessage.createRetryAction(context, voicemailStatus));
            } else if (4 == i3) {
                return new VoicemailErrorMessage((CharSequence) context2.getString(R.string.voicemail_error_communication_title), (CharSequence) context2.getString(R.string.voicemail_error_communication_message), VoicemailErrorMessage.createCallVoicemailAction(context2, voicemailStatus.getPhoneAccountHandle()), VoicemailErrorMessage.createRetryAction(context, voicemailStatus));
            } else if (5 == i3) {
                return new VoicemailErrorMessage((CharSequence) context2.getString(R.string.voicemail_error_server_title), (CharSequence) context2.getString(R.string.voicemail_error_server_message), VoicemailErrorMessage.createCallVoicemailAction(context2, voicemailStatus.getPhoneAccountHandle()), VoicemailErrorMessage.createRetryAction(context, voicemailStatus));
            } else if (6 == i3) {
                return new VoicemailErrorMessage((CharSequence) context2.getString(R.string.voicemail_error_server_connection_title), (CharSequence) context2.getString(R.string.voicemail_error_server_connection_message), VoicemailErrorMessage.createCallVoicemailAction(context2, voicemailStatus.getPhoneAccountHandle()), VoicemailErrorMessage.createRetryAction(context, voicemailStatus));
            } else {
                LogUtil.m8e("OmtpVoicemailMessageCreator.create", GeneratedOutlineSupport.outline6("Unhandled status: ", voicemailStatus2), new Object[0]);
                return null;
            }
        }
    }

    public static VoicemailErrorMessage create1(Context context, VoicemailStatus voicemailStatus, VoicemailStatusReader voicemailStatusReader) {
        VoicemailErrorMessage maybeCreateTosMessage = new VoicemailTosMessageCreator(context, voicemailStatus, voicemailStatusReader).maybeCreateTosMessage();
        if (maybeCreateTosMessage != null) {
            return maybeCreateTosMessage;
        }
        int i = voicemailStatus.dataChannelState;
        if (-9001 == i) {
            return new VoicemailErrorMessage((CharSequence) context.getString(R.string.vvm3_error_vms_dns_failure_title), getCustomerSupportString(context, R.string.vvm3_error_vms_dns_failure_message), VoicemailErrorMessage.createRetryAction(context, voicemailStatus), createCallCustomerSupportAction(context));
        }
        int i2 = voicemailStatus.configurationState;
        if (-9002 == i2) {
            return new VoicemailErrorMessage((CharSequence) context.getString(R.string.vvm3_error_vmg_dns_failure_title), getCustomerSupportString(context, R.string.vvm3_error_vmg_dns_failure_message), VoicemailErrorMessage.createRetryAction(context, voicemailStatus), createCallCustomerSupportAction(context));
        } else if (-9003 == i2) {
            return new VoicemailErrorMessage((CharSequence) context.getString(R.string.vvm3_error_spg_dns_failure_title), getCustomerSupportString(context, R.string.vvm3_error_spg_dns_failure_message), VoicemailErrorMessage.createRetryAction(context, voicemailStatus), createCallCustomerSupportAction(context));
        } else if (-9004 == i) {
            return new VoicemailErrorMessage((CharSequence) context.getString(R.string.vvm3_error_vms_no_cellular_title), getCustomerSupportString(context, R.string.vvm3_error_vms_no_cellular_message), VoicemailErrorMessage.createRetryAction(context, voicemailStatus), createCallCustomerSupportAction(context));
        } else if (-9005 == i2) {
            return new VoicemailErrorMessage((CharSequence) context.getString(R.string.vvm3_error_vmg_no_cellular_title), getCustomerSupportString(context, R.string.vvm3_error_vmg_no_cellular_message), VoicemailErrorMessage.createRetryAction(context, voicemailStatus), createCallCustomerSupportAction(context));
        } else if (-9006 == i2) {
            return new VoicemailErrorMessage((CharSequence) context.getString(R.string.vvm3_error_spg_no_cellular_title), getCustomerSupportString(context, R.string.vvm3_error_spg_no_cellular_message), VoicemailErrorMessage.createRetryAction(context, voicemailStatus), createCallCustomerSupportAction(context));
        } else if (-9007 == i) {
            return new VoicemailErrorMessage((CharSequence) context.getString(R.string.vvm3_error_vms_timeout_title), getCustomerSupportString(context, R.string.vvm3_error_vms_timeout_message), VoicemailErrorMessage.createRetryAction(context, voicemailStatus), createCallCustomerSupportAction(context));
        } else if (-9008 == i2) {
            return new VoicemailErrorMessage((CharSequence) context.getString(R.string.vvm3_error_vmg_timeout_title), getCustomerSupportString(context, R.string.vvm3_error_vmg_timeout_message), VoicemailErrorMessage.createRetryAction(context, voicemailStatus), createCallCustomerSupportAction(context));
        } else if (-9009 == voicemailStatus.notificationChannelState) {
            return new VoicemailErrorMessage((CharSequence) context.getString(R.string.vvm3_error_status_sms_timeout_title), getCustomerSupportString(context, R.string.vvm3_error_status_sms_timeout_message), VoicemailErrorMessage.createRetryAction(context, voicemailStatus), createCallCustomerSupportAction(context));
        } else if (-9990 == i2) {
            return new VoicemailErrorMessage((CharSequence) context.getString(R.string.vvm3_error_subscriber_blocked_title), getCustomerSupportString(context, R.string.vvm3_error_subscriber_blocked_message), VoicemailErrorMessage.createRetryAction(context, voicemailStatus), createCallCustomerSupportAction(context));
        } else if (-9991 == i2) {
            return new VoicemailErrorMessage((CharSequence) context.getString(R.string.vvm3_error_unknown_user_title), getCustomerSupportString(context, R.string.vvm3_error_unknown_user_message), VoicemailErrorMessage.createCallVoicemailAction(context, voicemailStatus.getPhoneAccountHandle()), createCallCustomerSupportAction(context));
        } else if (-9992 == i2) {
            return new VoicemailErrorMessage((CharSequence) context.getString(R.string.vvm3_error_unknown_device_title), getCustomerSupportString(context, R.string.vvm3_error_unknown_device_message), VoicemailErrorMessage.createCallVoicemailAction(context, voicemailStatus.getPhoneAccountHandle()), createCallCustomerSupportAction(context));
        } else if (-9993 == i2) {
            return new VoicemailErrorMessage((CharSequence) context.getString(R.string.vvm3_error_invalid_password_title), getCustomerSupportString(context, R.string.vvm3_error_invalid_password_message), VoicemailErrorMessage.createCallVoicemailAction(context, voicemailStatus.getPhoneAccountHandle()), createCallCustomerSupportAction(context));
        } else if (-9994 == i2) {
            return new VoicemailErrorMessage((CharSequence) context.getString(R.string.vvm3_error_mailbox_not_initialized_title), getCustomerSupportString(context, R.string.vvm3_error_mailbox_not_initialized_message), createCallCustomerSupportAction(context));
        } else if (-9995 == i2) {
            return new VoicemailErrorMessage((CharSequence) context.getString(R.string.vvm3_error_service_not_provisioned_title), getCustomerSupportString(context, R.string.vvm3_error_service_not_provisioned_message), createCallCustomerSupportAction(context));
        } else if (-9996 == i2) {
            return new VoicemailErrorMessage((CharSequence) context.getString(R.string.vvm3_error_service_not_activated_title), getCustomerSupportString(context, R.string.vvm3_error_service_not_activated_message), createCallCustomerSupportAction(context));
        } else if (-9998 == i2) {
            return new VoicemailErrorMessage((CharSequence) context.getString(R.string.vvm3_error_user_blocked_title), getCustomerSupportString(context, R.string.vvm3_error_user_blocked_message), createCallCustomerSupportAction(context));
        } else if (-99 == i2) {
            return new VoicemailErrorMessage((CharSequence) context.getString(R.string.vvm3_error_subscriber_unknown_title), getCustomerSupportString(context, R.string.vvm3_error_subscriber_unknown_message), VoicemailErrorMessage.createCallVoicemailAction(context, voicemailStatus.getPhoneAccountHandle()), createCallCustomerSupportAction(context));
        } else if (-9997 == i) {
            return new VoicemailErrorMessage((CharSequence) context.getString(R.string.vvm3_error_imap_getquota_error_title), getCustomerSupportString(context, R.string.vvm3_error_imap_getquota_error_message), VoicemailErrorMessage.createCallVoicemailAction(context, voicemailStatus.getPhoneAccountHandle()), createCallCustomerSupportAction(context));
        } else if (-9989 == i) {
            return new VoicemailErrorMessage((CharSequence) context.getString(R.string.vvm3_error_imap_select_error_title), getCustomerSupportString(context, R.string.vvm3_error_imap_select_error_message), VoicemailErrorMessage.createCallVoicemailAction(context, voicemailStatus.getPhoneAccountHandle()), createCallCustomerSupportAction(context));
        } else if (-9999 == i) {
            return new VoicemailErrorMessage((CharSequence) context.getString(R.string.vvm3_error_imap_error_title), getCustomerSupportString(context, R.string.vvm3_error_imap_error_message), VoicemailErrorMessage.createCallVoicemailAction(context, voicemailStatus.getPhoneAccountHandle()), createCallCustomerSupportAction(context));
        } else if (-100 != i2) {
            return create(context, voicemailStatus, voicemailStatusReader);
        } else {
            ((LoggingBindingsStub) Logger.get(context)).logImpression(DialerImpression$Type.VOICEMAIL_ALERT_SET_PIN_SHOWN);
            return new VoicemailErrorMessage((CharSequence) context.getString(R.string.voicemail_error_pin_not_set_title), getCustomerSupportString(context, R.string.voicemail_error_pin_not_set_message), new VoicemailErrorMessage.Action(context.getString(R.string.voicemail_action_set_pin), new View.OnClickListener(context, voicemailStatus.getPhoneAccountHandle()) {
                final /* synthetic */ Context val$context;
                final /* synthetic */ PhoneAccountHandle val$phoneAccountHandle;

                public void onClick(
/*
Method generation error in method: com.android.dialer.voicemail.listui.error.VoicemailErrorMessage.2.onClick(android.view.View):void, dex: classes.dex
                jadx.core.utils.exceptions.JadxRuntimeException: Method args not loaded: com.android.dialer.voicemail.listui.error.VoicemailErrorMessage.2.onClick(android.view.View):void, class status: UNLOADED
                	at jadx.core.dex.nodes.MethodNode.getArgRegs(MethodNode.java:278)
                	at jadx.core.codegen.MethodGen.addDefinition(MethodGen.java:116)
                	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:313)
                	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:271)
                	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:240)
                	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
                	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:485)
                	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:474)
                	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
                	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
                	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
                	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:497)
                	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
                	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
                	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:676)
                	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:607)
                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:364)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:231)
                	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:123)
                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:787)
                	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:640)
                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:364)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:231)
                	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:123)
                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                	at jadx.core.codegen.InsnGen.processVarArg(InsnGen.java:871)
                	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:784)
                	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:640)
                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:364)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:231)
                	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:123)
                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:314)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:250)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:221)
                	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
                	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
                	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:156)
                	at jadx.core.codegen.RegionGen.connectElseIf(RegionGen.java:175)
                	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:152)
                	at jadx.core.codegen.RegionGen.connectElseIf(RegionGen.java:175)
                	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:152)
                	at jadx.core.codegen.RegionGen.connectElseIf(RegionGen.java:175)
                	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:152)
                	at jadx.core.codegen.RegionGen.connectElseIf(RegionGen.java:175)
                	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:152)
                	at jadx.core.codegen.RegionGen.connectElseIf(RegionGen.java:175)
                	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:152)
                	at jadx.core.codegen.RegionGen.connectElseIf(RegionGen.java:175)
                	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:152)
                	at jadx.core.codegen.RegionGen.connectElseIf(RegionGen.java:175)
                	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:152)
                	at jadx.core.codegen.RegionGen.connectElseIf(RegionGen.java:175)
                	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:152)
                	at jadx.core.codegen.RegionGen.connectElseIf(RegionGen.java:175)
                	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:152)
                	at jadx.core.codegen.RegionGen.connectElseIf(RegionGen.java:175)
                	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:152)
                	at jadx.core.codegen.RegionGen.connectElseIf(RegionGen.java:175)
                	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:152)
                	at jadx.core.codegen.RegionGen.connectElseIf(RegionGen.java:175)
                	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:152)
                	at jadx.core.codegen.RegionGen.connectElseIf(RegionGen.java:175)
                	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:152)
                	at jadx.core.codegen.RegionGen.connectElseIf(RegionGen.java:175)
                	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:152)
                	at jadx.core.codegen.RegionGen.connectElseIf(RegionGen.java:175)
                	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:152)
                	at jadx.core.codegen.RegionGen.connectElseIf(RegionGen.java:175)
                	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:152)
                	at jadx.core.codegen.RegionGen.connectElseIf(RegionGen.java:175)
                	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:152)
                	at jadx.core.codegen.RegionGen.connectElseIf(RegionGen.java:175)
                	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:152)
                	at jadx.core.codegen.RegionGen.connectElseIf(RegionGen.java:175)
                	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:152)
                	at jadx.core.codegen.RegionGen.connectElseIf(RegionGen.java:175)
                	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:152)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
                	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:211)
                	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:204)
                	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:318)
                	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:271)
                	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:240)
                	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
                	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:485)
                	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:474)
                	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
                	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
                	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
                	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:497)
                	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
                	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
                	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:112)
                	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:78)
                	at jadx.core.codegen.CodeGen.wrapCodeGen(CodeGen.java:44)
                	at jadx.core.codegen.CodeGen.generateJavaCode(CodeGen.java:33)
                	at jadx.core.codegen.CodeGen.generate(CodeGen.java:21)
                	at jadx.core.ProcessClass.generateCode(ProcessClass.java:61)
                	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:273)
                
*/
            }));
        }
    }

    private static VoicemailErrorMessage.Action createCallCustomerSupportAction(Context context) {
        return new VoicemailErrorMessage.Action(context.getString(R.string.voicemail_action_call_customer_support), new Vvm3VoicemailMessageCreator$1(context));
    }

    private static CharSequence getCustomerSupportString(Context context, int i) {
        return R$style.getTtsSpannedPhoneNumber(context.getResources(), i, context.getString(R.string.verizon_domestic_customer_support_display_number));
    }

    public static void maybeFixVoicemailStatus(Context context, Cursor cursor, VoicemailStatusCorruptionHandler$Source voicemailStatusCorruptionHandler$Source) {
        if (!((SharedPrefConfigProvider) ConfigProviderComponent.get(context).getConfigProvider()).getBoolean("vvm_status_fix_disabled", false)) {
            int i = Build.VERSION.SDK_INT;
        }
    }
}
