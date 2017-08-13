package io.rumors.reactnativefirebaseui.storage;

import javax.annotation.Nullable;

import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.FirebaseStorage;
import com.bumptech.glide.Glide;

import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;

public abstract class ImageViewManager extends SimpleViewManager<ImageView>{
  protected ThemedReactContext mContext = null;

  @ReactProp(name = "path")
  public void setPath(ImageView imageView, @Nullable String path) {
    StorageReference storageReference = FirebaseStorage.getInstance().getReference(path);
    Glide.with(mContext)
        .using(new FirebaseImageLoader())
        .load(storageReference)
        .into(imageView);
  }

  @ReactProp(name = ViewProps.RESIZE_MODE)
  public void setResizeMode(ImageView view, @Nullable String resizeMode) {
    ScaleType scaleType = ScaleType.CENTER;
    if ("cover".equals(resizeMode)) {
      scaleType = ScaleType.CENTER_CROP;
    } else if ("contain".equals(resizeMode)) {
      scaleType = ScaleType.CENTER_INSIDE;
    } else if ("stretch".equals(resizeMode)) {
      scaleType = ScaleType.FIT_XY;
    }
    view.setScaleType(scaleType);
  }
}
