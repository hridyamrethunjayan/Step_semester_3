package inheritance_polymorphism.assignment_problems;
public class RaceWideEngine {
    static String settleNight(RaceEntry[] entries) {
        int processed=0;
        int nullSkipped=0;
        int relay=0;
        int individual=0;
        for(RaceEntry entry:entries) {
            if(entry==null) {
                nullSkipped++;
                continue;
            }
            processed++;
            if(entry instanceof RelayTeamEntry)
                relay++;
            else
                individual++;
        }
        return processed+" processed | "+nullSkipped+" null skipped | "+relay+" relay | "+individual+" individual";
    }
    public static void main(String[] args) {
        RunnerEntry r=new RunnerEntry("BIB2001",80,"Open 10K");
        EliteRunnerEntry eliteEntry=new EliteRunnerEntry("BIB3001",150,"Elite Full Marathon",500);
        RelayTeamEntry relayEntry=new RelayTeamEntry("BIB4001",300,4);
        RaceEntry e=new RaceEntry("BIB5001",100);

        System.out.println(RaceEntry.isValidDiscountCode("M123A"));
        System.out.println(RaceEntry.isValidDiscountCode("M12A"));
        System.out.println(RaceEntry.isValidDiscountCode("X123A"));

        r.pay(10,"UPI");

        RaceEntry[] entries={eliteEntry,null,relayEntry};
        System.out.println(settleNight(entries));
        System.out.println(RaceEntry.getBibCounter());
    }
}
