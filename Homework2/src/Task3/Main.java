package Task3;

/*
Task3
    Реализовать функцию нечеткого поиска

            fuzzySearch("car", "ca6$$#_rtwheel"); // true
            fuzzySearch("cwhl", "cartwheel"); // true
            fuzzySearch("cwhee", "cartwheel"); // true
            fuzzySearch("cartwheel", "cartwheel"); // true
            fuzzySearch("cwheeel", "cartwheel"); // false
            fuzzySearch("lw", "cartwheel"); // false
 */

public class Main {
    public static void main(String[] args) {

        System.out.println(fuzzySearch("car", "ca6$$#_rtwheel"));
        System.out.println(fuzzySearch("cwhl", "cartwheel"));
        System.out.println(fuzzySearch("cwhee", "cartwheel"));
        System.out.println(fuzzySearch("cartwheel", "cartwheel"));
        System.out.println(fuzzySearch("cwheeel", "cartwheel"));
        System.out.println(fuzzySearch("lw", "cartwheel"));

        assertFuzzySearch();
    }

    public static boolean fuzzySearch(String searchText, String text) {
        int textLength = text.length();
        int searchTextLength = searchText.length();
        if (searchTextLength > textLength) {
            return false;
        }
        if (searchTextLength == textLength) {
            return searchText.equals(text);
        }
        outer:
        for (int i = 0, j = 0; i < searchTextLength; i++) {
            int stCh = Character.codePointAt(searchText, i);
            while (j < textLength) {
                int tCh = Character.codePointAt(text, j++);
                if (tCh == stCh) {
                    continue outer;
                }
            }
            return false;
        }
        return true;
    }

    public static void assertFuzzySearch() {
        String searchText1 = "Alice";
        String searchText2 = "bmx";
        String searchText3 = "foxirubAfbklt643i@#m&ctfjwn1";
        String text = "foxirubAfbklt643i@#m&ctfjwne";
        assert fuzzySearch(searchText1, text) : "Incorrect search!";
        assert !fuzzySearch(searchText2, text) : "Incorrect search!";
        assert !fuzzySearch(searchText3, text) : "Incorrect search!";
    }
}