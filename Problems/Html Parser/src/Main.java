import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Main {
    public static void main(String[] args) {
        String str = new Scanner(System.in).nextLine();
        Deque<String> tags = new ArrayDeque<>();
        Deque<String> content = new ArrayDeque<>();

        Pattern pattern = Pattern.compile("<(.*?)>");
        Matcher matcher = pattern.matcher(str);
        String item = null;
        String regex = null;
        Pattern p;
        Matcher m;
        String lastItem = null;

        while (matcher.find()) {
            item = matcher.group(1);
            lastItem = "/" + tags.peekLast();
            if (!tags.isEmpty() && lastItem.equals(item)) {
                tags.pollLast();
                System.out.println(content.pollLast());
                regex = "<" + item.substring(1) + ">(.*?)<" + item + ">";
                p = Pattern.compile(regex);
                m = p.matcher(str);
                str = m.replaceFirst("");
            } else {
                regex = "<" + item + ">(.*?)<\\/" + item + ">";
                p = Pattern.compile(regex);
                m = p.matcher(str);
                tags.offerLast(item);
                if (m.find()) {
                    content.offerLast(m.group(1));
                }
            }
        }
    }
}