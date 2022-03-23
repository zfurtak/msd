public class Point {
    int type;
    Point next;
    boolean moved = false;
    public int acc;


    public void move(int cnt) {
        if(this.type == 1 && !this.moved){
            next.acc = cnt;
            this.type = 0;
            next.type = 1;
            this.moved = true;
            next.moved = true;
        }
    }

    public void clicked() {
        this.type = 1;
    }

    public void clear() {
        this.type = 0;
    }
}

