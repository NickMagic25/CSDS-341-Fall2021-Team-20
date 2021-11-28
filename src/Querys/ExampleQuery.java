package Querys;

import java.sql.*;

public class ExampleQuery {
	public static void main(String[] args) {

        System.out.println("performing setup");
        String cnnStr = "jdbc:sqlserver://arnold-tracker.database.windows.net:1433;database=arnold-tracker;user=team20@arnold-tracker;password={ArnOld2021$};encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
        String userName = "team20";
        String userPassword = "ArnOld2021$";
        
        ExampleQuery query = new ExampleQuery();
        System.out.println("connecting");
        
        
        String sql = "Select * From [Has] Where [User_ID] = 1";
        query.FAIUH("1", cnnStr);
        query.FNOAI("Carrot", cnnStr);
        query.FDNFU("1", cnnStr);
        query.FAIIR("Beef stew", cnnStr);
        query.FARWGTP("America", cnnStr);
        query.FARWGTP("Salty", "America", cnnStr);
        query.FMR("Beef stew", cnnStr);
        query.FARI("Beef",cnnStr);
		query.FCM("Protien", "<", "20", cnnStr);
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
		String sql = "Select * From [Nutrition] Where [Nutrition_ID] = (Select [Nutrition_ID] From [Contains] Where [Ingredient_Name] = '" + ingre_Name + "')";
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
		String sql = "Select * From [Uses] Where [Recipe_Name] = '" + resp_Name +"'";
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
		String sql = "Select * From [Taste_Profile] Where [Taste] = '" + taste_Profile +"'";
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

	// find all ingredients with general taste profile and region
	public ResultSet FARWGTP(String taste, String region, String cnnStr) {
		System.out.println("Looking for all recipe with " + taste + " and " + region);
		String sql = "Select * From [Taste_Profile] Where [Taste] = '" + taste +"' and [Region] = '" + region + "'";
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
		String sql="Select n1.Grams_Protien, n1.Grams_Carbs, n1.Grams_Fat From [Nutrition] as n1, [Macro_Contents] as m1 Where (m1.Recipe_Name='"+recipe_name+"') and (n1.Nutrition_ID=m1.Nutrition_ID)";
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
		System.out.println("Finding all recipes that use "+ ingre_name );
		String sql="SELECT u.Recipe_Name, u.Amount_Of FROM [USES] as u  WHERE u.Ingredient_Name='"+ingre_name+"'";
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
	 * sing is a string that is either > or <
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

	public ResultSet FAR(String user_ID,String cnnStr){
		System.out.println("Finding all recipes you can make");
		String sql="SELECT u.Recipe_Name FROM [Uses] as u  WHERE u.Amount_of <= all (Select h.Serving_Size as Amount_Of From [Has] as h WHERE h.User_Id="+ user_ID+ " )";
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
		return ans;

	}

}
