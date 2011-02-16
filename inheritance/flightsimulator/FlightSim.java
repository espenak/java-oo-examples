
class Glyph {
    char charsInGlyph[];
    Panel panel;
    int offset;
    int originalOffset;

    Glyph(int offset, Panel panel, String glyphString) {
        this.offset = offset;
        this.originalOffset = offset;
        this.panel = panel;
        this.charsInGlyph = glyphString.toCharArray();
    }

    void fillAreaWithNothing() {
        panel.fillAreaWithNothing(offset, offset+charsInGlyph.length);
    }

    void redraw() {
        for(int i=0; i<charsInGlyph.length; i++) {
            panel.putChar(offset+i, charsInGlyph[i]);
        }
        panel.redraw();
    }

    void moveRight() {
        fillAreaWithNothing();
        offset ++;
    }

    void resetOffset() {
        fillAreaWithNothing();
        offset = originalOffset;
    }

    int size() {
        return charsInGlyph.length;
    }
}


class Panel {
    char drawArea[];

    Panel(int width) {
        drawArea = new char[width];
        fillAreaWithNothing(0, width);
    }

    void fillAreaWithNothing(int offset, int size) {
        for(int i=offset; i<size; i++) {
            putChar(i, ' ');
        }
    }

    void backspaceOverAll() {
        for(char c : drawArea) {
            System.out.print("\b");
        }
    }

    void redraw() {
        backspaceOverAll();
        for(char c : drawArea) {
            System.out.print(c);
        }
    }

    void putChar(int offset, char c) {
        if(offset >= 0 && offset < drawArea.length) {
            drawArea[offset] = c;
        }
    }

    int size() {
        return drawArea.length;
    }
}



class Airplain extends Glyph {
    Airplain(Panel p, String glyphString) {
        super(0, p, glyphString);
    }
}

class F16eXXXtreeeme extends Airplain {
    F16eXXXtreeeme(Panel p) {
        super(p, "#====>");
    }
}


class Ammo extends Glyph {
    int timer;
    Ammo(Airplain airplain, Panel p, String glyphString, int timer) {
        super(airplain.size()+1, p, glyphString);
        this.timer = timer;
    }

    void animateRight() throws InterruptedException {
        Thread.sleep(timer);
        super.moveRight();
        redraw();
    }
}

class RailgunAmmo extends Ammo {
    RailgunAmmo(Airplain airplain, Panel p) {
        super(airplain, p, "*", 20);
    }
}


class FlightSim {
    public static void main(String[] args) throws InterruptedException {
        Panel p = new Panel(60);

        //while(true) {
            //for(int i=0; i<=80; i++) {
                //p.putChar(i, 'h');
                //p.putChar(10 + i, 'e');
                //p.putChar(20 + i, 'l');
                //p.putChar(30 + i, 'l');
                //p.putChar(40 + i, 'o');
                //p.redraw();
                //Thread.sleep(20);
                //p.fillAreaWithNothing(i, 40+i+1);
            //}
        //}


        //Glyph g = new Glyph(20, p, "h    e    l   l   o");
        F16eXXXtreeeme f16 = new F16eXXXtreeeme(p);
        RailgunAmmo ammo = new RailgunAmmo(f16, p);
        while(true) {
            for(int i=0; i<=p.size(); i++) {
                f16.moveRight();
                f16.redraw();
                for(int bulletSpeedUp=0; bulletSpeedUp<4; bulletSpeedUp++) {
                    ammo.animateRight();
                }
            }
            f16.resetOffset();
            ammo.resetOffset();
        }
    }
}
