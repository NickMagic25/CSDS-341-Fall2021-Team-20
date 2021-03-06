package Querys;

import java.sql.*;
import java.util.Locale;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;

public class ExampleQuery {
	public static void main(String[] args) {

        System.out.println("performing setup");
        String cnnStr = "jdbc:sqlserver://arnold-tracker.database.windows.net:1433;database=arnold-tracker;user=team20@arnold-tracker;password={ArnOld2021$};encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
        String userName = "team20";
        String userPassword = "ArnOld2021$";
        
        ExampleQuery query = new ExampleQuery();
        System.out.println("connecting");
		query.addIngredientToPantry("1", "Brown Rice", "10",cnnStr);
        //System.out.println(query.recipeExists("Beef stew", cnnStr));
        
        //System.out.println(query.FIE("alsdkjhfa", cnnStr));
    }
	
	private void SelectAzureSQL(String sql, String cnnStr) {
		System.out.println("Selecting data");
		ResultSet resultSet = null;
		try(Connection cnn = DriverManager.getConnection(cnnStr); Statement statement = cnn.createStatement();){
			resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				System.out.println(resultSet.getString(1) + "," + resultSet.getString(2)+ "," + resultSet.getString(3));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void ExecuteAzureSQL(String userName, String userPassword, String sql, String cnnStr) {
		System.out.println("Executing SQL Statement");
		try(Connection cnn = DriverManager.getConnection(cnnStr); PreparedStatement statement = cnn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);){
			ResultSet resultSet = null;
			statement.execute();
			resultSet = statement.getGeneratedKeys();
			while(resultSet.next()) {
				System.out.println("key: " + resultSet.getString(1));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}

	// find all ingredients user has
	public ResultSet FAIUH(String user_ID, String cnnStr) {
		System.out.println("Looking for all ingredient User" + user_ID + " has");
		String sql = "Select * From [Has] Where [User_ID] = " + user_ID;
		ResultSet resultSet = null;
		try(Connection cnn = DriverManager.getConnection(cnnStr); Statement statement = cnn.createStatement();){
			resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				System.out.println("Ingredient Name: " + resultSet.getString(2)+ ";  Serving Size: " + resultSet.getString(3));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return resultSet;
	}

	// find nutrition of an ingredient
	public ResultSet FNOAI(String ingre_Name, String cnnStr) {
		System.out.println("Looking for nutrition detail for " + ingre_Name);
		String sql = "Select * From [Nutrition] Where [Nutrition_ID] = (Select [Nutrition_ID] From [Contains] Where [Ingredient_Name] = '" + ingre_Name.toLowerCase() + "')";
		ResultSet resultSet = null;
		try(Connection cnn = DriverManager.getConnection(cnnStr); Statement statement = cnn.createStatement();){
			resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				System.out.println("Protein(g): " + resultSet.getString(2)+ ";   Carbs(g): " + resultSet.getString(3) + ";   Fat(g): " + resultSet.getString(4) + ";   Cals: " + resultSet.getString(5));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return resultSet;
	}

	//find daily nutritional values for users
	public ResultSet FDNFU(String user_ID, String cnnStr) {
		System.out.println("Looking for User" + user_ID + "'s daily nutrition");
		String sql = "Select * From [User] Where [User_ID] = " + user_ID;
		ResultSet resultSet = null;
		try(Connection cnn = DriverManager.getConnection(cnnStr); Statement statement = cnn.createStatement();){
			resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				System.out.println("Proteins: " + resultSet.getString(2)+ ";   Carbs: " + resultSet.getString(3)+ ";   Fats: " + resultSet.getString(4));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return resultSet;
	}

	// find all ingredients in a recipe
	public ResultSet FAIIR(String resp_Name, String cnnStr) {
		System.out.println("Looking for all ingredient needed for " + resp_Name);
		String sql = "Select * From [Uses] Where [Recipe_Name] = '" + resp_Name.toLowerCase() +"'";
		ResultSet resultSet = null;
		try(Connection cnn = DriverManager.getConnection(cnnStr); Statement statement = cnn.createStatement();){
			resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				System.out.println("Ingredient Name: " + resultSet.getString(1)+ ";   Amount: " + resultSet.getString(3));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return resultSet;
	}

	// find all recipes with general taste profile?
	public ResultSet FARWGTP(String taste_Profile, String cnnStr) {
		System.out.println("Looking for all recipe with " + taste_Profile);
		String sql = "Select * From [Taste_Profile] Where [Taste] = '" + taste_Profile.toLowerCase() +"'";
		ResultSet resultSet = null;
		try(Connection cnn = DriverManager.getConnection(cnnStr); Statement statement = cnn.createStatement();){
			resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				System.out.println("Recipe Name: " + resultSet.getString(1)+ ";   Dietary Restriction: " + resultSet.getString(4));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		sql = "Select * From [Taste_Profile] Where [Region] = '" + taste_Profile +"'";
		resultSet = null;
		try(Connection cnn = DriverManager.getConnection(cnnStr); Statement statement = cnn.createStatement();){
			resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				System.out.println("Recipe Name: " + resultSet.getString(1)+ ";   Dietary Restriction: " + resultSet.getString(4));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return resultSet;
	}

	// find all recipe with general taste profile and region
	public ResultSet FARWGTP(String taste, String region, String cnnStr) {
		System.out.println("Looking for all recipe with " + taste + " and " + region);
		String sql = "Select * From [Taste_Profile] Where [Taste] = '" + taste.toLowerCase() +"' and [Region] = '" + region.toLowerCase() + "'";
		ResultSet resultSet = null;
		try(Connection cnn = DriverManager.getConnection(cnnStr); Statement statement = cnn.createStatement();){
			resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				System.out.println("Recipe Name: " + resultSet.getString(1) + ";   Dietary Restriction: " + resultSet.getString(4));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return resultSet;
	}

	// find nutrition of a recipe
	public ResultSet FMR(String recipe_name, String cnnStr){
		System.out.println("Looking for macros of " + recipe_name);
		String sql="Select n1.Grams_Protien, n1.Grams_Carbs, n1.Grams_Fat From [Nutrition] as n1, [Macro_Contents] as m1 Where (m1.Recipe_Name='"+recipe_name.toLowerCase()+"') and (n1.Nutrition_ID=m1.Nutrition_ID)";
		ResultSet macros=null;
		try(Connection cnn = DriverManager.getConnection(cnnStr); Statement statement = cnn.createStatement();){
			macros = statement.executeQuery(sql);
			while(macros.next()) {
				System.out.println("Protien(g): "+ macros.getString("Grams_Protien")+" Carbs(g): "+ macros.getString("Grams_Carbs")+ " Fat(g): "+ macros.getString("Grams_Fat"));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return macros;
	}

	// find all recipes that matches a given ingredient
	public ResultSet FARI(String ingre_name, String cnnStr){
		System.out.println("Finding all recipes that use "+ ingre_name.toLowerCase() );
		String sql="SELECT u.Recipe_Name, u.Amount_Of FROM [USES] as u  WHERE u.Ingredient_Name='"+ingre_name.toLowerCase()+"'";
		ResultSet recipes=null;
		try(Connection cnn = DriverManager.getConnection(cnnStr); Statement statement = cnn.createStatement();){
			recipes = statement.executeQuery(sql);
			while(recipes.next()) {
				System.out.println("Name: "+ recipes.getString("Recipe_Name") +" Amount Required: "+recipes.getString("Amount_Of"));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return recipes;
	}

	/***
	 * finds all recipes with a certian amount of macros or more/less
	 * sign is a string that is either > or <
	 * macroTypre is a string that is either Proitien, Fat, or Carbs
	 *grams is a string that is a number (20,30,40, etc)
	 ***/
	public ResultSet FCM(String macroType, String sign, String grams, String cnnStr){
		String nSign="less";
		if(sign.equals(">"))
			nSign="more";
		System.out.println("Finding all recipes with " + grams +" of " + macroType + " or " + nSign);
		String sql="SELECT n.Grams_"+ macroType+ " as Macro, m.Recipe_Name as Name FROM [Macro_Contents] as m, [Nutrition] as n WHERE m.Nutrition_ID=n.Nutrition_ID and n.Grams_" + macroType + sign  +"=" + grams;
		ResultSet macros=null;
		try(Connection cnn = DriverManager.getConnection(cnnStr); Statement statement = cnn.createStatement();){
			macros = statement.executeQuery(sql);
			while(macros.next()) {
				System.out.println(macros.getString("Name") + " has " + macros.getString("Macro") + " grams of " + macroType);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return macros;
	}

	// finds all recipes a user can make
	public List<String> FAR(String user_ID,String cnnStr){
		System.out.println("Finding all recipes you can make");
		String sql="SELECT u.Recipe_Name FROM [Uses] as u  WHERE u.Amount_of <= all (Select h.Serving_Size as Amount_Of From [Has] as h WHERE h.User_Id="+ user_ID+ " )";
		ResultSet ans=null;
		ArrayList<String> recipes=new ArrayList<String>();
		try(Connection cnn = DriverManager.getConnection(cnnStr); Statement statement = cnn.createStatement();){
			ans = statement.executeQuery(sql);
			while(ans.next()) {
				String r=ans.getString("Recipe_Name");
				System.out.println("You can make "+ r);
				recipes.add(r);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return recipes;
	}
	
	/***
	 * Traverse through the database and see if the given ingredient exists
	 * @return true if exists, false if not
	 */
	public boolean FIE(String Name, String cnnStr) {
		System.out.println("Finding " + Name);
		String sql= "Select [Name] From [Ingredient] Where [Name] = '" + Name.toLowerCase() + "'";
		ResultSet resultSet = null;
		try(Connection cnn = DriverManager.getConnection(cnnStr); Statement statement = cnn.createStatement();){
			resultSet = statement.executeQuery(sql);
			return resultSet.next(); //true if exists, false if not
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/***
	 * Add ingredient into database
	 * @param Name
	 * @param cnnStr
	 */
	public void AI(String Name, String cnnStr) {
		System.out.println("Adding "+ Name +" into database");
		String sql = "Insert into [Ingredient]([Name], [Food_type], [Nutrition_ID]) Values('" + Name.toLowerCase()+ "', 'unknown', -1)";
		try(Connection cnn = DriverManager.getConnection(cnnStr); PreparedStatement statement = cnn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);){
			ResultSet resultSet = null;
			statement.execute();
			resultSet = statement.getGeneratedKeys();
			if(resultSet.next()) {
				System.out.println("Added to database!");
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	/***
	 * Find a Taste_Profile exist or not
	 * @param Name
	 * @param cnnStr
	 * @return
	 */
	private boolean FTP(String Name, String cnnStr) {
		System.out.println("Finding " + Name);
		String sql = "Select * From [Taste_Profile] Where [Taste] = '" + Name.toLowerCase() +"'";
		ResultSet resultSet = null;
		try(Connection cnn = DriverManager.getConnection(cnnStr); Statement statement = cnn.createStatement();){
			resultSet = statement.executeQuery(sql);
			return resultSet.next();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/***
	 * Add Taste_Profile
	 */
	public void ATP(String Taste, String R_Name, String cnnStr) {
		System.out.println("Adding Taste_Profile into database");
		String sql = "Insert into [Taste_Profile]([R_Name], [Taste], [Region], [Dietary_Rest]) Values('" + R_Name.toLowerCase()+ "', '" + Taste.toLowerCase() + "', 'Unknown', 'no')";
		try(Connection cnn = DriverManager.getConnection(cnnStr); PreparedStatement statement = cnn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);){
			ResultSet resultSet = null;
			statement.execute();
			resultSet = statement.getGeneratedKeys();
			while(resultSet.next()) {
				System.out.println("key: " + resultSet.getString(1));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	/***
	 * Add data into Has_Taste
	 * @param Taste
	 * @param R_Name
	 */
	public void AHT(String Taste, String R_Name, String cnnStr) {
		System.out.println("Adding Has_Taste into database");
		String sql = "Insert into [Has_Taste]([Taste], [R_Name], [Name]) Values('" + Taste.toLowerCase() + "', '" + R_Name.toLowerCase() + "', '" + R_Name.toLowerCase() + "')";
		try(Connection cnn = DriverManager.getConnection(cnnStr); PreparedStatement statement = cnn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);){
			ResultSet resultSet = null;
			statement.execute();
			resultSet = statement.getGeneratedKeys();
			while(resultSet.next()) {
				System.out.println("key: " + resultSet.getString(1));
			}
		}
		catch(SQLException e) {
			System.out.println(Taste.toLowerCase() + " " + R_Name.toLowerCase());
			e.printStackTrace();
		}
	}
	
	/***
	 * Add Macro_Contents into database
	 * @param R_Name
	 * @param N_ID
	 */
	public void AMC(String R_Name, String N_ID, String cnnStr) {
		System.out.println("Adding Macro_Contents into database");
		String sql = "Insert into [Macro_Contents]([Recipe_Name], [Nutrition_ID]) Values('" + R_Name.toLowerCase() + "', " + N_ID + ")";
		try(Connection cnn = DriverManager.getConnection(cnnStr); PreparedStatement statement = cnn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);){
			ResultSet resultSet = null;
			statement.execute();
			resultSet = statement.getGeneratedKeys();
			while(resultSet.next()) {
				System.out.println("key: " + resultSet.getString(1));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	/***
	 * Add Uses into databases
	 * @param I_Name
	 * @param R_Name
	 * @param cnnStr
	 */
	public void AU(String I_Name, String R_Name, String cnnStr) {
		System.out.println("Adding Uses into database");
		String sql = "Insert into [Uses]([Ingredient_Name], [Recipe_Name], [Amount_Of]) Values('" + I_Name.toLowerCase() + "', '" + R_Name.toLowerCase() + "', 1)";
		try(Connection cnn = DriverManager.getConnection(cnnStr); PreparedStatement statement = cnn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);){
			ResultSet resultSet = null;
			statement.execute();
			resultSet = statement.getGeneratedKeys();
			while(resultSet.next()) {
				System.out.println("key: " + resultSet.getString(1));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}

	/***
	 * adding recipe
	 */
	public void AR(String Name, String instructions, String Diff, String Taste, String Ingredients, String cnnStr){
		if(recipeExists(Name, cnnStr))
		{
			System.out.println(Name+ " already exists");
			return;
		}
		System.out.println("Adding recipe " + Name.toLowerCase() + "into the database");
		String[] Ingredient = Ingredients.split(",");
		Boolean[] exist = new Boolean[Ingredient.length];
		for(Boolean e : exist)
			e = true;
		for(int i = 0; i < Ingredient.length; i++){
			if(!FIE(Ingredient[i], cnnStr))
				exist[i] = false;
		}
		for(int i = 0; i < exist.length; i++) {
			if(exist[i]!=null && !exist[i])
				AI(Ingredient[i], cnnStr);
		}
		String sql = "Insert into [Recipe]([Name], [Instructions], [Diffculty_Level], [Nutrition_ID], [Taste_Profile]) "
				+ "Values('"+ Name.toLowerCase() +"', '" + instructions + "', " + Diff + ", -1, '" + Taste + "')";
		try(Connection cnn = DriverManager.getConnection(cnnStr);
			PreparedStatement statement = cnn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);){
				ResultSet resultSet = null;
				statement.execute();
				resultSet = statement.getGeneratedKeys();
				while(resultSet.next()) {
					System.out.println("key: " + resultSet.getString(1));
				}
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		if(!FTP(Taste, cnnStr)) {
			ATP(Taste, Name, cnnStr);
		}
		AHT(Taste, Name, cnnStr);
		AMC(Name, "-1", cnnStr);
		for(String i: Ingredient) {
			AU(i, Name, cnnStr);
		}
	}

	// Creates a new user
	public String newUser(String gProtien,String gCarbs, String gFat, String cnnStr)
	{
		System.out.println("Making new user...");
		int possibleID=findRandNum();
		while(userFound(String.valueOf(possibleID),cnnStr))
		{
			possibleID=findRandNum();
		}
		String realID=String.valueOf(possibleID);
		String sql="Insert into [User]([user_id], [daily_protien], [daily_carbs], [daily_fat]) Values(" + realID + ", "+ gProtien + ", "+ gCarbs +", " + gFat + ")";
		try(Connection cnn = DriverManager.getConnection(cnnStr); PreparedStatement statement = cnn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);){
			ResultSet resultSet = null;
			statement.execute();
			System.out.println("New user created!");
			System.out.println("User ID: " + realID);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}

		return realID;
	}

	// returns true if the user id already exists
	public static boolean userFound(String ID, String cnnStr)
	{
		String sql="SELECT user_id  FROM [User] WHERE user_id="+ID;
		ResultSet resultSet = null;
		try(Connection cnn = DriverManager.getConnection(cnnStr); Statement statement = cnn.createStatement();){
			resultSet = statement.executeQuery(sql);
			return resultSet.next(); // true if found, false if not in database
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	private static int findRandNum()
	{
		Random rand=new Random();
		return rand.nextInt(2147483647);
	}

	// returns true if the nutrition id is found
	public boolean nutritionIDFound(String ID, String cnnStr){
		String sql="SELECT Nutrition_ID FROM [Nutrition] WHERE Nutrition_ID=" + ID;
		ResultSet resultSet = null;
		try(Connection cnn = DriverManager.getConnection(cnnStr); Statement statement = cnn.createStatement();){
			resultSet = statement.executeQuery(sql);
			return resultSet.next(); // true if found, false if not in database
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	//creates a new nutrition ID and returns the new ID
	public String newNutrition(String gProtien, String gCarbs, String gFat,String cnnStr) {
		System.out.println("Creating new nutrition information");
		int possibleId = findRandNum();
		while (nutritionIDFound(String.valueOf(possibleId), cnnStr)) {
			possibleId = findRandNum();
		}
		String realID = String.valueOf(possibleId);
		String sql = "Insert into [Nutrition]([Nutrition_ID], [Grams_Protien], [Grams_Carbs], [Grams_Fat])" +
				"Values("+ realID + ","+gProtien+ "," + gCarbs + "," + gFat + ")";
		try(Connection cnn = DriverManager.getConnection(cnnStr); PreparedStatement statement = cnn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);){
			ResultSet resultSet = null;
			statement.execute();
			System.out.println("Nutrition information created!");
			System.out.println("Nutrition ID: " + realID);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return realID;
	}

	public void ate(String user_id, String r_name, String cnnStr){
		System.out.println("User " + user_id + " ate " + r_name);
		String sql="Insert into [Ate](User_ID, Recipe_Name, Nutrition_ID) Values(" + user_id + "," + r_name.toLowerCase() + ", -1)";
		try(Connection cnn = DriverManager.getConnection(cnnStr); PreparedStatement statement = cnn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);){
			ResultSet resultSet = null;
			statement.execute();
			System.out.println("Successfully recorded!");
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean recipeExists(String r_name, String cnnStr){
		String sql= "Select Name FROM [Recipe] WHERE Name='" + r_name.toLowerCase() + "'";
		ResultSet resultSet = null;
		try(Connection cnn = DriverManager.getConnection(cnnStr); Statement statement = cnn.createStatement();){
			resultSet = statement.executeQuery(sql);
			return resultSet.next(); // true if found, false if not in database
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public void findJornal(String user_id, String cnnStr){
		System.out.println("Finding what user " + user_id +" ate");
		String sql="Select Recipe_Name From [Ate] Where User_Id=" + user_id;
		ResultSet ans=null;
		try(Connection cnn = DriverManager.getConnection(cnnStr); Statement statement = cnn.createStatement();){
			ans = statement.executeQuery(sql);
			while(ans.next()) {
				System.out.println("Ate "+ ans.getString("Recipe_Name"));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	// adds an ingredient to a user's pantry
	public void addIngredientToPantry(String user_ID, String ingre_name,String amount ,String cnnStr)
	{
		if(!FIE(ingre_name, cnnStr))
		{
			AI(ingre_name,cnnStr);
		}
		if(notInPantry(user_ID,ingre_name,cnnStr))
			addIngredient(user_ID,ingre_name,amount,cnnStr);
		else
		{
			String sql="Update [Has] Set Serving_Size=Serving_Size+"+ amount + "WHERE User_ID="+ user_ID + "and Name='"+ ingre_name.toLowerCase() +"'";
			try(Connection cnn = DriverManager.getConnection(cnnStr); PreparedStatement statement = cnn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);){
				ResultSet resultSet = null;
				statement.execute();
				System.out.println("Ingredient added!");
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}

	//adds an existing ingredient into a users pantry
	private void addIngredient(String user_ID, String ingre_name,String amount ,String cnnStr){
		String sql="Insert [Has](User_ID, Name, Serving_Size) Values("+ user_ID + ",'" + ingre_name.toLowerCase() + "'," + amount + ")";
		try(Connection cnn = DriverManager.getConnection(cnnStr); PreparedStatement statement = cnn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);){
			ResultSet resultSet = null;
			statement.execute();
			System.out.println("Ingredient added!");
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}

	// checks to see if an ingredient is already in a users pantry returns true if it is
	private boolean notInPantry(String user_ID, String ingre_name, String cnnStr)
	{
		String sql="SELECT Name FROM [Has]  WHERE User_Id="+ user_ID +"and Name='"+ ingre_name.toLowerCase() +"'";
		ResultSet resultSet = null;
		try(Connection cnn = DriverManager.getConnection(cnnStr); Statement statement = cnn.createStatement();){
			resultSet = statement.executeQuery(sql);
			return !resultSet.next(); // true if not found, false if in database
		}
		catch (SQLException e) {
			e.printStackTrace();
		}

		return true;
	}

	// finds the instructions of a recipe
	public void FI(String r_name, String cnnStr){
		System.out.println("Finding instructions for " + r_name.toLowerCase());
		String sql="Select instructions FROM [Recipe] WHERE Name='" + r_name.toLowerCase() +"'";
		ResultSet ans=null;
		try(Connection cnn = DriverManager.getConnection(cnnStr); Statement statement = cnn.createStatement();){
			ans = statement.executeQuery(sql);
			while(ans.next()) {
				System.out.println("You can make "+ ans.getString("Recipe_Name"));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//updates the nutrition ID of an ingredient
	public void updateNutritionIDIngre(String name, String newID, String cnnStr){
		String sql= "Update [Ingredient] Set Nutrition_ID=" + newID + " WHERE Name='"+ name + "'";
		try(Connection cnn = DriverManager.getConnection(cnnStr); PreparedStatement statement = cnn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);){
			ResultSet resultSet = null;
			statement.execute();
			System.out.println("Ingredient added!");
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateNutritionRecipe(String name, String newID, String cnnStr)
	{

	}
}
