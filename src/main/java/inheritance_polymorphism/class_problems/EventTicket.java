package inheritance_polymorphism.class_problems;

public class EventTicket{

    private String attendeeId;
    private double basePrice;
    private double balanceDue;

    public EventTicket(String attendeeId,double basePrice){
        if(attendeeId==null||attendeeId.trim().length()<4)
            throw new IllegalArgumentException("Invalid attendee ID");

        this.attendeeId=attendeeId;
        this.basePrice=basePrice;
        this.balanceDue=basePrice;
    }
    void pay(double amount){
        balanceDue=balanceDue-amount;
        if(balanceDue<0)
            balanceDue=0;
    }
    double getBalanceDue(){
        return balanceDue;
    }
    void printTicket(){
        System.out.println("Standard Event Ticket | Balance Due: "+getBalanceDue());
    }
}