/*
author:Junjie CHen
cite: the Compile class is designed by the help of Siyu Chen
*/
package com.company;
import java.lang.*;
import java.util.List;
import java.util.*;
import java.util.regex.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.JScrollPane;

public class textEditor extends JFrame{
    private File workfile;
    String str_path;
    String str_filename1;
    String str_filename2;
    private List<String>  errorsplit;
    private List<Integer> linenumbers;
    int linenumber;
    JTextArea text = new JTextArea();
    JTextArea display = new JTextArea("result:");
    final String envp = "Path=C:\\Program Files\\Java\\jdk-10.0.2\\bin";
    public textEditor() {
        super("Java Text Editor");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = getContentPane();
        c.setBackground(Color.WHITE);

        JScrollPane t = new JScrollPane(text);
        t.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        t.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        Font f = new Font("Helvetica", Font.BOLD, 15);
        text.setFont(f);
        c.add(t,BorderLayout.CENTER);

        display.setFont(f);
        c.add(display,BorderLayout.SOUTH);

        text.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_F4:{
                        if(!linenumbers.isEmpty()){
                            showNextErrorLine();
                        }
                        break;
                    }
                }
            }
        });

        display.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_F4:{
                        if(!linenumbers.isEmpty()){
                            showNextErrorLine();
                        }
                        break;
                    }
                }
            }
        });


        JMenuBar menubar = new JMenuBar();
        JMenu File = new JMenu("File");
        menubar.add(File);
        JMenuItem New = new JMenuItem("New");
        JMenuItem Save = new JMenuItem("Save");
        JMenuItem Open = new JMenuItem("Open");
        JMenuItem Quit = new JMenuItem("Quit");
        File.add(New);
        File.add(Save);
        File.add(Open);
        File.add(Quit);
        JMenu Build = new JMenu("Build");
        menubar.add(Build);
        JMenuItem Compile = new JMenuItem("Compile");
        JMenuItem Run = new JMenuItem("Run");
        Build.add(Compile);
        Build.add(Run);
        New.addActionListener(new ListenerNew());
        Open.addActionListener(new ListenerOpen());
        Save.addActionListener(new ListenerSave());
        Quit.addActionListener(new ListenerQuit());
        Compile.addActionListener(new ListenerCompile());
        Run.addActionListener(new ListenerRun());
        setJMenuBar(menubar);
        setVisible(true);
    }
//    public void split(String line){
//        errorsplit.clear();
//        linenumbers.clear();
//        Pattern pattern=Pattern.compile("\\w+java:[0-9]+]");
//        Matcher matcher= pattern.matcher(line);
//        while(matcher.find()){
//            errorsplit.add(matcher.group());
//        }
//        if(!errorsplit.isEmpty()){
//            for(String s : errorsplit){
//                String sNum = s.substring(s.lastIndexOf(':') + 1, s.length());
//                linenumbers.add(Integer.valueOf(sNum));
//
//            }
//        }
//    }
    public void split(String lin){
        String regex = "(([a-zA-Z])\\w+.java:[0-9]+)";
        errorsplit.clear();
        Pattern ptn = Pattern.compile(regex);
        Matcher matcher = ptn.matcher(lin);
        while(matcher.find()){
            errorsplit.add(matcher.group());
        }
        if(!errorsplit.isEmpty()){
            linenumbers.clear();
            for(String s : errorsplit){
                String sNum = s.substring(s.lastIndexOf(':') + 1, s.length());
                linenumbers.add(Integer.valueOf(sNum));
                System.out.println(sNum);
            }
        }
        //System.out.println(regexSplitLine);
    }
    public void showNextErrorLine(){
        try{
            int lineNum = linenumbers.get(linenumber) - 1;
            int selectionStart = text.getLineStartOffset(lineNum);
            int selectionEnd = text.getLineEndOffset(lineNum);
            text.requestFocus();
            text.setSelectionStart(selectionStart);
            text.setSelectionEnd(selectionEnd);
            linenumber++;
            if(linenumber >= linenumbers.size())
                linenumber = 0;
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    class ListenerNew implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Frame frame = null;
            FileDialog fileDialog = new FileDialog(frame, "NEW", FileDialog.LOAD);
            fileDialog.setVisible(true);

            try {

                text.setText("");
                File file = new File(fileDialog.getDirectory(), fileDialog.getFile());
                workfile=file;
                str_path = workfile.getParent();
                str_filename1 = workfile.getName();
                str_filename2 = str_filename1.substring(0, str_filename1.lastIndexOf("."));
                FileReader filereader = new FileReader(file);
                BufferedReader bufferedreader = new BufferedReader(filereader);
                String aline;
                while ((aline = bufferedreader.readLine()) != null)
                    text.append(aline + "\r\n");
                filereader.close();
                bufferedreader.close();

            }
            catch (IOException ex) {
                System.out.println(ex);
            }
        }
    }
    class ListenerOpen implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            Frame frame = null;
            FileDialog fileDialog = new FileDialog(frame, "Open", FileDialog.LOAD);
            fileDialog.setVisible(true);

            try {

                text.setText("");
                File file = new File(fileDialog.getDirectory(), fileDialog.getFile());
                workfile=file;
                str_path = workfile.getParent();
                str_filename1 = workfile.getName();
                str_filename2 = str_filename1.substring(0, str_filename1.lastIndexOf("."));
                FileReader filereader = new FileReader(file);
                BufferedReader bufferedreader = new BufferedReader(filereader);
                String aline;
                while ((aline = bufferedreader.readLine()) != null)
                    text.append(aline + "\r\n");
                filereader.close();
                bufferedreader.close();

            }
            catch (IOException ex) {
                System.out.println(ex);
            }
        }
    }
    public void save(){
        try {

            FileWriter filewriter = new FileWriter(workfile);
            BufferedWriter bufferedwriter = new BufferedWriter(filewriter);
            String texttosave=text.getText();
            bufferedwriter.write(texttosave);
            bufferedwriter.flush();
            bufferedwriter.close();
        }
        catch (IOException ex) {
            System.out.println(ex);
        }
    }
    class ListenerSave implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            save();
        }

    }
    class ListenerQuit implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
    class ListenerCompile implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String str_path = workfile.getParent();
            System.out.println(str_path);
            String str_filename1 = workfile.getName();
            System.out.println(str_filename1);
            String str_filename2 = str_filename1.substring(0, str_filename1.lastIndexOf("."));
            Runtime r = Runtime.getRuntime();
            try{
                save();
                Process proc = r.exec("cmd /c javac "+ str_filename1,null,new File(str_path));
                String cmd ="cmd /c javac "+ str_filename2 +".java && java "+str_filename2;
                System.out.println(cmd);
                BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
                String line = null;
                StringBuilder stringbuilder = new StringBuilder();
                stringbuilder.append("cmd /c javac "+ str_filename2 +".java && java "+str_filename2+"\n");
                while ((line = reader.readLine()) != null) {
                    stringbuilder.append(line + "\n");

                }
                split(stringbuilder.toString());
                display.setText(stringbuilder.toString());

            }
            catch(Exception el){
                el.printStackTrace();
            }
        }
    }

    class ListenerRun implements ActionListener {
        public void actionPerformed(ActionEvent e) {


            try{
                save();
                Runtime r = Runtime.getRuntime();
                Process proc = Runtime.getRuntime().exec("cmd /c javac "+ str_filename1 +" && java "+str_filename2,null,new File(str_path));
                String cmd =""+ str_filename2 +".java && java "+str_filename2;
                System.out.println(cmd);
                BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
                String line = null;
                String save=null;
                StringBuilder stringbuilder = new StringBuilder();
                stringbuilder.append("cmd /c javac "+ str_filename2 +".java && java "+str_filename2+"\n");
                boolean CompileSuccess = false;
                while ((line = reader.readLine()) != null) {
                    stringbuilder.append(line + "\n");
                    CompileSuccess=true;
                }
                reader = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
                while ((line = reader.readLine()) != null) {
                    stringbuilder.append(line + "\n");
                }
                if(CompileSuccess)
                    display.setText("Result: \n"+stringbuilder.toString());
                else{
                    save=stringbuilder.toString();
//                    split(save);
                    display.setText("Errors: \n" + save + "" +
                            "\n Compile Error: Please press F4 to skip to the error line");
                }
//


            }
            catch(Exception el){
                el.printStackTrace();
            }
        }
    }

    public static void main(String[] args){
        new textEditor();
    }

}
