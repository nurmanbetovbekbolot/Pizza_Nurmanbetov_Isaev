package whz.pti.eva.praktikum_03;

public class BalanceIsLessException extends Exception{
    private static final String message = "Not enough balance";

    public BalanceIsLessException() {
        super(message);
    }
}
