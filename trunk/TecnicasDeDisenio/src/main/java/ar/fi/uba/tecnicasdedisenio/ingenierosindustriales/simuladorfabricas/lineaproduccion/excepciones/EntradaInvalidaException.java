package ar.fi.uba.tecnicasdedisenio.ingenierosindustriales.simuladorfabricas.lineaproduccion.excepciones;


public class EntradaInvalidaException extends Exception {
    
	private static final long serialVersionUID = -239071366505505273L;

	public EntradaInvalidaException() {
	}

	public EntradaInvalidaException(final String message) {
		super(message);
	}

	public EntradaInvalidaException(final Throwable cause) {
		super(cause);
	}

	public EntradaInvalidaException(final String message, final Throwable cause) {
		super(message, cause);
	}

}
