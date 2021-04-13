package project_16x16.ui;

public class CountdownClock {
    private long startTime, timeLimit, elapsedSec;

    public CountdownClock(long timeLimit){
        startTime = System.currentTimeMillis();
        this.timeLimit = timeLimit;
    }

    public long getTimeLimit() {
        return timeLimit;
    }

    public long timeRemaining(){
        return timeLimit - this.calculateTime();
    }

    public long calculateTime(){ //calculates time elapsed in seconds
        elapsedSec = (System.currentTimeMillis() - startTime)/1000;
        return elapsedSec;
    }

    public static void main(String args[]){
        System.out.println("TEST");
        CountdownClock c = new CountdownClock(10);
        while(c.calculateTime() <= c.getTimeLimit()){
            System.out.println(c.timeRemaining());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
