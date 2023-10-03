package p000;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.preference.DialogPreference;

/* renamed from: add */
/* compiled from: PG */
public abstract class add extends C0140fa implements DialogInterface.OnClickListener {

    /* renamed from: Z */
    private DialogPreference f199Z;

    /* renamed from: aa */
    private CharSequence f200aa;

    /* renamed from: ab */
    private CharSequence f201ab;

    /* renamed from: ac */
    public int f202ac;

    /* renamed from: ad */
    private CharSequence f203ad;

    /* renamed from: ae */
    private CharSequence f204ae;

    /* renamed from: af */
    private int f205af;

    /* renamed from: ag */
    private BitmapDrawable f206ag;

    /* access modifiers changed from: protected */
    /* renamed from: P */
    public boolean mo164P() {
        return false;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo175a(C0393oi oiVar) {
    }

    /* renamed from: b */
    public abstract void mo167b(boolean z);

    /* renamed from: Q */
    public final DialogPreference mo192Q() {
        if (this.f199Z == null) {
            this.f199Z = (DialogPreference) ((acf) mo5651j()).mo157a(this.f9592k.getString("key"));
        }
        return this.f199Z;
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public void mo166b(View view) {
        View findViewById = view.findViewById(16908299);
        if (findViewById != null) {
            CharSequence charSequence = this.f204ae;
            int i = 0;
            if (TextUtils.isEmpty(charSequence)) {
                i = 8;
            } else if (findViewById instanceof TextView) {
                ((TextView) findViewById).setText(charSequence);
            }
            if (findViewById.getVisibility() != i) {
                findViewById.setVisibility(i);
            }
        }
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        this.f202ac = i;
    }

    /* renamed from: a */
    public void mo165a(Bundle bundle) {
        super.mo165a(bundle);
        C0147fh j = mo5651j();
        if (j instanceof acf) {
            acf acf = (acf) j;
            String string = this.f9592k.getString("key");
            if (bundle == null) {
                DialogPreference dialogPreference = (DialogPreference) acf.mo157a(string);
                this.f199Z = dialogPreference;
                this.f200aa = dialogPreference.f1069a;
                this.f201ab = dialogPreference.f1072d;
                this.f203ad = dialogPreference.f1073e;
                this.f204ae = dialogPreference.f1070b;
                this.f205af = dialogPreference.f1074f;
                Drawable drawable = dialogPreference.f1071c;
                if (drawable == null || (drawable instanceof BitmapDrawable)) {
                    this.f206ag = (BitmapDrawable) drawable;
                    return;
                }
                Bitmap createBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(createBitmap);
                drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
                drawable.draw(canvas);
                this.f206ag = new BitmapDrawable(mo5657p(), createBitmap);
                return;
            }
            this.f200aa = bundle.getCharSequence("PreferenceDialogFragment.title");
            this.f201ab = bundle.getCharSequence("PreferenceDialogFragment.positiveText");
            this.f203ad = bundle.getCharSequence("PreferenceDialogFragment.negativeText");
            this.f204ae = bundle.getCharSequence("PreferenceDialogFragment.message");
            this.f205af = bundle.getInt("PreferenceDialogFragment.layout", 0);
            Bitmap bitmap = (Bitmap) bundle.getParcelable("PreferenceDialogFragment.icon");
            if (bitmap != null) {
                this.f206ag = new BitmapDrawable(mo5657p(), bitmap);
                return;
            }
            return;
        }
        throw new IllegalStateException("Target fragment must implement TargetFragment interface");
    }

    /* renamed from: c */
    public final Dialog mo193c(Bundle bundle) {
        C0149fj m = mo5653m();
        this.f202ac = -2;
        C0393oi oiVar = new C0393oi(m);
        oiVar.mo9525b(this.f200aa);
        oiVar.mo9521a((Drawable) this.f206ag);
        oiVar.mo9524a(this.f201ab, (DialogInterface.OnClickListener) this);
        CharSequence charSequence = this.f203ad;
        C0389oe oeVar = oiVar.f15411a;
        oeVar.f15358k = charSequence;
        oeVar.f15360m = this;
        int i = this.f205af;
        View view = null;
        if (i != 0) {
            view = LayoutInflater.from(m).inflate(i, (ViewGroup) null);
        }
        if (view == null) {
            oiVar.mo9523a(this.f204ae);
        } else {
            mo166b(view);
            oiVar.mo9522a(view);
        }
        mo175a(oiVar);
        C0394oj b = oiVar.mo6550b();
        if (mo164P()) {
            b.getWindow().setSoftInputMode(5);
        }
        return b;
    }

    public final void onDismiss(DialogInterface dialogInterface) {
        boolean z;
        super.onDismiss(dialogInterface);
        if (this.f202ac == -1) {
            z = true;
        } else {
            z = false;
        }
        mo167b(z);
    }

    /* renamed from: e */
    public void mo168e(Bundle bundle) {
        super.mo168e(bundle);
        bundle.putCharSequence("PreferenceDialogFragment.title", this.f200aa);
        bundle.putCharSequence("PreferenceDialogFragment.positiveText", this.f201ab);
        bundle.putCharSequence("PreferenceDialogFragment.negativeText", this.f203ad);
        bundle.putCharSequence("PreferenceDialogFragment.message", this.f204ae);
        bundle.putInt("PreferenceDialogFragment.layout", this.f205af);
        BitmapDrawable bitmapDrawable = this.f206ag;
        if (bitmapDrawable != null) {
            bundle.putParcelable("PreferenceDialogFragment.icon", bitmapDrawable.getBitmap());
        }
    }
}
