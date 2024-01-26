public class PartTimePosition extends Position{
    public PartTimePosition(String position){
        super(position);
    }

    static class Cleaner extends PartTimePosition{
        public Cleaner(){
            super("Cleaner");
        }
    }
}
