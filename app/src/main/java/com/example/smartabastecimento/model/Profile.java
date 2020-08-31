package com.example.smartabastecimento.model;

public class Profile {

    private String      nameMenu;
    private String      descriptionMenu;
    private int         iconMenu;

    public Profile () {

    }

    public Profile ( String nameMenu, String descriptionMenu, int iconMenu) {
        this.nameMenu           = nameMenu;
        this.descriptionMenu    = descriptionMenu;
        this.iconMenu           = iconMenu;
    }

    public String getNameMenu() {
        return nameMenu;
    }

    public void setNameMenu(String nameMenu) {
        this.nameMenu = nameMenu;
    }

    public String getDescriptionMenu() {
        return descriptionMenu;
    }

    public void setDescriptionMenu(String descriptionMenu) {
        this.descriptionMenu = descriptionMenu;
    }

    public int getIconMenu() {
        return iconMenu;
    }

    public void setIconMenu(int iconMenu) {
        this.iconMenu = iconMenu;
    }
}
