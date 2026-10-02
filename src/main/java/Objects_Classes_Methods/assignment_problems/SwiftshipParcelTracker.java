package Objects_Classes_Methods.assignment_problems;
import java.util.*;

interface ShippingType{
    double charge(double kg);
    String name();
}

class Standard implements ShippingType{
    public double charge(double kg){return 40+10*kg;}
    public String name(){return "Standard";}
}

class Express implements ShippingType{
    public double charge(double kg){return 80+15*kg;}
    public String name(){return "Express";}
}

class Fragile implements ShippingType{
    ShippingType standard=new Standard();
    public double charge(double kg){return standard.charge(kg)+50;}
    public String name(){return "Fragile";}
}

interface NotificationChannel{
    void notify(String id,String status);
}

class SmsChannel implements NotificationChannel{
    public void notify(String id,String status){
        System.out.println("[SMS] "+id+" is now "+status+".");
    }
}

class EmailChannel implements NotificationChannel{
    public void notify(String id,String status){
        System.out.println("[Email] "+id+" is now "+status+".");
    }
}

class Customer{
    String name;
    List<NotificationChannel> channels=new ArrayList<>();

    Customer(String name){this.name=name;}

    void subscribe(NotificationChannel c){
        channels.add(c);
    }
}

class Parcel{
    String id;
    double weight;
    ShippingType type;
    String status="BOOKED";
    Customer customer;

    Parcel(String id,double weight,ShippingType type,Customer customer){
        this.id=id;
        this.weight=weight;
        this.type=type;
        this.customer=customer;
    }

    double charge(){
        return type.charge(weight);
    }

    void notifyChannels(){
        for(NotificationChannel c:customer.channels)
            c.notify(id,status);
    }

    void updateStatus(String next){
        String expected="";

        if(status.equals("BOOKED"))expected="PICKED_UP";
        else if(status.equals("PICKED_UP"))expected="IN_TRANSIT";
        else if(status.equals("IN_TRANSIT"))expected="OUT_FOR_DELIVERY";
        else if(status.equals("OUT_FOR_DELIVERY"))expected="DELIVERED";

        if(next.equals(expected)){
            status=next;
            notifyChannels();
        }else{
            System.out.println("Invalid transition: "+status+" → "+next+" is not allowed.");
        }
    }

    void cancel(){
        if(status.equals("BOOKED"))
            System.out.println("Parcel "+id+" cancelled.");
        else
            System.out.println("Cancellation failed: "+id+" can be cancelled only while BOOKED.");
    }
}

class ParcelService{
    void book(Parcel p){
        System.out.println("Parcel "+p.id+" booked ("+p.type.name()+", "+p.weight+" kg).");
        System.out.printf("Charge: ₹%.2f%n",p.charge());
        p.notifyChannels();
    }

    void update(Parcel p,String status){
        p.updateStatus(status);
    }

    void cancel(Parcel p){
        p.cancel();
    }
}

public class SwiftshipParcelTracker{
    public static void main(String[] args){
        Customer c=new Customer("Customer");

        c.subscribe(new SmsChannel());
        c.subscribe(new EmailChannel());

        Parcel p=new Parcel("P101",2,new Express(),c);
        ParcelService service=new ParcelService();

        service.book(p);
        service.update(p,"PICKED_UP");
        service.cancel(p);
        service.update(p,"IN_TRANSIT");
        service.update(p,"DELIVERED");
    }
}