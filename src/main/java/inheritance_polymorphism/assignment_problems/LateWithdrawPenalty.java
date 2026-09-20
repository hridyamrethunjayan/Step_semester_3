package inheritance_polymorphism.assignment_problems;
import java.util.Arrays;

public class LateWithdrawPenalty {
    public static void main(String[] args) {
        RunnerEntry r=new RunnerEntry("BIB2001",80,"Open 10K");
        r.pay(30);
        r.applyLateFee(20);
        System.out.println(r.getBalanceDue());

        double[] history=r.getLateFeeHistory();
        System.out.println(Arrays.toString(history));
        history[0]=999;
        System.out.println(Arrays.toString(r.getLateFeeHistory()));
    }
}