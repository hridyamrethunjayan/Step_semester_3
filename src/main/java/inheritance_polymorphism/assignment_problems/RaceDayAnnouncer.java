package inheritance_polymorphism.assignment_problems;
public class RaceDayAnnouncer {
    static String announceAll(RaceEntry[] entries) {
        StringBuilder result=new StringBuilder();

        for(RaceEntry entry:entries) {
            if(entry instanceof RelayTeamEntry) {
                RelayTeamEntry team=(RelayTeamEntry)entry;
                result.append("Relay Team | Bib: "+team.getBibNumber()+" | Team Size: "+team.getTeamSize()+" | Balance: "+team.getBalanceDue());
                result.append(" | [Team size via downcast: "+team.getTeamSize()+"] | ");
            } else {
                result.append("Runner Entry | Bib: "+entry.getBibNumber()+" | Category: "+((RunnerEntry)entry).getCategory()+" | Balance: "+entry.getBalanceDue());
                result.append(" | ");
            }
        }
        return result.toString();
    }
    public static void main(String[] args) {
        RunnerEntry runnerEntry=new RunnerEntry("BIB2001",80,"Open 10K");
        RelayTeamEntry relayEntry=new RelayTeamEntry("BIB4001",300,4);
        RaceEntry[] fleet={runnerEntry,relayEntry};
        System.out.println(announceAll(fleet));
        RaceEntry plain=new RaceEntry("BIB5001",100);
        try {
            RelayTeamEntry bad=(RelayTeamEntry)plain;
        } catch(ClassCastException e) {
            System.out.println("ClassCastException at runtime");
        }
    }
}