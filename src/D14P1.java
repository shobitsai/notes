import java.util.*;
import java.util.concurrent.*;

class Order{
    int orderId;
    String customerName;
    String productName;
    int quantity;
    double pricePerUnit;
    double totalAmount;

    public Order(int orderId, String customerName, String productName,
                 int quantity, double pricePerUnit) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.productName = productName;
        this.quantity = quantity;
        this.pricePerUnit = pricePerUnit;
        this.totalAmount = quantity * pricePerUnit;
    }
    
    public String toString(){
        return "Order[ID=" + orderId +
                ", Customer=" + customerName +
                ", Product=" + productName +
                ", Qty=" + quantity +
                ", Total=" + totalAmount + "]";
    }
}

class OrderBuffer{
    Queue<Order> buffer; 
    int capacity;
    public OrderBuffer(int bC){
        this.capacity=bC;
        buffer=new LinkedList<>(); 
    }
    public synchronized void put(Order order) throws Exception{
        while(buffer.size()>=capacity){
            wait();
        }
        buffer.add(order);
        notifyAll();
    }
    public synchronized Order take() throws Exception{
        while(buffer.size()==0)wait();
        Order curr=buffer.poll();
        notifyAll();
        return curr;
    }
}

class OrderProducer implements Callable<List<String>>{
    OrderBuffer buffer;
    List<Order> orders;
    public OrderProducer(OrderBuffer buffer,List<Order> orders){
        this.buffer=buffer;
        this.orders=orders;
    }
    public List<String> call() throws Exception{
        List<String> logs=new ArrayList<>();
        for(Order o:orders){
            buffer.put(o);
            logs.add("PRODUCED " + o.toString());
        }
        return logs;
    }
}

class OrderConsumer implements Callable<List<String>>{
    OrderBuffer buffer;
    int count;

    public OrderConsumer(OrderBuffer buffer, int count) {
        this.buffer = buffer;
        this.count = count;
    }
    
    public List<String> call() throws Exception{
        List<String> logs=new ArrayList<>();
        while(count>0){
            count--;
            Order order = buffer.take();
            logs.add("CONSUMED Order[ID=" + order.orderId +
                     ", Total=" + order.totalAmount + "]");
        }
        return logs;
    }
}

public class ProducerConsumerCallableDemo {
    

    /* ==========================
       IMPLEMENT YOUR CODE HERE
       ========================== */
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        int bufferCapacity = sc.nextInt();
        int n = sc.nextInt();

        List<Order> orders = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            orders.add(new Order(
                    sc.nextInt(),
                    sc.next(),
                    sc.next(),
                    sc.nextInt(),
                    sc.nextDouble()
            ));
        }

        OrderBuffer buffer = new OrderBuffer(bufferCapacity);
        ExecutorService executor = Executors.newFixedThreadPool(2);

        long start = System.currentTimeMillis();

        Future<List<String>> producerFuture =
                executor.submit(new OrderProducer(buffer, orders));

        Future<List<String>> consumerFuture =
                executor.submit(new OrderConsumer(buffer, n));

        /* ---- PHASE 1: PRINT PRODUCERS ---- */
        for (String log : producerFuture.get()) {
            System.out.println(log);
        }

        /* ---- PHASE 2: PRINT CONSUMERS ---- */
        for (String log : consumerFuture.get()) {
            System.out.println(log);
        }

        executor.shutdown();
        sc.close();
    }
}
