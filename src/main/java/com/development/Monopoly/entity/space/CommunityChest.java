package com.development.Monopoly.entity.space;

public class CommunityChest extends Space{
    public CommunityChest(int id, String name){
        super(id, name);
    }

    @Override
    public String getInfo() {
        String nameRow = "<div class=\"row\"><div class=\"col\">Tên:</div><div class=\"col\">"+ this.name +"</div></div>";
        String action = "<div class=\"row\"><div class=\"col\"><button onclick=\"takeChest()\">Rút</button></div><div class=\"col\">"+ this.name +"</div></div>";
        return nameRow + action;
    }
}
