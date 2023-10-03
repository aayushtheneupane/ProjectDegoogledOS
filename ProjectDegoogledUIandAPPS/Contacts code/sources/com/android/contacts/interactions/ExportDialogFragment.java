package com.android.contacts.interactions;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;
import com.android.contacts.R;
import com.android.contacts.util.ImplicitIntentsUtil;
import com.android.contacts.vcard.ExportVCardActivity;
import com.android.contacts.vcard.ShareVCardActivity;

public class ExportDialogFragment extends DialogFragment {
    /* access modifiers changed from: private */
    public static int mExportMode = -1;
    private final String[] LOOKUP_PROJECTION = {"lookup"};

    public static void show(FragmentManager fragmentManager, Class cls, int i) {
        ExportDialogFragment exportDialogFragment = new ExportDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString("CALLING_ACTIVITY", cls.getName());
        exportDialogFragment.setArguments(bundle);
        exportDialogFragment.show(fragmentManager, "ExportDialogFragment");
        mExportMode = i;
    }

    public Context getContext() {
        return getActivity();
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    public Dialog onCreateDialog(Bundle bundle) {
        Resources resources = getActivity().getResources();
        final LayoutInflater layoutInflater = (LayoutInflater) getActivity().getSystemService("layout_inflater");
        final String string = getArguments().getString("CALLING_ACTIVITY");
        final C03641 r2 = new ArrayAdapter<AdapterEntry>(getActivity(), R.layout.select_dialog_item) {
            public View getView(int i, View view, ViewGroup viewGroup) {
                if (view == null) {
                    view = layoutInflater.inflate(R.layout.select_dialog_item, viewGroup, false);
                }
                view.findViewById(R.id.secondary_text).setVisibility(8);
                ((TextView) view.findViewById(R.id.primary_text)).setText(((AdapterEntry) getItem(i)).mLabel);
                return view;
            }
        };
        if (resources.getBoolean(R.bool.config_allow_export)) {
            r2.add(new AdapterEntry(getString(R.string.export_to_vcf_file), R.string.export_to_vcf_file));
        }
        if (resources.getBoolean(R.bool.config_allow_share_contacts)) {
            if (mExportMode == 0) {
                r2.add(new AdapterEntry(getString(R.string.share_favorite_contacts), R.string.share_contacts));
            } else {
                r2.add(new AdapterEntry(getString(R.string.share_contacts), R.string.share_contacts));
            }
        }
        C03652 r6 = new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                int i2 = ((AdapterEntry) r2.getItem(i)).mChoiceResourceId;
                if (i2 == R.string.export_to_vcf_file) {
                    Intent intent = new Intent(ExportDialogFragment.this.getActivity(), ExportVCardActivity.class);
                    intent.putExtra("CALLING_ACTIVITY", string);
                    ExportDialogFragment.this.getActivity().startActivity(intent);
                } else if (i2 != R.string.share_contacts) {
                    Log.e("ExportDialogFragment", "Unexpected resource: " + ExportDialogFragment.this.getActivity().getResources().getResourceEntryName(i2));
                } else if (ExportDialogFragment.mExportMode == 0) {
                    ExportDialogFragment.this.doShareFavoriteContacts();
                } else {
                    Intent intent2 = new Intent(ExportDialogFragment.this.getActivity(), ShareVCardActivity.class);
                    intent2.putExtra("CALLING_ACTIVITY", string);
                    ExportDialogFragment.this.getActivity().startActivity(intent2);
                }
                dialogInterface.dismiss();
            }
        };
        TextView textView = (TextView) View.inflate(getActivity(), R.layout.dialog_title, (ViewGroup) null);
        textView.setText(R.string.dialog_export);
        return new AlertDialog.Builder(getActivity()).setCustomTitle(textView).setSingleChoiceItems(r2, -1, r6).create();
    }

    /* access modifiers changed from: private */
    public void doShareFavoriteContacts() {
        Cursor query;
        try {
            query = getActivity().getContentResolver().query(ContactsContract.Contacts.CONTENT_STREQUENT_URI, this.LOOKUP_PROJECTION, (String) null, (String[]) null, "display_name COLLATE NOCASE ASC");
            if (query == null) {
                return;
            }
            if (!query.moveToFirst()) {
                Toast.makeText(getActivity(), R.string.no_contact_to_share, 0).show();
                query.close();
                return;
            }
            StringBuilder sb = new StringBuilder();
            int i = 0;
            do {
                if (i != 0) {
                    sb.append(':');
                }
                sb.append(query.getString(0));
                i++;
            } while (query.moveToNext());
            Uri withAppendedPath = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_MULTI_VCARD_URI, Uri.encode(sb.toString()));
            Intent intent = new Intent("android.intent.action.SEND");
            intent.setType("text/x-vcard");
            intent.putExtra("android.intent.extra.STREAM", withAppendedPath);
            ImplicitIntentsUtil.startActivityOutsideApp(getActivity(), intent);
            query.close();
        } catch (Exception e) {
            Log.e("ExportDialogFragment", "Sharing contacts failed", e);
            getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    Toast.makeText(ExportDialogFragment.this.getContext(), R.string.share_contacts_failure, 0).show();
                }
            });
        } catch (Throwable th) {
            query.close();
            throw th;
        }
    }

    private static class AdapterEntry {
        public final int mChoiceResourceId;
        public final CharSequence mLabel;
        public final int mSubscriptionId;

        public AdapterEntry(CharSequence charSequence, int i, int i2) {
            this.mLabel = charSequence;
            this.mChoiceResourceId = i;
            this.mSubscriptionId = i2;
        }

        public AdapterEntry(String str, int i) {
            this(str, i, -1);
        }
    }
}
