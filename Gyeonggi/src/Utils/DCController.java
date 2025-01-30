package Utils;

public class DCController {
	public boolean dlock = true;
	public boolean clock = true;
	public boolean tlock = true;
	
	public boolean isClock() {
        return clock;
    }

    public void setClock(boolean globalClock) {
        this.clock = globalClock;
    }

    public boolean isTlock() {
        return tlock;
    }

    public void setTlock(boolean globalTlock) {
        this.tlock = globalTlock;
    }
    
    public boolean isDlock() {
        return dlock;
    }

    public void setDlock(boolean globalDlock) {
        this.dlock = globalDlock;
    }
}
