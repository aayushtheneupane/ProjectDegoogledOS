package com.android.settings.network;

import android.content.Context;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.telephony.UiccSlotInfo;
import com.android.internal.util.CollectionUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

public class SubscriptionUtil {
    private static List<SubscriptionInfo> sActiveResultsForTesting;
    private static List<SubscriptionInfo> sAvailableResultsForTesting;

    public static void setAvailableSubscriptionsForTesting(List<SubscriptionInfo> list) {
        sAvailableResultsForTesting = list;
    }

    public static void setActiveSubscriptionsForTesting(List<SubscriptionInfo> list) {
        sActiveResultsForTesting = list;
    }

    public static List<SubscriptionInfo> getActiveSubscriptions(SubscriptionManager subscriptionManager) {
        List<SubscriptionInfo> list = sActiveResultsForTesting;
        if (list != null) {
            return list;
        }
        List<SubscriptionInfo> activeSubscriptionInfoList = subscriptionManager.getActiveSubscriptionInfoList(true);
        return activeSubscriptionInfoList == null ? new ArrayList() : activeSubscriptionInfoList;
    }

    static boolean isInactiveInsertedPSim(UiccSlotInfo uiccSlotInfo) {
        if (uiccSlotInfo != null && !uiccSlotInfo.getIsEuicc() && !uiccSlotInfo.getIsActive() && uiccSlotInfo.getCardStateInfo() == 2) {
            return true;
        }
        return false;
    }

    public static List<SubscriptionInfo> getAvailableSubscriptions(Context context) {
        List<SubscriptionInfo> list = sAvailableResultsForTesting;
        if (list != null) {
            return list;
        }
        SubscriptionManager subscriptionManager = (SubscriptionManager) context.getSystemService(SubscriptionManager.class);
        ArrayList arrayList = new ArrayList(CollectionUtils.emptyIfNull(subscriptionManager.getSelectableSubscriptionInfoList()));
        ArrayList arrayList2 = new ArrayList();
        UiccSlotInfo[] uiccSlotsInfo = ((TelephonyManager) context.getSystemService(TelephonyManager.class)).getUiccSlotsInfo();
        int i = 0;
        while (uiccSlotsInfo != null && i < uiccSlotsInfo.length) {
            UiccSlotInfo uiccSlotInfo = uiccSlotsInfo[i];
            if (isInactiveInsertedPSim(uiccSlotInfo)) {
                if (!arrayList.stream().anyMatch(new Predicate(uiccSlotInfo.getLogicalSlotIdx(), uiccSlotInfo.getCardId()) {
                    private final /* synthetic */ int f$0;
                    private final /* synthetic */ String f$1;

                    {
                        this.f$0 = r1;
                        this.f$1 = r2;
                    }

                    public final boolean test(Object obj) {
                        return SubscriptionUtil.lambda$getAvailableSubscriptions$0(this.f$0, this.f$1, (SubscriptionInfo) obj);
                    }
                })) {
                    arrayList2.add(uiccSlotInfo);
                }
            }
            i++;
        }
        if (!arrayList2.isEmpty()) {
            for (SubscriptionInfo subscriptionInfo : subscriptionManager.getAllSubscriptionInfoList()) {
                Iterator it = arrayList2.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    UiccSlotInfo uiccSlotInfo2 = (UiccSlotInfo) it.next();
                    if (subscriptionInfo.getSimSlotIndex() == uiccSlotInfo2.getLogicalSlotIdx() && subscriptionInfo.getCardString().equals(uiccSlotInfo2.getCardId())) {
                        arrayList.add(subscriptionInfo);
                        break;
                    }
                }
            }
        }
        return arrayList;
    }

    static /* synthetic */ boolean lambda$getAvailableSubscriptions$0(int i, String str, SubscriptionInfo subscriptionInfo) {
        return i == subscriptionInfo.getSimSlotIndex() && str.equals(subscriptionInfo.getCardString());
    }
}
