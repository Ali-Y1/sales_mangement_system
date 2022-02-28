package stock;

import Database.SQLQueries;

public class test {
    public static void main(String[] args){
        SQLQueries q = new SQLQueries();
        q.Addsupplier(new supplier("daniel",78654456,"no"));
    }
}
