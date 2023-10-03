package p000;

import android.content.res.Resources;
import android.database.DataSetObserver;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.SpinnerAdapter;
import android.widget.ThemedSpinnerAdapter;

/* renamed from: ty */
/* compiled from: PG */
final class C0544ty implements ListAdapter, SpinnerAdapter {

    /* renamed from: a */
    private final SpinnerAdapter f15969a;

    /* renamed from: b */
    private ListAdapter f15970b;

    public C0544ty(SpinnerAdapter spinnerAdapter, Resources.Theme theme) {
        this.f15969a = spinnerAdapter;
        if (spinnerAdapter instanceof ListAdapter) {
            this.f15970b = (ListAdapter) spinnerAdapter;
        }
        if (theme != null) {
            int i = Build.VERSION.SDK_INT;
            if (spinnerAdapter instanceof ThemedSpinnerAdapter) {
                ThemedSpinnerAdapter themedSpinnerAdapter = (ThemedSpinnerAdapter) spinnerAdapter;
                if (themedSpinnerAdapter.getDropDownViewTheme() != theme) {
                    themedSpinnerAdapter.setDropDownViewTheme(theme);
                }
            } else if (spinnerAdapter instanceof C0679yy) {
                C0679yy yyVar = (C0679yy) spinnerAdapter;
                if (yyVar.mo10716a() == null) {
                    yyVar.mo10717b();
                }
            }
        }
    }

    public final int getItemViewType(int i) {
        return 0;
    }

    public final int getViewTypeCount() {
        return 1;
    }

    public final boolean areAllItemsEnabled() {
        ListAdapter listAdapter = this.f15970b;
        if (listAdapter != null) {
            return listAdapter.areAllItemsEnabled();
        }
        return true;
    }

    public final int getCount() {
        SpinnerAdapter spinnerAdapter = this.f15969a;
        if (spinnerAdapter == null) {
            return 0;
        }
        return spinnerAdapter.getCount();
    }

    public final View getDropDownView(int i, View view, ViewGroup viewGroup) {
        SpinnerAdapter spinnerAdapter = this.f15969a;
        if (spinnerAdapter != null) {
            return spinnerAdapter.getDropDownView(i, view, viewGroup);
        }
        return null;
    }

    public final Object getItem(int i) {
        SpinnerAdapter spinnerAdapter = this.f15969a;
        if (spinnerAdapter != null) {
            return spinnerAdapter.getItem(i);
        }
        return null;
    }

    public final long getItemId(int i) {
        SpinnerAdapter spinnerAdapter = this.f15969a;
        if (spinnerAdapter == null) {
            return -1;
        }
        return spinnerAdapter.getItemId(i);
    }

    public final View getView(int i, View view, ViewGroup viewGroup) {
        return getDropDownView(i, view, viewGroup);
    }

    public final boolean hasStableIds() {
        SpinnerAdapter spinnerAdapter = this.f15969a;
        return spinnerAdapter != null && spinnerAdapter.hasStableIds();
    }

    public final boolean isEmpty() {
        return getCount() == 0;
    }

    public final boolean isEnabled(int i) {
        ListAdapter listAdapter = this.f15970b;
        if (listAdapter != null) {
            return listAdapter.isEnabled(i);
        }
        return true;
    }

    public final void registerDataSetObserver(DataSetObserver dataSetObserver) {
        SpinnerAdapter spinnerAdapter = this.f15969a;
        if (spinnerAdapter != null) {
            spinnerAdapter.registerDataSetObserver(dataSetObserver);
        }
    }

    public final void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
        SpinnerAdapter spinnerAdapter = this.f15969a;
        if (spinnerAdapter != null) {
            spinnerAdapter.unregisterDataSetObserver(dataSetObserver);
        }
    }
}
