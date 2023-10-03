package com.android.incallui;

import android.content.Context;
import android.net.Uri;
import android.support.p000v4.util.ArrayMap;
import android.telephony.PhoneNumberUtils;
import android.text.BidiFormatter;
import android.text.TextDirectionHeuristics;
import android.text.TextUtils;
import android.util.ArraySet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.android.dialer.R;
import com.android.dialer.common.LogUtil;
import com.android.dialer.contactphoto.ContactPhotoManager;
import com.android.dialer.contacts.ContactsComponent;
import com.android.incallui.ContactInfoCache;
import com.android.incallui.call.CallList;
import com.android.incallui.call.DialerCall;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ConferenceParticipantListAdapter extends BaseAdapter {
    private List<ParticipantInfo> conferenceParticipants = new ArrayList();
    private final ContactPhotoManager contactPhotoManager;
    private View.OnClickListener disconnectListener = new View.OnClickListener() {
        public void onClick(View view) {
            DialerCall access$000 = ConferenceParticipantListAdapter.this.getCallFromView(view);
            LogUtil.m9i("ConferenceParticipantListAdapter.mDisconnectListener.onClick", GeneratedOutlineSupport.outline6("call: ", access$000), new Object[0]);
            if (access$000 != null) {
                access$000.disconnect();
            }
        }
    };
    private final ListView listView;
    private boolean parentCanSeparate;
    private final Map<String, ParticipantInfo> participantsByCallId = new ArrayMap();
    private View.OnClickListener separateListener = new View.OnClickListener() {
        public void onClick(View view) {
            DialerCall access$000 = ConferenceParticipantListAdapter.this.getCallFromView(view);
            LogUtil.m9i("ConferenceParticipantListAdapter.mSeparateListener.onClick", GeneratedOutlineSupport.outline6("call: ", access$000), new Object[0]);
            if (access$000 != null) {
                access$000.splitFromConference();
            }
        }
    };

    public static class ContactLookupCallback implements ContactInfoCache.ContactInfoCacheCallback {
        private final WeakReference<ConferenceParticipantListAdapter> listAdapter;

        public ContactLookupCallback(ConferenceParticipantListAdapter conferenceParticipantListAdapter) {
            this.listAdapter = new WeakReference<>(conferenceParticipantListAdapter);
        }

        public void onContactInfoComplete(String str, ContactInfoCache.ContactCacheEntry contactCacheEntry) {
            ConferenceParticipantListAdapter conferenceParticipantListAdapter = (ConferenceParticipantListAdapter) this.listAdapter.get();
            if (conferenceParticipantListAdapter != null) {
                conferenceParticipantListAdapter.updateContactInfo(str, contactCacheEntry);
            }
        }

        public void onImageLoadComplete(String str, ContactInfoCache.ContactCacheEntry contactCacheEntry) {
            ConferenceParticipantListAdapter conferenceParticipantListAdapter = (ConferenceParticipantListAdapter) this.listAdapter.get();
            if (conferenceParticipantListAdapter != null) {
                conferenceParticipantListAdapter.updateContactInfo(str, contactCacheEntry);
            }
        }
    }

    private static class ParticipantInfo {
        private boolean cacheLookupComplete = false;
        private DialerCall call;
        private ContactInfoCache.ContactCacheEntry contactCacheEntry;

        public ParticipantInfo(DialerCall dialerCall, ContactInfoCache.ContactCacheEntry contactCacheEntry2) {
            this.call = dialerCall;
            this.contactCacheEntry = contactCacheEntry2;
        }

        public boolean equals(Object obj) {
            if (obj instanceof ParticipantInfo) {
                return Objects.equals(((ParticipantInfo) obj).call.getId(), this.call.getId());
            }
            return false;
        }

        public DialerCall getCall() {
            return this.call;
        }

        public ContactInfoCache.ContactCacheEntry getContactCacheEntry() {
            return this.contactCacheEntry;
        }

        public int hashCode() {
            return this.call.getId().hashCode();
        }

        public boolean isCacheLookupComplete() {
            return this.cacheLookupComplete;
        }

        public void setCacheLookupComplete(boolean z) {
            this.cacheLookupComplete = z;
        }

        public void setCall(DialerCall dialerCall) {
            this.call = dialerCall;
        }

        public void setContactCacheEntry(ContactInfoCache.ContactCacheEntry contactCacheEntry2) {
            this.contactCacheEntry = contactCacheEntry2;
        }
    }

    public ConferenceParticipantListAdapter(ListView listView2, ContactPhotoManager contactPhotoManager2) {
        this.listView = listView2;
        this.contactPhotoManager = contactPhotoManager2;
    }

    /* access modifiers changed from: private */
    public DialerCall getCallFromView(View view) {
        return CallList.getInstance().getCallById((String) ((View) view.getParent()).getTag());
    }

    /* access modifiers changed from: private */
    public Context getContext() {
        return this.listView.getContext();
    }

    private void refreshView(String str) {
        String str2;
        ContactPhotoManager.DefaultImageRequest defaultImageRequest;
        int i;
        int i2;
        int firstVisiblePosition = this.listView.getFirstVisiblePosition();
        int lastVisiblePosition = this.listView.getLastVisiblePosition();
        for (int i3 = 0; i3 <= lastVisiblePosition - firstVisiblePosition; i3++) {
            View childAt = this.listView.getChildAt(i3);
            if (((String) childAt.getTag()).equals(str)) {
                ParticipantInfo participantInfo = this.conferenceParticipants.get(i3 + firstVisiblePosition);
                DialerCall call = participantInfo.getCall();
                ContactInfoCache.ContactCacheEntry contactCacheEntry = participantInfo.getContactCacheEntry();
                ContactInfoCache instance = ContactInfoCache.getInstance(getContext());
                if (!participantInfo.isCacheLookupComplete()) {
                    instance.findInfo(participantInfo.getCall(), participantInfo.getCall().getState() == 4, new ContactLookupCallback(this));
                }
                boolean z = this.parentCanSeparate && call.can(4096);
                boolean can = call.can(8192);
                String displayName = ContactsComponent.get(getContext()).contactDisplayPreferences().getDisplayName(contactCacheEntry.namePrimary, contactCacheEntry.nameAlternative);
                String str3 = contactCacheEntry.namePrimary;
                String updateNameIfRestricted = call.updateNameIfRestricted(displayName);
                String str4 = contactCacheEntry.number;
                String str5 = contactCacheEntry.lookupKey;
                Uri uri = contactCacheEntry.displayPhotoUri;
                int nonConferenceState = call.getNonConferenceState();
                ImageView imageView = (ImageView) childAt.findViewById(R.id.callerPhoto);
                TextView textView = (TextView) childAt.findViewById(R.id.conferenceCallerStatus);
                TextView textView2 = (TextView) childAt.findViewById(R.id.conferenceCallerName);
                TextView textView3 = (TextView) childAt.findViewById(R.id.conferenceCallerNumber);
                View findViewById = childAt.findViewById(R.id.conferenceCallerDisconnect);
                DialerCall dialerCall = call;
                View findViewById2 = childAt.findViewById(R.id.conferenceCallerSeparate);
                View view = childAt;
                if (nonConferenceState == 8) {
                    str2 = str4;
                    textView.setText(TextUtils.concat(new CharSequence[]{getContext().getText(R.string.notification_on_hold).toString(), " • "}));
                    textView.setVisibility(0);
                    textView2.setEnabled(false);
                    textView3.setEnabled(false);
                    TypedValue typedValue = new TypedValue();
                    getContext().getResources().getValue(R.dimen.alpha_hiden, typedValue, true);
                    imageView.setAlpha(typedValue.getFloat());
                } else {
                    str2 = str4;
                    textView.setVisibility(8);
                    textView2.setEnabled(true);
                    textView3.setEnabled(true);
                    TypedValue typedValue2 = new TypedValue();
                    getContext().getResources().getValue(R.dimen.alpha_enabled, typedValue2, true);
                    imageView.setAlpha(typedValue2.getFloat());
                }
                findViewById.setVisibility(can ? 0 : 8);
                if (can) {
                    findViewById.setOnClickListener(this.disconnectListener);
                } else {
                    findViewById.setOnClickListener((View.OnClickListener) null);
                }
                findViewById2.setVisibility(z ? 0 : 8);
                if (z) {
                    findViewById2.setOnClickListener(this.separateListener);
                } else {
                    findViewById2.setOnClickListener((View.OnClickListener) null);
                }
                if (TextUtils.isEmpty(str3)) {
                    str3 = str2;
                }
                if (uri != null) {
                    defaultImageRequest = null;
                } else {
                    defaultImageRequest = new ContactPhotoManager.DefaultImageRequest(str3, str5, true);
                }
                TextView textView4 = textView2;
                this.contactPhotoManager.loadDirectoryPhoto(imageView, uri, false, true, defaultImageRequest);
                if (TextUtils.isEmpty(updateNameIfRestricted)) {
                    i2 = 8;
                    textView4.setVisibility(8);
                    i = 0;
                } else {
                    i2 = 8;
                    i = 0;
                    textView4.setVisibility(0);
                    textView4.setText(updateNameIfRestricted);
                }
                if (TextUtils.isEmpty(str2)) {
                    textView3.setVisibility(i2);
                } else {
                    textView3.setVisibility(i);
                    textView3.setText(PhoneNumberUtils.createTtsSpannable(BidiFormatter.getInstance().unicodeWrap(str2, TextDirectionHeuristics.LTR)));
                }
                view.setTag(dialerCall.getId());
                return;
            }
        }
    }

    public int getCount() {
        return this.conferenceParticipants.size();
    }

    public Object getItem(int i) {
        return this.conferenceParticipants.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        String str;
        ContactPhotoManager.DefaultImageRequest defaultImageRequest;
        int i2;
        int i3;
        View inflate = view == null ? LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.caller_in_conference, viewGroup, false) : view;
        ParticipantInfo participantInfo = this.conferenceParticipants.get(i);
        DialerCall call = participantInfo.getCall();
        ContactInfoCache.ContactCacheEntry contactCacheEntry = participantInfo.getContactCacheEntry();
        ContactInfoCache instance = ContactInfoCache.getInstance(getContext());
        if (!participantInfo.isCacheLookupComplete()) {
            instance.findInfo(participantInfo.getCall(), participantInfo.getCall().getState() == 4, new ContactLookupCallback(this));
        }
        boolean z = this.parentCanSeparate && call.can(4096);
        boolean can = call.can(8192);
        String displayName = ContactsComponent.get(getContext()).contactDisplayPreferences().getDisplayName(contactCacheEntry.namePrimary, contactCacheEntry.nameAlternative);
        String str2 = contactCacheEntry.namePrimary;
        String updateNameIfRestricted = call.updateNameIfRestricted(displayName);
        String str3 = contactCacheEntry.number;
        String str4 = contactCacheEntry.lookupKey;
        Uri uri = contactCacheEntry.displayPhotoUri;
        int nonConferenceState = call.getNonConferenceState();
        ImageView imageView = (ImageView) inflate.findViewById(R.id.callerPhoto);
        TextView textView = (TextView) inflate.findViewById(R.id.conferenceCallerStatus);
        TextView textView2 = (TextView) inflate.findViewById(R.id.conferenceCallerName);
        TextView textView3 = (TextView) inflate.findViewById(R.id.conferenceCallerNumber);
        View findViewById = inflate.findViewById(R.id.conferenceCallerDisconnect);
        DialerCall dialerCall = call;
        View findViewById2 = inflate.findViewById(R.id.conferenceCallerSeparate);
        View view2 = inflate;
        if (nonConferenceState == 8) {
            str = str3;
            textView.setText(TextUtils.concat(new CharSequence[]{getContext().getText(R.string.notification_on_hold).toString(), " • "}));
            textView.setVisibility(0);
            textView2.setEnabled(false);
            textView3.setEnabled(false);
            TypedValue typedValue = new TypedValue();
            getContext().getResources().getValue(R.dimen.alpha_hiden, typedValue, true);
            imageView.setAlpha(typedValue.getFloat());
        } else {
            str = str3;
            textView.setVisibility(8);
            textView2.setEnabled(true);
            textView3.setEnabled(true);
            TypedValue typedValue2 = new TypedValue();
            getContext().getResources().getValue(R.dimen.alpha_enabled, typedValue2, true);
            imageView.setAlpha(typedValue2.getFloat());
        }
        findViewById.setVisibility(can ? 0 : 8);
        if (can) {
            findViewById.setOnClickListener(this.disconnectListener);
        } else {
            findViewById.setOnClickListener((View.OnClickListener) null);
        }
        findViewById2.setVisibility(z ? 0 : 8);
        if (z) {
            findViewById2.setOnClickListener(this.separateListener);
        } else {
            findViewById2.setOnClickListener((View.OnClickListener) null);
        }
        if (TextUtils.isEmpty(str2)) {
            str2 = str;
        }
        if (uri != null) {
            defaultImageRequest = null;
        } else {
            defaultImageRequest = new ContactPhotoManager.DefaultImageRequest(str2, str4, true);
        }
        TextView textView4 = textView2;
        this.contactPhotoManager.loadDirectoryPhoto(imageView, uri, false, true, defaultImageRequest);
        if (TextUtils.isEmpty(updateNameIfRestricted)) {
            i3 = 8;
            textView4.setVisibility(8);
            i2 = 0;
        } else {
            i3 = 8;
            i2 = 0;
            textView4.setVisibility(0);
            textView4.setText(updateNameIfRestricted);
        }
        if (TextUtils.isEmpty(str)) {
            textView3.setVisibility(i3);
        } else {
            textView3.setVisibility(i2);
            textView3.setText(PhoneNumberUtils.createTtsSpannable(BidiFormatter.getInstance().unicodeWrap(str, TextDirectionHeuristics.LTR)));
        }
        View view3 = view2;
        view3.setTag(dialerCall.getId());
        return view3;
    }

    public void refreshCall(DialerCall dialerCall) {
        String id = dialerCall.getId();
        if (this.participantsByCallId.containsKey(id)) {
            this.participantsByCallId.get(id).setCall(dialerCall);
            refreshView(id);
        }
    }

    /* access modifiers changed from: package-private */
    public void updateContactInfo(String str, ContactInfoCache.ContactCacheEntry contactCacheEntry) {
        if (this.participantsByCallId.containsKey(str)) {
            ParticipantInfo participantInfo = this.participantsByCallId.get(str);
            participantInfo.setContactCacheEntry(contactCacheEntry);
            participantInfo.setCacheLookupComplete(true);
            refreshView(str);
        }
    }

    public void updateParticipants(List<DialerCall> list, boolean z) {
        this.parentCanSeparate = z;
        ContactInfoCache instance = ContactInfoCache.getInstance(getContext());
        ArraySet arraySet = new ArraySet(list.size());
        boolean z2 = false;
        for (DialerCall next : list) {
            String id = next.getId();
            arraySet.add(id);
            ContactInfoCache.ContactCacheEntry info = instance.getInfo(id);
            if (info == null) {
                info = ContactInfoCache.buildCacheEntryFromCall(getContext(), next);
            }
            if (this.participantsByCallId.containsKey(id)) {
                ParticipantInfo participantInfo = this.participantsByCallId.get(id);
                participantInfo.setCall(next);
                participantInfo.setContactCacheEntry(info);
            } else {
                z2 = true;
                ParticipantInfo participantInfo2 = new ParticipantInfo(next, info);
                this.conferenceParticipants.add(participantInfo2);
                this.participantsByCallId.put(next.getId(), participantInfo2);
            }
        }
        Iterator<Map.Entry<String, ParticipantInfo>> it = this.participantsByCallId.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry next2 = it.next();
            if (!arraySet.contains((String) next2.getKey())) {
                this.conferenceParticipants.remove((ParticipantInfo) next2.getValue());
                it.remove();
            }
        }
        if (z2) {
            Collections.sort(this.conferenceParticipants, new Comparator<ParticipantInfo>() {
                public int compare(Object obj, Object obj2) {
                    ParticipantInfo participantInfo = (ParticipantInfo) obj2;
                    ContactInfoCache.ContactCacheEntry contactCacheEntry = ((ParticipantInfo) obj).getContactCacheEntry();
                    String sortName = ContactsComponent.get(ConferenceParticipantListAdapter.this.getContext()).contactDisplayPreferences().getSortName(contactCacheEntry.namePrimary, contactCacheEntry.nameAlternative);
                    if (sortName == null) {
                        sortName = "";
                    }
                    ContactInfoCache.ContactCacheEntry contactCacheEntry2 = participantInfo.getContactCacheEntry();
                    String sortName2 = ContactsComponent.get(ConferenceParticipantListAdapter.this.getContext()).contactDisplayPreferences().getSortName(contactCacheEntry2.namePrimary, contactCacheEntry2.nameAlternative);
                    if (sortName2 == null) {
                        sortName2 = "";
                    }
                    return sortName.compareToIgnoreCase(sortName2);
                }
            });
        }
        notifyDataSetChanged();
    }
}
