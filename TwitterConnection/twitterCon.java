public class twitterCon {
    
    TwitterFactory tf;
    Twitter twitter;
    String todosTwits="";
    
     List<String> buenos = new ArrayList<String>();
     List<String> malos = new ArrayList<String>();
     
     public void setupsentiment(){
        buenos.add("bueno");
        buenos.add("bonito");
        buenos.add("feliz");
        buenos.add("positivo");
        buenos.add("eficaz");
        buenos.add("efectivo");        
        buenos.add("eficiente");
        buenos.add("provechoso");
        buenos.add("verdadero");        
        buenos.add("auténtico");
        buenos.add("real");
        buenos.add("afirmativo");        
        buenos.add("concreto");
        buenos.add("válido");
        buenos.add("activo");
        
        malos.add("malo");
        malos.add("feo");
        malos.add("desconfiado");
        malos.add("negativo");
        malos.add("dañino");
        malos.add("nocivo");
        malos.add("perjudicial");
        malos.add("contrario");
        malos.add("adverso");        
        
     }
    
    public void setup(){
        
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
          .setOAuthConsumerKey("***************")
          .setOAuthConsumerSecret("****************************")
          .setOAuthAccessToken("********************************")
          .setOAuthAccessTokenSecret("******************************");
        tf = new TwitterFactory(cb.build());
        twitter = tf.getInstance();
        
    }
    
    public int[] serch(String[] palabras, double lat, double lon,String date){
        int contador = 0;
        int bueno = 0;
        int malo = 0;
        int [] resultados = new int[3];
        try{
            for(int i = 0; i<palabras.length;i++){
                Query query = new Query(palabras[i]);
                query.setCount(1000);
                query.setSince(date);
                query.setGeoCode(new GeoLocation(lat,lon),40,Query.MILES);
                QueryResult result = twitter.search(query);
                contador = contador + result.getTweets().size();
                String twitindi = "";
                for (Status status : result.getTweets()) {
                    twitindi = status.getText();
                    bueno = bueno + sentimientopos(twitindi);
                    malo = malo + sentimientoneg(twitindi);
                }
            }
            resultados[0]= contador;
            resultados[1]= bueno;
            resultados[2]= malo;
            
        } catch (TwitterException ex) {
            Logger.getLogger(TwitterApp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultados;
    }
    
    public int sentimientopos(String twit){
        int cont = 0;
        twit = twit.replaceAll("[^a-zA-Z0-9]", " ");
        Scanner scanner = new Scanner(twit.toLowerCase());
        List<String> tokenlist = new ArrayList<>();
        
        while(scanner.hasNext()){
            tokenlist.add(scanner.next());
        }
        
        for(String word: tokenlist){
            if(buenos.contains(word)){
                cont = cont+1;
                break;
            }
        }
        
        return cont;
    }
    
    public int sentimientoneg(String twit){
        int cont = 0;
        twit = twit.replaceAll("[^a-zA-Z0-9]", " ");
        Scanner scanner = new Scanner(twit.toLowerCase());
        List<String> tokenlist = new ArrayList<>();
        
        while(scanner.hasNext()){
            tokenlist.add(scanner.next());
        }
        
        for(String word: tokenlist){
            if(malos.contains(word)){
                cont = cont-1;
                break;
            }
        }   
        return cont;
    }    
}
