package main;

import java.util.Random;
import java.util.Scanner;

public class PatternFinder {

    private static String randomStringGenerator(int length) {
        Random random = new Random(System.nanoTime());
        char[] array = new char[length];
        for (int i = 0; i < length; i++)
            array[i] = (char) ('a' + random.nextInt(26));
        return new String(array);
    }

    private static boolean isSingleton(String str) {
        return str.chars().distinct().count() == 1;
    }

    private static boolean isArithmeticString1(String str) {
        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) != str.charAt(i - 1) + 1) {
                return false;
            }
        }
        return true;
    }

    private static boolean isArithmeticStringMinus1(String str) {
        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) != str.charAt(i - 1) - 1) {
                return false;
            }
        }
        return true;
    }

    private static boolean isBalancedTripartite(String str) {
        int third = str.length() / 3;
        String part1 = str.substring(0, third);
        String part2 = str.substring(third, 2 * third);
        String part3 = str.substring(2 * third);
        return part1.equals(part2) && part2.equals(part3);
    }

    private static boolean isBalancedBipartite(String str) {
        int half = str.length() / 2;
        String part1 = str.substring(0, half);
        String part2 = str.substring(half);
        return part1.equals(part2);
    }

    private static boolean isPalindrome(String str) {
        int length = str.length();
        for (int i = 0; i < length / 2; i++) {
            if (str.charAt(i) != str.charAt(length - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);

        int randomStringLength = 0;
        int patternMaxLength = 0;

        while (true) {
            try {
                // Input for random string length
                System.out.print("Enter the length of the random string (between 100,000 and 1,000,000,000): ");
                randomStringLength = Integer.parseInt(keyboard.nextLine());

                if (randomStringLength < 100000 || randomStringLength > 1000000000) {
                    throw new NumberFormatException();
                }

                // Input for maximum pattern length
                System.out.print("Enter the maximum length of special patterns (between 3 and 15): ");
                patternMaxLength = Integer.parseInt(keyboard.nextLine());

                if (patternMaxLength < 3 || patternMaxLength > 15) {
                    throw new NumberFormatException();
                }

                break; // Break out of the loop if input is valid.
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter values within the specified legal ranges.");
            }
        }

        Random random = new Random();
        StringBuilder randomString = new StringBuilder();

        for (int i = 0; i < randomStringLength; i++) {
            randomString.append((char) ('a' + random.nextInt(26)));
        }

        int rarestPatternIndex = -1;
        String randomStringResult = randomString.toString();
        String rarestPattern = null;
        String rarestPatternType = null;

        for (int length = patternMaxLength; length >= 3; length--) {
            for (int start = 0; start <= randomStringResult.length() - length; start++) {
                String substring = randomStringResult.substring(start, start + length);

                if (isSingleton(substring)) {
                    if (rarestPattern == null || rarestPattern.length() < length || isPalindrome(substring)) {
                        rarestPattern = substring;
                        rarestPatternType = "Singleton";
                        rarestPatternIndex = start;
                    }
                } else if (isArithmeticString1(substring)) {
                    if (rarestPattern == null || rarestPattern.length() < length || isPalindrome(substring)) {
                        rarestPattern = substring;
                        rarestPatternType = "Arithmetic String of Order 1";
                        rarestPatternIndex = start;
                    }
                } else if (isArithmeticStringMinus1(substring)) {
                    if (rarestPattern == null || rarestPattern.length() < length || isPalindrome(substring)) {
                        rarestPattern = substring;
                        rarestPatternType = "Arithmetic String of Order -1";
                        rarestPatternIndex = start;
                    }
                } else if (isBalancedTripartite(substring)) {
                    if (rarestPattern == null || rarestPattern.length() < length || isPalindrome(substring)) {
                        rarestPattern = substring;
                        rarestPatternType = "Balanced Tripartite String";
                        rarestPatternIndex = start;
                    }
                } else if (isBalancedBipartite(substring)) {
                    if (rarestPattern == null || rarestPattern.length() < length || isPalindrome(substring)) {
                        rarestPattern = substring;
                        rarestPatternType = "Balanced Bipartite String";
                        rarestPatternIndex = start;
                    }
                } else if (isPalindrome(substring) && (rarestPattern == null || rarestPattern.length() < length)) {
                    rarestPattern = substring;
                    rarestPatternType = "Palindrome";
                    rarestPatternIndex = start;
                }
            }
        }

        if (rarestPattern != null) {
            System.out.println("Rarest Pattern Found: " + rarestPattern + " (Type: " + rarestPatternType + ", Index: " + rarestPatternIndex + ")");
        } else {
            System.out.println("No special patterns found.");
        }
    }
}
