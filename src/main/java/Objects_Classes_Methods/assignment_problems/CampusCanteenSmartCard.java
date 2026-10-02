package Objects_Classes_Methods.assignment_problems;

import java.util.*;

interface PricingPlan{
    double price(double amount);
    String name();
}

class DayScholarPlan implements PricingPlan{
    public double price(double amount){return amount;}
    public String name(){return "Day Scholar";}
}

class HostellerPlan implements PricingPlan{
    public double price(double amount){return amount*.90;}
    public String name(){return "Hosteller";}
}

class StaffPlan implements PricingPlan{
    public double price(double amount){return amount*.80;}
    public String name(){return "Staff";}
}

class Transaction{
    double amount;
    String type;
    String item;

    Transaction(double amount,String type,String item){
        this.amount=amount;
        this.type=type;
        this.item=item;
    }
}

class SmartCard{
    private String id;
    private PricingPlan plan;
    private double balance=0;
    private List<Transaction> transactions=new ArrayList<>();
    private boolean blocked=false;

    SmartCard(String id,PricingPlan plan){
        this.id=id;
        this.plan=plan;
    }

    void topUp(double amount){
        if(blocked){
            System.out.println("Top-up failed: Card is blocked.");
            return;
        }

        if(amount<100){
            System.out.println("Top-up failed: Minimum top-up is ₹100.00.");
            return;
        }

        if(balance+amount>5000){
            System.out.println("Top-up failed: Maximum balance is ₹5000.00.");
            return;
        }

        balance+=amount;
        transactions.add(new Transaction(amount,"TOPUP",""));
        System.out.printf("%s topped up with ₹%.2f. Balance: ₹%.2f.%n",
                id,amount,balance);
    }

    void purchase(String item,double price){
        if(blocked){
            System.out.println("Purchase failed: Card is blocked.");
            return;
        }

        double charged=plan.price(price);

        if(charged>balance){
            System.out.printf("Purchase failed: Insufficient balance (required ₹%.2f, available ₹%.2f).%n",
                    charged,balance);
            return;
        }

        balance-=charged;
        transactions.add(new Transaction(-charged,"PURCHASE",item));

        System.out.printf("%s purchased for ₹%.2f. Balance: ₹%.2f.%n",
                item,charged,balance);
    }

    void refund(String item){
        for(Transaction t:transactions){
            if(t.type.equals("PURCHASE")&&t.item.equals(item)){
                for(Transaction r:transactions){
                    if(r.type.equals("REFUND")&&r.item.equals(item)){
                        System.out.println("Refund rejected: "+item+
                                " has already been refunded.");
                        return;
                    }
                }

                double amount=-t.amount;
                balance+=amount;
                transactions.add(new Transaction(amount,"REFUND",item));

                System.out.printf("Refund of ₹%.2f for %s processed. Balance: ₹%.2f.%n",
                        amount,item,balance);
                return;
            }
        }

        System.out.println("Refund rejected: Purchase not found.");
    }

    void block(){
        blocked=true;
    }

    void unblock(){
        blocked=false;
    }

    void statement(){
        System.out.print("Mini-statement for "+id+": ");

        for(int i=0;i<transactions.size();i++){
            double amount=transactions.get(i).amount;

            if(i>0)System.out.print(", ");

            System.out.printf("%+.2f",amount);
        }

        System.out.printf(" = ₹%.2f.%n",balance);
    }
}

public class CampusCanteenSmartCard{
    public static void main(String[] args){
        SmartCard card=new SmartCard("C-2045",new HostellerPlan());

        card.topUp(500);
        card.purchase("Veg Thali",120);
        card.purchase("Cold Coffee",60);
        card.purchase("Items",400);
        card.refund("Veg Thali");
        card.refund("Veg Thali");
        card.statement();
    }
}