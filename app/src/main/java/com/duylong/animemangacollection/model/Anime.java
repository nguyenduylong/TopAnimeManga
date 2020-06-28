package com.duylong.animemangacollection.model;

public class Anime extends BaseObject {
    private int episodeCounts;

    public Anime(int id, String title) {
        super(id, title);
        this.episodeCounts = 0;
        this.setType(1);
    }

    public int getEpisodeCounts() {
        return episodeCounts;
    }

    public void setEpisodeCounts(int episodeCounts) {
        this.episodeCounts = episodeCounts;
    }
}
