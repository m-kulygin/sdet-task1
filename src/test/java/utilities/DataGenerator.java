package utilities;

import java.util.Random;

public class DataGenerator {

    private static final String ALPHABET_LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final Random random = new Random();
    private static final int POST_CODE_LENGTH = 10;
    private static final int ALPHABET_LENGTH = ALPHABET_LOWERCASE.length();

    public static String generatePostCode() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < POST_CODE_LENGTH; i++) {
            int digit = random.nextInt(POST_CODE_LENGTH);
            sb.append(digit);
        }
        return sb.toString();
    }

    public static String generateFirstNameByPostCode(String postCode) {
        if (postCode.length() != POST_CODE_LENGTH || !postCode.matches("\\d+")) {
            throw new IllegalArgumentException("Invalid post code format");
        }

        char[] chars = postCode.toCharArray();
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < chars.length; i += 2) {
            int num = Integer.parseInt(chars[i] + "" + chars[i + 1]);
            int index = num % ALPHABET_LENGTH;
            result.append((char) ('a' + index));
        }
        return result.toString();
    }

    public static String generateLastName() {
        int length = random.nextInt(6) + 5;
        StringBuilder surname = new StringBuilder();

        surname.append(ALPHABET_LOWERCASE.charAt(random.nextInt(ALPHABET_LENGTH)));

        for (int i = 1; i < length; i++) {
            surname.append(ALPHABET_LOWERCASE.charAt(random.nextInt(ALPHABET_LENGTH)));
        }

        return surname.toString();
    }

}
