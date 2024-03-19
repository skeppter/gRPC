import com.google.protobuf.Timestamp;
import hello.HealthCheckServiceGrpc;
import hello.Hello.PingMessage;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

public class HelloWorldClient {
    private static final String CLIENT_ID = "clientXXXXXX";

    public static void main(String[] args) throws InterruptedException {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext()
                .build();

        HealthCheckServiceGrpc.HealthCheckServiceStub healthStub = HealthCheckServiceGrpc.newStub(channel);

        StreamObserver<PingMessage> responseObserver = healthStub.checkHealth(new StreamObserver<PingMessage>() {
            @Override
            public void onNext(PingMessage ping) {
                System.out.println("Received health check from server for client ID: " + ping.getClientID());
                // Immediately respond indicating health
                Timestamp now = Timestamp.newBuilder()
                        .setSeconds(System.currentTimeMillis() / 1000)
                        .setNanos((int) ((System.currentTimeMillis() % 1000) * 1000000)).build();

                PingMessage response = PingMessage.newBuilder()
                        .setClientID(CLIENT_ID)
                        .setTimestamp(now)
                        .setStatus("healthy") // Indicating this client is healthy
                        .build();

                // Correct way to respond back to the server
                this.onNext(response); // Incorrect usage causing the redundancy error
            }

            @Override
            public void onError(Throwable t) {
                System.err.println("Health check stream error: " + t.getMessage());
                channel.shutdown();
            }

            @Override
            public void onCompleted() {
                System.out.println("Server has completed health checks.");
                channel.shutdownNow();
            }
        });

        // To keep the client alive to send and receive messages.
        Thread.sleep(Long.MAX_VALUE);
    }
}
