package rob.myappcompany.webmenagementapp;


public class StrucktDB {
    public String id;
    public String name;
    public String Title;
    public String description;
    public String address;
    public String andere;
    //insert(String nameIn, String titleIn, String addressIn, String descriptionIn, String andere)
    public StrucktDB(String idIn, String nameIn, String TitleIn, String addressIn, String descriptionIn, String andereIn){
        this.id=idIn;
        this.name=nameIn;
        this.Title=TitleIn;
        this.description=descriptionIn;
        this.address=addressIn;
        this.andere=andereIn;
    }
}
