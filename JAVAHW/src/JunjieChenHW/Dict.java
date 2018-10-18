/*
author:Junjie Chen
*/
package JunjieChenHW;
import java.io.*;
import java.util.*;
public class Dict {

    public static void main(String[] args) throws IOException{
	    HashMap<Integer,String>dictionary=new HashMap<Integer,String>();
	    try{
	        BufferedReader reader=new BufferedReader(new FileReader("dict.txt"));
	        String str;
	        int i=0;
	        while ((str=reader.readLine())!=null){
	            dictionary.put(i,str);
	            i++;
            }
        }
        catch(IOException e){}
        BufferedReader typein= new BufferedReader(new InputStreamReader(System.in));
	    String in=typein.readLine();
	    boolean exist=	dictionary.containsValue(in);
	    if (exist==false)
	        System.out.println("mis-spelled");

    }
}
