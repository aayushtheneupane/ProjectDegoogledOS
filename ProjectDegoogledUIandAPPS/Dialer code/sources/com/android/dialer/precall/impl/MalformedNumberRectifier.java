package com.android.dialer.precall.impl;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import com.android.dialer.callintent.CallIntentBuilder;
import com.android.dialer.precall.PreCallAction;
import com.android.dialer.precall.PreCallCoordinator;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;

public class MalformedNumberRectifier implements PreCallAction {
    private final ImmutableList<MalformedNumberHandler> handlers;

    public interface MalformedNumberHandler {
    }

    MalformedNumberRectifier(ImmutableList<MalformedNumberHandler> immutableList) {
        this.handlers = immutableList;
    }

    public void onDiscard() {
    }

    public boolean requiresUi(Context context, CallIntentBuilder callIntentBuilder) {
        return false;
    }

    public void runWithUi(PreCallCoordinator preCallCoordinator) {
        PreCallCoordinatorImpl preCallCoordinatorImpl = (PreCallCoordinatorImpl) preCallCoordinator;
        Activity activity = preCallCoordinatorImpl.getActivity();
        CallIntentBuilder builder = preCallCoordinatorImpl.getBuilder();
        if ("tel".equals(builder.getUri().getScheme())) {
            String schemeSpecificPart = builder.getUri().getSchemeSpecificPart();
            UnmodifiableIterator<MalformedNumberHandler> it = this.handlers.iterator();
            while (it.hasNext()) {
                Optional<String> handle = ((UkRegionPrefixInInternationalFormatHandler) it.next()).handle(activity, schemeSpecificPart);
                if (handle.isPresent()) {
                    builder.setUri(Uri.fromParts("tel", handle.get(), (String) null));
                    return;
                }
            }
        }
    }

    public void runWithoutUi(Context context, CallIntentBuilder callIntentBuilder) {
        if ("tel".equals(callIntentBuilder.getUri().getScheme())) {
            String schemeSpecificPart = callIntentBuilder.getUri().getSchemeSpecificPart();
            UnmodifiableIterator<MalformedNumberHandler> it = this.handlers.iterator();
            while (it.hasNext()) {
                Optional<String> handle = ((UkRegionPrefixInInternationalFormatHandler) it.next()).handle(context, schemeSpecificPart);
                if (handle.isPresent()) {
                    callIntentBuilder.setUri(Uri.fromParts("tel", handle.get(), (String) null));
                    return;
                }
            }
        }
    }
}
