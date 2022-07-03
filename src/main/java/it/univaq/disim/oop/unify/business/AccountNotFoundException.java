package it.univaq.disim.oop.unify.business;

@SuppressWarnings("serial")
public class AccountNotFoundException extends BusinessException{
	public AccountNotFoundException() {
	}
	
	public AccountNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	
	public AccountNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public AccountNotFoundException(String message) {
		super(message);
	}
	
	public AccountNotFoundException(Throwable cause) {
		super(cause);
	}

}
