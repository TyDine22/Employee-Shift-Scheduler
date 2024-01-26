public class FullTimePosition extends Position{
    public FullTimePosition(String position) {
        super(position);
    }

    static class Security extends FullTimePosition{
        public Security(){
            super("Security");
        }
    }
}
