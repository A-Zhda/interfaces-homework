public class Subscription {
    private  String id;
    private  String title;
    protected float monthlyPrice;
    protected  int startDate;
    protected boolean active;
    protected boolean isTrialMonth;

    public Subscription(String id, String title, float monthlyPrice, int startDate, boolean active,boolean isTrialMonth) {
        this.id = id;
        this.title = title;
        this.monthlyPrice = monthlyPrice;
        this.startDate = startDate;
        this.active = active;
        this.isTrialMonth = isTrialMonth;
    }


    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public float getMonthlyPrice() {
        return monthlyPrice;
    }

    public int getStartDate() {
        return startDate;
    }

    public boolean isActive() {
        return active;
    }
}
