import hello.Hello;
import hello.HelloWorldServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class HelloWorldClient {

    public static void main(String[] args) {
        // Initialize the gRPC channel
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext()
                .build();

        // Create a blocking stub for making synchronous RPC calls
        HelloWorldServiceGrpc.HelloWorldServiceBlockingStub stub = HelloWorldServiceGrpc.newBlockingStub(channel);

        // Make a request to the hello service
        Hello.HelloResponse helloResponse = stub.hello(Hello.HelloRequest.newBuilder()
                .setFirstname("Max")
                .setLastname("Mustermann")
                .build());

        // Print the response from the hello service
        System.out.println(helloResponse.getText());

        // Make a request for warehouse data
        Hello.WarehouseData warehouseData = stub.requestWarehouseData(Hello.WarehouseRequest.newBuilder()
                .setWarehouseID("warehouse123")
                .build());

        // Print the warehouse data response
        System.out.println("Warehouse Data: " + warehouseData.toString());

        // Shutdown the channel
        channel.shutdown();
    }
}
