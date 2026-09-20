package inheritance_polymorphism.class_problems;

public class WorkshopTicket extends EventTicket{

    protected String track;
    public WorkshopTicket(String attendeeId,double basePrice,String track){
        super(attendeeId,basePrice);
        this.track=track;
    }
    @Override
    void printTicket(){
        System.out.println("Workshop Ticket | Track: "+track+
                " | Balance Due: "+getBalanceDue());
    }
}