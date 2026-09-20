package inheritance_polymorphism.assignment_problems;

class EliteRunnerEntry extends RunnerEntry {
    private double sponsorBonus;

    public EliteRunnerEntry(String bibNumber,double entryFee,String category,double sponsorBonus) {
        super(bibNumber,entryFee,category);
        this.sponsorBonus=sponsorBonus;
    }

    @Override
    public void announce() {
        System.out.println("Elite Runner | Bib: "+getBibNumber()+" | Category: "+getCategory()+" | Sponsor Bonus: "+sponsorBonus+" | Balance: "+getBalanceDue());
    }
}

class RelayTeamEntry extends RaceEntry {
    private int teamSize;

    public RelayTeamEntry(String bibNumber,double entryFee,int teamSize) {
        super(bibNumber,entryFee);
        this.teamSize=teamSize;
    }

    public int getTeamSize() {
        return teamSize;
    }

    @Override
    public void announce() {
        System.out.println("Relay Team | Bib: "+getBibNumber()+" | Team Size: "+teamSize+" | Balance: "+getBalanceDue());
    }
}

public class RaceClassification {

    static String classifyGeneration(RaceEntry entry) {
        if(entry instanceof EliteRunnerEntry)
            return "Multilevel descendant (3 generations deep)";

        if(entry instanceof RelayTeamEntry)
            return "Hierarchical sibling (independent branch)";

        return "Base entry";
    }

    static double getTotalBalanceDue(RaceEntry[] entries) {
        double total=0;

        for(RaceEntry entry:entries)
            total+=entry.getBalanceDue();

        return total;
    }

    public static void main(String[] args) {
        RunnerEntry runnerEntry=new RunnerEntry("BIB2001",80,"Open 10K");
        EliteRunnerEntry eliteEntry=new EliteRunnerEntry("BIB3001",150,"Elite Full Marathon",500);
        RelayTeamEntry relayEntry=new RelayTeamEntry("BIB4001",300,4);

        runnerEntry.announce();
        eliteEntry.announce();
        relayEntry.announce();

        System.out.println(classifyGeneration(eliteEntry));
        System.out.println(classifyGeneration(relayEntry));

        RaceEntry[] entries={runnerEntry,eliteEntry,relayEntry};
        System.out.println(getTotalBalanceDue(entries));
    }
}