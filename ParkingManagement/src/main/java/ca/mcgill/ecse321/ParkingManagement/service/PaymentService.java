package ca.mcgill.ecse321.ParkingManagement.service;

import org.springframework.stereotype.Service;

/**
 * The PaymentService class checks for the validity of a given credit card number in order to authorize payments.
 * Algorithm and code taken from https://www.geeksforgeeks.org/program-credit-card-number-validation/.
 * Javadoc and comments added/redone
 */

@Service
public class PaymentService {
        /**
         * The paymentValidityCheck method takes a credit card number of type long and checks if it is
         * a valid one using Luhn's mod 10 algorithm.
         * @param number The credit card's number.
         * @return True if the card number is valid, False if it isn't.
         */
        public static boolean paymentValidityCheck(long number)
        {
           return (getSize(number) >= 13 &&
                   getSize(number) <= 16) &&
                   (prefixMatched(number, 4) ||
                    prefixMatched(number, 5) ||
                    prefixMatched(number, 37) ||
                    prefixMatched(number, 6)) &&
                  ((sumOfDoubleEvenPlace(number) +
                    sumOfOddPlace(number)) % 10 == 0);
        }
     
        
        //Part of the algorithm. Doubles every even placed digit in the card's number and adds them up.
        private static int sumOfDoubleEvenPlace(long number)
        {
            int sum = 0;
            String num = number + "";
            for (int i = getSize(number) - 2; i >= 0; i -= 2)
                sum += getDigit(Integer.parseInt(num.charAt(i) + "") * 2);
             
            return sum;
        }
     
        //Part of the previous helper method. 
        //Gets the result from the doubled digit if the result is less than 9
        //Gets the sum of the individual digits and returns it if the number is greater than or equal to 9
        private static int getDigit(int number)
        {
            if (number < 9)
                return number;
            return number / 10 + number % 10;
        }
     
        //Part of the algorithm. Returns the sum of the odd placed digits in the card's number
        private static int sumOfOddPlace(long number)
        {
            int sum = 0;
            String num = number + "";
            for (int i = getSize(number) - 1; i >= 0; i -= 2)
                sum += Integer.parseInt(num.charAt(i) + "");       
            return sum;
        }
     
        //Part of the algorithm. Checks if the specific prefix of the card is valid. 
        //A given card number might pass the algorithm even if it is not an actual issued card.
        private static boolean prefixMatched(long number, int d)
        {
            return getPrefix(number, getSize(d)) == d;
        }
     
        //Getter method to get the number of digits (size) of a given number.
        private static int getSize(long d)
        {
            String num = d + "";
            return num.length();
        }
     
        //Return the first k number of digits from
        //number. If the number of digits in number
        //is less than k, return number.
        private static long getPrefix(long number, int k)
        {
            if (getSize(number) > k) {
                String num = number + "";
                return Long.parseLong(num.substring(0, k));
            }
            return number;
        }
    }

