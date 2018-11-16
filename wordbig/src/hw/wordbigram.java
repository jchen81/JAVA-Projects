package hw;
import java.lang.*;
import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class wordbigram {
    public static void main(String[] args) {
        String book;
        String dict;
        HashMap<String,Integer>words=new HashMap<String,Integer>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader("Frankenstein.txt"));
            BufferedReader dictreader = new BufferedReader(new FileReader("shortwords.txt"));
            StringBuilder booktext = new StringBuilder();
            StringBuilder dicttext = new StringBuilder();
            String line;
            dicttext.append("\\w+\\s(");
            while ((line = reader.readLine()) != null) {
                booktext.append(line+" ");
            }
            while ((line = dictreader.readLine()) != null) {
                dicttext.append(line+" ");
            }
            dicttext.append(")\\s\\w+");
            book=booktext.toString();
            dict=dicttext.toString();
            book=book.replaceAll("\\pP","");
            book=book.replaceAll(" +"," ");
            dict=dict.replaceAll(" ","|");
//            System.out.println(book);
//            System.out.println(dict);
            Pattern pattern= Pattern.compile(dict);
            Matcher matcher=pattern.matcher(book);
            while(matcher.find()){
                String word1=matcher.group().replaceAll("\\s\\w+\\s\\w+","");
                String word2=matcher.group().replaceAll("\\w+\\s\\w+\\s","");
                if(words.containsKey(word1)){
                    int count=words.get(word1);
                    count++;
                    words.put(word1,count);
                }
                else{
                    words.put(word1,1);
                }
                if(words.containsKey(word2)){
                    int count=words.get(word2);
                    count++;
                    words.put(word2,count);
                }
                else{
                    words.put(word2,1);
                }
//                System.out.println(word1+" "+word2);
            }
            System.out.println(words.entrySet());
            for (Map.Entry<String,Integer> entry : words.entrySet()) {
                if (entry.getValue() >= 50)
                    System.out.println("word:" + entry.getKey() + " times:" + entry.getValue());

            }
        }
        catch(IOException e){}


    }
}
