package bindlish.com.piczilla.presenter;

import android.graphics.Bitmap;

import java.util.List;

import bindlish.com.piczilla.model.Photo;
import bindlish.com.piczilla.utils.ImagesCache;
import bindlish.com.piczilla.utils.Utility;
import bindlish.com.piczilla.view.MainActivityView;

/**
 * Created by Aman Bindlish on 14,September,2019
 */
public class ImagesPresenter implements UrlsListListener, ImageFetchListener {

    private ImagesCache cache;
    private MainActivityView activityView;

    public ImagesPresenter(MainActivityView activityView) {
        this.activityView = activityView;
        cache = ImagesCache.getInstance();
        cache.initializeCache();
    }

    public void fetchImagesUrl(int pageCount) {
        // fetch images list from api
        if (activityView != null) {
            activityView.showLoader();
        }
        FetchImageUrlsTask fetchImagesTask = new FetchImageUrlsTask(this);
        Utility.execute(fetchImagesTask, String.valueOf(pageCount));
    }

    public void fetchBitmap(String imgUrl) {
        Bitmap bm = cache.getImageFromWarehouse(imgUrl);
        if (bm != null) {
            activityView.updateBitmap(bm);
        } else {
            if (activityView != null) {
                activityView.showLoader();
            }
            DownloadImageTask imgTask = new DownloadImageTask(cache, this);
            Utility.execute(imgTask, imgUrl);
        }
    }

    @Override
    public void updateImages(List<Photo> photoList) {
        if (activityView != null) {
            activityView.hideLoader();
            activityView.updateImageUrls(photoList);
        }
    }

    @Override
    public void updateImageBitmap(Bitmap bitmap) {
        if (bitmap != null && activityView != null) {
            activityView.hideLoader();
            activityView.updateBitmap(bitmap);
        }
    }

    public void clearCache() {
        cache.clearCache();
    }
}
