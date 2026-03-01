package Entregable3;

public class HiloProcesador implements Runnable {
    private Sistema sistema;

    public HiloProcesador(Sistema sistema){
        this.sistema = sistema;
    }
    @Override
    public void run(){
        while(true){
            sistema.procesarPedidos();

            try{
                Thread.sleep(2000);

            }catch(InterruptedException e){
                System.out.println("Error al procesar el hilo");

            }
        }
    }
}
