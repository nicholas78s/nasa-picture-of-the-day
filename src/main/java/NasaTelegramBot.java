import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class NasaTelegramBot extends TelegramLongPollingBot {
    private final String BOT_NAME;
    private final String BOT_TOKEN;
    private final String URL_NASA = "https://api.nasa.gov/planetary/apod" +
            "?api_key=DEMO_KEY";

    public NasaTelegramBot(String name, String token) throws TelegramApiException {
        this.BOT_NAME = name;
        this.BOT_TOKEN = token;

        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        botsApi.registerBot(this);
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            long chatId = update.getMessage().getChatId();
            String answer = update.getMessage().getText();
            String[] separatedAnswer = answer.split(" ");
            String action = "";
            if (separatedAnswer.length > 0)
                action = separatedAnswer[0];

            switch (action) {
                case "/start":
                case "/начало":
                    sendMessage("Я бот НАСА, присылаю картинку дня", chatId);
                    break;
                case "/help":
                case "/помощь":
                    sendMessage("Напишите /image для получения картинки дня", chatId);
                    break;
                case "/image":
                case "/фото":
                case "/картинка":
                    sendMessage(Utils.getURL(URL_NASA), chatId);
                    break;
                case "/date":
                case "/дата":
                    String date = (separatedAnswer.length > 1) ? separatedAnswer[1] : "";
                    sendMessage(Utils.getURL(URL_NASA + "&date=" + date), chatId);
                    break;
                default:
                    sendMessage("Я тебя не понимаю", chatId);
            }
        }
    }

    void sendMessage(String text, long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId); //message.setChatId(update.getMessage().getChatId().toString());
        message.setText(text); //message.setText(update.getMessage().getText() + " a");

        try {
            execute(message);
        } catch (TelegramApiException e) {
            //e.printStackTrace();
            System.out.println("Сообщение не отправлено");
        }
    }
    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }
}
