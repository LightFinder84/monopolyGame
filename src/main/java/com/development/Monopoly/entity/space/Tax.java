package com.development.Monopoly.entity.space;

public class Tax extends Space{
    public Tax(int id, String name){
        super(id, name);
    }
    
    public int getTaxFee(int userMoney){
        return userMoney / 10;
    }

    @Override
    public String getInfo() {
        String nameRow = "<div class=\"w-100 d-flex justify-content-center\"><p class=\"\">"+ this.name +"</p></div>";
        String infoRow = "<div class=\"row\"><div class=\"col\">Đóng thuế 10% số tiền hiện hiện có</div>";
        return nameRow + infoRow;
    }
}
