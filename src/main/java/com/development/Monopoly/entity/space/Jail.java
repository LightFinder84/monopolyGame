package com.development.Monopoly.entity.space;

import java.util.ArrayList;
import java.util.List;

import com.development.Monopoly.entity.Player;

public class Jail extends Space{
    private List<Player> prisonerList;    
    
    public Jail(int id, String name){
        super(id, name);
        prisonerList = new ArrayList<>();
    }

    @Override
    public String getInfo() {
        String nameRow = "<div class=\"row\"><div class=\"col\">Tên:</div><div class=\"col\">"+ this.name +"</div></div>";
        String inprisions = "";
        for (Player prisoner : prisonerList) {
            inprisions += prisoner.getName() + " - ";
        }
        String inPrisonRow = "<div class=\"row\"><div class=\"col\">Tên:</div><div class=\"col\">"+ inprisions +"</div></div>";

        String visitorsName = "";
        for (Player visitor : vistors) {
            visitorsName += visitor.getName() + " - ";
        }
        String visitorRow = "<div class=\"row\"><div class=\"col\">Tên:</div><div class=\"col\">"+ visitorsName +"</div></div>";
        
        return nameRow + inPrisonRow + visitorRow;
    }
}
