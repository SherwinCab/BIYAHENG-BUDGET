// Helper methods that draw boxes, rows, and bars in the console (plain ASCII, works on any terminal)
public class Ui {
    public static final int WIDTH = 56;

    public static String repeat(char c, int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(c);
        }
        return sb.toString();
    }

    public static void line() {
        System.out.println("+" + repeat('-', WIDTH - 2) + "+");
    }

    public static void title(String text) {
        line();
        int space = WIDTH - 2 - text.length();
        int left = Math.max(space / 2, 0);
        int right = Math.max(space - left, 0);
        System.out.println("|" + repeat(' ', left) + text + repeat(' ', right) + "|");
        line();
    }

    // One row: text on the left only
    public static void row(String left) {
        row(left, "");
    }

    // One row: text on the left and text on the right
    public static void row(String left, String right) {
        int space = WIDTH - 4 - left.length() - right.length();
        if (space < 1) {
            space = 1;
        }
        System.out.println("| " + left + repeat(' ', space) + right + " |");
    }

    public static String money(double amount) {
        return String.format("PHP %.2f", amount);
    }

    // Example: [##########----------] 50%
    public static String bar(double used, double total, int size) {
        double ratio = total <= 0 ? 0 : used / total;
        if (ratio > 1) {
            ratio = 1;
        }
        int filled = (int) Math.round(ratio * size);
        return "[" + repeat('#', filled) + repeat('-', size - filled) + "] "
                + (int) Math.round(ratio * 100) + "%";
    }
}
