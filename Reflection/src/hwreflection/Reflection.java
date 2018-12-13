/*
 author: Junjie Chen
* */
package hwreflection;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.lang.reflect.*;

public class Reflection extends JFrame{
//    private JTextField inputFN,inputLN,displayFN,displayLN;
    private Object obj;
    public Reflection()throws Exception{
        super("API Reflection");
        setSize(350,350);
        Container c= getContentPane();
        JPanel panel= new JPanel();

        panel.setLayout(new GridLayout(2,2,5,5));
        panel.setBackground(Color.LIGHT_GRAY);
        c.add(panel,BorderLayout.CENTER);
        JTextField inputFN = new JTextField();
        JTextField inputLN = new JTextField();
        JTextField displayFN = new JTextField("Firstname:");
        JTextField displayLN = new JTextField("Lastname:");
        panel.add(inputFN);
        panel.add(displayFN);
        panel.add(inputLN);
        panel.add(displayLN);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        String beanname = "hwreflection.ReflectionBean";
        Class beanclass = Class.forName(beanname);
        Constructor constructor = beanclass.getConstructor();
        obj=constructor.newInstance();
        Method setfirstname  = beanclass.getMethod("setFN",java.lang.String.class);
        Method setlastname = beanclass.getMethod("setLN",java.lang.String.class);
        Method getfirstname = beanclass.getMethod("getFN");
        Method getlastname = beanclass.getMethod("getLN");

        inputFN.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                try{
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_ENTER:{
                            String s = inputFN.getText();
                            setfirstname.invoke(obj,s);
                            displayFN.setText("Firstname :\n" + getfirstname.invoke(obj));
                            System.out.println(obj);
                            break;
                        }
                    }
                }
                catch (Exception e0){
                }
            }
        });
        inputLN.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                try{
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_ENTER:{
                            String s = inputLN.getText();
                            setlastname.invoke(obj,s);
                            displayLN.setText("Lastname :\n" + getlastname.invoke(obj));
                            System.out.println(obj);
                            break;
                        }
                    }
                }
                catch (Exception e0){
                }
            }
        });
    }
    public static void main(String[] args) throws Exception{
        Reflection a;
        a = new Reflection();
    }
}
