package bindlish.com.piczilla.presenter;

import android.graphics.Bitmap;
import android.os.AsyncTask;

import bindlish.com.piczilla.utils.ImagesCache;
import bindlish.com.piczilla.utils.Utility;

/**
 * Created by Aman Bindlish on 14,September,2019
 */
public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {

    private String imageUrl;
    private ImagesCache cache;
    private ImageFetchListener listener;

    public DownloadImageTask(ImagesCache cache, ImageFetchListener listener) {
        this.cache = cache;
        this.listener = listener;
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        imageUrl = params[0];
        return Utility.getBitmapFromURL(imageUrl);
    }

    @Override
    protected void onPostExecute(Bitmap result) {
        super.onPostExecute(result);
        if (result != null) {
            cache.addImageToWarehouse(imageUrl, result);
            if (listener != null) {
                listener.updateImageBitmap(result);
            }
        }
    }
}