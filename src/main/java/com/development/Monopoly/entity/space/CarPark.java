package com.development.Monopoly.entity.space;

public class CarPark extends Space{
    public CarPark(int id, String name){
        super(id, name);
    }
    
    @Override
    public String getInfo() {
        String nameRow = "<div class=\"w-100 d-flex justify-content-center\"><p class=\"\">"+ this.name +"</p></div>";
        return nameRow;
    }
}
