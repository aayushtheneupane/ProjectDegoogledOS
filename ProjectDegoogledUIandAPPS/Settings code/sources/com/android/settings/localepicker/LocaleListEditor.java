package com.android.settings.localepicker;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.LocaleList;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import com.android.internal.app.LocalePicker;
import com.android.internal.app.LocaleStore;
import com.android.settings.RestrictedSettingsFragment;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;
import java.util.List;

public class LocaleListEditor extends RestrictedSettingsFragment {
    /* access modifiers changed from: private */
    public LocaleDragAndDropAdapter mAdapter;
    private View mAddLanguage;
    private boolean mIsUiRestricted;
    private Menu mMenu;
    /* access modifiers changed from: private */
    public boolean mRemoveMode;
    /* access modifiers changed from: private */
    public boolean mShowingRemoveDialog;

    public int getMetricsCategory() {
        return 344;
    }

    public LocaleListEditor() {
        super("no_config_locale");
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setHasOptionsMenu(true);
        LocaleStore.fillCache(getContext());
        this.mAdapter = new LocaleDragAndDropAdapter(getContext(), getUserLocaleList());
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        configureDragAndDrop(layoutInflater.inflate(C1715R.layout.locale_order_list, (ViewGroup) onCreateView));
        return onCreateView;
    }

    public void onResume() {
        super.onResume();
        boolean z = this.mIsUiRestricted;
        this.mIsUiRestricted = isUiRestricted();
        TextView emptyTextView = getEmptyTextView();
        if (this.mIsUiRestricted && !z) {
            emptyTextView.setText(C1715R.string.language_empty_list_user_restricted);
            emptyTextView.setVisibility(0);
            updateVisibilityOfRemoveMenu();
        } else if (!this.mIsUiRestricted && z) {
            emptyTextView.setVisibility(8);
            updateVisibilityOfRemoveMenu();
        }
    }

    public void onViewStateRestored(Bundle bundle) {
        super.onViewStateRestored(bundle);
        if (bundle != null) {
            this.mRemoveMode = bundle.getBoolean("localeRemoveMode", false);
            this.mShowingRemoveDialog = bundle.getBoolean("showingLocaleRemoveDialog", false);
        }
        setRemoveMode(this.mRemoveMode);
        this.mAdapter.restoreState(bundle);
        if (this.mShowingRemoveDialog) {
            showRemoveLocaleWarningDialog();
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("localeRemoveMode", this.mRemoveMode);
        bundle.putBoolean("showingLocaleRemoveDialog", this.mShowingRemoveDialog);
        this.mAdapter.saveState(bundle);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == 2) {
            if (this.mRemoveMode) {
                showRemoveLocaleWarningDialog();
            } else {
                setRemoveMode(true);
            }
            return true;
        } else if (itemId != 16908332 || !this.mRemoveMode) {
            return super.onOptionsItemSelected(menuItem);
        } else {
            setRemoveMode(false);
            return true;
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 0 && i2 == -1 && intent != null) {
            this.mAdapter.addLocale(intent.getSerializableExtra("localeInfo"));
            updateVisibilityOfRemoveMenu();
        }
        super.onActivityResult(i, i2, intent);
    }

    /* access modifiers changed from: private */
    public void setRemoveMode(boolean z) {
        this.mRemoveMode = z;
        this.mAdapter.setRemoveMode(z);
        this.mAddLanguage.setVisibility(z ? 4 : 0);
        updateVisibilityOfRemoveMenu();
    }

    private void showRemoveLocaleWarningDialog() {
        int checkedCount = this.mAdapter.getCheckedCount();
        if (checkedCount == 0) {
            setRemoveMode(!this.mRemoveMode);
        } else if (checkedCount == this.mAdapter.getItemCount()) {
            this.mShowingRemoveDialog = true;
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle((int) C1715R.string.dlg_remove_locales_error_title);
            builder.setMessage((int) C1715R.string.dlg_remove_locales_error_message);
            builder.setPositiveButton(17039379, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            });
            builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                public void onDismiss(DialogInterface dialogInterface) {
                    boolean unused = LocaleListEditor.this.mShowingRemoveDialog = false;
                }
            });
            builder.create().show();
        } else {
            String quantityString = getResources().getQuantityString(C1715R.plurals.dlg_remove_locales_title, checkedCount);
            this.mShowingRemoveDialog = true;
            AlertDialog.Builder builder2 = new AlertDialog.Builder(getActivity());
            builder2.setTitle((CharSequence) quantityString);
            builder2.setMessage((int) C1715R.string.dlg_remove_locales_message);
            builder2.setNegativeButton(17039369, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    LocaleListEditor.this.setRemoveMode(false);
                }
            });
            builder2.setPositiveButton(17039379, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    boolean unused = LocaleListEditor.this.mRemoveMode = false;
                    boolean unused2 = LocaleListEditor.this.mShowingRemoveDialog = false;
                    LocaleListEditor.this.mAdapter.removeChecked();
                    LocaleListEditor.this.setRemoveMode(false);
                }
            });
            builder2.setOnDismissListener(new DialogInterface.OnDismissListener() {
                public void onDismiss(DialogInterface dialogInterface) {
                    boolean unused = LocaleListEditor.this.mShowingRemoveDialog = false;
                }
            });
            builder2.create().show();
        }
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        MenuItem add = menu.add(0, 2, 0, C1715R.string.locale_remove_menu);
        add.setShowAsAction(4);
        add.setIcon(C1715R.C1717drawable.ic_delete);
        super.onCreateOptionsMenu(menu, menuInflater);
        this.mMenu = menu;
        updateVisibilityOfRemoveMenu();
    }

    private List<LocaleStore.LocaleInfo> getUserLocaleList() {
        ArrayList arrayList = new ArrayList();
        LocaleList locales = LocalePicker.getLocales();
        for (int i = 0; i < locales.size(); i++) {
            arrayList.add(LocaleStore.getLocaleInfo(locales.get(i)));
        }
        return arrayList;
    }

    private void configureDragAndDrop(View view) {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(C1715R.C1718id.dragList);
        LocaleLinearLayoutManager localeLinearLayoutManager = new LocaleLinearLayoutManager(getContext(), this.mAdapter);
        localeLinearLayoutManager.setAutoMeasureEnabled(true);
        recyclerView.setLayoutManager(localeLinearLayoutManager);
        recyclerView.setHasFixedSize(true);
        this.mAdapter.setRecyclerView(recyclerView);
        recyclerView.setAdapter(this.mAdapter);
        this.mAddLanguage = view.findViewById(C1715R.C1718id.add_language);
        this.mAddLanguage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                LocaleListEditor.this.startActivityForResult(new Intent(LocaleListEditor.this.getActivity(), LocalePickerWithRegionActivity.class), 0);
            }
        });
    }

    private void updateVisibilityOfRemoveMenu() {
        Menu menu = this.mMenu;
        if (menu != null) {
            int i = 2;
            MenuItem findItem = menu.findItem(2);
            if (findItem != null) {
                if (!this.mRemoveMode) {
                    i = 0;
                }
                findItem.setShowAsAction(i);
                boolean z = true;
                if (!(this.mAdapter.getItemCount() > 1) || this.mIsUiRestricted) {
                    z = false;
                }
                findItem.setVisible(z);
            }
        }
    }
}
