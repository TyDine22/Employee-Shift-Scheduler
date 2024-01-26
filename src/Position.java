
public class Position {
    private String title;
    public Position(String title){
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    static class Cashier extends Position {
        public Cashier(){
            super("Cashier");
        }
    }

    static class StockController extends Position {
        public StockController(){
            super("Stock Controller");
        }
    }
}
