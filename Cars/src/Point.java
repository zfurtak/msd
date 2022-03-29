public class Point {
    int type;
    Point next;
    Point left;
    Point right;
    boolean moved = false;
    public int acc;
    int maxAcc;
    public static Integer[] types = {0, 1, 2, 3, 5};


    public void move(int cnt) {
        next.maxAcc = this.maxAcc;
        this.maxAcc = 0;
        next.acc = cnt;
        next.type = this.type;
        this.type = 0;
        this.acc = 0;
        this.moved = true;
        next.moved = true;

    }

    public void stay(){
        this.acc = 0;
        this.moved = true;
    }

    public void overtake(){
        left.maxAcc = this.maxAcc;
        this.maxAcc = 0;
        left.type = this.type;
        this.type = 0;
        left.acc = this.acc + 1;
        this.acc = 0;
        this.moved = true;
    }

    public void goBack(){
        right.maxAcc = this.maxAcc;
        this.maxAcc = 0;
        right.type = this.type;
        this.type = 0;
        right.acc = this.acc;
        this.acc = 0;
        this.moved = true;
    }

    boolean isACar(){
        return this.type == 1 || this.type == 2 || this.type == 3;
    }

    public void clicked() {
        this.type = 0;
    }

    public void clear() {
        this.type = 0;
    }
}

