package utils;

import javax.swing.*;

/**
 * Created by venik on 08.01.17.
 */
public class Reporter {
    public static void reportError(String title,String message){
        JOptionPane.showMessageDialog(null,
                message,
                title,
                JOptionPane.ERROR_MESSAGE);
    }

    public static void reportOk(String title,String message){
        JOptionPane.showMessageDialog(null,
                message,
                title,
                JOptionPane.INFORMATION_MESSAGE);
    }

    public static void reportWarn(String title,String message){
        JOptionPane.showMessageDialog(null,
                message,
                title,
                JOptionPane.WARNING_MESSAGE);
    }
}
