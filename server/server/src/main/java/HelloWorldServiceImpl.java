import hello.Hello.HelloRequest;
import hello.Hello.HelloResponse;
import hello.Hello.WarehouseData;
import hello.Hello.WarehouseRequest;
import hello.Hello.Product;
import hello.HelloWorldServiceGrpc;
import io.grpc.stub.StreamObserver;
import com.google.protobuf.Timestamp;
import simulation.WarehouseSimulation; // Ensure this import matches your package structure

public class HelloWorldServiceImpl extends HelloWorldServiceGrpc.HelloWorldServiceImplBase {

    @Override
    public void hello(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
        // Implement your logic here
        String greeting = "Hello, " + request.getFirstname() + " " + request.getLastname();
        HelloResponse response = HelloResponse.newBuilder().setText(greeting).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void requestWarehouseData(WarehouseRequest request, StreamObserver<WarehouseData> responseObserver) {
        System.out.println("Someone there at Warehouse ID: " + request.getWarehouseID()+"?");
        // Simulate fetching data. Replace with your actual data fetching logic.
        WarehouseSimulation simulation = new WarehouseSimulation();
        // Assuming simulation.getData returns a compatible type. You might need to adjust this part.
        model.WarehouseData simulatedData = simulation.getData(request.getWarehouseID());

        // Building the WarehouseData response
        WarehouseData.Builder dataBuilder = WarehouseData.newBuilder()
                .setWarehouseID(simulatedData.getWarehouseID())
                .setWarehouseName(simulatedData.getWarehouseName())
                // Assuming you want to set the current timestamp
                .setTimestamp(Timestamp.newBuilder().setSeconds(System.currentTimeMillis() / 1000)
                        .setNanos((int) ((System.currentTimeMillis() % 1000) * 1000000)).build());

        // Add products to the response
        for (model.Product prod : simulatedData.getProducts()) {
            Product protobufProduct = Product.newBuilder()
                    .setProductID(prod.getProductID())
                    .setProductName(prod.getProductName())
                    .setProductCategory(prod.getProductCategory())
                    .setProductQuantity(prod.getProductQuantity())
                    .setProductUnit(prod.getProductUnit())
                    .build();
            dataBuilder.addProducts(protobufProduct);
        }

        responseObserver.onNext(dataBuilder.build());
        responseObserver.onCompleted();
    }
}
