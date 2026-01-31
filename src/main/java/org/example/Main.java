package org.example;

import java.io.Console; // adds me the needed IO interface in a cmd prompt


public class Main {
    Console conso = System.console();

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
    public void listen(String x){     // it automates input and sets it into a temp cache named temp as a string format
        print(x);
        this.temp = conso.readLine();
        print("");
    }
    public void addTask(String x){        // adds a task

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

    public static void main(String[] args){
        Main app = new Main();
        
        while (true){
            app.listen(" 1 view list / 2 add task / 3 remove task / 0 close program");
            if (app.temp.equals("0")){
                break;
            } else if (app.temp.equals("1")){
                app.showAll();
            } else if (app.temp.equals("2")){
                app.addTask(app.temp);
            } else if (app.temp.equals("3")){
                app.removeTask();
            }
        }
        app.print("closing program");
    }
}

