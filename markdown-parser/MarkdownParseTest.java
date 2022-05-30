import static org.junit.Assert.*;
import org.junit.*;
import java.util.*;
import java.io.IOException;
import java.nio.file.*;
/**
 * This file is intended to test the functionalities of MarkDownParse.
 */
public class MarkdownParseTest {
    @Test
    public void addition() {
        assertEquals(2, 1 + 1);
    }

    @Test
    public void testGetLinksForTestFile1() throws IOException {
        Path fileName = Path.of("test-file.md");
        String content = Files.readString(fileName);
        List<String> expected = List.of("https://something.com",
        "some-page.html");
        assertEquals(expected,MarkdownParse.getLinks(content));
    }

    @Test
    public void testGetLinksForTestFile2() throws IOException {
        Path fileName = Path.of("testfile2.md");
        String content = Files.readString(fileName);
        List<String> expected = List.of("www.myspace.com",
        "www.facebook.com");
        assertEquals(expected,MarkdownParse.getLinks(content));
    }
    @Test
    public void testGetLinksForTestFile3() throws IOException {
        Path fileName = Path.of("testfile3.md");
        String content = Files.readString(fileName);
        List<String> expected = List.of("www.speedrunners.com",
        "www.google.com");
        assertEquals(expected,MarkdownParse.getLinks(content));
    }

    @Test
    public void testGetLinksForTestFile4() throws IOException {
        Path fileName = Path.of("try-break.md");
        String content = Files.readString(fileName);
        List<String> expected = List.of(
            "https://www.youtube.com/watch?v=dQw4w9WgXcQ");
        assertEquals(expected,MarkdownParse.getLinks(content));
    }
    @Test 
    public void testIfActualLink() throws IOException {
        Path fileName = Path.of("testfarlink.md");
        String content = Files.readString(fileName);
        List<String> expected = new ArrayList<>();
        assertEquals(expected, MarkdownParse.getLinks(content));
    }

    @Test
    public void aFailingTestNowSuccessful() {
        assertEquals("https://www.youtube.com/watch?v=dQw4w9WgXcQ",
            "https://www.youtube.com/watch?v=dQw4w9WgXcQ");
    }

    @Test
    public void oneNewFailureInducingInput() throws IOException {
        Path fileName = Path.of("One-more-failure.md");
        String content = Files.readString(fileName);
        List<String> expected = new ArrayList<>();
        List<String> unexpected = List.of("Bruh");
        assertNotEquals(unexpected,MarkdownParse.getLinks(content));
        assertEquals(expected, MarkdownParse.getLinks(content));
    }
/*****************************Snippet 1**************************************/

    @Test
    public void testSnippet1part1() {
        List<String> expected1 = new ArrayList<>();
        List<String> actual1 = MarkdownParse.getLinks("`[a link`](url.com)");
        assertEquals(expected1,actual1);
        /*fix by checking for open and closing graves before the parentheses
            set and starting after the closing grave.
        */
    }
    @Test 
    public void testSnippet1part2() {
        List<String> expected2 = List.of("`google.com");
        List<String> actual2 = MarkdownParse.getLinks("[another link](`google.com)`");
        assertEquals(expected2,actual2);
    }
    @Test 
    public void testSnippet1part3() {
        List<String> expected3 = List.of("google.com");
        List<String> actual3 = MarkdownParse.getLinks("[`cod[e`](google.com)");
        assertEquals(expected3,actual3);
    }
    @Test 
    public void testSnippet1part4() {
        List<String> expected4 = List.of("ucsd.edu");
        List<String> actual4 = MarkdownParse.getLinks("[`code]`](ucsd.edu)");
        assertEquals(expected4,actual4);
    }

/*****************************Snippet 2**************************************/
    @Test
    public void testSnippet2part1() throws IOException {
        List<String> actual1 = MarkdownParse.getLinks(
            "[a [nested link](a.com)](b.com)");
        List<String> expected1 = List.of("a.com");
        assertEquals(expected1,actual1);
    }
    
    @Test
    public void testSnippet2part2() {
        List<String> actual2 = MarkdownParse.getLinks(
            "[a nested parenthesized url](a.com(()))");
        List<String> expected2 = List.of("a.com(())");
        assertEquals(expected2,actual2);
        //check for lastIndex of the parentheses before the next openBracket
    }
    
    @Test
    public void testSnippet2part3() throws IOException {
        List<String> actual3 = MarkdownParse.getLinks(Files.readString(
            Path.of("4snippet2num3.md")));
        List<String> expected3 = List.of("example.com");
        assertEquals(expected3,actual3); 
    }


/*****************************Snippet 3**************************************/
    @Test 
    public void testSnippet3part1() throws IOException {
        Path fileName = Path.of("4snippet3num1.md");
        String content1 = Files.readString(fileName);
        List<String> expected1 = new ArrayList<>();
        assertEquals(expected1,MarkdownParse.getLinks(content1));
        //fix by checking line breaks and skipping after that
    }
    
    @Test
    public void testSnippet3part2() throws IOException {
        Path fileName2 = Path.of("4snippet3num2.md");
        String content2 = Files.readString(fileName2);
        List<String> expected2 = List.of(
            "https://sites.google.com/eng.ucsd.edu/cse-15l-spring-2022/schedule");
        assertEquals(expected2, MarkdownParse.getLinks(content2));
        //fix by trim
    }

    @Test
    public void testSnippet3part3() throws IOException{
        Path fileName3 = Path.of("4snippet3num3.md");
        String content3 = Files.readString(fileName3);
        List<String> expected3 = new ArrayList<>();
        assertEquals(expected3,MarkdownParse.getLinks(content3));
        //fix by check for closing paren
    }
    
    @Test
    public void testSnippet3part4() throws IOException {
        Path fileName4 = Path.of("4snippet3num4.md");
        String content4 = Files.readString(fileName4);
        List<String> expected4 = new ArrayList<>();
        assertEquals(expected4,MarkdownParse.getLinks(content4));
        //fix by check for new lines 
    }
}
