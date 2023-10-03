package com.android.messaging.widget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Binder;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;
import com.android.messaging.R;
import com.android.messaging.datamodel.p038b.C0881u;
import com.android.messaging.util.C1430e;
import p026b.p027a.p030b.p031a.C0632a;

/* renamed from: com.android.messaging.widget.a */
abstract class C1489a implements RemoteViewsService.RemoteViewsFactory {

    /* renamed from: kM */
    protected static final Object f2361kM = new Object();

    /* renamed from: hM */
    protected boolean f2362hM;

    /* renamed from: iM */
    protected final AppWidgetManager f2363iM;

    /* renamed from: jM */
    protected C0881u f2364jM;
    protected final int mAppWidgetId;
    protected final Context mContext;
    protected Cursor mCursor;
    protected int mIconSize;

    public C1489a(Context context, Intent intent) {
        this.mContext = context;
        this.mAppWidgetId = intent.getIntExtra("appWidgetId", 0);
        this.f2363iM = AppWidgetManager.getInstance(context);
        if (Log.isLoggable("MessagingAppWidget", 2)) {
            C1430e.m3628v("MessagingAppWidget", "BaseWidgetFactory intent: " + intent + "widget id: " + this.mAppWidgetId);
        }
        this.mIconSize = (int) context.getResources().getDimension(R.dimen.contact_icon_view_normal_size);
    }

    /* JADX WARNING: type inference failed for: r1v6, types: [com.android.messaging.datamodel.b.b] */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* renamed from: F */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.graphics.Bitmap mo8251F(android.net.Uri r10) {
        /*
            r9 = this;
            r0 = 0
            if (r10 != 0) goto L_0x0005
            r1 = r0
            goto L_0x0009
        L_0x0005:
            java.lang.String r1 = com.android.messaging.util.C1426c.m3603p(r10)
        L_0x0009:
            java.lang.String r2 = "g"
            boolean r1 = r2.equals(r1)
            if (r1 == 0) goto L_0x0019
            com.android.messaging.datamodel.b.b r1 = new com.android.messaging.datamodel.b.b
            int r2 = r9.mIconSize
            r1.<init>(r10, r2, r2)
            goto L_0x0025
        L_0x0019:
            com.android.messaging.datamodel.b.d r1 = new com.android.messaging.datamodel.b.d
            int r6 = r9.mIconSize
            r7 = 1
            r8 = 0
            r3 = r1
            r4 = r10
            r5 = r6
            r3.<init>(r4, r5, r6, r7, r8)
        L_0x0025:
            android.content.Context r10 = r9.mContext
            com.android.messaging.datamodel.b.w r10 = r1.mo6084n(r10)
            com.android.messaging.datamodel.b.C r1 = com.android.messaging.datamodel.p038b.C0840C.get()
            com.android.messaging.datamodel.b.I r10 = r1.mo6082b(r10)
            com.android.messaging.datamodel.b.u r10 = (com.android.messaging.datamodel.p038b.C0881u) r10
            if (r10 == 0) goto L_0x004b
            com.android.messaging.datamodel.b.u r1 = r9.f2364jM
            if (r1 == r10) goto L_0x0044
            if (r1 == 0) goto L_0x0040
            r1.release()
        L_0x0040:
            r9.f2364jM = r0
            r9.f2364jM = r10
        L_0x0044:
            com.android.messaging.datamodel.b.u r9 = r9.f2364jM
            android.graphics.Bitmap r9 = r9.getBitmap()
            return r9
        L_0x004b:
            com.android.messaging.datamodel.b.u r10 = r9.f2364jM
            if (r10 == 0) goto L_0x0052
            r10.release()
        L_0x0052:
            r9.f2364jM = r0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.widget.C1489a.mo8251F(android.net.Uri):android.graphics.Bitmap");
    }

    /* access modifiers changed from: protected */
    /* renamed from: Mk */
    public abstract Cursor mo8252Mk();

    /* access modifiers changed from: protected */
    /* renamed from: Nk */
    public abstract int mo8253Nk();

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public CharSequence mo8254a(CharSequence charSequence, boolean z) {
        if (!z) {
            return charSequence;
        }
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(charSequence);
        spannableStringBuilder.setSpan(new StyleSpan(1), 0, charSequence.length(), 33);
        return spannableStringBuilder;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0019, code lost:
        return 0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int getCount() {
        /*
            r6 = this;
            java.lang.Object r0 = f2361kM
            monitor-enter(r0)
            android.database.Cursor r1 = r6.mCursor     // Catch:{ all -> 0x0052 }
            r2 = 2
            r3 = 0
            if (r1 != 0) goto L_0x001a
            java.lang.String r6 = "MessagingAppWidget"
            boolean r6 = android.util.Log.isLoggable(r6, r2)     // Catch:{ all -> 0x0052 }
            if (r6 == 0) goto L_0x0018
            java.lang.String r6 = "MessagingAppWidget"
            java.lang.String r1 = "getCount: 0"
            com.android.messaging.util.C1430e.m3628v(r6, r1)     // Catch:{ all -> 0x0052 }
        L_0x0018:
            monitor-exit(r0)     // Catch:{ all -> 0x0052 }
            return r3
        L_0x001a:
            int r1 = r6.getItemCount()     // Catch:{ all -> 0x0052 }
            java.lang.String r4 = "MessagingAppWidget"
            boolean r2 = android.util.Log.isLoggable(r4, r2)     // Catch:{ all -> 0x0052 }
            if (r2 == 0) goto L_0x003c
            java.lang.String r2 = "MessagingAppWidget"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0052 }
            r4.<init>()     // Catch:{ all -> 0x0052 }
            java.lang.String r5 = "getCount: "
            r4.append(r5)     // Catch:{ all -> 0x0052 }
            r4.append(r1)     // Catch:{ all -> 0x0052 }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x0052 }
            com.android.messaging.util.C1430e.m3628v(r2, r4)     // Catch:{ all -> 0x0052 }
        L_0x003c:
            android.database.Cursor r2 = r6.mCursor     // Catch:{ all -> 0x0052 }
            int r2 = r2.getCount()     // Catch:{ all -> 0x0052 }
            r4 = 1
            if (r1 >= r2) goto L_0x0047
            r2 = r4
            goto L_0x0048
        L_0x0047:
            r2 = r3
        L_0x0048:
            r6.f2362hM = r2     // Catch:{ all -> 0x0052 }
            boolean r6 = r6.f2362hM     // Catch:{ all -> 0x0052 }
            if (r6 == 0) goto L_0x004f
            r3 = r4
        L_0x004f:
            int r1 = r1 + r3
            monitor-exit(r0)     // Catch:{ all -> 0x0052 }
            return r1
        L_0x0052:
            r6 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0052 }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.widget.C1489a.getCount():int");
    }

    /* access modifiers changed from: protected */
    public int getItemCount() {
        if (Log.isLoggable("MessagingAppWidget", 2)) {
            StringBuilder Pa = C0632a.m1011Pa("getItemCount: ");
            Pa.append(this.mCursor.getCount());
            C1430e.m3628v("MessagingAppWidget", Pa.toString());
        }
        return Math.min(this.mCursor.getCount(), 25);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public boolean hasStableIds() {
        return true;
    }

    public void onCreate() {
        if (Log.isLoggable("MessagingAppWidget", 2)) {
            C1430e.m3628v("MessagingAppWidget", "onCreate");
        }
    }

    public void onDataSetChanged() {
        if (Log.isLoggable("MessagingAppWidget", 2)) {
            C1430e.m3628v("MessagingAppWidget", "onDataSetChanged");
        }
        synchronized (f2361kM) {
            if (this.mCursor != null) {
                this.mCursor.close();
                this.mCursor = null;
            }
            long clearCallingIdentity = Binder.clearCallingIdentity();
            try {
                this.mCursor = mo8252Mk();
                if (Log.isLoggable("MessagingAppWidget", 2)) {
                    C1430e.m3628v("MessagingAppWidget", "onLoadComplete");
                }
                this.f2363iM.partiallyUpdateAppWidget(this.mAppWidgetId, new RemoteViews(this.mContext.getPackageName(), mo8253Nk()));
            } finally {
                Binder.restoreCallingIdentity(clearCallingIdentity);
            }
        }
    }

    public void onDestroy() {
        if (Log.isLoggable("MessagingAppWidget", 2)) {
            C1430e.m3628v("MessagingAppWidget", "onDestroy");
        }
        synchronized (f2361kM) {
            if (this.mCursor != null && !this.mCursor.isClosed()) {
                this.mCursor.close();
                this.mCursor = null;
            }
        }
    }
}
