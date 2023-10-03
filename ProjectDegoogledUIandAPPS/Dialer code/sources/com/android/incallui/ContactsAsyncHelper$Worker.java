package com.android.incallui;

import com.android.dialer.common.concurrent.DialerExecutor;

class ContactsAsyncHelper$Worker implements DialerExecutor.Worker<ContactsAsyncHelper$WorkerArgs, Void> {
    /* synthetic */ ContactsAsyncHelper$Worker(ContactsAsyncHelper$1 contactsAsyncHelper$1) {
    }

    /* JADX WARNING: Removed duplicated region for block: B:45:0x00ab A[SYNTHETIC, Splitter:B:45:0x00ab] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object doInBackground(java.lang.Object r11) throws java.lang.Throwable {
        /*
            r10 = this;
            com.android.incallui.ContactsAsyncHelper$WorkerArgs r11 = (com.android.incallui.ContactsAsyncHelper$WorkerArgs) r11
            java.lang.String r10 = "Unable to close input stream."
            java.lang.String r0 = "ContactsAsyncHelper.Worker.doInBackground"
            r1 = 0
            android.content.Context r2 = r11.context     // Catch:{ Exception -> 0x0017 }
            android.content.ContentResolver r2 = r2.getContentResolver()     // Catch:{ Exception -> 0x0017 }
            android.net.Uri r3 = r11.displayPhotoUri     // Catch:{ Exception -> 0x0017 }
            java.io.InputStream r2 = r2.openInputStream(r3)     // Catch:{ Exception -> 0x0017 }
            goto L_0x001e
        L_0x0014:
            r11 = move-exception
            goto L_0x00a9
        L_0x0017:
            r2 = move-exception
            java.lang.String r3 = "error opening photo input stream"
            com.android.dialer.common.LogUtil.m7e((java.lang.String) r0, (java.lang.String) r3, (java.lang.Throwable) r2)     // Catch:{ all -> 0x0014 }
            r2 = r1
        L_0x001e:
            r3 = 0
            r4 = 1
            if (r2 == 0) goto L_0x007f
            android.net.Uri r5 = r11.displayPhotoUri     // Catch:{ all -> 0x00a7 }
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x00a7 }
            android.graphics.drawable.Drawable r5 = android.graphics.drawable.Drawable.createFromStream(r2, r5)     // Catch:{ all -> 0x00a7 }
            r11.photo = r5     // Catch:{ all -> 0x00a7 }
            android.content.Context r5 = r11.context     // Catch:{ all -> 0x00a7 }
            android.graphics.drawable.Drawable r6 = r11.photo     // Catch:{ all -> 0x00a7 }
            boolean r7 = r6 instanceof android.graphics.drawable.BitmapDrawable     // Catch:{ all -> 0x00a7 }
            if (r7 != 0) goto L_0x0037
            goto L_0x0073
        L_0x0037:
            android.content.res.Resources r5 = r5.getResources()     // Catch:{ all -> 0x00a7 }
            r7 = 2131165604(0x7f0701a4, float:1.794543E38)
            int r5 = r5.getDimensionPixelSize(r7)     // Catch:{ all -> 0x00a7 }
            android.graphics.drawable.BitmapDrawable r6 = (android.graphics.drawable.BitmapDrawable) r6     // Catch:{ all -> 0x00a7 }
            android.graphics.Bitmap r6 = r6.getBitmap()     // Catch:{ all -> 0x00a7 }
            int r7 = r6.getWidth()     // Catch:{ all -> 0x00a7 }
            int r8 = r6.getHeight()     // Catch:{ all -> 0x00a7 }
            if (r7 <= r8) goto L_0x0054
            r9 = r7
            goto L_0x0055
        L_0x0054:
            r9 = r8
        L_0x0055:
            if (r9 <= r5) goto L_0x0075
            float r9 = (float) r9     // Catch:{ all -> 0x00a7 }
            float r5 = (float) r5     // Catch:{ all -> 0x00a7 }
            float r9 = r9 / r5
            float r5 = (float) r7     // Catch:{ all -> 0x00a7 }
            float r5 = r5 / r9
            int r5 = (int) r5     // Catch:{ all -> 0x00a7 }
            float r7 = (float) r8     // Catch:{ all -> 0x00a7 }
            float r7 = r7 / r9
            int r7 = (int) r7     // Catch:{ all -> 0x00a7 }
            if (r5 <= 0) goto L_0x006a
            if (r7 > 0) goto L_0x0065
            goto L_0x006a
        L_0x0065:
            android.graphics.Bitmap r5 = android.graphics.Bitmap.createScaledBitmap(r6, r5, r7, r4)     // Catch:{ all -> 0x00a7 }
            goto L_0x0076
        L_0x006a:
            java.lang.Object[] r5 = new java.lang.Object[r3]     // Catch:{ all -> 0x00a7 }
            java.lang.String r6 = "ContactsAsyncHelper.Worker.getPhotoIconWhenAppropriate"
            java.lang.String r7 = "Photo icon's width or height become 0."
            com.android.dialer.common.LogUtil.m10w(r6, r7, r5)     // Catch:{ all -> 0x00a7 }
        L_0x0073:
            r5 = r1
            goto L_0x0076
        L_0x0075:
            r5 = r6
        L_0x0076:
            r11.photoIcon = r5     // Catch:{ all -> 0x00a7 }
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch:{ all -> 0x00a7 }
            android.net.Uri r5 = r11.displayPhotoUri     // Catch:{ all -> 0x00a7 }
            r4[r3] = r5     // Catch:{ all -> 0x00a7 }
            goto L_0x0089
        L_0x007f:
            r11.photo = r1     // Catch:{ all -> 0x00a7 }
            r11.photoIcon = r1     // Catch:{ all -> 0x00a7 }
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch:{ all -> 0x00a7 }
            android.net.Uri r5 = r11.displayPhotoUri     // Catch:{ all -> 0x00a7 }
            r4[r3] = r5     // Catch:{ all -> 0x00a7 }
        L_0x0089:
            com.android.incallui.ContactsAsyncHelper$OnImageLoadCompleteListener r3 = r11.listener     // Catch:{ all -> 0x00a7 }
            if (r3 == 0) goto L_0x009c
            com.android.incallui.ContactsAsyncHelper$OnImageLoadCompleteListener r3 = r11.listener     // Catch:{ all -> 0x00a7 }
            int r4 = r11.token     // Catch:{ all -> 0x00a7 }
            android.graphics.drawable.Drawable r5 = r11.photo     // Catch:{ all -> 0x00a7 }
            android.graphics.Bitmap r6 = r11.photoIcon     // Catch:{ all -> 0x00a7 }
            java.lang.Object r11 = r11.cookie     // Catch:{ all -> 0x00a7 }
            com.android.incallui.ContactInfoCache r3 = (com.android.incallui.ContactInfoCache) r3
            r3.onImageLoaded(r4, r5, r6, r11)     // Catch:{ all -> 0x00a7 }
        L_0x009c:
            if (r2 == 0) goto L_0x00a6
            r2.close()     // Catch:{ IOException -> 0x00a2 }
            goto L_0x00a6
        L_0x00a2:
            r11 = move-exception
            com.android.dialer.common.LogUtil.m7e((java.lang.String) r0, (java.lang.String) r10, (java.lang.Throwable) r11)
        L_0x00a6:
            return r1
        L_0x00a7:
            r11 = move-exception
            r1 = r2
        L_0x00a9:
            if (r1 == 0) goto L_0x00b3
            r1.close()     // Catch:{ IOException -> 0x00af }
            goto L_0x00b3
        L_0x00af:
            r1 = move-exception
            com.android.dialer.common.LogUtil.m7e((java.lang.String) r0, (java.lang.String) r10, (java.lang.Throwable) r1)
        L_0x00b3:
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.incallui.ContactsAsyncHelper$Worker.doInBackground(java.lang.Object):java.lang.Object");
    }
}
