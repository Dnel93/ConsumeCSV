import com.opencsv.CSVReader;
import okhttp3.*;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ConsumeCSV {
    public static void main(String[] args) throws IOException {
        consumeAPI("http://localhost:8021", "monitors", "");
    }

    public static void consumeAPI(String URL, String fileName, String filePath) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(URL)
                //.addHeader("","")
                .build();
        try (Response response = client.newCall(request).execute()) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd");
            FileOutputStream fos = new FileOutputStream(filePath + "/" + fileName + "_" + dtf.format(LocalDate.now()) + ".csv");
            fos.write(response.body().bytes());
            fos.close();
            System.out.println("File has been created at root path");
        } catch (IOException e) {
            System.out.println("Failed to download file ->" + e.getMessage());
        }
    }


}