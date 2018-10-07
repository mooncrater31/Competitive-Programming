public class TT {
    private static int level = 0;

    public static long fact(int n) {
        level++;
        return n < 2 ? n : n * fact(n - 1);
    }

    public static void main(String[] args) throws InterruptedException {
        final int n = 20 ;
        Thread t = new Thread(null, null, "TT", 1180000) {
            @Override
            public void run() {
                try {
                    level = 0;
                    System.out.println(fact(1 << n));
                } catch (StackOverflowError e) {
                    System.err.println("true recursion level was " + level);
                    System.err.println("reported recursion level was "
                            + e.getStackTrace().length);
                }
            }

        };
        t.start();
        t.join();
        try {
            level = 0;
            System.out.println(fact(1 << n));
        } catch (StackOverflowError e) {
            System.err.println("true recursion level was " + level);
            System.err.println("reported recursion level was "
                    + e.getStackTrace().length);
        }
    }

}