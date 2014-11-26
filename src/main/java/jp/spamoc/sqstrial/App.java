package jp.spamoc.sqstrial;

import com.amazonaws.services.sqs.model.Message;


/**
 * Hello world!
 * Hello SQS!
 *
 */
public class App 
{
    public static void main( String[] args ){
        if(args.length == 0){
            System.out.println("usage: sqstrial [send {message}] or [receive]");
            return;
        }
        SQSManager manager = new SQSManager();
        switch(args[0]){
        case "send":
            System.out.println("send message mode");
            if(args.length < 2){
                System.out.println("empty message");
            }
            String message = args[1];
            manager.sendMessage(Queue.DEFAULT.getPath(), message);
            break;
        case "receive":
            Message response = manager.receiveMessage(Queue.DEFAULT.getPath());
            System.out.println(response.getBody());
            manager.deleteMessage(Queue.DEFAULT.getPath(), response);
            break;
        default:
            throw new RuntimeException("bad parameter");
        }
        
        System.out.println( "Hello World!" );
    }
}
