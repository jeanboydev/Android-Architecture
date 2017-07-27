package com.jeanboy.base.manager.images;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import java.io.File;

/**
 * Created by jeanboy on 2016/12/14.
 */

public interface ImageHandler {

    void init(Context context);

    void load(ImageView imageView, String url);

    void load(ImageView imageView, File file);

    void load(ImageView imageView, Uri uri);

    void load(ImageView imageView, String url, ImageConfig config);

    void load(ImageView imageView, File file, ImageConfig config);

    void load(ImageView imageView, Uri uri, ImageConfig config);

}
