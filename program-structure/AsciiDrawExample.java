
class Glyph {
    char glyph;

    Glyph(char glyph) {
        this.glyph = glyph;
    }

    void draw() {
        System.out.print(glyph);
    }
    void erase() {
        System.out.print("\b");
    }
}


class DrawBox {
    int width;
    Glyph[] box;

    DrawBox(int width) {
        this.width = width;
        box = new Glyph[width];
        fill(' ');
    }

    void fill(char fillChar) {
        for(int widthCounter=0; widthCounter<width; widthCounter++) {
            box[widthCounter] = new Glyph(fillChar);
        }
    }

    void clear() {
        for(int widthCounter=0; widthCounter<width; widthCounter++) {
            box[widthCounter].erase();
        }
    }

    void setString(String s) {
        for(int widthCounter=0; widthCounter<s.length(); widthCounter++) {
            box[widthCounter] = new Glyph(s.charAt(widthCounter));
        }
    }

    void draw() {
        clear();
        for(int widthCounter=0; widthCounter<width; widthCounter++) {
            box[widthCounter].draw();
        }
    }

    void shiftRight() {
        for(int widthCounter=width-1; widthCounter>0; widthCounter--) {
            box[widthCounter] = box[widthCounter-1];
        }
        box[0] = new Glyph(' ');
    }
}


public class AsciiDrawExample {
    public static void main(String[] args) throws InterruptedException {
        Glyph a = new Glyph('a');
        Glyph b = new Glyph('b');
        Glyph c = new Glyph('c');
        a.draw();
        b.draw();
        c.draw();
        c.erase();

        System.out.println();
        DrawBox box = new DrawBox(60);

        box.setString("-- Help meeee! -->");
        box.draw();
        for(int i=0; i<box.width; i++) {
            Thread.sleep(200);
            box.shiftRight();
            box.draw();
        }
    }
}
