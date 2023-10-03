package com.android.settings.homepage.contextualcards;

import com.android.settings.intelligence.ContextualCardProto$ContextualCard;
import com.android.settings.intelligence.ContextualCardProto$ContextualCardList;
import com.android.settings.slices.CustomSliceRegistry;
import com.google.android.settings.intelligence.libs.contextualcards.ContextualCardProvider;

public class SettingsContextualCardProvider extends ContextualCardProvider {
    public ContextualCardProto$ContextualCardList getContextualCards() {
        ContextualCardProto$ContextualCard.Builder newBuilder = ContextualCardProto$ContextualCard.newBuilder();
        newBuilder.setSliceUri(CustomSliceRegistry.CONTEXTUAL_WIFI_SLICE_URI.toString());
        newBuilder.setCardName(CustomSliceRegistry.CONTEXTUAL_WIFI_SLICE_URI.toString());
        newBuilder.setCardCategory(ContextualCardProto$ContextualCard.Category.IMPORTANT);
        ContextualCardProto$ContextualCard.Builder newBuilder2 = ContextualCardProto$ContextualCard.newBuilder();
        newBuilder2.setSliceUri(CustomSliceRegistry.BLUETOOTH_DEVICES_SLICE_URI.toString());
        newBuilder2.setCardName(CustomSliceRegistry.BLUETOOTH_DEVICES_SLICE_URI.toString());
        newBuilder2.setCardCategory(ContextualCardProto$ContextualCard.Category.IMPORTANT);
        ContextualCardProto$ContextualCard.Builder newBuilder3 = ContextualCardProto$ContextualCard.newBuilder();
        newBuilder3.setSliceUri(CustomSliceRegistry.LOW_STORAGE_SLICE_URI.toString());
        newBuilder3.setCardName(CustomSliceRegistry.LOW_STORAGE_SLICE_URI.toString());
        newBuilder3.setCardCategory(ContextualCardProto$ContextualCard.Category.IMPORTANT);
        ContextualCardProto$ContextualCard.Builder newBuilder4 = ContextualCardProto$ContextualCard.newBuilder();
        newBuilder4.setSliceUri(CustomSliceRegistry.BATTERY_FIX_SLICE_URI.toString());
        newBuilder4.setCardName(CustomSliceRegistry.BATTERY_FIX_SLICE_URI.toString());
        newBuilder4.setCardCategory(ContextualCardProto$ContextualCard.Category.IMPORTANT);
        String uri = CustomSliceRegistry.CONTEXTUAL_NOTIFICATION_CHANNEL_SLICE_URI.toString();
        ContextualCardProto$ContextualCard.Builder newBuilder5 = ContextualCardProto$ContextualCard.newBuilder();
        newBuilder5.setSliceUri(uri);
        newBuilder5.setCardName(uri);
        newBuilder5.setCardCategory(ContextualCardProto$ContextualCard.Category.POSSIBLE);
        String uri2 = CustomSliceRegistry.CONTEXTUAL_ADAPTIVE_SLEEP_URI.toString();
        ContextualCardProto$ContextualCard.Builder newBuilder6 = ContextualCardProto$ContextualCard.newBuilder();
        newBuilder6.setSliceUri(uri2);
        newBuilder6.setCardName(uri2);
        newBuilder6.setCardCategory(ContextualCardProto$ContextualCard.Category.DEFAULT);
        ContextualCardProto$ContextualCard.Builder newBuilder7 = ContextualCardProto$ContextualCard.newBuilder();
        newBuilder7.setSliceUri(CustomSliceRegistry.FACE_ENROLL_SLICE_URI.toString());
        newBuilder7.setCardName(CustomSliceRegistry.FACE_ENROLL_SLICE_URI.toString());
        newBuilder7.setCardCategory(ContextualCardProto$ContextualCard.Category.DEFAULT);
        ContextualCardProto$ContextualCard.Builder newBuilder8 = ContextualCardProto$ContextualCard.newBuilder();
        newBuilder8.setSliceUri(CustomSliceRegistry.DARK_THEME_SLICE_URI.toString());
        newBuilder8.setCardName(CustomSliceRegistry.DARK_THEME_SLICE_URI.toString());
        newBuilder8.setCardCategory(ContextualCardProto$ContextualCard.Category.IMPORTANT);
        ContextualCardProto$ContextualCardList.Builder newBuilder9 = ContextualCardProto$ContextualCardList.newBuilder();
        newBuilder9.addCard((ContextualCardProto$ContextualCard) newBuilder.build());
        newBuilder9.addCard((ContextualCardProto$ContextualCard) newBuilder2.build());
        newBuilder9.addCard((ContextualCardProto$ContextualCard) newBuilder3.build());
        newBuilder9.addCard((ContextualCardProto$ContextualCard) newBuilder4.build());
        newBuilder9.addCard((ContextualCardProto$ContextualCard) newBuilder5.build());
        newBuilder9.addCard((ContextualCardProto$ContextualCard) newBuilder6.build());
        newBuilder9.addCard((ContextualCardProto$ContextualCard) newBuilder7.build());
        newBuilder9.addCard((ContextualCardProto$ContextualCard) newBuilder8.build());
        return (ContextualCardProto$ContextualCardList) newBuilder9.build();
    }
}
