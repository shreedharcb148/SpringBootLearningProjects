//Copyright (C) Robert Bosch GmbH 2022. All rights reserved.
package com.pav2py.excel.utlilty;

import java.awt.HeadlessException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
//import javafx.fxml.FXMLLoader;
//import javafx.fxml.Initializable;
//import javafx.geometry.Rectangle2D;
//import javafx.scene.control.TextField;
//import javafx.scene.layout.AnchorPane;
//import javafx.stage.DirectoryChooser;
//import javafx.stage.FileChooser;
//import javafx.stage.Screen;
//import javafx.stage.Stage;
//import javafx.stage.Window;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author AAK3COB
 */
public class BasicUtilities {

    public static boolean isNotBlank(String data) {
        return data != null && !data.isEmpty();
    }

//    public static void chooseDirectory(String dialogTitle, TextField textField) {
//        if (null != textField) {
//            DirectoryChooser directoryChooser = new DirectoryChooser();
//            if (textField.getText() != null && new File(textField.getText()).exists()) {
//                directoryChooser.setInitialDirectory(new File(textField.getText()));
//            }
//            directoryChooser.setTitle(dialogTitle);
//            File file = directoryChooser.showDialog(textField.getScene().getWindow());
//            if (file != null) {
//                textField.setText(file.getAbsolutePath());
//            }
//        }
//    }
//
//    public static <T> Initializable getFxmlController(URL location) throws IOException {
//        FXMLLoader loader = new FXMLLoader(location);
//        loader.load();
//        return loader.getController();
//    }
//
//    public static <T> Initializable getFxmlController(Class<T> fxmlControllerClass, String fxmlFileName) throws IOException {
//        URL location = fxmlControllerClass.getResource(fxmlFileName);
//        FXMLLoader loader = new FXMLLoader(location);
//        loader.load();
//
//        return loader.getController();
//    }

    public static boolean isDirectoryExists(String path) {
        File file = new File(path);
        return (file.isDirectory());
    }

    public static boolean doesFileExist(String path) {
        File file = new File(path);
        return (file.isFile());
    }

    public static boolean isValidInteger(String value) {
        return value.matches("-?\\d+");
    }

    public static boolean isValidDoube(String value) {
        try {
            Double.parseDouble(value);
        } catch (NumberFormatException numberFormatException) {
            return false;
        }
        return true;
    }

    public static File createFolderWithTimeStamp(String folderName, String folderPath) throws IOException {
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss");
        String timeStamp = dateFormat.format(now);
        File newDir = new File(folderPath + "//" + folderName + timeStamp);
        Files.createDirectory(newDir.toPath());
        return newDir;
    }

    public static File createLogWithTimeStamp(String outputFolder, String contents) throws IOException {
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss");
        String timeStamp = dateFormat.format(now);
        File logFile = new File(outputFolder + "//RMI_Log_" + timeStamp + ".log");
        try (FileWriter fileWriter = new FileWriter(logFile)) {
            fileWriter.write(contents);
        }
        return logFile;
    }

//    public static File chooseFile(String dialogTitle, TextField textField, String extension, String extensionInfo) {
//        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter(extensionInfo, extension);
//        FileChooser fileChooser = new FileChooser();
//
//        if (textField.getText() != null && new File(textField.getText()).exists()) {
//            fileChooser.setInitialDirectory(new File(textField.getText()).getParentFile());
//        }
//
//        fileChooser.setTitle(dialogTitle);
//        fileChooser.getExtensionFilters().add(extensionFilter);
//        File openedFile = fileChooser.showOpenDialog(textField.getScene().getWindow());
//
//        if (openedFile != null) {
//            textField.setText(openedFile.getAbsolutePath());
//        }
//        return openedFile;
//    }

//    public static File chooseFile(String dialogTitle, TextField textField, String[] extension, String[] extensionInfo) {
//        FileChooser fileChooser = new FileChooser();
//        if (textField.getText() != null && new File(textField.getText()).exists()) {
//            fileChooser.setInitialDirectory(new File(textField.getText()));
//        }
//        fileChooser.setTitle(dialogTitle);
//
//        for (int index = 0; index < extension.length; index++) {
//            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(extensionInfo[index], extension[index]));
//        }
//
//        File openedFile = fileChooser.showOpenDialog(textField.getScene().getWindow());
//        
//
//
//        if (openedFile != null) {
//            textField.setText(openedFile.getAbsolutePath());
//        }
//        return openedFile;
//    }
//
//    public static void setStageOnCenter(final Stage dialogStage, final AnchorPane mainAnchorPane) {
//        //Update multiple screen support
//        try {
//            Point p = MouseInfo.getPointerInfo().getLocation();
//            Rectangle2D screenBounds;
//            List<Screen> screens = Screen.getScreens();
//            boolean sizeSet = false;
//            if (p != null && screens != null && screens.size() > 1) {
//                for (Screen screen : screens) {
//                    screenBounds = screen.getVisualBounds();
//                    // Trying to compute Left Top X-Y position for the Application Window
//                    if (screenBounds.contains(p.x, p.y)) {
//                        // Fixed Size Window Width and Height
//                        dialogStage.setX(screenBounds.getMinX() + ((screenBounds.getMaxX() - screenBounds.getMinX() - mainAnchorPane.getPrefWidth()) / 2));
//                        dialogStage.setY(screenBounds.getMinY() + ((screenBounds.getMaxY() - screenBounds.getMinY() - mainAnchorPane.getPrefHeight()) / 2));
//                        sizeSet = true;
//                        break;
//                    }
//                }
//            }
//            if (!sizeSet) {
//                dialogStage.centerOnScreen();
//            }
//        } catch (HeadlessException e) {
//            dialogStage.centerOnScreen();
//        }
//    }

    public static long toNumerical(String value) {
        if (value.toLowerCase().startsWith("0x")) {
            value = value.substring(2);
        }
        long num = Long.parseLong(value);
        return num;
    }

    public static long hexToNumerical(String value) {
        if (value.toLowerCase().startsWith("0x")) {
            value = value.substring(2);
        }
        long num = Long.parseLong(value, 16);
        return num;
    }

    public static boolean isHex(String parameter) {
        if (parameter.toLowerCase().startsWith("0x")) {
            parameter = parameter.replaceAll("0x", "").trim();
        }
        Pattern hexPattern = Pattern.compile("^[0-9A-F]+$");
        return hexPattern.matcher(parameter.toUpperCase()).matches();
    }

    //To format the bytes based on the configuration
    public static String formatRxBytes(String bytes, int byteSize) {
        String groupRegEx = "";
        for (int index = 0; index < byteSize; index++) {
            groupRegEx = groupRegEx + ".(?!$)";
        }
        bytes = bytes.replaceAll(groupRegEx, "$0 ").toUpperCase();
        return bytes;
    }

    public static List<String> splitStringToStringList(String parameterSet, String separator) {
        List<String> parameters = new ArrayList<>();
        if (!("".equals(parameterSet))) {
            parameters = Arrays.asList(parameterSet.split(separator + "\\s*", -1));
        }

        return parameters;
    }

    public static String mergeStringListToString(List<String> list, String separator) {
        String finalString = "";

        if (!list.isEmpty()) {
            int length = list.size() * separator.length();
            for (String string : list) {
                length += string.length();
            }

            StringBuilder stringBuilder = new StringBuilder(length);

            for (String string : list) {
                stringBuilder.append(separator).append(string);
            }

            finalString = stringBuilder.substring(separator.length());
        }

        return finalString;
    }

    /**
     * This method converts the passed in number in string format (HEX/BIN/DEC)
     * to bytes form. This takes care of the byte length i.e. appends required
     * number of zeroes as required for BIN and HEX values. e.g. 0x00001 =
     * 000001, 0b000000001 = 0001
     *
     * @param input Number in string format (HEX/BIN/DEC)
     * @return String - Converted bytes
     */
    public static String stringNumericalToBytesForm(String input) {
        String bytesOutput;
        long numericalValue;
        try {

            input = input.toUpperCase();
            input = input.replaceAll(" ", "");

            if (input.startsWith("0X")) {
                bytesOutput = "";
                //input = input.replace("0X", "");
                input = input.substring(2);
                if (isHex(input)) {
                    bytesOutput = input;
                }

            } else if (input.startsWith("0B")) {
                numericalValue = Long.parseLong(input.substring(2), 2);
                bytesOutput = Long.toHexString(numericalValue);

                //Change the length of byte as per the passed in binary value
                input = input.replace("0B", "");
                int nibbleLength = input.length();
                int noNibbles = bytesOutput.length();
                if (nibbleLength % 4 == 0) {
                    nibbleLength = nibbleLength / 4;
                } else {
                    nibbleLength = (nibbleLength / 4) + 1;
                }

                for (int i = noNibbles; i < nibbleLength; i++) {
                    bytesOutput = "0" + bytesOutput;
                }

            } else if ((!(input.startsWith("0X"))) && (input.contains("E"))) {
                numericalValue = Double.valueOf(input).longValue();
                bytesOutput = Long.toHexString(numericalValue);
            } else {
                numericalValue = Long.parseLong(input);
                bytesOutput = Long.toHexString(numericalValue);
            }

            if (bytesOutput.length() % 2 != 0) {
                bytesOutput = "0" + bytesOutput;
            }

        } catch (NumberFormatException | NullPointerException exception) {
            bytesOutput = "";
        }
        //bytesOutput = bytesOutput.replaceAll(".(?!$).(?!$)", "$0 ").toUpperCase();
        return bytesOutput;
    }

    //For supporting blockint values more than 8 bytes upto INTEGER.MAX number of bytes
    public static BigInteger toBigInteger(String parameter) {
        BigInteger numericalValue;
        parameter = parameter.replaceAll(" ", "");
        try {
            if (parameter.startsWith("0x") || parameter.startsWith("0X")) {
                numericalValue = new BigInteger(parameter.substring(2), 16);
            } else if (parameter.startsWith("0b") || parameter.startsWith("0B")) {
                numericalValue = new BigInteger(parameter.substring(2), 2);
            } else {
                numericalValue = new BigInteger(parameter);
            }
        } catch (NumberFormatException exception) {
            numericalValue = BigInteger.ZERO;
        }
        return numericalValue;
    }

    public static String stringBigIntegerToBytesForm(String input) {
        String bytesOutput;
        BigInteger numericalValue;
        try {

            input = input.toUpperCase();
            input = input.replaceAll(" ", "");

            if (input.startsWith("0X")) {
                bytesOutput = "";
                //input = input.replace("0X", "");
                input = input.substring(2);
                if (isHex(input)) {
                    bytesOutput = input;
                }

            } else if (input.startsWith("0B")) {
                numericalValue = new BigInteger(input.substring(2), 2);
                bytesOutput = numericalValue.toString(16);

                //Change the length of byte as per the passed in binary value
                input = input.replace("0B", "");
                int nibbleLength = input.length();
                int noNibbles = bytesOutput.length();
                if (nibbleLength % 4 == 0) {
                    nibbleLength = nibbleLength / 4;
                } else {
                    nibbleLength = (nibbleLength / 4) + 1;
                }

                for (int i = noNibbles; i < nibbleLength; i++) {
                    bytesOutput = "0" + bytesOutput;
                }

            } else {
                numericalValue = new BigInteger(input);
                bytesOutput = numericalValue.toString(16);
            }

            if (bytesOutput.length() % 2 != 0) {
                bytesOutput = "0" + bytesOutput;
            }

        } catch (NumberFormatException | NullPointerException exception) {
            bytesOutput = "";
        }
        //bytesOutput = bytesOutput.replaceAll(".(?!$).(?!$)", "$0 ").toUpperCase();
        return bytesOutput;
    }

    public static boolean addToArchive(List<String> filePathsList, String archivePath) throws FileNotFoundException, IOException {
        try (FileOutputStream fileOutputStream = new FileOutputStream(archivePath); ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream)) {
            for (String filePath : filePathsList) {
                File file = new File(filePath);
                if (!file.exists()) {
                    continue;
                }
                try (FileInputStream fileInputStream = new FileInputStream(file)) {
                    ZipEntry zipEntry = new ZipEntry(file.getName());
                    zipOutputStream.putNextEntry(zipEntry);

                    byte[] bytes = new byte[1024];
                    int length;
                    while ((length = fileInputStream.read(bytes)) >= 0) {
                        zipOutputStream.write(bytes, 0, length);
                    }
                }
            }
        }
        return new File(archivePath).exists() && new File(archivePath).isFile();
    }

    public static void createArchive(File directory, File archiveFile) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(archiveFile);
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream)) {
            zipSubDirectory("", directory, zipOutputStream);
        }
    }

    private static void zipSubDirectory(String root, File directory, ZipOutputStream zipOutputStream) throws IOException {
        byte[] buffer = new byte[4096];
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                String path = root + file.getName() + "/";
                zipOutputStream.putNextEntry(new ZipEntry(path));
                zipSubDirectory(path, file, zipOutputStream);
                zipOutputStream.closeEntry();
            } else {
                try (FileInputStream fileInputStream = new FileInputStream(file)) {
                    zipOutputStream.putNextEntry(new ZipEntry(root + file.getName()));
                    int length;
                    while ((length = fileInputStream.read(buffer)) > 0) {
                        zipOutputStream.write(buffer, 0, length);
                    }
                    zipOutputStream.closeEntry();
                }
            }
        }
    }

    public static boolean checkIfFileExists(String tabSchemaPath) {
        if (null == tabSchemaPath || tabSchemaPath.isEmpty()) {
            return false;
        }
        File file = new File(tabSchemaPath);
        return file.exists();
    }

    public static boolean validateFolderPath(String folderPath) {
        if (null != folderPath && !folderPath.isEmpty()) {
            File lastUsedFile = new File(folderPath);
            if (lastUsedFile.exists() && lastUsedFile.isDirectory()) {
                return true;
            }
        }
        return false;
    }

    public static boolean validateFilePath(String filePath) {
        if (null != filePath && !filePath.isEmpty()) {
            File lastUsedFile = new File(filePath);
            if (lastUsedFile.exists() && lastUsedFile.isFile()) {
                return true;
            }
        }
        return false;
    }

    public static String removeLineFeeds(String str) {
        str = str.replaceAll("\n", " ");
        str = str.replaceAll("\r", " ");
        return str;
    }

    public static String getPyStringValueSafe(String str) {
        if (str == null || str.isEmpty()) {
            return "None";
        }
        return str;
    }
    
//    public static void copyDafualtData() throws IOException {
//        try{
//        File infile = new File(Headers.DEVICE_DEFAULT_XML_FILE_PATH);
//        File outfile = new File(Headers.DEVICE_XML_FILE);
//        FileUtils.copyFile(infile, outfile);
//       }
//        catch(IOException e){
//            
//       }
//    }
//    
//   
    public static String replaceToPyBooleanValue(String str){
        return str.replaceAll("TRUE", "True").replaceAll("true", "True").replaceAll("FALSE", "False").replaceAll("false", "False");
    }
//    
//    public static File saveFile(Window dialogStage,String location,String fileExtension){
//         FileChooser fileChooser = new FileChooser();
//            //set title
//            fileChooser.setTitle("Save File");
//
//            //set initial directory
//            fileChooser.setInitialDirectory(new File(location));
//
//            //restricate for only xml files
//            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(fileExtension+" Files(*."+fileExtension+")", "*."+fileExtension);
//            fileChooser.getExtensionFilters().add(extFilter);
//
//            // Show the dialog and get the selected file
//            File file = fileChooser.showSaveDialog(dialogStage);
//          
//            return file;
//    }
//    
//    public static File chooseFile(Window dialogStage,String location,String fileExtension){
//         FileChooser fileChooser = new FileChooser();
//
//            //set title
//            fileChooser.setTitle("Choose File");
//
//            //set initial directory
//            fileChooser.setInitialDirectory(new File(location));
//
//            //restricate for only xml files
//            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(fileExtension+" Files(*."+fileExtension+")", "*."+fileExtension);
//            fileChooser.getExtensionFilters().add(extFilter);
//
//            // Show the dialog and get the selected file
//            File file = fileChooser.showOpenDialog(dialogStage);
//          
//            return file;
//    }

}
