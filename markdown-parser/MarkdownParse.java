//https://howtodoinjava.com/java/io/java-read-file-to-string-examples/

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;


public class MarkdownParse {
    static final int nonexistent = -1;

    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        // find the next [, then find the ], then find the (, then read link upto next )
        int currentIndex = 0;
        while(currentIndex < markdown.length()) {
            int openBracket = markdown.indexOf("[", currentIndex);
            int backTick = markdown.indexOf("`",currentIndex);
            if(backTick != nonexistent && backTick < openBracket) {
                currentIndex = openBracket+1;
                continue;
            }
            if(openBracket == nonexistent) {
                break;
            }
            int closeBracket = markdown.indexOf("]", openBracket);
            String checkLn = markdown.substring(openBracket,
                closeBracket).replaceFirst("\n", "");
            if(checkLn.contains("\n")) {
                currentIndex = closeBracket+1;
                continue;
            }
            int openParen = markdown.indexOf("(", closeBracket);
            int closeParen = markdown.indexOf(")", openParen);
            if(closeParen == nonexistent) {
                break;
            }
            String excludeBoundaryChars = markdown.substring(openParen+3,
                closeParen-3);
            if(excludeBoundaryChars.contains("\n")) {
                currentIndex = closeParen+1;
                continue;
            }
            String withinParen = markdown.substring(openParen+1,closeParen);
            if(withinParen.toLowerCase().endsWith(".jpg") || 
                withinParen.toLowerCase().endsWith(".png") ||
                withinParen.toLowerCase().endsWith(".pdf") ||
                ! withinParen.contains(".") || 
                withinParen.indexOf(".") == withinParen.length()-1 
                ) {}
            else {
                toReturn.add(withinParen.trim());
            }
            currentIndex = closeParen + 1;
        }
        return toReturn;
    }


    public static void main(String[] args) throws IOException {
        Path file = Path.of(args[0]);
        String fileContent = Files.readString(file);
        ArrayList<String> links = getLinks(fileContent);
	    System.out.println(links);
        System.out.println("Next File");
    }
}