package inventorySystem.Models;

public class Outsourced extends Part {
    private String companyName;

    public void setCompanyName(String companyName){
        this.companyName = companyName;
    }
    public String getCompanyName(){
        return companyName;
    }

}
