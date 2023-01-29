package bot;

import serwer.dane.Gracz;

public class Bot extends Gracz implements Runnable {
    int poziom;

    public void setPoziom(String poziom) {
        switch (poziom) {
            case "latwy":
                this.poziom = 1;
                break;
            case "sredni":
                this.poziom = 3;
                break;
            case "trudny":
                this.poziom = 5;
                break;
            default:
                this.poziom = 0;
        }
    }


    @Override
    public void run() {

    }
}
