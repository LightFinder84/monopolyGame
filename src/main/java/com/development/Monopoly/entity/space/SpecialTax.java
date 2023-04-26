package com.development.Monopoly.entity.space;

public class SpecialTax extends Space{
    public SpecialTax(int id, String name){
        super(id, name);
    }

    public int getTaxFee(){
        return 100;
    }

    @Override
    public String getInfo() {
        String nameRow = "<div class=\"row\"><div class=\"col\">Tên:</div><div class=\"col\">"+ this.name +"</div></div>";
        String infoRow = "<div class=\"row\"><div class=\"col\">Trả $100</div>";
        return nameRow + infoRow;
    }
}
