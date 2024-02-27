# Drinor Sutaj

First CLIL syt
Date: 20.02.2024

###### What is gRPC and why does it work across languages and platforms?

gRPC is a modern RPC framework that uses HTTP/2 and Protocol Buffers, making it efficient and interoperable across different languages and platforms due to its standardized, language-agnostic method of defining and serializing structured data.

### Describe the RPC life cycle starting with the RPC client?

1. **Invocation**: Client calls a method on a server.
2. **Request**: Client's library serializes and sends the request.
3. **Transmission**: Request is sent over the network.
4. **Server Processing**: Server executes the method and prepares a response.
5. **Response**: Server sends the serialized response back.
6. **Client Handling**: Client deserializes and processes the response.

### Describe the workflow of Protocol Buffers?

1. **Definition**: Define data structure in a `.proto` file.
2. **Compilation**: Use `protoc` to generate source code.
3. **Integration**: Integrate generated code into your application.
4. **Operation**: Serialize and deserialize data as needed using generated methods.

### What are the benefits of using protocol buffers?

- **Efficiency**: Smaller and faster than XML or JSON.
- **Interoperability**: Works across different languages and platforms.
- **Scalability**: Supports backward and forward compatibility.
- **Automatic Code Generation**: Reduces boilerplate code for serialization.

### When is the use of protocol not recommended?

- **Human Readability**: Binary format is not human-readable.
- **Dynamic Data Structures**: Not as flexible as JSON for schema-less data.
- **Small Projects**: Overhead may not be justified for simple payloads.

### List 3 different data types that can be used with protocol buffers?

1. **Basic Types**: `int32`, `string`, etc., for simple data.
2. **Enumerations (`enum`)**: For a set of named constants.
3. **Messages**: Complex, nested structures with multiple fields.

![](C:\Users\drino\AppData\Roaming\marktext\images\2024-02-20-17-15-42-image.png)

## ## Project Extension - Implementation of WarehouseData Service and Python Client

**Date of Extension: February 2024**

### Introduction to Project Extension

With the existing gRPC infrastructure in place, we identified a need to extend the functionality of our services to include a new feature: `WarehouseData`. This addition would enable clients to request specific warehouse-related data, providing a richer, more versatile API. Furthermore, to demonstrate cross-language compatibility and client-server interaction, we developed a Python client capable of interacting with our Java-based gRPC server.

### Extending the gRPC Service

#### Implementation of WarehouseData Service

1. **Service Definition**: We extended the `hello.proto` file to define a new RPC method `requestWarehouseData` within our service.

2. **Generating Stubs**: Utilizing the `protoc` compiler, we regenerated the gRPC stubs, which now included the newly defined `WarehouseData` service.

3. **Server Implementation**:
   
   - Created a new method within `HelloWorldServiceImpl` class that overrides the `requestWarehouseData` method from the generated stub.
   - Implemented the business logic to handle the incoming `WarehouseRequest` and to return the corresponding `WarehouseData`.

4. **Server Logging**: Modified the `HelloWorldServer` to include logging functionality that prints a message to the console whenever `WarehouseData` is requested. This aids in monitoring and debugging.
   
   ```java
   // Inside your HelloWorldServiceImpl class override
   @Override
   public void requestWarehouseData(WarehouseRequest request, StreamObserver<WarehouseData> responseObserver) {
       System.out.println("WarehouseData requested for ID: " + request.getWarehouseID());
       // Implementation of response generation
       WarehouseData data = ... // generate WarehouseData
       responseObserver.onNext(data);
       responseObserver.onCompleted();
   }
   ```
   
   ### Development of Python Client
   
   1. **Client Setup**: We established the Python environment and included necessary `grpcio` and `grpcio-tools` packages to enable gRPC functionality in Python.
   
   2. **Import Adjustments**: Modified the Python client code to adjust the import statements correctly, allowing the client to locate the generated `hello_pb2.py` and `hello_pb2_grpc.py` modules.

```python
import sys
sys.path.append('src/main/proto')
import hello_pb2
import hello_pb2_grpc
```

- **Client Functionality**:
  
  - Created a Python client script `HelloWorldPythonClient.py` that establishes a connection to the gRPC server.
  - Implemented the functionality to send `HelloRequest` and `WarehouseRequest` to the server and to receive the respective responses.

- **Testing**: Ran the Python client to ensure it successfully communicates with the Java server, correctly sends requests, and receives responses.

### Documentation Update

- **WarehouseData Feature**: Documented the process of adding `WarehouseData` feature to the gRPC service, including proto definition, server implementation, and logging.

- **Python Client Integration**: Described the steps taken to create a Python client that communicates with a Java gRPC server, demonstrating cross-language interoperability.

- **Troubleshooting**: Included common issues encountered during development, such as import errors, and provided solutions to address them.

### Conclusion

The extension of the gRPC service to include `WarehouseData` and the addition of a Python client serves as a testament to gRPC's powerful cross-language capabilities. It demonstrates how seamlessly services can be extended and how different components can be integrated to provide a comprehensive system that meets evolving business needs.
