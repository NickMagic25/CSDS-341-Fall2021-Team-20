package Querys;

public class User {
    private int user_id;
    public ExampleQuery database;

    public User(int id){
        database=new ExampleQuery();
        user_id=id;
    }

    public int getUserID(){
        return user_id;
    }

    public static void main(String[] args){
        System.out.println(args[0]);

    }
}
