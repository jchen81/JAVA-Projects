package hwreflection;

public class ReflectionBean {
    private String firstname,lastname;
    public ReflectionBean(){};
    public void setFN(String fn){ firstname=fn;}
    public void setLN(String ln){ lastname=ln;}
    public String getFN(){return firstname;}
    public String getLN(){return lastname;}
    public String toString(){return "the firstname is changed to: " + firstname+"   the lastname is changed to "+lastname;}
}
