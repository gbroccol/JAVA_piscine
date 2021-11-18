
public class Program {

    public static void main(String[] args) {
        int nmb = 479598;

        int result = 0;

        result = result + nmb % 10;
        nmb = nmb / 10;
        result = result + nmb % 10;
        nmb = nmb / 10;
        result = result + nmb % 10;
        nmb = nmb / 10;
        result = result + nmb % 10;
        nmb = nmb / 10;
        result = result + nmb % 10;
        nmb = nmb / 10;
        result = result + nmb % 10;
        System.out.println(result);
    }
}
