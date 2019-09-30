package bindlish.com.piczilla.presenter;

import java.util.List;

import bindlish.com.piczilla.model.Photo;

/**
 * Created by Aman Bindlish on 14,September,2019
 */
public interface UrlsListListener {

    void updateImages(List<Photo> photoList);
}
