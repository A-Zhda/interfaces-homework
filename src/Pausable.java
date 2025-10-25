public interface Pausable {
    void pause(int from, int to); // прекратить подписку с from до to
    boolean isPausedOn(int date);
}
