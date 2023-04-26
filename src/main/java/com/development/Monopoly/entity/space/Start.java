package com.development.Monopoly.entity.space;

public class Start extends Space{
    public Start(int id, String name){
        super(id, name);
    }

    @Override
    public String getInfo() {
        String nameRow = "<div class=\"w-100 d-flex justify-content-center\"><p class=\"\">"+ this.name +"</p></div>";
        String infoRow = "<div class=\"row\"><div class=\"col\">Đi qua được lãnh $200</div>";
        return nameRow + infoRow;
    }
}
