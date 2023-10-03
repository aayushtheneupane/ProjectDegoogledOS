package com.android.dialer.precall.impl;

import android.content.Context;
import android.telephony.SubscriptionInfo;
import android.telephony.TelephonyManager;
import com.android.dialer.assisteddialing.AssistedDialingMediator;
import com.android.dialer.assisteddialing.ConcreteCreator;
import com.android.dialer.assisteddialing.TransformationInfo;
import com.android.dialer.callintent.CallIntentBuilder;
import com.android.dialer.common.Assert;
import com.android.dialer.common.LogUtil;
import com.android.dialer.configprovider.ConfigProvider;
import com.android.dialer.configprovider.ConfigProviderComponent;
import com.android.dialer.configprovider.SharedPrefConfigProvider;
import com.android.dialer.precall.PreCallAction;
import com.android.dialer.telecom.TelecomUtil;
import com.android.dialer.util.CallUtil;
import com.google.common.base.Optional;

public class AssistedDialAction implements PreCallAction {
    public void onDiscard() {
    }

    public boolean requiresUi(Context context, CallIntentBuilder callIntentBuilder) {
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0078  */
    /* JADX WARNING: Removed duplicated region for block: B:26:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void runWithUi(com.android.dialer.precall.PreCallCoordinator r6) {
        /*
            r5 = this;
            com.android.dialer.precall.impl.PreCallCoordinatorImpl r6 = (com.android.dialer.precall.impl.PreCallCoordinatorImpl) r6
            android.app.Activity r5 = r6.getActivity()
            com.android.dialer.callintent.CallIntentBuilder r6 = r6.getBuilder()
            boolean r0 = r6.isAssistedDialAllowed()
            if (r0 != 0) goto L_0x0012
            goto L_0x00d7
        L_0x0012:
            com.android.dialer.configprovider.ConfigProviderComponent r0 = com.android.dialer.configprovider.ConfigProviderComponent.get(r5)
            com.android.dialer.configprovider.ConfigProvider r0 = r0.getConfigProvider()
            java.lang.Class<android.telephony.TelephonyManager> r1 = android.telephony.TelephonyManager.class
            java.lang.Object r1 = r5.getSystemService(r1)
            android.telephony.TelephonyManager r1 = (android.telephony.TelephonyManager) r1
            android.telecom.PhoneAccountHandle r2 = r6.getPhoneAccountHandle()
            r3 = 0
            if (r2 != 0) goto L_0x002a
            goto L_0x0064
        L_0x002a:
            com.android.dialer.configprovider.SharedPrefConfigProvider r0 = (com.android.dialer.configprovider.SharedPrefConfigProvider) r0
            java.lang.String r2 = "assisted_dialing_dual_sim_enabled"
            boolean r0 = r0.getBoolean(r2, r3)
            if (r0 != 0) goto L_0x0035
            goto L_0x0064
        L_0x0035:
            android.telecom.PhoneAccountHandle r0 = r6.getPhoneAccountHandle()
            com.google.common.base.Optional r0 = com.android.dialer.telecom.TelecomUtil.getSubscriptionInfo(r5, r0)
            boolean r2 = r0.isPresent()
            java.lang.String r4 = "AssistedDialAction.getAssistedDialingTelephonyManager"
            if (r2 != 0) goto L_0x004d
            java.lang.Object[] r0 = new java.lang.Object[r3]
            java.lang.String r2 = "subcriptionInfo was absent."
            com.android.dialer.common.LogUtil.m9i(r4, r2, r0)
            goto L_0x0064
        L_0x004d:
            java.lang.Object r0 = r0.get()
            android.telephony.SubscriptionInfo r0 = (android.telephony.SubscriptionInfo) r0
            int r0 = r0.getSubscriptionId()
            android.telephony.TelephonyManager r0 = r1.createForSubscriptionId(r0)
            if (r0 != 0) goto L_0x0066
            java.lang.Object[] r0 = new java.lang.Object[r3]
            java.lang.String r2 = "createForSubscriptionId pinnedtelephonyManager was null."
            com.android.dialer.common.LogUtil.m9i(r4, r2, r0)
        L_0x0064:
            r0 = r1
            goto L_0x006d
        L_0x0066:
            java.lang.Object[] r1 = new java.lang.Object[r3]
            java.lang.String r2 = "createForPhoneAccountHandle using pinnedtelephonyManager from subscription id."
            com.android.dialer.common.LogUtil.m9i(r4, r2, r1)
        L_0x006d:
            com.android.dialer.assisteddialing.AssistedDialingMediator r5 = com.android.dialer.assisteddialing.ConcreteCreator.createNewAssistedDialingMediator(r0, r5)
            boolean r0 = r5.isPlatformEligible()
            if (r0 != 0) goto L_0x0078
            goto L_0x00d7
        L_0x0078:
            android.net.Uri r0 = r6.getUri()
            java.lang.String r0 = r0.getScheme()
            java.lang.String r1 = "tel"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0091
            android.net.Uri r0 = r6.getUri()
            java.lang.String r0 = r0.getSchemeSpecificPart()
            goto L_0x0093
        L_0x0091:
            java.lang.String r0 = ""
        L_0x0093:
            java.util.Optional r5 = r5.attemptAssistedDial(r0)
            boolean r0 = r5.isPresent()
            if (r0 == 0) goto L_0x00d7
            android.os.Bundle r0 = r6.getInCallUiIntentExtras()
            r1 = 1
            java.lang.String r2 = "android.telecom.extra.USE_ASSISTED_DIALING"
            r0.putBoolean(r2, r1)
            java.lang.Object r0 = r5.get()
            com.android.dialer.assisteddialing.TransformationInfo r0 = (com.android.dialer.assisteddialing.TransformationInfo) r0
            android.os.Bundle r0 = r0.toBundle()
            android.os.Bundle r1 = r6.getInCallUiIntentExtras()
            java.lang.String r2 = "android.telecom.extra.ASSISTED_DIALING_EXTRAS"
            r1.putBundle(r2, r0)
            java.lang.Object r5 = r5.get()
            com.android.dialer.assisteddialing.TransformationInfo r5 = (com.android.dialer.assisteddialing.TransformationInfo) r5
            java.lang.String r5 = r5.transformedNumber()
            com.android.dialer.common.Assert.isNotNull(r5)
            android.net.Uri r5 = com.android.dialer.util.CallUtil.getCallUri(r5)
            r6.setUri(r5)
            java.lang.Object[] r5 = new java.lang.Object[r3]
            java.lang.String r6 = "AssistedDialAction.runWithoutUi"
            java.lang.String r0 = "assisted dialing was used."
            com.android.dialer.common.LogUtil.m9i(r6, r0, r5)
        L_0x00d7:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.precall.impl.AssistedDialAction.runWithUi(com.android.dialer.precall.PreCallCoordinator):void");
    }

    public void runWithoutUi(Context context, CallIntentBuilder callIntentBuilder) {
        if (callIntentBuilder.isAssistedDialAllowed()) {
            ConfigProvider configProvider = ConfigProviderComponent.get(context).getConfigProvider();
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(TelephonyManager.class);
            if (callIntentBuilder.getPhoneAccountHandle() != null && ((SharedPrefConfigProvider) configProvider).getBoolean("assisted_dialing_dual_sim_enabled", false)) {
                Optional<SubscriptionInfo> subscriptionInfo = TelecomUtil.getSubscriptionInfo(context, callIntentBuilder.getPhoneAccountHandle());
                if (!subscriptionInfo.isPresent()) {
                    LogUtil.m9i("AssistedDialAction.getAssistedDialingTelephonyManager", "subcriptionInfo was absent.", new Object[0]);
                } else {
                    TelephonyManager createForSubscriptionId = telephonyManager.createForSubscriptionId(subscriptionInfo.get().getSubscriptionId());
                    if (createForSubscriptionId == null) {
                        LogUtil.m9i("AssistedDialAction.getAssistedDialingTelephonyManager", "createForSubscriptionId pinnedtelephonyManager was null.", new Object[0]);
                    } else {
                        LogUtil.m9i("AssistedDialAction.getAssistedDialingTelephonyManager", "createForPhoneAccountHandle using pinnedtelephonyManager from subscription id.", new Object[0]);
                        telephonyManager = createForSubscriptionId;
                    }
                }
            }
            AssistedDialingMediator createNewAssistedDialingMediator = ConcreteCreator.createNewAssistedDialingMediator(telephonyManager, context);
            if (createNewAssistedDialingMediator.isPlatformEligible()) {
                java.util.Optional<TransformationInfo> attemptAssistedDial = createNewAssistedDialingMediator.attemptAssistedDial(callIntentBuilder.getUri().getScheme().equals("tel") ? callIntentBuilder.getUri().getSchemeSpecificPart() : "");
                if (attemptAssistedDial.isPresent()) {
                    callIntentBuilder.getInCallUiIntentExtras().putBoolean("android.telecom.extra.USE_ASSISTED_DIALING", true);
                    callIntentBuilder.getInCallUiIntentExtras().putBundle("android.telecom.extra.ASSISTED_DIALING_EXTRAS", attemptAssistedDial.get().toBundle());
                    String transformedNumber = attemptAssistedDial.get().transformedNumber();
                    Assert.isNotNull(transformedNumber);
                    callIntentBuilder.setUri(CallUtil.getCallUri(transformedNumber));
                    LogUtil.m9i("AssistedDialAction.runWithoutUi", "assisted dialing was used.", new Object[0]);
                }
            }
        }
    }
}
