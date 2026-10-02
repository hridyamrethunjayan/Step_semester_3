package Objects_Classes_Methods.assignment_problems;
import java.util.*;

interface Capability{
    String name();
    void setValue(int value,String device);
}

class PowerCapability implements Capability{
    public String name(){return "Power";}
    public void setValue(int value,String device){
        if(value<0||value>1){
            System.out.println("Rejected: "+device+" power must be 0 or 1.");
            return;
        }
        System.out.println(device+": "+(value==1?"ON":"OFF")+".");
    }
}

class BrightnessCapability implements Capability{
    public String name(){return "Brightness";}
    public void setValue(int value,String device){
        if(value<0||value>100){
            System.out.println("Rejected: "+device+" brightness must be between 0% and 100%.");
            return;
        }
        System.out.println(device+": brightness set to "+value+"%.");
    }
}

class TemperatureCapability implements Capability{
    public String name(){return "Temperature";}
    public void setValue(int value,String device){
        if(value<16||value>30){
            System.out.println("Rejected: "+device+" temperature must be between 16°C and 30°C.");
            return;
        }
        System.out.println(device+": temperature set to "+value+"°C.");
    }
}

class Device{
    String name;
    Map<String,Capability> capabilities=new HashMap<>();

    Device(String name){this.name=name;}

    void addCapability(Capability c){
        capabilities.put(c.name(),c);
        System.out.println(name+": "+c.name()+" capability added.");
    }

    boolean hasCapability(String name){
        return capabilities.containsKey(name);
    }

    void set(String capability,int value){
        if(hasCapability(capability))
            capabilities.get(capability).setValue(value,name);
    }
}

class SceneStep{
    String capability;
    int value;

    SceneStep(String capability,int value){
        this.capability=capability;
        this.value=value;
    }
}

class Scene{
    String name;
    List<SceneStep> steps=new ArrayList<>();

    Scene(String name){this.name=name;}

    void addStep(String capability,int value){
        steps.add(new SceneStep(capability,value));
    }

    int execute(List<Device> devices){
        System.out.println("Scene '"+name+"' started.");
        int actions=0;

        for(SceneStep step:steps){
            for(Device d:devices){
                if(d.hasCapability(step.capability)){
                    d.set(step.capability,step.value);
                    actions++;
                }
            }
        }

        System.out.println("Scene '"+name+"' completed: "+actions+" actions applied.");
        return actions;
    }
}

public class SmartLabControlPanel{
    public static void main(String[] args){
        Device ac=new Device("Lab AC");
        Device lights=new Device("Ceiling Lights");
        Device projector=new Device("Projector");

        ac.addCapability(new PowerCapability());
        ac.addCapability(new TemperatureCapability());

        lights.addCapability(new PowerCapability());
        lights.addCapability(new BrightnessCapability());

        projector.addCapability(new PowerCapability());

        List<Device> devices=Arrays.asList(ac,lights,projector);

        Scene lecture=new Scene("Lecture Mode");
        lecture.addStep("Power",1);
        lecture.addStep("Brightness",40);
        lecture.addStep("Temperature",24);

        lecture.execute(devices);

        ac.set("Temperature",12);

        projector.addCapability(new BrightnessCapability());
        projector.set("Brightness",70);
    }
}