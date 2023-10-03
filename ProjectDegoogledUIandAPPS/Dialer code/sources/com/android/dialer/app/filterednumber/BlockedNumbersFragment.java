package com.android.dialer.app.filterednumber;

import android.app.ListFragment;
import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.p002v7.app.ActionBar;
import android.support.p002v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;
import com.android.dialer.R;
import com.android.dialer.blocking.BlockedNumbersMigrator;
import com.android.dialer.blocking.FilteredNumberCompat;
import com.android.dialer.blocking.FilteredNumbersUtil;
import com.android.dialer.database.FilteredNumberContract;
import com.android.dialer.lettertile.LetterTileDrawable;
import com.android.dialer.theme.base.ThemeComponent;
import com.android.dialer.theme.base.impl.AospThemeImpl;
import com.android.dialer.voicemailstatus.VisualVoicemailEnabledChecker;

public class BlockedNumbersFragment extends ListFragment implements LoaderManager.LoaderCallbacks<Cursor>, View.OnClickListener, VisualVoicemailEnabledChecker.Callback {
    private BlockedNumbersAdapter adapter;
    private View blockedNumberListDivider;
    private View blockedNumbersDisabledForEmergency;
    private BlockedNumbersMigrator blockedNumbersMigratorForTest;
    private TextView blockedNumbersText;
    private TextView footerText;
    /* access modifiers changed from: private */
    public View importSettings;
    protected View migratePromoView;
    private VisualVoicemailEnabledChecker voicemailEnabledChecker;

    private void updateActiveVoicemailProvider() {
        if (getActivity() != null && !getActivity().isFinishing()) {
            if (this.voicemailEnabledChecker.isVisualVoicemailEnabled()) {
                this.footerText.setText(R.string.block_number_footer_message_vvm);
            } else {
                this.footerText.setText(R.string.block_number_footer_message_no_vvm);
            }
        }
    }

    public Context getContext() {
        return getActivity();
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        LayoutInflater layoutInflater = (LayoutInflater) getActivity().getSystemService("layout_inflater");
        getListView().addHeaderView(layoutInflater.inflate(R.layout.blocked_number_header, (ViewGroup) null));
        getListView().addFooterView(layoutInflater.inflate(R.layout.blocked_number_footer, (ViewGroup) null));
        LetterTileDrawable letterTileDrawable = new LetterTileDrawable(getResources());
        letterTileDrawable.setLetter('+');
        letterTileDrawable.setColor(((AospThemeImpl) ThemeComponent.get(getActivity()).theme()).getColorIcon());
        letterTileDrawable.setIsCircular(true);
        if (this.adapter == null) {
            this.adapter = BlockedNumbersAdapter.newBlockedNumbersAdapter(getActivity(), getActivity().getFragmentManager());
        }
        setListAdapter(this.adapter);
        this.blockedNumbersText = (TextView) getListView().findViewById(R.id.blocked_number_text_view);
        this.migratePromoView = getListView().findViewById(R.id.migrate_promo);
        getListView().findViewById(R.id.migrate_promo_allow_button).setOnClickListener(this);
        this.importSettings = getListView().findViewById(R.id.import_settings);
        this.blockedNumbersDisabledForEmergency = getListView().findViewById(R.id.blocked_numbers_disabled_for_emergency);
        this.blockedNumberListDivider = getActivity().findViewById(R.id.blocked_number_list_divider);
        getListView().findViewById(R.id.import_button).setOnClickListener(this);
        getListView().findViewById(R.id.view_numbers_button).setOnClickListener(this);
        this.footerText = (TextView) getActivity().findViewById(R.id.blocked_number_footer_textview);
        this.voicemailEnabledChecker = new VisualVoicemailEnabledChecker(getActivity(), this);
        this.voicemailEnabledChecker.asyncUpdate();
        updateActiveVoicemailProvider();
    }

    public void onClick(View view) {
        final BlockedNumbersSettingsActivity blockedNumbersSettingsActivity = (BlockedNumbersSettingsActivity) getActivity();
        if (blockedNumbersSettingsActivity != null) {
            int id = view.getId();
            if (id == R.id.view_numbers_button) {
                blockedNumbersSettingsActivity.showNumbersToImportPreviewUi();
            } else if (id == R.id.import_button) {
                FilteredNumbersUtil.importSendToVoicemailContacts(blockedNumbersSettingsActivity, new FilteredNumbersUtil.ImportSendToVoicemailContactsListener() {
                    public void onImportComplete() {
                        BlockedNumbersFragment.this.importSettings.setVisibility(8);
                    }
                });
            } else if (id == R.id.migrate_promo_allow_button) {
                view.setEnabled(false);
                BlockedNumbersMigrator blockedNumbersMigrator = this.blockedNumbersMigratorForTest;
                if (blockedNumbersMigrator == null) {
                    blockedNumbersMigrator = new BlockedNumbersMigrator(getActivity());
                }
                blockedNumbersMigrator.migrate(new BlockedNumbersMigrator.Listener() {
                    public void onComplete() {
                        BlockedNumbersFragment.this.getActivity().startActivity(FilteredNumberCompat.createManageBlockedNumbersIntent(BlockedNumbersFragment.this.getActivity()));
                        blockedNumbersSettingsActivity.finish();
                    }
                });
            }
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getLoaderManager().initLoader(0, (Bundle) null, this);
    }

    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(getActivity(), FilteredNumberContract.FilteredNumber.CONTENT_URI, new String[]{"_id", "country_iso", "number", "normalized_number"}, "type=1", (String[]) null, (String) null);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.blocked_number_fragment, viewGroup, false);
    }

    public void onDestroy() {
        setListAdapter((ListAdapter) null);
        super.onDestroy();
    }

    public void onLoadFinished(Loader loader, Object obj) {
        this.adapter.swapCursor((Cursor) obj);
        this.blockedNumberListDivider.setVisibility(4);
    }

    public void onLoaderReset(Loader<Cursor> loader) {
        this.adapter.swapCursor((Cursor) null);
    }

    public void onResume() {
        super.onResume();
        ActionBar supportActionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        supportActionBar.setBackgroundDrawable(new ColorDrawable(((AospThemeImpl) ThemeComponent.get(getActivity()).theme()).getColorPrimary()));
        supportActionBar.setDisplayShowCustomEnabled(false);
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        supportActionBar.setDisplayShowHomeEnabled(true);
        supportActionBar.setDisplayShowTitleEnabled(true);
        supportActionBar.setTitle((int) R.string.manage_blocked_numbers_label);
        this.migratePromoView.setVisibility(0);
        this.blockedNumbersText.setVisibility(8);
        this.blockedNumberListDivider.setVisibility(8);
        this.importSettings.setVisibility(8);
        getListView().findViewById(R.id.import_button).setOnClickListener((View.OnClickListener) null);
        getListView().findViewById(R.id.view_numbers_button).setOnClickListener((View.OnClickListener) null);
        this.blockedNumbersDisabledForEmergency.setVisibility(8);
        this.footerText.setVisibility(8);
        this.blockedNumbersDisabledForEmergency.setVisibility(8);
        this.voicemailEnabledChecker.asyncUpdate();
    }

    public void onVisualVoicemailEnabledStatusChanged(boolean z) {
        updateActiveVoicemailProvider();
    }
}
