package backend.models;

public class CostResult {
    public int code;
    public int remaining;

    public CostResult(){
        code = 0;
        remaining = 0;
    }
    public CostResult(int res, int rem){
        code = res;
        remaining = remaining;
    }

    public void set(int res, int rem){
        code = res;
        remaining = rem;
    }
}
