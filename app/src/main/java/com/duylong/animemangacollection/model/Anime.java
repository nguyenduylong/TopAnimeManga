package com.duylong.animemangacollection.model;

public class Anime {
    private int id;
    private int rank;
    private String title;
    private String url;
    private String imageUrl;
    private int episodeCounts;
    private String startDate;
    private String endDate;
    private double score;

    public Anime(int id, String title) {
        this.id = id;
        this.title = title;
        this.rank = 0;
        this.url = "";
        this.imageUrl = "";
        this.episodeCounts = 0;
        this.startDate = "";
        this.endDate = "";
        this.score = 0.0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getEpisodeCounts() {
        return episodeCounts;
    }

    public void setEpisodeCounts(int episodeCounts) {
        this.episodeCounts = episodeCounts;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getShortTitle() {
        if (this.title.length() > 15)
        {
            return this.title.substring(0,13) + "...";
        }
        return this.title;
    }
}
