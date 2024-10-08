import com.google.gson.JsonObject; // For Gson
// or
import org.json.JSONObject; // For org.json library
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        String url = "https://accounts.adp.com/core/v1/event-notification-messages";

        storeEventsInDB(url, auth);

    }

    private void storeEventsInDB(String url, JSONObject auth) {
        while(true) {
            JSONObject result = null;

            result = getEventNotificationMessage(url, auth);

            if (result.isEmpty() || result==null) {
                break;
            }

            storeDataInDB(result);
            eventResponse.put(result);
            String deleteUrl = url+"/"+authenticate.getHeaderValue();
            deleteNotificationMessage(deleteUrl, auth);
            writeJSONArrayToFile(eventResponse, "output.json");

        }

    }
}