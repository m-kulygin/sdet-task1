package utilities;

import java.util.Random;

/**
 * Utility class, that provides customers data generation.
 *
 * @author Max Kulygin
 */
public class DataGenerator {

    private static final String ALPHABET_LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final Random random = new Random();
    private static final int POST_CODE_LENGTH = 10;
    private static final int ALPHABET_LENGTH = ALPHABET_LOWERCASE.length();

    /**
     * Generate post code, containing 10 random digits.
     *
     * @return Generated post code value as string containing digits
     */
    public static String generatePostCode() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < POST_CODE_LENGTH; i++) {
            int digit = random.nextInt(POST_CODE_LENGTH);
            sb.append(digit);
        }
        return sb.toString();
    }

    /**
     * Generate first name depending on post code value.
     *
     * @param postCode Post code value for first name value generating
     * @return Generated first name value
     */
    public static String generateFirstNameByPostCode(String postCode) {
        if (postCode.length() != POST_CODE_LENGTH || !postCode.matches("\\d+")) {
            throw new AssertionError();
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

    /**
     * Generate last name, containing from 5 to 10 random english letters.
     *
     * @return Generated last name value
     */
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
