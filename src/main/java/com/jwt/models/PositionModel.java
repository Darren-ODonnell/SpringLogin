package com.jwt.models;

public class PositionModel {


    private String name;

    public String getAbbrev() {
        return abbrev;
    }

    public void setAbbrev(String abbrev) {
        this.abbrev = abbrev;
    }

    private String abbrev;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


    public Position translateModelToPosition(){
        Position position = new Position();

        position.setName(this.name);
        position.setAbbrev(this.abbrev);

        return position;
    }

    public Position translateModelToPosition(Long id){
        Position position = translateModelToPosition();

        position.setId(id);

        return position;
    }

}