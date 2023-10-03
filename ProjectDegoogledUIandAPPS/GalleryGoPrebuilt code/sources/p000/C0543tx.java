package p000;

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;

/* renamed from: tx */
/* compiled from: PG */
final class C0543tx implements DialogInterface.OnClickListener, C0552uf {

    /* renamed from: a */
    private C0394oj f15965a;

    /* renamed from: b */
    private ListAdapter f15966b;

    /* renamed from: c */
    private CharSequence f15967c;

    /* renamed from: d */
    private final /* synthetic */ C0553ug f15968d;

    public C0543tx(C0553ug ugVar) {
        this.f15968d = ugVar;
    }

    /* renamed from: a */
    public final CharSequence mo10180a() {
        return this.f15967c;
    }

    /* renamed from: b */
    public final Drawable mo10186b() {
        return null;
    }

    /* renamed from: c */
    public final int mo10188c() {
        return 0;
    }

    /* renamed from: f */
    public final int mo10192f() {
        return 0;
    }

    /* renamed from: d */
    public final void mo10190d() {
        C0394oj ojVar = this.f15965a;
        if (ojVar != null) {
            ojVar.dismiss();
            this.f15965a = null;
        }
    }

    /* renamed from: e */
    public final boolean mo10191e() {
        C0394oj ojVar = this.f15965a;
        if (ojVar == null) {
            return false;
        }
        return ojVar.isShowing();
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        this.f15968d.setSelection(i);
        if (this.f15968d.getOnItemClickListener() != null) {
            this.f15968d.performItemClick((View) null, i, this.f15966b.getItemId(i));
        }
        mo10190d();
    }

    /* renamed from: a */
    public final void mo10184a(ListAdapter listAdapter) {
        this.f15966b = listAdapter;
    }

    /* renamed from: a */
    public final void mo10183a(Drawable drawable) {
        Log.e("AppCompatSpinner", "Cannot set popup background for MODE_DIALOG, ignoring");
    }

    /* renamed from: b */
    public final void mo10187b(int i) {
        Log.e("AppCompatSpinner", "Cannot set horizontal offset for MODE_DIALOG, ignoring");
    }

    /* renamed from: c */
    public final void mo10189c(int i) {
        Log.e("AppCompatSpinner", "Cannot set horizontal (original) offset for MODE_DIALOG, ignoring");
    }

    /* renamed from: a */
    public final void mo10185a(CharSequence charSequence) {
        this.f15967c = charSequence;
    }

    /* renamed from: a */
    public final void mo10181a(int i) {
        Log.e("AppCompatSpinner", "Cannot set vertical offset for MODE_DIALOG, ignoring");
    }

    /* renamed from: a */
    public final void mo10182a(int i, int i2) {
        if (this.f15966b != null) {
            C0393oi oiVar = new C0393oi(this.f15968d.f15982a);
            CharSequence charSequence = this.f15967c;
            if (charSequence != null) {
                oiVar.mo9525b(charSequence);
            }
            ListAdapter listAdapter = this.f15966b;
            int selectedItemPosition = this.f15968d.getSelectedItemPosition();
            C0389oe oeVar = oiVar.f15411a;
            oeVar.f15363p = listAdapter;
            oeVar.f15364q = this;
            oeVar.f15371x = selectedItemPosition;
            oeVar.f15370w = true;
            C0394oj b = oiVar.mo6550b();
            this.f15965a = b;
            ListView listView = b.f15413a.f15391g;
            int i3 = Build.VERSION.SDK_INT;
            listView.setTextDirection(i);
            listView.setTextAlignment(i2);
            this.f15965a.show();
        }
    }
}
