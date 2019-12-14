package io.rumors.reactnativefirebaseui.storage;

import javax.annotation.Nullable;
import android.widget.ImageView.ScaleType;

import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;

public class FirebaseImageViewManager extends SimpleViewManager<ExtendedImageView> {
  public static final String REACT_CLASS = "RCTFirebaseImageView";

  @Override
  public String getName() {
    return REACT_CLASS;
  }

  @Override
  public ExtendedImageView createViewInstance(ThemedReactContext context) {
    return new ExtendedImageView(context);
  }

  @ReactProp(name = "path")
  public void setPath(ExtendedImageView imageView, @Nullable String path) {
    imageView.setPath(path);
  }

  @ReactProp(name = "defaultSource")
  public void setDefaultSource(ExtendedImageView imageView, @Nullable String source) {
    imageView.setDefaultSource(source);
  }

  @ReactProp(name = "timestamp")
  public void setTimestamp(ExtendedImageView imageView, @Nullable double timestamp) {
    imageView.setTimestamp((long)timestamp);
  }

  @ReactProp(name = ViewProps.RESIZE_MODE)
  public void setResizeMode(ExtendedImageView imageView, @Nullable String resizeMode) {
    ScaleType scaleType = ScaleType.CENTER_INSIDE;
    if ("cover".equals(resizeMode)) {
      scaleType = ScaleType.CENTER_CROP;
    } else if ("contain".equals(resizeMode)) {
      scaleType = ScaleType.CENTER_INSIDE;
    } else if ("stretch".equals(resizeMode)) {
      scaleType = ScaleType.FIT_XY;
    } else if ("center".equals(resizeMode)) {
      scaleType = ScaleType.CENTER;
    }
    imageView.setScaleType(scaleType);
  }

  @Override
  protected void onAfterUpdateTransaction(ExtendedImageView imageView) {
    super.onAfterUpdateTransaction(imageView);
    imageView.updateView();
  }
}
