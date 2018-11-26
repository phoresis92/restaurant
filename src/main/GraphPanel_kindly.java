package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import dao.AdminSalesDAO;

public class GraphPanel_kindly extends JPanel {

    private int width = 800; //프레임 넓이
    private int heigth = 400; //프레임 높이
    private int padding = 25; //여백
    private int labelPadding = 25; //x축y축 글씨 여백
    private Color lineColor = new Color(44, 102, 230, 180); // 그래프선 색깔
    private Color pointColor = new Color(100, 100, 100, 180); //점색깔
    private Color gridColor = new Color(200, 200, 200, 200); // 그리드 색깔
    private static final Stroke GRAPH_STROKE = new BasicStroke(3f); //그래프 선 넓이
    private int pointWidth = 4; // 점 크기
    private int numberYDivisions = 7; //y축 범위 개수
    private static List<Double> main;
    private static List<Double> side;
    private static List<Double> drink;
    
    private double max_sales = 700;//만원
    private double min_sales = 100;
    

    public GraphPanel_kindly(List<Double> main, List<Double> side, List<Double> drink) {
        this.main = main;
        this.side = side;
        this.drink = drink;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        double xScale = ((double) getWidth() - (2 * padding) - labelPadding) / (main.size() - 1);
        //x축 단위당 거리
        double yScale = ((double) getHeight() - 2 * padding - labelPadding) / (max_sales*10000 - 0);
        //y축 단위당 거리
        
        List<Point> maingraphPoints = new ArrayList<>(); // Point x,y 점추가
        for (int i = 0; i < main.size(); i++) {
            int x1 = (int) (i * xScale + padding + labelPadding);
            int y1 = (int) ((max_sales*10000 - main.get(i)) * yScale + padding);
            
            maingraphPoints.add(new Point(x1, y1));
        }
        
        List<Point> sidegraphPoints = new ArrayList<>(); // Point x,y 점추가
        for (int i = 0; i < side.size(); i++) {
            int x1 = (int) (i * xScale + padding + labelPadding);
            int y1 = (int) ((max_sales*10000 - drink.get(i)*1) * yScale + padding);
            
            sidegraphPoints.add(new Point(x1, y1));
        }

        List<Point> drinkgraphPoints = new ArrayList<>(); // Point x,y 점추가
        for (int i = 0; i < drink.size(); i++) {
            int x1 = (int) (i * xScale + padding + labelPadding);
            int y1 = (int) ((max_sales*10000 - drink.get(i)*2) * yScale + padding);
            drinkgraphPoints.add(new Point(x1, y1));
        }
        
        
        
//=============================================================================================        
        
        
        // draw white background
        g2.setColor(Color.WHITE); //배경색
        g2.fillRect(padding + labelPadding, padding, getWidth() - (2 * padding) - labelPadding, getHeight() - 2 * padding - labelPadding);
        //배경색 범위 정하기
        
               
//=============================================================================================        

        
        // create hatch marks and grid lines for y axis. //y축 점찍기
        for (int i = 0; i < numberYDivisions + 1; i++) {
            int x0 = padding + labelPadding;
            int x1 = pointWidth + padding + labelPadding;
            int y0 = getHeight() - ((i * (getHeight() - padding * 2 - labelPadding)) / numberYDivisions + padding + labelPadding);
            int y1 = y0;
            if (main.size() > 0) {
                g2.setColor(gridColor);
                g2.drawLine(padding + labelPadding + 1 + pointWidth, y0, getWidth() - padding, y1);
                g2.setColor(Color.BLACK); // y축 글씨 색 설정
                String yLabel = (int) i*100 + "만원";
                FontMetrics metrics = g2.getFontMetrics(); // 객체 생성
                int labelWidth = metrics.stringWidth(yLabel); //글씨 입력
                g2.drawString(yLabel, x0 - labelWidth - 5, y0 + (metrics.getHeight() / 2) - 3);
            }
            g2.drawLine(x0, y0, x1, y1);
        }
                  
           
                

        // and for x axis x 축 점찍기
        for (int i = 0; i < main.size(); i++) {
            if (main.size() > 1) {
                int x0 = i * (getWidth() - padding * 2 - labelPadding) / (main.size() - 1) + padding + labelPadding;
                int x1 = x0;
                int y0 = getHeight() - padding - labelPadding;
                int y1 = y0 - pointWidth;
                    g2.setColor(gridColor);
                    g2.drawLine(x0, getHeight() - padding - labelPadding - 1 - pointWidth, x1, padding);
                    g2.setColor(Color.BLACK); //x축 글씨 색 설정
                    String xLabel = i+1+"월";
                    
                    FontMetrics metrics = g2.getFontMetrics(); //객체 생성
                    int labelWidth = metrics.stringWidth(xLabel); // 글씨 입력
                    g2.drawString(xLabel, x0 - labelWidth / 2, y0 + metrics.getHeight() + 3);
                
                g2.drawLine(x0, y0, x1, y1);
            }
        }
        
//=============================================================================================        
        
        
        // create x and y axes 
        g2.drawLine(padding + labelPadding, getHeight() - padding - labelPadding, padding + labelPadding, padding);
        //y축 밑선
        g2.drawLine(padding + labelPadding, getHeight() - padding - labelPadding, getWidth() - padding, getHeight() - padding - labelPadding);
        //x축 밑선
        
        
//=============================================================================================        

        Stroke oldStroke = g2.getStroke();
        g2.setColor(Color.BLACK);
        g2.setStroke(GRAPH_STROKE);
        for (int i = 0; i < maingraphPoints.size() - 1; i++) { //그래프 선그리기
            int x1 = maingraphPoints.get(i).x;
            int y1 = maingraphPoints.get(i).y;
            int x2 = maingraphPoints.get(i + 1).x;
            int y2 = maingraphPoints.get(i + 1).y;
            
            g2.drawLine(x1, y1, x2, y2);
        }
        
        oldStroke = g2.getStroke();
        g2.setColor(Color.GREEN);
        g2.setStroke(GRAPH_STROKE);
        for (int i = 0; i < sidegraphPoints.size() - 1; i++) { //그래프 선그리기
            int x1 = sidegraphPoints.get(i).x;
            int y1 = sidegraphPoints.get(i).y;
            int x2 = sidegraphPoints.get(i + 1).x;
            int y2 = sidegraphPoints.get(i + 1).y;
            
            g2.drawLine(x1, y1, x2, y2);
        }
        
        oldStroke = g2.getStroke();
        g2.setColor(Color.PINK);
        g2.setStroke(GRAPH_STROKE);
        for (int i = 0; i < drinkgraphPoints.size() - 1; i++) { //그래프 선그리기
            int x1 = drinkgraphPoints.get(i).x;
            int y1 = drinkgraphPoints.get(i).y;
            int x2 = drinkgraphPoints.get(i + 1).x;
            int y2 = drinkgraphPoints.get(i + 1).y;
            
            g2.drawLine(x1, y1, x2, y2);
        }
        
        
        
        
        g2.setStroke(oldStroke);
        g2.setColor(pointColor);
        for (int i = 0; i < maingraphPoints.size(); i++) { 
            int x = maingraphPoints.get(i).x - pointWidth / 2;
            int y = maingraphPoints.get(i).y - pointWidth / 2;
            
            int ovalW = pointWidth;
            int ovalH = pointWidth;
            g2.fillOval(x, y, ovalW, ovalH);
        }
        
        g2.setStroke(oldStroke);
        g2.setColor(pointColor);
        for (int i = 0; i < sidegraphPoints.size(); i++) { 
            int x = sidegraphPoints.get(i).x - pointWidth / 2;
            int y = sidegraphPoints.get(i).y - pointWidth / 2;
            
            int ovalW = pointWidth;
            int ovalH = pointWidth;
            g2.fillOval(x, y, ovalW, ovalH);
        }
        
        g2.setStroke(oldStroke);
        g2.setColor(pointColor);
        for (int i = 0; i < drinkgraphPoints.size(); i++) { 
            int x = drinkgraphPoints.get(i).x - pointWidth / 2;
            int y = drinkgraphPoints.get(i).y - pointWidth / 2;
            
            int ovalW = pointWidth;
            int ovalH = pointWidth;
            g2.fillOval(x, y, ovalW, ovalH);
        }
    }


//=============================================================================================        
    
    
//    @Override
//    public Dimension getPreferredSize() {
//        return new Dimension(width, heigth);
//    }
    private double getMinScore() {
        double minScore = Double.MAX_VALUE;
        for (Double score : main) {
            minScore = Math.min(minScore, score);
        }
        return minScore;
    }

    private double getMaxScore() {
        double maxScore = Double.MIN_VALUE;
        for (Double score : main) {
            maxScore = Math.max(maxScore, score);
        }
        return maxScore;
    }

/*    public void setScores(List<Double> scores) {
        this.scores = scores;
        invalidate();
        this.repaint();
    }

    public List<Double> getScores() {
        return scores;
    }*/

    
//=============================================================================================        
    
    
    
    private static void createAndShowGui(List<Double> main, List<Double> side, List<Double> drink) {
       
        
        
        GraphPanel_kindly mainPanel = new GraphPanel_kindly(main, side, drink);
        mainPanel.setPreferredSize(new Dimension(800, 600));
        JFrame frame = new JFrame("종류별 매출 현황 그래프");
        frame.getContentPane().add(mainPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setAlwaysOnTop(true);
    }

    public static void main(String[] args) {
      SwingUtilities.invokeLater(new Runnable() {
         public void run() {
            createAndShowGui(main, side, drink);
         }
      });
   }
}