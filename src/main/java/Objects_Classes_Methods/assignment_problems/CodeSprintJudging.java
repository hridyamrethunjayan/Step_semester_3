package Objects_Classes_Methods.assignment_problems;

import java.util.*;

interface ScoringRule{
    double calculate(double i,double e,double p);
}

class InnovationRule implements ScoringRule{
    public double calculate(double i,double e,double p){
        return i*.5+e*.3+p*.2;
    }
}

class OpenRule implements ScoringRule{
    public double calculate(double i,double e,double p){
        return(i+e+p)/3;
    }
}

class Student{
    String name;
    Student(String name){this.name=name;}
}

class Judge{
    String name;
    Judge(String name){this.name=name;}
}

class Score{
    double idea,execution,presentation;
    Score(double i,double e,double p){
        idea=i;execution=e;presentation=p;
    }
}

class Project{
    String name;
    Score score;
    boolean scored;
    Project(String name){this.name=name;}
}

class Team{
    String name;
    List<Student> members;
    ScoringRule rule;
    Project project;

    Team(String name,List<Student> members,ScoringRule rule){
        this.name=name;this.members=members;this.rule=rule;
    }

    void submit(Project p){
        if(project==null){
            project=p;
            System.out.println("Project '"+p.name+"' submitted by "+name+".");
        }
    }

    void score(double i,double e,double p){
        if(project!=null&&project.score==null){
            project.score=new Score(i,e,p);
            project.scored=true;
            System.out.println("Score recorded for '"+project.name+"'.");
        }
    }

    double finalScore(){
        Score s=project.score;
        return rule.calculate(s.idea,s.execution,s.presentation);
    }
}

class Hackathon{
    String name,state="Open";
    List<Team> teams=new ArrayList<>();
    Set<Student> registeredStudents=new HashSet<>();

    Hackathon(String name){this.name=name;}

    void register(Team t){
        if(!state.equals("Open"))return;
        if(t.members.size()<2||t.members.size()>4){
            System.out.println("Registration failed: A team must have 2 to 4 members.");
            return;
        }
        for(Student s:t.members){
            if(registeredStudents.contains(s)){
                System.out.println("Registration failed: A student can belong to only one team.");
                return;
            }
        }
        teams.add(t);
        registeredStudents.addAll(t.members);
        String track=t.rule instanceof InnovationRule?"Innovation":"Open";
        System.out.println("Team "+t.name+" registered ("+t.members.size()+" members, "+track+" track).");
    }

    void startJudging(){state="Judging";}

    void publish(){
        if(state.equals("Judging")){
            state="Published";
            for(Team t:teams)
                System.out.printf("Final score: %.2f%n",t.finalScore());
            System.out.println("Results published.");
        }
    }

    void rescore(Team t,double idea){
        if(state.equals("Published"))
            System.out.println("Rescore rejected: Results have already been published.");
    }
}

public class CodeSprintJudging{
    public static void main(String[] args){
        Hackathon h=new Hackathon("Code Sprint");

        Student asha=new Student("Asha");
        Student ravi=new Student("Ravi");
        Student neha=new Student("Neha");
        Student kiran=new Student("Kiran");

        Team byteBusters=new Team("ByteBusters",
                Arrays.asList(asha,ravi,neha),new InnovationRule());

        Team soloCoder=new Team("SoloCoder",
                Arrays.asList(kiran),new OpenRule());

        h.register(byteBusters);
        h.register(soloCoder);

        byteBusters.submit(new Project("SmartAttend"));
        h.startJudging();

        byteBusters.score(8,7,9);
        h.publish();

        h.rescore(byteBusters,10);
    }
}