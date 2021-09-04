import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Bot extends TelegramLongPollingBot {

    @Override
    public String getBotUsername() {
        return "commies_bot";
    }

    @Override
    public String getBotToken() {
        return "REDACTED";
    }

    @Override
    public void onUpdateReceived(Update update) {

        Message message = update.getMessage();

        // response to commands only
        if (message.hasText()) {

            System.out.println(message);

            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(update.getMessage().getChatId().toString());

            // TODO: calls
            message.setText("Hello");


            switch (message.getText()) {

                // add a new user to prezenta (may be multiple)
                case "/prezenta_addUsernames" :

                    try {
                        String[] users = message.getText().split(" ");

                        BufferedWriter writer = new BufferedWriter(new FileWriter("resourses" + File.separator + "user.info"));

                        for (String user: users) {

                            writer.append(user).append(" ");
                            writer.flush();
                        }

                        writer.close();

                    } catch (IOException ex){

                        ex.printStackTrace();
                    }

            }

            try{
                execute(sendMessage);

            } catch (TelegramApiException ex) {

                ex.printStackTrace();
            }
        }
    }
}
