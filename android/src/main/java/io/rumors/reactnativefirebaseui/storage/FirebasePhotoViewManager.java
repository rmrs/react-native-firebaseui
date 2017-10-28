package io.rumors.reactnativefirebaseui.storage;

import javax.annotation.Nullable;

import android.widget.ImageView.ScaleType;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.annotations.ReactPropGroup;

import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.yoga.YogaConstants;
import com.facebook.react.uimanager.PixelUtil;

public class FirebasePhotoViewManager extends SimpleViewManager<ExtendedPhotoView> {
  public static final String REACT_CLASS = "RCTFirebasePhotoView";

  @Override
  public String getName() {
    return REACT_CLASS;
  }

  @Override
  public ExtendedPhotoView createViewInstance(ThemedReactContext context) {
    return new ExtendedPhotoView(context);
  }

  @ReactProp(name = "path")
  public void setPath(ExtendedPhotoView imageView, @Nullable String path) {
    imageView.setPath(path);
  }

  @ReactProp(name = "timestamp")
  public void setTimestamp(ExtendedImageView imageView, @Nullable double timestamp) {
    imageView.setTimestamp((long)timestamp);
  }

  @ReactProp(name = ViewProps.RESIZE_MODE)
  public void setResizeMode(ExtendedPhotoView imageView, @Nullable String resizeMode) {
    ScaleType scaleType = ScaleType.CENTER_INSIDE;
    if ("cover".equals(resizeMode)) {
      scaleType = ScaleType.CENTER_CROP;
    } else if ("contain".equals(resizeMode)) {
      scaleType = ScaleType.CENTER_INSIDE;
    }

    imageView.setScaleType(scaleType);
  }

  @ReactPropGroup(names = {
      ViewProps.BORDER_RADIUS,
      ViewProps.BORDER_TOP_LEFT_RADIUS,
      ViewProps.BORDER_TOP_RIGHT_RADIUS,
      ViewProps.BORDER_BOTTOM_RIGHT_RADIUS,
      ViewProps.BORDER_BOTTOM_LEFT_RADIUS
  }, defaultFloat = YogaConstants.UNDEFINED)
  public void setBorderRadius(ExtendedPhotoView imageView, int index, float borderRadius) {
    if (!YogaConstants.isUndefined(borderRadius)) {
      borderRadius = PixelUtil.toPixelFromDIP(borderRadius);
    }

    RoundedCornersTransformation.CornerType cornerType = RoundedCornersTransformation.CornerType.ALL;
    switch (index) {
      case 0:
        cornerType = RoundedCornersTransformation.CornerType.ALL;
        break;
      case 1:
        cornerType = RoundedCornersTransformation.CornerType.TOP_LEFT;
        break;
      case 2:
        cornerType = RoundedCornersTransformation.CornerType.TOP_RIGHT;
        break;
      case 3:
        cornerType = RoundedCornersTransformation.CornerType.BOTTOM_RIGHT;
        break;
      case 4:
        cornerType = RoundedCornersTransformation.CornerType.BOTTOM_LEFT;
        break;
    }

    imageView.setBorderRadius(Math.round(borderRadius), cornerType);
  }

  @Override
  protected void onAfterUpdateTransaction(ExtendedPhotoView imageView) {
    super.onAfterUpdateTransaction(imageView);
    imageView.updateView();
  }
}
