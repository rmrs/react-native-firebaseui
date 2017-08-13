package io.rumors.reactnativefirebaseui.storage;

import android.widget.ImageView;
import com.github.chrisbanes.photoview.PhotoView;
import com.facebook.react.uimanager.ThemedReactContext;

public class FirebasePhotoViewManager extends ImageViewManager {
  public static final String REACT_CLASS = "RCTFirebasePhotoView";

  @Override
  public String getName() {
    return REACT_CLASS;
  }

  @Override
  public ImageView createViewInstance(ThemedReactContext context) {
    mContext = context;
    return new PhotoView(context);
  }
}
