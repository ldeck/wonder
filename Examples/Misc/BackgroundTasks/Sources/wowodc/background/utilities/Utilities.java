package wowodc.background.utilities;

import java.util.Random;

import org.apache.log4j.Logger;

/**
 * A class with methods that do various things - just to consume time for example tasks.
 * 
 * @author kieran
 *
 */
public class Utilities {
	
	private static final Logger log = Logger.getLogger(Utilities.class);
	
	// Random number generator shared instance
	private static class RANDOM {
		static Random GENERATOR = new Random();
	}
	/**
	 * A Prime Number can be divided evenly only by 1 or itself.
	 * And it must be greater than 1.
	 * Example: 7 can be divided evenly only by 1 or 7, so it is a prime number.
	 * If it is not a Prime Number it is called a Composite Number
	 * Example: 6 can be divided evenly by 1, 2, 3 and 6 so it is a composite number.
	 * 
	 * Refs:
	 * 		http://primes.utm.edu/
	 * 		http://primes.utm.edu/notes/faq/one.html
	 * 
	 * @param aNumber
	 * @return true if a number is a prime number, false otherwise
	 */
	public static boolean isPrime(long aNumber) {
		if (aNumber < 2) {
			return false;
		}
		
		boolean result = true;
		long remainder;
		long checkValue;
		
		// We check all values up to the square root of the number since logically
		// if a number smaller than the square root does not fit, then one larger
		// than the square root will not fit.
		for (checkValue = 2; checkValue * checkValue < aNumber; checkValue++) {
			remainder = aNumber % checkValue;
			if (log.isDebugEnabled())
				log.debug("aNumber = " + aNumber + "; checkValue = " + checkValue + "; remainder = " + remainder);
			if (remainder == 0) {
				// aNumber can be divided evenly by checkValue, so it is not prime
				result = false;
				if (log.isDebugEnabled())
					log.debug(aNumber + " is NOT a prime number. It is a composite number!");
				break;
			}
		}
		
		if (result) {
			if (log.isDebugEnabled())
				log.debug(aNumber + " IS prime!");
		}
		
		return result;
	}
	
	/**
	 * @return a positive random Long between 0 and 1 million
	 */
	public static long newStartNumber() {
		return RANDOM.GENERATOR.nextInt(1000001);
	}
	
	public static Random sharedRandom() {
		return RANDOM.GENERATOR;
	}
}
