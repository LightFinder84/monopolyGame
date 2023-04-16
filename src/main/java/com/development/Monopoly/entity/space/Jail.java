package com.development.Monopoly.entity.space;

public class Jail extends Space{
    private String prisonerName;
    private String visitorName;

    public String getPrisonerName() {
        return prisonerName;
    }
    public String getVisitorName() {
        return visitorName;
    }
    public void setPrisonerName(String prisonerName) {
        this.prisonerName = prisonerName;
    }
    public void setVisitorName(String visitorName) {
        this.visitorName = visitorName;
    }
    
    public Jail(int id, String name, String prisonerName, String visitorName){
        super(id, name);
        this.prisonerName = prisonerName;
        this.visitorName = visitorName;
    }
}
