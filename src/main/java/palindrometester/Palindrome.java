package palindrometester;
import java.lang.*;

public class Palindrome {
    private String thePalindrome;
    private boolean alphaOnly;
    private boolean alphanumericOnly;
    private boolean caseSensitive;
    private boolean allowSpace;
    private boolean ignoreSpace;
    private boolean ignorePunctuation;

    public Palindrome(String inPalindrome){
         this(inPalindrome, false, false, false, false, false, false);
    }

    public Palindrome(String inPalindrome, boolean isAlphaOnly, boolean isAlphanumericOnly, boolean isCaseSensitive, boolean isAllowSpace, boolean isIgnoreSpace, boolean isIgnorePunctuation){
        thePalindrome = inPalindrome;
        alphaOnly = isAlphaOnly;
        alphanumericOnly = isAlphanumericOnly;
        caseSensitive = isCaseSensitive;
        allowSpace = isAllowSpace;
        ignoreSpace = isIgnoreSpace;
        ignorePunctuation = isIgnorePunctuation;
    }

    public boolean IsPalindrome(){

        if(ignoreSpace){
            thePalindrome = thePalindrome.trim();
            thePalindrome = thePalindrome.replaceAll("\\s+","");
        }
        if(ignorePunctuation){
            thePalindrome = thePalindrome.replaceAll("[^a-zA-Z0-9]", "");
        }


        if(alphaOnly && !alphanumericOnly && !caseSensitive && !allowSpace)
        {
            return PalindromeAlphaOnlyIgnoreCase(thePalindrome);
        }
        else if(alphaOnly == true && alphanumericOnly == false && caseSensitive == true && allowSpace == false)
        {
            return PalindromeAlphaOnlyCaseSensitive(thePalindrome);
        }
        else if(alphaOnly == true && alphanumericOnly == false && caseSensitive == true && allowSpace == true)
        {
            return PalindromeAlphaOnlyCaseSensitiveIncludeSpace(thePalindrome);
        }
        else if(alphaOnly == true && alphanumericOnly == false && caseSensitive == false && allowSpace == true)
        {
            return PalindromeAlphaOnlyIgnoreCaseIncludeSpace(thePalindrome);
        }
        else if(alphaOnly == false && alphanumericOnly == true && caseSensitive == false && allowSpace == false)
        {
            return PalindromeAlphanumericIgnoreCase(thePalindrome);
        }
        else if(alphaOnly == false && alphanumericOnly == true && caseSensitive == true && allowSpace == false)
        {
            return PalindromeAlphanumericCaseSensitive(thePalindrome);
        }
        else if(alphaOnly == false && alphanumericOnly == true && caseSensitive == true && allowSpace == true)
        {
            return PalindromeAlphanumericCaseSensitiveIncludeSpace(thePalindrome);
        }
        else if(alphaOnly == false && alphanumericOnly == true && caseSensitive == false && allowSpace == true)
        {
            return PalindromeAlphanumericIgnoreCaseIncludeSpace(thePalindrome);
        }
        else if(alphaOnly == false && alphanumericOnly == false && caseSensitive == false && allowSpace == false)
        {
            return PalindromeAnyIgnoreCaseNoSpace(thePalindrome);
        }
        else if(alphaOnly == false && alphanumericOnly == false && caseSensitive == true && allowSpace == false)
        {
            return PalindromeAnyCaseSensitiveNoSpace(thePalindrome);
        }
        else if(alphaOnly == false && alphanumericOnly == false && caseSensitive == true && allowSpace == true)
        {
            return PalindromeAnyCaseSensitive(thePalindrome);
        }
        else if(alphaOnly == false && alphanumericOnly == false && caseSensitive == false && allowSpace == true) {
            return PalindromeAnyIgnoreCase(thePalindrome);
        }
        else{
            return false;
        }
    }

    public boolean PalindromeTest(String testString){
        if (testString.isEmpty()){
            // message algerbra Result<ok, fail>
            return false;
        }
        char[] tempChars = testString.toCharArray();
        int right = tempChars.length-1;

        //reverse
        for (int left=0; left < right ; left++, right--){
            char tempChar = tempChars[left];
            tempChars[left] = tempChars[right];
            tempChars[right]=tempChar;
        }
        //Sets the reversed char array to string
        String reversedString = String.valueOf(tempChars);

        //Search the string for blank space then if input == output
        if (reversedString.contains(" ")){
            return false;
        }
        else if(testString.equals(reversedString)){
            return true;
        }
        else return false;
    }

    public boolean PalindromeAlphaOnlyCaseSensitive(String testString){
        if (testString.isEmpty())
        {
            return false;
        }
        char[] tempChars = testString.toCharArray();
        int left, right=0;
        right = tempChars.length-1;

        //Check for non alphabetic characters
        for(int i = tempChars.length-1; i>=0; i--)
        {
            if(Character.isLetter(tempChars[i])==false)
            {
                return false;
            }
        }

        //Reverse string
        for (left=0; left < right ; left++ ,right--)
        {
            char tempChar = tempChars[left];
            tempChars[left] = tempChars[right];
            tempChars[right]=tempChar;
        }
        //Sets the reversed char array to string
        String testOutput = String.valueOf(tempChars);

        //Search the string for blank space then if input == output
        if (testOutput.contains(" "))
        {
            return false;
        }
        else if(testString.equals(testOutput))
        {
            return true;
        }
        else return false;
    }

    public boolean PalindromeAlphaOnlyIgnoreCase(String testString){
        if (testString.isEmpty()){
            return false;
        }
        char[] tempChars = testString.toCharArray();
        int left, right=0;
        right = tempChars.length-1;

        //Check for non alphabetic characters
        for(int i = tempChars.length-1; i>=0; i--){
            if(Character.isLetter(tempChars[i])==false){
                return false;
            }
        }

        //Reverse string
        for (left=0; left < right ; left++ ,right--){
            char tempChar = tempChars[left];
            tempChars[left] = tempChars[right];
            tempChars[right]=tempChar;
        }
        //Sets the reversed char array to string
        String testOutput = String.valueOf(tempChars);

        //Search the string for blank space then if input == output
        if (testOutput.contains(" ")){
            return false;
        }
        else if(testString.equalsIgnoreCase(testOutput)){
            return true;
        }
        else return false;
    }

    public boolean PalindromeAlphaOnlyCaseSensitiveIncludeSpace(String testString){
        if (testString.isEmpty()){
            return false;
        }
        char[] tempChars = testString.toCharArray();
        int left, right=0;
        right = tempChars.length-1;

        //Check for non alphabetic characters
        for(int i = tempChars.length-1; i>=0; i--){
            if(Character.isLetter(tempChars[i])==false && Character.isSpaceChar(tempChars[i])==false){
                return false;
            }
        }

        //Reverse string
        for (left=0; left < right ; left++ ,right--){
            char tempChar = tempChars[left];
            tempChars[left] = tempChars[right];
            tempChars[right]=tempChar;
        }
        //Sets the reversed char array to string
        String testOutput = String.valueOf(tempChars);

        //Search the string for blank space then if input == output
        if(testString.equals(testOutput)){
            return true;
        }
        else return false;
    }

    public boolean PalindromeAlphaOnlyIgnoreCaseIncludeSpace(String testString){
        if (testString.isEmpty()){
            return false;
        }
        char[] tempChars = testString.toCharArray();
        int left, right=0;
        right = tempChars.length-1;

        //Check for non alphabetic characters
        for(int i = tempChars.length-1; i>=0; i--){
            if(Character.isLetter(tempChars[i])==false && Character.isSpaceChar(tempChars[i])==false){
                return false;
            }
        }

        //Reverse string
        for (left=0; left < right ; left++ ,right--){
            char tempChar = tempChars[left];
            tempChars[left] = tempChars[right];
            tempChars[right]=tempChar;
        }
        //Sets the reversed char array to string
        String testOutput = String.valueOf(tempChars);

        //Search the string for blank space then if input == output
        if(testString.equalsIgnoreCase(testOutput)){
            return true;
        }
        else return false;
    }

    public boolean PalindromeAlphanumericCaseSensitive(String testString){
        if (testString.isEmpty()){
            return false;
        }
        char[] tempChars = testString.toCharArray();
        int left, right=0;
        right = tempChars.length-1;

        //Check for non alphabetic characters
        for(int i = tempChars.length-1; i>=0; i--){
            if(Character.isLetterOrDigit(tempChars[i])==false){
                return false;
            }
        }

        //Reverse string
        for (left=0; left < right ; left++ ,right--){
            char tempChar = tempChars[left];
            tempChars[left] = tempChars[right];
            tempChars[right]=tempChar;
        }
        //Sets the reversed char array to string
        String testOutput = String.valueOf(tempChars);

        //Search the string for blank space then if input == output
        if (testOutput.contains(" ")){
            return false;
        }
        else if(testString.equals(testOutput)){
            return true;
        }
        else return false;
    }

    public boolean PalindromeAlphanumericIgnoreCase(String testString){
        if (testString.isEmpty()){
            return false;
        }
        char[] tempChars = testString.toCharArray();
        int left, right=0;
        right = tempChars.length-1;

        //Check for non alphabetic characters
        for(int i = tempChars.length-1; i>=0; i--){
            if(Character.isLetterOrDigit(tempChars[i])==false){
                return false;
            }
        }

        //Reverse string
        for (left=0; left < right ; left++ ,right--){
            char tempChar = tempChars[left];
            tempChars[left] = tempChars[right];
            tempChars[right]=tempChar;
        }
        //Sets the reversed char array to string
        String testOutput = String.valueOf(tempChars);

        //Search the string for blank space then if input == output
        if (testOutput.contains(" ")){
            return false;
        }
        else if(testString.equalsIgnoreCase(testOutput)){
            return true;
        }
        else return false;
    }

    public boolean PalindromeAlphanumericCaseSensitiveIncludeSpace(String testString){
        if (testString.isEmpty()){
            return false;
        }
        char[] tempChars = testString.toCharArray();
        int left, right=0;
        right = tempChars.length-1;

        //Check for non alphabetic characters
        for(int i = tempChars.length-1; i>=0; i--){
            if(Character.isLetterOrDigit(tempChars[i])==false && Character.isSpaceChar(tempChars[i])==false){
                return false;
            }
        }

        //Reverse string
        for (left=0; left < right ; left++ ,right--){
            char tempChar = tempChars[left];
            tempChars[left] = tempChars[right];
            tempChars[right]=tempChar;
        }
        //Sets the reversed char array to string
        String testOutput = String.valueOf(tempChars);

        //Search the string for blank space then if input == output
        if(testString.equals(testOutput)){
            return true;
        }
        else return false;
    }

    public boolean PalindromeAlphanumericIgnoreCaseIncludeSpace(String testString){
        if (testString.isEmpty()){
            return false;
        }
        char[] tempChars = testString.toCharArray();
        int left, right=0;
        right = tempChars.length-1;

        //Check for non alphabetic characters
        for(int i = tempChars.length-1; i>=0; i--){
            if(Character.isLetterOrDigit(tempChars[i])==false && Character.isSpaceChar(tempChars[i])==false){
                return false;
            }
        }

        //Reverse string
        for (left=0; left < right ; left++ ,right--){
            char tempChar = tempChars[left];
            tempChars[left] = tempChars[right];
            tempChars[right]=tempChar;
        }
        //Sets the reversed char array to string
        String testOutput = String.valueOf(tempChars);

        //Search the string for blank space then if input == output
        if(testString.equalsIgnoreCase(testOutput)){
            return true;
        }
        else return false;
    }

    public boolean PalindromeAnyIgnoreCaseNoSpace(String testString){
        if (testString.isEmpty()){
            return false;
        }
        char[] tempChars = testString.toCharArray();
        int left, right=0;
        right = tempChars.length-1;

        //Reverse string
        for (left=0; left < right ; left++ ,right--){
            char tempChar = tempChars[left];
            tempChars[left] = tempChars[right];
            tempChars[right]=tempChar;
        }
        //Sets the reversed char array to string
        String testOutput = String.valueOf(tempChars);

        //Search the string for blank space then if input == output
        if (testOutput.contains(" ")){
            return false;
        }
        else if(testString.equalsIgnoreCase(testOutput)){
            return true;
        }
        else return false;
    }

    public boolean PalindromeAnyIgnoreCase(String testString){
        if (testString.isEmpty()){
            return false;
        }
        char[] tempChars = testString.toCharArray();
        int left, right=0;
        right = tempChars.length-1;

        //Reverse string
        for (left=0; left < right ; left++ ,right--){
            char tempChar = tempChars[left];
            tempChars[left] = tempChars[right];
            tempChars[right]=tempChar;
        }
        //Sets the reversed char array to string
        String testOutput = String.valueOf(tempChars);

        //Search the string for blank space then if input == output
        if(testString.equalsIgnoreCase(testOutput)){
            return true;
        }
        else return false;
    }

    public boolean PalindromeAnyCaseSensitiveNoSpace(String testString){
        if (testString.isEmpty()){
            return false;
        }
        char[] tempChars = testString.toCharArray();
        int left, right=0;
        right = tempChars.length-1;

        //Reverse string
        for (left=0; left < right ; left++ ,right--){
            char tempChar = tempChars[left];
            tempChars[left] = tempChars[right];
            tempChars[right]=tempChar;
        }
        //Sets the reversed char array to string
        String testOutput = String.valueOf(tempChars);

        //Search the string for blank space then if input == output
        if (testOutput.contains(" ")){
            return false;
        }
        else if(testString.equals(testOutput)){
            return true;
        }
        else return false;
    }

    public boolean PalindromeAnyCaseSensitive(String testString){
        if (testString.isEmpty()){
            return false;
        }
        char[] tempChars = testString.toCharArray();
        int left, right=0;
        right = tempChars.length-1;

        //Reverse string
        for (left=0; left < right ; left++ ,right--){
            char tempChar = tempChars[left];
            tempChars[left] = tempChars[right];
            tempChars[right]=tempChar;
        }
        //Sets the reversed char array to string
        String testOutput = String.valueOf(tempChars);

        //Search the string for blank space then if input == output
        if(testString.equals(testOutput)){
            return true;
        }
        else return false;
    }


}
