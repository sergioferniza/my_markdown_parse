import static org.junit.Assert.*;
import org.junit.*;
import java.util.*;
import java.io.IOException;
import java.nio.file.*;

/// Test file for markdow-parse method ///

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

/// snippet 1 ///

    @Test

    public void testSnip_1_1() {
        List<String> actVal = MarkdownParse.getLinks("`[a link`](url.com)");
        List<String> expVal = new ArrayList<>();
        assertEquals(expVal,actVal);
    }

    @Test 

    public void testSnip_1_2() {
        List<String> actVal = MarkdownParse.getLinks("[another link](`google.com)`");
        List<String> expVal = List.of("`google.com");
        assertEquals(expVal,actVal);
    }

    @Test 

    public void testSnip_1_3() {
        List<String> actVal = MarkdownParse.getLinks("[`cod[e`](google.com)");
        List<String> expVal = List.of("google.com");
        assertEquals(expVal,actVal);
    }

    @Test 

    public void testSnip_1_4() {
        List<String> actVal = MarkdownParse.getLinks("[`code]`](ucsd.edu)");
        List<String> expVal = List.of("ucsd.edu");
        assertEquals(expVal,actVal);
    }

/// snippet 2 ///

    @Test

    public void testSnip2_1() throws IOException {
        List<String> actVal = MarkdownParse.getLinks(
            "[a [nested link](a.com)](b.com)");
        List<String> expVal = List.of("a.com");
        assertEquals(expVal,actVal);
    }
    
    @Test

    public void testSnip2_2() {
        List<String> actVal = MarkdownParse.getLinks(
            "[a nested parenthesized url](a.com(()))");
        List<String> expVal = List.of("a.com(())");
        assertEquals(expVal,actVal);
    }
    
    @Test

    public void testSnip2_3() throws IOException {
        List<String> actVal = MarkdownParse.getLinks(Files.readString(
            Path.of("snip2_3.md")));
        List<String> expVal = List.of("example.com");
        assertEquals(expVal,actVal); 
    }


/// snippet 3 ///

    @Test 

    public void testSnip3_1() throws IOException {
        Path file = Path.of("snip3_1.md");
        String fileContent = Files.readString(file);
        List<String> expVal = new ArrayList<>();
        assertEquals(expVal,MarkdownParse.getLinks(fileContent));
    }
    
    @Test

    public void testSnip3_2() throws IOException {
        Path file = Path.of("snip3_2.md");
        String fileContent = Files.readString(file);
        List<String> expVal = List.of(
            "https://sites.google.com/eng.ucsd.edu/cse-15l-spring-2022/schedule");
        assertEquals(expVal, MarkdownParse.getLinks(fileContent));
    }

    @Test

    public void testSnip3_3() throws IOException{
        Path file = Path.of("snip3_3.md");
        String fileContent = Files.readString(file);
        List<String> expVal = new ArrayList<>();
        assertEquals(expVal,MarkdownParse.getLinks(fileContent));
    }
    
    @Test

    public void testSnip3_4() throws IOException {
        Path file = Path.of("snip3_4.md");
        String fileContent = Files.readString(file);
        List<String> expVal = new ArrayList<>();
        assertEquals(expVal,MarkdownParse.getLinks(fileContent));
    }
}
