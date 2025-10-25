
public class Main {
    public static void main(String[] args) {

        MusicPlan myMusicPlan = new MusicPlan("1a","Base",
                556.78f,
                6,
                true,true);

        MusicPlan myMusicPlan2 = new MusicPlan("1a","Base",
                500f,
                2,
                true,false);

        System.out.println(myMusicPlan.monthlyCharge(6));
        System.out.println(myMusicPlan2.monthlyCharge(6));

        }
}
