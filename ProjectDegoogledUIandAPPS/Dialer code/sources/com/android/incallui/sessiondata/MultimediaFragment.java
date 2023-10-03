package com.android.incallui.sessiondata;

import android.graphics.drawable.Drawable;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.dialer.R;
import com.android.dialer.common.FragmentUtils;
import com.android.dialer.common.LogUtil;
import com.android.dialer.multimedia.MultimediaData;
import com.android.dialer.theme.base.ThemeComponent;
import com.android.dialer.theme.base.impl.AospThemeImpl;
import com.android.incallui.answer.impl.AnswerFragment;
import com.android.incallui.maps.MapsComponent;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory;

public class MultimediaFragment extends Fragment implements AvatarPresenter {
    private ImageView avatarImageView;
    private boolean isSpam;
    private boolean showAvatar;

    public interface Holder {
    }

    public static MultimediaFragment newInstance(MultimediaData multimediaData, boolean z, boolean z2, boolean z3) {
        return newInstance(multimediaData.getText(), multimediaData.getImageUri(), multimediaData.getLocation(), z, z2, z3);
    }

    public ImageView getAvatarImageView() {
        return this.avatarImageView;
    }

    public int getAvatarSize() {
        return getResources().getDimensionPixelSize(R.dimen.answer_message_avatar_size);
    }

    public Uri getImageUri() {
        return (Uri) getArguments().getParcelable("image");
    }

    public Location getLocation() {
        return (Location) getArguments().getParcelable("location");
    }

    public String getSubject() {
        return getArguments().getString("subject");
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.showAvatar = getArguments().getBoolean("show_avatar");
        this.isSpam = getArguments().getBoolean("is_spam");
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        LayoutInflater cloneInContext = layoutInflater.cloneInContext(((AospThemeImpl) ThemeComponent.get(getContext()).theme()).getThemedContext(getContext()));
        if (this.isSpam) {
            LogUtil.m9i("MultimediaFragment.onCreateView", "show spam layout", new Object[0]);
            return cloneInContext.inflate(R.layout.fragment_spam, viewGroup, false);
        }
        boolean z = true;
        boolean z2 = getImageUri() != null;
        boolean z3 = !TextUtils.isEmpty(getSubject());
        if (getLocation() == null) {
            z = false;
        }
        if (z) {
            MapsComponent.get(getContext()).getMaps().isAvailable();
        }
        if (!z2) {
            LogUtil.m9i("MultimediaFragment.onCreateView", "show text layout", new Object[0]);
            return cloneInContext.inflate(R.layout.fragment_composer_text, viewGroup, false);
        } else if (z3) {
            LogUtil.m9i("MultimediaFragment.onCreateView", "show text, image layout", new Object[0]);
            return cloneInContext.inflate(R.layout.fragment_composer_text_image, viewGroup, false);
        } else {
            LogUtil.m9i("MultimediaFragment.onCreateView", "show image layout", new Object[0]);
            return cloneInContext.inflate(R.layout.fragment_composer_image, viewGroup, false);
        }
    }

    public void onViewCreated(final View view, Bundle bundle) {
        View findViewById = view.findViewById(R.id.answer_message_container);
        if (findViewById != null) {
            findViewById.setClipToOutline(true);
        }
        if (this.isSpam && getLocation() == null && getImageUri() == null && !TextUtils.isEmpty(getSubject())) {
            ((ImageView) view.findViewById(R.id.spam_image)).setImageResource(R.drawable.quantum_ic_message_white_24);
            ((TextView) view.findViewById(R.id.spam_text)).setText(R.string.spam_message_text);
        }
        TextView textView = (TextView) view.findViewById(R.id.answer_message_text);
        if (textView != null) {
            textView.setText(getSubject());
        }
        ImageView imageView = (ImageView) view.findViewById(R.id.answer_message_image);
        if (imageView != null) {
            RequestBuilder<Drawable> load = Glide.with((Fragment) this).load(getImageUri());
            DrawableTransitionOptions drawableTransitionOptions = new DrawableTransitionOptions();
            drawableTransitionOptions.transition(new DrawableCrossFadeFactory.Builder().build());
            load.transition(drawableTransitionOptions).listener(new RequestListener<Drawable>(this) {
                public boolean onLoadFailed(GlideException glideException, Object obj, Target<Drawable> target, boolean z) {
                    view.findViewById(R.id.loading_spinner).setVisibility(8);
                    LogUtil.m7e("MultimediaFragment.onLoadFailed", (String) null, (Throwable) glideException);
                    return false;
                }

                public boolean onResourceReady(Object obj, Object obj2, Target target, DataSource dataSource, boolean z) {
                    Drawable drawable = (Drawable) obj;
                    LogUtil.enterBlock("MultimediaFragment.onResourceReady");
                    view.findViewById(R.id.loading_spinner).setVisibility(8);
                    return false;
                }
            }).into(imageView);
            imageView.setClipToOutline(true);
        }
        FrameLayout frameLayout = (FrameLayout) view.findViewById(R.id.answer_message_frag);
        if (frameLayout == null) {
            this.avatarImageView = (ImageView) view.findViewById(R.id.answer_message_avatar);
            ImageView imageView2 = this.avatarImageView;
            if (imageView2 != null) {
                imageView2.setVisibility(this.showAvatar ? 0 : 8);
            }
            Holder holder = (Holder) FragmentUtils.getParent((Fragment) this, Holder.class);
            if (holder != null) {
                ((AnswerFragment) holder).updateAvatar(this);
                return;
            }
            return;
        }
        frameLayout.setClipToOutline(true);
        MapsComponent.get(getContext()).getMaps().createStaticMapFragment(getLocation());
        throw null;
    }

    public boolean shouldShowAnonymousAvatar() {
        return this.showAvatar;
    }

    public static MultimediaFragment newInstance(String str, Uri uri, Location location, boolean z, boolean z2, boolean z3) {
        Bundle bundle = new Bundle();
        bundle.putString("subject", str);
        bundle.putParcelable("image", uri);
        bundle.putParcelable("location", location);
        bundle.putBoolean("interactive", z);
        bundle.putBoolean("show_avatar", z2);
        bundle.putBoolean("is_spam", z3);
        MultimediaFragment multimediaFragment = new MultimediaFragment();
        multimediaFragment.setArguments(bundle);
        return multimediaFragment;
    }
}
