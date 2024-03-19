import io.grpc.Server;
import io.grpc.ServerBuilder;
import java.io.IOException;

public class HelloWorldServer {

    private static final int PORT = 50051;
    private Server server;

    public void start() throws IOException {
        // Initialize services
        HealthCheckServiceImpl healthCheckService = new HealthCheckServiceImpl();

        // Build and start the server
        server = ServerBuilder.forPort(PORT)
                .addService(new HelloWorldServiceImpl())
                .addService(healthCheckService) // Add the HealthCheckService
                .build()
                .start();

        System.out.println("Server started, listening on " + PORT);

        // Add a shutdown hook
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.err.println("*** shutting down gRPC server since JVM is shutting down");
            this.stop();
            System.err.println("*** server shut down");
        }));
    }

    private void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    public void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        HelloWorldServer server = new HelloWorldServer();
        server.start();
        server.blockUntilShutdown();
    }
}
