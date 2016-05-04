package sample;

import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.web.HTMLEditor;
import javafx.scene.control.*;
import javafx.geometry.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.reflect.Array;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.text.Text;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.*;



public class Main extends Application {

    private static final int q0 = 0;
    private static final int q1 = 1;
    private static final int q2 = 2;
    private static final int q3 = 3;
    private static final int q4 = 4;
    private static final int q5 = 5;
    private static final int q6 = 6;
    private static final int q7 = 7;
    private static final int q8 = 8;
    private static final int q9 = 9;
    private static final int q10 =10 ;
    private static final int q11 =11 ;
    private static final int q12 =12 ;
    private static final int q13 =13 ;
    private static final int q14 =14 ;
    private static final int q15 =15 ;
    private static final int q16 =16 ;
    private static final int q17 =17 ;
    private static final int q18 =18 ;
    private static final int q19 =19 ;
    private static final int q20 =20 ;
    private static final int q21 =21 ;
    private static final int q22 =22 ;
    private static final int q23 =23 ;
    private static final int q24 =24 ;
    private static final int q25 =25 ;
    private static final int q26 =26 ;
    private static final int q27 =27 ;
    private static final int q28 =28 ;
    private static final int q29 =29 ;
    private static final int q30 =30 ;
    private static final int q31 =31 ;
    private static final int q32 =32 ;
    private static final int q33 =33 ;
    private static final int q34 =34 ;
    private static final int q35 =35 ;
    private static final int q36 =36 ;
    private static final int q37 =37 ;
    private static final int q38 =38 ;
    private static final int q39 =39 ;
    private static final int q40 =40 ;
    private static final int q41 = 41;
    private static final int q50 = 42;

    private static int state = q0;

    private static String blackText = "";
    private static String coloredText = "<html dir=\"ltr\"><head></head><body contenteditable=\"true\">";
    private static String splitText[];
    private static int icounter = 0;

    private static int passcounter = 0;

    public static String varStatement;
    public static String printStatement;
    public static String identStatement;
    public static String intStatement;
    public static String stringStatement;
    public static String commentStatement;
    public static String errorStatement;


    @Override
    public void start(Stage stage) {
        stage.setTitle("PAIR IDE");
        stage.setWidth(500);
        stage.setHeight(500);
        Scene scene = new Scene(new Group());

        final VBox root = new VBox();
        root.setPadding(new Insets(8, 8, 8, 8));
        root.setSpacing(5);
        root.setAlignment(Pos.BOTTOM_LEFT);

        final GridPane grid = new GridPane();
        grid.setVgap(5);
        grid.setHgap(10);

        final HTMLEditor htmlEditor = new HTMLEditor();
        final Button colorButton = new Button("Verify and color program");
        colorButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent arg0) {
                blackText = htmlEditor.getHtmlText();
                //System.out.println(blackText);
                blackText = blackText.replaceAll("<p>" , ",");
                eliminateHTMLTags(blackText);
                blackText = blackText.substring(1);
                blackText = blackText.replaceAll("  , ", ",");
                codeChecker(blackText);
                htmlEditor.setHtmlText(coloredText);

                //reset everything so the button is reusable
                blackText = "";
                coloredText = "<html dir=\"ltr\"><head></head><body contenteditable=\"true\">";
                String splitText[];
                icounter = 0;
                state = q0;


            }
            });
        root.getChildren().addAll(htmlEditor, colorButton);

        final Label htmlLabel = new Label();
        htmlLabel.setWrapText(true);

        scene.setRoot(root);
        stage.setScene(scene);
        stage.show();

        htmlEditor.setStyle(
                "-fx-font: 12 cambria;"
                        + "-fx-border-color: deepskyblue; "
                        + "-fx-border-style: ridge;"
                        + "-fx-border-width: 4;"
        );

    }






    private void eliminateHTMLTags(String temp) {

            Pattern pattern = Pattern.compile("<[^>]*>");
            Matcher matcher = pattern.matcher(temp);
            final StringBuffer sb = new StringBuffer(temp.length());
            while (matcher.find()) {
                matcher.appendReplacement(sb, " ");
            }
            matcher.appendTail(sb);
            blackText = sb.toString().trim();
    }

    public static void main(String[] args) {
        launch(args);
    }



    public void statementSorter(String in){

        //this splits the different statements into groups of statements
        splitText = in.split(",");


            if (state == q5) { // TODO: working
                 varColorizer(splitText[icounter]);
                System.out.println("var: " + splitText[icounter]);

            } else if (state == q18) { // TODO: working

                printColorizer(splitText[icounter]);

                System.out.println("print: " + splitText[icounter]);

            } else if (state == q28) { // TODO: CHECK
                identColorizer(splitText[icounter]);

                System.out.println("ident: " + splitText[icounter]);

            } else if (state == q29) {// TODO: working
                intColorizer(splitText[icounter]);

                System.out.println("int: " + splitText[icounter]);

            } else if (state == q35) { // TODO: working
                stringColorizer(splitText[icounter]);

                System.out.println("string: " + splitText[icounter]);

            } else if (state == q41) {// TODO: CHECK COLOR SCHEME
                commentColorizer(splitText[icounter]);

                System.out.println("comment: " + splitText[icounter]);

            } else if (state == q50) { // TODO: CHECK
                errorColorizer(splitText[icounter]);

                System.out.println("error: " + splitText[icounter]);


            } /*else if (state != q5 || state != q18 || state != q28 || state != q29 || state != q35 || state != q41 || state != q50) {
                System.out.println("counter: " + icounter);
                errorColorizer(splitText[icounter]);
                if (splitText.length > icounter){
                    icounter += 1;
                }
                System.out.println("error: " + splitText[icounter - 1]);
            }*/

    }

    /*
    <html dir="ltr"><head></head><body contenteditable="true">

    <p><font face="Lucida Grande" color="#669966">var x</font></p>

    <p><font face="Lucida Grande" color="#331a80">print ( y )</font></p>

    </body></html>


     */

    public void varColorizer(String in){
        coloredText += "<p><font face=\"Lucida Grande\" color=\"#002bff\">" + in + "</font></p>";
    }

    public void printColorizer(String in){
        coloredText += "<p><font face=\"Lucida Grande\" color=\"#7c00ff\">" + in + "</font></p>";
    }

    public void identColorizer(String in){
        coloredText += "<p><font face=\"Lucida Grande\" color=\"#00ff45\">" + in + "</font></p>";
    }

    public void intColorizer(String in){
        coloredText += "<p><font face=\"Lucida Grande\" color=\"#00ecff\">" + in + "</font></p>";
    }

    public void stringColorizer(String in){
        coloredText += "<p><font face=\"Lucida Grande\" color=\"#ffb400\">" + in + "</font></p>";
    }

    public void commentColorizer(String in){
        coloredText += "<p><font face=\"Lucida Grande\" color=\"#8d8d8d\" style=\"background-color: ##fff400 \">" + in + "</font></p>";
    }

    public void errorColorizer(String in){
        coloredText += "<p><font face=\"Lucida Grande\" color=\"#ff0000\">" + in + "</font></p>";
    }



    public void codeChecker(String in){
        in = in.substring(1);
        System.out.println("in: " + in);
    int loc =  0; //only used for initialization

        splitText = in.split(",");

        for (int j = 0; j < splitText.length; j++) {
            String temp = splitText[j];

        for (int i = 0; i < temp.length(); i++) {
            char c = temp.charAt(i);
            passcounter++;

            switch (c){
                case 'v':
                    loc = 0;
                    break;

                case 'a':
                    loc = 1;
                    break;

                case 'r':
                    loc = 2;
                    break;

                case 'p':
                    loc = 3;
                    break;

                case 'i':
                    loc = 4;
                    break;

                case 'n':
                    loc = 5;
                    break;

                case 't':
                    loc = 6;
                    break;

                case 'b':
                case 'c':
                case 'd':
                case 'e':
                case 'f':
                case 'g':
                case 'h':
                case 'j':
                case 'k':
                case 'l':
                case 'm':
                case 'o':
                case 'q':
                case 's':
                case 'u':
                case 'w':
                case 'x':
                case 'y':
                case 'z':
                    loc = 7;
                    break;

                case'0':
                case'1':
                case'2':
                case'3':
                case'4':
                case'5':
                case'6':
                case'7':
                case'8':
                case'9':
                    loc= 8;
                    break;

                case '+':
                    loc = 9;
                    break;

                case '=':
                    loc = 10;
                    break;

                case ' ':
                    loc = 11;
                    break;

                case '"':
                    loc = 12;
                    break;

                case '(':
                    loc = 13;
                    break;

                case ')':
                    loc = 14;
                    break;

                case '*':
                    loc = 15;
                    break;

                case '-':
                    loc = 16;
                    break;

                case ',':
                    loc = 11;
                    break;

            }

            try {
                System.out.println("q" + state + " | " + loc);
                state = delta[state][loc];
            } catch (ArrayIndexOutOfBoundsException ex) {
                state = q50;
            }
            statementSorter(in);

        }
            icounter +=1;
            passcounter = 0;
        }
        coloredText += "</body></html>";

        }

    static private int[][] delta =
            {
                    // A = {a-z} - {v,a,r,p,i,n,t}
                    // 0    1    2    3    4    5    6    7    8    9   10   11   12   13   14   15   16
                    // v    a    r    p    i    n    t    A  {0-9}  +    =    _    "    (    )    *    -
                    { q1, q24, q24,  q6, q24, q24, q24, q24, q50, q50, q50, q50, q50, q50, q50, q36, q50}, //q0
                    {q24,  q2, q24, q24, q24, q24, q24, q24, q50, q50, q50, q50, q50, q50, q50, q50, q50}, //q1
                    {q24, q24,  q3, q24, q24, q24, q24, q24, q50, q50, q50, q50, q50, q50, q50, q50, q50}, //q2
                    {q50, q50, q50, q50, q50, q50, q50, q50, q50, q50, q50,  q4, q50, q50, q50, q50, q50}, //q3
                    { q5,  q5,  q5,  q5,  q5,  q5,  q5,  q5, q50, q50, q50, q50, q50, q50, q50, q50, q50}, //q4
                    { q5,  q5,  q5,  q5,  q5,  q5,  q5,  q5, q50, q50, q50,  q0, q50, q50, q50, q36, q50}, //q5
                    {q24, q24,  q7, q24, q24, q24, q24, q24, q50, q50, q50, q50, q50, q50, q50, q50, q50}, //q6
                    {q24, q24, q24, q24,  q8, q24, q24, q24, q50, q50, q50, q50, q50, q50, q50, q50, q50}, //q7
                    {q24, q24, q24, q24, q24,  q9, q24, q24, q50, q50, q50, q50, q50, q50, q50, q50, q50}, //q8
                    {q24, q24, q24, q24, q24, q24, q10, q24, q50, q50, q50, q50, q50, q50, q50, q50, q50}, //q9
                    {q50, q50, q50, q50, q50, q50, q50, q50, q50, q50, q50, q11, q50, q50, q50, q50, q50}, //q10
                    {q50, q50, q50, q50, q50, q50, q50, q50, q50, q50, q50, q50, q50, q12, q50, q50, q50}, //q11
                    {q50, q50, q50, q50, q50, q50, q50, q50, q50, q50, q50, q13, q50, q50, q50, q50, q50}, //q12
                    {q23, q23, q23, q23, q23, q23, q23, q23, q14, q50, q50, q50, q19, q50, q50, q50, q50}, //q13
                    {q50, q50, q50, q50, q50, q50, q50, q50, q14, q13, q50, q17, q50, q50, q50, q50, q50}, //q14
                    {q50, q50, q50, q50, q50, q50, q50, q50, q50, q50, q50, q50, q50, q50, q50, q50, q50}, //q15 ---
                    {q50, q50, q50, q50, q50, q50, q50, q50, q50, q50, q50, q50, q50, q50, q50, q50, q50}, //q16 ---
                    {q50, q50, q50, q50, q50, q50, q50, q50, q50, q50, q50, q50, q50, q50, q18, q50, q50}, //q17
                    {q50, q50, q50, q50, q50, q50, q50, q50, q50, q50, q50,  q0, q50, q50, q50, q36, q50}, //q18
                    {q50, q50, q50, q50, q50, q50, q50, q50, q50, q50, q50, q20, q50, q50, q50, q50, q50}, //q19
                    {q20, q20, q20, q20, q20, q20, q20, q20, q50, q50, q50, q21, q50, q50, q50, q50, q50}, //q20
                    {q20, q20, q20, q20, q20, q20, q20, q20, q50, q50, q50, q50, q22, q50, q50, q50, q50}, //q21
                    {q50, q50, q50, q50, q50, q50, q50, q50, q50, q50, q50, q17, q50, q50, q50, q50, q50}, //q22
                    {q50, q50, q50, q50, q50, q50, q50, q50, q50, q50, q50, q17, q50, q50, q50, q50, q50}, //q23
                    {q24, q24, q24, q24, q24, q24, q24, q24, q50, q50, q50, q25, q50, q50, q50, q50, q50}, //q24
                    {q50, q50, q50, q50, q50, q50, q50, q50, q50, q50, q26, q50, q50, q50, q50, q50, q50}, //q25
                    {q50, q50, q50, q50, q50, q50, q50, q50, q50, q50, q50, q27, q50, q50, q50, q50, q50}, //q26
                    {q28, q28, q28, q28, q28, q28, q28, q28, q29, q50, q50, q50, q32, q50, q50, q50, q50}, //q27
                    {q28, q28, q28, q28, q28, q28, q28, q28, q50, q50, q50,  q0, q50, q50, q50, q36, q50}, //q28
                    {q50, q50, q50, q50, q50, q50, q50, q29, q29, q27, q50,  q0, q50, q50, q50, q36, q50}, //q29
                    {q50, q50, q50, q50, q50, q50, q50, q50, q50, q50, q50, q50, q50, q50, q50, q50, q50}, //q30 ---
                    {q50, q50, q50, q50, q50, q50, q50, q50, q50, q50, q50, q50, q50, q50, q50, q50, q50}, //q31 ---
                    {q50, q50, q50, q50, q50, q50, q50, q50, q50, q50, q50, q33, q50, q50, q50, q50, q50}, //q32
                    {q33, q33, q33, q33, q33, q33, q33, q33, q50, q50, q50, q34, q50, q50, q50, q50, q50}, //q33
                    {q33, q33, q33, q33, q33, q33, q33, q33, q50, q50, q50, q50, q35, q50, q50, q50, q50}, //q34
                    {q50, q50, q50, q50, q50, q50, q50, q50, q50, q50, q50,  q0, q50, q50, q50, q36, q50}, //q35
                    {q50, q50, q50, q50, q50, q50, q50, q50, q50, q50, q50, q50, q50, q50, q50, q50, q37}, //q36
                    {q50, q50, q50, q50, q50, q50, q50, q50, q50, q50, q50, q38, q50, q50, q50, q50, q50}, //q37
                    {q38, q38, q38, q38, q38, q38, q38, q38, q50, q50, q50, q39, q50, q50, q50, q50, q50}, //q38
                    {q38, q38, q38, q38, q38, q38, q38, q38, q50, q50, q50, q50, q50, q50, q50, q50, q40}, //q39
                    {q50, q50, q50, q50, q50, q50, q50, q50, q50, q50, q50, q50, q50, q50, q50, q41, q50}, //q40
                    {q50, q50, q50, q50, q50, q50, q50, q50, q50, q50, q50,  q0, q50, q50, q50, q50, q50}, //q41
                    {q50, q50, q50, q50, q50, q50, q50, q50, q50, q50, q50, q50, q50, q50, q50, q50, q50}  //q50
            };


}