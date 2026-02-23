import com.google.gson.Gson;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Utilities {
    private static Gson gson = new Gson();
    private static File path = new File("asset/saves");
    public static List<String> getSaveList() {
        List<String> listItem = new ArrayList<>();
        String[] list;
        if (path.exists()) {
//            try (FileReader reader = new FileReader()) {
//
//            } catch () {
//
//            }

            list = path.list();
            for (String i : list) {
                listItem.add(i);
            }
        }

        return listItem;
    }

    public static Datawrapper Load(String file) {
        File fullpath = new File(path, file);
        Datawrapper n = null;
        if (fullpath.exists()) {
            System.out.println("Found");
            try (FileReader fileReader = new FileReader(fullpath)) {
                System.out.println("Success Load : " + file);
                n = gson.fromJson(fileReader, Datawrapper.class);
            } catch (Exception e) {
                System.out.println("Failed to Load : " + file);
                return null;
            }
        }
        if (n != null) {
            return n;
        }
        return null;
    }

    public static void save(Datawrapper data) {
        int savenum = getSaveList().size();

        String savename = ("save_"+savenum+".json");
        File fullpath = new File(path, savename);

        try (FileWriter writer = new FileWriter(fullpath)) {
            gson.toJson(data , writer);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
