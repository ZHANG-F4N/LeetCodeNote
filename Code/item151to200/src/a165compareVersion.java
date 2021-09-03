public class a165compareVersion {
    public static void main(String[] args) {
        String version1 = "1.2";
        String version2 = "1.10";
        System.out.println(compareVersion(version1, version2));
    }

    public static int compareVersion(String version1, String version2) {
        String[] number1 = version1.split("\\.");
        String[] number2 = version2.split("\\.");
        int i = 0, j = 0;
        while (i < number1.length && j < number2.length) {
            int ver1 = Integer.parseInt(number1[i]);
            int ver2 = Integer.parseInt(number2[j]);
            if (ver1 > ver2) {
                return 1;
            } else if (ver1 < ver2) {
                return -1;
            }
            i++;
            j++;
        }

        while (i < number1.length) {
            if (Integer.parseInt(number1[i]) != 0) {
                return 1;
            }
            i++;
        }
        while (j < number2.length) {
            if (Integer.parseInt(number2[j]) != 0) {
                return -1;
            }
            j++;
        }
        return 0;

    }
}
