package org.example;
import java.util.Scanner; // adds me the needed IO interface in a cmd prompt
import org.json.JSONObject;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    public class Task{
        String title;
        String des;

        public Task(String x, String y){
            this.title = x;
            this.des = y;
        }
        public String giveT(){
            return this.title;
        }
        public String giveD(){
            return this.des;
        }
    }

    Task[] todo = new Task[0];
    String temp;


    public void print(String x){   // outputs on console data
        System.out.println(x);
    }    
    public void print(int x){   // the same but guess waht. numbers
        System.out.println(x);
    }
    Scanner scan = new Scanner(System.in);
    public void listen(String x){     // it automates input and sets it into a temp cache named temp as a string format
        print(x);
        this.temp = scan.nextLine();
        print("");
    }
    public void addTask(){        // adds a task

        Task[] clone = new Task[this.todo.length+1];
        listen("Task title");
        String xvar = this.temp;
        listen("Description");
        String yvar = this.temp;

        for(int i = 0 ; i < this.todo.length; i++){
            clone[i] = this.todo[i];
        }
        clone[this.todo.length] = new Task(xvar,yvar);
        this.todo = clone;
        
    }

    public void addTask(String x , String y){        // adds a task

        Task[] clone = new Task[this.todo.length+1];
        String xvar = x;
        String yvar = y;

        for(int i = 0 ; i < this.todo.length; i++){
            clone[i] = this.todo[i];
        }
        clone[this.todo.length] = new Task(xvar,yvar);
        this.todo = clone;

    }
    
    public void showAll(){
        if (this.todo.length == 0 ){
            print("No tasks add some");
        } else {
            for (int i =0; i < this.todo.length; i++){
                print(i+" "+this.todo[i].giveT());
                print(this.todo[i].giveD());
            }
        }
    }
    
    
    public void removeTask(){                                // removes  a task
        this.showAll();
        this.listen("enter no of task to remove");
        
        int rm = Integer.parseInt(this.temp); // sets the index to be ignored
        rm = rm-1; // this is because arrays are indexed 0
        int iclone = 0; // this is the clone index controller
        Task[] clone = new Task[this.todo.length-1];//sets the clone array size

        for (int i = 0; i < this.todo.length;i++){
            if (i != rm){
                clone[iclone]= new Task(this.todo[i].title,this.todo[i].des);
            }else{
                iclone--;
            }
            iclone++;
        }
        this.todo = clone;
    }

    public static void main(String[] args) throws IOException {
        Main app = new Main();
        String content = new String(Files.readAllBytes(Paths.get("data.json")));
        JSONObject json = new JSONObject(content);

        for (String key : json.keySet()) {
            app.addTask(key,json.getString((key)));
        }


        while (true){
            app.listen(" 1 view list / 2 add task / 3 remove task / 0 close program");
            if (app.temp.equals("0")){
                break;
            } else if (app.temp.equals("1")){
                app.showAll();
            } else if (app.temp.equals("2")){
                app.addTask();
            } else if (app.temp.equals("3")){
                app.removeTask();
            }
        }
        app.print("saving data");

        JSONObject parser = new JSONObject(content);

        for (int i = app.todo.length-1; i >=0; i--){
            parser.put(app.todo[i].title,app.todo[i].des);
        }
        try (FileWriter file = new FileWriter("data.json")){
            file.write(parser.toString(2));
        }
        app.print("sucess");
        app.print("closing");
    }
}

