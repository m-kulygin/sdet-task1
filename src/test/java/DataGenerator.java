import java.util.Random;

public class DataGenerator {

    private static final String ALPHABET_LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final Random random = new Random();


    public static String generatePostCode() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            int digit = random.nextInt(10);
            sb.append(digit);
        }
        return sb.toString();
    }

    public static String generateFirstNameByPostCode(String postCode) {
        if (postCode.length() != 10 || !postCode.matches("\\d+")) {
            throw new IllegalArgumentException("Invalid post code format");
        }

        char[] chars = postCode.toCharArray();
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < chars.length; i += 2) {
            int num = Integer.parseInt(chars[i] + "" + chars[i + 1]);
            int index = num % 26;
            result.append((char)('a' + index));
        }
        return result.toString();
    }

    public static String generateLastName() {
        int length = random.nextInt(6) + 5;
        StringBuilder surname = new StringBuilder();

        surname.append(ALPHABET_LOWERCASE.charAt(random.nextInt(26)));

        for (int i = 1; i < length; i++) {
            surname.append(ALPHABET_LOWERCASE.charAt(random.nextInt(26)));
        }

        return surname.toString();
    }

}
