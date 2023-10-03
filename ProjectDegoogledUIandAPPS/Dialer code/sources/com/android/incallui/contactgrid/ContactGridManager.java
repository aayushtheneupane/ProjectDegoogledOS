package com.android.incallui.contactgrid;

import android.content.Context;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.support.p000v4.view.ViewCompat;
import android.support.p002v7.appcompat.R$style;
import android.telephony.PhoneNumberUtils;
import android.text.BidiFormatter;
import android.text.TextDirectionHeuristics;
import android.text.TextUtils;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.Space;
import android.widget.TextView;
import android.widget.ViewAnimator;
import com.android.dialer.R;
import com.android.dialer.common.Assert;
import com.android.dialer.common.LogUtil;
import com.android.dialer.configprovider.ConfigProviderComponent;
import com.android.dialer.configprovider.SharedPrefConfigProvider;
import com.android.dialer.glidephotomanager.GlidePhotoManagerComponent;
import com.android.dialer.glidephotomanager.PhotoInfo;
import com.android.dialer.glidephotomanager.impl.GlidePhotoManagerImpl;
import com.android.dialer.lettertile.LetterTileDrawable;
import com.android.dialer.widget.BidiTextView;
import com.android.incallui.contactgrid.BottomRow;
import com.android.incallui.incall.protocol.PrimaryCallState;
import com.android.incallui.incall.protocol.PrimaryInfo;
import java.util.List;

public class ContactGridManager {
    private ImageView avatarImageView;
    private int avatarSize;
    private final ViewAnimator bottomTextSwitcher;
    private final BidiTextView bottomTextView;
    private final Chronometer bottomTimerView;
    private final ImageView connectionIconImageView;
    private final View contactGridLayout;
    private final TextView contactNameTextView;
    private final Context context;
    private final View deviceNumberDivider;
    private final TextView deviceNumberTextView;
    private final ImageView forwardIconImageView;
    private final TextView forwardedNumberView;
    private final ImageView hdIconImageView;
    private boolean hideAvatar;
    private boolean isInMultiWindowMode;
    private boolean isTimerStarted;
    private final LetterTileDrawable letterTile;
    private boolean middleRowVisible = true;
    private PrimaryCallState primaryCallState = PrimaryCallState.empty();
    private PrimaryInfo primaryInfo = PrimaryInfo.empty();
    private boolean showAnonymousAvatar;
    private final ImageView spamIconImageView;
    private final TextView statusTextView;
    private final Space topRowSpace;
    private final ImageView workIconImageView;

    public ContactGridManager(View view, ImageView imageView, int i, boolean z) {
        this.context = view.getContext();
        Assert.isNotNull(this.context);
        this.avatarImageView = imageView;
        this.avatarSize = i;
        this.showAnonymousAvatar = z;
        this.connectionIconImageView = (ImageView) view.findViewById(R.id.contactgrid_connection_icon);
        this.statusTextView = (TextView) view.findViewById(R.id.contactgrid_status_text);
        this.contactNameTextView = (TextView) view.findViewById(R.id.contactgrid_contact_name);
        this.workIconImageView = (ImageView) view.findViewById(R.id.contactgrid_workIcon);
        this.hdIconImageView = (ImageView) view.findViewById(R.id.contactgrid_hdIcon);
        this.forwardIconImageView = (ImageView) view.findViewById(R.id.contactgrid_forwardIcon);
        this.forwardedNumberView = (TextView) view.findViewById(R.id.contactgrid_forwardNumber);
        this.spamIconImageView = (ImageView) view.findViewById(R.id.contactgrid_spamIcon);
        this.bottomTextSwitcher = (ViewAnimator) view.findViewById(R.id.contactgrid_bottom_text_switcher);
        this.bottomTextView = (BidiTextView) view.findViewById(R.id.contactgrid_bottom_text);
        this.bottomTimerView = (Chronometer) view.findViewById(R.id.contactgrid_bottom_timer);
        this.topRowSpace = (Space) view.findViewById(R.id.contactgrid_top_row_space);
        this.contactGridLayout = (View) this.contactNameTextView.getParent();
        this.letterTile = new LetterTileDrawable(this.context.getResources());
        this.isTimerStarted = false;
        this.deviceNumberTextView = (TextView) view.findViewById(R.id.contactgrid_device_number_text);
        this.deviceNumberDivider = view.findViewById(R.id.contactgrid_location_divider);
    }

    private boolean updateAvatarVisibility() {
        ImageView imageView = this.avatarImageView;
        if (imageView == null) {
            return false;
        }
        if (!this.middleRowVisible) {
            imageView.setVisibility(8);
            return false;
        }
        if ((!(this.primaryInfo.photo() == null && this.primaryInfo.photoUri() == null) && this.primaryInfo.photoType() == 2) || this.showAnonymousAvatar) {
            this.avatarImageView.setVisibility(0);
            return true;
        }
        this.avatarImageView.setVisibility(8);
        return false;
    }

    private void updateBottomRow() {
        BottomRow.Info info = BottomRow.getInfo(this.context, this.primaryCallState, this.primaryInfo);
        this.bottomTextView.setText(info.label);
        this.bottomTextView.setAllCaps(info.isSpamIconVisible);
        this.workIconImageView.setVisibility(info.isWorkIconVisible ? 0 : 8);
        if (this.hdIconImageView.getVisibility() == 8) {
            if (info.isHdAttemptingIconVisible) {
                this.hdIconImageView.setImageResource(R.drawable.asd_hd_icon);
                this.hdIconImageView.setVisibility(0);
                this.hdIconImageView.setActivated(false);
                Drawable current = this.hdIconImageView.getDrawable().getCurrent();
                if (current instanceof Animatable) {
                    Animatable animatable = (Animatable) current;
                    if (!animatable.isRunning()) {
                        animatable.start();
                    }
                }
            } else if (info.isHdIconVisible) {
                this.hdIconImageView.setImageResource(R.drawable.asd_hd_icon);
                this.hdIconImageView.setVisibility(0);
                this.hdIconImageView.setActivated(true);
            }
        } else if (info.isHdIconVisible) {
            this.hdIconImageView.setActivated(true);
        } else if (!info.isHdAttemptingIconVisible) {
            this.hdIconImageView.setVisibility(8);
        }
        this.spamIconImageView.setVisibility(info.isSpamIconVisible ? 0 : 8);
        if (info.isForwardIconVisible) {
            this.forwardIconImageView.setVisibility(0);
            this.forwardedNumberView.setVisibility(0);
            if (info.isTimerVisible) {
                this.bottomTextSwitcher.setVisibility(0);
                if (ViewCompat.getLayoutDirection(this.contactGridLayout) == 0) {
                    this.forwardedNumberView.setText(TextUtils.concat(new CharSequence[]{info.label, " • "}));
                } else {
                    this.forwardedNumberView.setText(TextUtils.concat(new CharSequence[]{" • ", info.label}));
                }
            } else {
                this.bottomTextSwitcher.setVisibility(8);
                this.forwardedNumberView.setText(info.label);
            }
        } else {
            this.forwardIconImageView.setVisibility(8);
            this.forwardedNumberView.setVisibility(8);
            this.bottomTextSwitcher.setVisibility(0);
        }
        if (info.isTimerVisible) {
            this.bottomTextSwitcher.setDisplayedChild(1);
            this.bottomTimerView.setBase(SystemClock.elapsedRealtime() + (this.primaryCallState.connectTimeMillis() - System.currentTimeMillis()));
            if (!this.isTimerStarted) {
                LogUtil.m9i("ContactGridManager.updateBottomRow", "starting timer with base: %d", Long.valueOf(this.bottomTimerView.getBase()));
                this.bottomTimerView.start();
                this.isTimerStarted = true;
                return;
            }
            return;
        }
        this.bottomTextSwitcher.setDisplayedChild(0);
        this.bottomTimerView.stop();
        this.isTimerStarted = false;
    }

    private void updateDeviceNumberRow() {
        if (this.deviceNumberTextView != null) {
            if (this.isInMultiWindowMode || TextUtils.isEmpty(this.primaryCallState.callbackNumber())) {
                this.deviceNumberTextView.setVisibility(8);
                this.deviceNumberDivider.setVisibility(8);
                return;
            }
            this.deviceNumberTextView.setText(this.context.getString(R.string.contact_grid_callback_number, new Object[]{BidiFormatter.getInstance().unicodeWrap(this.primaryCallState.callbackNumber(), TextDirectionHeuristics.LTR)}));
            this.deviceNumberTextView.setVisibility(0);
            if (this.primaryInfo.shouldShowLocation()) {
                this.deviceNumberDivider.setVisibility(0);
            }
        }
    }

    private void updatePrimaryNameAndPhoto() {
        CharSequence charSequence;
        boolean z = false;
        if (TextUtils.isEmpty(this.primaryInfo.name())) {
            this.contactNameTextView.setText((CharSequence) null);
        } else {
            TextView textView = this.contactNameTextView;
            if (this.primaryInfo.nameIsNumber()) {
                charSequence = PhoneNumberUtils.createTtsSpannable(this.primaryInfo.name());
            } else {
                charSequence = this.primaryInfo.name();
            }
            textView.setText(charSequence);
            this.contactNameTextView.setTextDirection(this.primaryInfo.nameIsNumber() ? 3 : 0);
        }
        ImageView imageView = this.avatarImageView;
        if (imageView == null) {
            return;
        }
        if (this.hideAvatar) {
            imageView.setVisibility(8);
        } else if (this.avatarSize > 0 && updateAvatarVisibility()) {
            if (((SharedPrefConfigProvider) ConfigProviderComponent.get(this.context).getConfigProvider()).getBoolean("enable_glide_photo", false)) {
                PhotoInfo.Builder newBuilder = PhotoInfo.newBuilder();
                if (this.primaryInfo.photoType() == 1) {
                    z = true;
                }
                newBuilder.setIsBusiness(z);
                newBuilder.setIsVoicemail(this.primaryCallState.isVoiceMailNumber());
                newBuilder.setIsSpam(this.primaryInfo.isSpam());
                newBuilder.setIsConference(this.primaryCallState.isConference());
                if (this.primaryInfo.nameIsNumber() && this.primaryInfo.number() != null) {
                    newBuilder.setName(this.primaryInfo.number());
                } else if (this.primaryInfo.name() != null) {
                    newBuilder.setName(this.primaryInfo.name());
                }
                if (this.primaryInfo.number() != null) {
                    newBuilder.setFormattedNumber(this.primaryInfo.number());
                }
                if (this.primaryInfo.photoUri() != null) {
                    newBuilder.setPhotoUri(this.primaryInfo.photoUri().toString());
                }
                if (this.primaryInfo.contactInfoLookupKey() != null) {
                    newBuilder.setLookupUri(this.primaryInfo.contactInfoLookupKey());
                }
                ((GlidePhotoManagerImpl) GlidePhotoManagerComponent.get(this.context).glidePhotoManager()).loadContactPhoto(this.avatarImageView, (PhotoInfo) newBuilder.build());
                return;
            }
            if (this.primaryInfo.photo() != null && this.primaryInfo.photoType() == 2) {
                z = true;
            }
            if (z) {
                ImageView imageView2 = this.avatarImageView;
                Context context2 = this.context;
                Drawable photo = this.primaryInfo.photo();
                int i = this.avatarSize;
                imageView2.setBackground(R$style.getRoundedDrawable(context2, photo, i, i));
                return;
            }
            this.letterTile.setCanonicalDialerLetterTileDetails(this.primaryInfo.name(), this.primaryInfo.contactInfoLookupKey(), 1, LetterTileDrawable.getContactTypeFromPrimitives(this.primaryCallState.isVoiceMailNumber(), this.primaryInfo.isSpam(), this.primaryCallState.isBusinessNumber(), this.primaryInfo.numberPresentation(), this.primaryCallState.isConference()));
            this.avatarImageView.invalidate();
            this.avatarImageView.setBackground(this.letterTile);
        }
    }

    public void dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        dispatchPopulateAccessibilityEvent(accessibilityEvent, this.statusTextView);
        dispatchPopulateAccessibilityEvent(accessibilityEvent, this.contactNameTextView);
        if (BottomRow.getInfo(this.context, this.primaryCallState, this.primaryInfo).shouldPopulateAccessibilityEvent) {
            dispatchPopulateAccessibilityEvent(accessibilityEvent, this.bottomTextView);
        }
    }

    public View getContainerView() {
        return this.contactGridLayout;
    }

    public void onMultiWindowModeChanged(boolean z) {
        if (this.isInMultiWindowMode != z) {
            this.isInMultiWindowMode = z;
            updateDeviceNumberRow();
        }
    }

    public void setAvatarHidden(boolean z) {
        if (z != this.hideAvatar) {
            this.hideAvatar = z;
            updatePrimaryNameAndPhoto();
        }
    }

    public void setAvatarImageView(ImageView imageView, int i, boolean z) {
        this.avatarImageView = imageView;
        this.avatarSize = i;
        this.showAnonymousAvatar = z;
        updatePrimaryNameAndPhoto();
    }

    public void setCallState(PrimaryCallState primaryCallState2) {
        this.primaryCallState = primaryCallState2;
        updatePrimaryNameAndPhoto();
        updateBottomRow();
        TopRow$Info info = BottomRow.getInfo(this.context, this.primaryCallState, this.primaryInfo);
        if (TextUtils.isEmpty(info.label)) {
            this.statusTextView.setVisibility(4);
            this.statusTextView.setText((CharSequence) null);
        } else {
            this.statusTextView.setText(info.label);
            this.statusTextView.setVisibility(0);
            this.statusTextView.setSingleLine(info.labelIsSingleLine);
            this.statusTextView.setSelected(true);
        }
        if (info.icon == null) {
            this.connectionIconImageView.setVisibility(8);
            this.topRowSpace.setVisibility(8);
        } else {
            this.connectionIconImageView.setVisibility(0);
            this.connectionIconImageView.setImageDrawable(info.icon);
            if (this.statusTextView.getVisibility() != 0 || TextUtils.isEmpty(this.statusTextView.getText())) {
                this.topRowSpace.setVisibility(8);
            } else {
                this.topRowSpace.setVisibility(0);
            }
        }
        updateDeviceNumberRow();
    }

    public void setIsMiddleRowVisible(boolean z) {
        if (this.middleRowVisible != z) {
            this.middleRowVisible = z;
            this.contactNameTextView.setVisibility(z ? 0 : 8);
            updateAvatarVisibility();
        }
    }

    public void setPrimary(PrimaryInfo primaryInfo2) {
        this.primaryInfo = primaryInfo2;
        updatePrimaryNameAndPhoto();
        updateBottomRow();
        updateDeviceNumberRow();
    }

    public void show() {
        this.contactGridLayout.setVisibility(0);
    }

    private void dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent, View view) {
        List text = accessibilityEvent.getText();
        int size = text.size();
        view.dispatchPopulateAccessibilityEvent(accessibilityEvent);
        if (size == text.size()) {
            text.add((Object) null);
        }
    }
}
