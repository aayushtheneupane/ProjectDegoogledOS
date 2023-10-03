package com.google.android.systemui.smartspace;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import com.android.systemui.C1782R$plurals;
import com.android.systemui.C1784R$string;
import com.google.android.systemui.smartspace.nano.SmartspaceProto;

public class SmartSpaceCard {
    private static int sRequestCode;
    private final SmartspaceProto.SmartspaceUpdate.SmartspaceCard mCard;
    private final Context mContext;
    private Bitmap mIcon;
    private boolean mIconProcessed;
    private final Intent mIntent;
    private final boolean mIsIconGrayscale;
    private final boolean mIsWeather;
    private final long mPublishTime;
    private int mRequestCode;

    public SmartSpaceCard(Context context, SmartspaceProto.SmartspaceUpdate.SmartspaceCard smartspaceCard, Intent intent, boolean z, Bitmap bitmap, boolean z2, long j) {
        this.mContext = context.getApplicationContext();
        this.mCard = smartspaceCard;
        this.mIsWeather = z;
        this.mIntent = intent;
        this.mIcon = bitmap;
        this.mPublishTime = j;
        this.mIsIconGrayscale = z2;
        int i = sRequestCode + 1;
        sRequestCode = i;
        if (i > 2147483646) {
            sRequestCode = 0;
        }
        this.mRequestCode = sRequestCode;
    }

    public Intent getIntent() {
        return this.mIntent;
    }

    public Bitmap getIcon() {
        return this.mIcon;
    }

    public void setIcon(Bitmap bitmap) {
        this.mIcon = bitmap;
    }

    public void setIconProcessed(boolean z) {
        this.mIconProcessed = z;
    }

    public boolean isIconProcessed() {
        return this.mIconProcessed;
    }

    public String getTitle() {
        return substitute(true);
    }

    public CharSequence getFormattedTitle() {
        SmartspaceProto.SmartspaceUpdate.SmartspaceCard.Message.FormattedText formattedText;
        String str;
        SmartspaceProto.SmartspaceUpdate.SmartspaceCard.Message.FormattedText.FormatParam[] formatParamArr;
        SmartspaceProto.SmartspaceUpdate.SmartspaceCard.Message message = getMessage();
        if (message == null || (formattedText = message.title) == null || (str = formattedText.text) == null) {
            return "";
        }
        if (!hasParams(formattedText)) {
            return str;
        }
        String str2 = null;
        String str3 = null;
        int i = 0;
        while (true) {
            formatParamArr = formattedText.formatParam;
            if (i >= formatParamArr.length) {
                break;
            }
            SmartspaceProto.SmartspaceUpdate.SmartspaceCard.Message.FormattedText.FormatParam formatParam = formatParamArr[i];
            if (formatParam != null) {
                int i2 = formatParam.formatParamArgs;
                if (i2 == 1 || i2 == 2) {
                    str3 = getDurationText(formatParam);
                } else if (i2 == 3) {
                    str2 = formatParam.text;
                }
            }
            i++;
        }
        if (this.mCard.cardType == 3 && formatParamArr.length == 2) {
            str3 = formatParamArr[0].text;
            str2 = formatParamArr[1].text;
        }
        if (str2 == null) {
            return "";
        }
        if (str3 == null) {
            if (message != this.mCard.duringEvent) {
                return str;
            }
            str3 = this.mContext.getString(C1784R$string.smartspace_now);
        }
        return this.mContext.getString(C1784R$string.smartspace_pill_text_format, new Object[]{str3, str2});
    }

    public String getSubtitle() {
        return substitute(false);
    }

    private SmartspaceProto.SmartspaceUpdate.SmartspaceCard.Message getMessage() {
        SmartspaceProto.SmartspaceUpdate.SmartspaceCard.Message message;
        SmartspaceProto.SmartspaceUpdate.SmartspaceCard.Message message2;
        long currentTimeMillis = System.currentTimeMillis();
        SmartspaceProto.SmartspaceUpdate.SmartspaceCard smartspaceCard = this.mCard;
        long j = smartspaceCard.eventTimeMillis;
        long j2 = smartspaceCard.eventDurationMillis + j;
        if (currentTimeMillis < j && (message2 = smartspaceCard.preEvent) != null) {
            return message2;
        }
        if (currentTimeMillis > j2 && (message = this.mCard.postEvent) != null) {
            return message;
        }
        SmartspaceProto.SmartspaceUpdate.SmartspaceCard.Message message3 = this.mCard.duringEvent;
        if (message3 != null) {
            return message3;
        }
        return null;
    }

    private SmartspaceProto.SmartspaceUpdate.SmartspaceCard.Message.FormattedText getFormattedText(boolean z) {
        SmartspaceProto.SmartspaceUpdate.SmartspaceCard.Message message = getMessage();
        if (message == null) {
            return null;
        }
        return z ? message.title : message.subtitle;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0006, code lost:
        r0 = r1.formatParam;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean hasParams(com.google.android.systemui.smartspace.nano.SmartspaceProto.SmartspaceUpdate.SmartspaceCard.Message.FormattedText r1) {
        /*
            r0 = this;
            if (r1 == 0) goto L_0x000f
            java.lang.String r0 = r1.text
            if (r0 == 0) goto L_0x000f
            com.google.android.systemui.smartspace.nano.SmartspaceProto$SmartspaceUpdate$SmartspaceCard$Message$FormattedText$FormatParam[] r0 = r1.formatParam
            if (r0 == 0) goto L_0x000f
            int r0 = r0.length
            if (r0 <= 0) goto L_0x000f
            r0 = 1
            return r0
        L_0x000f:
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.smartspace.SmartSpaceCard.hasParams(com.google.android.systemui.smartspace.nano.SmartspaceProto$SmartspaceUpdate$SmartspaceCard$Message$FormattedText):boolean");
    }

    public long getMillisToEvent(SmartspaceProto.SmartspaceUpdate.SmartspaceCard.Message.FormattedText.FormatParam formatParam) {
        long j;
        if (formatParam.formatParamArgs == 2) {
            SmartspaceProto.SmartspaceUpdate.SmartspaceCard smartspaceCard = this.mCard;
            j = smartspaceCard.eventTimeMillis + smartspaceCard.eventDurationMillis;
        } else {
            j = this.mCard.eventTimeMillis;
        }
        return Math.abs(System.currentTimeMillis() - j);
    }

    private int getMinutesToEvent(SmartspaceProto.SmartspaceUpdate.SmartspaceCard.Message.FormattedText.FormatParam formatParam) {
        return (int) Math.ceil(((double) getMillisToEvent(formatParam)) / 60000.0d);
    }

    private String substitute(boolean z) {
        return substitute(z, (String) null);
    }

    private String[] getTextArgs(SmartspaceProto.SmartspaceUpdate.SmartspaceCard.Message.FormattedText.FormatParam[] formatParamArr, String str) {
        String[] strArr = new String[formatParamArr.length];
        for (int i = 0; i < strArr.length; i++) {
            int i2 = formatParamArr[i].formatParamArgs;
            if (i2 == 1 || i2 == 2) {
                strArr[i] = getDurationText(formatParamArr[i]);
            } else {
                String str2 = "";
                if (i2 != 3) {
                    strArr[i] = str2;
                } else if (str == null || formatParamArr[i].truncateLocation == 0) {
                    if (formatParamArr[i].text != null) {
                        str2 = formatParamArr[i].text;
                    }
                    strArr[i] = str2;
                } else {
                    strArr[i] = str;
                }
            }
        }
        return strArr;
    }

    private String getDurationText(SmartspaceProto.SmartspaceUpdate.SmartspaceCard.Message.FormattedText.FormatParam formatParam) {
        int minutesToEvent = getMinutesToEvent(formatParam);
        if (minutesToEvent >= 60) {
            int i = minutesToEvent / 60;
            int i2 = minutesToEvent % 60;
            String quantityString = this.mContext.getResources().getQuantityString(C1782R$plurals.smartspace_hours, i, new Object[]{Integer.valueOf(i)});
            if (i2 <= 0) {
                return quantityString;
            }
            String quantityString2 = this.mContext.getResources().getQuantityString(C1782R$plurals.smartspace_minutes, i2, new Object[]{Integer.valueOf(i2)});
            return this.mContext.getString(C1784R$string.smartspace_hours_mins, new Object[]{quantityString, quantityString2});
        }
        return this.mContext.getResources().getQuantityString(C1782R$plurals.smartspace_minutes, minutesToEvent, new Object[]{Integer.valueOf(minutesToEvent)});
    }

    private String substitute(boolean z, String str) {
        String str2;
        SmartspaceProto.SmartspaceUpdate.SmartspaceCard.Message.FormattedText formattedText = getFormattedText(z);
        if (formattedText == null || (str2 = formattedText.text) == null) {
            return "";
        }
        return hasParams(formattedText) ? String.format(str2, getTextArgs(formattedText.formatParam, str)) : str2;
    }

    public boolean isExpired() {
        return System.currentTimeMillis() > getExpiration();
    }

    public long getExpiration() {
        SmartspaceProto.SmartspaceUpdate.SmartspaceCard.ExpiryCriteria expiryCriteria;
        SmartspaceProto.SmartspaceUpdate.SmartspaceCard smartspaceCard = this.mCard;
        if (smartspaceCard == null || (expiryCriteria = smartspaceCard.expiryCriteria) == null) {
            return 0;
        }
        return expiryCriteria.expirationTimeMillis;
    }

    public String toString() {
        return "title:" + getTitle() + " subtitle:" + getSubtitle() + " expires:" + getExpiration() + " published:" + this.mPublishTime;
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0029 A[Catch:{ Exception -> 0x0069 }] */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0033 A[Catch:{ Exception -> 0x0069 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static com.google.android.systemui.smartspace.SmartSpaceCard fromWrapper(android.content.Context r11, com.google.android.systemui.smartspace.nano.SmartspaceProto.CardWrapper r12, boolean r13) {
        /*
            r0 = 0
            if (r12 != 0) goto L_0x0004
            return r0
        L_0x0004:
            com.google.android.systemui.smartspace.nano.SmartspaceProto$SmartspaceUpdate$SmartspaceCard r1 = r12.card     // Catch:{ Exception -> 0x0069 }
            com.google.android.systemui.smartspace.nano.SmartspaceProto$SmartspaceUpdate$SmartspaceCard$TapAction r1 = r1.tapAction     // Catch:{ Exception -> 0x0069 }
            r2 = 0
            if (r1 == 0) goto L_0x0024
            com.google.android.systemui.smartspace.nano.SmartspaceProto$SmartspaceUpdate$SmartspaceCard r1 = r12.card     // Catch:{ Exception -> 0x0069 }
            com.google.android.systemui.smartspace.nano.SmartspaceProto$SmartspaceUpdate$SmartspaceCard$TapAction r1 = r1.tapAction     // Catch:{ Exception -> 0x0069 }
            java.lang.String r1 = r1.intent     // Catch:{ Exception -> 0x0069 }
            boolean r1 = android.text.TextUtils.isEmpty(r1)     // Catch:{ Exception -> 0x0069 }
            if (r1 == 0) goto L_0x0018
            goto L_0x0024
        L_0x0018:
            com.google.android.systemui.smartspace.nano.SmartspaceProto$SmartspaceUpdate$SmartspaceCard r1 = r12.card     // Catch:{ Exception -> 0x0069 }
            com.google.android.systemui.smartspace.nano.SmartspaceProto$SmartspaceUpdate$SmartspaceCard$TapAction r1 = r1.tapAction     // Catch:{ Exception -> 0x0069 }
            java.lang.String r1 = r1.intent     // Catch:{ Exception -> 0x0069 }
            android.content.Intent r1 = android.content.Intent.parseUri(r1, r2)     // Catch:{ Exception -> 0x0069 }
            r4 = r1
            goto L_0x0025
        L_0x0024:
            r4 = r0
        L_0x0025:
            byte[] r1 = r12.icon     // Catch:{ Exception -> 0x0069 }
            if (r1 == 0) goto L_0x0033
            byte[] r1 = r12.icon     // Catch:{ Exception -> 0x0069 }
            byte[] r3 = r12.icon     // Catch:{ Exception -> 0x0069 }
            int r3 = r3.length     // Catch:{ Exception -> 0x0069 }
            android.graphics.Bitmap r1 = android.graphics.BitmapFactory.decodeByteArray(r1, r2, r3, r0)     // Catch:{ Exception -> 0x0069 }
            goto L_0x0034
        L_0x0033:
            r1 = r0
        L_0x0034:
            android.content.res.Resources r2 = r11.getResources()     // Catch:{ Exception -> 0x0069 }
            int r3 = com.android.systemui.C1775R$dimen.header_icon_size     // Catch:{ Exception -> 0x0069 }
            int r2 = r2.getDimensionPixelSize(r3)     // Catch:{ Exception -> 0x0069 }
            if (r1 == 0) goto L_0x0059
            int r3 = r1.getHeight()     // Catch:{ Exception -> 0x0069 }
            if (r3 <= r2) goto L_0x0059
            int r3 = r1.getWidth()     // Catch:{ Exception -> 0x0069 }
            float r3 = (float) r3     // Catch:{ Exception -> 0x0069 }
            float r5 = (float) r2     // Catch:{ Exception -> 0x0069 }
            int r6 = r1.getHeight()     // Catch:{ Exception -> 0x0069 }
            float r6 = (float) r6     // Catch:{ Exception -> 0x0069 }
            float r5 = r5 / r6
            float r3 = r3 * r5
            int r3 = (int) r3     // Catch:{ Exception -> 0x0069 }
            r5 = 1
            android.graphics.Bitmap r1 = android.graphics.Bitmap.createScaledBitmap(r1, r3, r2, r5)     // Catch:{ Exception -> 0x0069 }
        L_0x0059:
            r6 = r1
            com.google.android.systemui.smartspace.SmartSpaceCard r10 = new com.google.android.systemui.smartspace.SmartSpaceCard     // Catch:{ Exception -> 0x0069 }
            com.google.android.systemui.smartspace.nano.SmartspaceProto$SmartspaceUpdate$SmartspaceCard r3 = r12.card     // Catch:{ Exception -> 0x0069 }
            boolean r7 = r12.isIconGrayscale     // Catch:{ Exception -> 0x0069 }
            long r8 = r12.publishTime     // Catch:{ Exception -> 0x0069 }
            r1 = r10
            r2 = r11
            r5 = r13
            r1.<init>(r2, r3, r4, r5, r6, r7, r8)     // Catch:{ Exception -> 0x0069 }
            return r10
        L_0x0069:
            r11 = move-exception
            java.lang.String r12 = "SmartspaceCard"
            java.lang.String r13 = "from proto"
            android.util.Log.e(r12, r13, r11)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.smartspace.SmartSpaceCard.fromWrapper(android.content.Context, com.google.android.systemui.smartspace.nano.SmartspaceProto$CardWrapper, boolean):com.google.android.systemui.smartspace.SmartSpaceCard");
    }

    public void performCardAction(View view) {
        if (this.mCard.tapAction == null) {
            Log.e("SmartspaceCard", "no tap action available: " + this);
            return;
        }
        Intent intent = new Intent(getIntent());
        int i = this.mCard.tapAction.actionType;
        if (i == 1) {
            intent.addFlags(268435456);
            intent.setPackage("com.google.android.googlequicksearchbox");
            try {
                view.getContext().sendBroadcast(intent);
            } catch (SecurityException e) {
                Log.w("SmartspaceCard", "Cannot perform click action", e);
            }
        } else if (i != 2) {
            Log.w("SmartspaceCard", "unrecognized tap action: " + this.mCard.tapAction.actionType);
        } else {
            this.mContext.startActivity(intent);
        }
    }

    public PendingIntent getPendingIntent() {
        if (this.mCard.tapAction == null) {
            return null;
        }
        Intent intent = new Intent(getIntent());
        int i = this.mCard.tapAction.actionType;
        if (i == 1) {
            intent.addFlags(268435456);
            intent.setPackage("com.google.android.googlequicksearchbox");
            return PendingIntent.getBroadcast(this.mContext, this.mRequestCode, intent, 0);
        } else if (i != 2) {
            return null;
        } else {
            return PendingIntent.getActivity(this.mContext, this.mRequestCode, intent, 0);
        }
    }
}
