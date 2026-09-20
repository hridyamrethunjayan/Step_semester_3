package inheritance_polymorphism.class_problems;

class PremiumWorkshopTicket extends WorkshopTicket{
    private double kitFee;
    public PremiumWorkshopTicket(String attendeeId,double basePrice, String track,double kitFee){
        super(attendeeId,basePrice,track);
        this.kitFee=kitFee;
    }
    @Override
    void printTicket(){
        System.out.println("Premium Workshop Ticket | Track: "+track+" | Kit Fee: "+kitFee+ " | Balance Due: "+getBalanceDue());
    }
}
class HackathonTicket extends EventTicket{
    private String teamName;
    public HackathonTicket(String attendeeId,double basePrice,String teamName){
        super(attendeeId,basePrice);
        this.teamName=teamName;
    }

    @Override
    void printTicket(){
        System.out.println("Hackathon Ticket | Team: "+teamName+
                " | Balance Due: "+getBalanceDue());
    }
}

public class DifferentTickets{
    static String classifyGeneration(EventTicket ticket){
        if(ticket instanceof PremiumWorkshopTicket)
            return "Multilevel descendant (3 generations deep)";
        if(ticket instanceof HackathonTicket)
            return "Hierarchical sibling (independent branch)";
        return "Standard or Workshop ticket";
    }

    static double getTotalBalanceDue(EventTicket[] tickets){
        double total=0;
        for(EventTicket ticket:tickets)
            total=total+ticket.getBalanceDue();
        return total;
    }

    public static void main(String[] args){
        EventTicket t1=new EventTicket("STU1",500);
        t1.printTicket();
        WorkshopTicket t2=new WorkshopTicket("STU2",1200,"AI/ML");
        t2.printTicket();
        PremiumWorkshopTicket t3=new PremiumWorkshopTicket("STU3",2000,"Cloud Native",300);
        t3.printTicket();
        HackathonTicket t4=new HackathonTicket("STU4",800,"Byte Force");
        t4.printTicket();

        System.out.println(classifyGeneration(t3));
        System.out.println(classifyGeneration(t4));

        EventTicket[] tickets={t1,t2,t3,t4};
        System.out.println(getTotalBalanceDue(tickets));
    }
}