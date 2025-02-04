package com.example.careerguidancesystem;

// VideoModel.java
public class VideoModel {
    private String thumbnailUrl;
    private String videoUrl;

    public VideoModel(String thumbnailUrl, String videoUrl) {
        this.thumbnailUrl = thumbnailUrl;
        this.videoUrl = videoUrl;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }
}

