public class VideoPlan extends Subscription implements Billable, Pausable, Sharable{
    private  int maxProfiles = 4;
    private  String[] members= new String[maxProfiles];
    private  PauseWindow[] pauses = new PauseWindow[5];

    public VideoPlan(String id, String title, float monthlyPrice, int startDate, boolean active, boolean isTrialMonth, int maxProfiles, String[] members, PauseWindow[] pauses) {
        super(id, title, monthlyPrice, startDate, active, isTrialMonth);
        this.maxProfiles = maxProfiles;
        this.members = members;
        this.pauses = pauses;
    }

    @Override
    public float monthlyCharge(float forMonth) {
        if (!active) return 0;

        int currentMonth = (int) forMonth;
        int daysInMonth = DaysInMonth(currentMonth);

        if (currentMonth < 1 || currentMonth > 12) {
            System.err.println("Некорректно задан месяц!");
            return 0;
        }

        int activeDays = 0;
        for (int i = 1; i <= daysInMonth; i++) {
            if(!isPausedOn(i)){
                activeDays++;
            }
        }

        float priceDay =monthlyPrice/daysInMonth;
        return priceDay*activeDays;
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
    public void pause(int from, int to) {
        for (int i = 0; i < pauses.length; i++) {
            if (pauses[i] == null) {
                pauses[i] = new PauseWindow(from, to);
                return;
            }
        }
        System.err.println("Превышен лимит пауз: " + pauses.length);
    }

    @Override
    public boolean isPausedOn(int date) {
        if(pauses.length == 0) return false;
        for (int i = 0; i < pauses.length; i++) {
            if (pauses[i] == null) continue;
            if (date >= pauses[i].getFrom() || date<= pauses[i].getTo()){
                return true;
            }
        }
        return false;
    }

    @Override
    public int maxProfiles() {
        return 0;
    }

    @Override
    public void addMember(String userId) {
        for (int i = 0; i < members.length; i++) {
            if (members[i] !=null) continue;
            members[i] = userId;
            break;
        }
    }

    @Override
    public void removeMember(String userId) {
        for (int i = 0; i < members.length; i++) {
            if (members.equals(userId)){
                members[i] = null;
                break;
            }
        }
    }
}
