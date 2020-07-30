class InputClass {
    public static String getString() {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        String input = scanner.nextLine();
        scanner.close();
        return input;
    }
}