# Introduction to Sockets in Java

In Java, sockets provide a mechanism for communication between two applications over a network. Java's `java.net` package includes classes that facilitate the creation, connection, and communication through sockets.

## Socket Basics

Java supports both TCP and UDP sockets. Here's a brief overview:

### TCP Sockets

- **ServerSocket Class:**
  - Represents a server-side socket that listens for incoming connections.
  - Created using `ServerSocket` class.

- **Socket Class:**
  - Represents a client-side socket that connects to a server.
  - Created using the `Socket` class.

### UDP Sockets

- **DatagramSocket Class:**
  - Used for connectionless communication.
  - Datagram packets can be sent and received using `DatagramSocket` class.
