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
        String nameRow = "<div class=\"w-100 d-flex justify-content-center\"><p class=\"\">"+ this.name +"</p></div>";
        String inprisions = "";
        for (Player prisoner : prisonerList) {
            inprisions += prisoner.getName() + " - ";
        }
        String inPrisonRow = "<div class=\"row\"><div class=\"col\">Tên:</div><div class=\"col\">"+ inprisions +"</div></div>";
        
        return nameRow + inPrisonRow;
    }
}
