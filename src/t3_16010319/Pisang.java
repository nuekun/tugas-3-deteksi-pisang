/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package t3_16010319;

import java.util.ArrayList;
import java.util.List;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

/**
 *
 * @author Nue
 */
public class Pisang {
    
    String lokasi;

    public void detect(String lokasi) {
        this.lokasi = lokasi;
        
        Mat foto = Imgcodecs.imread(lokasi);
        Mat Ori = foto.clone();
        
        Imgproc.cvtColor(foto, foto, Imgproc.COLOR_BGR2GRAY);
        
        Imgproc.medianBlur(foto, foto, 21); 
        Imgproc.Canny(foto, foto, 45, 255);
        Imgproc.threshold(foto, foto, 100, 255, Imgproc.THRESH_OTSU);
        
        Mat hierarchy = new Mat();
        List<MatOfPoint> countours = new ArrayList<>();
        Imgproc.findContours(foto, countours, hierarchy, Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);
        Mat drawing = Mat.zeros(foto.size(), CvType.CV_8UC1);
          
        Scalar color = new Scalar(255);
    
        Rect kanan;
        Rect kiri;
        
        if (Imgproc.boundingRect(countours.get(0)).x < Imgproc.boundingRect(countours.get(2)).x) {
            kiri = Imgproc.boundingRect(countours.get(0));
            kanan = Imgproc.boundingRect(countours.get(2));              
        }else{
            kiri = Imgproc.boundingRect(countours.get(2));
            kanan = Imgproc.boundingRect(countours.get(0));                       
        }
        
        int lk1 = kiri.height*kiri.width ;
        int lk2 = kanan.height*kanan.width ;
         
        if(lk1<lk2){            
            countours.forEach((_item) -> {
                Imgproc.rectangle(Ori, kanan.tl(), kanan.br(), new Scalar(0,255,0), 1);
            });
                 
             }else{
            countours.forEach((_item) -> {
                Imgproc.rectangle(Ori, kiri.tl(), kiri.br(), new Scalar(0,255,0), 1);
            });
             }
            HighGui.imshow("hasil"+lokasi, Ori);
            Imgcodecs.imwrite("hasil"+lokasi, Ori);
           
    }

}
