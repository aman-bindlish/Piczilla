package bindlish.com.piczilla.view;

import android.graphics.Bitmap;

import java.util.List;

import bindlish.com.piczilla.model.Photo;

/**
 * Created by Aman Bindlish on 14,September,2019
 */
public interface MainActivityView {

    void updateImageUrls(List<Photo> photoList);

    void updateBitmap(Bitmap bitmap);

    void showLoader();

    void hideLoader();
}
