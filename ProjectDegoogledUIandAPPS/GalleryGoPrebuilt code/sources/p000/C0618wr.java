package p000;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.support.p002v7.view.menu.ListMenuItemView;
import android.view.KeyEvent;
import android.view.MenuItem;

/* renamed from: wr */
/* compiled from: PG */
public final class C0618wr extends C0582vi {

    /* renamed from: c */
    public C0617wq f16269c;

    /* renamed from: d */
    private final int f16270d;

    /* renamed from: e */
    private final int f16271e;

    /* renamed from: f */
    private MenuItem f16272f;

    public C0618wr(Context context, boolean z) {
        super(context, z);
        Configuration configuration = context.getResources().getConfiguration();
        int i = Build.VERSION.SDK_INT;
        if (configuration.getLayoutDirection() == 1) {
            this.f16270d = 21;
            this.f16271e = 22;
            return;
        }
        this.f16270d = 22;
        this.f16271e = 21;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v6, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v3, resolved type: qz} */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x003a, code lost:
        r3 = (r3 = pointToPosition((int) r9.getX(), (int) r9.getY())) - r1;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean onHoverEvent(android.view.MotionEvent r9) {
        /*
            r8 = this;
            wq r0 = r8.f16269c
            if (r0 != 0) goto L_0x0006
            goto L_0x00c3
        L_0x0006:
            android.widget.ListAdapter r0 = r8.getAdapter()
            boolean r1 = r0 instanceof android.widget.HeaderViewListAdapter
            r2 = 0
            if (r1 == 0) goto L_0x001c
            android.widget.HeaderViewListAdapter r0 = (android.widget.HeaderViewListAdapter) r0
            int r1 = r0.getHeadersCount()
            android.widget.ListAdapter r0 = r0.getWrappedAdapter()
            rd r0 = (p000.C0469rd) r0
            goto L_0x0020
        L_0x001c:
            rd r0 = (p000.C0469rd) r0
            r1 = 0
        L_0x0020:
            int r3 = r9.getAction()
            r4 = 10
            r5 = -1
            r6 = 0
            if (r3 == r4) goto L_0x0049
            float r3 = r9.getX()
            int r3 = (int) r3
            float r4 = r9.getY()
            int r4 = (int) r4
            int r3 = r8.pointToPosition(r3, r4)
            if (r3 == r5) goto L_0x0049
            int r3 = r3 - r1
            if (r3 < 0) goto L_0x0049
            int r1 = r0.getCount()
            if (r3 >= r1) goto L_0x0048
            rj r1 = r0.getItem(r3)
            goto L_0x004a
        L_0x0048:
        L_0x0049:
            r1 = r6
        L_0x004a:
            android.view.MenuItem r3 = r8.f16272f
            if (r3 == r1) goto L_0x00c3
            rg r0 = r0.f15742a
            if (r3 == 0) goto L_0x0063
            wq r3 = r8.f16269c
            ws r3 = (p000.C0619ws) r3
            wq r3 = r3.f16274b
            if (r3 == 0) goto L_0x0063
            qy r3 = (p000.C0463qy) r3
            ra r3 = r3.f15705a
            android.os.Handler r3 = r3.f15709a
            r3.removeCallbacksAndMessages(r0)
        L_0x0063:
            r8.f16272f = r1
            if (r1 == 0) goto L_0x00c3
            wq r3 = r8.f16269c
            ws r3 = (p000.C0619ws) r3
            wq r3 = r3.f16274b
            if (r3 == 0) goto L_0x00c3
            qy r3 = (p000.C0463qy) r3
            ra r4 = r3.f15705a
            android.os.Handler r4 = r4.f15709a
            r4.removeCallbacksAndMessages(r6)
            ra r4 = r3.f15705a
            java.util.List r4 = r4.f15710b
            int r4 = r4.size()
        L_0x0080:
            if (r2 >= r4) goto L_0x0093
            ra r7 = r3.f15705a
            java.util.List r7 = r7.f15710b
            java.lang.Object r7 = r7.get(r2)
            qz r7 = (p000.C0464qz) r7
            rg r7 = r7.f15707b
            if (r0 == r7) goto L_0x0095
            int r2 = r2 + 1
            goto L_0x0080
        L_0x0093:
            r2 = -1
        L_0x0095:
            if (r2 == r5) goto L_0x00c3
            int r2 = r2 + 1
            ra r4 = r3.f15705a
            java.util.List r4 = r4.f15710b
            int r4 = r4.size()
            if (r2 < r4) goto L_0x00a4
            goto L_0x00af
        L_0x00a4:
            ra r4 = r3.f15705a
            java.util.List r4 = r4.f15710b
            java.lang.Object r2 = r4.get(r2)
            r6 = r2
            qz r6 = (p000.C0464qz) r6
        L_0x00af:
            qx r2 = new qx
            r2.<init>(r3, r6, r1, r0)
            long r4 = android.os.SystemClock.uptimeMillis()
            ra r1 = r3.f15705a
            android.os.Handler r1 = r1.f15709a
            r6 = 200(0xc8, double:9.9E-322)
            long r4 = r4 + r6
            r1.postAtTime(r2, r0, r4)
        L_0x00c3:
            boolean r9 = super.onHoverEvent(r9)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.C0618wr.onHoverEvent(android.view.MotionEvent):boolean");
    }

    public final boolean onKeyDown(int i, KeyEvent keyEvent) {
        ListMenuItemView listMenuItemView = (ListMenuItemView) getSelectedView();
        if (listMenuItemView != null && i == this.f16270d) {
            if (listMenuItemView.isEnabled() && listMenuItemView.f870a.hasSubMenu()) {
                performItemClick(listMenuItemView, getSelectedItemPosition(), getSelectedItemId());
            }
            return true;
        } else if (listMenuItemView == null || i != this.f16271e) {
            return super.onKeyDown(i, keyEvent);
        } else {
            setSelection(-1);
            ((C0469rd) getAdapter()).f15742a.mo9835a(false);
            return true;
        }
    }
}
