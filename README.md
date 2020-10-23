# Socket-Programming
This repository contains solutions to problem statements involving socket programming.

[![ForTheBadge built-with-love](http://ForTheBadge.com/images/badges/built-with-love.svg)](https://GitHub.com/thisisnsh/)

## Problem Statement 1
Implement a distributed file server using sockets on Linux. The server creates a thread per client. In the first message, a client sends a file name to the server and asks for the length of the file. A file is transferred to the client block by block.

#### Run Server 
```sh
$ java FileServer.java
```

#### Run Client
```sh
$ java FileServer.java
FILENAME.EXTENSION
```

#### Explanation
* File Server listens on **port number 1102** and creates a new thread for each new client. 
* The client enters the file name which is transferred to the server using data streams. The server returns the size of the file in bytes if it exists. If it doesn’t exist it returns 0. 
* The file is transferred in blocks of **4096 bytes**. The log for each block transfer is displayed in the end. 
* The file received at the client is saved as **"received-FILENAME.EXTENSION"** where **FILENAME.EXTENSION** is the file sent by the server. 

## Problem Statement 2
Implement concurrent echo client-server application. Write a Server (TCP) Program that opens a listening socket and waits to serve client. Write a Client (TCP) Program that connects with the server program knowing IP address and port number. Get the input string from console on client and send it to server, server echoes back that string to client.

#### Run Server 
```sh
$ java EchoServer.java
```

#### Run Client
```sh
$ java EchoServer.java
TYPE MESSAGE
```

#### Explanation
* The server listens on **port number 1102** and sends back the message received from the client through the data stream. 
* Client types the message and sends it to the server through the data stream. The server reads the message and sends it back to the client using the same stream. 
* **“end”** message sent by the client ends the conversation. 
