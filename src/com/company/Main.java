package com.company;

import edu.duke.*;
import java.io.File;

public class Main {
    public double getPerimeter (Shape s) {

        double totalPerim = 0.0;
        Point prevPt = s.getLastPoint();

        for (Point currPt : s.getPoints()) {

            double currDist = prevPt.distance(currPt);
            totalPerim = totalPerim + currDist;
            prevPt = currPt;

        }

        return totalPerim;

    }

    public int getNumPoints (Shape s) {

        int number = 0;

        for (Point pt : s.getPoints()) {
            number++;
        }

        return number;

    }

    public double getAverageLength(Shape s) {

        return getPerimeter(s) / getNumPoints(s);

    }

    public double getLargestSide(Shape s) {

        double largestSide = 0.0;
        Point prevPt = s.getLastPoint();
        boolean lever = true;

        for (Point currPt : s.getPoints()) {

            double dist = prevPt.distance(currPt);
            if (lever) {
                largestSide = dist;
                lever = false;
            } else if (largestSide < dist) {
                largestSide = dist;
            }
            prevPt = currPt;

        }

        return largestSide;

    }

    public double getLargestX(Shape s) {

        boolean lever = true;
        double x = 0.0;

        for (Point pt : s.getPoints()) {
            if(lever){
                x = pt.getX();
                lever = false;
            } else if(x < pt.getX()){
                x = pt.getX();
            }
        }

        return x;
    }

    public double getLargestPerimeterMultipleFiles() {
        DirectoryResource dr = new DirectoryResource();
        boolean lever = true;
        double maxLength = 0.0;

        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double length = getPerimeter(s);
            if (lever) {
                maxLength = length;
                lever = false;
            } else if (maxLength < length) {
                maxLength = length;
            }
        }

        return maxLength;
    }

    public String getFileWithLargestPerimeter() {
        DirectoryResource dr = new DirectoryResource();
        boolean lever = true;
        double maxLength = 0.0;
        File temp = null;

        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double length = getPerimeter(s);

            if (lever) {
                maxLength = length;
                lever = false;
                temp = f;
            } else if (maxLength < length) {
                maxLength = length;
                temp = f;
            }
        }

        return temp.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource("datatest/datatest4.txt");
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("Perimeter = " + length);
        System.out.println("AmountPoint = " + getNumPoints(s));
        System.out.println("AverageLength = " + getAverageLength(s));
        System.out.println("LargestSide = " + getLargestSide(s));
        System.out.println("LargestX = " + getLargestX(s));
    }

    public void testPerimeterMultipleFiles() {
        System.out.println("LargestPerimeterMultipleFiles = " + getLargestPerimeterMultipleFiles());
    }

    public void testFileWithLargestPerimeter() {
        System.out.println("FileWithLargestPerimeter = " + getFileWithLargestPerimeter());
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = " + peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        Main pr = new Main();
        pr.testPerimeter();
        pr.testPerimeterMultipleFiles();
        pr.testFileWithLargestPerimeter();
    }
}