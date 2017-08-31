package io.rumors.reactnativefirebaseui.storage;

import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.FirebaseStorage;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.FitCenter;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;

import com.facebook.react.uimanager.ThemedReactContext;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class ExtendedImageView extends ImageView {
  protected String mPath = null;
  protected int mBorderRadius = 0;
  protected RoundedCornersTransformation.CornerType mCornerType;
  protected ScaleType mScaleType;

  protected ThemedReactContext mContext = null;

  public ExtendedImageView(ThemedReactContext context) {
    super(context);
    mContext = context;
  }

  public void setPath(String path) {
    mPath = path;
  }

  @Override
  public void setScaleType(ScaleType scaleType) {
    mScaleType = scaleType;
  }

  public void setBorderRadius(int borderRadius, RoundedCornersTransformation.CornerType cornerType) {
    mBorderRadius = borderRadius;
    mCornerType = cornerType;
  }

  public void updateView() {
    StorageReference storageReference = FirebaseStorage.getInstance().getReference(mPath);
    FirebaseImageLoader imageLoader = new FirebaseImageLoader();
    if (mBorderRadius != 0) {
      //ctor is (context, radius, margin, cornerType)
      RoundedCornersTransformation transformation =
        new RoundedCornersTransformation(mContext, mBorderRadius, 0, mCornerType);

      if (mScaleType == ScaleType.CENTER_CROP) {
        Glide.with(mContext).using(imageLoader)
        .load(storageReference)
        .bitmapTransform(new CenterCrop(mContext), transformation)
        .into(this);
      } else {
        Glide.with(mContext).using(imageLoader)
        .load(storageReference)
        .bitmapTransform(new FitCenter(mContext), transformation)
        .into(this);
      }
    } else {
      if (mScaleType == ScaleType.CENTER_CROP) {
        Glide.with(mContext).using(imageLoader)
        .load(storageReference)
        .centerCrop()
        .into(this);
      } else {
        Glide.with(mContext).using(imageLoader)
        .load(storageReference)
        .fitCenter()
        .into(this);
      }
    }
  }
}
