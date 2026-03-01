package Entregable3;

public class HiloDemonio implements Runnable {
    private Sistema sistema;

    public HiloDemonio(Sistema sistema){
        this.sistema = sistema;
    }

    @Override
    public void run() {
        while(true){
            try{
                sistema.generarReporte();
                Thread.sleep(10000);

            }catch(InterruptedException e){
                System.out.println("Error en el demonio");
            }
        }
    }
}
