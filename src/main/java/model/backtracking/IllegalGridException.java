package model.backtracking;

public class IllegalGridException extends RuntimeException {

    public IllegalGridException()
    {   super("La griglia non è quadrata");
    }

}
