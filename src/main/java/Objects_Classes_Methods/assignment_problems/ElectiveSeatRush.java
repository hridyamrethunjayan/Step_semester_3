import java.util.*;

interface CreditPolicy{
    int limit();
    String name();
}

class RegularPolicy implements CreditPolicy{
    public int limit(){return 24;}
    public String name(){return "Regular";}
}

class HonorsPolicy implements CreditPolicy{
    public int limit(){return 28;}
    public String name(){return "Honors";}
}

class ExchangePolicy implements CreditPolicy{
    public int limit(){return 20;}
    public String name(){return "Exchange";}
}

class Student{
    String name;
    int credits;
    CreditPolicy policy;

    Student(String name,int credits,CreditPolicy policy){
        this.name=name;
        this.credits=credits;
        this.policy=policy;
    }

    boolean canAdd(int c){
        return credits+c<=policy.limit();
    }
}

class Enrollment{
    Student student;
    Elective elective;

    Enrollment(Student student,Elective elective){
        this.student=student;
        this.elective=elective;
    }
}

class Elective{
    String name;
    int credits,capacity;
    List<Enrollment> enrolled=new ArrayList<>();
    Queue<Student> waitlist=new LinkedList<>();

    Elective(String name,int credits,int capacity){
        this.name=name;
        this.credits=credits;
        this.capacity=capacity;
    }

    boolean hasStudent(Student s){
        for(Enrollment e:enrolled)
            if(e.student==s)return true;
        return waitlist.contains(s);
    }

    boolean isFull(){
        return enrolled.size()>=capacity;
    }

    void addWaitlist(Student s){
        waitlist.add(s);
        System.out.println(s.name+" added to waitlist (position "+waitlist.size()+").");
    }

    void enroll(Student s){
        enrolled.add(new Enrollment(s,this));
        s.credits+=credits;
        System.out.println(s.name+" enrolled in "+name+
                " (credits: "+s.credits+"/"+s.policy.limit()+").");
    }

    void drop(Student s){
        Enrollment found=null;

        for(Enrollment e:enrolled){
            if(e.student==s){
                found=e;
                break;
            }
        }

        if(found!=null){
            enrolled.remove(found);
            s.credits-=credits;
            System.out.println(s.name+" dropped "+name+
                    " (credits: "+s.credits+"/"+s.policy.limit()+").");
            promote();
        }
    }

    void promote(){
        while(!waitlist.isEmpty()&&!isFull()){
            Student s=waitlist.poll();

            if(s.canAdd(credits)){
                enrolled.add(new Enrollment(s,this));
                s.credits+=credits;
                System.out.println(s.name+
                        " promoted from waitlist and enrolled in "+name+
                        " (credits: "+s.credits+"/"+s.policy.limit()+").");
                break;
            }
        }
    }
}

class EnrollmentService{
    void enroll(Student s,Elective e){
        if(e.hasStudent(s)){
            System.out.println("Enrollment failed: "+s.name+
                    " is already enrolled or waitlisted.");
            return;
        }

        if(!s.canAdd(e.credits)){
            System.out.println("Enrollment failed: "+s.name+
                    " would exceed the "+s.policy.name()+
                    " credit limit ("+(s.credits+e.credits)+"/"+
                    s.policy.limit()+").");
            return;
        }

        if(e.isFull()){
            System.out.println(e.name+" is full.");
            e.addWaitlist(s);
            return;
        }

        e.enroll(s);
    }

    void drop(Student s,Elective e){
        e.drop(s);
    }
}

public class ElectiveSeatRush{
    public static void main(String[] args){
        Elective e=new Elective("Cloud Computing",4,2);

        Student asha=new Student("Asha",20,new RegularPolicy());
        Student ravi=new Student("Ravi",22,new HonorsPolicy());
        Student neha=new Student("Neha",12,new ExchangePolicy());
        Student kiran=new Student("Kiran",22,new RegularPolicy());

        EnrollmentService service=new EnrollmentService();

        service.enroll(asha,e);
        service.enroll(ravi,e);
        service.enroll(neha,e);
        service.enroll(kiran,e);
        service.drop(asha,e);
    }
}