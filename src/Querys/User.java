package Querys;

public class User{
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

        int size=args.length;

        if(query.equals("FAIUH"))
            database.FAIUH(id,cnnStr);
        if(query.equals("FNOAI")){
            String ingreName=args[2];
            database.FNOAI(ingreName,cnnStr);
        }
        if(query.equals("FDNFU"))
            database.FDNFU(id,cnnStr);
        if(query.equals("FAIIR")){
            String respName=args[2];
            database.FAIIR(respName,cnnStr);
        }
        if(query.equals("FARWGTP") && size==3){
            String taste=args[2];
            database.FAIIR(taste,cnnStr);
        }
        if(query.equals("FARWGTP")){
            String taste=args[2];
            String region=args[3];
            database.FARWGTP(taste,region,cnnStr);
        }
        if(query.equals("FMR")){
            String name=args[2];
            database.FMR(name,cnnStr);
        }
        if(query.equals("FARI")){
            String ingreName=args[2];
            database.FARI(ingreName,cnnStr);
        }
        if(query.equals("FCM")){
            String macroType=args[2];
            String sign=args[3];
            String grams=args[4];
            database.FCM(macroType,sign,grams,cnnStr);
        }
        if(query.equals("FAR"))
            database.FAR(id,cnnStr);
    }
}