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
