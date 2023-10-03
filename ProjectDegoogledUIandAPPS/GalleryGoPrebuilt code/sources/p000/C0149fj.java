package p000;

import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* renamed from: fj */
/* compiled from: PG */
public class C0149fj extends aac implements C0127eo {

    /* renamed from: a */
    public final C0002ab f9773a = new C0002ab(this);

    /* renamed from: b */
    public boolean f9774b;

    /* renamed from: c */
    public boolean f9775c;

    /* renamed from: e */
    private final C0158fs f9776e = new C0158fs(new C0148fi(this));

    /* renamed from: f */
    private boolean f9777f;

    /* renamed from: g */
    private boolean f9778g;

    /* renamed from: h */
    private boolean f9779h = true;

    /* renamed from: i */
    private int f9780i;

    /* renamed from: j */
    private C0310lg f9781j;

    /* renamed from: e */
    public void mo5853e() {
    }

    /* renamed from: a */
    public final int mo5849a(C0147fh fhVar) {
        if (this.f9781j.mo9338b() < 65534) {
            while (true) {
                C0310lg lgVar = this.f9781j;
                int i = this.f9780i;
                if (lgVar.f15196a) {
                    lgVar.mo9336a();
                }
                if (C0294kr.m14537a(lgVar.f15197b, lgVar.f15199d, i) >= 0) {
                    this.f9780i = (this.f9780i + 1) % 65534;
                } else {
                    int i2 = this.f9780i;
                    this.f9781j.mo9337a(i2, fhVar.f9591j);
                    this.f9780i = (this.f9780i + 1) % 65534;
                    return i2;
                }
            }
        } else {
            throw new IllegalStateException("Too many pending Fragment activity results.");
        }
    }

    /* renamed from: b */
    static void m9012b(int i) {
        if ((i & -65536) != 0) {
            throw new IllegalArgumentException("Can only use lower 16 bits for requestCode");
        }
    }

    /* renamed from: a */
    private final View m9010a(View view, String str, Context context, AttributeSet attributeSet) {
        return this.f9776e.f10343a.f10595e.f10984c.onCreateView(view, str, context, attributeSet);
    }

    public final void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        super.dump(str, fileDescriptor, printWriter, strArr);
        printWriter.print(str);
        printWriter.print("Local FragmentActivity ");
        printWriter.print(Integer.toHexString(System.identityHashCode(this)));
        printWriter.println(" State:");
        String str2 = str + "  ";
        printWriter.print(str2);
        printWriter.print("mCreated=");
        printWriter.print(this.f9777f);
        printWriter.print(" mResumed=");
        printWriter.print(this.f9778g);
        printWriter.print(" mStopped=");
        printWriter.print(this.f9779h);
        if (getApplication() != null) {
            C0205hj.m11569a(this).mo7491a(str2, printWriter);
        }
        this.f9776e.mo6089a().mo6426a(str, fileDescriptor, printWriter, strArr);
    }

    /* renamed from: d */
    public final C0171gd mo5851d() {
        return this.f9776e.mo6089a();
    }

    /* renamed from: f */
    private final void mo9534f() {
        do {
        } while (m9011a(mo5851d(), C0573v.CREATED));
    }

    /* renamed from: a */
    private static boolean m9011a(C0171gd gdVar, C0573v vVar) {
        boolean z = false;
        for (C0147fh fhVar : gdVar.f10982a.mo6826b()) {
            if (fhVar != null) {
                if (fhVar.mo5654o() != null) {
                    z |= m9011a(fhVar.mo5659r(), vVar);
                }
                if (fhVar.mo5ad().mo61a().mo10359a(C0573v.STARTED)) {
                    fhVar.f9583V.mo63a(vVar);
                    z = true;
                }
            }
        }
        return z;
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        this.f9776e.mo6090b();
        int i3 = i >> 16;
        if (i3 != 0) {
            int i4 = i3 - 1;
            String str = (String) this.f9781j.mo9335a(i4);
            this.f9781j.mo9339b(i4);
            if (str == null) {
                Log.w("FragmentActivity", "Activity result delivered for unknown Fragment.");
                return;
            }
            C0147fh a = this.f9776e.mo6088a(str);
            if (a == null) {
                Log.w("FragmentActivity", "Activity result no fragment exists for who: " + str);
                return;
            }
            a.mo2665a((int) (char) i, i2, intent);
            return;
        }
        super.onActivityResult(i, i2, intent);
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.f9776e.mo6090b();
        this.f9776e.f10343a.f10595e.mo6420a(configuration);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        int length;
        C0160fu fuVar = this.f9776e.f10343a;
        fuVar.f10595e.mo6424a(fuVar, fuVar, (C0147fh) null);
        if (bundle != null) {
            this.f9776e.f10343a.f10595e.mo6421a(bundle.getParcelable("android:support:fragments"));
            if (bundle.containsKey("android:support:next_request_index")) {
                this.f9780i = bundle.getInt("android:support:next_request_index");
                int[] intArray = bundle.getIntArray("android:support:request_indicies");
                String[] stringArray = bundle.getStringArray("android:support:request_fragment_who");
                if (intArray == null || stringArray == null || (length = intArray.length) != stringArray.length) {
                    Log.w("FragmentActivity", "Invalid requestCode mapping in savedInstanceState.");
                } else {
                    this.f9781j = new C0310lg(length);
                    for (int i = 0; i < intArray.length; i++) {
                        this.f9781j.mo9337a(intArray[i], stringArray[i]);
                    }
                }
            }
        }
        if (this.f9781j == null) {
            this.f9781j = new C0310lg();
            this.f9780i = 0;
        }
        super.onCreate(bundle);
        this.f9773a.mo62a(C0546u.ON_CREATE);
        this.f9776e.f10343a.f10595e.mo6446e();
    }

    public final boolean onCreatePanelMenu(int i, Menu menu) {
        if (i != 0) {
            return super.onCreatePanelMenu(i, menu);
        }
        boolean onCreatePanelMenu = super.onCreatePanelMenu(0, menu);
        C0158fs fsVar = this.f9776e;
        return onCreatePanelMenu | fsVar.f10343a.f10595e.mo6429a(menu, getMenuInflater());
    }

    public final View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        View a = m9010a(view, str, context, attributeSet);
        return a == null ? super.onCreateView(view, str, context, attributeSet) : a;
    }

    public final View onCreateView(String str, Context context, AttributeSet attributeSet) {
        View a = m9010a((View) null, str, context, attributeSet);
        return a == null ? super.onCreateView(str, context, attributeSet) : a;
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        this.f9776e.f10343a.f10595e.mo6453k();
        this.f9773a.mo62a(C0546u.ON_DESTROY);
    }

    public void onLowMemory() {
        super.onLowMemory();
        this.f9776e.f10343a.f10595e.mo6454l();
    }

    public boolean onMenuItemSelected(int i, MenuItem menuItem) {
        if (super.onMenuItemSelected(i, menuItem)) {
            return true;
        }
        if (i == 0) {
            return this.f9776e.f10343a.f10595e.mo6430a(menuItem);
        }
        if (i != 6) {
            return false;
        }
        return this.f9776e.f10343a.f10595e.mo6439b(menuItem);
    }

    public final void onMultiWindowModeChanged(boolean z) {
        this.f9776e.f10343a.f10595e.mo6427a(z);
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        this.f9776e.mo6090b();
    }

    public final void onPanelClosed(int i, Menu menu) {
        if (i == 0) {
            this.f9776e.f10343a.f10595e.mo6434b(menu);
        }
        super.onPanelClosed(i, menu);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        this.f9778g = false;
        this.f9776e.f10343a.f10595e.mo6451i();
        this.f9773a.mo62a(C0546u.ON_PAUSE);
    }

    public final void onPictureInPictureModeChanged(boolean z) {
        this.f9776e.f10343a.f10595e.mo6437b(z);
    }

    /* access modifiers changed from: protected */
    public void onPostResume() {
        super.onPostResume();
        this.f9773a.mo62a(C0546u.ON_RESUME);
        this.f9776e.f10343a.f10595e.mo6450h();
    }

    public final boolean onPreparePanel(int i, View view, Menu menu) {
        if (i == 0) {
            return super.onPreparePanel(0, view, menu) | this.f9776e.f10343a.f10595e.mo6428a(menu);
        }
        return super.onPreparePanel(i, view, menu);
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        this.f9776e.mo6090b();
        int i2 = i >>> 16;
        if (i2 != 0) {
            int i3 = i2 - 1;
            String str = (String) this.f9781j.mo9335a(i3);
            this.f9781j.mo9339b(i3);
            if (str == null) {
                Log.w("FragmentActivity", "Activity result delivered for unknown Fragment.");
                return;
            }
            C0147fh a = this.f9776e.mo6088a(str);
            if (a == null) {
                Log.w("FragmentActivity", "Activity result no fragment exists for who: " + str);
                return;
            }
            a.mo2705a((int) (char) i, strArr, iArr);
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        this.f9778g = true;
        this.f9776e.mo6090b();
        this.f9776e.mo6091c();
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        mo9534f();
        this.f9773a.mo62a(C0546u.ON_STOP);
        Parcelable d = this.f9776e.f10343a.f10595e.mo6444d();
        if (d != null) {
            bundle.putParcelable("android:support:fragments", d);
        }
        if (this.f9781j.mo9338b() > 0) {
            bundle.putInt("android:support:next_request_index", this.f9780i);
            int[] iArr = new int[this.f9781j.mo9338b()];
            String[] strArr = new String[this.f9781j.mo9338b()];
            for (int i = 0; i < this.f9781j.mo9338b(); i++) {
                iArr[i] = this.f9781j.mo9340c(i);
                strArr[i] = (String) this.f9781j.mo9342d(i);
            }
            bundle.putIntArray("android:support:request_indicies", iArr);
            bundle.putStringArray("android:support:request_fragment_who", strArr);
        }
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        this.f9779h = false;
        if (!this.f9777f) {
            this.f9777f = true;
            this.f9776e.f10343a.f10595e.mo6448f();
        }
        this.f9776e.mo6090b();
        this.f9776e.mo6091c();
        this.f9773a.mo62a(C0546u.ON_START);
        this.f9776e.f10343a.f10595e.mo6449g();
    }

    public final void onStateNotSaved() {
        this.f9776e.mo6090b();
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        this.f9779h = true;
        mo9534f();
        this.f9776e.f10343a.f10595e.mo6452j();
        this.f9773a.mo62a(C0546u.ON_STOP);
    }

    public void startActivityForResult(Intent intent, int i) {
        if (!this.f9775c && i != -1) {
            m9012b(i);
        }
        super.startActivityForResult(intent, i);
    }

    public void startActivityForResult(Intent intent, int i, Bundle bundle) {
        if (!this.f9775c && i != -1) {
            m9012b(i);
        }
        super.startActivityForResult(intent, i, bundle);
    }

    public final void startIntentSenderForResult(IntentSender intentSender, int i, Intent intent, int i2, int i3, int i4) {
        if (i != -1) {
            m9012b(i);
        }
        super.startIntentSenderForResult(intentSender, i, intent, i2, i3, i4);
    }

    public final void startIntentSenderForResult(IntentSender intentSender, int i, Intent intent, int i2, int i3, int i4, Bundle bundle) {
        if (i != -1) {
            m9012b(i);
        }
        super.startIntentSenderForResult(intentSender, i, intent, i2, i3, i4, bundle);
    }

    @Deprecated
    /* renamed from: c */
    public void mo5850c() {
        invalidateOptionsMenu();
    }

    /* renamed from: a */
    public final void mo5082a(int i) {
        if (!this.f9774b && i != -1) {
            m9012b(i);
        }
    }
}
