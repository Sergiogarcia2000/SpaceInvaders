package game.settings;

import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Score {

    private static String documentsDirectory = FileSystemView.getFileSystemView().getDefaultDirectory().getPath();
    private static String scoreFile = "score.dat";
    private static String dataPath = documentsDirectory + "/" + scoreFile;


    public static int getScore(){

        try {
           File data = new File(dataPath);

           boolean exist = data.createNewFile();

            if (exist){
                BufferedWriter bw = new BufferedWriter(new FileWriter(dataPath, true));
                bw.append("0");
                bw.close();
            }

            BufferedReader br = new BufferedReader(new FileReader(dataPath));
            String line = br.readLine();

            return Integer.parseInt(line);

        } catch(Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static void setScore(int num){

        try {
            List<String> fileContent = new ArrayList<>(Files.readAllLines(Paths.get(dataPath), StandardCharsets.UTF_8));

            int sc = Integer.parseInt(fileContent.get(0)) + num;

            fileContent.set(0, Integer.toString(sc));

            Files.write(Paths.get(dataPath),fileContent, StandardCharsets.UTF_8);

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
