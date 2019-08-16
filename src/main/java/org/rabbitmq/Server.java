package org.rabbitmq;

/**
 * @Author: Hao Qin
 * @Date: 19-8-16  下午3:18
 * @Version 1.0
 */
import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.QueueingConsumer.Delivery;
import net.sf.json.JSONObject;

public class Server
{
    private Connection connection;
    private Channel channel;
    private QueueingConsumer consumer;

    public Server Server(){
        return this;
    }

    public Server init()
            throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
//    factory.setUsername("rpc_user");
//    factory.setPassword("rpcme");
        factory.setHost("localhost");
        connection = factory.newConnection();
        channel = connection.createChannel();

        channel.exchangeDeclare("rpc", "direct");
        channel.queueDeclare("ping", false, false, false, null);
        channel.queueBind("ping", "rpc", "ping");

        consumer = new QueueingConsumer(channel);
        channel.basicConsume("ping", false, "ping", consumer);

        System.out.println(
                "Waiting for RPC calls..."
        );

        return this;
    }

    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            }
            catch (Exception ignore) {}
        }
    }

    public void serveRequests() {
        while (true) {
            try {

                Delivery delivery = consumer.nextDelivery();
                BasicProperties props = delivery.getProperties();

                channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
                System.out.println(
                        "Received API call...replying..."
                );

                channel.basicPublish(
                        "",
                        props.getReplyTo(),
                        null,
                        getResponse(delivery).getBytes("UTF-8")
                );

            } catch (Exception e){
                System.out.println(e.toString());
            }
        }
    }

    private String getResponse(Delivery delivery) {
        String response = null;
        try {
            String message = new String(delivery.getBody(), "UTF-8");
            JSONObject jsonobject = JSONObject.fromObject(message);
            response = "Pong!" + jsonobject.getString("time");
        }
        catch (Exception e){
            System.out.println(e.toString());
            response = "";
        }
        return response;
    }

    public static void main(String[] args) {
        Server server = null;
        try {
            server = new Server();
            server.init().serveRequests();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            if(server != null) {
                server.closeConnection();
            }
        }
    }
}