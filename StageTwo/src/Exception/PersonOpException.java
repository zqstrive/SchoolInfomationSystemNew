package Exception;

public class PersonOpException extends Exception {
    public PersonOpException(){
        this("PersonOpException");
    }
    public PersonOpException(String msg){
        super(msg);
    }
}
