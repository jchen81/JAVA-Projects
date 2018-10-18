/*
author:Junjie Chen
 */
package JunjieChenHW;
import java.io.*;
import java.util.ArrayList;

public class SolarSystem {
    public ArrayList<Planet> solarsystem;
    public SolarSystem(){
        solarsystem=new  ArrayList<Planet>();
    }
    public void showData(){
        int i=0;
        while(i<4){
            solarsystem.get(i).show();
            i++;
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader reader= new BufferedReader(new FileReader("solarsystem.dat"));
        SolarSystem sunsystem=new SolarSystem();
        String[] arrs=null;
        String line;
        line=reader.readLine();
        while ((line=reader.readLine())!=null) {
            line=line.trim();
            line=line.replaceAll("\t"," ");
            line=line.replaceAll(" +"," ");
            arrs=line.split(" ");

//            double dis=(arrs[4]+arrs[5])/2;

            if(arrs[0].equals("Sun")||arrs[0].equals("Moon")||arrs[0].equals("Earth")||arrs[0].equals("Venus")) {
                double mass=Double.valueOf(arrs[2].toString());
                double diam=Double.valueOf(arrs[3].toString());
                double per=Double.valueOf(arrs[4].toString());
                double ape=Double.valueOf(arrs[5].toString());
                double distance=(per+ape)/2;
                sunsystem.solarsystem.add(new Planet(arrs[0], mass, diam, arrs[1],distance));
            }
        }
        reader.close();
        sunsystem.showData();
    }

}
class Planet{
    private String name="";
    private double mass;
    private double diam;
    private String orbit="";
    private double distance;
    public Planet(){
    }
    public Planet (String name ,double mass,double diam, String orbit,double distance){
        this.name=name;
        this.mass=mass;
        this.diam=diam;
        this.orbit=orbit;
        this.distance=distance;
    }
    void show(){
        System.out.println("name:"+name+" mass:"+mass+" diam:"+diam+" orbit:"+orbit+" distance:"+distance);
    }
}

