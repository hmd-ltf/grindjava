import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
public class NotificationSender implements Runnable {

  private Queue<Notification> notificationQueue = new ConcurrentLinkedQueue<>(); 

  @Override
  public void run() {
    System.out.println("Running");
  }

  

}
