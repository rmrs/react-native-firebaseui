package io.rumors.reactnativefirebaseui.storage;

import javax.annotation.Nullable;

import android.widget.ImageView;
import android.graphics.drawable.Drawable;

import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.views.imagehelper.ResourceDrawableIdHelper;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.FirebaseStorage;
import com.bumptech.glide.Glide;
import com.bumptech.glide.signature.MediaStoreSignature;

public class ExtendedImageView extends ImageView {
  protected String mPath = null;
  protected @Nullable Drawable mDefaultImageDrawable;
  protected long mTimestamp = 0;

  protected ThemedReactContext mContext = null;

  public ExtendedImageView(ThemedReactContext context) {
    super(context);
    mContext = context;
  }

  public void setPath(String path) {
    mPath = path;
  }

  public void setDefaultSource(String name) {
    mDefaultImageDrawable = ResourceDrawableIdHelper.getInstance().getResourceDrawable(getContext(), name);
  }

  public void setTimestamp(long timestamp) {
    mTimestamp = timestamp;
  }

  public void updateView() {
    StorageReference storageReference = FirebaseStorage.getInstance().getReference(mPath);
    GlideApp.with(mContext)
            .load(storageReference)
            .placeholder(mDefaultImageDrawable)
            .dontTransform()
            //(String mimeType, long dateModified, int orientation)
            .signature(new MediaStoreSignature("", mTimestamp, 0))
            .into(this);
  }
}
