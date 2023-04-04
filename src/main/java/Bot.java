import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.CopyMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class Bot extends TelegramLongPollingBot {

    private boolean isCalculate = false;

    private boolean isCalculateFirst = false;
    private boolean isCalculateSecond = false;
    private boolean isCalculateThird = false;
    private boolean isCalculateFour = false;
    private boolean isCalculateFive = false;
    private boolean isCalculateSix = false;
    private boolean isCalculateSeven = false;


    @Override
    public String getBotUsername() {
        return "fikus_710_bot";
    }

    @Override
    public String getBotToken() {
        return "6187338436:AAGLeT0LnEoHjRLNm9mwp6C5paOuHL077jc";
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            var msg = update.getMessage();
            var user = msg.getFrom();
            var id = msg.getChatId();
            if (msg.isCommand()) {
                if(msg.getText().equals("/start")) sendText(id, "Привет " + user.getUserName());
                if (msg.getText().equals("/calculate")) {
                    isCalculate = true;
                    ask(id);
                }
            } else {
                if (isCalculate) {
                    if (isCalculateFirst) {
                        sendText(id, String.valueOf(func_0(msg.getText())));
                        isCalculateFirst = false;
                    } else if (isCalculateSecond) {
                        sendText(id, String.valueOf(func_1(msg.getText())));
                        isCalculateSecond = false;
                    } else if (isCalculateThird) {
                        sendText(id, String.valueOf(func_3(msg.getText())));
                        isCalculateThird = false;
                    } else if (isCalculateFour) {
                        sendText(id, String.valueOf(func_4(msg.getText())));
                        isCalculateFour = false;
                    } else if (isCalculateFive) {
                        sendText(id, String.valueOf(func_5(msg.getText())));
                        isCalculateFive = false;
                    } else if (isCalculateSix) {
                        sendText(id, String.valueOf(func_6(msg.getText())));
                        isCalculateSix = false;
                    } else if (isCalculateSeven) {
                        sendText(id, String.valueOf(func_7(msg.getText())));
                        isCalculateSeven = false;
                    }
                    isCalculate = false;
                }
            }
        }else {
            if (update.hasCallbackQuery()) {

                String callbackData = update.getCallbackQuery().getData();
                long messageId = update.getCallbackQuery().getMessage().getMessageId();
                long chatId = update.getCallbackQuery().getMessage().getChatId();

                EditMessageText message = new EditMessageText();
                message.setChatId(String.valueOf(chatId));
                message.setMessageId((int) messageId);

                if (callbackData.equals("1_BUTTON")) {
                    isCalculateFirst = true;
                    message.setText("Введите n, x, a, b, c через пробел:");
                }
                if (callbackData.equals("2_BUTTON")) {
                    isCalculateSecond = true;
                    message.setText("Введите a,x,y,w через пробел:");
                }
                if (callbackData.equals("3_BUTTON")) {
                    isCalculateThird = true;
                    message.setText("Введите a0, a1, a2, x");
                }
                if (callbackData.equals("4_BUTTON")) {
                    isCalculateFour = true;
                    message.setText("Введите а, x");
                }
                if (callbackData.equals("5_BUTTON")) {
                    isCalculateFive = true;
                    message.setText("Введите a,b,c,d,x через пробел");
                }
                if (callbackData.equals("6_BUTTON")) {
                    isCalculateSix = true;
                    message.setText("Введите x");
                }
                if (callbackData.equals("7_BUTTON")) {
                    isCalculateSeven = true;
                    message.setText("Введите х");
                }
                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }


//    public void copyMessage(Long who, Integer msgId) {
//        CopyMessage cm = CopyMessage.builder()
//                .fromChatId(who.toString())  //We copy from the user
//                .chatId(who.toString())      //And send it back to him
//                .messageId(msgId)            //Specifying what message
//                .build();
//        try {
//            execute(cm);
//        } catch (TelegramApiException e) {
//            throw new RuntimeException(e);
//        }
//    }

//    private void sendMessageWithMarkup(long chatId, String textToSend, ReplyKeyboardMarkup replyKeyboardMarkup) throws TelegramApiException {
//        SendMessage message = new SendMessage();
//        message.setChatId(String.valueOf(chatId));
//        message.setText(textToSend);
//        if (calculate) {
//            // разметка для клавиатуры
//            message.setReplyMarkup(replyKeyboardMarkup);
//            calculate = false;
//        }
//        execute(message);
//    }

//    private void sendMessage(long chatId, String textToSend) throws TelegramApiException {
//        SendMessage message = new SendMessage();
//        message.setChatId(String.valueOf(chatId));
//        message.setText(textToSend);
//
//        execute(message);
//    }

//    public ReplyKeyboardMarkup getKeyboardMarkup(){
//        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
//        // список рядов
//        List<KeyboardRow> keyboardRows = new ArrayList<>();
//        // конкретный ряд
//        KeyboardRow row = new KeyboardRow();
//        row.add("func0");
//        row.add("func1");
//        keyboardRows.add(row);
//        KeyboardRow row2 = new KeyboardRow();
//        row2.add("func2");
//        row2.add("func3");
//        keyboardRows.add(row2);
//        KeyboardRow row3 = new KeyboardRow();
//        row3.add("func4");
//        row3.add("func5");
//        keyboardRows.add(row3);
//        KeyboardRow row4 = new KeyboardRow();
//        row4.add("func6");
//        keyboardRows.add(row4);
//        keyboardMarkup.setKeyboard(keyboardRows);
//       // keyboardMarkup.setOneTimeKeyboard(true);
//        return keyboardMarkup;
//    }


    public void sendText(Long who, String what) {
        SendMessage sm = SendMessage.builder()
                .chatId(who.toString()) //Who are we sending a message to
                .text(what).build();    //Message content
        try {
            execute(sm);                        //Actually sending the message
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);      //Any error will be printed here
        }
    }

    private void ask(long chatId) {
        System.out.println("метод");
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText("Выберите номер функции, значение которой вы хотите вычислить:");

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        List<InlineKeyboardButton> rowInLine = new ArrayList<>();

        var button_1 = new InlineKeyboardButton();
        button_1.setText("1");
        button_1.setCallbackData("1_BUTTON");

        var button_2 = new InlineKeyboardButton();
        button_2.setText("2");
        button_2.setCallbackData("2_BUTTON");

        var button_3 = new InlineKeyboardButton();
        button_3.setText("3");
        button_3.setCallbackData("3_BUTTON");

        var button_4 = new InlineKeyboardButton();
        button_4.setText("4");
        button_4.setCallbackData("4_BUTTON");

        rowInLine.add(button_1);
        rowInLine.add(button_2);
        rowInLine.add(button_3);
        rowInLine.add(button_4);

        List<InlineKeyboardButton> rowInLine1 = new ArrayList<>();

        var button_5 = new InlineKeyboardButton();
        button_5.setText("5");
        button_5.setCallbackData("5_BUTTON");

        var button_6 = new InlineKeyboardButton();
        button_6.setText("6");
        button_6.setCallbackData("6_BUTTON");

        var button_7 = new InlineKeyboardButton();
        button_7.setText("7");
        button_7.setCallbackData("7_BUTTON");

        rowInLine1.add(button_5);
        rowInLine1.add(button_6);
        rowInLine1.add(button_7);

        rowsInLine.add(rowInLine);
        rowsInLine.add(rowInLine1);

        markupInline.setKeyboard(rowsInLine);
        message.setReplyMarkup(markupInline);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private double func_0(String s) {
        String[] mas = s.split(" ");
        try {
            double n = Double.parseDouble(mas[0]);
            double x = Double.parseDouble(mas[1]);
            double a = Double.parseDouble(mas[2]);
            double b = Double.parseDouble(mas[3]);
            double c = Double.parseDouble(mas[4]);
            return (5 * Math.pow(a, (n * x))) / (b + c) - Math.sqrt(Math.abs(Math.cos(x * x * x)));
        } catch (ClassCastException e) {

        }
        return 0.0;
    }
    private double func_1(String s){
        String[] mas = s.split(" ");
        try {
            double a = Double.parseDouble(mas[0]);
            double x = Double.parseDouble(mas[1]);
            double y = Double.parseDouble(mas[2]);
            double w = Double.parseDouble(mas[3]);
            return Math.abs(x-y)/Math.pow((1+2*x),a)-Math.pow(2.7,Math.sqrt(1+w));
        } catch (ClassCastException e) {

        }
        return 0.0;
    }
    private double func_3(String s){
        String[] mas = s.split(" ");
        try {
            double a0 = Double.parseDouble(mas[0]);
            double a1 = Double.parseDouble(mas[1]);
            double a2 = Double.parseDouble(mas[2]);
            double x = Double.parseDouble(mas[3]);
            return Math.sqrt(a0+a1*x+a2*Math.pow(Math.abs(Math.sin(x)),(double)1/3));
        } catch (ClassCastException e) {

        }
        return 0.0;
    }
    private double func_4(String s){
        String[] mas = s.split(" ");
        try {
            double a = Double.parseDouble(mas[0]);
            double x = Double.parseDouble(mas[1]);
            return Math.log(Math.abs(Math.pow(a,7)))+Math.atan(x*x)+Math.PI/(Math.sqrt(Math.sqrt(Math.abs(a+x))));
        } catch (ClassCastException e) {

        }
        return 0.0;
    }
    private double func_5(String s){
        String[] mas = s.split(" ");
        try {
            double a = Double.parseDouble(mas[0]);
            double b = Double.parseDouble(mas[1]);
            double c = Double.parseDouble(mas[2]);
            double d = Double.parseDouble(mas[3]);
            double x = Double.parseDouble(mas[4]);
            double result = ((a+b)*(a+b))/(c+d)+Math.pow(2.7,Math.sqrt(x+1));
            return Math.pow(result, 0.2);
        } catch (ClassCastException e) {

        }
        return 0.0;
    }
    private double func_6(String s){
        String[] mas = s.split(" ");
        try {
            double x = Double.parseDouble(mas[0]);
            return Math.pow(Math.E,((2*Math.sin(4*x)+Math.cos(x*x)*Math.cos(x*x))/3*x));
        } catch (ClassCastException e) {

        }
        return 0.0;
    }
    private double func_7(String s){
        String[] mas = s.split(" ");
        try {
            double x = Double.parseDouble(mas[0]);
            return 0.25*((1+x*x)/(1-x)+0.5*Math.tan(x));
        } catch (ClassCastException e) {

        }
        return 0.0;
    }


}

