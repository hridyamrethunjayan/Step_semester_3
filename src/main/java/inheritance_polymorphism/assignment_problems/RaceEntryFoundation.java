package inheritance_polymorphism.assignment_problems;

class RaceEntry {
    private static int bibCounter;
    private final int entryCode;
    private String bibNumber;
    private double entryFee;
    private double paid;
    private double[] lateFeeHistory=new double[10];
    private int lateFeeCount;

    public RaceEntry(String bibNumber,double entryFee) {
        if(bibNumber==null||bibNumber.trim().length()<4)
            throw new IllegalArgumentException("Invalid bib number");
        this.bibNumber=bibNumber;
        this.entryFee=entryFee;
        entryCode=++bibCounter;
    }

    public void pay(double amount) {
        paid+=amount;
    }

    public void pay(double amount,String mode) {
        System.out.println("Paying via "+mode);
        pay(amount);
    }

    public double getBalanceDue() {
        return entryFee-paid;
    }

    public String getBibNumber() {
        return bibNumber;
    }

    protected void applyLateFee(double amount) {
        if(amount<=0)
            throw new IllegalArgumentException("Invalid late fee");
        paid-=amount;
        lateFeeHistory[lateFeeCount]=amount;
        lateFeeCount++;
    }

    public double[] getLateFeeHistory() {
        double[] history=new double[lateFeeCount];
        for(int i=0;i<lateFeeCount;i++)
            history[i]=lateFeeHistory[i];
        return history;
    }

    public static boolean isValidDiscountCode(String code) {
        if(code==null||code.length()!=5)
            return false;
        if(code.charAt(0)!='M')
            return false;
        if(!Character.isDigit(code.charAt(1))||
                !Character.isDigit(code.charAt(2))||
                !Character.isDigit(code.charAt(3)))
            return false;
        return Character.isUpperCase(code.charAt(4));
    }

    public static int getBibCounter() {
        return bibCounter;
    }

    public void announce() {
        System.out.println("Race Entry | Bib: "+bibNumber+" | Balance: "+getBalanceDue());
    }
}

class RunnerEntry extends RaceEntry {
    private String category;

    public RunnerEntry(String bibNumber,double entryFee,String category) {
        super(bibNumber,entryFee);
        this.category=category;
    }

    public String getCategory() {
        return category;
    }

    @Override
    protected void applyLateFee(double amount) {
        super.applyLateFee(amount*2);
    }

    @Override
    public void announce() {
        System.out.println("Runner Entry | Bib: "+getBibNumber()+" | Category: "+category+" | Balance: "+getBalanceDue());
    }
}

public class RaceEntryFoundation {
    public static String registerBatch(String[] bibNumbers,double entryFee) {
        int registered=0;
        int rejected=0;

        for(String bibNumber:bibNumbers) {
            try {
                new RaceEntry(bibNumber,entryFee);
                registered++;
            } catch(IllegalArgumentException e) {
                rejected++;
            }
        }

        return "Registered: "+registered+" | Rejected: "+rejected;
    }

    public static void main(String[] args) {
        try {
            new RaceEntry("B1",50);
        } catch(IllegalArgumentException e) {
            System.out.println("construction rejected");
        }

        RunnerEntry r=new RunnerEntry("BIB2001",80,"Open 10K");
        r.pay(30);
        System.out.println(r.getBalanceDue());

        String[] bibNumbers={"BIB1","B1","BIB2"};
        System.out.println(registerBatch(bibNumbers,80));
    }
}