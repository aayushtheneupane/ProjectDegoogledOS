package androidx.preference;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import com.google.android.apps.photosgo.R;

/* compiled from: PG */
public class DropDownPreference extends ListPreference {

    /* renamed from: D */
    private final Context f1075D;

    /* renamed from: E */
    private final ArrayAdapter f1076E;

    /* renamed from: F */
    private Spinner f1077F;

    /* renamed from: G */
    private final AdapterView.OnItemSelectedListener f1078G = new acg(this);

    public DropDownPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R.attr.dropdownPreferenceStyle);
        this.f1075D = context;
        ArrayAdapter arrayAdapter = new ArrayAdapter(this.f1075D, 17367049);
        this.f1076E = arrayAdapter;
        arrayAdapter.clear();
        CharSequence[] charSequenceArr = this.f1082g;
        if (charSequenceArr != null) {
            for (CharSequence charSequence : charSequenceArr) {
                this.f1076E.add(charSequence.toString());
            }
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public final void mo1157b() {
        super.mo1157b();
        ArrayAdapter arrayAdapter = this.f1076E;
        if (arrayAdapter != null) {
            arrayAdapter.notifyDataSetChanged();
        }
    }

    /* renamed from: a */
    public final void mo169a(ady ady) {
        Spinner spinner = (Spinner) ady.f16382a.findViewById(R.id.spinner);
        this.f1077F = spinner;
        spinner.setAdapter(this.f1076E);
        this.f1077F.setOnItemSelectedListener(this.f1078G);
        Spinner spinner2 = this.f1077F;
        String str = this.f1084i;
        CharSequence[] charSequenceArr = this.f1083h;
        int i = -1;
        if (str != null && charSequenceArr != null) {
            int length = charSequenceArr.length - 1;
            while (true) {
                if (length >= 0) {
                    if (charSequenceArr[length].equals(str)) {
                        i = length;
                        break;
                    }
                    length--;
                } else {
                    break;
                }
            }
        }
        spinner2.setSelection(i);
        super.mo169a(ady);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void mo1156a() {
        this.f1077F.performClick();
    }
}
