package io.rumors.reactnativefirebaseui.storage;

import java.util.Map;
import java.util.Map.Entry;
import java.util.HashMap;
import java.util.ArrayList;

import com.github.chrisbanes.photoview.PhotoView;
import android.widget.ImageView.ScaleType;

import com.facebook.react.uimanager.ThemedReactContext;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.FirebaseStorage;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.resource.bitmap.FitCenter;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.signature.MediaStoreSignature;
import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation.CornerType;


public class ExtendedPhotoView extends PhotoView {
  protected String mPath = null;
  protected Map<CornerType, Integer> mBorderRadii = new HashMap<CornerType, Integer>();
  protected ScaleType mScaleType;
  protected long mTimestamp = 0;

  protected ThemedReactContext mContext = null;

  public ExtendedPhotoView(ThemedReactContext context) {
    super(context);
    mContext = context;
  }

  public void setPath(String path) {
    mPath = path;
  }

  public void setTimestamp(long timestamp) {
    mTimestamp = timestamp;
  }

  @Override
  public void setScaleType(ScaleType scaleType) {
    mScaleType = scaleType;
  }

  public void setBorderRadius(int borderRadius, CornerType cornerType) {
    mBorderRadii.put(cornerType, borderRadius);
  }

  public void updateView() {
    StorageReference storageReference = FirebaseStorage.getInstance().getReference(mPath);

    ArrayList<Transformation> transformations = new ArrayList<Transformation>(1 + mBorderRadii.size());

    if (mScaleType == ScaleType.CENTER_CROP) {
      transformations.add(new CenterCrop());
    } else {
      transformations.add(new FitCenter());
    }

    for (Entry<CornerType, Integer> entry : mBorderRadii.entrySet()) {
      CornerType cornerType = entry.getKey();
      Integer radius = entry.getValue();
      transformations.add(new RoundedCornersTransformation(radius, 0, cornerType));
    }


    Transformation[] transformationsArray = transformations.toArray(new Transformation[transformations.size()]);
    MultiTransformation multi = new MultiTransformation<>(transformationsArray);

    GlideApp.with(mContext)
            .load(storageReference)
            .apply(bitmapTransform(multi))
            //(String mimeType, long dateModified, int orientation)
            .signature(new MediaStoreSignature("", mTimestamp, 0))
            .into(this);
  }
}
