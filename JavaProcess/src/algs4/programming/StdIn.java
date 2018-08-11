package algs4.programming;

import java.util.InputMismatchException;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class StdIn {

	// assume Unicode UTF-8 encoding
	private static final String CHARSET_NAME = "UTF-8";
	
	private static final Locale LOCALE = Locale.US;

//		private static final Pattern WHITESPACE_PATTERN = Pattern.compile("\\p{javaWhilespace}+");
	
	private static final Pattern WHITESPACE_PATTERN = Pattern.compile("\\p{javaWhitespace}+");
	
	private static final Pattern EMPTY_PATTERN = Pattern.compile("");
	
	private static final Pattern EVERYTHING_PATTERN = Pattern.compile("\\A");
	
	private static Scanner scanner;
	
    // do this once when StdIn is initialized
    static {
        resync();
    }

    /**
     * If StdIn changes, use this to reinitialize the scanner.
     */
    private static void resync() {
        setScanner(new Scanner(new java.io.BufferedInputStream(System.in), CHARSET_NAME));
    }
    
    private static void setScanner(Scanner scanner) {
        StdIn.scanner = scanner;
        StdIn.scanner.useLocale(LOCALE);
    }
	
	private StdIn()
	{
		
	}
	
	public static boolean isEmpty()
	{
		return !scanner.hasNext();
	}
	
	public static boolean hasNextLine()
	{
		return scanner.hasNextLine();
	}
	
	public static boolean hasNextChar()
	{
		scanner.useDelimiter(EMPTY_PATTERN);
		boolean result = scanner.hasNext();
		scanner.useDelimiter(WHITESPACE_PATTERN);
		return result;
	}
	
	public static String readLine()
	{
		String line;
		try
		{
			line = scanner.nextLine();
		}
		catch (NoSuchElementException e)
		{
			line = null;
		}
		
		return line;
	}
	
	public static char readChar()
	{
		try
		{
			scanner.useDelimiter(EMPTY_PATTERN);
			String ch = scanner.next();
			assert ch.length() == 1 : "Internal (Std)In.readChar() error! Please contact the authors.";
			scanner.useDelimiter(WHITESPACE_PATTERN);
			return ch.charAt(0);
		}
		catch (NoSuchElementException e)
		{
			throw new NoSuchElementException("attempts to read a 'char' value from standard input, " +
					"but no more tokens are available");
		}
	}
	
	public static String readAll()
	{
		if (!scanner.hasNextLine())
		{
			return "";
		}
		String result = scanner.useDelimiter(EVERYTHING_PATTERN).next();
		scanner.useDelimiter(WHITESPACE_PATTERN);
		return result;
	}
	
	public static String readString()
	{
		try
		{
			return scanner.next();
		}
		catch (NoSuchElementException e)
		{
			throw new NoSuchElementException("attempts to read a 'String' value from standard input");
		}
	}
	
	public static int readInt()
	{
		try
		{
			return scanner.nextInt();
		}
		catch (InputMismatchException e)
		{
			String token = scanner.next();
			throw new InputMismatchException("attempts to read an 'int' value from standard input");
		}
		catch (NoSuchElementException e)
		{
			throw new NoSuchElementException("attempts to read 'int' value from standard input");
		}
	}
	
	public static String[] readAllStrings()
	{
		String[] tokens = WHITESPACE_PATTERN.split(readAll());
		if (tokens.length == 0 || tokens[0].length() > 0)
		{
			return tokens;
		}
		String[] decapitokens = new String[tokens.length - 1];
		for (int i=0; i<tokens.length-1; i++)
		{
			decapitokens[i] = tokens[i+1];
		}
		return decapitokens;
		
		
	}
	
}
