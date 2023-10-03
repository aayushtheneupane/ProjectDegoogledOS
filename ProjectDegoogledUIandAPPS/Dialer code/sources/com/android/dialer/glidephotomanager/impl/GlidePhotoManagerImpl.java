package com.android.dialer.glidephotomanager.impl;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.widget.QuickContactBadge;
import com.android.dialer.common.Assert;
import com.android.dialer.glidephotomanager.GlidePhotoManager;
import com.android.dialer.glidephotomanager.PhotoInfo;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GlidePhotoManagerImpl implements GlidePhotoManager {
    private static final int LOOKUP_URI_PATH_SEGMENTS = ContactsContract.Contacts.CONTENT_LOOKUP_URI.getPathSegments().size();
    private final Context appContext;

    public GlidePhotoManagerImpl(Context context) {
        this.appContext = context;
    }

    private static Uri parseUri(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return Uri.parse(str);
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x009c  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x00b0  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0105  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void loadContactPhoto(android.widget.ImageView r9, com.android.dialer.glidephotomanager.PhotoInfo r10) {
        /*
            r8 = this;
            com.android.dialer.common.Assert.isMainThread()
            android.content.Context r0 = r8.appContext
            r1 = 2131820552(0x7f110008, float:1.9273822E38)
            java.lang.CharSequence r0 = r0.getText(r1)
            r1 = 1
            java.lang.CharSequence[] r2 = new java.lang.CharSequence[r1]
            java.lang.String r3 = r10.getName()
            java.lang.CharSequence r3 = android.support.p002v7.appcompat.R$style.format(r3)
            r4 = 0
            r2[r4] = r3
            java.lang.CharSequence r0 = android.text.TextUtils.expandTemplate(r0, r2)
            r9.setContentDescription(r0)
            com.bumptech.glide.RequestManager r0 = com.bumptech.glide.Glide.with((android.view.View) r9)
            com.android.dialer.glide.GlideRequests r0 = (com.android.dialer.glide.GlideRequests) r0
            boolean r2 = r10.getIsBlocked()
            r3 = 0
            if (r2 == 0) goto L_0x003a
            r2 = 2131230864(0x7f080090, float:1.8077793E38)
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            com.android.dialer.glide.GlideRequest r0 = r0.load((java.lang.Integer) r2)
            goto L_0x0086
        L_0x003a:
            boolean r2 = r10.getIsSpam()
            if (r2 == 0) goto L_0x004c
            r2 = 2131231056(0x7f080150, float:1.8078182E38)
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            com.android.dialer.glide.GlideRequest r0 = r0.load((java.lang.Integer) r2)
            goto L_0x0087
        L_0x004c:
            java.lang.String r2 = r10.getPhotoUri()
            boolean r2 = android.text.TextUtils.isEmpty(r2)
            if (r2 != 0) goto L_0x0063
            java.lang.String r2 = r10.getPhotoUri()
            android.net.Uri r2 = parseUri(r2)
            com.android.dialer.glide.GlideRequest r0 = r0.load((android.net.Uri) r2)
            goto L_0x0086
        L_0x0063:
            long r4 = r10.getPhotoId()
            r6 = 0
            int r2 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r2 == 0) goto L_0x007c
            android.net.Uri r2 = android.provider.ContactsContract.Data.CONTENT_URI
            long r4 = r10.getPhotoId()
            android.net.Uri r2 = android.content.ContentUris.withAppendedId(r2, r4)
            com.android.dialer.glide.GlideRequest r0 = r0.load((android.net.Uri) r2)
            goto L_0x0086
        L_0x007c:
            com.bumptech.glide.RequestBuilder r0 = r0.asDrawable()
            com.bumptech.glide.RequestBuilder r0 = r0.load((java.lang.Object) r3)
            com.android.dialer.glide.GlideRequest r0 = (com.android.dialer.glide.GlideRequest) r0
        L_0x0086:
            r4 = r1
        L_0x0087:
            com.android.dialer.lettertile.LetterTileDrawable r2 = new com.android.dialer.lettertile.LetterTileDrawable
            android.content.Context r8 = r8.appContext
            android.content.res.Resources r8 = r8.getResources()
            r2.<init>(r8)
            java.lang.String r8 = r10.getLookupUri()
            boolean r8 = android.text.TextUtils.isEmpty(r8)
            if (r8 == 0) goto L_0x00b0
            java.lang.String r8 = r10.getName()
            boolean r8 = android.text.TextUtils.isEmpty(r8)
            if (r8 == 0) goto L_0x00ab
            java.lang.String r8 = r10.getFormattedNumber()
            goto L_0x00e6
        L_0x00ab:
            java.lang.String r8 = r10.getName()
            goto L_0x00e6
        L_0x00b0:
            java.lang.String r3 = r10.getName()
            java.lang.String r8 = r10.getLookupUri()
            android.net.Uri r5 = android.provider.ContactsContract.Contacts.CONTENT_LOOKUP_URI
            java.lang.String r5 = r5.toString()
            boolean r5 = r8.startsWith(r5)
            if (r5 != 0) goto L_0x00c5
            goto L_0x00e6
        L_0x00c5:
            android.net.Uri r5 = android.net.Uri.parse(r8)
            java.util.List r5 = r5.getPathSegments()
            int r6 = r5.size()
            int r7 = LOOKUP_URI_PATH_SEGMENTS
            if (r6 >= r7) goto L_0x00d6
            goto L_0x00e6
        L_0x00d6:
            java.lang.Object r5 = r5.get(r7)
            java.lang.String r5 = (java.lang.String) r5
            java.lang.String r6 = "encoded"
            boolean r6 = r6.equals(r5)
            if (r6 == 0) goto L_0x00e5
            goto L_0x00e6
        L_0x00e5:
            r8 = r5
        L_0x00e6:
            boolean r5 = r10.getIsVoicemail()
            boolean r6 = r10.getIsSpam()
            boolean r7 = r10.getIsBusiness()
            boolean r10 = r10.getIsConference()
            int r10 = com.android.dialer.lettertile.LetterTileDrawable.getContactTypeFromPrimitives(r5, r6, r7, r1, r10)
            r2.setCanonicalDialerLetterTileDetails(r3, r8, r1, r10)
            r0.placeholder(r2)
            r0.fallback(r2)
            if (r4 == 0) goto L_0x0108
            r0.circleCrop()
        L_0x0108:
            r0.into((android.widget.ImageView) r9)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.glidephotomanager.impl.GlidePhotoManagerImpl.loadContactPhoto(android.widget.ImageView, com.android.dialer.glidephotomanager.PhotoInfo):void");
    }

    public void loadQuickContactBadge(QuickContactBadge quickContactBadge, PhotoInfo photoInfo) {
        Uri uri;
        Assert.isMainThread();
        if (TextUtils.isEmpty(photoInfo.getLookupUri())) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("display_name", photoInfo.getFormattedNumber());
                jSONObject.put("display_name_source", 20);
                JSONObject jSONObject2 = new JSONObject();
                JSONObject jSONObject3 = new JSONObject();
                jSONObject3.put("data1", photoInfo.getFormattedNumber());
                jSONObject2.put("vnd.android.cursor.item/phone_v2", new JSONArray().put(jSONObject3));
                jSONObject.put("vnd.android.cursor.item/contact", jSONObject2);
                uri = ContactsContract.Contacts.CONTENT_LOOKUP_URI.buildUpon().appendPath("encoded").encodedFragment(jSONObject.toString()).appendQueryParameter("directory", String.valueOf(Integer.MAX_VALUE)).build();
            } catch (JSONException e) {
                throw new AssertionError(e);
            }
        } else {
            String lookupUri = photoInfo.getLookupUri();
            uri = TextUtils.isEmpty(lookupUri) ? null : Uri.parse(lookupUri);
        }
        quickContactBadge.assignContactUri(uri);
        quickContactBadge.setOverlay((Drawable) null);
        loadContactPhoto(quickContactBadge, photoInfo);
    }
}
