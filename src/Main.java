import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class Main {

    static StringBuilder log = new StringBuilder();

    static void greatDirectory(String way, String nameOfDirectory) {
        File dir = new File(way + "/" + nameOfDirectory);
        if (dir.mkdir()) {
            log.append(new Date() + ": " + " Создан каталог: " + '\"' + way + "/" + nameOfDirectory + "/" + '\"' + '\n');
        } else {
            log.append(new Date() + ": " + " Каталог: " + "\'" + nameOfDirectory + "/" + '\"' +
                    " не создан. Возможно он уже сущетвует" + '\n');
        }
    }

    static void greatFile(String way, String nameOfFile) {
        File myFile = new File(way + "//" + nameOfFile);
        try {
            if (myFile.createNewFile()) {
                log.append(new Date() + ": " + " Создан файл: " + '\"' + way + "/" + nameOfFile + '\"' + '\n');
            } else {
                log.append(new Date() + ": " + " Файл: " + "\'" + "/" + nameOfFile + '\"' +
                        " не создан. Возможно он уже сущетвует" + '\n');
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        greatDirectory("Games", "src");
        greatDirectory("Games", "res");
        greatDirectory("Games", "savegames");
        greatDirectory("Games", "temp");
        greatDirectory("Games/src", "main");
        greatDirectory("Games/src", "test");
        greatFile("Games/src/main", "Main.java");
        greatFile("Games/src/main", "Utils.java");
        greatDirectory("Games/res", "drawables");
        greatDirectory("Games/res", "vectors");
        greatDirectory("Games/res", "icons");
        greatFile("Games/temp", "temp.txt");

        try (FileWriter writer = new FileWriter("Games/temp/temp.txt", false)) {
            writer.write(log.toString());
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}