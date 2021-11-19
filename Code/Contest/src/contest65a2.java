import java.util.HashMap;

public class contest65a2 {
    public static void main(String[] args) {

        Robot obj = new Robot(8, 2);
        obj.move(17);
        obj.move(21);
        obj.move(22);
        obj.move(34);
        obj.move(1);
        obj.move(46);
        obj.move(35);
        int[] param_2 = obj.getPos();
        String param_3 = obj.getDir();
        obj.move(2);
        obj.move(1);
        obj.move(4);
        int[] param_4 = obj.getPos();
        String param_5 = obj.getDir();
    }

    static class Robot {
        //public int[][] map = new int[201][201];
        //HashMap<Character, Character> hashMap;
        int width;
        int height;
        char dir;
        int posX;
        int posY;

        public Robot(int width, int height) {
            this.width = width;
            this.height = height;
            posX = 0;
            posY = 0;
            dir = 'E';
//            hashMap = new HashMap<>();
//            hashMap.put('E', 'N');
//            hashMap.put('N', 'W');
//            hashMap.put('W', 'S');
//            hashMap.put('S', 'E');
        }

        public void move(int num) {

            // 计算一圈多少格
            int loop = 2 * (width + height) - 4;
            while (num != 0) {
                if (posX == 0 && posY == 0) {
                    num = num % loop;
                    if (num == 0) {
                        dir = 'S';
                    } else {
                        dir = 'E';
                    }
                }
                // 0,0 num = 5
                if (dir == 'E') {
                    int dis = width - posX - 1;
                    if (dis >= num) {
                        posX = posX + num;
                        num = 0;
                    } else {
                        posX = width - 1;
                        dir = 'N';
                        num = num - dis;
                        continue;
                    }
                }
                if (dir == 'N') {
                    int dis = height - posY - 1;
                    if (dis >= num) {
                        posY = posY + num;
                        num = 0;
                    } else {
                        posY = height - 1;
                        dir = 'W';
                        num = num - dis;
                        continue;
                    }
                }
                if (dir == 'W') {
                    int dis = posX;
                    if (dis >= num) {
                        posX = posX - num;
                        num = 0;
                    } else {
                        posX = 0;
                        dir = 'S';
                        num = num - dis;
                        continue;
                    }
                }
                if (dir == 'S') {
                    int dis = posY;
                    if (dis >= num) {
                        posY = posY - num;
                        num = 0;
                    } else {
                        posY = 0;
                        dir = 'E';
                        num = num - dis;
                        continue;
                    }
                }
            }
        }

        public int[] getPos() {
            return new int[]{posX, posY};
        }

        public String getDir() {
            if (dir == 'E') {
                return "East";
            }
            if (dir == 'W') {
                return "West";
            }
            if (dir == 'N') {
                return "North";
            }
            return "South";
        }
    }
}