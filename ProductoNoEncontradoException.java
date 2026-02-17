public class ProductoNoEncontradoException extends Exception {
    public ProductoNoEncontradoException() {
        super();
    }

    public ProductoNoEncontradoException(String message) {
        super(message);
    }

    public ProductoNoEncontradoException(String message, Throwable cause) {
        super(message, cause);
    }
}
