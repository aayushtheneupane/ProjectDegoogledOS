package com.android.dialer.callcomposer;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.p000v4.content.ContextCompat;
import android.support.p000v4.content.FileProvider;
import android.support.p000v4.util.Pair;
import android.support.p000v4.view.ViewPager;
import android.support.p000v4.view.animation.FastOutSlowInInterpolator;
import android.support.p002v7.app.AppCompatActivity;
import android.support.p002v7.appcompat.R$style;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.QuickContactBadge;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.android.dialer.R;
import com.android.dialer.callcomposer.CallComposerFragment;
import com.android.dialer.callintent.CallInitiationType$Type;
import com.android.dialer.callintent.CallIntentBuilder;
import com.android.dialer.common.LogUtil;
import com.android.dialer.common.concurrent.DefaultDialerExecutorFactory;
import com.android.dialer.common.concurrent.DialerExecutor;
import com.android.dialer.common.concurrent.DialerExecutorComponent;
import com.android.dialer.common.concurrent.DialerExecutorModule;
import com.android.dialer.configprovider.ConfigProviderComponent;
import com.android.dialer.configprovider.SharedPrefConfigProvider;
import com.android.dialer.constants.Constants;
import com.android.dialer.contactphoto.ContactPhotoManager;
import com.android.dialer.dialercontact.DialerContact;
import com.android.dialer.enrichedcall.EnrichedCallComponent;
import com.android.dialer.enrichedcall.EnrichedCallManager;
import com.android.dialer.enrichedcall.stub.EnrichedCallManagerStub;
import com.android.dialer.logging.DialerImpression$Type;
import com.android.dialer.logging.Logger;
import com.android.dialer.logging.LoggingBindingsStub;
import com.android.dialer.multimedia.MultimediaData;
import com.android.dialer.precall.PreCall;
import com.android.dialer.protos.ProtoParsers;
import com.android.dialer.storage.StorageComponent;
import com.android.dialer.telecom.TelecomUtil;
import com.android.dialer.util.CallUtil;
import com.android.dialer.widget.BidiTextView;
import com.android.dialer.widget.DialerToolbar;
import com.android.dialer.widget.LockableViewPager;
import com.android.incallui.callpending.CallPendingActivity;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.google.protobuf.InvalidProtocolBufferException;
import java.io.File;

public class CallComposerActivity extends AppCompatActivity implements View.OnClickListener, ViewPager.OnPageChangeListener, CallComposerFragment.CallComposerListener, EnrichedCallManager.StateChangedListener {
    private CallComposerPagerAdapter adapter;
    private FrameLayout background;
    /* access modifiers changed from: private */
    public ImageView cameraIcon;
    private DialerContact contact;
    private RelativeLayout contactContainer;
    private QuickContactBadge contactPhoto;
    private DialerExecutor<Uri> copyAndResizeExecutor;
    private int currentIndex;
    /* access modifiers changed from: private */
    public ImageView galleryIcon;
    private boolean inFullscreenMode;
    private FastOutSlowInInterpolator interpolator;
    /* access modifiers changed from: private */
    public boolean isSendAndCallHidingOrHidden = true;
    private ProgressBar loading;
    /* access modifiers changed from: private */
    public ImageView messageIcon;
    private TextView nameView;
    private BidiTextView numberView;
    private LockableViewPager pager;
    private boolean pendingCallStarted;
    private final Runnable placeTelecomCallRunnable = new Runnable() {
        public final void run() {
            CallComposerActivity.this.lambda$new$1$CallComposerActivity();
        }
    };
    private boolean runningExitAnimation;
    /* access modifiers changed from: private */
    public View sendAndCall;
    private boolean sendAndCallReady;
    private TextView sendAndCallText;
    private Long sessionId = -1L;
    private final Runnable sessionStartedTimedOut = new Runnable() {
        public final void run() {
            CallComposerActivity.this.lambda$new$0$CallComposerActivity();
        }
    };
    private boolean shouldAnimateEntrance = true;
    private final Handler timeoutHandler = DialerExecutorModule.getUiThreadHandler();
    private DialerToolbar toolbar;
    private LinearLayout windowContainer;

    private void animateSendAndCall(boolean z) {
        int i = 0;
        if (CallUtil.areAnimationsDisabled(this)) {
            this.isSendAndCallHidingOrHidden = z;
            View view = this.sendAndCall;
            if (z) {
                i = 4;
            }
            view.setVisibility(i);
        } else if (this.isSendAndCallHidingOrHidden != z) {
            int width = this.sendAndCall.getWidth() / 2;
            CallUtil.doOnPreDraw(this.sendAndCall, true, new Runnable(width, this.sendAndCall.getHeight() / 2, z ? width : 0, z ? 0 : width, z) {
                private final /* synthetic */ int f$1;
                private final /* synthetic */ int f$2;
                private final /* synthetic */ int f$3;
                private final /* synthetic */ int f$4;
                private final /* synthetic */ boolean f$5;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                    this.f$3 = r4;
                    this.f$4 = r5;
                    this.f$5 = r6;
                }

                public final void run() {
                    CallComposerActivity.this.lambda$animateSendAndCall$8$CallComposerActivity(this.f$1, this.f$2, this.f$3, this.f$4, this.f$5);
                }
            });
        }
    }

    private EnrichedCallManager getEnrichedCallManager() {
        return EnrichedCallComponent.get(this).getEnrichedCallManager();
    }

    private Uri grantUriPermission(Uri uri) {
        grantUriPermission("com.google.android.apps.messaging", uri, 1);
        return uri;
    }

    public static Intent newIntent(Context context, DialerContact dialerContact) {
        Intent intent = new Intent(context, CallComposerActivity.class);
        ProtoParsers.put(intent, "CALL_COMPOSER_CONTACT", dialerContact);
        return intent;
    }

    /* access modifiers changed from: private */
    public void onCopyAndResizeImageFailure(Throwable th) {
        LogUtil.m7e("CallComposerActivity.onCopyAndResizeImageFailure", "copy Failed", th);
    }

    /* access modifiers changed from: private */
    public void onCopyAndResizeImageSuccess(Pair<File, String> pair) {
        Uri uriForFile = FileProvider.getUriForFile(this, Constants.get().getFileProviderAuthority(), (File) pair.first);
        MultimediaData.Builder builder = MultimediaData.builder();
        grantUriPermission(uriForFile);
        builder.setImage(uriForFile, (String) pair.second);
        placeRCSCall(builder);
    }

    private void onHandleIntent(Intent intent) {
        String str;
        if (intent.getExtras().containsKey("CALL_COMPOSER_CONTACT_BASE64")) {
            try {
                this.contact = DialerContact.parseFrom(Base64.decode(intent.getStringExtra("CALL_COMPOSER_CONTACT_BASE64"), 0));
            } catch (InvalidProtocolBufferException e) {
                throw new AssertionError(e.toString());
            }
        } else {
            this.contact = (DialerContact) ProtoParsers.getTrusted(intent, "CALL_COMPOSER_CONTACT", DialerContact.getDefaultInstance());
        }
        ContactPhotoManager.getInstance(this).loadDialerThumbnailOrPhoto(this.contactPhoto, this.contact.hasContactUri() ? Uri.parse(this.contact.getContactUri()) : null, this.contact.getPhotoId(), this.contact.hasPhotoUri() ? Uri.parse(this.contact.getPhotoUri()) : null, this.contact.getNameOrNumber(), this.contact.getContactType());
        this.nameView.setText(this.contact.getNameOrNumber());
        this.toolbar.setTitle((CharSequence) this.contact.getNameOrNumber());
        if (!TextUtils.isEmpty(this.contact.getDisplayNumber())) {
            this.numberView.setVisibility(0);
            if (TextUtils.isEmpty(this.contact.getNumberLabel())) {
                str = this.contact.getDisplayNumber();
            } else {
                str = getString(R.string.call_subject_type_and_number, new Object[]{this.contact.getNumberLabel(), this.contact.getDisplayNumber()});
            }
            this.numberView.setText(str);
            this.toolbar.setSubtitle(str);
            return;
        }
        this.numberView.setVisibility(8);
        this.numberView.setText((CharSequence) null);
    }

    private void placeTelecomCall() {
        PreCall.start(this, new CallIntentBuilder(this.contact.getNumber(), CallInitiationType$Type.CALL_COMPOSER).setAllowAssistedDial(true));
        setResult(-1);
        finish();
    }

    private void setFailedResultAndFinish() {
        setResult(1, new Intent().putExtra("contact_name", this.contact.getNameOrNumber()));
        finish();
    }

    private void setMediaIconSelected(int i) {
        float f = 1.0f;
        this.cameraIcon.setAlpha(i == 0 ? 1.0f : 0.7f);
        this.galleryIcon.setAlpha(i == 1 ? 1.0f : 0.7f);
        ImageView imageView = this.messageIcon;
        if (i != 2) {
            f = 0.7f;
        }
        imageView.setAlpha(f);
    }

    public void composeCall(CallComposerFragment callComposerFragment) {
        if (this.adapter.instantiateItem(this.pager, this.currentIndex) == callComposerFragment) {
            animateSendAndCall(callComposerFragment.shouldHide());
        }
    }

    public long getRCSTimeoutMillis() {
        return ((SharedPrefConfigProvider) ConfigProviderComponent.get(this).getConfigProvider()).getLong("ec_image_upload_timeout", 15000);
    }

    public long getSessionStartedTimeoutMillis() {
        return ((SharedPrefConfigProvider) ConfigProviderComponent.get(this).getConfigProvider()).getLong("ec_session_started_timeout", 10000);
    }

    public boolean isFullscreen() {
        return this.inFullscreenMode;
    }

    public boolean isLandscapeLayout() {
        return getResources().getConfiguration().orientation == 2;
    }

    public /* synthetic */ void lambda$animateSendAndCall$8$CallComposerActivity(int i, int i2, int i3, int i4, final boolean z) {
        Animator createCircularReveal = ViewAnimationUtils.createCircularReveal(this.sendAndCall, i, i2, (float) i3, (float) i4);
        createCircularReveal.addListener(new Animator.AnimatorListener() {
            public void onAnimationCancel(Animator animator) {
            }

            public void onAnimationEnd(Animator animator) {
                if (CallComposerActivity.this.isSendAndCallHidingOrHidden) {
                    CallComposerActivity.this.sendAndCall.setVisibility(4);
                    return;
                }
                CallComposerActivity.this.cameraIcon.setVisibility(8);
                CallComposerActivity.this.galleryIcon.setVisibility(8);
                CallComposerActivity.this.messageIcon.setVisibility(8);
            }

            public void onAnimationRepeat(Animator animator) {
            }

            public void onAnimationStart(Animator animator) {
                boolean unused = CallComposerActivity.this.isSendAndCallHidingOrHidden = z;
                CallComposerActivity.this.sendAndCall.setVisibility(0);
                CallComposerActivity.this.cameraIcon.setVisibility(0);
                CallComposerActivity.this.galleryIcon.setVisibility(0);
                CallComposerActivity.this.messageIcon.setVisibility(0);
            }
        });
        createCircularReveal.start();
    }

    public /* synthetic */ void lambda$new$0$CallComposerActivity() {
        LogUtil.m9i("CallComposerActivity.sessionStartedTimedOutRunnable", "session never started", new Object[0]);
        setFailedResultAndFinish();
    }

    public /* synthetic */ void lambda$new$1$CallComposerActivity() {
        LogUtil.m9i("CallComposerActivity.placeTelecomCallRunnable", "upload timed out.", new Object[0]);
        placeTelecomCall();
    }

    public /* synthetic */ void lambda$onCreate$2$CallComposerActivity() {
        showFullscreen(this.inFullscreenMode);
        if (this.shouldAnimateEntrance) {
            this.shouldAnimateEntrance = false;
            ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{(float) (isLandscapeLayout() ? this.windowContainer.getWidth() : this.windowContainer.getHeight()), 0.0f});
            ofFloat.setInterpolator(this.interpolator);
            ofFloat.setDuration(500);
            ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                    CallComposerActivity.this.lambda$runEntranceAnimation$4$CallComposerActivity(valueAnimator);
                }
            });
            if (!isLandscapeLayout()) {
                ValueAnimator ofObject = ValueAnimator.ofObject(new ArgbEvaluator(), new Object[]{Integer.valueOf(ContextCompat.getColor(this, 17170445)), Integer.valueOf(ContextCompat.getColor(this, R.color.call_composer_background_color))});
                ofObject.setInterpolator(this.interpolator);
                ofObject.setDuration(500);
                ofObject.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                        CallComposerActivity.this.lambda$runEntranceAnimation$5$CallComposerActivity(valueAnimator);
                    }
                });
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.play(ofFloat).with(ofObject);
                animatorSet.start();
                return;
            }
            ofFloat.start();
        }
    }

    public /* synthetic */ void lambda$runEntranceAnimation$4$CallComposerActivity(ValueAnimator valueAnimator) {
        if (isLandscapeLayout()) {
            this.windowContainer.setX(((Float) valueAnimator.getAnimatedValue()).floatValue());
        } else {
            this.windowContainer.setY(((Float) valueAnimator.getAnimatedValue()).floatValue());
        }
    }

    public /* synthetic */ void lambda$runEntranceAnimation$5$CallComposerActivity(ValueAnimator valueAnimator) {
        this.background.setBackgroundColor(((Integer) valueAnimator.getAnimatedValue()).intValue());
    }

    public /* synthetic */ void lambda$runExitAnimation$6$CallComposerActivity(ValueAnimator valueAnimator) {
        if (isLandscapeLayout()) {
            this.windowContainer.setX(((Float) valueAnimator.getAnimatedValue()).floatValue());
        } else {
            this.windowContainer.setY(((Float) valueAnimator.getAnimatedValue()).floatValue());
        }
        if (((double) valueAnimator.getAnimatedFraction()) > 0.95d) {
            finish();
        }
    }

    public /* synthetic */ void lambda$runExitAnimation$7$CallComposerActivity(ValueAnimator valueAnimator) {
        this.background.setBackgroundColor(((Integer) valueAnimator.getAnimatedValue()).intValue());
    }

    public void onBackPressed() {
        LogUtil.enterBlock("CallComposerActivity.onBackPressed");
        if (!this.isSendAndCallHidingOrHidden) {
            ((CallComposerFragment) this.adapter.instantiateItem(this.pager, this.currentIndex)).clearComposer();
        } else if (!this.runningExitAnimation) {
            ((EnrichedCallManagerStub) getEnrichedCallManager()).unregisterStateChangedListener(this);
            ((EnrichedCallManagerStub) getEnrichedCallManager()).getSession(this.sessionId.longValue());
            ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{0.0f, (float) (isLandscapeLayout() ? this.windowContainer.getWidth() : this.windowContainer.getHeight())});
            ofFloat.setInterpolator(this.interpolator);
            ofFloat.setDuration(500);
            ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                    CallComposerActivity.this.lambda$runExitAnimation$6$CallComposerActivity(valueAnimator);
                }
            });
            if (!isLandscapeLayout()) {
                int color = ContextCompat.getColor(this, 17170445);
                ValueAnimator ofObject = ValueAnimator.ofObject(new ArgbEvaluator(), new Object[]{Integer.valueOf(ContextCompat.getColor(this, R.color.call_composer_background_color)), Integer.valueOf(color)});
                ofObject.setInterpolator(this.interpolator);
                ofObject.setDuration(500);
                ofObject.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                        CallComposerActivity.this.lambda$runExitAnimation$7$CallComposerActivity(valueAnimator);
                    }
                });
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.play(ofFloat).with(ofObject);
                animatorSet.start();
            } else {
                ofFloat.start();
            }
            this.runningExitAnimation = true;
        }
    }

    public void onClick(View view) {
        LogUtil.enterBlock("CallComposerActivity.onClick");
        if (view == this.cameraIcon) {
            this.pager.setCurrentItem(0, true);
        } else if (view == this.galleryIcon) {
            this.pager.setCurrentItem(1, true);
        } else if (view == this.messageIcon) {
            this.pager.setCurrentItem(2, true);
        } else if (view == this.sendAndCall) {
            sendAndCall();
        } else {
            throw new IllegalStateException(GeneratedOutlineSupport.outline6("View on click not implemented: ", view));
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.call_composer_activity);
        this.nameView = (TextView) findViewById(R.id.contact_name);
        this.numberView = (BidiTextView) findViewById(R.id.phone_number);
        this.contactPhoto = (QuickContactBadge) findViewById(R.id.contact_photo);
        this.cameraIcon = (ImageView) findViewById(R.id.call_composer_camera);
        this.galleryIcon = (ImageView) findViewById(R.id.call_composer_photo);
        this.messageIcon = (ImageView) findViewById(R.id.call_composer_message);
        this.contactContainer = (RelativeLayout) findViewById(R.id.contact_bar);
        this.pager = (LockableViewPager) findViewById(R.id.call_composer_view_pager);
        this.background = (FrameLayout) findViewById(R.id.background);
        this.windowContainer = (LinearLayout) findViewById(R.id.call_composer_container);
        this.toolbar = (DialerToolbar) findViewById(R.id.toolbar);
        this.sendAndCall = findViewById(R.id.send_and_call_button);
        this.sendAndCallText = (TextView) findViewById(R.id.send_and_call_text);
        this.loading = (ProgressBar) findViewById(R.id.call_composer_loading);
        this.interpolator = new FastOutSlowInInterpolator();
        this.adapter = new CallComposerPagerAdapter(getSupportFragmentManager(), getResources().getInteger(R.integer.call_composer_message_limit));
        this.pager.setAdapter(this.adapter);
        this.pager.addOnPageChangeListener(this);
        this.cameraIcon.setOnClickListener(this);
        this.galleryIcon.setOnClickListener(this);
        this.messageIcon.setOnClickListener(this);
        this.sendAndCall.setOnClickListener(this);
        onHandleIntent(getIntent());
        if (bundle != null) {
            this.shouldAnimateEntrance = bundle.getBoolean("entrance_animation_key");
            this.sendAndCallReady = bundle.getBoolean("send_and_call_ready_key");
            this.pager.onRestoreInstanceState(bundle.getParcelable("view_pager_state_key"));
            this.currentIndex = bundle.getInt("current_index_key");
            this.sessionId = Long.valueOf(bundle.getLong("session_id_key", -1));
            onPageSelected(this.currentIndex);
        }
        CallUtil.doOnPreDraw(this.windowContainer, false, new Runnable() {
            public final void run() {
                CallComposerActivity.this.lambda$onCreate$2$CallComposerActivity();
            }
        });
        setMediaIconSelected(this.currentIndex);
        this.copyAndResizeExecutor = ((DefaultDialerExecutorFactory) DialerExecutorComponent.get(getApplicationContext()).dialerExecutorFactory()).createUiTaskBuilder(getFragmentManager(), "copyAndResizeImageToSend", new CopyAndResizeImageWorker(getApplicationContext())).onSuccess(new DialerExecutor.SuccessListener() {
            public final void onSuccess(Object obj) {
                CallComposerActivity.this.onCopyAndResizeImageSuccess((Pair) obj);
            }
        }).onFailure(new DialerExecutor.FailureListener() {
            public final void onFailure(Throwable th) {
                CallComposerActivity.this.onCopyAndResizeImageFailure(th);
            }
        }).build();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        ((EnrichedCallManagerStub) getEnrichedCallManager()).unregisterStateChangedListener(this);
        this.timeoutHandler.removeCallbacksAndMessages((Object) null);
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        onHandleIntent(intent);
    }

    public void onPageScrollStateChanged(int i) {
    }

    public void onPageScrolled(int i, float f, int i2) {
    }

    public void onPageSelected(int i) {
        if (i == 2) {
            this.sendAndCallText.setText(R.string.send_and_call);
        } else {
            this.sendAndCallText.setText(R.string.share_and_call);
        }
        if (this.currentIndex == 2) {
            R$style.hideKeyboardFrom(this, this.windowContainer);
        }
        this.currentIndex = i;
        animateSendAndCall(((CallComposerFragment) this.adapter.instantiateItem(this.pager, i)).shouldHide());
        setMediaIconSelected(i);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        ((EnrichedCallManagerStub) getEnrichedCallManager()).registerStateChangedListener(this);
        if (this.pendingCallStarted) {
            this.timeoutHandler.removeCallbacks(this.placeTelecomCallRunnable);
            setResult(-1);
            finish();
        } else if (this.sessionId.longValue() == -1) {
            LogUtil.m9i("CallComposerActivity.onResume", "creating new session", new Object[0]);
            ((EnrichedCallManagerStub) getEnrichedCallManager()).startCallComposerSession(this.contact.getNumber());
            this.sessionId = -1L;
        } else {
            ((EnrichedCallManagerStub) getEnrichedCallManager()).getSession(this.sessionId.longValue());
            LogUtil.m9i("CallComposerActivity.onResume", "session closed while activity paused, creating new", new Object[0]);
            ((EnrichedCallManagerStub) getEnrichedCallManager()).startCallComposerSession(this.contact.getNumber());
            this.sessionId = -1L;
        }
        if (this.sessionId.longValue() == -1) {
            LogUtil.m10w("CallComposerActivity.onResume", "failed to create call composer session", new Object[0]);
            setFailedResultAndFinish();
        }
        ((EnrichedCallManagerStub) getEnrichedCallManager()).getSession(this.sessionId.longValue());
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putParcelable("view_pager_state_key", this.pager.onSaveInstanceState());
        bundle.putBoolean("entrance_animation_key", this.shouldAnimateEntrance);
        bundle.putBoolean("send_and_call_ready_key", this.sendAndCallReady);
        bundle.putInt("current_index_key", this.currentIndex);
        bundle.putLong("session_id_key", this.sessionId.longValue());
    }

    public void placeRCSCall(MultimediaData.Builder builder) {
        MultimediaData build = builder.build();
        LogUtil.m9i("CallComposerActivity.placeRCSCall", GeneratedOutlineSupport.outline6("placing enriched call, data: ", build), new Object[0]);
        ((LoggingBindingsStub) Logger.get(this)).logImpression(DialerImpression$Type.CALL_COMPOSER_ACTIVITY_PLACE_RCS_CALL);
        ((EnrichedCallManagerStub) getEnrichedCallManager()).sendCallComposerData(this.sessionId.longValue(), build);
        SharedPreferences unencryptedSharedPrefs = StorageComponent.get(this).unencryptedSharedPrefs();
        if (unencryptedSharedPrefs.getBoolean("is_first_call_compose", true)) {
            Toast makeText = Toast.makeText(this, build.hasImageData() ? R.string.image_sent_messages : R.string.message_sent_messages, 1);
            makeText.setGravity(81, 0, getResources().getDimensionPixelOffset(R.dimen.privacy_toast_y_offset));
            makeText.show();
            unencryptedSharedPrefs.edit().putBoolean("is_first_call_compose", false).apply();
        }
        if (!build.hasImageData() || !((SharedPrefConfigProvider) ConfigProviderComponent.get(this).getConfigProvider()).getBoolean("enable_delayed_ec_images", true) || TelecomUtil.isInManagedCall(this)) {
            placeTelecomCall();
            return;
        }
        this.timeoutHandler.postDelayed(this.placeTelecomCallRunnable, getRCSTimeoutMillis());
        startActivity(CallPendingActivity.getIntent(this, this.contact.getNameOrNumber(), this.contact.getDisplayNumber(), this.contact.getNumberLabel(), R$style.getLookupKeyFromUri(Uri.parse(this.contact.getContactUri())), getString(R.string.call_composer_image_uploading), Uri.parse(this.contact.getPhotoUri()), this.sessionId.longValue()));
        this.pendingCallStarted = true;
    }

    public void sendAndCall() {
        ((EnrichedCallManagerStub) getEnrichedCallManager()).getSession(this.sessionId.longValue());
        this.sendAndCallReady = true;
        this.loading.setVisibility(0);
        this.pager.setSwipingLocked(true);
        LogUtil.m9i("CallComposerActivity.onClick", "sendAndCall pressed, but the session isn't ready", new Object[0]);
        ((LoggingBindingsStub) Logger.get(this)).logImpression(DialerImpression$Type.CALL_COMPOSER_ACTIVITY_SEND_AND_CALL_PRESSED_WHEN_SESSION_NOT_READY);
    }

    public void showFullscreen(boolean z) {
        this.inFullscreenMode = z;
        ViewGroup.LayoutParams layoutParams = this.pager.getLayoutParams();
        if (isLandscapeLayout()) {
            layoutParams.height = this.background.getHeight();
            this.toolbar.setVisibility(4);
            this.contactContainer.setVisibility(8);
        } else if (z || getResources().getBoolean(R.bool.show_toolbar)) {
            layoutParams.height = this.background.getHeight() - this.toolbar.getHeight();
            this.toolbar.setVisibility(0);
            this.contactContainer.setVisibility(8);
        } else {
            layoutParams.height = getResources().getDimensionPixelSize(R.dimen.call_composer_view_pager_height);
            this.toolbar.setVisibility(4);
            this.contactContainer.setVisibility(0);
        }
        this.pager.setLayoutParams(layoutParams);
    }
}
