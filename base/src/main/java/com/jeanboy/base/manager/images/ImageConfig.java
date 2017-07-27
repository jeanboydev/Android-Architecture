package com.jeanboy.base.manager.images;

/**
 * Created by jeanboy on 2016/12/14.
 */

public class ImageConfig {

    private int maxWidth;
    private int maxHeight;
    private int placeholder;
    private int error;

    public ImageConfig setSize(int maxWidth, int maxHeight) {
        this.maxWidth = maxWidth;
        this.maxHeight = maxHeight;
        return this;
    }

    public ImageConfig setPlaceholder(int placeholder) {
        this.placeholder = placeholder;
        return this;
    }

    public ImageConfig setError(int error) {
        this.error = error;
        return this;
    }

    public int getMaxWidth() {
        return maxWidth;
    }

    public int getMaxHeight() {
        return maxHeight;
    }

    public int getPlaceholder() {
        return placeholder;
    }

    public int getError() {
        return error;
    }
}
