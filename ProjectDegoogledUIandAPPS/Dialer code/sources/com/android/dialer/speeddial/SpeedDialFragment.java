package com.android.dialer.speeddial;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.p000v4.app.Fragment;
import android.support.p000v4.app.FragmentActivity;
import android.support.p000v4.app.FragmentManager;
import android.support.p002v7.widget.RecyclerView;
import android.support.p002v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.contacts.common.list.ContactEntry;
import com.android.dialer.R;
import com.android.dialer.callintent.CallInitiationType$Type;
import com.android.dialer.callintent.CallIntentBuilder;
import com.android.dialer.common.Assert;
import com.android.dialer.common.FragmentUtils;
import com.android.dialer.common.LogUtil;
import com.android.dialer.common.concurrent.DefaultFutureCallback;
import com.android.dialer.common.concurrent.DialerExecutor;
import com.android.dialer.common.concurrent.DialerExecutorComponent;
import com.android.dialer.common.concurrent.DialerExecutorModule;
import com.android.dialer.common.concurrent.SupportUiListener;
import com.android.dialer.historyitemactions.DividerModule;
import com.android.dialer.historyitemactions.HistoryItemActionBottomSheet;
import com.android.dialer.historyitemactions.HistoryItemActionModule;
import com.android.dialer.historyitemactions.HistoryItemBottomSheetHeaderInfo;
import com.android.dialer.historyitemactions.IntentModule;
import com.android.dialer.logging.DialerImpression$Type;
import com.android.dialer.logging.Logger;
import com.android.dialer.logging.LoggingBindingsStub;
import com.android.dialer.precall.PreCall;
import com.android.dialer.shortcuts.ShortcutRefresher;
import com.android.dialer.speeddial.ContextMenu;
import com.android.dialer.speeddial.FavoritesViewHolder;
import com.android.dialer.speeddial.HeaderViewHolder;
import com.android.dialer.speeddial.SuggestionViewHolder;
import com.android.dialer.speeddial.database.SpeedDialEntry;
import com.android.dialer.speeddial.draghelper.SpeedDialItemTouchHelperCallback;
import com.android.dialer.speeddial.draghelper.SpeedDialLayoutManager;
import com.android.dialer.speeddial.loader.SpeedDialUiItem;
import com.android.dialer.speeddial.loader.UiItemLoaderComponent;
import com.android.dialer.util.CallUtil;
import com.android.dialer.util.PermissionsUtil;
import com.android.dialer.widget.EmptyContentView;
import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.Callable;

public class SpeedDialFragment extends Fragment {
    private SpeedDialAdapter adapter;
    private EmptyContentView emptyContentView;
    private SpeedDialFavoritesListener favoritesListener;
    private final HeaderViewHolder.SpeedDialHeaderListener headerListener = new SpeedDialFragmentHeaderListener((C05701) null);
    private final BroadcastReceiver readContactsPermissionGrantedReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            SpeedDialFragment.this.loadContacts();
        }
    };
    /* access modifiers changed from: private */
    public SupportUiListener<ImmutableList<SpeedDialUiItem>> speedDialLoaderListener;
    private final ContentObserver strequentsContentObserver = new ContentObserver(DialerExecutorModule.getUiThreadHandler()) {
        public void onChange(boolean z) {
            super.onChange(z);
            SpeedDialFragment.this.loadContacts();
        }
    };
    private final SpeedDialSuggestedListener suggestedListener = new SpeedDialSuggestedListener((C05701) null);
    private boolean updateSpeedDialItemsOnResume = true;

    public interface HostInterface {
        void dragFavorite(boolean z);

        void setHasFrequents(boolean z);
    }

    private static final class SpeedDialContactPermissionEmptyViewListener implements EmptyContentView.OnEmptyViewActionButtonClickedListener {
        private final Context context;
        private final Fragment fragment;

        /* synthetic */ SpeedDialContactPermissionEmptyViewListener(Context context2, Fragment fragment2, C05701 r3) {
            this.context = context2;
            this.fragment = fragment2;
        }

        public void onEmptyViewActionButtonClicked() {
            String[] permissionsCurrentlyDenied = PermissionsUtil.getPermissionsCurrentlyDenied(this.context, PermissionsUtil.allContactsGroupPermissionsUsedInDialer);
            Assert.checkArgument(permissionsCurrentlyDenied.length > 0);
            LogUtil.m9i("OldSpeedDialFragment.onEmptyViewActionButtonClicked", "Requesting permissions: " + Arrays.toString(permissionsCurrentlyDenied), new Object[0]);
            this.fragment.requestPermissions(permissionsCurrentlyDenied, 1);
        }
    }

    static final class SpeedDialFavoritesListener implements FavoritesViewHolder.FavoriteContactsListener {
        /* access modifiers changed from: private */
        public final FragmentActivity activity;
        private final FragmentManager childFragmentManager;
        private ContextMenu contextMenu;
        private final SpeedDialLayoutManager layoutManager;
        private final SpeedDialContextMenuItemListener speedDialContextMenuItemListener = new SpeedDialContextMenuItemListener();
        /* access modifiers changed from: private */
        public final SupportUiListener<ImmutableList<SpeedDialUiItem>> speedDialLoaderListener;
        /* access modifiers changed from: private */
        public final UpdateSpeedDialAdapterListener updateAdapterListener;

        class SpeedDialContextMenuItemListener implements ContextMenu.ContextMenuItemListener {
            SpeedDialContextMenuItemListener() {
            }

            public void openContactInfo(SpeedDialUiItem speedDialUiItem) {
                ((LoggingBindingsStub) Logger.get(SpeedDialFavoritesListener.this.activity)).logImpression(DialerImpression$Type.FAVORITE_OPEN_CONTACT_CARD);
                SpeedDialFavoritesListener.this.activity.startActivity(new Intent("android.intent.action.VIEW", Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_URI, String.valueOf(speedDialUiItem.contactId()))));
            }

            public void openSmsConversation(String str) {
                ((LoggingBindingsStub) Logger.get(SpeedDialFavoritesListener.this.activity)).logImpression(DialerImpression$Type.FAVORITE_SEND_MESSAGE);
                SpeedDialFavoritesListener.this.activity.startActivity(CallUtil.getSendSmsIntent(str));
            }

            public void placeCall(SpeedDialEntry.Channel channel) {
                if (channel.technology() == 3) {
                    ((LoggingBindingsStub) Logger.get(SpeedDialFavoritesListener.this.activity)).logImpression(DialerImpression$Type.LIGHTBRINGER_VIDEO_REQUESTED_FOR_FAVORITE_CONTACT);
                }
                FragmentActivity access$400 = SpeedDialFavoritesListener.this.activity;
                boolean z = true;
                CallIntentBuilder isVideoCall = new CallIntentBuilder(channel.number(), CallInitiationType$Type.SPEED_DIAL).setAllowAssistedDial(true).setIsVideoCall(channel.isVideoTechnology());
                if (channel.technology() != 3) {
                    z = false;
                }
                PreCall.start(access$400, isVideoCall.setIsDuoCall(z));
            }

            public void removeFavoriteContact(SpeedDialUiItem speedDialUiItem) {
                ((LoggingBindingsStub) Logger.get(SpeedDialFavoritesListener.this.activity)).logImpression(DialerImpression$Type.FAVORITE_REMOVE_FAVORITE);
                SupportUiListener access$600 = SpeedDialFavoritesListener.this.speedDialLoaderListener;
                FragmentActivity access$400 = SpeedDialFavoritesListener.this.activity;
                ListenableFuture<ImmutableList<SpeedDialUiItem>> removeSpeedDialUiItem = UiItemLoaderComponent.get(SpeedDialFavoritesListener.this.activity).speedDialUiItemMutator().removeSpeedDialUiItem(speedDialUiItem);
                UpdateSpeedDialAdapterListener access$500 = SpeedDialFavoritesListener.this.updateAdapterListener;
                Objects.requireNonNull(access$500);
                access$600.listen(access$400, removeSpeedDialUiItem, new DialerExecutor.SuccessListener() {
                    public final void onSuccess(Object obj) {
                        SpeedDialFragment.this.onSpeedDialUiItemListLoaded((ImmutableList) obj);
                    }
                }, C0566x8ed429f9.INSTANCE);
            }
        }

        SpeedDialFavoritesListener(FragmentActivity fragmentActivity, FragmentManager fragmentManager, SpeedDialLayoutManager speedDialLayoutManager, UpdateSpeedDialAdapterListener updateSpeedDialAdapterListener, SupportUiListener<ImmutableList<SpeedDialUiItem>> supportUiListener) {
            this.activity = fragmentActivity;
            this.childFragmentManager = fragmentManager;
            this.layoutManager = speedDialLayoutManager;
            this.updateAdapterListener = updateSpeedDialAdapterListener;
            this.speedDialLoaderListener = supportUiListener;
        }

        /* access modifiers changed from: package-private */
        public void hideMenu() {
            ContextMenu contextMenu2 = this.contextMenu;
            if (contextMenu2 != null) {
                contextMenu2.hide();
                this.contextMenu = null;
            }
        }

        public void onAmbiguousContactClicked(SpeedDialUiItem speedDialUiItem) {
            if (speedDialUiItem.channels().size() == 1) {
                onClick(speedDialUiItem.channels().get(0));
                return;
            }
            ((LoggingBindingsStub) Logger.get(this.activity)).logImpression(DialerImpression$Type.FAVORITE_OPEN_DISAMBIG_DIALOG);
            DisambigDialog.show(speedDialUiItem, this.childFragmentManager);
        }

        public void onClick(SpeedDialEntry.Channel channel) {
            if (channel.technology() == 3) {
                ((LoggingBindingsStub) Logger.get(this.activity)).logImpression(DialerImpression$Type.LIGHTBRINGER_VIDEO_REQUESTED_FOR_FAVORITE_CONTACT);
            }
            FragmentActivity fragmentActivity = this.activity;
            boolean z = true;
            CallIntentBuilder isVideoCall = new CallIntentBuilder(channel.number(), CallInitiationType$Type.SPEED_DIAL).setAllowAssistedDial(true).setIsVideoCall(channel.isVideoTechnology());
            if (channel.technology() != 3) {
                z = false;
            }
            PreCall.start(fragmentActivity, isVideoCall.setIsDuoCall(z));
        }

        public void onRequestRemove(SpeedDialUiItem speedDialUiItem) {
            ((LoggingBindingsStub) Logger.get(this.activity)).logImpression(DialerImpression$Type.FAVORITE_REMOVE_FAVORITE_BY_DRAG_AND_DROP);
            SpeedDialContextMenuItemListener speedDialContextMenuItemListener2 = this.speedDialContextMenuItemListener;
            ((LoggingBindingsStub) Logger.get(SpeedDialFavoritesListener.this.activity)).logImpression(DialerImpression$Type.FAVORITE_REMOVE_FAVORITE);
            SupportUiListener access$600 = SpeedDialFavoritesListener.this.speedDialLoaderListener;
            FragmentActivity access$400 = SpeedDialFavoritesListener.this.activity;
            ListenableFuture<ImmutableList<SpeedDialUiItem>> removeSpeedDialUiItem = UiItemLoaderComponent.get(SpeedDialFavoritesListener.this.activity).speedDialUiItemMutator().removeSpeedDialUiItem(speedDialUiItem);
            UpdateSpeedDialAdapterListener access$500 = SpeedDialFavoritesListener.this.updateAdapterListener;
            Objects.requireNonNull(access$500);
            access$600.listen(access$400, removeSpeedDialUiItem, new DialerExecutor.SuccessListener() {
                public final void onSuccess(Object obj) {
                    SpeedDialFragment.this.onSpeedDialUiItemListLoaded((ImmutableList) obj);
                }
            }, C0566x8ed429f9.INSTANCE);
        }

        public void onTouchFinished(boolean z) {
            this.layoutManager.setScrollEnabled(true);
            if (z) {
                this.contextMenu.hide();
                this.contextMenu = null;
            }
        }

        public void showContextMenu(View view, SpeedDialUiItem speedDialUiItem) {
            ((LoggingBindingsStub) Logger.get(this.activity)).logImpression(DialerImpression$Type.FAVORITE_OPEN_FAVORITE_MENU);
            this.layoutManager.setScrollEnabled(false);
            this.contextMenu = ContextMenu.show(this.activity, view, this.speedDialContextMenuItemListener, speedDialUiItem);
        }
    }

    private class SpeedDialFragmentHeaderListener implements HeaderViewHolder.SpeedDialHeaderListener {
        /* synthetic */ SpeedDialFragmentHeaderListener(C05701 r2) {
        }

        public void onAddFavoriteClicked() {
            SpeedDialFragment.this.startActivityForResult(new Intent("android.intent.action.PICK", ContactsContract.CommonDataKinds.Phone.CONTENT_URI), 5);
        }
    }

    private static final class SpeedDialNoContactsEmptyViewListener implements EmptyContentView.OnEmptyViewActionButtonClickedListener {
        private final Fragment fragment;

        SpeedDialNoContactsEmptyViewListener(Fragment fragment2) {
            this.fragment = fragment2;
        }

        public void onEmptyViewActionButtonClicked() {
            this.fragment.startActivityForResult(new Intent("android.intent.action.PICK", ContactsContract.CommonDataKinds.Phone.CONTENT_URI), 5);
        }
    }

    private final class SpeedDialSuggestedListener implements SuggestionViewHolder.SuggestedContactsListener {
        private HistoryItemActionBottomSheet bottomSheet;

        private final class ContactInfoModule extends IntentModule {
            public ContactInfoModule(SpeedDialSuggestedListener speedDialSuggestedListener, Context context, Intent intent, int i, int i2) {
                super(context, intent, i, i2);
            }

            public boolean tintDrawable() {
                return false;
            }
        }

        private final class StarContactModule implements HistoryItemActionModule {
            private final SpeedDialUiItem speedDialUiItem;

            StarContactModule(SpeedDialUiItem speedDialUiItem2) {
                this.speedDialUiItem = speedDialUiItem2;
            }

            public int getDrawableId() {
                return R.drawable.quantum_ic_star_vd_theme_24;
            }

            public int getStringId() {
                return R.string.suggested_contact_bottom_sheet_add_favorite_option;
            }

            public boolean onClick() {
                SpeedDialFragment.this.speedDialLoaderListener.listen(SpeedDialFragment.this.getContext(), UiItemLoaderComponent.get(SpeedDialFragment.this.getContext()).speedDialUiItemMutator().starContact(Uri.withAppendedPath(ContactsContract.CommonDataKinds.Phone.CONTENT_FILTER_URI, this.speedDialUiItem.defaultChannel().number())), 
                /*  JADX ERROR: Method code generation error
                    jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x003f: INVOKE  
                      (wrap: com.android.dialer.common.concurrent.SupportUiListener : 0x0004: INVOKE  (r0v2 com.android.dialer.common.concurrent.SupportUiListener) = 
                      (wrap: com.android.dialer.speeddial.SpeedDialFragment : 0x0002: IGET  (r0v1 com.android.dialer.speeddial.SpeedDialFragment) = 
                      (wrap: com.android.dialer.speeddial.SpeedDialFragment$SpeedDialSuggestedListener : 0x0000: IGET  (r0v0 com.android.dialer.speeddial.SpeedDialFragment$SpeedDialSuggestedListener) = 
                      (r5v0 'this' com.android.dialer.speeddial.SpeedDialFragment$SpeedDialSuggestedListener$StarContactModule A[THIS])
                     com.android.dialer.speeddial.SpeedDialFragment.SpeedDialSuggestedListener.StarContactModule.this$1 com.android.dialer.speeddial.SpeedDialFragment$SpeedDialSuggestedListener)
                     com.android.dialer.speeddial.SpeedDialFragment.SpeedDialSuggestedListener.this$0 com.android.dialer.speeddial.SpeedDialFragment)
                     com.android.dialer.speeddial.SpeedDialFragment.access$700(com.android.dialer.speeddial.SpeedDialFragment):com.android.dialer.common.concurrent.SupportUiListener type: STATIC)
                      (wrap: android.content.Context : 0x000c: INVOKE  (r1v2 android.content.Context) = 
                      (wrap: com.android.dialer.speeddial.SpeedDialFragment : 0x000a: IGET  (r1v1 com.android.dialer.speeddial.SpeedDialFragment) = 
                      (wrap: com.android.dialer.speeddial.SpeedDialFragment$SpeedDialSuggestedListener : 0x0008: IGET  (r1v0 com.android.dialer.speeddial.SpeedDialFragment$SpeedDialSuggestedListener) = 
                      (r5v0 'this' com.android.dialer.speeddial.SpeedDialFragment$SpeedDialSuggestedListener$StarContactModule A[THIS])
                     com.android.dialer.speeddial.SpeedDialFragment.SpeedDialSuggestedListener.StarContactModule.this$1 com.android.dialer.speeddial.SpeedDialFragment$SpeedDialSuggestedListener)
                     com.android.dialer.speeddial.SpeedDialFragment.SpeedDialSuggestedListener.this$0 com.android.dialer.speeddial.SpeedDialFragment)
                     android.support.v4.app.Fragment.getContext():android.content.Context type: VIRTUAL)
                      (wrap: com.google.common.util.concurrent.ListenableFuture<com.google.common.collect.ImmutableList<com.android.dialer.speeddial.loader.SpeedDialUiItem>> : 0x0030: INVOKE  (r2v5 com.google.common.util.concurrent.ListenableFuture<com.google.common.collect.ImmutableList<com.android.dialer.speeddial.loader.SpeedDialUiItem>>) = 
                      (wrap: com.android.dialer.speeddial.loader.SpeedDialUiItemMutator : 0x001c: INVOKE  (r2v4 com.android.dialer.speeddial.loader.SpeedDialUiItemMutator) = 
                      (wrap: com.android.dialer.speeddial.loader.UiItemLoaderComponent : 0x0018: INVOKE  (r2v3 com.android.dialer.speeddial.loader.UiItemLoaderComponent) = 
                      (wrap: android.content.Context : 0x0014: INVOKE  (r2v2 android.content.Context) = 
                      (wrap: com.android.dialer.speeddial.SpeedDialFragment : 0x0012: IGET  (r2v1 com.android.dialer.speeddial.SpeedDialFragment) = 
                      (wrap: com.android.dialer.speeddial.SpeedDialFragment$SpeedDialSuggestedListener : 0x0010: IGET  (r2v0 com.android.dialer.speeddial.SpeedDialFragment$SpeedDialSuggestedListener) = 
                      (r5v0 'this' com.android.dialer.speeddial.SpeedDialFragment$SpeedDialSuggestedListener$StarContactModule A[THIS])
                     com.android.dialer.speeddial.SpeedDialFragment.SpeedDialSuggestedListener.StarContactModule.this$1 com.android.dialer.speeddial.SpeedDialFragment$SpeedDialSuggestedListener)
                     com.android.dialer.speeddial.SpeedDialFragment.SpeedDialSuggestedListener.this$0 com.android.dialer.speeddial.SpeedDialFragment)
                     android.support.v4.app.Fragment.getContext():android.content.Context type: VIRTUAL)
                     com.android.dialer.speeddial.loader.UiItemLoaderComponent.get(android.content.Context):com.android.dialer.speeddial.loader.UiItemLoaderComponent type: STATIC)
                     com.android.dialer.speeddial.loader.UiItemLoaderComponent.speedDialUiItemMutator():com.android.dialer.speeddial.loader.SpeedDialUiItemMutator type: VIRTUAL)
                      (wrap: android.net.Uri : 0x002c: INVOKE  (r3v1 android.net.Uri) = 
                      (wrap: android.net.Uri : 0x0020: SGET  (r3v0 android.net.Uri) =  android.provider.ContactsContract.CommonDataKinds.Phone.CONTENT_FILTER_URI android.net.Uri)
                      (wrap: java.lang.String : 0x0028: INVOKE  (r4v2 java.lang.String) = 
                      (wrap: com.android.dialer.speeddial.database.SpeedDialEntry$Channel : 0x0024: INVOKE  (r4v1 com.android.dialer.speeddial.database.SpeedDialEntry$Channel) = 
                      (wrap: com.android.dialer.speeddial.loader.SpeedDialUiItem : 0x0022: IGET  (r4v0 com.android.dialer.speeddial.loader.SpeedDialUiItem) = 
                      (r5v0 'this' com.android.dialer.speeddial.SpeedDialFragment$SpeedDialSuggestedListener$StarContactModule A[THIS])
                     com.android.dialer.speeddial.SpeedDialFragment.SpeedDialSuggestedListener.StarContactModule.speedDialUiItem com.android.dialer.speeddial.loader.SpeedDialUiItem)
                     com.android.dialer.speeddial.loader.SpeedDialUiItem.defaultChannel():com.android.dialer.speeddial.database.SpeedDialEntry$Channel type: VIRTUAL)
                     com.android.dialer.speeddial.database.SpeedDialEntry.Channel.number():java.lang.String type: VIRTUAL)
                     android.net.Uri.withAppendedPath(android.net.Uri, java.lang.String):android.net.Uri type: STATIC)
                     com.android.dialer.speeddial.loader.SpeedDialUiItemMutator.starContact(android.net.Uri):com.google.common.util.concurrent.ListenableFuture type: VIRTUAL)
                      (wrap: com.android.dialer.speeddial.-$$Lambda$SpeedDialFragment$SpeedDialSuggestedListener$StarContactModule$_neNUd-UXQLUcy60bG1ExJqGnQY : 0x003a: CONSTRUCTOR  (r3v2 com.android.dialer.speeddial.-$$Lambda$SpeedDialFragment$SpeedDialSuggestedListener$StarContactModule$_neNUd-UXQLUcy60bG1ExJqGnQY) = 
                      (wrap: com.android.dialer.speeddial.SpeedDialFragment : 0x0036: IGET  (r5v2 com.android.dialer.speeddial.SpeedDialFragment) = 
                      (wrap: com.android.dialer.speeddial.SpeedDialFragment$SpeedDialSuggestedListener : 0x0034: IGET  (r5v1 com.android.dialer.speeddial.SpeedDialFragment$SpeedDialSuggestedListener) = 
                      (r5v0 'this' com.android.dialer.speeddial.SpeedDialFragment$SpeedDialSuggestedListener$StarContactModule A[THIS])
                     com.android.dialer.speeddial.SpeedDialFragment.SpeedDialSuggestedListener.StarContactModule.this$1 com.android.dialer.speeddial.SpeedDialFragment$SpeedDialSuggestedListener)
                     com.android.dialer.speeddial.SpeedDialFragment.SpeedDialSuggestedListener.this$0 com.android.dialer.speeddial.SpeedDialFragment)
                     call: com.android.dialer.speeddial.-$$Lambda$SpeedDialFragment$SpeedDialSuggestedListener$StarContactModule$_neNUd-UXQLUcy60bG1ExJqGnQY.<init>(com.android.dialer.speeddial.SpeedDialFragment):void type: CONSTRUCTOR)
                      (wrap: com.android.dialer.speeddial.-$$Lambda$SpeedDialFragment$SpeedDialSuggestedListener$StarContactModule$dGytpkcCv0ma0mcnao2VrXzvDNI : 0x003d: SGET  (r5v3 com.android.dialer.speeddial.-$$Lambda$SpeedDialFragment$SpeedDialSuggestedListener$StarContactModule$dGytpkcCv0ma0mcnao2VrXzvDNI) =  com.android.dialer.speeddial.-$$Lambda$SpeedDialFragment$SpeedDialSuggestedListener$StarContactModule$dGytpkcCv0ma0mcnao2VrXzvDNI.INSTANCE com.android.dialer.speeddial.-$$Lambda$SpeedDialFragment$SpeedDialSuggestedListener$StarContactModule$dGytpkcCv0ma0mcnao2VrXzvDNI)
                     com.android.dialer.common.concurrent.SupportUiListener.listen(android.content.Context, com.google.common.util.concurrent.ListenableFuture, com.android.dialer.common.concurrent.DialerExecutor$SuccessListener, com.android.dialer.common.concurrent.DialerExecutor$FailureListener):void type: VIRTUAL in method: com.android.dialer.speeddial.SpeedDialFragment.SpeedDialSuggestedListener.StarContactModule.onClick():boolean, dex: classes.dex
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:256)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:221)
                    	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                    	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:211)
                    	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:204)
                    	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:318)
                    	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:271)
                    	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:240)
                    	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
                    	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                    	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                    	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:485)
                    	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:474)
                    	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
                    	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
                    	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
                    	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:497)
                    	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
                    	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
                    	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:112)
                    	at jadx.core.codegen.ClassGen.addInnerClass(ClassGen.java:249)
                    	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:238)
                    	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
                    	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                    	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                    	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:485)
                    	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:474)
                    	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
                    	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
                    	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
                    	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:497)
                    	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
                    	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
                    	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:112)
                    	at jadx.core.codegen.ClassGen.addInnerClass(ClassGen.java:249)
                    	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:238)
                    	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
                    	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                    	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                    	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:485)
                    	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:474)
                    	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
                    	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
                    	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
                    	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:497)
                    	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
                    	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
                    	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:112)
                    	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:78)
                    	at jadx.core.codegen.CodeGen.wrapCodeGen(CodeGen.java:44)
                    	at jadx.core.codegen.CodeGen.generateJavaCode(CodeGen.java:33)
                    	at jadx.core.codegen.CodeGen.generate(CodeGen.java:21)
                    	at jadx.core.ProcessClass.generateCode(ProcessClass.java:61)
                    	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:273)
                    Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x003a: CONSTRUCTOR  (r3v2 com.android.dialer.speeddial.-$$Lambda$SpeedDialFragment$SpeedDialSuggestedListener$StarContactModule$_neNUd-UXQLUcy60bG1ExJqGnQY) = 
                      (wrap: com.android.dialer.speeddial.SpeedDialFragment : 0x0036: IGET  (r5v2 com.android.dialer.speeddial.SpeedDialFragment) = 
                      (wrap: com.android.dialer.speeddial.SpeedDialFragment$SpeedDialSuggestedListener : 0x0034: IGET  (r5v1 com.android.dialer.speeddial.SpeedDialFragment$SpeedDialSuggestedListener) = 
                      (r5v0 'this' com.android.dialer.speeddial.SpeedDialFragment$SpeedDialSuggestedListener$StarContactModule A[THIS])
                     com.android.dialer.speeddial.SpeedDialFragment.SpeedDialSuggestedListener.StarContactModule.this$1 com.android.dialer.speeddial.SpeedDialFragment$SpeedDialSuggestedListener)
                     com.android.dialer.speeddial.SpeedDialFragment.SpeedDialSuggestedListener.this$0 com.android.dialer.speeddial.SpeedDialFragment)
                     call: com.android.dialer.speeddial.-$$Lambda$SpeedDialFragment$SpeedDialSuggestedListener$StarContactModule$_neNUd-UXQLUcy60bG1ExJqGnQY.<init>(com.android.dialer.speeddial.SpeedDialFragment):void type: CONSTRUCTOR in method: com.android.dialer.speeddial.SpeedDialFragment.SpeedDialSuggestedListener.StarContactModule.onClick():boolean, dex: classes.dex
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:256)
                    	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:123)
                    	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                    	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:787)
                    	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:728)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:368)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:250)
                    	... 59 more
                    Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Expected class to be processed at this point, class: com.android.dialer.speeddial.-$$Lambda$SpeedDialFragment$SpeedDialSuggestedListener$StarContactModule$_neNUd-UXQLUcy60bG1ExJqGnQY, state: NOT_LOADED
                    	at jadx.core.dex.nodes.ClassNode.ensureProcessed(ClassNode.java:260)
                    	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:606)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:364)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:231)
                    	... 65 more
                    */
                /*
                    this = this;
                    com.android.dialer.speeddial.SpeedDialFragment$SpeedDialSuggestedListener r0 = com.android.dialer.speeddial.SpeedDialFragment.SpeedDialSuggestedListener.this
                    com.android.dialer.speeddial.SpeedDialFragment r0 = com.android.dialer.speeddial.SpeedDialFragment.this
                    com.android.dialer.common.concurrent.SupportUiListener r0 = r0.speedDialLoaderListener
                    com.android.dialer.speeddial.SpeedDialFragment$SpeedDialSuggestedListener r1 = com.android.dialer.speeddial.SpeedDialFragment.SpeedDialSuggestedListener.this
                    com.android.dialer.speeddial.SpeedDialFragment r1 = com.android.dialer.speeddial.SpeedDialFragment.this
                    android.content.Context r1 = r1.getContext()
                    com.android.dialer.speeddial.SpeedDialFragment$SpeedDialSuggestedListener r2 = com.android.dialer.speeddial.SpeedDialFragment.SpeedDialSuggestedListener.this
                    com.android.dialer.speeddial.SpeedDialFragment r2 = com.android.dialer.speeddial.SpeedDialFragment.this
                    android.content.Context r2 = r2.getContext()
                    com.android.dialer.speeddial.loader.UiItemLoaderComponent r2 = com.android.dialer.speeddial.loader.UiItemLoaderComponent.get(r2)
                    com.android.dialer.speeddial.loader.SpeedDialUiItemMutator r2 = r2.speedDialUiItemMutator()
                    android.net.Uri r3 = android.provider.ContactsContract.CommonDataKinds.Phone.CONTENT_FILTER_URI
                    com.android.dialer.speeddial.loader.SpeedDialUiItem r4 = r5.speedDialUiItem
                    com.android.dialer.speeddial.database.SpeedDialEntry$Channel r4 = r4.defaultChannel()
                    java.lang.String r4 = r4.number()
                    android.net.Uri r3 = android.net.Uri.withAppendedPath(r3, r4)
                    com.google.common.util.concurrent.ListenableFuture r2 = r2.starContact(r3)
                    com.android.dialer.speeddial.SpeedDialFragment$SpeedDialSuggestedListener r5 = com.android.dialer.speeddial.SpeedDialFragment.SpeedDialSuggestedListener.this
                    com.android.dialer.speeddial.SpeedDialFragment r5 = com.android.dialer.speeddial.SpeedDialFragment.this
                    com.android.dialer.speeddial.-$$Lambda$SpeedDialFragment$SpeedDialSuggestedListener$StarContactModule$_neNUd-UXQLUcy60bG1ExJqGnQY r3 = new com.android.dialer.speeddial.-$$Lambda$SpeedDialFragment$SpeedDialSuggestedListener$StarContactModule$_neNUd-UXQLUcy60bG1ExJqGnQY
                    r3.<init>(r5)
                    com.android.dialer.speeddial.-$$Lambda$SpeedDialFragment$SpeedDialSuggestedListener$StarContactModule$dGytpkcCv0ma0mcnao2VrXzvDNI r5 = com.android.dialer.speeddial.C0568xc04525fb.INSTANCE
                    r0.listen(r1, r2, r3, r5)
                    r5 = 1
                    return r5
                */
                throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.speeddial.SpeedDialFragment.SpeedDialSuggestedListener.StarContactModule.onClick():boolean");
            }
        }

        /* synthetic */ SpeedDialSuggestedListener(C05701 r2) {
        }

        public void onOverFlowMenuClicked(SpeedDialUiItem speedDialUiItem, HistoryItemBottomSheetHeaderInfo historyItemBottomSheetHeaderInfo) {
            ArrayList arrayList = new ArrayList();
            SpeedDialEntry.Channel defaultChannel = speedDialUiItem.defaultChannel();
            SpeedDialEntry.Channel defaultVoiceChannel = speedDialUiItem.getDefaultVoiceChannel();
            if (defaultVoiceChannel != null) {
                arrayList.add(IntentModule.newCallModule(SpeedDialFragment.this.getContext(), new CallIntentBuilder(defaultVoiceChannel.number(), CallInitiationType$Type.SPEED_DIAL).setAllowAssistedDial(true)));
            }
            SpeedDialEntry.Channel defaultVideoChannel = speedDialUiItem.getDefaultVideoChannel();
            if (defaultVideoChannel != null) {
                arrayList.add(IntentModule.newCallModule(SpeedDialFragment.this.getContext(), new CallIntentBuilder(defaultVideoChannel.number(), CallInitiationType$Type.SPEED_DIAL).setIsVideoCall(true).setAllowAssistedDial(true)));
            }
            if (!TextUtils.isEmpty(defaultChannel.number())) {
                arrayList.add(IntentModule.newModuleForSendingTextMessage(SpeedDialFragment.this.getContext(), defaultChannel.number()));
            }
            arrayList.add(new DividerModule());
            arrayList.add(new StarContactModule(speedDialUiItem));
            arrayList.add(new ContactInfoModule(this, SpeedDialFragment.this.getContext(), new Intent("android.intent.action.VIEW", Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_URI, String.valueOf(speedDialUiItem.contactId()))), R.string.contact_menu_contact_info, R.drawable.context_menu_contact_icon));
            this.bottomSheet = HistoryItemActionBottomSheet.show(SpeedDialFragment.this.getContext(), historyItemBottomSheetHeaderInfo, arrayList);
        }

        public void onPause() {
            HistoryItemActionBottomSheet historyItemActionBottomSheet = this.bottomSheet;
            if (historyItemActionBottomSheet != null && historyItemActionBottomSheet.isShowing()) {
                this.bottomSheet.dismiss();
            }
        }

        public void onRowClicked(SpeedDialEntry.Channel channel) {
            if (channel.technology() == 3) {
                ((LoggingBindingsStub) Logger.get(SpeedDialFragment.this.getContext())).logImpression(DialerImpression$Type.LIGHTBRINGER_VIDEO_REQUESTED_FOR_SUGGESTED_CONTACT);
            }
            Context context = SpeedDialFragment.this.getContext();
            boolean z = true;
            CallIntentBuilder isVideoCall = new CallIntentBuilder(channel.number(), CallInitiationType$Type.SPEED_DIAL).setAllowAssistedDial(true).setIsVideoCall(channel.isVideoTechnology());
            if (channel.technology() != 3) {
                z = false;
            }
            PreCall.start(context, isVideoCall.setIsDuoCall(z));
        }
    }

    class UpdateSpeedDialAdapterListener {
        UpdateSpeedDialAdapterListener() {
        }
    }

    static /* synthetic */ void lambda$loadContacts$1(Throwable th) {
        throw new RuntimeException(th);
    }

    static /* synthetic */ void lambda$onActivityResult$2(Throwable th) {
        throw new RuntimeException(th);
    }

    /* access modifiers changed from: private */
    public void loadContacts() {
        boolean z = true;
        if (!this.updateSpeedDialItemsOnResume) {
            this.updateSpeedDialItemsOnResume = true;
            return;
        }
        if (PermissionsUtil.hasContactsReadPermissions(getContext())) {
            this.emptyContentView.setVisibility(8);
            z = false;
        } else {
            this.emptyContentView.setVisibility(0);
            this.emptyContentView.setActionLabel(R.string.speed_dial_turn_on_contacts_permission);
            this.emptyContentView.setDescription(R.string.speed_dial_contacts_permission_description);
            this.emptyContentView.setActionClickedListener(new SpeedDialContactPermissionEmptyViewListener(getContext(), this, (C05701) null));
        }
        if (!z) {
            this.speedDialLoaderListener.listen(getContext(), UiItemLoaderComponent.get(getContext()).speedDialUiItemMutator().loadSpeedDialUiItems(), new DialerExecutor.SuccessListener() {
                public final void onSuccess(Object obj) {
                    SpeedDialFragment.this.onSpeedDialUiItemListLoaded((ImmutableList) obj);
                }
            }, $$Lambda$SpeedDialFragment$gcZVSGNwrDGK6iyqrYfRDKehE.INSTANCE);
        }
    }

    private void onHidden() {
        if (PermissionsUtil.hasContactsReadPermissions(getContext())) {
            Futures.addCallback(DialerExecutorComponent.get(getContext()).backgroundExecutor().submit(new Callable() {
                public final Object call() {
                    return SpeedDialFragment.this.lambda$onHidden$0$SpeedDialFragment();
                }
            }), new DefaultFutureCallback(), DialerExecutorComponent.get(getContext()).backgroundExecutor());
            Context context = getContext();
            ImmutableList<SpeedDialUiItem> speedDialUiItems = this.adapter.getSpeedDialUiItems();
            ArrayList arrayList = new ArrayList();
            for (SpeedDialUiItem next : speedDialUiItems) {
                ContactEntry contactEntry = new ContactEntry();
                contactEntry.f5id = next.contactId();
                contactEntry.lookupKey = next.lookupKey();
                contactEntry.namePrimary = next.name();
                arrayList.add(contactEntry);
            }
            ShortcutRefresher.refresh(context, arrayList);
        }
    }

    /* access modifiers changed from: private */
    public void onSpeedDialUiItemListLoaded(ImmutableList<SpeedDialUiItem> immutableList) {
        LogUtil.enterBlock("SpeedDialFragment.onSpeedDialUiItemListLoaded");
        this.adapter.setSpeedDialUiItems(UiItemLoaderComponent.get(getContext()).speedDialUiItemMutator().insertDuoChannels(getContext(), immutableList));
        this.adapter.notifyDataSetChanged();
        if (this.adapter.getItemCount() != 0) {
            this.emptyContentView.setVisibility(8);
        } else {
            this.emptyContentView.setVisibility(0);
            this.emptyContentView.setActionLabel(R.string.speed_dial_no_contacts_action_text);
            this.emptyContentView.setDescription(R.string.speed_dial_no_contacts_description);
            this.emptyContentView.setActionClickedListener(new SpeedDialNoContactsEmptyViewListener(this));
        }
        if (getActivity() != null) {
            ((HostInterface) FragmentUtils.getParentUnsafe((Fragment) this, HostInterface.class)).setHasFrequents(this.adapter.hasFrequents());
        }
    }

    public /* synthetic */ Object lambda$onHidden$0$SpeedDialFragment() throws Exception {
        UiItemLoaderComponent.get(getContext()).speedDialUiItemMutator().updatePinnedPosition(this.adapter.getSpeedDialUiItems());
        return null;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 5 && i2 == -1 && intent.getData() != null) {
            ((LoggingBindingsStub) Logger.get(getContext())).logImpression(DialerImpression$Type.FAVORITE_ADD_FAVORITE);
            this.updateSpeedDialItemsOnResume = false;
            this.speedDialLoaderListener.listen(getContext(), UiItemLoaderComponent.get(getContext()).speedDialUiItemMutator().starContact(intent.getData()), new DialerExecutor.SuccessListener() {
                public final void onSuccess(Object obj) {
                    SpeedDialFragment.this.onSpeedDialUiItemListLoaded((ImmutableList) obj);
                }
            }, $$Lambda$SpeedDialFragment$pdqKoKJFQo6qDbJL6Q47IOtZNrs.INSTANCE);
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        LogUtil.enterBlock("SpeedDialFragment.onCreateView");
        View inflate = layoutInflater.inflate(R.layout.fragment_speed_dial, viewGroup, false);
        this.emptyContentView = (EmptyContentView) inflate.findViewById(R.id.speed_dial_empty_content_view);
        this.emptyContentView.setImage(R.drawable.empty_speed_dial);
        this.speedDialLoaderListener = DialerExecutorComponent.get(getContext()).createUiListener(getChildFragmentManager(), "speed_dial_loader_listener");
        SpeedDialLayoutManager speedDialLayoutManager = new SpeedDialLayoutManager(getContext(), 3);
        this.favoritesListener = new SpeedDialFavoritesListener(getActivity(), getChildFragmentManager(), speedDialLayoutManager, new UpdateSpeedDialAdapterListener(), this.speedDialLoaderListener);
        this.adapter = new SpeedDialAdapter(getContext(), this.favoritesListener, this.suggestedListener, this.headerListener, (HostInterface) FragmentUtils.getParentUnsafe((Fragment) this, HostInterface.class));
        speedDialLayoutManager.setSpanSizeLookup(this.adapter.getSpanSizeLookup());
        RecyclerView recyclerView = (RecyclerView) inflate.findViewById(R.id.speed_dial_recycler_view);
        recyclerView.setLayoutManager(speedDialLayoutManager);
        recyclerView.setAdapter(this.adapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SpeedDialItemTouchHelperCallback(getContext(), this.adapter));
        itemTouchHelper.attachToRecyclerView(recyclerView);
        this.adapter.setItemTouchHelper(itemTouchHelper);
        return inflate;
    }

    public void onHiddenChanged(boolean z) {
        if (z) {
            onHidden();
        } else {
            loadContacts();
        }
    }

    public void onPause() {
        super.onPause();
        this.favoritesListener.hideMenu();
        this.suggestedListener.onPause();
        onHidden();
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (i == 1 && iArr.length > 0 && iArr[0] == 0) {
            PermissionsUtil.notifyPermissionGranted(getContext(), "android.permission.READ_CONTACTS");
            loadContacts();
        }
    }

    public void onResume() {
        super.onResume();
        loadContacts();
    }

    public void onStart() {
        super.onStart();
        PermissionsUtil.registerPermissionReceiver(getActivity(), this.readContactsPermissionGrantedReceiver, "android.permission.READ_CONTACTS");
        if (PermissionsUtil.hasContactsReadPermissions(getContext())) {
            getContext().getContentResolver().registerContentObserver(ContactsContract.Contacts.CONTENT_STREQUENT_URI, true, this.strequentsContentObserver);
        }
    }

    public void onStop() {
        super.onStop();
        PermissionsUtil.unregisterPermissionReceiver(getContext(), this.readContactsPermissionGrantedReceiver);
        getContext().getContentResolver().unregisterContentObserver(this.strequentsContentObserver);
    }
}
