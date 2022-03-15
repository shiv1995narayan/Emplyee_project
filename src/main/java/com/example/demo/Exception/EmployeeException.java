package com.example.demo.Exception;

public class EmployeeException extends Exception {
	
		/**
		 *
		 */
		private static final long serialVersionUID = -7866616924378144580L;
		private String guiMessage;
		/**
		 * 
		 * @return String
		 */
		public String getGuiMessage(){
			return guiMessage;
		}
		/**
		 * 
		 * @param message
		 */
		public void setGuiMessage(String message){
			guiMessage="{\"errorMsg\": \""+message+"\"}";
		}
		/**
		 * 
		 */
	    public EmployeeException() {
	        super();
	    }
		/**
		 * Constructor.
		 *
		 * @param message
		 *             exception message
		 * @param cause
		 *             the cause of the exception
		 */
		public EmployeeException(String message, Throwable cause) {
			super(message, cause);
		}
		/**
		 * 
		 * @param cause
		 */
		public EmployeeException(Throwable cause) {
			super(cause.getMessage(), cause);
		}
		/**
		 * Constructor.
		 * @param message
		             
		 */
		public EmployeeException(String message) {
			super(message);
			setGuiMessage(message);
		}
		/**
		 * 
		 * @param message
		 * @param guiMessage
		 */
		public EmployeeException(String message, String guiMessage) {
			super(message);
			setGuiMessage(guiMessage);
		}

}