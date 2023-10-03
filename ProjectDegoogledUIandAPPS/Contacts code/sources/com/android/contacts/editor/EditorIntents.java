package com.android.contacts.editor;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.android.contacts.activities.ContactEditorActivity;
import com.android.contacts.activities.ContactEditorSpringBoardActivity;
import com.android.contacts.util.MaterialColorMapUtils;
import java.util.ArrayList;

public class EditorIntents {
    public static Intent createEditContactIntent(Context context, Uri uri, MaterialColorMapUtils.MaterialPalette materialPalette, long j) {
        Intent intent = new Intent("android.intent.action.EDIT", uri, context, ContactEditorSpringBoardActivity.class);
        putMaterialPalette(intent, materialPalette);
        putPhotoId(intent, j);
        return intent;
    }

    public static Intent createViewLinkedContactsIntent(Context context, Uri uri, MaterialColorMapUtils.MaterialPalette materialPalette) {
        Intent createEditContactIntent = createEditContactIntent(context, uri, materialPalette, -1);
        createEditContactIntent.putExtra("showReadOnly", true);
        return createEditContactIntent;
    }

    public static Intent createEditContactIntentForRawContact(Context context, Uri uri, long j, MaterialColorMapUtils.MaterialPalette materialPalette) {
        Intent intent = new Intent("android.intent.action.EDIT", uri, context, ContactEditorActivity.class);
        intent.putExtra("raw_contact_id_to_display_alone", j);
        putMaterialPalette(intent, materialPalette);
        return intent;
    }

    public static Intent createEditOtherRawContactIntent(Context context, Uri uri, long j, ArrayList<ContentValues> arrayList) {
        Intent intent = new Intent("android.intent.action.EDIT", uri, context, ContactEditorActivity.class);
        intent.setFlags(41943040);
        intent.putExtra("addToDefaultDirectory", "");
        intent.putExtra("raw_contact_id_to_display_alone", j);
        if (!(arrayList == null || arrayList.size() == 0)) {
            intent.putParcelableArrayListExtra("data", arrayList);
        }
        return intent;
    }

    private static void putMaterialPalette(Intent intent, MaterialColorMapUtils.MaterialPalette materialPalette) {
        if (materialPalette != null) {
            intent.putExtra("material_palette_primary_color", materialPalette.mPrimaryColor);
            intent.putExtra("material_palette_secondary_color", materialPalette.mSecondaryColor);
        }
    }

    private static void putPhotoId(Intent intent, long j) {
        if (j >= 0) {
            intent.putExtra("photo_id", j);
        }
    }
}
