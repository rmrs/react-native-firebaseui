package io.rumors.reactnativefirebaseui.storage;

import com.bumptech.glide.load.Transformation;
import com.github.chrisbanes.photoview.PhotoView;
import android.widget.ImageView.ScaleType;

import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.FirebaseStorage;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.FitCenter;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;

import com.facebook.react.uimanager.ThemedReactContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class ExtendedPhotoView extends PhotoView {
  protected String mPath = null;
  protected Map<RoundedCornersTransformation.CornerType, Integer> mBorderRadii = null;
  protected ScaleType mScaleType;

  protected ThemedReactContext mContext = null;

  public ExtendedPhotoView(ThemedReactContext context) {
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
    if (mBorderRadii == null ) {
      mBorderRadii = new HashMap<RoundedCornersTransformation.CornerType, Integer>();
    }
    mBorderRadii.put(cornerType, borderRadius);
  }

  public void updateView() {
    StorageReference storageReference = FirebaseStorage.getInstance().getReference(mPath);
    FirebaseImageLoader imageLoader = new FirebaseImageLoader();

    if (mBorderRadii != null) {
      ArrayList<Transformation> transformations = new ArrayList<>();
      for (RoundedCornersTransformation.CornerType cornerType : mBorderRadii.keySet()) {
        transformations.add(new RoundedCornersTransformation(mContext, mBorderRadii.get(cornerType), 0, cornerType));
      }
      Transformation[] transformationsArray;

      if (mScaleType == ScaleType.CENTER_CROP) {
        transformations.add(0, new CenterCrop(mContext));
        transformationsArray = transformations.toArray(new Transformation[transformations.size()]);

        Glide.with(mContext).using(imageLoader)
        .load(storageReference)
        .bitmapTransform(transformationsArray)
        .into(this);
      } else {
        transformations.add(0, new FitCenter(mContext));
        transformationsArray = transformations.toArray(new Transformation[transformations.size()]);

        Glide.with(mContext).using(imageLoader)
        .load(storageReference)
        .bitmapTransform(transformationsArray)
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
