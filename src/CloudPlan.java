public class CloudPlan extends Subscription implements Billable{
    int storageTb;
    int baseTbPrice;
    int extraTbPrice;

    public CloudPlan(String id, String title, float monthlyPrice, int startDate, boolean active, boolean isTrialMonth, int storageTb, int baseTbPrice, int extraTbPrice) {
        super(id, title, monthlyPrice, startDate, active, isTrialMonth);
        this.storageTb = storageTb;
        this.baseTbPrice = baseTbPrice;
        this.extraTbPrice = extraTbPrice;
    }

    @Override
    public float monthlyCharge(float forMonth) {
        if(!active) return 0;
       float totalPrice = monthlyPrice;

       if(storageTb > baseTbPrice){
           int extraStor = storageTb - baseTbPrice;
           totalPrice += extraStor*extraTbPrice;

       }

       return totalPrice;
    }

    @Override
    public int DaysInMonth(int month) {
        return switch (month) {
            case 1, 3, 5, 7, 8, 10, 12 -> 31;
            case 4, 6, 9, 11 -> 30;
            case 2 -> 28;
            default -> throw new IllegalArgumentException("Неверный месяц: " + month);
        };
    }
}
