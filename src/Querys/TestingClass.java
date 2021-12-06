package Querys;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Random;


public class TestingClass {
    private static String cnnStr="jdbc:sqlserver://arnold-tracker.database.windows.net:1433;database=arnold-tracker;user=team20@arnold-tracker;password={ArnOld2021$};encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
    private static ExampleQuery database=new ExampleQuery();

    private static int findRandNum()
    {
        return findRandNum(2147483647);
    }

    private static int findRandNum(int bound){
        Random rand=new Random();
        return rand.nextInt(bound);
    }

    private static int[] findRandIndex(int outLen, int inLen){
        int[] indecies=new int[outLen];
        for(int i=0; i< indecies.length; i++)
            indecies[i]=findRandNum(inLen);
        return indecies;
    }

    private static File makeFile()
    {
        try{
            File out=new File(System.getProperty("user.dir")+ "/src/Querys/Text Files/output.txt");
            if (out.createNewFile())
                System.out.println("Output file created successfully");
            else
                System.out.println("Output file already exists");
            return out;
        }
        catch (IOException e)
        {
            System.out.println("Uh-oh something bad happened");
            e.printStackTrace();
        }
        return null;
    }

    // takes in some common ingredients from a txt file and then 2000 random ingredients
    // updates the nutrition ID for them as well with random vals
    private static List<String> addIngredients()
    {
        List<String> ingredients=new ArrayList<>();
        File file= new File(System.getProperty("user.dir")+"/src/Querys/Text Files/ingredients");
        for(int i=0;i<=4000;i++){
            ingredients.add("test ingredient " + findRandNum());
        }
        //fileToList(ingredients, new Scanner(file)); not needed anymore
        for(String name:ingredients)
        {
            if(!database.FIE(name,cnnStr))
                database.AI(name,cnnStr);

            else
                System.out.println(name + " already in database");
            //get random values for protien, carbs, and fat
            String p=String.valueOf(findRandNum());
            String c= String.valueOf(findRandNum());
            String f= String.valueOf(findRandNum());
            //generate a new nutrition ID
            String nID=database.newNutrition(p,c,f,cnnStr);
            //update the ingredient's nutrition id
            database.updateNutritionIDIngre(name,nID,cnnStr);
            System.out.println("Updated nutrition ID for " + name);
        }
        return ingredients;
    }

    // makes 2000 random recipes, at least one user can make any given recipes
    private  static void randomRecipes(List<String> ingre, List<String> ids)
    {
        int len=ingre.size()-1;
        int i=20;
        while(i<=3000)
        {
            //has 20 ingredients max
            int r_size = findRandNum(20);
            int[] index = findRandIndex(r_size, len);
            try{
                String ingredients=ingre.get(index[0]);
                for(int j=1;j< index.length;j++)
                    ingredients+="," + ingre.get(index[j]);
                //System.out.println("Adding a recipe with ingredients: " + ingredients);
                database.AR("test recipe" + findRandNum(),"Do the thing with the other thing and boom food", "2", "unknown", ingredients, cnnStr );
                String[] pantry= ingredients.split(",");
                String randId=ids.get(findRandNum(ids.size()-1));
                for(String name:pantry)
                    database.addIngredientToPantry(randId,name,String.valueOf(findRandNum(100)),cnnStr);
                i++;
            }
            catch(ArrayIndexOutOfBoundsException e){
                System.out.println("Failed when indexes are " + index);
                e.printStackTrace();
            }
        }

    }
    // generates 1000 new users
    private static List<String> newUsers()
    {
        int i=0;
        List<String> ids=new ArrayList<>();
        while(i<=1000)
        {
            String p=String.valueOf(findRandNum());
            String c= String.valueOf(findRandNum());
            String f= String.valueOf(findRandNum());
            ids.add(database.newUser(p,c,f,cnnStr));
            i++;
        }
        return ids;
    }

    private static void findWhatEveryoneCanMake(List<String> ids)
    {
        try
        {
            File log=makeFile();
            FileWriter wr=new FileWriter(log);
            for(String id:ids){
                String line="User " + id + " can make: \n";
                ArrayList<String> recipes= (ArrayList<String>) database.FAR(id,cnnStr);
                for(String recipe: recipes)
                    line+= recipe+"\n";
                line+="\n \n \n \n";
                wr.write(line);
                wr.flush();
            }
            wr.close();
        }
        catch (IOException e) {
            System.out.println("Bad bad thing happen again");
            e.printStackTrace();
        }

    }

    public static void main(String[] args){
        List<String> ids= newUsers();
        //generate ingredient list
        List<String> ingre=addIngredients();
        //make random recipes
        randomRecipes(ingre,ids);
        //find what every id can make, writes to an output file
        findWhatEveryoneCanMake(ids);
    }
}
