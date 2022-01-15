package order;

public class status {
    private int  currentStatus;
    private int shipped = 2 ;
    private int pendding = 1;
    private int finished = 3;

    public status() {
        currentStatus = pendding;
    }

    public String getStatus(){
        switch(currentStatus){
            case 1:
                return "pendding";
            case 2:
                return "Shipped";
            case 3:
                return "Finished";
            default:
                return null;
        }
    }
    public void updateStatus(int currentStatus2){
        switch(currentStatus2){
            case 1:
                currentStatus = pendding;
            case 2:
                currentStatus = shipped;
            case 3:
                currentStatus = finished;
            default:
                break;
        }
    }



}