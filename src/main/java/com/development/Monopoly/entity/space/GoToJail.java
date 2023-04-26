package com.development.Monopoly.entity.space;

public class GoToJail extends Space{
    public GoToJail(int id, String name){
        super(id, name);
    }

    @Override
    public String getInfo() {
        String nameRow = "<div class=\"row\"><div class=\"col\">Tên:</div><div class=\"col\">"+ this.name +"</div></div>";
        return nameRow;
    }
}
