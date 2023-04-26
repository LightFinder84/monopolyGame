package com.development.Monopoly.entity.space;

public class CarPark extends Space{
    public CarPark(int id, String name){
        super(id, name);
    }
    
    @Override
    public String getInfo() {
        String nameRow = "<div class=\"row\"><div class=\"col\">Tên:</div><div class=\"col\">"+ this.name +"</div></div>";
        return nameRow;
    }
}
