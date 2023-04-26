package com.development.Monopoly.entity.space;

public class Chance extends Space{
    public Chance(int id, String name){
        super(id, name);
    }

    @Override
    public String getInfo() {
        String nameRow = "<div class=\"row\"><div class=\"col\">Tên:</div><div class=\"col\">"+ this.name +"</div></div>";
        String action = "<div class=\"row\"><div class=\"col\"><button onclick=\"takeChance()\">Rút</button></div><div class=\"col\">"+ this.name +"</div></div>";
        return nameRow + action;
    }
}
