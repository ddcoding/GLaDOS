package GLaDOSService.Translate;

public class LanguageNotFoundException extends Exception {
    public LanguageNotFoundException() {
        System.err.println("Nie znaleziono tego języka");
    }
}
