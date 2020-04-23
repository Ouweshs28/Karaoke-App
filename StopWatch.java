public class StopWatch {
    private long startTime;
    
    public StopWatch(){
        startTime=0;
    }
    
    public void start(){
        startTime= System.currentTimeMillis();
    }
    
    public double stop(){
        return (System.currentTimeMillis() - startTime)/1000.0;
    }
}
