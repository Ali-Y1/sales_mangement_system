package order;

import Database.SQLDatabaseConnection;
import Database.SQLQueries;

public class checkCustomer {
    SQLQueries query = new SQLQueries();
    //checks if customer exists in DB and return its id
    public int isCustomer(int phone){
      try{
          return query.fetchCustomer(phone);
      }catch(Exception e){
          return -1;
      }

    }
    public customer addCustomer(String FnName, String LnName, String email, String Address, int phone){
        //adds customer to the order
        customer c = new customer();
        SQLQueries queries = new SQLQueries();
        c.setAddress(Address);
        c.setEmail(email);
        c.setFnName(FnName);
        c.setLnName(LnName);
        c.setPhone(phone);
        //adds customer to the database
        queries.ADD_DB_Customer(c);
        return c;
    }
}
