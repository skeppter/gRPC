import hello.Hello.PingMessage;
import hello.HealthCheckServiceGrpc;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import com.google.protobuf.Timestamp;

public class HealthCheckServiceImpl extends HealthCheckServiceGrpc.HealthCheckServiceImplBase {
    private static final Logger logger = Logger.getLogger(HealthCheckServiceImpl.class.getName());
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    // Track client IDs and their corresponding StreamObservers for ongoing communication
    private final ConcurrentHashMap<String, StreamObserver<PingMessage>> clients = new ConcurrentHashMap<>();

    public HealthCheckServiceImpl() {
        startSendingHealthChecks();
    }

    @Override
    public StreamObserver<PingMessage> checkHealth(StreamObserver<PingMessage> responseObserver) {
        return new StreamObserver<PingMessage>() {
            String clientId = null;

            @Override
            public void onNext(PingMessage value) {
                clientId = value.getClientID();
                // Assuming the client sends a ping as a response to the server's health check request
                if ("healthy".equals(value.getStatus())) {
                    logResponse(clientId, "healthy");
                } else {
                    logResponse(clientId, "no response");
                }
                // Update or add the client's response observer for future communication
                clients.put(clientId, responseObserver);
            }

            @Override
            public void onError(Throwable t) {
                logger.warning("Client " + clientId + " health check stream error: " + t.getMessage());
                clients.remove(clientId);
            }

            @Override
            public void onCompleted() {
                logger.info("Client " + clientId + " health check stream completed.");
                clients.remove(clientId);
                responseObserver.onCompleted();
            }
        };
    }

    public void startSendingHealthChecks() {
        scheduler.scheduleAtFixedRate(() -> {
            Timestamp now = Timestamp.newBuilder()
                    .setSeconds(System.currentTimeMillis() / 1000)
                    .setNanos((int) ((System.currentTimeMillis() % 1000) * 1000000)).build();

            clients.forEach((clientId, clientStream) -> {
                PingMessage ping = PingMessage.newBuilder()
                        .setClientID(clientId)
                        .setTimestamp(now)
                        .setStatus("ping") // Optional: indicate this is a ping message
                        .build();
                clientStream.onNext(ping);
            });
        }, 0, 2, TimeUnit.SECONDS); // Send a ping every 2 seconds
    }


    private void logResponse(String clientId, String status) {
        // Format the log message
        String logMessage = String.format("ClientID: %s, Time: %s, Status: %s%n", // %n for platform-independent newline
                clientId,
                java.time.Instant.now().toString(), // Using Instant.now() for simplicity
                status);

        // Define the path to the log file
        java.nio.file.Path path = Paths.get("health_checks.log");

        try {
            // Ensure directory exists (optional, remove if directory surely exists)
            java.nio.file.Path parentDir = path.getParent();
            if (parentDir != null) Files.createDirectories(parentDir);

            // Write the log message to the file, creating the file if it doesn't exist, and appending if it does
            Files.write(path, logMessage.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            logger.severe("Failed to log health check response: " + e.getMessage());
        }
    }

    public void shutdown() {
        scheduler.shutdown();
        try {
            if (!scheduler.awaitTermination(5, TimeUnit.SECONDS)) {
                scheduler.shutdownNow();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            scheduler.shutdownNow();
        }
    }
}
