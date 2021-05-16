package cat.bb_8;


public class variables extends android.app.Activity {

    private static String ip = "tcp://192.168.43.30";

    public static String getip() {
        return ip;
    }

    public static void setip(String ip) {
        variables.ip = "tcp://"+ip;
    }
}

