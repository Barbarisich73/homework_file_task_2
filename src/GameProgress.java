import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class GameProgress implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private static List<String> namesOfSavedFiles = new ArrayList<>();

    private int heals;
    private int weapons;
    private int lvl;
    private double distance;

    public GameProgress(int heals, int weapons, int lvl, double distance) {
        this.heals = heals;
        this.weapons = weapons;
        this.lvl = lvl;
        this.distance = distance;
    }

    public static void saveGame(String way, GameProgress save) {
        try (FileOutputStream fos = new FileOutputStream(way);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(save);
            namesOfSavedFiles.add(way);
            System.out.println("Игра сохранена: " + "\"" + way + "\"");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void zipFiles(String way, List<String> names) {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(way))) {
            for (String name : names) {
                try (FileInputStream fis = new FileInputStream(name)) {
                    ZipEntry entry = new ZipEntry(name);
                    zout.putNextEntry(entry);
                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);
                    zout.write(buffer);
                    zout.closeEntry();
                }
            }
            System.out.println("Создан архив: " + "\"" + way + "\"");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void deleteFile(List<String> names) {
        for (String name : names) {
            File file = new File(name);
            if (file.delete()) {
                System.out.println("Удален файл " + "\"" + name + "\"");
            } else {
                System.out.println("Файл " + "\"" + name + "\"" + " не может быть удален");
            }
        }
    }

    public static void main(String[] args) {
        GameProgress save1 = new GameProgress(5, 32, 4, 56.6);
        GameProgress save2 = new GameProgress(4, 25, 8, 156.6);
        GameProgress save3 = new GameProgress(12, 20, 2, 256.6);

        saveGame("Games\\savegames\\save1.dat", save1);
        saveGame("Games\\savegames\\save2.dat", save2);
        saveGame("Games\\savegames\\save3.dat", save3);

        zipFiles("Games\\savegames\\save.zip", namesOfSavedFiles);

        deleteFile(namesOfSavedFiles);
    }
}