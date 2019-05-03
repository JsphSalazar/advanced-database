public class TwitterApp {

    public static void main(String[] args) {
        
        String [] palabrasPol = {"politica","presidente","leyes"};
        String [] palabrasDep = {"futbol","gol","liguilla"};
        String [] palabrasSeg = {"policia","robo","agresion"};
        String [] palabrasRel = {"iglesia","sacerdote","orar"};
        String [] palabrasSal = {"hospital","doctor","emergencia"};
        
        int[] contPol = new int[3];
        int[] contDep = new int[3];
        int[] contSeg = new int[3];
        int[] contRel = new int[3];
        int[] contSal = new int[3];
        
        String fecha = "2019-04-26";
        String fechaActual = "2019-04-26";
        
        MYSQLCon basededatos = new MYSQLCon();
        twitterCon twit = new twitterCon();
        twit.setupsentiment();
        twit.setup();
        
        
        
        String res = basededatos.checarDuplicado(fechaActual,fecha);
        if(res.equals(fecha)){
            System.out.println("Ya existe");
        }else{
            System.out.println("No existe");
            //Monterrey (id 1)
            int id = 1;
            contPol = twit.serch(palabrasPol,25.685818,-100.319542,fecha);
            contDep = twit.serch(palabrasDep,25.685818,-100.319542,fecha);
            contSeg = twit.serch(palabrasSeg,25.685818,-100.319542,fecha);
            contRel = twit.serch(palabrasRel,25.685818,-100.319542,fecha);
            contSal = twit.serch(palabrasSal,25.685818,-100.319542,fecha);

            basededatos.insertinto(id,contPol[0],contRel[0],contSeg[0],contDep[0],contSal[0],fecha,fechaActual);

            int idcosas = basededatos.getBusquedaId();
            basededatos.insertintosentiment((idcosas),contPol[1],contRel[1],contSeg[1],contDep[1],contSal[1],contPol[2],contRel[2],contSeg[2],contDep[2],contSal[2]);
            
            //Guadalajara (id 2)
            id = 2;
            contPol = twit.serch(palabrasPol,20.659957,-103.351550,fecha);
            contDep = twit.serch(palabrasDep,20.659957,-103.351550,fecha);
            contSeg = twit.serch(palabrasSeg,20.659957,-103.351550,fecha);
            contRel = twit.serch(palabrasRel,20.659957,-103.351550,fecha);
            contSal = twit.serch(palabrasSal,20.659957,-103.351550,fecha);
            
            basededatos.insertinto(id,contPol[0],contRel[0],contSeg[0],contDep[0],contSal[0],fecha,fechaActual);
            idcosas = basededatos.getBusquedaId();
            basededatos.insertintosentiment((idcosas),contPol[1],contRel[1],contSeg[1],contDep[1],contSal[1],contPol[2],contRel[2],contSeg[2],contDep[2],contSal[2]);
            
        }
    }
}
