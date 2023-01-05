public class KontrolerDanych {
    public synchronized static KontrolerDanych getInstance() {
        if(this == null) {
            return new KontrolerDanych();
        }
        return this;
    }
}
