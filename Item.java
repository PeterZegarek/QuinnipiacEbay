public class Item {
    private int price;
    private String name;
    private String category;
    private String studentName;
    private String studentEmail;


    // pass in all required information
    
    public Item(int price, String name, String category, String studentName, String studentEmail){
        this.price = price;
        this.name = name;
        this.category = category;
        this.studentName = studentName;
        this.studentEmail = studentEmail;
    }


    // This allows you to call print on the Item and it will print out neatly
    @Override
    public String toString(){
        return " Name of the item: " + this.name + "\n Category: "  + this.category + "\n Student who is selling it: " + studentName + "\n student email: " + studentEmail + "\n price: " + price;
    }
}
