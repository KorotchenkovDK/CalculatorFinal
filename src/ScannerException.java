public class ScannerException extends Exception {
    public ScannerException(String description) {
        super(description);
        System.out.println("throws Exception //"+description);
    }
}
