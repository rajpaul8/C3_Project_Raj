import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Restaurant {

    private String name;
    private String location;
    public LocalTime openingTime;
    public LocalTime closingTime;
    private List<Item> menu = new ArrayList<Item>();
    private List<Item> selectedMenu = new ArrayList<Item>();

    public Restaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        this.name = name;
        this.location = location;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    public boolean isRestaurantOpen() {
        //Implemented RestaurantOpen/Close_Status method
        if (this.getCurrentTime().isBefore(closingTime) && this.getCurrentTime().isAfter(openingTime)){
            return true;
        }else {
            return false;
        }
    }

    public LocalTime getCurrentTime(){ return  LocalTime.now(); }

    public List<Item> getMenu() {
        //Implemented getMenu method
        return menu;
    }

    private Item findItemByName(String itemName){
        for(Item item: menu) {
            if(item.getName().equals(itemName))
                return item;
        }
        return null;
    }

    public void addToMenu(String name, int price) {
        Item newItem = new Item(name,price);
        menu.add(newItem);
    }
    
    public void removeFromMenu(String itemName) throws itemNotFoundException {

        Item itemToBeRemoved = findItemByName(itemName);
        if (itemToBeRemoved == null)
            throw new itemNotFoundException(itemName);
        menu.remove(itemToBeRemoved);
    }
    public void displayDetails(){
        System.out.println("Restaurant:"+ name + "\n"
                +"Location:"+ location + "\n"
                +"Opening time:"+ openingTime +"\n"
                +"Closing time:"+ closingTime +"\n"
                +"Menu:"+"\n"+getMenu());
    }
    public String getName() {
        return name;
    }

    public void IncludeSelectItem(String itemName){
        Item itemToBeSelected = findItemByName(itemName);
        selectedMenu.add(itemToBeSelected);
    }


    public void RemoveSelectItem(String itemName){
        Item deSelected = findItemByName(itemName);
        selectedMenu.remove(deSelected);
    }

    public Double displayOrderTotal(){
        double sum = 0;
        for(Item item:selectedMenu){
            sum += item.getPrice();
        }
        return sum;

    }

    public List<Item> selectedMenu() {
        return selectedMenu;
    }
}
