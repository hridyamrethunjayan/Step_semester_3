interface Dimmable {
    void dim(int level);
}

interface Schedulable {
    void schedule(String time);
}

interface EnergyMonitor {
    void energy();
}

class Device {
    String id;
    boolean on = false;

    Device(String id) {
        this.id = id;
    }

    void turnOn() {
        on = true;
        System.out.println(id + " is ON");
    }
}

class Light extends Device implements Dimmable, Schedulable {
    Light(String id) {
        super(id);
    }

    public void dim(int level) {
        System.out.println(id + " dimmed to " + level);
    }

    public void schedule(String time) {
        System.out.println(id + " scheduled " + time);
    }
}

class Fan extends Device implements Schedulable {
    Fan(String id) {
        super(id);
    }

    public void schedule(String time) {
        System.out.println(id + " scheduled " + time);
    }
}

class Plug extends Device implements EnergyMonitor {
    int kwh;

    Plug(String id, int kwh) {
        super(id);
        this.kwh = kwh;
    }

    public void energy() {
        System.out.println(id + " energy " + kwh + " kWh");
    }
}

public class SmartHome {
    public static void main(String[] args) {
        Light l = new Light("L1");
        Fan f = new Fan("F1");
        Plug p = new Plug("P1", 12);

        l.turnOn();
        l.dim(40);

        if (f instanceof Dimmable)
            ((Dimmable) f).dim(30);
        else
            System.out.println("F1 rejected: DIM unsupported");

        f.schedule("22:00");
        p.energy();

        if (l instanceof EnergyMonitor)
            ((EnergyMonitor) l).energy();
        else
            System.out.println("L1 rejected: ENERGY unsupported");
    }
}