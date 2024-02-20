# Drinor Sutaj

First CLIL syt
Date: 20.02.2024



### What is gRPC and why does it work across languages and platforms?

gRPC is a modern, open source remote procedure call (RPC) framework that enables client and server applications to communicate transparently, and build connected systems. Developed by Google, it leverages HTTP/2 for transport, Protocol Buffers as the interface description language, and provides features such as authentication, load balancing, and more.

The reason gRPC works seamlessly across languages and platforms is due to its use of Protocol Buffers (Protobuf), a language-agnostic binary serialization format. Protobuf allows you to define how you want your data to be structured once, and then automatically generate source code to easily write and read the structured data to and from different data streams in a variety of languages. This ensures that gRPC can be used in a polyglot environment where services written in different languages can easily interoperate.

### Describe the RPC life cycle starting with the RPC client?

1. **Invocation**: The RPC life cycle begins when the client application invokes a call to a server's method, providing the necessary arguments. This is typically done through a stub or client object that provides methods corresponding to the server's services.
2. **Request**: The client's RPC library then serializes the method invocation, including the method name, and the serialized arguments, into a request message. This serialization is usually done using Protocol Buffers.
3. **Transmission**: The request message is then sent to the server via the network. If the RPC is synchronous, the client will block and wait for the server's response. If it's asynchronous, the client may continue with other tasks and handle the response at a later time.
4. **Server Processing**: Upon receiving the request, the server deserializes the message to understand which method was called and what the arguments are. It then executes the requested method with the provided arguments.
5. **Response**: The server then serializes the result of the method execution into a response message and sends this message back to the client.
6. **Client Handling**: The client receives the response message, deserializes it to obtain the method result, and then proceeds based on the outcome of the call, whether it's processing the data, handling an error, or otherwise.

### Describe the workflow of Protocol Buffers?

1. **Definition**: First, you define the structure of the data you want to serialize in a `.proto` file. This includes specifying the data types, fields, and other structures like enums and messages.
2. **Compilation**: Using the Protocol Buffers compiler (`protoc`), you compile the `.proto` file. The compiler generates source code in your chosen programming language that includes methods for encoding (serializing) your structured data to a binary format and decoding (deserializing) it back.
3. **Integration**: You integrate the generated source code into your application. This allows your application to easily serialize data to Protocol Buffers format to save to disk, send over a network, etc., and deserialize data from this format when received.
4. **Operation**: When your application needs to transmit data, it uses the generated methods to serialize the structured data into the compact, binary format. The receiving application then uses the generated methods to deserialize this data back into a usable structure.

### What are the benefits of using protocol buffers?

- **Efficiency**: Protocol Buffers are designed to serialize data in a smaller, faster, and simpler format than traditional XML or JSON, which makes them highly efficient both in terms of bandwidth usage and speed of encoding/decoding.
- **Interoperability**: Given their language-neutral and platform-neutral nature, Protocol Buffers enable easy, reliable, and consistent data exchange across different languages and platforms.
- **Scalability**: Protocol Buffers are designed to support backward and forward compatibility; fields can be added or removed in the data structure without breaking deployed programs that are compiled against the "old" format.
- **Automatic Code Generation**: The `protoc` compiler automatically generates source code from `.proto` files for the programmer's chosen language, significantly reducing the boilerplate code needed to serialize and deserialize structured data.

### When is the use of protocol not recommended?

- **Human Readability**: Because Protocol Buffers serialize data to a binary format, it's not human-readable like JSON or XML. This can make debugging, logging, or direct manual interaction more challenging.
- **Dynamic Data Structures**: If your data model requires frequent changes or consists of highly dynamic, schema-less data structures, Protocol Buffers may not be as flexible as document-oriented formats like JSON.
- **Small Projects or Simple Payloads**: For small-scale projects or when the data exchange involves simple payloads that don't benefit from the efficiency and structured approach of Protocol Buffers, the overhead of defining schemas and compiling them might not be justified.

### List 3 different data types that can be used with protocol buffers?

1. **Basic Types**: Includes `int32`, `int64`, `uint32`, `uint64`, `double`, `float`, `bool`, `string`, and `bytes`. These types represent the basic data types for integers, floating-point numbers, boolean values, strings of text, and sequences of bytes.
2. **Enumerations (`enum`)**: Allows you to specify a set of named constants. This is useful for representing a value from a limited set of options.
3. **Messages**: Complex types that are composed of multiple fields. Messages can contain other messages, and thus, can be nested to represent complex data structures. They can also include repeated fields, which are akin to arrays or lists in other languages.
