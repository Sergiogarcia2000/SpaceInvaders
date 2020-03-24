package game.settings;

import game.sounds.PlaySound;
import game.sounds.Sounds;

import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Life {

    private static String documentsDirectory = FileSystemView.getFileSystemView().getDefaultDirectory().getPath();
    private static String scoreFile = "life.dat";
    private static String dataPath = documentsDirectory + "/" + scoreFile;

    public static int getLife(){

        try {
            File data = new File(dataPath);

            boolean exist = data.createNewFile();

            if (exist){
                BufferedWriter bw = new BufferedWriter(new FileWriter(dataPath, true));
                bw.append("3");
                bw.append("\n");
                bw.append("3");
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

    public static void setLife(int num){

        PlaySound.play(Sounds.COLLISION.getPath());

        try {
            List<String> fileContent = new ArrayList<>(Files.readAllLines(Paths.get(dataPath), StandardCharsets.UTF_8));

            int life = Integer.parseInt(fileContent.get(0)) + num;

            fileContent.set(0, Integer.toString(life));

            Files.write(Paths.get(dataPath),fileContent, StandardCharsets.UTF_8);

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static int getBaseLife(){

        try {
            File data = new File(dataPath);

            boolean exist = data.createNewFile();

            if (exist){
                BufferedWriter bw = new BufferedWriter(new FileWriter(dataPath, true));
                bw.append("3");
                bw.append("\n");
                bw.append("3");
                bw.close();
            }

            BufferedReader br = new BufferedReader(new FileReader(dataPath));
            String line = br.readLine();
            line = br.readLine();
            return Integer.parseInt(line);

        } catch(Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static void setBaseLife(int num){

        PlaySound.play(Sounds.COLLISION.getPath());

        try {
            List<String> fileContent = new ArrayList<>(Files.readAllLines(Paths.get(dataPath), StandardCharsets.UTF_8));

            int life = Integer.parseInt(fileContent.get(1)) + num;

            fileContent.set(1, Integer.toString(life));

            Files.write(Paths.get(dataPath),fileContent, StandardCharsets.UTF_8);

        }catch (IOException e){
            e.printStackTrace();
        }
    }


    public static void restartLife(){

        try {
            List<String> fileContent = new ArrayList<>(Files.readAllLines(Paths.get(dataPath), StandardCharsets.UTF_8));

            int sc = Integer.parseInt(fileContent.get(1));

            fileContent.set(0, Integer.toString(sc));

            Files.write(Paths.get(dataPath),fileContent, StandardCharsets.UTF_8);

        }catch (IOException e){
            System.out.println("Restarting life");
        }
    }
}
