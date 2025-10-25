public class MusicPlan extends Subscription implements Billable,TrialSupport{
    private  int trialDays = 14;

    public MusicPlan(String id, String title, float monthlyPrice, int startDate, boolean active, boolean isTrialMonth) {
        super(id, title, monthlyPrice, startDate, active, isTrialMonth);
        this.trialDays = trialDays;
    }

    @Override
    public float monthlyCharge(float forMonth) {
        if (!active){
            return 0;
        }
        int currentMonth = (int) forMonth;
        if (!isTrialMonth) return monthlyPrice;
        if (currentMonth < 1 || currentMonth > 12) {
            System.err.println("Некорректно задан месяц!");
            return 0;
        }
        int DaysInMonth = DaysInMonth(currentMonth);
        float priceForDay = monthlyPrice/DaysInMonth;
        int paidDay = 0;
        for (int i = startDate; i <= DaysInMonth; i++) {
            if (!isInTrial(i)){
                paidDay ++;
            }
        }

        return paidDay * priceForDay;
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

    @Override
    public int trialDays() {
        return trialDays;
    }

    @Override
    public boolean isInTrial(int date) {
        return date - startDate < trialDays;
    }
}
