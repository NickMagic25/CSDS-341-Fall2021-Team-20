package Querys;
import java.util.Scanner;

public class User{

    //handles adding a recipe
    private static void addRecipeHandler(ExampleQuery database, String r_name,String cnnStr){
        if(database.recipeExists(r_name,cnnStr))
        {
            System.out.print(r_name + " already exists in database");
            return;
        }
        System.out.println("Type the instructions");
        Scanner scanner=new Scanner(System.in);
        String instructions=scanner.nextLine();
        System.out.println("Whats the difficulty from 1-10?");
        String diff= scanner.nextLine();
        System.out.println("What is the taste of the recipe?");
        String taste= scanner.nextLine();
        System.out.println("What are the ingredients in the recipe (seperate them with a comma)");
        String ingredients= scanner.nextLine();
        database.AR(r_name,instructions,diff,taste,ingredients,cnnStr);
    }

    //handles eating a recipe
    private static void ateHandler(ExampleQuery database, String id, String cnnStr)
    {
        Scanner scanner=new Scanner(System.in);
        System.out.println("What is the name of the recipe you ate");
        String r_name=scanner.nextLine();
        if(database.recipeExists(r_name,cnnStr))
            database.ate(id,r_name,cnnStr);
        else {
            System.out.println("Recipe does not exists in database");
            System.out.println("Adding " + r_name + " to the database");
            addRecipeHandler(database, r_name, cnnStr);
            database.ate(id,r_name,cnnStr);
        }
    }
    /**
     * Requirements:
     * args[0] is the user id
     * args[1] is the type of query they want to do
     *
     */
    public static void main(String[] args)
    {
        String id= args[0];
        String query=args[1];
        String cnnStr="jdbc:sqlserver://arnold-tracker.database.windows.net:1433;database=arnold-tracker;user=team20@arnold-tracker;password={ArnOld2021$};encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
        ExampleQuery database=new ExampleQuery();

        // find all ingredients a user has
        if(query.equals("My-Pantry"))
            database.FAIUH(id,cnnStr);
        // finds nutrition of an ingredient
        if(query.equals("Find-Ingredient-Macros")){
            String ingreName=args[2];
            database.FNOAI(ingreName,cnnStr);
        }
        // finds daily nutritional value for a user
        if(query.equals("Find-My-Macros"))
            database.FDNFU(id,cnnStr);
        // finds all ingredients in a recipe
        if(query.equals("Find-Ingredients-OF")){
            String r_Name=args[2];
            database.FAIIR(r_Name,cnnStr);
        }
        // finds all recipes with the same taste profile
        if(query.equals("Find-Taste")){
            String taste=args[2];
            database.FAIIR(taste,cnnStr);
        }
        // finds all recipes with the same taste profile and from the same region
        if(query.equals("Find-Taste-And-Region")){
            String taste=args[2];
            String region=args[3];
            database.FARWGTP(taste,region,cnnStr);
        }
        // finds the nutrition of a recipe
        if(query.equals("Find-Nutrition-Of")){
            String name=args[2];
            database.FMR(name,cnnStr);
        }
        // finds all recipes that match a given ingredient
        if(query.equals("Find-All-Recipes-With")){
            String ingreName=args[2];
            database.FARI(ingreName,cnnStr);
        }
        // finds all recipes are over/under a given macro
        if(query.equals("Find-Recipe-Restraint")){
            String macroType=args[2];
            String sign=args[3];
            String grams=args[4];
            database.FCM(macroType,sign,grams,cnnStr);
        }
        // finds all recipes that a user can make
        if(query.equals("Find-All-Recipes"))
            database.FAR(id,cnnStr);
        // adds and incredient to the database
        if(query.equals("Add-Ingredient")){
            String name=args[2];
            String amount=args [3];
            database.addIngredientToPantry(id, name, amount,cnnStr);
        }
        // adds a recipe to the database
        if(query.equals("Add-Recipe")){
            String r_name=args[2];
            addRecipeHandler(database,r_name, cnnStr);
        }
        // tracks what a user ate
        if(query.equals("Ate")){
            ateHandler(database,id,cnnStr);
        }
        //finds what a user ate
        if(query.equals("Journal"))
            database.findJornal(id,cnnStr);
    }
}