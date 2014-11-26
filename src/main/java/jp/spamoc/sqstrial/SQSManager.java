package jp.spamoc.sqstrial;

import java.util.List;

import com.amazonaws.services.sqs.model.DeleteMessageRequest;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;
import com.amazonaws.services.sqs.model.SendMessageRequest;


public class SQSManager extends AbstractSQSManager{
    
    public SQSManager(){
        
    }

    
    public void sendMessage(String queue, String message){
        String queueUrl = createQueueUrl(queue);
        SendMessageRequest sendMsgReq = new SendMessageRequest(queueUrl, message);
        client.sendMessage(sendMsgReq);
    }
    
    public List<Message> receiveMessages(String queue, int maxNumberOfMessages) {
        String queueUrl = createQueueUrl(queue);
        ReceiveMessageRequest receiveMsgReq = new ReceiveMessageRequest(queueUrl);
        receiveMsgReq.setMaxNumberOfMessages(maxNumberOfMessages);
        ReceiveMessageResult result = client.receiveMessage(receiveMsgReq);
        if(result.getMessages().size() == 0){
            return null;
        }

        return result.getMessages();
    }
    
    public Message receiveMessage(String queue){
        String queueUrl = createQueueUrl(queue);
        ReceiveMessageResult result = client.receiveMessage(queueUrl);
        if(result.getMessages().size() == 0){
            return null;
        }
        return result.getMessages().get(0);
    }
    
    public void deleteMessage(String queue, Message message){
        String queueUrl = createQueueUrl(queue);
        DeleteMessageRequest delMsgRequest = new DeleteMessageRequest(queueUrl, message.getReceiptHandle());
        client.deleteMessage(delMsgRequest);
    }
    
    private String createQueueUrl(String queue){
        return url + "/" + queue;
    }

}
