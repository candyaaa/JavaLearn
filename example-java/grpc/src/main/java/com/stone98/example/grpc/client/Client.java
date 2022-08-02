package com.stone98.example.grpc.client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import proto.Definition;
import proto.UserServiceGrpc;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Client {
    
    public static void main( String[] args ) throws InterruptedException, TimeoutException, ExecutionException {
        ManagedChannel channel = ManagedChannelBuilder.forAddress( "localhost", 5500 ).usePlaintext(  ).build();
        // 阻塞的客户端存根
        UserServiceGrpc.UserServiceBlockingStub stub = UserServiceGrpc.newBlockingStub( channel );
        // 非阻塞的客户端存根
        UserServiceGrpc.UserServiceFutureStub asyncStub = UserServiceGrpc.newFutureStub( channel );
    
        // 构建请求消息
        Definition.Request request = Definition.Request.newBuilder().setId(1).build();
        // 发起同步请求
        Definition.User user1 = stub.getUserById( request );
        // 发起异步请求
        Definition.User user2 = asyncStub.getUserById(request).get(1, TimeUnit.SECONDS);
        System.out.println(user1.toString());
        System.out.println(user2.toString());
        // 关闭通道
        channel.shutdown().awaitTermination( 5, TimeUnit.SECONDS );
    }
}
