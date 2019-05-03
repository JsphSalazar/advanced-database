public class MYSQLCon {
    
    
    public void connect(){
        try{  
            Class.forName("com.mysql.jdbc.Driver");  
            Connection con=DriverManager.getConnection(  
            "jdbc:mysql://localhost:3306/testgo?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","root");  
            System.out.println("Conectado");
            
            Statement stmt=con.createStatement();  
            ResultSet rs=stmt.executeQuery("select * from users");  
            while(rs.next())  
            System.out.println(rs.getString(1)); 
            
            con.close(); 

        }catch(Exception e){ 
            System.out.println(e);
        }    
    }
    
    public void insertinto(int id, int pol,int rel, int seg, int dep, int sal ,String info,String infoact){
        try{

            Class.forName("com.mysql.jdbc.Driver");  
            Connection con=DriverManager.getConnection(  
            "jdbc:mysql://localhost:3306/testgo?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","admin");  
            System.out.println("Conectado");
            Statement st = con.createStatement();
            st.executeUpdate("INSERT INTO EstadoResultados VALUES  ("+id+",0,"+pol+","+rel+","+seg+","+dep+","+sal+",'"+info+"','"+infoact+"')");

            con.close();
            
        }catch(Exception e){ 
            System.out.println(e);
        }
    }
    
        public void insertintosentiment(int id, int polpos,int relpos, int segpos, int deppos, int salpos, int polneg,int relneg, int segneg, int depneg, int salneg){
        try{

            Class.forName("com.mysql.jdbc.Driver");  
            Connection con=DriverManager.getConnection(  
            "jdbc:mysql://localhost:3306/testgo?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","admin");  
            System.out.println("Conectado");
            Statement st = con.createStatement();
            
            st.executeUpdate("INSERT INTO BusquedaSentiment VALUES  (0,"+id+","+polpos+","+polneg+","+relpos+","+relneg+","+segpos+","+segneg+","+deppos+","+depneg+","+segpos+","+salneg+")");

            con.close();
            
        }catch(Exception e){ 
            System.out.println(e);
        }
    }
    
    public int getBusquedaId(){
        int cosas = 0;
        try{
            Class.forName("com.mysql.jdbc.Driver");  
            Connection con=DriverManager.getConnection(  
            "jdbc:mysql://localhost:3306/testgo?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","admin");  
            System.out.println("Conectado");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT idBusqueda \n" +
                            "FROM EstadoResultados\n" +
                            "ORDER BY idBusqueda DESC\n" +
                            "LIMIT 1;");
            
            while(rs.next()){
                cosas = rs.getInt("idBusqueda");
            }
            con.close();
        }catch(Exception e){ 
            System.out.println(e);
        }
        return cosas;
    }
    
    public String checarDuplicado(String fechaActual, String fechaSolicitada){
        String resultado = "";
        try{
            Class.forName("com.mysql.jdbc.Driver");  
            Connection con=DriverManager.getConnection(  
            "jdbc:mysql://localhost:3306/testgo?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","admin");  
            System.out.println("Conectado");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT FechaSolicitada, FechaActual \n"+
                                        "FROM EstadoResultados \n"+
                                        "WHERE FechaSolicitada = '"+fechaSolicitada +"' \n"+ 
                                        "AND FechaActual = '"+fechaActual +"' \n");
            
            while(rs.next()){
                resultado = rs.getString("FechaSolicitada");
            }
            con.close();
        }catch(Exception e){ 
            System.out.println(e);
        }
        return resultado;
    }
}
