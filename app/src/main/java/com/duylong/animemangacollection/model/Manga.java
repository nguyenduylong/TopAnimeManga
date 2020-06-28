package com.duylong.animemangacollection.model;

public class Manga extends BaseObject{

    public Manga(int id, String title) {
        super(id, title);
        this.setType(2);
    }

}
