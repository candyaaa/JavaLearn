package com.stone98.example.grpc.server;

import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import proto.Definition;
import proto.UserServiceGrpc;

import java.io.IOException;

public class Server {
    public static void main(String[] args) throws IOException, InterruptedException {
        io.grpc.Server server = ServerBuilder
                .forPort(5500)
                .addService(new Server.UserServiceImpl())
                .build()
                .start();
        server.awaitTermination();
        // 优雅的关闭GRPC服务器
        Runtime.getRuntime().addShutdownHook( new Thread( () -> server.shutdown() ) );
    }
    
    private static class UserServiceImpl extends UserServiceGrpc.UserServiceImplBase{
        
        @Override
        public void getUserById(Definition.Request request, StreamObserver<Definition.User> responseObserver) {
            Definition.User user = Definition.User.newBuilder().setId(1).setAge(18).build();
            responseObserver.onNext(user);
            responseObserver.onCompleted();
        }
        
    }

}
