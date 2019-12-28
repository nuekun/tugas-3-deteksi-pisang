/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package t3_16010319;

/**
 *
 * @author Nue
 */

 
import org.opencv.core.Core;
import org.opencv.highgui.HighGui;

public class Main {
    
    
    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Pisang pisang = new Pisang();
        pisang.detect("1.jpg");
        pisang.detect("2.jpg");
        pisang.detect("3.jpg");
        pisang.detect("4.jpg");
        HighGui.waitKey();
        System.exit(0);
    }
    
}
